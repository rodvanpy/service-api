/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
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
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author daniel
 */

@Entity
@Table(name = "SUCURSALES" , schema = "PUBLIC" ,uniqueConstraints = @UniqueConstraint(name = "sucursal_cod_uq", columnNames = { "codigo_sucursal" }) )
public class Sucursales extends Base {

    private static final long serialVersionUID = 79861856088L;
    private static final String SECUENCIA = "seq_sucursal_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    //@NotEmpty(message = "Ingrese Codigo Sucursal")
    @Column(name = "CODIGO_SUCURSAL")
    private String codigoSucursal;
    
    @NotEmpty(message = "Ingrese Nombre")
    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;   

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty(message = "Ingrese Direccion")
    @Column(name = "DIRECCION")
    private String direccion;
    
    @Column(name = "TELEFONO")
    private String telefono;
    
    @Column(name = "FAX")
    private String fax;

    @Column(name = "telefono_movil")
    private String telefonoMovil;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty(message = "Ingrese Email")
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "OBSERVACION")
    private String observacion;
    
    @Column(name = "LATITUD")
    private Double latitud;
    
    @Column(name = "LONGITUD")
    private Double longitud;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "Ingrese Pais")
    @ManyToOne
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "id")
    private Paises pais;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "Ingrese Departamento")
    @ManyToOne
    @JoinColumn(name = "ID_DEPARTAMENTO_PAIS", referencedColumnName = "id")
    private DepartamentosPais departamento;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "Ingrese Ciudad")
    @ManyToOne
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "id")
    private Ciudades ciudad;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne
    @JoinColumn(name = "ID_BARRIO", referencedColumnName = "id")
    private Barrios barrio;
    
    @NotNull(message = "Ingrese Empresa")
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "id")
    private Empresas empresa;
    
    @Column(name = "ENTIDAD")
    private String entidad = "SUCURSALES";
    
    @Transient
    private List<DepartamentosSucursal> departamentos;

    public Sucursales() {

    }

    public Sucursales(Long id) {
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
     * @return the codigoSucursal
     */
    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    /**
     * @param codigoSucursal the codigoSucursal to set
     */
    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    /**
     * @return the empresa
     */
    public Empresas getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the latitud
     */
    public Double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public Double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public List<DepartamentosSucursal> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<DepartamentosSucursal> departamentos) {
        this.departamentos = departamentos;
    }
    
    public String getClassName() {
        return this.getClass().getSimpleName();
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
    
}
