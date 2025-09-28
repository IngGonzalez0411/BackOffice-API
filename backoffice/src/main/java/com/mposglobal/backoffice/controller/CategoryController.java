package com.mposglobal.backoffice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mposglobal.backoffice.dto.CategoryRequest;
import com.mposglobal.backoffice.dto.CategoryResponse;
import com.mposglobal.backoffice.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de categorías.
 * Proporciona endpoints para consultar, crear, actualizar y desactivar categorías de productos.
 */
@RestController
@RequestMapping("/categories")
@Tag(name = "Categorías", description = "Gestión de las categorías de productos (CRUD).")
public class CategoryController {

    private final CategoryService service;

    /**
     * Constructor para inyección de dependencia del servicio de categorías.
     * @param service El servicio de categorías.
     */
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    /**
     * Obtiene una lista de todas las categorías que se encuentran en estado 'ACTIVO'.
     *
     * @return Una lista de objetos Category activos.
     */
    @GetMapping
    @Operation(summary = "Obtener todas las categorías activas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                     description = "Lista obtenida con éxito",
                     content = @Content(mediaType = "application/json", 
                                        schema = @Schema(implementation = CategoryResponse.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<CategoryResponse> all() {
        return service.findAllActive();
    }

    /**
     * Crea una nueva categoría.
     *
     * @param request Los datos de la nueva categoría (nombre y opcionalmente estado).
     * @return La categoría creada, incluyendo el ID y las fechas de creación/actualización.
     */
    @PostMapping
    @Operation(summary = "Crea una nueva categoría")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                     description = "Categoría creada con éxito",
                     content = @Content(mediaType = "application/json", 
                                        schema = @Schema(implementation = CategoryResponse.class)))
    })
    public CategoryResponse create(@RequestBody CategoryRequest c) {
        return service.create(c);
    }

    /**
     * Actualiza una categoría existente basándose en su ID.
     *
     * @param id El ID de la categoría a actualizar.
     * @param request Los nuevos datos de la categoría.
     * @return La categoría actualizada.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una categoría existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                     description = "Categoría actualizada con éxito",
                     content = @Content(mediaType = "application/json", 
                                        schema = @Schema(implementation = CategoryResponse.class)))
    })
    public CategoryResponse update(@PathVariable Long id, @RequestBody CategoryRequest c) {
        return service.update(id, c);
    }

    /**
     * Desactiva lógicamente una categoría (soft delete).
     * Esto cambia el estado de la categoría a 'INACTIVO'.
     *
     * @param id El ID de la categoría a desactivar.
     * @return Una respuesta HTTP 200 OK con un mensaje de confirmación.
     */
    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Desactiva lógicamente una categoría por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                     description = "Categoría desactivada con éxito",
                     content = @Content(mediaType = "application/json", 
                                        schema = @Schema(implementation = Map.class)))
    })
    public ResponseEntity<Map<String, String>> deactivate(@PathVariable Long id) {
        service.deactivate(id);
        return ResponseEntity.ok(Map.of("message", "Categoría desactivada"));
    }
}
