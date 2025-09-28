package com.mposglobal.backoffice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.mposglobal.backoffice.dto.ProductRequest;
import com.mposglobal.backoffice.dto.ProductResponse;
import com.mposglobal.backoffice.service.ProductService;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de productos.
 * Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Desactivar) para productos.
 */
@RestController
@RequestMapping("/products")
@Tag(name = "Productos", description = "Gestión completa de productos en el inventario.")
public class ProductController {

    private final ProductService service;

    /**
     * Constructor para inyección de dependencia del servicio de productos.
     * @param service El servicio de productos.
     */
    public ProductController(ProductService service) {
        this.service = service;
    }

// -------------------------------------------------------------------------
    
    /**
     * Obtiene una lista de todos los productos que se encuentran en estado 'ACTIVO'.
     *
     * @return Una lista de DTOs ProductResponse activos.
     */
    @GetMapping
    @Operation(summary = "Obtener todos los productos activos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                     description = "Lista obtenida con éxito",
                     content = @Content(mediaType = "application/json", 
                                        // Schema para el DTO de respuesta
                                        schema = @Schema(implementation = ProductResponse.class)))
    })
    public List<ProductResponse> all() {
        return service.findAllActive();
    }

    /**
     * Crea un nuevo producto en el inventario.
     * Nota: El estado inicial se setea como 'ACTIVO' en el controlador o servicio.
     *
     * @param request El DTO ProductRequest con los datos del nuevo producto.
     * @return El DTO ProductResponse del producto creado.
     */
    @PostMapping
    @Operation(summary = "Crea un nuevo producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                     description = "Producto creado con éxito",
                     content = @Content(mediaType = "application/json", 
                                        schema = @Schema(implementation = ProductResponse.class)))
    })
    public ProductResponse create(@RequestBody ProductRequest p) {
        
        return service.create(p);
    }

    /**
     * Actualiza un producto existente basándose en su ID.
     *
     * @param id El ID del producto a actualizar.
     * @param request El DTO ProductRequest con los campos a modificar.
     * @return El DTO ProductResponse del producto actualizado.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                     description = "Producto actualizado con éxito",
                     content = @Content(mediaType = "application/json", 
                                        schema = @Schema(implementation = ProductResponse.class)))
    })
    public ProductResponse update(@PathVariable Long id, @RequestBody ProductRequest p) {
        return service.update(id, p);
    }

    /**
     * Desactiva lógicamente un producto (soft delete).
     * Esto cambia el estado del producto a 'INACTIVO'.
     *
     * @param id El ID del producto a desactivar.
     * @return Una respuesta HTTP 200 OK con un mensaje de confirmación.
     */
    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Desactiva lógicamente un producto por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                     description = "Producto desactivado con éxito",
                     content = @Content(mediaType = "application/json", 
                                        // Mapa simple para el mensaje de respuesta
                                        schema = @Schema(implementation = Map.class)))
    })
    public ResponseEntity<Map<String, String>> deactivate(@PathVariable Long id) {
        service.deactivate(id);
        return ResponseEntity.ok(Map.of("message", "Producto desactivado"));
    }
}
