package com.mposglobal.backoffice.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

/**
 * Componente proveedor de JWT (JSON Web Token).
 * <p>
 * Se encarga de la creación, firma y validación de los tokens JWT
 * utilizados para la autenticación y autorización en la aplicación.
 * La clave secreta y el tiempo de expiración se inyectan desde las propiedades de la aplicación.
 * </p>
 */
@Component
public class JwtProvider {
  private final Key key;
  private final long expirationMillis;

  /**
   * Constructor que inyecta los valores de configuración para la clave secreta y la expiración.
   * <p>
   * Inicializa la clave de firma (HMAC-SHA) y calcula el tiempo de expiración en milisegundos.
   * </p>
   *
   * @param secret La clave secreta para la firma del token, obtenida de ${app.jwt.secret}.
   * @param expirationMinutes El tiempo de vida del token en minutos, obtenido de ${app.jwt.expiration-minutes}.
   */
  public JwtProvider(@Value("${app.jwt.secret}") String secret,
                     @Value("${app.jwt.expiration-minutes}") long expirationMinutes) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
    this.expirationMillis = expirationMinutes * 60 * 1000;
  }

  //-------------------------------------------------------------------------
  
  /**
   * Genera un nuevo token JWT para un usuario y rol específicos.
   *
   * @param username El nombre de usuario (subject) para el token.
   * @param role El rol del usuario, añadido como una 'claim' personalizada.
   * @return El token JWT serializado (compactado) como una cadena.
   */
  public String generateToken(String username, String role) {
    Date now = new Date();
    Date exp = new Date(now.getTime() + expirationMillis);
    return Jwts.builder()
      .setSubject(username)
      .claim("role", role)
      .setIssuedAt(now)
      .setExpiration(exp)
      .signWith(key)
      .compact();
  }

  //-------------------------------------------------------------------------
  
  /**
   * Valida la firma del token JWT y parsea las claims (cuerpo).
   * <p>
   * Este método verifica la integridad del token y si ha expirado. Si el token es
   * inválido o la firma no coincide, lanzará una {@code JwtException}.
   * </p>
   *
   * @param token El token JWT como cadena de texto.
   * @return El objeto Jws (firmado) que contiene las Claims (cuerpo del token).
   * @throws io.jsonwebtoken.JwtException Si el token no es válido, ha expirado, o la firma es incorrecta.
   */
  public Jws<Claims> validateToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
  }
  
}
