package com.mposglobal.backoffice.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Entidad de persistencia que representa un Producto en el inventario.
 * <p>
 * Se mapea a la tabla "Productos" en la base de datos y define las propiedades
 * clave, precios, y la relación con su categoría.
 * </p>
 */
@Entity
@Table(name = "Productos")
public class Product {

    /**
     * Identificador único del producto. Es la clave primaria (PK) y se genera
     * automáticamente.
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre descriptivo del producto. Es un campo requerido (no nulo) y debe ser único.
     */
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    /**
     * Relación ManyToOne con la entidad {@link Category}.
     * <p>
     * Mapea la columna {@code categoriaId} en la tabla de productos como la clave foránea (FK),
     * lo que asegura que cada producto esté asociado a una categoría válida.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "categoriaId", nullable = false)
    private Category categoria;
    
    /**
     * El costo de adquisición del producto. Campo requerido.
     */
    @Column(name = "costo", nullable = false)
    private Double costo;
    
    /**
     * El precio de venta al público. Campo requerido.
     */
    @Column(name = "precio", nullable = false)
    private Double precio;
    
    /**
     * Etiquetas o palabras clave asociadas al producto, almacenadas como una cadena de texto.
     * Campo requerido.
     */
    @Column(name = "tags", nullable = false)
    private String tags;    
    
    /**
     * Fecha de la creación del registro. Se almacena solo la parte de la fecha.
     */
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    
    /**
     * Fecha de la última actualización del registro. Se almacena solo la parte de la fecha.
     */
    @Column(name = "fechaActualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    
    /**
     * Estado lógico del producto (ej. "ACTIVO", "DESACTIVADO"). Campo requerido.
     */
    @Column(name = "estado", nullable = false)
    private String estado; // ACTIVO / DESACTIVADO

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    /**
     * Obtiene el identificador único del producto.
     * @return El ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     * @param id El ID.
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
     * Obtiene la entidad {@link Category} asociada al producto.
     * @return La categoría del producto.
     */
    public Category getCategoria() {
        return categoria;
    }

    /**
     * Establece la entidad {@link Category} asociada al producto.
     * @param categoria La categoría.
     */
    public void setCategoria(Category categoria) {
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
     * @return El precio.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de venta del producto.
     * @param precio El precio.
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
     * Obtiene el estado lógico del producto.
     * @return El estado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado lógico del producto.
     * @param estado El estado.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Genera una representación en cadena de la entidad Product, útil para fines de logging y depuración.
     * @return Una cadena que contiene los valores de los campos del producto.
     */
    @Override
    public String toString() {
        return "Product [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", costo=" + costo + ", precio="
                + precio + ", tags=" + tags + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion="
                + fechaActualizacion + ", estado=" + estado + "]";
    }
}
