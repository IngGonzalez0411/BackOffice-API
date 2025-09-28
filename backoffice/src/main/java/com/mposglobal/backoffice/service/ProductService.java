package com.mposglobal.backoffice.service;

import org.springframework.stereotype.Service;

import com.mposglobal.backoffice.dto.ProductRequest;
import com.mposglobal.backoffice.dto.ProductResponse;
import com.mposglobal.backoffice.entity.Category;
import com.mposglobal.backoffice.entity.Product;
import com.mposglobal.backoffice.exceptions.ProductException;
import com.mposglobal.backoffice.repository.CategoryRepository;
import com.mposglobal.backoffice.repository.ProductRepository;
import com.mposglobal.backoffice.util.Constant;

import java.util.Date;
import java.util.List;

/**
 * Servicio central para la gestión de productos.
 * <p>
 * Implementa la lógica de negocio para las operaciones CRUD sobre la entidad {@code Product},
 * incluyendo la validación de la existencia de la {@code Category} asociada y el mapeo
 * entre DTOs de solicitud/respuesta y la entidad de persistencia.
 * </p>
 */
@Service
public class ProductService {

	private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo; // ¡Necesitas esto!

    /**
     * Constructor para inyectar los repositorios de Producto y Categoría.
     *
     * @param productRepo El repositorio JPA para el acceso a datos de productos.
     * @param categoryRepo El repositorio JPA para la búsqueda de entidades de categorías.
     */
    public ProductService(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    // -------------------------------------------------------------------------
    // Métodos de Lógica de Negocio
    // -------------------------------------------------------------------------

    /**
     * Busca y devuelve todos los productos que se encuentran en estado "ACTIVO".
     * <p>
     * Filtra los productos activos y mapea las entidades {@code Product} a DTOs {@code ProductResponse}.
     * </p>
     *
     * @return Una lista de {@code ProductResponse} de todos los productos activos.
     */
    public List<ProductResponse> findAllActive() {
        return productRepo.findAll()
                .stream()
                .filter(p -> Constant.ACTIVO.equalsIgnoreCase(p.getEstado()))
                .map(this::convertToResponse) // aquí mapeas Product -> ProductResponse
                .toList();
    }

    /**
     * Crea un nuevo producto.
     * <p>
     * 1. Busca la entidad {@code Category} usando el ID proporcionado en el DTO.
     * 2. Mapea {@code ProductRequest} a la entidad, asigna la categoría y establece el estado
     * y las fechas de control.
     * 3. Persiste la entidad y mapea el resultado a {@code ProductResponse}.
     * </p>
     *
     * @param request El DTO de solicitud con los datos del nuevo producto.
     * @return El DTO de respuesta del producto creado.
     * @throws RuntimeException Si la categoría referenciada no existe.
     */
    public ProductResponse create(ProductRequest request) {
        // 1. Buscar la Entidad Category usando el ID del Request
        Category categoryEntity = categoryRepo.findById(request.getCategoria())
            .orElseThrow(() -> new RuntimeException(Constant.ERROR_NOFOUND_CATEGORY + request.getCategoria()));
        
        validateCategoryIsActive(categoryEntity);
        
        // 2. Mapear Request a Entidad Product
        Product p = new Product();
        
        p.setNombre(request.getNombre());
        
        // ASIGNACIÓN CORRECTA: Asignamos el objeto Category completo
        p.setCategoria(categoryEntity); 
        
        p.setCosto(request.getCosto());
        p.setPrecio(request.getPrecio());
        p.setTags(request.getTags());
        
        // 3. Asignar valores del sistema
        p.setEstado(Constant.ACTIVO);
        Date now = new Date();
        p.setFechaCreacion(now);
        p.setFechaActualizacion(now);
        
        // 4. Guardar y Mapear a Respuesta
        Product savedProduct = productRepo.save(p);
        return convertToResponse(savedProduct);
    }

    /**
     * Actualiza un producto existente.
     * <p>
     * 1. Busca la entidad {@code Category} y verifica que exista.
     * 2. Busca la entidad {@code Product} a actualizar. Si existe, aplica los cambios del DTO
     * (incluida la nueva categoría) y actualiza la fecha de modificación.
     * 3. Persiste los cambios y mapea el resultado a {@code ProductResponse}.
     * </p>
     *
     * @param id El ID del producto a actualizar.
     * @param request El DTO de solicitud con los nuevos datos.
     * @return El DTO de respuesta del producto actualizado.
     * @throws RuntimeException Si el producto o la categoría referenciada no se encuentran.
     */
    public ProductResponse update(Long id, ProductRequest request) {
        
        // 1. Buscar la Entidad Category usando el ID del Request
        Category categoryEntity = categoryRepo.findById(request.getCategoria())
            .orElseThrow(() -> new RuntimeException(Constant.ERROR_NOFOUND_CATEGORY + request.getCategoria()));
        
        validateCategoryIsActive(categoryEntity);
        
        return productRepo.findById(id).map(existing -> {
            
            // 2. Aplicar cambios del Request a la Entidad existente
            existing.setNombre(request.getNombre());
            
            // ASIGNACIÓN CORRECTA: Asignamos el objeto Category completo
            existing.setCategoria(categoryEntity); 
            
            existing.setCosto(request.getCosto());
            existing.setPrecio(request.getPrecio());
            existing.setTags(request.getTags());
            
            // ... (resto de la lógica de update)
            existing.setEstado(Constant.ACTIVO);
            existing.setFechaActualizacion(new Date());
            
            // 3. Guardar y Mapear a Respuesta
            Product updatedProduct = productRepo.save(existing);
            return convertToResponse(updatedProduct);
            
        }).orElseThrow(() -> new RuntimeException(Constant.ERROR_NOFOUND_PRODUCT));
    }

    /**
     * Desactiva lógicamente un producto (soft delete).
     * <p>
     * Cambia el estado del producto a "DESACTIVADO" y actualiza la fecha de modificación,
     * siempre y cuando el producto exista.
     * </p>
     *
     * @param id El ID del producto a desactivar.
     */
    public void deactivate(Long id) {
    	productRepo.findById(id).ifPresent(prod -> {
            prod.setEstado(Constant.DESACTIVADO);
            prod.setFechaActualizacion(new Date());
            productRepo.save(prod);
        });
    }
    
    // -------------------------------------------------------------------------
    // Método de Mapeo Interno
    // -------------------------------------------------------------------------

    /**
     * Mapea una entidad de persistencia {@code Product} a un DTO de respuesta {@code ProductResponse}.
     *
     * @param prod La entidad {@code Product} a mapear.
     * @return El DTO de respuesta listo para ser enviado al controlador.
     */
    private ProductResponse convertToResponse(Product prod) {
        ProductResponse response = new ProductResponse();
        
        response.setId(prod.getId());
        response.setNombre(prod.getNombre());

        response.setCosto(prod.getCosto());
        response.setPrecio(prod.getPrecio());
        response.setTags(prod.getTags());
        
        if (prod.getCategoria().getNombre() != null) {
            // Debes tener un setter en ProductResponse que acepte el ID de Categoría
            response.setCategoria(prod.getCategoria().getNombre()); 
        }
        
        response.setEstado(prod.getEstado());
        response.setFechaCreacion(prod.getFechaCreacion());
        response.setFechaActualizacion(prod.getFechaActualizacion());
        
        return response;
    }
    
    /**
     * Valida si una categoría está inactiva y lanza una excepción si lo está.
     *
     * @param categoryEntity La entidad Category a validar.
     */
    private void validateCategoryIsActive(Category categoryEntity) {
        
        if (categoryEntity.getEstado().equals(Constant.DESACTIVADO)) {
            throw new ProductException(
            		Constant.ERROR_CATEGORY_INVALID
            );
        }
    }
}