package com.mposglobal.backoffice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;

import java.io.IOException;
import java.util.List;

/**
 * Filtro de autenticación JWT.
 * <p>
 * Esta clase intercepta cada solicitud entrante para buscar un token JWT en el encabezado
 * 'Authorization' (formato Bearer). Si encuentra un token válido, extrae la información del usuario
 * (username y rol) y establece la autenticación en el contexto de seguridad de Spring.
 * </p>
 */
public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtProvider provider;
  
  /**
   * Constructor para inyectar el proveedor de JWT (JwtProvider).
   *
   * @param provider El proveedor de tokens JWT responsable de la creación y validación.
   */
  public JwtAuthFilter(JwtProvider provider) { this.provider = provider; }

  /**
   * Implementación principal del filtro.
   * <p>
   * Procesa la solicitud HTTP para verificar la presencia y validez de un token JWT.
   * Si el token es válido, se extraen las claims (cuerpo del token) y se crea un objeto
   * de autenticación para el contexto de seguridad de Spring. Si el token no existe,
   * no es válido o está expirado, la solicitud continúa sin autenticación forzada.
   * </p>
   *
   * @param request La solicitud HTTP que se está procesando.
   * @param response La respuesta HTTP.
   * @param chain La cadena de filtros para continuar el flujo de la solicitud.
   * @throws ServletException Si ocurre un error de servlet.
   * @throws IOException Si ocurre un error de I/O.
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    String header = request.getHeader("Authorization");
    if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
      String token = header.substring(7);
      try {
        var claims = provider.validateToken(token).getBody();
        String username = claims.getSubject();
        String role = (String) claims.get("role");
        var auth = new UsernamePasswordAuthenticationToken(username, null,
            List.of(new SimpleGrantedAuthority("ROLE_" + role)));
        SecurityContextHolder.getContext().setAuthentication(auth);
      } catch (JwtException ex) {
        // invalid token => don't set authentication
      }
    }
    chain.doFilter(request, response);
  }
}
