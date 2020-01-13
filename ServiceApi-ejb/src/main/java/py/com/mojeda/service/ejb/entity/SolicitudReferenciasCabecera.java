/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
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
@Table(name = "SOLICITUD_REFERENCIAS_CABECERA", schema = "PUBLIC")
public class SolicitudReferenciasCabecera extends Base {

    private static final long serialVersionUID = 1L;
    private static final String SECUENCIA = "seq_solicitud_ref_cab_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Basic(optional = true)
    @Column(name = "FECHA_INICIO_REFERENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioReferencia;

    @Basic(optional = true)
    @Column(name = "FECHA_FIN_REFERENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinReferencia;

    @JoinColumn(name = "ID_FUNCIONARIO_REFERENCIA", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Funcionarios funcionarioReferencia;

    @JoinColumn(name = "ID_FUNCIONARIO_SOLICITANTE", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Funcionarios funcionarioSolicitante;
    
    @Column(name = "OBSERVACION", length = 4000)
    private String observacion;   

    @JoinColumn(name = "ID_SOLICITUD_PROPUESTA", referencedColumnName = "id")
    @ManyToOne
    private PropuestaSolicitud propuestaSolicitud;
    
    @JsonIgnore
    @Column(name = "ID_EMPRESA")
    private Long idEmpresa;


    public SolicitudReferenciasCabecera() {

    }

    public SolicitudReferenciasCabecera(Long id) {
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
     * @return the fechaInicioReferencia
     */
    public Date getFechaInicioReferencia() {
        return fechaInicioReferencia;
    }

    /**
     * @param fechaInicioReferencia the fechaInicioReferencia to set
     */
    public void setFechaInicioReferencia(Date fechaInicioReferencia) {
        this.fechaInicioReferencia = fechaInicioReferencia;
    }

    /**
     * @return the fechaFinReferencia
     */
    public Date getFechaFinReferencia() {
        return fechaFinReferencia;
    }

    /**
     * @param fechaFinReferencia the fechaFinReferencia to set
     */
    public void setFechaFinReferencia(Date fechaFinReferencia) {
        this.fechaFinReferencia = fechaFinReferencia;
    }

    /**
     * @return the funcionarioReferencia
     */
    public Funcionarios getFuncionarioReferencia() {
        return funcionarioReferencia;
    }

    /**
     * @param funcionarioReferencia the funcionarioReferencia to set
     */
    public void setFuncionarioReferencia(Funcionarios funcionarioReferencia) {
        this.funcionarioReferencia = funcionarioReferencia;
    }

    /**
     * @return the funcionarioSolicitante
     */
    public Funcionarios getFuncionarioSolicitante() {
        return funcionarioSolicitante;
    }

    /**
     * @param funcionarioSolicitante the funcionarioSolicitante to set
     */
    public void setFuncionarioSolicitante(Funcionarios funcionarioSolicitante) {
        this.funcionarioSolicitante = funcionarioSolicitante;
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
     * @return the idEmpresa
     */
    public Long getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    
        
}
