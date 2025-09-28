package com.mposglobal.backoffice.dto;

/**
 * Data Transfer Object (DTO) para la solicitud de inicio de sesión.
 * <p>
 * Esta clase encapsula las credenciales de un usuario (nombre de usuario y contraseña)
 * enviadas al endpoint de autenticación para su verificación.
 * </p>
 */
public class LoginRequest {
    
    /**
     * El nombre de usuario. Campo requerido para la autenticación.
     */
    private String username;
    
    /**
     * La contraseña del usuario, generalmente enviada en texto plano y cifrada por la seguridad del transporte (HTTPS).
     * Nota: Este valor NO debe ser registrado ni almacenado sin cifrar.
     */
    private String password;

    /**
     * Constructor vacío requerido para que Spring (Jackson) pueda deserializar el cuerpo JSON de la solicitud HTTP.
     */
    public LoginRequest() {}

    /**
     * Constructor con todos los parámetros.
     *
     * @param username El nombre de usuario.
     * @param password La contraseña.
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
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
     * Obtiene la contraseña.
     *
     * @return La contraseña en texto plano (en el contexto de la solicitud).
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña.
     *
     * @param password La contraseña.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
