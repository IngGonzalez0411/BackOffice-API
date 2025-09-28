package com.mposglobal.backoffice.util;


/**
 * Clase de utilidad que contiene constantes de negocio y del sistema.
 * <p>
 * Esta clase no debe ser instanciada. Almacena cadenas de texto para mensajes de error
 * comunes que se utilizan a lo largo de la aplicación (por ejemplo, en la autenticación,
 * controladores de excepciones y logs).
 * </p>
 */
public final class Constant {

    /**
     * Constructor privado para evitar la instanciación de la clase estática.
     */
    private Constant() {
        // Evitar instanciación
    }

    // -------------------------------------------------------------------------
    // Constantes de Mensajes de Error y Logs
    // -------------------------------------------------------------------------

    /**
     * Mensaje de error genérico que indica que el usuario no existe o está desactivado.
     * Utilizado en la capa de autenticación/seguridad.
     */
    public static final String ERROR_USER_INVALID = "Usuario inválido";

    /**
     * Mensaje de error que indica que la contraseña proporcionada no es correcta.
     * Utilizado en la capa de autenticación/seguridad.
     */
    public static final String ERROR_CRED_INVALID = "Credenciales inválidas";

    /**
     * Etiqueta estandarizada para identificar un tipo de respuesta como error en el JSON.
     */
    public static final String ERROR = "error";

    /**
     * Prefijo para mensajes de error inesperados y personalizados que deben mostrarse al cliente.
     */
    public static final String ERROR_CUSTOM = "Ocurrió un error inesperado: ";
    
    public static final String ACTIVO = "ACTIVO";
    
    public static final String DESACTIVADO = "DESACTIVADO";
    
    public static final String ERROR_NOFOUND = "Usuario no encontrado para actualizar, ID = ";
    
    public static final String ERROR_NOFOUND_CATEGORY = "Categoría no encontrada con ID: ";
    
    public static final String ERROR_NOFOUND_PRODUCT = "Producto no encontrado";
    
    public static final String ERROR_CATEGORY_INVALID = "Operación no permitida: La categoría está desactivada.";
    
    public static final String ERROR_LOGPATH_INVALID = "La variable de entorno ENV_VAR_LOGPATH no está definida";
    
}
