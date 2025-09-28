package com.mposglobal.backoffice.dto;

/**
 * Data Transfer Object (DTO) para solicitudes de creación o actualización de productos.
 * <p>
 * Esta clase encapsula los datos requeridos para definir o modificar un producto,
 * incluyendo el nombre, precios y la referencia a la categoría por su ID.
 * </p>
 */
public class ProductRequest {

    /**
     * El nombre descriptivo del producto. Campo requerido.
     */
    private String nombre;
    
    /**
     * El ID de la categoría a la que pertenece el producto.
     * Es crucial para el mapeo a la entidad {@code Category} en el servicio.
     */
    private Long categoria;
    
    /**
     * El costo de adquisición del producto.
     */
    private Double costo;
    
    /**
     * El precio de venta al público del producto.
     */
    private Double precio;
    
    /**
     * Una cadena de texto con etiquetas o palabras clave relacionadas con el producto.
     */
    private String tags;
    
    // El constructor vacío por defecto es implícito o se asume si no hay otros constructores.

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del producto.
     * @param nombre El nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el ID de la categoría a la que pertenece el producto.
     * @return El ID de la categoría (Long).
     */
    public Long getCategoria() {
        return categoria;
    }
    
    /**
     * Establece el ID de la categoría.
     * @param categoria El ID de la categoría.
     */
    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }
    
    /**
     * Obtiene el costo del producto.
     * @return El costo.
     */
    public Double getCosto() {
        return costo;
    }
    
    /**
     * Establece el costo del producto.
     * @param costo El costo.
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    
    /**
     * Obtiene el precio de venta del producto.
     * @return El precio de venta.
     */
    public Double getPrecio() {
        return precio;
    }
    
    /**
     * Establece el precio de venta del producto.
     * @param precio El precio de venta.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    /**
     * Obtiene las etiquetas (tags) del producto.
     * @return Las etiquetas.
     */
    public String getTags() {
        return tags;
    }
    
    /**
     * Establece las etiquetas (tags) del producto.
     * @param tags Las etiquetas.
     */
    public void setTags(String tags) {
        this.tags = tags;
    }
 
}
