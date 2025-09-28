package com.mposglobal.backoffice.dto;


/**
 * Data Transfer Object (DTO) para solicitudes de creación o actualización de categorías.
 * <p>
 * Esta clase encapsula los datos mínimos requeridos que un cliente debe enviar al servidor
 * para crear una nueva categoría o modificar una existente.
 * </p>
 */
public class CategoryRequest {
    
    /**
     * El nombre de la categoría. Es un campo requerido para la creación y actualización.
     */
    private String nombre;   

    /**
     * Constructor vacío requerido para la deserialización JSON (Jackson) de la solicitud HTTP.
     */
    public CategoryRequest() {
        // vacio 
    }
    
    /**
     * Obtiene el nombre de la categoría.
     *
     * @return El nombre de la categoría.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la categoría.
     *
     * @param nombre El nombre de la categoría.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
