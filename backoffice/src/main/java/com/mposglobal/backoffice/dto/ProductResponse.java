package com.mposglobal.backoffice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data Transfer Object (DTO) para la respuesta de un producto.
 * <p>
 * Esta clase encapsula la información completa de un producto que se devuelve al cliente
 * después de una consulta, creación o actualización exitosa. Incluye el nombre de la
 * categoría y las fechas de control con formato específico.
 * </p>
 */
public class ProductResponse {

    /**
     * Identificador único del producto.
     */
	private Long id;
    
    /**
     * El nombre descriptivo del producto.
     */
	private String nombre;
    
    /**
     * El nombre de la categoría a la que pertenece el producto.
     * En la respuesta, se prefiere el nombre en lugar del ID para claridad.
     */
	private String categoria; // Asumimos que este es el nombre de la categoría.
    
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
    
    /**
     * El estado actual del producto (ej. "ACTIVO", "INACTIVO").
     */
	private String estado;
    
    /**
     * Fecha en la que se creó el producto.
     * <p>
     * Se formatea como "dd/MM/yyyy" en la salida JSON.
     * </p>
     */
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Panama")
    private Date fechaCreacion;
    
    /**
     * Fecha de la última modificación o actualización del producto.
     * <p>
     * Se formatea como "dd/MM/yyyy" en la salida JSON.
     * </p>
     */
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Panama")
    private Date fechaActualizacion;
	
    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    /**
     * Obtiene el identificador único del producto.
     * @return El ID del producto.
     */
	public Long getId() {
		return id;
	}
    
    /**
     * Establece el identificador único del producto.
     * @param id El ID del producto.
     */
	public void setId(Long id) {
		this.id = id;
	}
    
    /**
     * Obtiene el nombre del producto.
     * @return El nombre.
     */
	public String getNombre() {
		return nombre;
	}
    
    /**
     * Establece el nombre del producto.
     * @param nombre El nombre.
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
    /**
     * Obtiene el nombre de la categoría asociada.
     * @return El nombre de la categoría.
     */
	public String getCategoria() {
		return categoria;
	}
    
    /**
     * Establece el nombre de la categoría.
     * @param categoria El nombre de la categoría.
     */
	public void setCategoria(String categoria) {
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
    
    /**
     * Obtiene el estado del producto.
     * @return El estado.
     */
	public String getEstado() {
		return estado;
	}
    
    /**
     * Establece el estado del producto.
     * @param estado El estado.
     */
	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    /**
     * Obtiene la fecha de creación del producto.
     * @return La fecha de creación.
     */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
    
    /**
     * Establece la fecha de creación del producto.
     * @param fechaCreacion La fecha de creación.
     */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
    
    /**
     * Obtiene la fecha de la última actualización del producto.
     * @return La fecha de actualización.
     */
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
    
    /**
     * Establece la fecha de la última actualización del producto.
     * @param fechaActualizacion La fecha de actualización.
     */
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
}
