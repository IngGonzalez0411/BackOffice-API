package com.mposglobal.backoffice.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import java.io.IOException;

/**
 * Implementación de {@link AccessDeniedHandler} personalizada para Spring Security.
 * <p>
 * Este manejador se activa cuando un usuario ya autenticado (es decir, que tiene un token válido),
 * intenta acceder a un recurso para el cual su rol o sus permisos son insuficientes.
 * </p>
 * <p>
 * Su principal responsabilidad es devolver una respuesta HTTP 401 (Unauthorized)
 * o 403 (Forbidden), junto con un mensaje de error legible en formato JSON.
 * </p>
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {
	
	/**
	 * Maneja los errores de acceso denegado.
	 * <p>
	 * Al detectar que un usuario autenticado no tiene los permisos necesarios,
	 * establece el código de estado HTTP en 401 (Unauthorized) y escribe
	 * un mensaje de error descriptivo en el cuerpo de la respuesta en formato JSON.
	 * </p>
	 *
	 * @param request La solicitud HTTP que fue denegada.
	 * @param response La respuesta HTTP donde se escribirá la respuesta de error.
	 * @param accessDeniedException La excepción lanzada por Spring Security.
	 * @throws IOException Si ocurre un error de I/O al escribir la respuesta.
	 */
	  @Override
	  public void handle(HttpServletRequest request, HttpServletResponse response,
	                     AccessDeniedException accessDeniedException) throws IOException {
	    // Requerimiento: peticiones no autorizadas => 401
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
	    response.setContentType("application/json;charset=UTF-8");
	    response.getWriter().write("{\"error\":\"No tiene autorización - sus privilegios son insuficientes\"}");
	  }
}