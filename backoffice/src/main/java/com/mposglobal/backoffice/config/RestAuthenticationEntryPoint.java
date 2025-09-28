package com.mposglobal.backoffice.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;

/**
 * Implementación de {@link AuthenticationEntryPoint} personalizada para Spring Security.
 * <p>
 * Este punto de entrada se activa cuando un usuario intenta acceder a un recurso protegido
 * pero no está autenticado (es decir, no ha proporcionado credenciales válidas o no ha iniciado
 * sesión). En un entorno REST, esto generalmente se traduce en la falta de un token JWT válido.
 * </p>
 * <p>
 * Su función es devolver una respuesta HTTP clara, indicando que se requiere autenticación.
 * </p>
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	/**
     * Se invoca cuando una solicitud no autenticada intenta acceder a un recurso protegido.
     * <p>
     * Aunque el estándar REST para 'no autenticado' es típicamente 401 (Unauthorized),
     * este método sigue el requerimiento de la aplicación y devuelve 403 (Forbidden),
     * informando al cliente que la autenticación es requerida.
     * </p>
     *
     * @param request La solicitud HTTP que causó la excepción.
     * @param response La respuesta HTTP donde se escribirá la respuesta de error.
     * @param authException La excepción lanzada por Spring Security que indica la falla de autenticación.
     * @throws IOException Si ocurre un error de I/O al escribir la respuesta.
     */
	  @Override
	  public void commence(HttpServletRequest request, HttpServletResponse response,
	                       AuthenticationException authException) throws IOException {
	    // Requerimiento: peticiones sin autenticacion => 403
	    response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
	    response.setContentType("application/json;charset=UTF-8");
	    response.getWriter().write("{\"error\":\"Acceso Denegado - Se requiere autenticación\"}");
	  }
}
