/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "GARANTIAS")
public class Garantias implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SECUENCIA = "SEQ_GARANTIAS_ID";   
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RIESGO_ASUMIDO")
    private BigDecimal riesgoAsumido;
    
    @Basic(optional = false)
    @Column(name = "TIPO_RELACION")
    private String tipoRelacion;
    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private String estado;
    
    @Basic(optional = false)
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    
    @JsonIgnore
    @Column(name = "FECHA_CREACION")
    private Timestamp fechaCreacion;
    
    @JsonIgnore
    @Column(name = "FECHA_MODIFICACION")
    private Timestamp fechaModificacion;
    
    @Column(name = "INDICADOR_IMPRESION")
    private String indicadorImpresion;
    
    @Column(name = "ID_CREDITO")
    private String idCredito;
    
    @Column(name = "ID_HIPOTECA")
    private Integer idHipoteca;
    
    @Column(name = "ID_DOCUMENTO")
    private Long idDocumento;
    
    @Column(name = "ID_REFERENCIA")
    private String idReferencia;
    
    @Column(name = "SALDO")
    private BigDecimal saldo;
    
    @Column(name = "SALDO_ORIGINAL")
    private BigDecimal saldoOriginal;
    
    @Column(name = "GENERACION_AUTOMATICA")
    private String generacionAutomatica;
//    @JoinColumns({
//        @JoinColumn(name = "ID_AHORRO", referencedColumnName = "ID_AHORRO")
//        , @JoinColumn(name = "ID_BLOQUEO", referencedColumnName = "ID_BLOQUEO")})
//    @ManyToOne
//    private Bloqueos bloqueos;
    @JoinColumn(name = "ID_TIPO_GARANTIA", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoGarantias tipoGarantias;
    
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    @ManyToOne
    private Personas persona;
    
    @JoinColumn(name = "ID_SOLICITUD_PROPUESTA", referencedColumnName = "id")
    @ManyToOne
    private PropuestaSolicitud propuestaSolicitud;

    public Garantias() {
    }

    public Garantias(Long id) {
        this.id = id;
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
     * @return the riesgoAsumido
     */
    public BigDecimal getRiesgoAsumido() {
        return riesgoAsumido;
    }

    /**
     * @param riesgoAsumido the riesgoAsumido to set
     */
    public void setRiesgoAsumido(BigDecimal riesgoAsumido) {
        this.riesgoAsumido = riesgoAsumido;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the fechaEstado
     */
    public Date getFechaEstado() {
        return fechaEstado;
    }

    /**
     * @param fechaEstado the fechaEstado to set
     */
    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    /**
     * @return the indicadorImpresion
     */
    public String getIndicadorImpresion() {
        return indicadorImpresion;
    }

    /**
     * @param indicadorImpresion the indicadorImpresion to set
     */
    public void setIndicadorImpresion(String indicadorImpresion) {
        this.indicadorImpresion = indicadorImpresion;
    }

    /**
     * @return the idCredito
     */
    public String getIdCredito() {
        return idCredito;
    }

    /**
     * @param idCredito the idCredito to set
     */
    public void setIdCredito(String idCredito) {
        this.idCredito = idCredito;
    }

    /**
     * @return the idHipoteca
     */
    public Integer getIdHipoteca() {
        return idHipoteca;
    }

    /**
     * @param idHipoteca the idHipoteca to set
     */
    public void setIdHipoteca(Integer idHipoteca) {
        this.idHipoteca = idHipoteca;
    }

    /**
     * @return the idDocumento
     */
    public Long getIdDocumento() {
        return idDocumento;
    }

    /**
     * @param idDocumento the idDocumento to set
     */
    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    /**
     * @return the idReferencia
     */
    public String getIdReferencia() {
        return idReferencia;
    }

    /**
     * @param idReferencia the idReferencia to set
     */
    public void setIdReferencia(String idReferencia) {
        this.idReferencia = idReferencia;
    }

    /**
     * @return the saldo
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the saldoOriginal
     */
    public BigDecimal getSaldoOriginal() {
        return saldoOriginal;
    }

    /**
     * @param saldoOriginal the saldoOriginal to set
     */
    public void setSaldoOriginal(BigDecimal saldoOriginal) {
        this.saldoOriginal = saldoOriginal;
    }

    /**
     * @return the generacionAutomatica
     */
    public String getGeneracionAutomatica() {
        return generacionAutomatica;
    }

    /**
     * @param generacionAutomatica the generacionAutomatica to set
     */
    public void setGeneracionAutomatica(String generacionAutomatica) {
        this.generacionAutomatica = generacionAutomatica;
    }

    /**
     * @return the tipoGarantias
     */
    public TipoGarantias getTipoGarantias() {
        return tipoGarantias;
    }

    /**
     * @param tipoGarantias the tipoGarantias to set
     */
    public void setTipoGarantias(TipoGarantias tipoGarantias) {
        this.tipoGarantias = tipoGarantias;
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
     * @return the propuestaSolicitud
     */
    public PropuestaSolicitud getPropuestaSolicitud() {
        return propuestaSolicitud;
    }

    /**
     * @param propuestaSolicitud the propuestaSolicitud to set
     */
    public void setPropuestaSolicitud(PropuestaSolicitud propuestaSolicitud) {
        this.propuestaSolicitud = propuestaSolicitud;
    }

    /**
     * @return the fechaCreacion
     */
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the fechaModificacion
     */
    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }
        
}
