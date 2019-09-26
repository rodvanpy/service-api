/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

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
import javax.persistence.Transient;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "EVALUACION_SOLICITUDES_CABECERA")
public class EvaluacionSolicitudesCabecera extends Base {

    private static final long serialVersionUID = 1L;
    private static final String SECUENCIA = "seq_evaluacion_solicitud_cab_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Basic(optional = true)
    @Column(name = "FECHA_INICIO_ANALISIS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioAnalisis;

    @Basic(optional = true)
    @Column(name = "FECHA_FIN_ANALISIS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinAnalisis;

    @Column(name = "FECHA_PRIMERA_APROB_RECH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrimeraAprobRech;

    @Column(name = "FECHA_SEGUNDA_APROB_RECH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSegundaAprobRech;

    @JoinColumn(name = "ID_FUNCIONARIO_APROBACION", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Funcionarios funcionarioAprobacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_ESTADO_ANALISIS", referencedColumnName = "id")
    private EstadosSolicitud estado;

    @Column(name = "MONTO_APROBADO")
    private Long montoAprobado;

    @JoinColumn(name = "ID_FUNCIONARIO_ANALISIS", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Funcionarios funcionarioAnalisis;

    @JoinColumn(name = "ID_FUNCIONARIO_VERIFICADOR", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Funcionarios funcionarioVerificador;

    @Column(name = "OBSERVACION")
    private String observacion;

    @Column(name = "OBSERVACION_RECOMENDACION")
    private String observacionRecomendacion;
    
    @Column(name = "OBSERVACION_RETRANSFERENCIA")
    private String observacionRetransferencia;

    @Column(name = "OBS_APRO")
    private String obsApro;

    @JoinColumn(name = "ID_SOLICITUD_PROPUESTA", referencedColumnName = "id")
    @ManyToOne
    private PropuestaSolicitud propuestaSolicitud;

    @Transient
    private List<EvaluacionSolicitudesDetalles> detalles;

    public EvaluacionSolicitudesCabecera() {

    }

    public EvaluacionSolicitudesCabecera(Long id) {
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
     * @return the fechaInicioAnalisis
     */
    public Date getFechaInicioAnalisis() {
        return fechaInicioAnalisis;
    }

    /**
     * @param fechaInicioAnalisis the fechaInicioAnalisis to set
     */
    public void setFechaInicioAnalisis(Date fechaInicioAnalisis) {
        this.fechaInicioAnalisis = fechaInicioAnalisis;
    }

    /**
     * @return the fechaFinAnalisis
     */
    public Date getFechaFinAnalisis() {
        return fechaFinAnalisis;
    }

    /**
     * @param fechaFinAnalisis the fechaFinAnalisis to set
     */
    public void setFechaFinAnalisis(Date fechaFinAnalisis) {
        this.fechaFinAnalisis = fechaFinAnalisis;
    }

    /**
     * @return the fechaPrimeraAprobRech
     */
    public Date getFechaPrimeraAprobRech() {
        return fechaPrimeraAprobRech;
    }

    /**
     * @param fechaPrimeraAprobRech the fechaPrimeraAprobRech to set
     */
    public void setFechaPrimeraAprobRech(Date fechaPrimeraAprobRech) {
        this.fechaPrimeraAprobRech = fechaPrimeraAprobRech;
    }

    /**
     * @return the fechaSegundaAprobRech
     */
    public Date getFechaSegundaAprobRech() {
        return fechaSegundaAprobRech;
    }

    /**
     * @param fechaSegundaAprobRech the fechaSegundaAprobRech to set
     */
    public void setFechaSegundaAprobRech(Date fechaSegundaAprobRech) {
        this.fechaSegundaAprobRech = fechaSegundaAprobRech;
    }

    /**
     * @return the funcionarioAprobacion
     */
    public Funcionarios getFuncionarioAprobacion() {
        return funcionarioAprobacion;
    }

    /**
     * @param funcionarioAprobacion the funcionarioAprobacion to set
     */
    public void setFuncionarioAprobacion(Funcionarios funcionarioAprobacion) {
        this.funcionarioAprobacion = funcionarioAprobacion;
    }

    /**
     * @return the estado
     */
    public EstadosSolicitud getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadosSolicitud estado) {
        this.estado = estado;
    }

    /**
     * @return the montoAprobado
     */
    public Long getMontoAprobado() {
        return montoAprobado;
    }

    /**
     * @param montoAprobado the montoAprobado to set
     */
    public void setMontoAprobado(Long montoAprobado) {
        this.montoAprobado = montoAprobado;
    }

    /**
     * @return the funcionarioAnalisis
     */
    public Funcionarios getFuncionarioAnalisis() {
        return funcionarioAnalisis;
    }

    /**
     * @param funcionarioAnalisis the funcionarioAnalisis to set
     */
    public void setFuncionarioAnalisis(Funcionarios funcionarioAnalisis) {
        this.funcionarioAnalisis = funcionarioAnalisis;
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
     * @return the observacionRecomendacion
     */
    public String getObservacionRecomendacion() {
        return observacionRecomendacion;
    }

    /**
     * @param observacionRecomendacion the observacionRecomendacion to set
     */
    public void setObservacionRecomendacion(String observacionRecomendacion) {
        this.observacionRecomendacion = observacionRecomendacion;
    }

    /**
     * @return the funcionarioVerificador
     */
    public Funcionarios getFuncionarioVerificador() {
        return funcionarioVerificador;
    }

    /**
     * @param funcionarioVerificador the funcionarioVerificador to set
     */
    public void setFuncionarioVerificador(Funcionarios funcionarioVerificador) {
        this.funcionarioVerificador = funcionarioVerificador;
    }

    /**
     * @return the obsApro
     */
    public String getObsApro() {
        return obsApro;
    }

    /**
     * @param obsApro the obsApro to set
     */
    public void setObsApro(String obsApro) {
        this.obsApro = obsApro;
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
     * @return the detalles
     */
    public List<EvaluacionSolicitudesDetalles> getDetalles() {
        return detalles;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(List<EvaluacionSolicitudesDetalles> detalles) {
        this.detalles = detalles;
    }

    public String getObservacionRetransferencia() {
        return observacionRetransferencia;
    }

    public void setObservacionRetransferencia(String observacionRetransferencia) {
        this.observacionRetransferencia = observacionRetransferencia;
    }
    
    

}
