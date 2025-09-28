package com.mposglobal.backoffice.dto;


/**
 * Data Transfer Object (DTO) para solicitudes de creación o actualización de usuarios.
 * <p>
 * Esta clase encapsula los datos requeridos o modificables de un usuario que se envían
 * al servidor (generalmente en el cuerpo de una solicitud HTTP POST o PUT).
 * </p>
 */
public class UserRequest {

    /**
     * El ID del usuario. Es opcional para la creación, pero crucial para identificar
     * al usuario en las solicitudes de actualización (PUT).
     */
	private Long id;
    
    /**
     * Nombre completo del usuario. Requerido para la creación.
     */
	private String nombreCompleto;
    
    /**
     * Nombre de usuario único. Requerido para la creación e identificación.
     */
	private String username;
    
    /**
     * Contraseña del usuario. Se envía en texto plano en la solicitud y debe ser
     * cifrada por el servicio antes de ser persistida. Requerida para la creación.
     */
	private String clave;	
    
    /**
     * El nivel de acceso o rol deseado para el usuario (ej. "USER" o "ADMIN").
     */
	private String nivelAcceso; 
	
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
     * Establece el identificador único del usuario (usado en actualizaciones).
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
     * Obtiene la contraseña.
     * @return La contraseña en texto plano.
     */
	public String getClave() {
		return clave;
	}
    
    /**
     * Establece la contraseña.
     * @param clave La contraseña en texto plano.
     */
	public void setClave(String clave) {
		this.clave = clave;
	}
    
    /**
     * Obtiene el nivel de acceso (rol) deseado.
     * @return El nivel de acceso.
     */
	public String getNivelAcceso() {
		return nivelAcceso;
	}
    
    /**
     * Establece el nivel de acceso (rol) deseado.
     * @param nivelAcceso El nivel de acceso.
     */
	public void setNivelAcceso(String nivelAcceso) {
		this.nivelAcceso = nivelAcceso;
	}
    
}
