package com.mposglobal.backoffice.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Entidad de persistencia que representa una Categoría en el sistema.
 * <p>
 * Se mapea a la tabla "Categorias" en la base de datos y define las propiedades
 * clave para la gestión y control de las categorías de productos.
 * </p>
 */
@Entity
@Table(name = "Categorias")
public class Category {

    /**
     * Identificador único de la categoría. Es la clave primaria (PK) y se genera
     * automáticamente usando la estrategia de identidad del motor de la base de datos.
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre de la categoría. Es un campo requerido (no nulo) y debe ser único en la tabla.
     */
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    /**
     * Fecha de la creación del registro. Se almacena solo la parte de la fecha (sin hora)
     * y se inicializa automáticamente con la fecha actual del sistema.
     */
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    
    /**
     * Fecha de la última actualización del registro. Se almacena solo la parte de la fecha
     * y se inicializa automáticamente con la fecha actual del sistema al momento de la creación.
     */
    @Column(name = "fechaActualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    
    /**
     * Estado lógico de la categoría (ej. "ACTIVO", "DESACTIVADO"). Es un campo requerido (no nulo).
     */
    @Column(name = "estado", nullable = false)
    private String estado; // ACTIVO / DESACTIVADO

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    /**
     * Obtiene el identificador único de la categoría.
     * @return El ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la categoría.
     * @param id El ID.
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
     * Obtiene la fecha de creación del registro.
     * @return La fecha de creación.
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación del registro.
     * @param fechaCreacion La fecha de creación.
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene la fecha de la última actualización del registro.
     * @return La fecha de actualización.
     */
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * Establece la fecha de la última actualización del registro.
     * @param fechaActualizacion La fecha de actualización.
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * Obtiene el estado lógico de la categoría.
     * @return El estado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado lógico de la categoría.
     * @param estado El estado.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Genera una representación en cadena de la entidad Category, útil para fines de logging y depuración.
     * @return Una cadena que contiene los valores de los campos de la categoría.
     */
    @Override
    public String toString() {
        return "Category [id=" + id + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion="
                + fechaActualizacion + ", estado=" + estado + "]";
    }
}
