/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
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
import org.hibernate.annotations.Index;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "PROPUESTA_SOLICITUD", schema = "PUBLIC")
public class PropuestaSolicitud extends Base{

    private static final long serialVersionUID = 1L;
    private static final String SECUENCIA = "seq_propuesta_solicitud_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "FECHA_PRESENTACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPresentacion;
    
    @Basic(optional = false)
    @Column(name = "HORA_PRESENTACION")
    private String horaPresentacion;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_ESTADO_SOLICITUD", referencedColumnName = "id")
    private EstadosSolicitud estado;
    
    @Basic(optional = false)
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    
    @Column(name = "PUNTAJE")
    private Short puntaje;
    
    @Basic(optional = false)
    @Column(name = "MONTO_SOLICITADO")
    private BigDecimal montoSolicitado;
    
    @Column(name = "TIPO_CREDITO")
    private String tipoCredito;
    
    @Column(name = "TIPO_DESCUENTO")
    private String tipoDescuento;
    
    @Column(name = "TASA_INTERES")
    private BigDecimal tasaInteres;
    
    @Column(name = "GASTOS_ADMINISTRATIVOS")
    private BigDecimal gastosAdministrativos;
    
    @Column(name = "IMPUESTOS")
    private Long impuestos;
    
    @Column(name = "COMISION")
    private Long comision;
    
    @Column(name = "GASTOS_VARIOS")
    private Long gastosVarios;
    
    @Column(name = "SEGUROS")
    private Long seguros;
    
    @Column(name = "VENCIMIENTO_INTERES")
    private Long vencimientoInteres;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_TIPO_DESEMBOLSO", referencedColumnName = "id")
    private TipoDesembolsos tipoDesembolso;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_TIPO_CALCULO", referencedColumnName = "id")
    private TipoCalculos tipoCalculoImporte;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_TIPO_GARANTIA", referencedColumnName = "id")
    private TipoGarantias tipoGarantia;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_TIPO_PAGO", referencedColumnName = "id")
    private TipoPagos tipoPago;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_TIPO_DESTINO", referencedColumnName = "id")
    private TipoDestinos tipoDestino;
    
    @Basic(optional = true)
    @Column(name = "TIPO_INTERES")
    private String tipoInteres;
    
    @Column(name = "ORDEN_CHEQUE")
    private String ordenCheque;
    
    @Column(name = "FORMA_PAGO")
    private String formaPago;
    
    @Column(name = "PLAZO")
    private Long plazo;
    
    @Column(name = "PERIODO_GRACIA")
    private Short periodoGracia;
    
    @Column(name = "PERIODO_CAPITAL")
    private Short periodoCapital;
    
    @Column(name = "PERIODO_INTERES")
    private Short periodoInteres;
    
    @Column(name = "PLAZO_OPERACION")
    private Short plazoOperacion;
    
    @Column(name = "ID_AHORRO_DEBITO")
    private String idAhorroDebito;
    
    @Column(name = "ID_AHORRO_DESEMBOLSO")
    private String idAhorroDesembolso;
    
