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
import javax.validation.constraints.NotNull;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "EVALUACION_SOLICITUDES_DETALLES")
public class EvaluacionSolicitudesDetalles extends Base {

    private static final long serialVersionUID = 1L;
    private static final String SECUENCIA = "seq_evaluacion_solicitud_det_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "TIPO_RELACION")
    private String tipoRelacion;

    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Personas persona;

    @Column(name = "SALDO_TOTAL")
    private Long saldoTotal;

    @Column(name = "MONTO_TOTAL_CUOTAS")
    private Long montoTotalCuotas;

    @Column(name = "TOTAL_TARJETA")
    private Long totalTarjeta;

    @Column(name = "TOTAL_TARJETA_MINIMO")
    private Long totalTarjetaMinimo;

    @Column(name = "MONTO_TOTAL_A_CANCELAR")
    private Long montoTotalACancelar;

    @Column(name = "MONTO_CUOTA_A_CANCELAR")
    private Long montoCuotaACancelar;

    @Column(name = "INGRESO_TOTAL")
    private Long ingresoTotal;
    
    @Column(name = "EGRESOS_TOTAL")
    private Long egresosTotal;

    @Column(name = "MONTO_CAPACIDAD_PAGO")
    private Long montoCapacidadPago;

    @Column(name = "PORCENTAJE_CAPACIDAD")
    private Long porcentajeCapacidad;

    @Column(name = "INGRESOS_OTROS")
    private Long ingresosOtros;

    @Column(name = "PORCENTAJE_CAPACIDAD_OTROS")
    private Long porcentajeCapacidadOtros;

    @Column(name = "TOTAL_DIFERENCIA_ING_EGR")
    private Long totalDiferenciaIngEgr;

    @Column(name = "CALIFICACION_CREDITOS_ACTUAL")
    private String calificacionCreditosActual;

    @Column(name = "GARANTIAS_VIGENTE")
    private String garantiasVigente;

    @Column(name = "INFORMCONF")
    private String informconf;

    @Column(name = "CAPACIDAD_PAGO")
    private String capacidadPago;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ANTIGUEDAD_LABORAL")
    private BigDecimal antiguedadLaboral;

    @Column(name = "EDAD")
    private Integer edad;

    @Column(name = "CANTIDAD_HIJOS")
    private Short cantidadHijos;

    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;

    @Column(name = "OBSERVACION")
    private String observacion;

    @Column(name = "OBSERVACION_RECOMENDACION")
    private String observacionRecomendacion;

    @Column(name = "FECHA_REUNION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReunion;

    @Column(name = "ID_REUNION")
    private Integer idReunion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PROFESION", referencedColumnName = "id")
    private Profesiones profesion;

    @Column(name = "DESCRIPCION_PROFESION")
    private String descripcionProfesion;    

    @Column(name = "CALIFICACION_CRED_CANC")
    private String calificacionCredCanc;

    @Column(name = "CAPITAL_NO_MENSUAL")
    private Long capitalNoMensual;

    @Column(name = "TIPO_CAPITAL")
    private String tipoCapital;

    @Column(name = "SALDO_TOTAL_GARANTIA")
    private Long saldoTotalGarantia;

    @Column(name = "SEPARACION_BIENES")
    private String separacionBienes;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "CONCLUIDO")
    private String concluido;        

    @Column(name = "TIPO_CANC_TARJETA")
    private String tipoCancTarjeta;

    @Column(name = "SALDO_TOTAL_CTRAL_RGO")
    private Long saldoTotalCtralRgo;

    @Column(name = "GENERA_TARJETA")
    private String generaTarjeta;

    @Column(name = "LIMITE_TARJETA")
    private BigInteger limiteTarjeta;

    @Column(name = "FAJA_INFORMCONF")
    private String fajaInformconf;

    @NotNull()
    @ManyToOne
    @JoinColumn(name = "ID_CABECERA", referencedColumnName = "id")
    private EvaluacionSolicitudesCabecera evaluacionSolicitudCabecera;

    public EvaluacionSolicitudesDetalles() {

    }

