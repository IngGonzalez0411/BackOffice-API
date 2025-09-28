package com.mposglobal.backoffice.dto;

/**
 * Data Transfer Object (DTO) para la respuesta de autenticación (Login).
 * <p>
 * Esta clase se utiliza para encapsular la información devuelta al cliente después de un
 * inicio de sesión exitoso. Contiene el token JWT necesario para acceder a los recursos protegidos,
 * el nombre de usuario y el rol asociado.
 * </p>
 */
public class AuthResponse {
	
    private String token;
    private String username;
    private String nivelAcceso;

    /**
     * Constructor por defecto requerido para la deserialización JSON (Jackson).
     */
    public AuthResponse() {
    	//vacio
    }

    /**
     * Constructor principal para inicializar la respuesta de autenticación.
     *
     * @param token El token JWT emitido por el servidor.
     * @param username El nombre de usuario que ha iniciado sesión.
     * @param nivelAcceso El rol o nivel de acceso del usuario (ej. ADMIN, USER).
     */
    public AuthResponse(String token, String username, String nivelAcceso) {
        this.token = token;
        this.username = username;
        this.nivelAcceso = nivelAcceso;
    }

    /**
     * Obtiene el token JWT.
     *
     * @return El token de acceso.
     */
    public String getToken() {
        return token;
    }

    /**
     * Establece el token JWT.
     *
     * @param token El token de acceso.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario asociado al token.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username El nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene el nivel de acceso (rol) del usuario.
     *
     * @return El nivel de acceso.
     */
    public String getNivelAcceso() {
        return nivelAcceso;
    }

    /**
     * Establece el nivel de acceso (rol) del usuario.
     *
     * @param nivelAcceso El nivel de acceso.
     */
    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
}