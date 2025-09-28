package com.mposglobal.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mposglobal.backoffice.entity.Product;

/**
 * Interfaz de repositorio para la entidad {@link Product}.
 * <p>
 * Extiende {@link JpaRepository}, proporcionando métodos CRUD (Create, Read, Update)
 * estándar y funcionalidad de paginación/ordenamiento para la entidad {@code Product}.
 * <p>
 * Esta interfaz está tipificada para trabajar con la entidad {@code Product} y su
 * clave primaria de tipo {@code Long}.
 * </p>
 */
public interface ProductRepository extends JpaRepository<Product,Long> {
	  
}
