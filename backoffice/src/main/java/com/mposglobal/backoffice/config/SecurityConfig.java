package com.mposglobal.backoffice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase de configuración principal para Spring Security.
 * <p>
 * Define la cadena de filtros de seguridad, incluyendo la deshabilitación de CSRF,
 * la configuración de las reglas de autorización basadas en roles (Role-Based Access Control - RBAC)
 * y la integración del filtro de autenticación JWT.
 * </p>
 */
@Configuration
public class SecurityConfig {
	
  private final JwtProvider jwtProvider;
  
  /**
   * Constructor para inyectar el proveedor de JWT.
   *
   * @param jwtProvider El componente encargado de generar y validar tokens JWT.
   */
  	public SecurityConfig(JwtProvider jwtProvider) {
	  this.jwtProvider = jwtProvider;
  	}
	
  	/**
     * Define la cadena de filtros de seguridad HTTP principal.
     * <p>
     * 1. Deshabilita CSRF (típico en APIs REST).
     * 2. Configura las reglas de autorización de acceso a las rutas.
     * 3. Define manejadores personalizados para excepciones de autenticación/autorización.
     * 4. Añade el filtro JWT personalizado antes del filtro de autenticación estándar de Spring.
     * </p>
     *
     * @param http Objeto HttpSecurity para configurar las reglas de seguridad.
     * @return La cadena de filtros de seguridad configurada.
     * @throws Exception Si ocurre un error al construir la cadena de filtros.
     */
	 @Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    JwtAuthFilter jwtFilter = new JwtAuthFilter(jwtProvider);
	
	    http
	    
	    .csrf(csrf -> csrf.disable()) 
	    
	    
	    .authorizeHttpRequests(auth -> auth
	        .requestMatchers("/auth/**", "/swagger-ui.html/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
	        .requestMatchers("/users/**").hasAnyRole("ADMIN")
	        .requestMatchers("/categories/**","/products/**").hasAnyRole("ADMIN","USER")
	        .anyRequest().authenticated()
	    )
	    
	    
	    .exceptionHandling(exceptions -> exceptions
	        .authenticationEntryPoint(new RestAuthenticationEntryPoint()) // 403 
	        .accessDeniedHandler(new RestAccessDeniedHandler()) // 401 
	    )
	    
	    
	    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	
	    return http.build();
	  }
}
