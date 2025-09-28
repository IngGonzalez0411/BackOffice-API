package com.mposglobal.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mposglobal.backoffice.entity.Category;

/**
 * Interfaz de repositorio para la entidad {@link Category}.
 * <p>
 * Extiende {@link JpaRepository}, proporcionando métodos CRUD (Create, Read, Update)
 * estándar y funcionalidad de paginación/ordenamiento para la entidad {@code Category}.
 * <p>
 * Esta interfaz está tipificada para trabajar con la entidad {@code Category} y su
 * clave primaria de tipo {@code Long}.
 * </p>
 */
public interface CategoryRepository extends JpaRepository<Category,Long>{

}