    public EvaluacionSolicitudesDetalles(Long id) {
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
     * @return the tipoRelacion
     */
    public String getTipoRelacion() {
        return tipoRelacion;
    }

    /**
     * @param tipoRelacion the tipoRelacion to set
     */
    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
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
     * @return the saldoTotal
     */
    public Long getSaldoTotal() {
        return saldoTotal;
    }

    /**
     * @param saldoTotal the saldoTotal to set
     */
    public void setSaldoTotal(Long saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    /**
     * @return the montoTotalCuotas
     */
    public Long getMontoTotalCuotas() {
        return montoTotalCuotas;
    }

    /**
     * @param montoTotalCuotas the montoTotalCuotas to set
     */
    public void setMontoTotalCuotas(Long montoTotalCuotas) {
        this.montoTotalCuotas = montoTotalCuotas;
    }

    /**
     * @return the totalTarjeta
     */
    public Long getTotalTarjeta() {
        return totalTarjeta;
    }

    /**
     * @param totalTarjeta the totalTarjeta to set
     */
    public void setTotalTarjeta(Long totalTarjeta) {
        this.totalTarjeta = totalTarjeta;
    }

    /**
     * @return the totalTarjetaMinimo
     */
    public Long getTotalTarjetaMinimo() {
        return totalTarjetaMinimo;
    }

    /**
     * @param totalTarjetaMinimo the totalTarjetaMinimo to set
     */
    public void setTotalTarjetaMinimo(Long totalTarjetaMinimo) {
        this.totalTarjetaMinimo = totalTarjetaMinimo;
    }
    

    /**
     * @return the montoTotalACancelar
     */
    public Long getMontoTotalACancelar() {
        return montoTotalACancelar;
    }

    /**
     * @param montoTotalACancelar the montoTotalACancelar to set
     */
    public void setMontoTotalACancelar(Long montoTotalACancelar) {
        this.montoTotalACancelar = montoTotalACancelar;
    }

    /**
     * @return the montoCuotaACancelar
     */
    public Long getMontoCuotaACancelar() {
        return montoCuotaACancelar;
    }

    /**
     * @param montoCuotaACancelar the montoCuotaACancelar to set
     */
    public void setMontoCuotaACancelar(Long montoCuotaACancelar) {
        this.montoCuotaACancelar = montoCuotaACancelar;
    }

    /**
     * @return the ingresoTotal
     */
    public Long getIngresoTotal() {
        return ingresoTotal;
    }

    /**
     * @param ingresoTotal the ingresoTotal to set
     */
    public void setIngresoTotal(Long ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }

    /**
     * @return the montoCapacidadPago
     */
    public Long getMontoCapacidadPago() {
        return montoCapacidadPago;
    }

    /**
     * @param montoCapacidadPago the montoCapacidadPago to set
     */
    public void setMontoCapacidadPago(Long montoCapacidadPago) {
        this.montoCapacidadPago = montoCapacidadPago;
    }

    /**
     * @return the porcentajeCapacidad
     */
    public Long getPorcentajeCapacidad() {
        return porcentajeCapacidad;
    }

    /**
     * @param porcentajeCapacidad the porcentajeCapacidad to set
     */
    public void setPorcentajeCapacidad(Long porcentajeCapacidad) {
        this.porcentajeCapacidad = porcentajeCapacidad;
    }

    /**
     * @return the ingresosOtros
     */
    public Long getIngresosOtros() {
        return ingresosOtros;
    }

    /**
     * @param ingresosOtros the ingresosOtros to set
     */
    public void setIngresosOtros(Long ingresosOtros) {
        this.ingresosOtros = ingresosOtros;
    }

    /**
     * @return the porcentajeCapacidadOtros
     */
    public Long getPorcentajeCapacidadOtros() {
        return porcentajeCapacidadOtros;
    }

    /**
     * @param porcentajeCapacidadOtros the porcentajeCapacidadOtros to set
     */
    public void setPorcentajeCapacidadOtros(Long porcentajeCapacidadOtros) {
        this.porcentajeCapacidadOtros = porcentajeCapacidadOtros;
    }

    /**
     * @return the totalDiferenciaIngEgr
     */
    public Long getTotalDiferenciaIngEgr() {
        return totalDiferenciaIngEgr;
    }

    /**
     * @param totalDiferenciaIngEgr the totalDiferenciaIngEgr to set
     */
    public void setTotalDiferenciaIngEgr(Long totalDiferenciaIngEgr) {
        this.totalDiferenciaIngEgr = totalDiferenciaIngEgr;
    }

    /**
     * @return the calificacionCreditosActual
     */
    public String getCalificacionCreditosActual() {
        return calificacionCreditosActual;
    }

    /**
     * @param calificacionCreditosActual the calificacionCreditosActual to set
     */
    public void setCalificacionCreditosActual(String calificacionCreditosActual) {
        this.calificacionCreditosActual = calificacionCreditosActual;
    }

    /**
     * @return the garantiasVigente
     */
    public String getGarantiasVigente() {
        return garantiasVigente;
    }

    /**
     * @param garantiasVigente the garantiasVigente to set
     */
    public void setGarantiasVigente(String garantiasVigente) {
        this.garantiasVigente = garantiasVigente;
    }

    /**
     * @return the informconf
     */
    public String getInformconf() {
        return informconf;
    }

    /**
     * @param informconf the informconf to set
     */
    public void setInformconf(String informconf) {
        this.informconf = informconf;
    }

    /**
     * @return the capacidadPago
     */
    public String getCapacidadPago() {
        return capacidadPago;
    }

    /**
     * @param capacidadPago the capacidadPago to set
     */
    public void setCapacidadPago(String capacidadPago) {
        this.capacidadPago = capacidadPago;
    }

    /**
     * @return the antiguedadLaboral
     */
    public BigDecimal getAntiguedadLaboral() {
        return antiguedadLaboral;
    }

    /**
     * @param antiguedadLaboral the antiguedadLaboral to set
     */
    public void setAntiguedadLaboral(BigDecimal antiguedadLaboral) {
        this.antiguedadLaboral = antiguedadLaboral;
    }

    /**
     * @return the edad
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * @return the cantidadHijos
     */
    public Short getCantidadHijos() {
        return cantidadHijos;
    }

    /**
     * @param cantidadHijos the cantidadHijos to set
     */
    public void setCantidadHijos(Short cantidadHijos) {
        this.cantidadHijos = cantidadHijos;
    }

    /**
     * @return the estadoCivil
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * @param estadoCivil the estadoCivil to set
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
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
     * @return the fechaReunion
     */
    public Date getFechaReunion() {
        return fechaReunion;
    }

    /**
     * @param fechaReunion the fechaReunion to set
     */
    public void setFechaReunion(Date fechaReunion) {
        this.fechaReunion = fechaReunion;
    }

    /**
     * @return the idReunion
     */
    public Integer getIdReunion() {
        return idReunion;
    }

    /**
     * @param idReunion the idReunion to set
     */
    public void setIdReunion(Integer idReunion) {
        this.idReunion = idReunion;
    }

    /**
     * @return the profesion
     */
    public Profesiones getProfesion() {
        return profesion;
    }

    /**
     * @param profesion the profesion to set
     */
    public void setProfesion(Profesiones profesion) {
        this.profesion = profesion;
    }

    /**
     * @return the descripcionProfesion
     */
    public String getDescripcionProfesion() {
        return descripcionProfesion;
    }

    /**
     * @param descripcionProfesion the descripcionProfesion to set
     */
    public void setDescripcionProfesion(String descripcionProfesion) {
        this.descripcionProfesion = descripcionProfesion;
    }
    

    /**
     * @return the calificacionCredCanc
     */
    public String getCalificacionCredCanc() {
        return calificacionCredCanc;
    }

    /**
     * @param calificacionCredCanc the calificacionCredCanc to set
     */
    public void setCalificacionCredCanc(String calificacionCredCanc) {
        this.calificacionCredCanc = calificacionCredCanc;
    }

    /**
     * @return the capitalNoMensual
     */
    public Long getCapitalNoMensual() {
        return capitalNoMensual;
    }

    /**
     * @param capitalNoMensual the capitalNoMensual to set
     */
    public void setCapitalNoMensual(Long capitalNoMensual) {
        this.capitalNoMensual = capitalNoMensual;
    }

    /**
     * @return the tipoCapital
     */
    public String getTipoCapital() {
        return tipoCapital;
    }

    /**
     * @param tipoCapital the tipoCapital to set
     */
    public void setTipoCapital(String tipoCapital) {
        this.tipoCapital = tipoCapital;
    }

    /**
     * @return the saldoTotalGarantia
     */
    public Long getSaldoTotalGarantia() {
        return saldoTotalGarantia;
    }

    /**
     * @param saldoTotalGarantia the saldoTotalGarantia to set
     */
    public void setSaldoTotalGarantia(Long saldoTotalGarantia) {
        this.saldoTotalGarantia = saldoTotalGarantia;
    }

    /**
     * @return the separacionBienes
     */
    public String getSeparacionBienes() {
        return separacionBienes;
    }

    /**
     * @param separacionBienes the separacionBienes to set
     */
    public void setSeparacionBienes(String separacionBienes) {
        this.separacionBienes = separacionBienes;
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
     * @return the concluido
     */
    public String getConcluido() {
        return concluido;
    }

    /**
     * @param concluido the concluido to set
     */
    public void setConcluido(String concluido) {
        this.concluido = concluido;
    }

    /**
     * @return the tipoCancTarjeta
     */
    public String getTipoCancTarjeta() {
        return tipoCancTarjeta;
    }

    /**
     * @param tipoCancTarjeta the tipoCancTarjeta to set
     */
    public void setTipoCancTarjeta(String tipoCancTarjeta) {
        this.tipoCancTarjeta = tipoCancTarjeta;
    }

    /**
     * @return the saldoTotalCtralRgo
     */
    public Long getSaldoTotalCtralRgo() {
        return saldoTotalCtralRgo;
    }

    /**
     * @param saldoTotalCtralRgo the saldoTotalCtralRgo to set
     */
    public void setSaldoTotalCtralRgo(Long saldoTotalCtralRgo) {
        this.saldoTotalCtralRgo = saldoTotalCtralRgo;
    }

    /**
     * @return the generaTarjeta
     */
    public String getGeneraTarjeta() {
        return generaTarjeta;
    }

    /**
     * @param generaTarjeta the generaTarjeta to set
     */
    public void setGeneraTarjeta(String generaTarjeta) {
        this.generaTarjeta = generaTarjeta;
    }

    /**
     * @return the limiteTarjeta
     */
    public BigInteger getLimiteTarjeta() {
        return limiteTarjeta;
    }

    /**
     * @param limiteTarjeta the limiteTarjeta to set
     */
    public void setLimiteTarjeta(BigInteger limiteTarjeta) {
        this.limiteTarjeta = limiteTarjeta;
    }

    /**
     * @return the fajaInformconf
     */
    public String getFajaInformconf() {
        return fajaInformconf;
    }

    /**
     * @param fajaInformconf the fajaInformconf to set
     */
    public void setFajaInformconf(String fajaInformconf) {
        this.fajaInformconf = fajaInformconf;
    }

    /**
     * @return the evaluacionSolicitudCabecera
     */
    public EvaluacionSolicitudesCabecera getEvaluacionSolicitudCabecera() {
        return evaluacionSolicitudCabecera;
    }

    /**
     * @param evaluacionSolicitudCabecera the evaluacionSolicitudCabecera to set
     */
    public void setEvaluacionSolicitudCabecera(EvaluacionSolicitudesCabecera evaluacionSolicitudCabecera) {
        this.evaluacionSolicitudCabecera = evaluacionSolicitudCabecera;
    }

    /**
     * @return the egresosTotal
     */
    public Long getEgresosTotal() {
        return egresosTotal;
    }

    /**
     * @param egresosTotal the egresosTotal to set
     */
    public void setEgresosTotal(Long egresosTotal) {
        this.egresosTotal = egresosTotal;
    }
    
    

}
