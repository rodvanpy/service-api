/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "CUOTAS", schema = "PUBLIC")
public class Cuotas implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SECUENCIA = "seq_cuota_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "NUMERO_CUOTA")
    private Integer numeroCuota;
    
    @Basic(optional = false)
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    
    @Basic(optional = false)
    @Column(name = "ESTADO_CUOTA")
    private EstadosCuota estadoCuota;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CAPITAL")
    private BigDecimal capital;
    
    @Column(name = "INTERES")
    private BigDecimal interes;
    
    @Column(name = "INTERES_DESCONTADO")
    private BigDecimal interesDescontado;
    
    @Basic(optional = false)
    @Column(name = "SALDO_CAPITAL")
    private BigDecimal saldoCapital;
    
    @Basic(optional = false)
    @Column(name = "SALDO_INTERES")
    private BigDecimal saldoInteres;
    
    @Basic(optional = false)
    @Column(name = "PRIORIDAD_COBRO")
    private String prioridadCobro;
    
    @Column(name = "FECHA_ULTIMO_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoPago;
    
    @Column(name = "FECHA_ESPERA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEspera;
    
    @Column(name = "FECHA_TRANSFERENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTransferencia;
    
    @Column(name = "INTERES_MORATORIO")
    private BigDecimal interesMoratorio;
    
    @Column(name = "PAGADO_INTERES_MORATORIO")
    private BigDecimal pagadoInteresMoratorio;
    
    @Column(name = "INTERES_PUNITORIO")
    private BigDecimal interesPunitorio;
    
    @Column(name = "PAGADO_INTERES_PUNITORIO")
    private BigDecimal pagadoInteresPunitorio;
    
    @Column(name = "GASTOS_COBRANZA")
    private BigDecimal gastosCobranza;
    
    @Column(name = "OBSERVACION")
    private String observacion;
    
    @Column(name = "TASA_INTERES")
    private BigDecimal tasaInteres;
    
    @Column(name = "INTERES_ADELANTADO")
    private BigDecimal interesAdelantado;
    
    @Column(name = "FECHA_ULT_PAGO_MORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltPagoMora;
    
    @Column(name = "FECHA_PAGO_ANTERIOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPagoAnterior;
    
    @Column(name = "FECHA_PAGO_ANT_MORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPagoAntMora;
    
    @Column(name = "FECHA_ULT_PAGO_MORATORIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltPagoMoratorio;
    
    @JoinColumn(name = "ID_CREDITO", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Creditos credito;

    public Cuotas() {
    }

    public Cuotas(Long id) {
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
     * @return the numeroCuota
     */
    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    /**
     * @param numeroCuota the numeroCuota to set
     */
    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public EstadosCuota getEstadoCuota() {
        return estadoCuota;
    }

    public void setEstadoCuota(EstadosCuota estadoCuota) {
        this.estadoCuota = estadoCuota;
    }   

    /**
     * @return the capital
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * @return the interes
     */
    public BigDecimal getInteres() {
        return interes;
    }

    /**
     * @param interes the interes to set
     */
    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    /**
     * @return the interesDescontado
     */
    public BigDecimal getInteresDescontado() {
        return interesDescontado;
    }

    /**
     * @param interesDescontado the interesDescontado to set
     */
    public void setInteresDescontado(BigDecimal interesDescontado) {
        this.interesDescontado = interesDescontado;
    }

    /**
     * @return the saldoCapital
     */
    public BigDecimal getSaldoCapital() {
        return saldoCapital;
    }

    /**
     * @param saldoCapital the saldoCapital to set
     */
    public void setSaldoCapital(BigDecimal saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    /**
     * @return the saldoInteres
     */
    public BigDecimal getSaldoInteres() {
        return saldoInteres;
    }

    /**
     * @param saldoInteres the saldoInteres to set
     */
    public void setSaldoInteres(BigDecimal saldoInteres) {
        this.saldoInteres = saldoInteres;
    }

    /**
     * @return the prioridadCobro
     */
    public String getPrioridadCobro() {
        return prioridadCobro;
    }

    /**
     * @param prioridadCobro the prioridadCobro to set
     */
    public void setPrioridadCobro(String prioridadCobro) {
        this.prioridadCobro = prioridadCobro;
    }

    /**
     * @return the fechaUltimoPago
     */
    public Date getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    /**
     * @param fechaUltimoPago the fechaUltimoPago to set
     */
    public void setFechaUltimoPago(Date fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    /**
     * @return the fechaEspera
     */
    public Date getFechaEspera() {
        return fechaEspera;
    }

    /**
     * @param fechaEspera the fechaEspera to set
     */
    public void setFechaEspera(Date fechaEspera) {
        this.fechaEspera = fechaEspera;
    }

    /**
     * @return the fechaTransferencia
     */
    public Date getFechaTransferencia() {
        return fechaTransferencia;
    }

    /**
     * @param fechaTransferencia the fechaTransferencia to set
     */
    public void setFechaTransferencia(Date fechaTransferencia) {
        this.fechaTransferencia = fechaTransferencia;
    }

    /**
     * @return the interesMoratorio
     */
    public BigDecimal getInteresMoratorio() {
        return interesMoratorio;
    }

    /**
     * @param interesMoratorio the interesMoratorio to set
     */
    public void setInteresMoratorio(BigDecimal interesMoratorio) {
        this.interesMoratorio = interesMoratorio;
    }

    /**
     * @return the pagadoInteresMoratorio
     */
    public BigDecimal getPagadoInteresMoratorio() {
        return pagadoInteresMoratorio;
    }

    /**
     * @param pagadoInteresMoratorio the pagadoInteresMoratorio to set
     */
    public void setPagadoInteresMoratorio(BigDecimal pagadoInteresMoratorio) {
        this.pagadoInteresMoratorio = pagadoInteresMoratorio;
    }

    /**
     * @return the interesPunitorio
     */
    public BigDecimal getInteresPunitorio() {
        return interesPunitorio;
    }

    /**
     * @param interesPunitorio the interesPunitorio to set
     */
    public void setInteresPunitorio(BigDecimal interesPunitorio) {
        this.interesPunitorio = interesPunitorio;
    }

    /**
     * @return the pagadoInteresPunitorio
     */
    public BigDecimal getPagadoInteresPunitorio() {
        return pagadoInteresPunitorio;
    }

    /**
     * @param pagadoInteresPunitorio the pagadoInteresPunitorio to set
     */
    public void setPagadoInteresPunitorio(BigDecimal pagadoInteresPunitorio) {
        this.pagadoInteresPunitorio = pagadoInteresPunitorio;
    }

    /**
     * @return the gastosCobranza
     */
    public BigDecimal getGastosCobranza() {
        return gastosCobranza;
    }

    /**
     * @param gastosCobranza the gastosCobranza to set
     */
    public void setGastosCobranza(BigDecimal gastosCobranza) {
        this.gastosCobranza = gastosCobranza;
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
     * @return the tasaInteres
     */
    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    /**
     * @param tasaInteres the tasaInteres to set
     */
    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    /**
     * @return the interesAdelantado
     */
    public BigDecimal getInteresAdelantado() {
        return interesAdelantado;
    }

    /**
     * @param interesAdelantado the interesAdelantado to set
     */
    public void setInteresAdelantado(BigDecimal interesAdelantado) {
        this.interesAdelantado = interesAdelantado;
    }

    /**
     * @return the fechaUltPagoMora
     */
    public Date getFechaUltPagoMora() {
        return fechaUltPagoMora;
    }

    /**
     * @param fechaUltPagoMora the fechaUltPagoMora to set
     */
    public void setFechaUltPagoMora(Date fechaUltPagoMora) {
        this.fechaUltPagoMora = fechaUltPagoMora;
    }

    /**
     * @return the fechaPagoAnterior
     */
    public Date getFechaPagoAnterior() {
        return fechaPagoAnterior;
    }

    /**
     * @param fechaPagoAnterior the fechaPagoAnterior to set
     */
    public void setFechaPagoAnterior(Date fechaPagoAnterior) {
        this.fechaPagoAnterior = fechaPagoAnterior;
    }

    /**
     * @return the fechaPagoAntMora
     */
    public Date getFechaPagoAntMora() {
        return fechaPagoAntMora;
    }

    /**
     * @param fechaPagoAntMora the fechaPagoAntMora to set
     */
    public void setFechaPagoAntMora(Date fechaPagoAntMora) {
        this.fechaPagoAntMora = fechaPagoAntMora;
    }

    /**
     * @return the fechaUltPagoMoratorio
     */
    public Date getFechaUltPagoMoratorio() {
        return fechaUltPagoMoratorio;
    }

    /**
     * @param fechaUltPagoMoratorio the fechaUltPagoMoratorio to set
     */
    public void setFechaUltPagoMoratorio(Date fechaUltPagoMoratorio) {
        this.fechaUltPagoMoratorio = fechaUltPagoMoratorio;
    }

    /**
     * @return the credito
     */
    public Creditos getCredito() {
        return credito;
    }

    /**
     * @param credito the credito to set
     */
    public void setCredito(Creditos credito) {
        this.credito = credito;
    }

    
    
}
