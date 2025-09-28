package com.mposglobal.backoffice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mposglobal.backoffice.dto.UserRequest;
import com.mposglobal.backoffice.dto.UserResponse;
import com.mposglobal.backoffice.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de usuarios en el sistema.
 * <p>
 * Proporciona endpoints para obtener, crear, actualizar y desactivar usuarios.
 * Todos los endpoints están bajo el path base '/users'.
 */
@RestController
@RequestMapping("/users")
@Tag(name = "Usuarios", description = "Gestión de la información y estado de los usuarios del sistema.")
public class UserController {

    private final UserService service;

    /**
     * Constructor para inyección de dependencias (DI).
     *
     * @param service El servicio de lógica de negocio para la entidad Usuario.
     */
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Recupera una lista de todos los usuarios marcados como activos en la base de datos.
     *
     * @return Una {@link List} de {@link UserResponse} que representan a los usuarios activos.
     */
    @Operation(summary = "Obtiene la lista de todos los usuarios activos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito")
    })
    @GetMapping
    public List<UserResponse> all() {
        return service.findAll();
    }

    /**
     * Crea un nuevo usuario basado en la información proporcionada en el cuerpo de la solicitud.
     * La contraseña se cifra internamente.
     *
     * @param u Los datos de la solicitud para la creación del usuario.
     * @return Un {@link UserResponse} del usuario creado, incluyendo su ID.
     */
    @Operation(summary = "Crea un nuevo usuario en el sistema", 
            description = "La contraseña (clave) debe ser enviada en texto plano y será cifrada por el servicio.")
	 @ApiResponses(value = {
	     // Éxito: HTTP 200 OK (o 201 CREATED si usaras ResponseEntity.created())
	     @ApiResponse(
	         responseCode = "200", 
	         description = "Usuario creado con éxito",
	         content = @Content(schema = @Schema(implementation = UserResponse.class))
	     )
	 })
    @PostMapping
    public UserResponse create(@RequestBody UserRequest u) {
        return service.create(u);
    }

    /**
     * Actualiza la información de un usuario específico.
     *
     * @param id El ID del usuario a actualizar, tomado del path de la URL.
     * @param u  Los nuevos datos del usuario, tomados del cuerpo de la solicitud.
     * @return Un {@link UserResponse} del usuario actualizado.
     */
    @Operation(summary = "Actualiza la información de un usuario existente", 
            description = "Usa el 'username' del path para identificar el registro. Los datos del cuerpo reemplazarán los existentes.")
	 @ApiResponses(value = {
	     // Éxito: HTTP 200 OK
	     @ApiResponse(
	         responseCode = "200", 
	         description = "Usuario actualizado con éxito",
	         content = @Content(schema = @Schema(implementation = UserResponse.class))
	     )
	 })
    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest u) {
        u.setId(id);
        return service.update(u);
    }

    /**
     * Desactiva lógicamente un usuario, cambiando su estado a inactivo en lugar de eliminarlo permanentemente.
     *
     * @param id El ID del usuario a desactivar.
     * @return Una {@link ResponseEntity} con un mensaje de confirmación.
     */
    @Operation(summary = "Desactiva lógicamente un usuario por ID")
    @ApiResponses(value = {
        // Respuesta exitosa (HTTP 200 OK)
        @ApiResponse(
            responseCode = "200", 
            description = "Usuario desactivado con éxito",
            content = @Content(
                schema = @Schema(implementation = Map.class) 
            )
        )
    })
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Map<String, String>> deactivate(@PathVariable Long id) {
        service.softDeactivate(id);
        return ResponseEntity.ok(Map.of("message", "Usuario desactivado"));
    }
}
