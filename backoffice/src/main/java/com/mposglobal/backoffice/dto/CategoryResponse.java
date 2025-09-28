package com.mposglobal.backoffice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Data Transfer Object (DTO) para la respuesta de una categoría.
 * <p>
 * Esta clase encapsula la información completa de una categoría que se devuelve al cliente,
 * incluyendo los metadatos generados por el sistema, como el ID y las fechas de control.
 * </p>
 */
public class CategoryResponse {

    /**
     * Identificador único de la categoría.
     */
    private Long id;

    /**
     * El nombre de la categoría.
     */
    private String nombre;

    /**
     * El estado actual de la categoría (ej. "ACTIVO", "INACTIVO").
     */
    private String estado;

    /**
     * Fecha en la que se creó la categoría.
     * <p>
     * Se formatea como "dd/MM/yyyy" en la salida JSON.
     * </p>
     */
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Panama")
    private Date fechaCreacion;

    /**
     * Fecha de la última modificación o actualización de la categoría.
     * <p>
     * Se formatea como "dd/MM/yyyy" en la salida JSON.
     * </p>
     */
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Panama")
    private Date fechaActualizacion;
    
    // El constructor vacío por defecto es implícito o se asume si no hay otros constructores.

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    /**
     * Obtiene el identificador único de la categoría.
     * @return El ID de la categoría.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la categoría.
     * @param id El ID de la categoría.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la categoría.
     * @return El nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la categoría.
     * @param nombre El nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el estado de la categoría.
     * @return El estado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la categoría.
     * @param estado El estado.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la fecha de creación.
     * @return La fecha de creación.
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación.
     * @param fechaCreacion La fecha de creación.
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene la fecha de la última actualización.
     * @return La fecha de actualización.
     */
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * Establece la fecha de la última actualización.
     * @param fechaActualizacion La fecha de actualización.
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
