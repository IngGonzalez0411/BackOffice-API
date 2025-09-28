package com.mposglobal.backoffice.service;

import org.springframework.stereotype.Service;

import com.mposglobal.backoffice.dto.CategoryRequest;
import com.mposglobal.backoffice.dto.CategoryResponse;
import com.mposglobal.backoffice.entity.Category;
import com.mposglobal.backoffice.repository.CategoryRepository;
import com.mposglobal.backoffice.util.Constant;

import java.util.Date;
import java.util.List;

/**
 * Servicio central para la gestión de categorías.
 * <p>
 * Implementa la lógica de negocio para las operaciones CRUD (Crear, Leer, Actualizar, Desactivar)
 * sobre la entidad {@code Category}, manejando la conversión entre DTOs de solicitud/respuesta
 * y la entidad de persistencia.
 * </p>
 */
@Service
public class CategoryService {

    private final CategoryRepository repo;

    /**
     * Constructor para inyectar el repositorio de categorías.
     *
     * @param repo El repositorio JPA para el acceso a datos de categorías.
     */
    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    // -------------------------------------------------------------------------
    // Métodos de Lógica de Negocio
    // -------------------------------------------------------------------------

    /**
     * Busca y devuelve todas las categorías que se encuentran en estado "ACTIVO".
     * <p>
     * Este método realiza un filtro y mapea las entidades {@code Category} a DTOs {@code CategoryResponse}.
     * </p>
     *
     * @return Una lista de {@code CategoryResponse} de todas las categorías activas.
     */
    public List<CategoryResponse> findAllActive() {
        return repo.findAll()
                .stream()
                .filter(c -> Constant.ACTIVO.equalsIgnoreCase(c.getEstado()))
                .map(this::convertToResponse) // aquí mapeas Category -> CategoryResponse
                .toList();
    }

    /**
     * Crea una nueva categoría con estado inicial "ACTIVO".
     * <p>
     * Realiza el mapeo de {@code CategoryRequest} a la entidad, asigna valores del sistema
     * (fecha de creación, actualización y estado) y luego mapea la entidad guardada a {@code CategoryResponse}.
     * </p>
     *
     * @param request El DTO de solicitud con los datos de la categoría.
     * @return El DTO de respuesta de la categoría creada.
     */
    public CategoryResponse create(CategoryRequest request) {
        Category c = new Category();
        
        c.setNombre(request.getNombre());
        
        // 2. Asignar valores del sistema (buenas prácticas)
        c.setEstado(Constant.ACTIVO); // Estado por defecto
        Date now = new Date();
        c.setFechaCreacion(now);
        c.setFechaActualizacion(now);
        
        Category savedCategory = repo.save(c);
        
        return convertToResponse(savedCategory);
    }

    /**
     * Actualiza una categoría existente.
     * <p>
     * Si la categoría existe, aplica los cambios del DTO de solicitud (nombre, estado opcional)
     * y actualiza la fecha de modificación. Si la categoría no existe, lanza una excepción.
     * </p>
     *
     * @param id El ID de la categoría a actualizar.
     * @param request El DTO de solicitud con los nuevos datos.
     * @return El DTO de respuesta de la categoría actualizada.
     * @throws RuntimeException Si la categoría con el ID especificado no se encuentra.
     */
    public CategoryResponse update(Long id, CategoryRequest request) {
        return repo.findById(id).map(existing -> {
            
            existing.setNombre(request.getNombre());
            
            existing.setEstado(Constant.ACTIVO);
            
            existing.setFechaActualizacion(new Date());
            
            Category savedCategory = repo.save(existing);
            
            return convertToResponse(savedCategory);
        }).orElseThrow(() -> new RuntimeException(Constant.ERROR_NOFOUND_CATEGORY + id));
    }

    /**
     * Desactiva lógicamente una categoría (soft delete).
     * <p>
     * Cambia el estado de la categoría a "DESACTIVADO" si esta existe.
     * </p>
     *
     * @param id El ID de la categoría a desactivar.
     */
    public void deactivate(Long id) {
        repo.findById(id).ifPresent(cat -> {
            cat.setEstado(Constant.DESACTIVADO);
            cat.setFechaActualizacion(new Date());
            repo.save(cat);
        });
    }
    
    // -------------------------------------------------------------------------
    // Método de Mapeo Interno
    // -------------------------------------------------------------------------

    /**
     * Mapea una entidad de persistencia {@code Category} a un DTO de respuesta {@code CategoryResponse}.
     *
     * @param category La entidad {@code Category} a mapear.
     * @return El DTO de respuesta listo para ser enviado al controlador.
     */
    private CategoryResponse convertToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setNombre(category.getNombre());
        response.setEstado(category.getEstado());
        response.setFechaCreacion(category.getFechaCreacion());
        response.setFechaActualizacion(category.getFechaActualizacion());
        return response;
    }
}
