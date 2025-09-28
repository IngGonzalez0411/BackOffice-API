package com.mposglobal.backoffice.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Entidad de persistencia que representa un Usuario del sistema.
 * <p>
 * Se mapea a la tabla "Usuarios" y contiene las credenciales de acceso,
 * el rol (nivel de acceso) y las fechas de control y estado del usuario.
 * </p>
 */
@Entity
@Table(name = "Usuarios")
public class User {
    
    /**
     * Identificador único del usuario. Es la clave primaria (PK) y se genera
     * automáticamente.
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre completo del usuario. Campo requerido (no nulo).
     */
    @Column(name = "nombreCompleto", nullable = false)
    private String nombreCompleto;
    
    /**
     * Nombre de usuario único para el inicio de sesión. Es un campo requerido y debe ser único.
     */
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    
    /**
     * Contraseña del usuario, debe almacenarse cifrada (ej. usando BCrypt). Campo requerido.
     */
    @Column(name = "clave", nullable = false)
    private String clave;    
    
    /**
     * Fecha de creación del registro del usuario. Se almacena solo la parte de la fecha
     * y se inicializa con la fecha actual al momento de la creación.
     */
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion = new Date();
    
    /**
     * Fecha del último ingreso exitoso del usuario al sistema.
     * Se almacena solo la parte de la fecha.
     */
    @Column(name = "fechaUltimoIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimoIngreso;
    
    /**
     * El nivel de acceso o rol del usuario para la autorización (ej. "USER" o "ADMIN"). Campo requerido.
     */
    @Column(name = "nivelAcceso", nullable = false)
    private String nivelAcceso; // "USER" or "ADMIN"
    
    /**
     * Estado lógico del usuario (ej. "ACTIVO" o "DESACTIVADO"). Campo requerido.
     */
    @Column(name = "estado", nullable = false)
    private String estado; // "ACTIVO" or "DESACTIVADO"

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
     * Establece el identificador único del usuario.
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
     * Obtiene el nombre de usuario (username) usado para el login.
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
     * Obtiene la clave (contraseña cifrada) del usuario.
     * @return La clave.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Establece la clave (contraseña cifrada) del usuario.
     * @param clave La clave.
     */
    public void setClave(String clave) {
        this.clave = clave;
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
     * Obtiene la fecha del último ingreso del usuario.
     * @return La fecha del último ingreso.
     */
    public Date getFechaUltimoIngreso() {
        return fechaUltimoIngreso;
    }

    /**
     * Establece la fecha del último ingreso del usuario.
     * @param fechaUltimoIngreso La fecha del último ingreso.
     */
    public void setFechaUltimoIngreso(Date fechaUltimoIngreso) {
        this.fechaUltimoIngreso = fechaUltimoIngreso;
    }

    /**
     * Obtiene el nivel de acceso (rol) del usuario.
     * @return El nivel de acceso.
     */
    public String getNivelAcceso() {
        return nivelAcceso;
    }

    /**
     * Establece el nivel de acceso (rol) del usuario.
     * @param nivelAcceso El nivel de acceso.
     */
    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    /**
     * Obtiene el estado lógico del usuario.
     * @return El estado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado lógico del usuario.
     * @param estado El estado.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Genera una representación en cadena de la entidad User, útil para fines de logging y depuración.
     * @return Una cadena que contiene los valores de los campos del usuario.
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", nombreCompleto=" + nombreCompleto + ", username=" + username + ", clave=" + clave
                + ", fechaCreacion=" + fechaCreacion + ", fechaUltimoIngreso=" + fechaUltimoIngreso + ", nivelAcceso="
                + nivelAcceso + ", estado=" + estado + "]";
    }
}