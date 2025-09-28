package com.mposglobal.backoffice.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.mposglobal.backoffice.config.JwtProvider;
import com.mposglobal.backoffice.dto.AuthResponse;
import com.mposglobal.backoffice.dto.LoginRequest;
import com.mposglobal.backoffice.entity.User;
import com.mposglobal.backoffice.exceptions.LoginException;
import com.mposglobal.backoffice.service.UserService;
import com.mposglobal.backoffice.util.Constant;

/**
 * Controlador REST para manejar la autenticación y la gestión de tokens.
 * <p>
 * Proporciona el endpoint de login para verificar credenciales y emitir un token JWT
 * para el acceso a recursos protegidos.
 * </p>
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Endpoints para el inicio de sesión y emisión de tokens JWT.")
public class AuthController {
  private final UserService userService;
  private final JwtProvider jwtProvider;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  /**
   * Constructor para inyección de dependencias de servicios.
   *
   * @param userService El servicio de usuarios para la búsqueda y actualización.
   * @param jwtProvider El proveedor de JWT para la generación de tokens.
   */
  public AuthController(UserService userService, JwtProvider jwtProvider) {
    this.userService = userService;
    this.jwtProvider = jwtProvider;
  }

  /**
   * Procesa la solicitud de inicio de sesión, verifica las credenciales y emite un token JWT.
   *
   * @param req El DTO LoginRequest con el nombre de usuario y la contraseña en texto plano.
   * @return El DTO AuthResponse que contiene el token JWT, el nombre de usuario y el nivel de acceso.
   */
  @PostMapping("/login")
  @Operation(summary = "Inicia sesión y genera un token JWT",
             description = "Verifica las credenciales y, si son válidas, actualiza el último ingreso y emite un token.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
                   description = "Autenticación exitosa. Token JWT emitido.",
                   content = @Content(mediaType = "application/json",
                                      schema = @Schema(implementation = AuthResponse.class)))
  })
  public AuthResponse login(@RequestBody LoginRequest req) {
      User u = userService.findByUsername(req.getUsername())
                .orElseThrow(() -> new LoginException(Constant.ERROR_USER_INVALID));

      
      if (!encoder.matches(req.getPassword(), u.getClave()) || !Constant.ACTIVO.equals(u.getEstado())) {
          throw new LoginException(Constant.ERROR_CRED_INVALID);
      }
      Long userId = u.getId();

      userService.updateLastLoginDate(userId);

      String token = jwtProvider.generateToken(u.getUsername(), u.getNivelAcceso());
      return new AuthResponse(token, u.getUsername(), u.getNivelAcceso());
  }
}
