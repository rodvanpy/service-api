/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "CREDITOS")
public class Creditos extends Base {

    private static final long serialVersionUID = 1L;
    private static final String SECUENCIA = "seq_credito_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTO_CAPITAL")
    private BigDecimal montoCapital;
    
    @Basic(optional = false)
    @Column(name = "MONTO_INTERES")
    private BigDecimal montoInteres;
    
    //descuento de los pagos al capital
    @Basic(optional = false)
    @Column(name = "SALDO_CAPITAL")
    private BigDecimal saldoCapital;
    
    //descuentos de monto interes
    @Basic(optional = false)
    @Column(name = "SALDO_INTERES")
    private BigDecimal saldoInteres;
    
    @Column(name = "ORDEN_CHEQUE")
    private String ordenCheque;
    
    @Basic(optional = true)
    @Column(name = "ID_FUNCIONARIO_DESEMBOLSO")
    private Long idFunsionarioDesembolso;
    
    @Basic(optional = true)
    @Column(name = "OPERACION")
    private String operacion;
    
    @Basic(optional = false)
    @Column(name = "TOTAL_DEVENGADO")
    private BigDecimal totalDevengado;
    
    @Column(name = "ID_CREDITO_CANCELADO")
    private String idCreditoCancelado;
    
    @Basic(optional = false)
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    
    @Basic(optional = false)
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    
    @Basic(optional = false)
    @Column(name = "PERIODO_GRACIA")
    private Short periodoGracia;
    
    @Basic(optional = false)
    @Column(name = "PERIODO_INTERES")
    private Short periodoInteres;
    
    @Basic(optional = false)
    @Column(name = "PERIODO_CAPITAL")
    private Short periodoCapital;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_TIPO_DESEMBOLSO", referencedColumnName = "id")
    private TipoDesembolsos tipoDesembolso;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_TIPO_CALCULO", referencedColumnName = "id")
    private TipoCalculos tipoCalculoImporte;
    
    @Basic(optional = false)
    @Column(name = "TIPO_INTERES")
    private String tipoInteres;
    
    @Column(name = "TASA_INTERES")
    private BigDecimal tasaInteres;
    
    @Column(name = "GASTOS_ADMINISTRATIVOS")
    private BigDecimal gastosAdministrativos;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "id")
    private EstadosCredito estado;
    
    @Basic(optional = false)
    @Column(name = "FECHA_GENERACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGeneracion;
    
    @Column(name = "FECHA_CALIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCalificacion;
    
    @Column(name = "FECHA_ULTIMO_DEVENGADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoDevengado;
    
    @Column(name = "FECHA_SITUACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSituacion;
    
    @Column(name = "PLAZO_OPERACION")
    private Short plazoOperacion;
    
    @Column(name = "TOTAL_DESEMBOLSADO")
    private BigDecimal totalDesembolsado;
    
    @Column(name = "CAMBIO_VENCIMIENTOS")
    private Short cambioVencimientos;
    
    @Column(name = "FECHA_ULT_CALCULO_SALDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltCalculoSaldo;
    
    @Column(name = "CAPITAL_A_CANCELAR")
    private BigDecimal capitalACancelar;
    
    @Column(name = "INTERES_A_CANCELAR")
    private BigDecimal interesACancelar;
    
    @Column(name = "MULTA_A_CANCELAR")
    private BigDecimal multaACancelar;
    
    @Column(name = "SALDO_A_CUENTA")
    private BigDecimal saldoACuenta;
    
    @Column(name = "SALDO_A_CUENTA_MULTA")
    private BigDecimal saldoACuentaMulta;
    
    @Column(name = "INTERES_MORATORIO_A_CANCELAR")
    private BigDecimal interesMoratorioACancelar;
    
    @Column(name = "FECHA_ULT_PAGO_CAPITAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltPagoCapital;
    
    @Column(name = "FECHA_ULT_PAGO_COMPENSAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltPagoCompensat;
    
    @Column(name = "FECHA_ULT_PAGO_MORAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltPagoMorat;
    
    @Column(name = "FECHA_ULT_PAGO_PUNIT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltPagoPunit;
    
    @Column(name = "FECHA_TRANSF_JUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTransfJud;
    
    @Column(name = "MONTO_TRANSF_JUD")
    private Long montoTransfJud;
    
    @Column(name = "ESTADO_BLOQUEADO")
    private String estadoBloqueado;
    
    @Column(name = "FECHA_LIBERACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLiberacion;
    
    @Column(name = "IVA_A_CANCELAR")
    private BigDecimal ivaACancelar;
    
    @Column(name = "PRIORIDAD_COBRO")
    private String prioridadCobro;
    
    @Column(name = "IVA_DEVENGADO_A_CANCELAR")
    private BigDecimal ivaDevengadoACancelar;
    
    @Column(name = "ETAPA_PRE_JUDICIAL")
    private String etapaPreJudicial;
    
    @Column(name = "PLAN_ESTA")
    private String planEsta;
    
    @Column(name = "ETAP_PASO_INCO")
    private String etapPasoInco;
    
    @Column(name = "D_VARIOS_DATO_MOTIVO")
    private Short dVariosDatoMotivo;
    
    @Column(name = "D_VARIOS_NUMERO_MOTIVO")
    private Short dVariosNumeroMotivo;
    
    @Column(name = "FECHA_ULT_CAMB_PRIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltCambPrio;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_TIPO_DESTINO", referencedColumnName = "id")
    private TipoDestinos tipoDestino;
    
    @ManyToOne
    @JoinColumn(name = "ID_SUCURSAL", referencedColumnName = "id")
    private Sucursales sucursal;
    
    @JoinColumn(name = "LEGAJO_ULT_CAMB_PRIO", referencedColumnName = "ID")
    @ManyToOne
    private Funcionarios legajoUltCambPrio;
    
    @JoinColumn(name = "SUPE_A_RECO", referencedColumnName = "ID")
    @ManyToOne
    private Funcionarios supeAReco;
    
    @JoinColumn(name = "ID_MODALIDAD", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Modalidades modalidad;
    
    @JoinColumn(name = "ID_MONEDA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Monedas moneda;
    
    @JoinColumn(name = "ID_SITUACION", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SituacionesCredito situacionCredito;
    
    @JoinColumn(name = "ID_SOLICITUD_PROPUESTA", referencedColumnName = "id")
    @ManyToOne
    private PropuestaSolicitud propuestaSolicitud;
    
    @JsonIgnore
    @Column(name = "ID_EMPRESA")
    private Long idEmpresa;

    public Creditos() {
    }

    public Creditos(Long id) {
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
     * @return the montoCapital
     */
    public BigDecimal getMontoCapital() {
        return montoCapital;
    }

    /**
     * @param montoCapital the montoCapital to set
     */
    public void setMontoCapital(BigDecimal montoCapital) {
        this.montoCapital = montoCapital;
    }

    /**
     * @return the montoInteres
     */
    public BigDecimal getMontoInteres() {
        return montoInteres;
    }

    /**
     * @param montoInteres the montoInteres to set
     */
    public void setMontoInteres(BigDecimal montoInteres) {
        this.montoInteres = montoInteres;
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
     * @return the ordenCheque
     */
    public String getOrdenCheque() {
        return ordenCheque;
    }

    /**
     * @param ordenCheque the ordenCheque to set
     */
    public void setOrdenCheque(String ordenCheque) {
        this.ordenCheque = ordenCheque;
    }

    public Long getIdFunsionarioDesembolso() {
        return idFunsionarioDesembolso;
    }

    public void setIdFunsionarioDesembolso(Long idFunsionarioDesembolso) {
        this.idFunsionarioDesembolso = idFunsionarioDesembolso;
    }    

    /**
     * @return the operacion
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    /**
     * @return the totalDevengado
     */
    public BigDecimal getTotalDevengado() {
        return totalDevengado;
    }

    /**
     * @param totalDevengado the totalDevengado to set
     */
    public void setTotalDevengado(BigDecimal totalDevengado) {
        this.totalDevengado = totalDevengado;
    }

    /**
     * @return the idCreditoCancelado
     */
    public String getIdCreditoCancelado() {
        return idCreditoCancelado;
    }

    /**
     * @param idCreditoCancelado the idCreditoCancelado to set
     */
    public void setIdCreditoCancelado(String idCreditoCancelado) {
        this.idCreditoCancelado = idCreditoCancelado;
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
     * @return the periodoGracia
     */
    public Short getPeriodoGracia() {
        return periodoGracia;
    }

    /**
     * @param periodoGracia the periodoGracia to set
     */
    public void setPeriodoGracia(Short periodoGracia) {
        this.periodoGracia = periodoGracia;
    }

    /**
     * @return the periodoInteres
     */
    public Short getPeriodoInteres() {
        return periodoInteres;
    }

    /**
     * @param periodoInteres the periodoInteres to set
     */
    public void setPeriodoInteres(Short periodoInteres) {
        this.periodoInteres = periodoInteres;
    }

    /**
     * @return the periodoCapital
     */
    public Short getPeriodoCapital() {
        return periodoCapital;
    }

    /**
     * @param periodoCapital the periodoCapital to set
     */
    public void setPeriodoCapital(Short periodoCapital) {
        this.periodoCapital = periodoCapital;
    }

    /**
     * @return the tipoDesembolso
     */
    public TipoDesembolsos getTipoDesembolso() {
        return tipoDesembolso;
    }

    /**
     * @param tipoDesembolso the tipoDesembolso to set
     */
    public void setTipoDesembolso(TipoDesembolsos tipoDesembolso) {
        this.tipoDesembolso = tipoDesembolso;
    }

    /**
     * @return the tipoCalculoImporte
     */
    public TipoCalculos getTipoCalculoImporte() {
        return tipoCalculoImporte;
    }

    /**
     * @param tipoCalculoImporte the tipoCalculoImporte to set
     */
    public void setTipoCalculoImporte(TipoCalculos tipoCalculoImporte) {
        this.tipoCalculoImporte = tipoCalculoImporte;
    }

    /**
     * @return the tipoInteres
     */
    public String getTipoInteres() {
        return tipoInteres;
    }

    /**
     * @param tipoInteres the tipoInteres to set
     */
    public void setTipoInteres(String tipoInteres) {
        this.tipoInteres = tipoInteres;
    }    

    /**
     * @return the fechaGeneracion
     */
    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    /**
     * @param fechaGeneracion the fechaGeneracion to set
     */
    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    /**
     * @return the fechaCalificacion
     */
    public Date getFechaCalificacion() {
        return fechaCalificacion;
    }

    /**
     * @param fechaCalificacion the fechaCalificacion to set
     */
    public void setFechaCalificacion(Date fechaCalificacion) {
        this.fechaCalificacion = fechaCalificacion;
    }

    /**
     * @return the fechaUltimoDevengado
     */
    public Date getFechaUltimoDevengado() {
        return fechaUltimoDevengado;
    }

    /**
     * @param fechaUltimoDevengado the fechaUltimoDevengado to set
     */
    public void setFechaUltimoDevengado(Date fechaUltimoDevengado) {
        this.fechaUltimoDevengado = fechaUltimoDevengado;
    }

    /**
     * @return the fechaSituacion
     */
    public Date getFechaSituacion() {
        return fechaSituacion;
    }

    /**
     * @param fechaSituacion the fechaSituacion to set
     */
    public void setFechaSituacion(Date fechaSituacion) {
        this.fechaSituacion = fechaSituacion;
    }

    /**
     * @return the plazoOperacion
     */
    public Short getPlazoOperacion() {
        return plazoOperacion;
    }

    /**
     * @param plazoOperacion the plazoOperacion to set
     */
    public void setPlazoOperacion(Short plazoOperacion) {
        this.plazoOperacion = plazoOperacion;
    }

    /**
     * @return the totalDesembolsado
     */
    public BigDecimal getTotalDesembolsado() {
        return totalDesembolsado;
    }

    /**
     * @param totalDesembolsado the totalDesembolsado to set
     */
    public void setTotalDesembolsado(BigDecimal totalDesembolsado) {
        this.totalDesembolsado = totalDesembolsado;
    }

    /**
     * @return the cambioVencimientos
     */
    public Short getCambioVencimientos() {
        return cambioVencimientos;
    }

    /**
     * @param cambioVencimientos the cambioVencimientos to set
     */
    public void setCambioVencimientos(Short cambioVencimientos) {
        this.cambioVencimientos = cambioVencimientos;
    }

    /**
     * @return the fechaUltCalculoSaldo
     */
    public Date getFechaUltCalculoSaldo() {
        return fechaUltCalculoSaldo;
    }

    /**
     * @param fechaUltCalculoSaldo the fechaUltCalculoSaldo to set
     */
    public void setFechaUltCalculoSaldo(Date fechaUltCalculoSaldo) {
        this.fechaUltCalculoSaldo = fechaUltCalculoSaldo;
    }

    /**
     * @return the capitalACancelar
     */
    public BigDecimal getCapitalACancelar() {
        return capitalACancelar;
    }

    /**
     * @param capitalACancelar the capitalACancelar to set
     */
    public void setCapitalACancelar(BigDecimal capitalACancelar) {
        this.capitalACancelar = capitalACancelar;
    }

    /**
     * @return the interesACancelar
     */
    public BigDecimal getInteresACancelar() {
        return interesACancelar;
    }

    /**
     * @param interesACancelar the interesACancelar to set
     */
    public void setInteresACancelar(BigDecimal interesACancelar) {
        this.interesACancelar = interesACancelar;
    }

    /**
     * @return the multaACancelar
     */
    public BigDecimal getMultaACancelar() {
        return multaACancelar;
    }

    /**
     * @param multaACancelar the multaACancelar to set
     */
    public void setMultaACancelar(BigDecimal multaACancelar) {
        this.multaACancelar = multaACancelar;
    }

    /**
     * @return the saldoACuenta
     */
    public BigDecimal getSaldoACuenta() {
        return saldoACuenta;
    }

    /**
     * @param saldoACuenta the saldoACuenta to set
     */
    public void setSaldoACuenta(BigDecimal saldoACuenta) {
        this.saldoACuenta = saldoACuenta;
    }

    /**
     * @return the saldoACuentaMulta
     */
    public BigDecimal getSaldoACuentaMulta() {
        return saldoACuentaMulta;
    }

    /**
     * @param saldoACuentaMulta the saldoACuentaMulta to set
     */
    public void setSaldoACuentaMulta(BigDecimal saldoACuentaMulta) {
        this.saldoACuentaMulta = saldoACuentaMulta;
    }

    /**
     * @return the interesMoratorioACancelar
     */
    public BigDecimal getInteresMoratorioACancelar() {
        return interesMoratorioACancelar;
    }

    /**
     * @param interesMoratorioACancelar the interesMoratorioACancelar to set
     */
    public void setInteresMoratorioACancelar(BigDecimal interesMoratorioACancelar) {
        this.interesMoratorioACancelar = interesMoratorioACancelar;
    }

    /**
     * @return the fechaUltPagoCapital
     */
    public Date getFechaUltPagoCapital() {
        return fechaUltPagoCapital;
    }

    /**
     * @param fechaUltPagoCapital the fechaUltPagoCapital to set
     */
    public void setFechaUltPagoCapital(Date fechaUltPagoCapital) {
        this.fechaUltPagoCapital = fechaUltPagoCapital;
    }

    /**
     * @return the fechaUltPagoCompensat
     */
    public Date getFechaUltPagoCompensat() {
        return fechaUltPagoCompensat;
    }

    /**
     * @param fechaUltPagoCompensat the fechaUltPagoCompensat to set
     */
    public void setFechaUltPagoCompensat(Date fechaUltPagoCompensat) {
        this.fechaUltPagoCompensat = fechaUltPagoCompensat;
    }

    /**
     * @return the fechaUltPagoMorat
     */
    public Date getFechaUltPagoMorat() {
        return fechaUltPagoMorat;
    }

    /**
     * @param fechaUltPagoMorat the fechaUltPagoMorat to set
     */
    public void setFechaUltPagoMorat(Date fechaUltPagoMorat) {
        this.fechaUltPagoMorat = fechaUltPagoMorat;
    }

    /**
     * @return the fechaUltPagoPunit
     */
    public Date getFechaUltPagoPunit() {
        return fechaUltPagoPunit;
    }

    /**
     * @param fechaUltPagoPunit the fechaUltPagoPunit to set
     */
    public void setFechaUltPagoPunit(Date fechaUltPagoPunit) {
        this.fechaUltPagoPunit = fechaUltPagoPunit;
    }

    /**
     * @return the fechaTransfJud
     */
    public Date getFechaTransfJud() {
        return fechaTransfJud;
    }

    /**
     * @param fechaTransfJud the fechaTransfJud to set
     */
    public void setFechaTransfJud(Date fechaTransfJud) {
        this.fechaTransfJud = fechaTransfJud;
    }

    /**
     * @return the montoTransfJud
     */
    public Long getMontoTransfJud() {
        return montoTransfJud;
    }

    /**
     * @param montoTransfJud the montoTransfJud to set
     */
    public void setMontoTransfJud(Long montoTransfJud) {
        this.montoTransfJud = montoTransfJud;
    }

    /**
     * @return the estadoBloqueado
     */
    public String getEstadoBloqueado() {
        return estadoBloqueado;
    }

    /**
     * @param estadoBloqueado the estadoBloqueado to set
     */
    public void setEstadoBloqueado(String estadoBloqueado) {
        this.estadoBloqueado = estadoBloqueado;
    }

    /**
     * @return the fechaLiberacion
     */
    public Date getFechaLiberacion() {
        return fechaLiberacion;
    }

    /**
     * @param fechaLiberacion the fechaLiberacion to set
     */
    public void setFechaLiberacion(Date fechaLiberacion) {
        this.fechaLiberacion = fechaLiberacion;
    }

    /**
     * @return the ivaACancelar
     */
    public BigDecimal getIvaACancelar() {
        return ivaACancelar;
    }

    /**
     * @param ivaACancelar the ivaACancelar to set
     */
    public void setIvaACancelar(BigDecimal ivaACancelar) {
        this.ivaACancelar = ivaACancelar;
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
     * @return the ivaDevengadoACancelar
     */
    public BigDecimal getIvaDevengadoACancelar() {
        return ivaDevengadoACancelar;
    }

    /**
     * @param ivaDevengadoACancelar the ivaDevengadoACancelar to set
     */
    public void setIvaDevengadoACancelar(BigDecimal ivaDevengadoACancelar) {
        this.ivaDevengadoACancelar = ivaDevengadoACancelar;
    }

    /**
     * @return the etapaPreJudicial
     */
    public String getEtapaPreJudicial() {
        return etapaPreJudicial;
    }

    /**
     * @param etapaPreJudicial the etapaPreJudicial to set
     */
    public void setEtapaPreJudicial(String etapaPreJudicial) {
        this.etapaPreJudicial = etapaPreJudicial;
    }

    /**
     * @return the planEsta
     */
    public String getPlanEsta() {
        return planEsta;
    }

    /**
     * @param planEsta the planEsta to set
     */
    public void setPlanEsta(String planEsta) {
        this.planEsta = planEsta;
    }

    /**
     * @return the etapPasoInco
     */
    public String getEtapPasoInco() {
        return etapPasoInco;
    }

    /**
     * @param etapPasoInco the etapPasoInco to set
     */
    public void setEtapPasoInco(String etapPasoInco) {
        this.etapPasoInco = etapPasoInco;
    }

    /**
     * @return the dVariosDatoMotivo
     */
    public Short getdVariosDatoMotivo() {
        return dVariosDatoMotivo;
    }

    /**
     * @param dVariosDatoMotivo the dVariosDatoMotivo to set
     */
    public void setdVariosDatoMotivo(Short dVariosDatoMotivo) {
        this.dVariosDatoMotivo = dVariosDatoMotivo;
    }

    /**
     * @return the dVariosNumeroMotivo
     */
    public Short getdVariosNumeroMotivo() {
        return dVariosNumeroMotivo;
    }

    /**
     * @param dVariosNumeroMotivo the dVariosNumeroMotivo to set
     */
    public void setdVariosNumeroMotivo(Short dVariosNumeroMotivo) {
        this.dVariosNumeroMotivo = dVariosNumeroMotivo;
    }

    /**
     * @return the fechaUltCambPrio
     */
    public Date getFechaUltCambPrio() {
        return fechaUltCambPrio;
    }

    /**
     * @param fechaUltCambPrio the fechaUltCambPrio to set
     */
    public void setFechaUltCambPrio(Date fechaUltCambPrio) {
        this.fechaUltCambPrio = fechaUltCambPrio;
    }

    /**
     * @return the tipoDestino
     */
    public TipoDestinos getTipoDestino() {
        return tipoDestino;
    }

    /**
     * @param tipoDestino the tipoDestino to set
     */
    public void setTipoDestino(TipoDestinos tipoDestino) {
        this.tipoDestino = tipoDestino;
    }

    /**
     * @return the sucursal
     */
    public Sucursales getSucursal() {
        return sucursal;
    }

    /**
     * @param sucursal the sucursal to set
     */
    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * @return the legajoUltCambPrio
     */
    public Funcionarios getLegajoUltCambPrio() {
        return legajoUltCambPrio;
    }

    /**
     * @param legajoUltCambPrio the legajoUltCambPrio to set
     */
    public void setLegajoUltCambPrio(Funcionarios legajoUltCambPrio) {
        this.legajoUltCambPrio = legajoUltCambPrio;
    }

    /**
     * @return the supeAReco
     */
    public Funcionarios getSupeAReco() {
        return supeAReco;
    }

    /**
     * @param supeAReco the supeAReco to set
     */
    public void setSupeAReco(Funcionarios supeAReco) {
        this.supeAReco = supeAReco;
    }

    /**
     * @return the modalidad
     */
    public Modalidades getModalidad() {
        return modalidad;
    }

    /**
     * @param modalidad the modalidad to set
     */
    public void setModalidad(Modalidades modalidad) {
        this.modalidad = modalidad;
    }

    /**
     * @return the moneda
     */
    public Monedas getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Monedas moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the situacionCredito
     */
    public SituacionesCredito getSituacionCredito() {
        return situacionCredito;
    }

    /**
     * @param situacionCredito the situacionCredito to set
     */
    public void setSituacionCredito(SituacionesCredito situacionCredito) {
        this.situacionCredito = situacionCredito;
    }

    public EstadosCredito getEstado() {
        return estado;
    }

    public void setEstado(EstadosCredito estado) {
        this.estado = estado;
    }

    public PropuestaSolicitud getPropuestaSolicitud() {
        return propuestaSolicitud;
    }

    public void setPropuestaSolicitud(PropuestaSolicitud propuestaSolicitud) {
        this.propuestaSolicitud = propuestaSolicitud;
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public BigDecimal getGastosAdministrativos() {
        return gastosAdministrativos;
    }

    public void setGastosAdministrativos(BigDecimal gastosAdministrativos) {
        this.gastosAdministrativos = gastosAdministrativos;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
          
}
