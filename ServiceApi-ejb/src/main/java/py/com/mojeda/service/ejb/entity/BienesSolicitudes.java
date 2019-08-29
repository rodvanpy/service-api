/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "BIENES_SOLICITUDES")
public class BienesSolicitudes extends Base {

    private static final long serialVersionUID = 1L;
    private static final String SECUENCIA = "seq_bienes_solicitud_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "NUMERO_FINCA")
    private String numeroFinca;

    @Column(name = "CUENTA_CATASTRAL")
    private String cuentaCatastral;

    @Column(name = "DISTRITO")
    private String distrito;

    @Column(name = "ESCRITURADO")
    private Boolean escriturado;

    @Column(name = "EDIFICADO")
    private Boolean edificado;

    @Column(name = "HIPOTECADO")
    private Boolean hipotecado;

    @Column(name = "FECHA_HIPOTECA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHipoteca;

    @Column(name = "VENCIMIENTO_HIPOTECA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vencimientoHipoteca;

    @Column(name = "LUGAR_HIPOTECA")
    private String lugarHipoteca;

    @Column(name = "FECHA_DECLARACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeclaracion;

    @Column(name = "CUOTA_MENSUAL")
    private Double cuotaMensual;

    @Column(name = "VALOR_ACTUAL")
    private Double valorActual;

    @Column(name = "SALDO")
    private Double saldo;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "MARCA")
    private String marca;

    @Column(name = "MODELO_ANIO")
    private String modeloAnio;

    @Column(name = "FECHA_VENTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenta;

    @Column(name = "NUMERO_MATRICULA")
    private String numeroMatricula;

    @ManyToOne
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "id")
    private Paises pais;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTAMENTO_PAIS", referencedColumnName = "id")
    private DepartamentosPais departamento;

    @ManyToOne
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "id")
    private Ciudades ciudad;

    @Column(name = "BARRIO")
    private String barrio;
    
    @Column(name = "TIPO_BIEN")
    private String tipoBien;

    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Personas persona;
    
    @JoinColumn(name = "ID_SOLICITUD_PROPUESTA", referencedColumnName = "id")
    @ManyToOne
    private PropuestaSolicitud propuestaSolicitud;
    
    @Column(name = "LATITUD")
    private Double latitud;

    @Column(name = "LONGITUD")
    private Double longitud;
    
    @Column(name = "ENTIDAD")
    private String entidad = "BIENES_SOLICITUDES";

    public BienesSolicitudes() {
    }

    public BienesSolicitudes(Long id) {
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
     * @return the numeroFinca
     */
    public String getNumeroFinca() {
        return numeroFinca;
    }

    /**
     * @param numeroFinca the numeroFinca to set
     */
    public void setNumeroFinca(String numeroFinca) {
        this.numeroFinca = numeroFinca;
    }

    /**
     * @return the cuentaCatastral
     */
    public String getCuentaCatastral() {
        return cuentaCatastral;
    }

    /**
     * @param cuentaCatastral the cuentaCatastral to set
     */
    public void setCuentaCatastral(String cuentaCatastral) {
        this.cuentaCatastral = cuentaCatastral;
    }

    /**
     * @return the distrito
     */
    public String getDistrito() {
        return distrito;
    }

    /**
     * @param distrito the distrito to set
     */
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public Boolean getEscriturado() {
        return escriturado;
    }

    public void setEscriturado(Boolean escriturado) {
        this.escriturado = escriturado;
    }

    public Boolean getEdificado() {
        return edificado;
    }

    public void setEdificado(Boolean edificado) {
        this.edificado = edificado;
    }

    public Boolean getHipotecado() {
        return hipotecado;
    }

    public void setHipotecado(Boolean hipotecado) {
        this.hipotecado = hipotecado;
    }

    

    /**
     * @return the fechaHipoteca
     */
    public Date getFechaHipoteca() {
        return fechaHipoteca;
    }

    /**
     * @param fechaHipoteca the fechaHipoteca to set
     */
    public void setFechaHipoteca(Date fechaHipoteca) {
        this.fechaHipoteca = fechaHipoteca;
    }

    /**
     * @return the vencimientoHipoteca
     */
    public Date getVencimientoHipoteca() {
        return vencimientoHipoteca;
    }

    /**
     * @param vencimientoHipoteca the vencimientoHipoteca to set
     */
    public void setVencimientoHipoteca(Date vencimientoHipoteca) {
        this.vencimientoHipoteca = vencimientoHipoteca;
    }

    /**
     * @return the lugarHipoteca
     */
    public String getLugarHipoteca() {
        return lugarHipoteca;
    }

    /**
     * @param lugarHipoteca the lugarHipoteca to set
     */
    public void setLugarHipoteca(String lugarHipoteca) {
        this.lugarHipoteca = lugarHipoteca;
    }

    /**
     * @return the fechaDeclaracion
     */
    public Date getFechaDeclaracion() {
        return fechaDeclaracion;
    }

    /**
     * @param fechaDeclaracion the fechaDeclaracion to set
     */
    public void setFechaDeclaracion(Date fechaDeclaracion) {
        this.fechaDeclaracion = fechaDeclaracion;
    }

    /**
     * @return the cuotaMensual
     */
    public Double getCuotaMensual() {
        return cuotaMensual;
    }

    /**
     * @param cuotaMensual the cuotaMensual to set
     */
    public void setCuotaMensual(Double cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }

    /**
     * @return the valorActual
     */
    public Double getValorActual() {
        return valorActual;
    }

    /**
     * @param valorActual the valorActual to set
     */
    public void setValorActual(Double valorActual) {
        this.valorActual = valorActual;
    }

    /**
     * @return the saldo
     */
    public Double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
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
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the modeloAnio
     */
    public String getModeloAnio() {
        return modeloAnio;
    }

    /**
     * @param modeloAnio the modeloAnio to set
     */
    public void setModeloAnio(String modeloAnio) {
        this.modeloAnio = modeloAnio;
    }

    /**
     * @return the fechaVenta
     */
    public Date getFechaVenta() {
        return fechaVenta;
    }

    /**
     * @param fechaVenta the fechaVenta to set
     */
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    /**
     * @return the numeroMatricula
     */
    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    /**
     * @param numeroMatricula the numeroMatricula to set
     */
    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
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
    public String getBarrio() {
        return barrio;
    }

    /**
     * @param barrio the barrio to set
     */
    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    /**
     * @return the tipoBien
     */
    public String getTipoBien() {
        return tipoBien;
    }

    /**
     * @param tipoBien the tipoBien to set
     */
    public void setTipoBien(String tipoBien) {
        this.tipoBien = tipoBien;
    }

    /**
     * @return the persona
     */
    public Personas getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Personas persona) {
        this.persona = persona;
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

    public PropuestaSolicitud getPropuestaSolicitud() {
        return propuestaSolicitud;
    }

    public void setPropuestaSolicitud(PropuestaSolicitud propuestaSolicitud) {
        this.propuestaSolicitud = propuestaSolicitud;
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
    
    
    
}
