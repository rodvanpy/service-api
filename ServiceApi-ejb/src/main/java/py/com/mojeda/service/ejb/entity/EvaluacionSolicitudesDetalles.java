/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Personas persona;
    
    @Column(name = "ID_CONYUGE")
    private Long idConyuge;
    
    @Column(name = "NOMBRE_CONYUGE")
    private String nombreConyuge;
       
    @Column(name = "MONTO_DEUDA")
    private Long montoDeuda;
    
    @Column(name = "MONTO_DEUDA_CUOTAS")
    private Long montoDeudaCuotas;
    
    @Column(name = "MONTO_DEUDA_TARJETA")
    private Long montoDeudaTarjeta;

    @Column(name = "MONTO_DEUDA_TARJETA_MINIMO")
    private Long montoDeudaTarjetaMinimo;
    
    @Column(name = "MONTO_DEUDA_CONY")
    private Long montoDeudaConyuge;
    
    @Column(name = "MONTO_DEUDA_CUOTAS_CONY")
    private Long montoDeudaCuotasConyuge;
    
    @Column(name = "MONTO_DEUDA_TARJETA_CONY")
    private Long montoDeudaTarjetaConyuge;

    @Column(name = "MONTO_DEUDA_TARJETA_MINIMO_CONY")
    private Long montoDeudaTarjetaMinimoConyuge;
    
    @Column(name = "MONTO_DEUDA_TOTAL")
    private Long montoDeudaTotal;
    
    @Column(name = "MONTO_DEUDA_TOTAL_CUOTA")
    private Long montoDeudaTotalCuota;
    
    @Column(name = "MONTO_DEUDA_DESCUENTO_SOL")
    private Long montoDeudaDescuento;
    
    @Column(name = "MONTO_DEUDA_DESCUENTO_CUOTAS_SOL")
    private Long montoDeudaDescuentoCuotas;
    
    @Column(name = "MONTO_DEUDA_DESCUENTO_SOL_TOTAL")
    private Long montoDeudaDescuentoTotal;
    
    @Column(name = "MONTO_DEUDA_SOL")
    private Long montoDeudaSolicitud;
    
    @Column(name = "MONTO_DEUDA_SOL_CUOTAS")
    private Long montoDeudaSolicitudCuotas;
    
    @Column(name = "MONTO_SOLI_TOTAL")
    private Long montoDeudaSolicitudTotal;
    
    @Column(name = "MONTO_SOLI_CUOTAS_TOTAL")
    private Long montoDeudaSolicitudCuotaTotal;   

    @Column(name = "SALDO_TOTAL_SOLICITUD")
    private Long saldoTotalSolicitud;    

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
    
    @Column(name = "MONTO_TOTAL_ING")
    private Long montoTotalIngresos;
    
    @Column(name = "MONTO_TOTAL_ING_PORCENTAJE")
    private Long montoTotalIngresosPorcentaje;

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
    
    @Column(name = "DATOS_RELEVANTES")
    private String datosRelevantes;

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
    
    @JsonIgnore
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

    public Long getIdConyuge() {
        return idConyuge;
    }

    public void setIdConyuge(Long idConyuge) {
        this.idConyuge = idConyuge;
    }
          

    public String getNombreConyuge() {
        return nombreConyuge;
    }

    public void setNombreConyuge(String nombreConyuge) {
        this.nombreConyuge = nombreConyuge;
    }    

    /**
     * @return the montoDeuda
     */
    public Long getMontoDeuda() {
        return montoDeuda;
    }

    /**
     * @param montoDeuda the montoDeuda to set
     */
    public void setMontoDeuda(Long montoDeuda) {
        this.montoDeuda = montoDeuda;
    }

    /**
     * @return the montoDeudaCuotas
     */
    public Long getMontoDeudaCuotas() {
        return montoDeudaCuotas;
    }

    /**
     * @param montoDeudaCuotas the montoDeudaCuotas to set
     */
    public void setMontoDeudaCuotas(Long montoDeudaCuotas) {
        this.montoDeudaCuotas = montoDeudaCuotas;
    }

    /**
     * @return the montoDeudaTarjeta
     */
    public Long getMontoDeudaTarjeta() {
        return montoDeudaTarjeta;
    }

    /**
     * @param montoDeudaTarjeta the montoDeudaTarjeta to set
     */
    public void setMontoDeudaTarjeta(Long montoDeudaTarjeta) {
        this.montoDeudaTarjeta = montoDeudaTarjeta;
    }

    /**
     * @return the montoDeudaTarjetaMinimo
     */
    public Long getMontoDeudaTarjetaMinimo() {
        return montoDeudaTarjetaMinimo;
    }

    /**
     * @param montoDeudaTarjetaMinimo the montoDeudaTarjetaMinimo to set
     */
    public void setMontoDeudaTarjetaMinimo(Long montoDeudaTarjetaMinimo) {
        this.montoDeudaTarjetaMinimo = montoDeudaTarjetaMinimo;
    }

    /**
     * @return the montoDeudaConyuge
     */
    public Long getMontoDeudaConyuge() {
        return montoDeudaConyuge;
    }

    /**
     * @param montoDeudaConyuge the montoDeudaConyuge to set
     */
    public void setMontoDeudaConyuge(Long montoDeudaConyuge) {
        this.montoDeudaConyuge = montoDeudaConyuge;
    }

    /**
     * @return the montoDeudaCuotasConyuge
     */
    public Long getMontoDeudaCuotasConyuge() {
        return montoDeudaCuotasConyuge;
    }

    /**
     * @param montoDeudaCuotasConyuge the montoDeudaCuotasConyuge to set
     */
    public void setMontoDeudaCuotasConyuge(Long montoDeudaCuotasConyuge) {
        this.montoDeudaCuotasConyuge = montoDeudaCuotasConyuge;
    }

    /**
     * @return the montoDeudaTarjetaConyuge
     */
    public Long getMontoDeudaTarjetaConyuge() {
        return montoDeudaTarjetaConyuge;
    }

    /**
     * @param montoDeudaTarjetaConyuge the montoDeudaTarjetaConyuge to set
     */
    public void setMontoDeudaTarjetaConyuge(Long montoDeudaTarjetaConyuge) {
        this.montoDeudaTarjetaConyuge = montoDeudaTarjetaConyuge;
    }

    /**
     * @return the montoDeudaTarjetaMinimoConyuge
     */
    public Long getMontoDeudaTarjetaMinimoConyuge() {
        return montoDeudaTarjetaMinimoConyuge;
    }

    /**
     * @param montoDeudaTarjetaMinimoConyuge the montoDeudaTarjetaMinimoConyuge to set
     */
    public void setMontoDeudaTarjetaMinimoConyuge(Long montoDeudaTarjetaMinimoConyuge) {
        this.montoDeudaTarjetaMinimoConyuge = montoDeudaTarjetaMinimoConyuge;
    }

    /**
     * @return the montoDeudaTotal
     */
    public Long getMontoDeudaTotal() {
        return montoDeudaTotal;
    }

    /**
     * @param montoDeudaTotal the montoDeudaTotal to set
     */
    public void setMontoDeudaTotal(Long montoDeudaTotal) {
        this.montoDeudaTotal = montoDeudaTotal;
    }

    /**
     * @return the montoDeudaTotalCuota
     */
    public Long getMontoDeudaTotalCuota() {
        return montoDeudaTotalCuota;
    }

    /**
     * @param montoDeudaTotalCuota the montoDeudaTotalCuota to set
     */
    public void setMontoDeudaTotalCuota(Long montoDeudaTotalCuota) {
        this.montoDeudaTotalCuota = montoDeudaTotalCuota;
    }

    /**
     * @return the montoDeudaDescuento
     */
    public Long getMontoDeudaDescuento() {
        return montoDeudaDescuento;
    }

    /**
     * @param montoDeudaDescuento the montoDeudaDescuento to set
     */
    public void setMontoDeudaDescuento(Long montoDeudaDescuento) {
        this.montoDeudaDescuento = montoDeudaDescuento;
    }

    /**
     * @return the montoDeudaDescuentoCuotas
     */
    public Long getMontoDeudaDescuentoCuotas() {
        return montoDeudaDescuentoCuotas;
    }

    /**
     * @param montoDeudaDescuentoCuotas the montoDeudaDescuentoCuotas to set
     */
    public void setMontoDeudaDescuentoCuotas(Long montoDeudaDescuentoCuotas) {
        this.montoDeudaDescuentoCuotas = montoDeudaDescuentoCuotas;
    }

    /**
     * @return the montoDeudaDescuentoTotal
     */
    public Long getMontoDeudaDescuentoTotal() {
        return montoDeudaDescuentoTotal;
    }

    /**
     * @param montoDeudaDescuentoTotal the montoDeudaDescuentoTotal to set
     */
    public void setMontoDeudaDescuentoTotal(Long montoDeudaDescuentoTotal) {
        this.montoDeudaDescuentoTotal = montoDeudaDescuentoTotal;
    }

    /**
     * @return the montoDeudaSolicitud
     */
    public Long getMontoDeudaSolicitud() {
        return montoDeudaSolicitud;
    }

    /**
     * @param montoDeudaSolicitud the montoDeudaSolicitud to set
     */
    public void setMontoDeudaSolicitud(Long montoDeudaSolicitud) {
        this.montoDeudaSolicitud = montoDeudaSolicitud;
    }

    /**
     * @return the montoDeudaSolicitudCuotas
     */
    public Long getMontoDeudaSolicitudCuotas() {
        return montoDeudaSolicitudCuotas;
    }

    /**
     * @param montoDeudaSolicitudCuotas the montoDeudaSolicitudCuotas to set
     */
    public void setMontoDeudaSolicitudCuotas(Long montoDeudaSolicitudCuotas) {
        this.montoDeudaSolicitudCuotas = montoDeudaSolicitudCuotas;
    }

    /**
     * @return the montoDeudaSolicitudTotal
     */
    public Long getMontoDeudaSolicitudTotal() {
        return montoDeudaSolicitudTotal;
    }

    /**
     * @param montoDeudaSolicitudTotal the montoDeudaSolicitudTotal to set
     */
    public void setMontoDeudaSolicitudTotal(Long montoDeudaSolicitudTotal) {
        this.montoDeudaSolicitudTotal = montoDeudaSolicitudTotal;
    }

    /**
     * @return the montoDeudaSolicitudCuotaTotal
     */
    public Long getMontoDeudaSolicitudCuotaTotal() {
        return montoDeudaSolicitudCuotaTotal;
    }

    /**
     * @param montoDeudaSolicitudCuotaTotal the montoDeudaSolicitudCuotaTotal to set
     */
    public void setMontoDeudaSolicitudCuotaTotal(Long montoDeudaSolicitudCuotaTotal) {
        this.montoDeudaSolicitudCuotaTotal = montoDeudaSolicitudCuotaTotal;
    }

    /**
     * @return the saldoTotalSolicitud
     */
    public Long getSaldoTotalSolicitud() {
        return saldoTotalSolicitud;
    }

    /**
     * @param saldoTotalSolicitud the saldoTotalSolicitud to set
     */
    public void setSaldoTotalSolicitud(Long saldoTotalSolicitud) {
        this.saldoTotalSolicitud = saldoTotalSolicitud;
    }

    /**
     * @return the datosRelevantes
     */
    public String getDatosRelevantes() {
        return datosRelevantes;
    }

    /**
     * @param datosRelevantes the datosRelevantes to set
     */
    public void setDatosRelevantes(String datosRelevantes) {
        this.datosRelevantes = datosRelevantes;
    }

    /**
     * @return the montoTotalIngresos
     */
    public Long getMontoTotalIngresos() {
        return montoTotalIngresos;
    }

    /**
     * @param montoTotalIngresos the montoTotalIngresos to set
     */
    public void setMontoTotalIngresos(Long montoTotalIngresos) {
        this.montoTotalIngresos = montoTotalIngresos;
    }

    /**
     * @return the montoTotalIngresosPorcentaje
     */
    public Long getMontoTotalIngresosPorcentaje() {
        return montoTotalIngresosPorcentaje;
    }

    /**
     * @param montoTotalIngresosPorcentaje the montoTotalIngresosPorcentaje to set
     */
    public void setMontoTotalIngresosPorcentaje(Long montoTotalIngresosPorcentaje) {
        this.montoTotalIngresosPorcentaje = montoTotalIngresosPorcentaje;
    }
    
    
}