    @Column(name = "FECHA_ANALISIS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnalisis;
    
    @Column(name = "FECHA_APROBACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;
    
    @Column(name = "FECHA_RECHAZO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRechazo;
    
    @Column(name = "ID_TARJETA")
    private Long idTarjeta;
    
    @Column(name = "ID_CREDITO")
    private String idCredito;
    
    @Column(name = "OBSERVACIONES_DEPARTAMENTO", length = 4000)
    private String observacionesDepartamento;
    
    @Column(name = "OBSERVACIONES_COMITE", length = 4000)
    private String observacionesComite;
    
    @Column(name = "OBSERVACIONES_CONSEJO", length = 4000)
    private String observacionesConsejo;
    
    @Column(name = "FECHA_GENERACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGeneracion;
    
    @Column(name = "PRIMER_VENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date primerVencimiento;
    
    @Column(name = "MONTO_SOLICITADO_ORIGINAL")
    private BigDecimal montoSolicitadoOriginal;
    
    @Column(name = "HORA_ESTADO")
    private String horaEstado;
    
    @Column(name = "USER_NUMERO_LEGAJO")
    private Long userNumeroLegajo;
    
    @Column(name = "OBSERVACION_FORMALIZADOR", length = 4000)
    private String observacionFormalizador;
    
    @Column(name = "IMPORTE_CUOTA")
    private Long importeCuota;
    
    @Column(name = "IMPORTE_ENTREGAR")
    private Long importeEntregar;
    
    @Column(name = "DETALLE_DESTINO", length = 4000)
    private String detalleDestino;
    
    @Column(name = "BENEFICIARIO_CHEQUE")
    private String beneficiarioCheque;
    
    @Column(name = "NUMERO_LEGAJO_ADM")
    private Long numeroLegajoAdm;
    
    @Column(name = "TIPO_ENVIO_SOLICITUD")
    private String tipoEnvioSolicitud;
    
    @JoinColumn(name = "ID_MONEDA", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Monedas moneda;
    
    @JoinColumn(name = "ID_MODALIDAD", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Modalidades modalidad;
    
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Clientes cliente;
    
    @JoinColumn(name = "ID_PERSONA_CODEUDOR", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Personas codeudor;
    
    @JoinColumn(name = "ID_FUNCIONARIO_ALTA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Funcionarios funcionario;
    
    @ManyToOne
    @JoinColumn(name = "ID_SUCURSAL_ALTA", referencedColumnName = "id")
    private Sucursales sucursal;
    
    @Column(name = "ENTIDAD")
    private String entidad = "PROPUESTA_SOLICITUD";
    
    @Column(name = "DESEMBOLSADO")
    private String desembolsado;
    
    @Column(name = "FECHA_DESEMBOLSO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesembolso;
    
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_SOLICITUD", referencedColumnName = "id")
    private TipoSolicitudes tipoSolicitud;
    
    @JsonIgnore
    @Column(name = "ID_EMPRESA")
    private Long idEmpresa;
    
    @Transient
    private EvaluacionSolicitudesCabecera evaluacion;
    
    public PropuestaSolicitud() {

    }

    public PropuestaSolicitud(Long id) {
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
     * @return the fechaPresentacion
     */
    public Date getFechaPresentacion() {
        return fechaPresentacion;
    }

    /**
     * @param fechaPresentacion the fechaPresentacion to set
     */
    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

    /**
     * @return the horaPresentacion
     */
    public String getHoraPresentacion() {
        return horaPresentacion;
    }

    /**
     * @param horaPresentacion the horaPresentacion to set
     */
    public void setHoraPresentacion(String horaPresentacion) {
        this.horaPresentacion = horaPresentacion;
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
     * @return the puntaje
     */
    public Short getPuntaje() {
        return puntaje;
    }

    /**
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(Short puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * @return the montoSolicitado
     */
    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    /**
     * @param montoSolicitado the montoSolicitado to set
     */
    public void setMontoSolicitado(BigDecimal montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    /**
     * @return the tipoCredito
     */
    public String getTipoCredito() {
        return tipoCredito;
    }

    /**
     * @param tipoCredito the tipoCredito to set
     */
    public void setTipoCredito(String tipoCredito) {
        this.tipoCredito = tipoCredito;
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

    /**
     * @return the formaPago
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * @return the plazo
     */
    public Long getPlazo() {
        return plazo;
    }

    /**
     * @param plazo the plazo to set
     */
    public void setPlazo(Long plazo) {
        this.plazo = plazo;
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

    public Date getPrimerVencimiento() {
        return primerVencimiento;
    }

    public void setPrimerVencimiento(Date primerVencimiento) {
        this.primerVencimiento = primerVencimiento;
    }

    /**
     * @return the idAhorroDebito
     */
    public String getIdAhorroDebito() {
        return idAhorroDebito;
    }

    /**
     * @param idAhorroDebito the idAhorroDebito to set
     */
    public void setIdAhorroDebito(String idAhorroDebito) {
        this.idAhorroDebito = idAhorroDebito;
    }

    /**
     * @return the idAhorroDesembolso
     */
    public String getIdAhorroDesembolso() {
        return idAhorroDesembolso;
    }

    /**
     * @param idAhorroDesembolso the idAhorroDesembolso to set
     */
    public void setIdAhorroDesembolso(String idAhorroDesembolso) {
        this.idAhorroDesembolso = idAhorroDesembolso;
    }

    /**
     * @return the fechaAnalisis
     */
    public Date getFechaAnalisis() {
        return fechaAnalisis;
    }

    /**
     * @param fechaAnalisis the fechaAnalisis to set
     */
    public void setFechaAnalisis(Date fechaAnalisis) {
        this.fechaAnalisis = fechaAnalisis;
    }

    /**
     * @return the fechaAprobacion
     */
    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    /**
     * @param fechaAprobacion the fechaAprobacion to set
     */
    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    /**
     * @return the fechaRechazo
     */
    public Date getFechaRechazo() {
        return fechaRechazo;
    }

    /**
     * @param fechaRechazo the fechaRechazo to set
     */
    public void setFechaRechazo(Date fechaRechazo) {
        this.fechaRechazo = fechaRechazo;
    }

    /**
     * @return the idTarjeta
     */
    public Long getIdTarjeta() {
        return idTarjeta;
    }

    /**
     * @param idTarjeta the idTarjeta to set
     */
    public void setIdTarjeta(Long idTarjeta) {
        this.idTarjeta = idTarjeta;
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
     * @return the observacionesDepartamento
     */
    public String getObservacionesDepartamento() {
        return observacionesDepartamento;
    }

    /**
     * @param observacionesDepartamento the observacionesDepartamento to set
     */
    public void setObservacionesDepartamento(String observacionesDepartamento) {
        this.observacionesDepartamento = observacionesDepartamento;
    }

    /**
     * @return the observacionesComite
     */
    public String getObservacionesComite() {
        return observacionesComite;
    }

    /**
     * @param observacionesComite the observacionesComite to set
     */
    public void setObservacionesComite(String observacionesComite) {
        this.observacionesComite = observacionesComite;
    }

    /**
     * @return the observacionesConsejo
     */
    public String getObservacionesConsejo() {
        return observacionesConsejo;
    }

    /**
     * @param observacionesConsejo the observacionesConsejo to set
     */
    public void setObservacionesConsejo(String observacionesConsejo) {
        this.observacionesConsejo = observacionesConsejo;
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
     * @return the montoSolicitadoOriginal
     */
    public BigDecimal getMontoSolicitadoOriginal() {
        return montoSolicitadoOriginal;
    }

    /**
     * @param montoSolicitadoOriginal the montoSolicitadoOriginal to set
     */
    public void setMontoSolicitadoOriginal(BigDecimal montoSolicitadoOriginal) {
        this.montoSolicitadoOriginal = montoSolicitadoOriginal;
    }

    /**
     * @return the horaEstado
     */
    public String getHoraEstado() {
        return horaEstado;
    }

    /**
     * @param horaEstado the horaEstado to set
     */
    public void setHoraEstado(String horaEstado) {
        this.horaEstado = horaEstado;
    }

    /**
     * @return the userNumeroLegajo
     */
    public Long getUserNumeroLegajo() {
        return userNumeroLegajo;
    }

    /**
     * @param userNumeroLegajo the userNumeroLegajo to set
     */
    public void setUserNumeroLegajo(Long userNumeroLegajo) {
        this.userNumeroLegajo = userNumeroLegajo;
    }

    /**
     * @return the observacionFormalizador
     */
    public String getObservacionFormalizador() {
        return observacionFormalizador;
    }

    /**
     * @param observacionFormalizador the observacionFormalizador to set
     */
    public void setObservacionFormalizador(String observacionFormalizador) {
        this.observacionFormalizador = observacionFormalizador;
    }

    

    /**
     * @return the detalleDestino
     */
    public String getDetalleDestino() {
        return detalleDestino;
    }

    /**
     * @param detalleDestino the detalleDestino to set
     */
    public void setDetalleDestino(String detalleDestino) {
        this.detalleDestino = detalleDestino;
    }

    /**
     * @return the beneficiarioCheque
     */
    public String getBeneficiarioCheque() {
        return beneficiarioCheque;
    }

    /**
     * @param beneficiarioCheque the beneficiarioCheque to set
     */
    public void setBeneficiarioCheque(String beneficiarioCheque) {
        this.beneficiarioCheque = beneficiarioCheque;
    }

    /**
     * @return the numeroLegajoAdm
     */
    public Long getNumeroLegajoAdm() {
        return numeroLegajoAdm;
    }

    /**
     * @param numeroLegajoAdm the numeroLegajoAdm to set
     */
    public void setNumeroLegajoAdm(Long numeroLegajoAdm) {
        this.numeroLegajoAdm = numeroLegajoAdm;
    }

    /**
     * @return the tipoEnvioSolicitud
     */
    public String getTipoEnvioSolicitud() {
        return tipoEnvioSolicitud;
    }

    /**
     * @param tipoEnvioSolicitud the tipoEnvioSolicitud to set
     */
    public void setTipoEnvioSolicitud(String tipoEnvioSolicitud) {
        this.tipoEnvioSolicitud = tipoEnvioSolicitud;
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
     * @return the cliente
     */
    public Clientes getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public TipoDestinos getTipoDestino() {
        return tipoDestino;
    }

    public void setTipoDestino(TipoDestinos tipoDestino) {
        this.tipoDestino = tipoDestino;
    }

    public TipoGarantias getTipoGarantia() {
        return tipoGarantia;
    }

    public void setTipoGarantia(TipoGarantias tipoGarantia) {
        this.tipoGarantia = tipoGarantia;
    }

    public TipoPagos getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPagos tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Long getImporteCuota() {
        return importeCuota;
    }

    public void setImporteCuota(Long importeCuota) {
        this.importeCuota = importeCuota;
    }

    public BigDecimal getGastosAdministrativos() {
        return gastosAdministrativos;
    }

    public void setGastosAdministrativos(BigDecimal gastosAdministrativos) {
        this.gastosAdministrativos = gastosAdministrativos;
    }

    public Long getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Long impuestos) {
        this.impuestos = impuestos;
    }

    public Long getComision() {
        return comision;
    }

    public void setComision(Long comision) {
        this.comision = comision;
    }

    public Long getGastosVarios() {
        return gastosVarios;
    }

    public void setGastosVarios(Long gastosVarios) {
        this.gastosVarios = gastosVarios;
    }

    public Long getSeguros() {
        return seguros;
    }

    public void setSeguros(Long seguros) {
        this.seguros = seguros;
    }

    /**
     * @return the funcionario
     */
    public Funcionarios getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionarios funcionario) {
        this.funcionario = funcionario;
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
     * @return the tipoDescuento
     */
    public String getTipoDescuento() {
        return tipoDescuento;
    }

    /**
     * @param tipoDescuento the tipoDescuento to set
     */
    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    /**
     * @return the importeEntregar
     */
    public Long getImporteEntregar() {
        return importeEntregar;
    }

    /**
     * @param importeEntregar the importeEntregar to set
     */
    public void setImporteEntregar(Long importeEntregar) {
        this.importeEntregar = importeEntregar;
    }

    /**
     * @return the vencimientoInteres
     */
    public Long getVencimientoInteres() {
        return vencimientoInteres;
    }

    /**
     * @param vencimientoInteres the vencimientoInteres to set
     */
    public void setVencimientoInteres(Long vencimientoInteres) {
        this.vencimientoInteres = vencimientoInteres;
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
     * @return the codeudor
     */
    public Personas getCodeudor() {
        return codeudor;
    }

    /**
     * @param codeudor the codeudor to set
     */
    public void setCodeudor(Personas codeudor) {
        this.codeudor = codeudor;
    }

    public TipoSolicitudes getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(TipoSolicitudes tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }  

    public EvaluacionSolicitudesCabecera getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(EvaluacionSolicitudesCabecera evaluacion) {
        this.evaluacion = evaluacion;
    }    

    /**
     * @return the desembolsado
     */
    public String getDesembolsado() {
        return desembolsado;
    }

    /**
     * @param desembolsado the desembolsado to set
     */
    public void setDesembolsado(String desembolsado) {
        this.desembolsado = desembolsado;
    }

    /**
     * @return the fechaDesembolso
     */
    public Date getFechaDesembolso() {
        return fechaDesembolso;
    }

    /**
     * @param fechaDesembolso the fechaDesembolso to set
     */
    public void setFechaDesembolso(Date fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
              
}
