package com.mposglobal.backoffice.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mposglobal.backoffice.dto.UserRequest;
import com.mposglobal.backoffice.dto.UserResponse;
import com.mposglobal.backoffice.entity.User;
import com.mposglobal.backoffice.repository.UserRepository;
import com.mposglobal.backoffice.util.Constant;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Servicio central para la gestión de usuarios y la lógica de autenticación (cifrado de claves).
 * <p>
 * Implementa las operaciones CRUD y de control de estado (activar/desactivar) sobre la
 * entidad {@code User}. Es responsable de manejar la seguridad de la clave del usuario
 * cifrándola usando {@link BCryptPasswordEncoder} antes de la persistencia y de
 * mapear las entidades a DTOs para su exposición en la capa de controlador.
 * </p>
 */
@Service
public class UserService {
  private final UserRepository repo;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  /**
   * Constructor para inyectar el repositorio de usuarios.
   *
   * @param repo El repositorio JPA para el acceso a datos de usuarios.
   */
  public UserService(UserRepository repo) { 
	  this.repo = repo;
  }

//-------------------------------------------------------------------------
  // Métodos de Lógica de Negocio Adaptados
  // -------------------------------------------------------------------------

  /**
   * Crea un nuevo usuario en el sistema.
   * La contraseña se cifra con BCrypt y el estado se establece por defecto a "ACTIVO".
   *
   * @param request El DTO con los datos del nuevo usuario.
   * @return El DTO de respuesta del usuario creado.
   */
  public UserResponse create(UserRequest request) {
      User u = convertToEntity(request);
      
      // 1. Cifrar clave
      u.setClave(encoder.encode(u.getClave()));
      
      // Nota: fechaCreacion se inicializa en la entidad.
      
      User savedUser = repo.save(u);
      return convertToResponse(savedUser);
  }

  /**
   * Busca un usuario por su nombre de usuario.
   *
   * @param username El nombre de usuario a buscar.
   * @return Un Optional que contiene el DTO de respuesta si se encuentra.
   */
  public Optional<User> findByUsername(String username) {
      return repo.findByUsername(username); // Mapea la entidad a DTO si está presente
  }

  /**
   * Obtiene una lista de todos los usuarios registrados.
   *
   * @return Una lista de DTOs de respuesta de usuarios.
   */
  public List<UserResponse> findAll() {
      return repo.findAll().stream()
              .map(this::convertToResponse)
              .toList();
  }

  /**
   * Actualiza los datos de un usuario existente, buscando por ID.
   *
   * @param request El DTO con los datos a actualizar. Se asume que el ID es válido.
   * @return El DTO de respuesta del usuario actualizado.
   * @throws RuntimeException Si el usuario con el ID proporcionado no se encuentra.
   */
  public UserResponse update(UserRequest request) {

      // 1. Intentar encontrar el usuario existente usando el ID del Request
      return repo.findById(request.getId()).map(existing -> {
          
          // 2. Aplicar solo los campos que vienen del DTO
          existing.setNombreCompleto(request.getNombreCompleto());
          existing.setUsername(request.getUsername());
          existing.setNivelAcceso(request.getNivelAcceso());
          existing.setEstado(Constant.ACTIVO);
          
          // 3. La clave solo se actualiza si se envía un valor y se cifra.
          if (request.getClave() != null && !request.getClave().isEmpty()) {
              existing.setClave(encoder.encode(request.getClave()));
          }

          // 4. Guardar y retornar DTO
          User updatedUser = repo.save(existing);
          return convertToResponse(updatedUser);

      }).orElseThrow(() -> 
          new RuntimeException(Constant.ERROR_NOFOUND + request.getId())
      );
  }
  
  /**
   * Actualiza la fecha del último inicio de sesión (login) para un usuario específico.
   *
   * <p>El método busca el usuario por su ID y, si la entidad existe, actualiza
   * únicamente el campo de la fecha del último ingreso con la fecha y hora
   * del sistema actual.
   *
   * @param userId el identificador único (ID) del usuario cuya fecha de
   * último ingreso se desea actualizar. Debe ser un valor no nulo.
   * @see #repo.findById(Object)
   * @see #user.setFechaUltimoIngreso(java.util.Date)
   * @see #repo.save(Object)
   */
  public void updateLastLoginDate(Long userId) {
	    // Buscar la entidad existente para evitar sobrescribir datos
	    repo.findById(userId).ifPresent(user -> {
	        
	        // **SOLO** actualizar la fecha de ingreso
	        user.setFechaUltimoIngreso(new Date()); 
	        
	        // Persistir (la clave NO se toca en este proceso)
	        repo.save(user); 
	    });
	}

  /**
   * Desactiva lógicamente un usuario (soft deactivate) cambiándole el estado.
   * <p>
   * Busca al usuario por su ID y, si está presente, establece su estado a "DESACTIVADO"
   * y persiste el cambio. Si el usuario no existe, la operación se ignora silenciosamente.
   * </p>
   *
   * @param id El ID del usuario que se desea desactivar.
   */
  public void softDeactivate(Long id) {
    repo.findById(id).ifPresent(u -> {
      u.setEstado(Constant.DESACTIVADO);
      repo.save(u);
    });
  }
  
//-------------------------------------------------------------------------
  // Métodos de Mapeo Interno
  // -------------------------------------------------------------------------

  /**
   * Mapea un DTO de Solicitud {@code UserRequest} a la entidad de persistencia {@code User}.
   *
   * @param request El DTO de solicitud.
   * @return La entidad {@code User} lista para ser persistida.
   */
  private User convertToEntity(UserRequest request) {
      User user = new User();
      // Nota: Solo seteamos los campos que vienen del request.
      user.setId(request.getId()); // Útil para update
      user.setNombreCompleto(request.getNombreCompleto());
      user.setUsername(request.getUsername());
      user.setClave(request.getClave()); 
      user.setNivelAcceso(request.getNivelAcceso());
      user.setEstado(Constant.ACTIVO);
      user.setFechaCreacion(new Date());
      return user;
  }
  
  /**
   * Mapea una entidad de persistencia {@code User} a un DTO de respuesta {@code UserResponse}.
   *
   * @param user La entidad {@code User} a mapear.
   * @return El DTO de respuesta listo para ser enviado al controlador.
   */
  private UserResponse convertToResponse(User user) {
      UserResponse response = new UserResponse();
      response.setId(user.getId());
      response.setNombreCompleto(user.getNombreCompleto());
      response.setUsername(user.getUsername());
      
      response.setFechaCreacion(user.getFechaCreacion());
      response.setFechaUltimoIngreso(user.getFechaUltimoIngreso());
      response.setNivelAcceso(user.getNivelAcceso());
      response.setEstado(user.getEstado());
      return response;
  }
  
}
