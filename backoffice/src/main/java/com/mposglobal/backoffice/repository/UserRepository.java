package com.mposglobal.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mposglobal.backoffice.entity.User;

import java.util.Optional;

/**
 * Interfaz de repositorio para la entidad {@link User}.
 * <p>
 * Extiende {@link JpaRepository}, proporcionando métodos CRUD estándar para la gestión de usuarios.
 * Está tipificada para trabajar con la entidad {@code User} y su clave primaria de tipo {@code Long}.
 * </p>
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
     * Busca un usuario en la base de datos por su nombre de usuario.
     * <p>
     * Este método se utiliza típicamente durante el proceso de autenticación (login).
     * </p>
     *
     * @param username El nombre de usuario que se desea buscar.
     * @return Un {@link Optional} que contiene el objeto {@code User} si se encuentra, o un {@code Optional} vacío si no existe.
     */
	Optional<User> findByUsername(String username);
  
}
