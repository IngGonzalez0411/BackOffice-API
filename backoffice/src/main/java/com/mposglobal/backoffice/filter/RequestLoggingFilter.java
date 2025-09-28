package com.mposglobal.backoffice.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mposglobal.backoffice.util.Constant;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Filtro de registro de peticiones (Request Logging Filter).
 * <p>
 * Esta clase intercepta cada solicitud HTTP para registrar metadatos clave
 * (fecha/hora, IP remota, método y URI) en un archivo de log externo.
 * Extiende {@link OncePerRequestFilter} para asegurar que se ejecuta solo una vez por solicitud.
 * </p>
 */
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {
  
  // La ruta del archivo de log se toma directamente de una variable de entorno del sistema.
  private String logFile = System.getenv("ENV_VAR_LOGPATH");

  /**
   * Implementación principal del filtro que realiza el logging.
   * <p>
   * 1. Verifica que la variable de entorno {@code ENV_VAR_LOGPATH} esté definida; si no, lanza una excepción.
   * 2. Formatea una línea de log con la información de la solicitud.
   * 3. Intenta escribir la línea en el archivo de log en modo de añadir (append).
   * 4. En caso de que la escritura del log falle (permisos, archivo no encontrado, etc.), el error
   * es silenciado para asegurar que la solicitud HTTP original pueda continuar sin interrupciones.
   * </p>
   *
   * @param req La solicitud HTTP.
   * @param res La respuesta HTTP.
   * @param chain La cadena de filtros para continuar el flujo.
   * @throws ServletException Si ocurre un error interno del servlet.
   * @throws IOException Si ocurre un error de I/O, especialmente al verificar la ruta del log.
   * @throws IllegalStateException Si la variable de entorno {@code ENV_VAR_LOGPATH} no está definida.
   */
  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws ServletException, IOException {
	
	  if (logFile == null || logFile.isEmpty()) {
	      throw new IllegalStateException(Constant.ERROR_LOGPATH_INVALID);
	  }
	  
    String line = String.format("%s | %s | %s | %s%n",
        LocalDateTime.now(), req.getRemoteAddr(), req.getMethod(), req.getRequestURI());
    try (FileWriter fw = new FileWriter(logFile, true)) {
      fw.write(line);
    } catch (Exception e) {
      // no bloquear petición si logging falla
    }
    chain.doFilter(req, res);
  }
}
