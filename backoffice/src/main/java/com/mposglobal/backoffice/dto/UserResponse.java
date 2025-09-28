package com.mposglobal.backoffice.dto;

import java.util.Date;

/**
 * Data Transfer Object (DTO) para la respuesta de un usuario.
 * <p>
 * Esta clase encapsula la información que se devuelve al cliente después de una
 * consulta de usuario, incluyendo datos de control como el nivel de acceso,
 * el estado y las fechas de ingreso/creación.
 * </p>
 * <p>
 * **Nota de Seguridad:** Aunque el campo {@code clave} está presente, por buenas
 * prácticas, el servicio debe omitir su valor (dejarlo {@code null} o vacío) al
 * mapear desde la entidad, para evitar exponer la clave cifrada.
 * </p>
 */
public class UserResponse {

    /**
     * Identificador único del usuario.
     */
	private Long id;
    
    /**
     * Nombre completo del usuario.
     */
	private String nombreCompleto;
    
    /**
     * Nombre de usuario único usado para el login.
     */
	private String username;
    
    /**
     * Fecha de creación del registro del usuario.
     */
	private Date fechaCreacion;
    
    /**
     * Fecha del último ingreso exitoso del usuario al sistema.
     */
	private Date fechaUltimoIngreso;
    
    /**
     * El nivel de acceso o rol del usuario (ej. "USER" o "ADMIN").
     */
	private String nivelAcceso; 
    
    /**
     * Estado lógico del usuario (ej. "ACTIVO" o "DESACTIVADO").
     */
	private String estado; 
	
    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------
    
    /**
     * Obtiene el identificador único del usuario.
     * @return El ID.
     */
	public Long getId() {
		return id;
	}
    
    /**
     * Establece el identificador único del usuario.
     * @param id El ID.
     */
	public void setId(Long id) {
		this.id = id;
	}
    
    /**
     * Obtiene el nombre completo del usuario.
     * @return El nombre completo.
     */
	public String getNombreCompleto() {
		return nombreCompleto;
	}
    
    /**
     * Establece el nombre completo del usuario.
     * @param nombreCompleto El nombre completo.
     */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
    
    /**
     * Obtiene el nombre de usuario.
     * @return El nombre de usuario.
     */
	public String getUsername() {
		return username;
	}
    
    /**
     * Establece el nombre de usuario.
     * @param username El nombre de usuario.
     */
	public void setUsername(String username) {
		this.username = username;
	}
    
    /**
     * Obtiene la fecha de creación del usuario.
     * @return La fecha de creación.
     */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
    
    /**
     * Establece la fecha de creación del usuario.
     * @param fechaCreacion La fecha de creación.
     */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
    
    /**
     * Obtiene la fecha del último ingreso del usuario.
     * @return La fecha del último ingreso.
     */
	public Date getFechaUltimoIngreso() {
		return fechaUltimoIngreso;
	}
    
    /**
     * Establece la fecha del último ingreso del usuario.
     * @param fechaUltimoIngreso La fecha del último ingreso.
     */
	public void setFechaUltimoIngreso(Date fechaUltimoIngreso) {
		this.fechaUltimoIngreso = fechaUltimoIngreso;
	}
    
    /**
     * Obtiene el nivel de acceso (rol) del usuario.
     * @return El nivel de acceso.
     */
	public String getNivelAcceso() {
		return nivelAcceso;
	}
    
    /**
     * Establece el nivel de acceso (rol) del usuario.
     * @param nivelAcceso El nivel de acceso.
     */
	public void setNivelAcceso(String nivelAcceso) {
		this.nivelAcceso = nivelAcceso;
	}
    
    /**
     * Obtiene el estado lógico del usuario.
     * @return El estado.
     */
	public String getEstado() {
		return estado;
	}
    
    /**
     * Establece el estado lógico del usuario.
     * @param estado El estado.
     */
	public void setEstado(String estado) {
		this.estado = estado;
	}	
}
