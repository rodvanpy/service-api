/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import py.com.mojeda.service.ejb.utils.Avatar;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "EMPRESAS", schema = "PUBLIC", uniqueConstraints = @UniqueConstraint(name = "empresa_ruc_uq", columnNames = {"ruc"}))
public class Empresas extends Base {

    private static final long serialVersionUID = 79861856088L;
    private static final String SECUENCIA = "seq_empresa_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Ingrese Nombre")
    @Column(name = "NOMBRE")
    private String nombre;

    //@NotEmpty(message = "Ingrese Nombre Fantasia")
    @Column(name = "NOMBRE_FANTASIA")
    private String nombreFantasia;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @NotEmpty(message = "Ingrese Ruc")
    @Column(name = "RUC")
    private String ruc;

    @NotEmpty(message = "Ingrese Direccion")
    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "telefono_movil")
    private String telefonoMovil;

    @NotEmpty(message = "Ingrese Email")
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "OBSERVACION")
    private String observacion;

    @Column(name = "LATITUD")
    private Double latitud;

    @Column(name = "LONGITUD")
    private Double longitud;
    
    @NotNull(message = "Ingrese Pais")
    @ManyToOne
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "id")
    private Paises pais;
    
    @NotNull(message = "Ingrese Departamento")
    @ManyToOne
    @JoinColumn(name = "ID_DEPARTAMENTO_PAIS", referencedColumnName = "id")
    private DepartamentosPais departamento;
    
    @NotNull(message = "Ingrese Ciudad")
    @ManyToOne
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "id")
    private Ciudades ciudad;
    
    @ManyToOne
    @JoinColumn(name = "ID_BARRIO", referencedColumnName = "id")
    private Barrios barrio;
    
    @Column(name = "IMAGE_PATH")
    private String imagePath;   
    
    @Column(name = "MONTO_VERIFICACION_CREDITO")
    private BigDecimal montoVerificacionCredito;
    
    @Column(name = "PORCENTAJE_ENDEUDAMIENTO")
    private BigDecimal porcentajeEndeudamiento;
    
    @Column(name = "ENTIDAD")
    private String entidad = "EMPRESAS";
        
    @Transient
    @JsonIgnore
    private Avatar avatar;   

    public Empresas() {

    }

    public Empresas(Long id) {
        this.setId(id);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the ruc
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the telefonoMovil
     */
    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    /**
     * @param telefonoMovil the telefonoMovil to set
     */
    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the nombreFantasia
     */
    public String getNombreFantasia() {
        return nombreFantasia;
    }

    /**
     * @param nombreFantasia the nombreFantasia to set
     */
    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
    
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return the pais
     */
    public Paises getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(Paises pais) {
        this.pais = pais;
    }

    /**
     * @return the departamento
     */
    public DepartamentosPais getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(DepartamentosPais departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the ciudad
     */
    public Ciudades getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the barrio
     */
    public Barrios getBarrio() {
        return barrio;
    }

    /**
     * @param barrio the barrio to set
     */
    public void setBarrio(Barrios barrio) {
        this.barrio = barrio;
    }
       
    /**
     * @return the entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * @param entidad the entidad to set
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public BigDecimal getMontoVerificacionCredito() {
        return montoVerificacionCredito;
    }

    public void setMontoVerificacionCredito(BigDecimal montoVerificacionCredito) {
        this.montoVerificacionCredito = montoVerificacionCredito;
    } 


    public BigDecimal getPorcentajeEndeudamiento() {
        return porcentajeEndeudamiento;
    }

    public void setPorcentajeEndeudamiento(BigDecimal porcentajeEndeudamiento) {
        this.porcentajeEndeudamiento = porcentajeEndeudamiento;
    }
    
    
}
