/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.EstadosSolicitud;
import py.com.mojeda.service.ejb.entity.EvaluacionSolicitudesCabecera;
import py.com.mojeda.service.ejb.entity.EvaluacionSolicitudesDetalles;
import py.com.mojeda.service.ejb.entity.Funcionarios;
import py.com.mojeda.service.ejb.entity.PropuestaSolicitud;
import py.com.mojeda.service.ejb.manager.EstadosSolicitudManager;
import py.com.mojeda.service.ejb.manager.EvaluacionSolicitudesCabeceraManager;
import py.com.mojeda.service.ejb.manager.EvaluacionSolicitudesDetallesManager;
import py.com.mojeda.service.ejb.manager.FuncionariosManager;
import py.com.mojeda.service.ejb.manager.PropuestaSolicitudManager;

/**
 *
 * @author mojeda
 */
@Stateless
public class EvaluacionSolicitudesCabeceraManagerImpl extends GenericDaoImpl<EvaluacionSolicitudesCabecera, Long>
        implements EvaluacionSolicitudesCabeceraManager {

    @Override
    protected Class<EvaluacionSolicitudesCabecera> getEntityBeanType() {
        return EvaluacionSolicitudesCabecera.class;
    }
    protected static final DateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    protected static final DateFormat dateFormatHHMM = new SimpleDateFormat("HH:mm");
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/FuncionariosManagerImpl")
    private FuncionariosManager funcionariosManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/PropuestaSolicitudManagerImpl")
    private PropuestaSolicitudManager propuestaSolicitudManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/EstadosSolicitudManagerImpl")
    private EstadosSolicitudManager estadosSolicitudManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/EvaluacionSolicitudesDetallesManagerImpl")
    private EvaluacionSolicitudesDetallesManager evaluacionSolicitudesDetallesManager;

    @Override
    public EvaluacionSolicitudesCabecera evaluar(Long idFuncionario, Long idEvaluacion) throws Exception {
        EvaluacionSolicitudesCabecera evaluacionSolicitudesCabecera = null;

        if (idFuncionario == null
                || idEvaluacion == null) {
            return null;
        }

        evaluacionSolicitudesCabecera = this.get(new EvaluacionSolicitudesCabecera(idEvaluacion));

        if (evaluacionSolicitudesCabecera != null
                && evaluacionSolicitudesCabecera.getFuncionarioAnalisis() == null) {

            evaluacionSolicitudesCabecera.setEstado(new EstadosSolicitud(2L));
            evaluacionSolicitudesCabecera.setFechaInicioAnalisis(new Date(System.currentTimeMillis()));
            evaluacionSolicitudesCabecera.setFuncionarioAnalisis(new Funcionarios(idFuncionario));
            evaluacionSolicitudesCabecera.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            evaluacionSolicitudesCabecera.setIdUsuarioModificacion(idFuncionario);

            this.update(evaluacionSolicitudesCabecera);
        }
        evaluacionSolicitudesCabecera = new EvaluacionSolicitudesCabecera();
        evaluacionSolicitudesCabecera = this.getEvaluacion(new EvaluacionSolicitudesCabecera(idEvaluacion));

        EvaluacionSolicitudesDetalles ejDetalle = new EvaluacionSolicitudesDetalles();
        ejDetalle.setEvaluacionSolicitudCabecera(new EvaluacionSolicitudesCabecera(evaluacionSolicitudesCabecera.getId()));

        evaluacionSolicitudesCabecera.setDetalles(evaluacionSolicitudesDetallesManager.list(ejDetalle));

        return evaluacionSolicitudesCabecera;

    }

    @Override
    public EvaluacionSolicitudesCabecera getEvaluacion(EvaluacionSolicitudesCabecera evaluacion) throws Exception {
        EvaluacionSolicitudesCabecera evaluacionSolicitudesCabecera = null;

        String atributos = "id,fechaInicioAnalisis,fechaFinAnalisis,fechaPrimeraAprobRech,fechaSegundaAprobRech,funcionarioAprobacion.id,estado.id,"
                + "montoAprobado,funcionarioAnalisis.id,funcionarioVerificador.id,obsApro,observacion,observacionRecomendacion,propuestaSolicitud.id,fechaCreacion,"
                + "idUsuarioCreacion,fechaModificacion,idUsuarioModificacion,activo";

        Map<String, Object> evaluacionCabecera = this.getAtributos(evaluacion, atributos.split(","));

        evaluacionSolicitudesCabecera = new EvaluacionSolicitudesCabecera();
        evaluacionSolicitudesCabecera.setActivo(evaluacionCabecera.get("activo") == null ? "" : evaluacionCabecera.get("activo").toString());
        evaluacionSolicitudesCabecera.setEstado(evaluacionCabecera.get("estado.id") == null ? null : estadosSolicitudManager.get(Long.parseLong(evaluacionCabecera.get("estado.id").toString())));
        evaluacionSolicitudesCabecera.setFechaFinAnalisis(evaluacionCabecera.get("fechaFinAnalisis") == null ? null : date.parse(evaluacionCabecera.get("fechaFinAnalisis").toString()));
        evaluacionSolicitudesCabecera.setFechaInicioAnalisis(evaluacionCabecera.get("fechaInicioAnalisis") == null ? null : date.parse(evaluacionCabecera.get("fechaInicioAnalisis").toString()));
        evaluacionSolicitudesCabecera.setFechaPrimeraAprobRech(evaluacionCabecera.get("fechaPrimeraAprobRech") == null ? null : date.parse(evaluacionCabecera.get("fechaPrimeraAprobRech").toString()));
        evaluacionSolicitudesCabecera.setFechaSegundaAprobRech(evaluacionCabecera.get("fechaSegundaAprobRech") == null ? null : date.parse(evaluacionCabecera.get("fechaSegundaAprobRech").toString()));
        evaluacionSolicitudesCabecera.setFuncionarioAnalisis(evaluacionCabecera.get("funcionarioAnalisis.id") == null ? null : funcionariosManager.getUsuario(new Funcionarios(Long.parseLong(evaluacionCabecera.get("funcionarioAnalisis.id").toString())), null));
        evaluacionSolicitudesCabecera.setFuncionarioAprobacion(evaluacionCabecera.get("funcionarioAprobacion.id") == null ? null : funcionariosManager.getUsuario(new Funcionarios(Long.parseLong(evaluacionCabecera.get("funcionarioAprobacion.id").toString())), null));
        evaluacionSolicitudesCabecera.setFuncionarioVerificador(evaluacionCabecera.get("funcionarioVerificador.id") == null ? null : funcionariosManager.getUsuario(new Funcionarios(Long.parseLong(evaluacionCabecera.get("funcionarioVerificador.id").toString())), null));
        evaluacionSolicitudesCabecera.setId(evaluacionCabecera.get("id") == null ? null : Long.parseLong(evaluacionCabecera.get("id").toString()));
        evaluacionSolicitudesCabecera.setMontoAprobado(evaluacionCabecera.get("montoAprobado") == null ? null : Long.parseLong(evaluacionCabecera.get("montoAprobado").toString()));
        evaluacionSolicitudesCabecera.setObservacion(evaluacionCabecera.get("observacion") == null ? "" : evaluacionCabecera.get("observacion").toString());
        evaluacionSolicitudesCabecera.setObservacionRecomendacion(evaluacionCabecera.get("observacionRecomendacion") == null ? "" : evaluacionCabecera.get("observacionRecomendacion").toString());
        evaluacionSolicitudesCabecera.setObsApro(evaluacionCabecera.get("obsApro") == null ? "" : evaluacionCabecera.get("obsApro").toString());
        evaluacionSolicitudesCabecera.setPropuestaSolicitud(evaluacionCabecera.get("propuestaSolicitud.id") == null ? null : propuestaSolicitudManager.getPropuestaSolicitud(new PropuestaSolicitud(Long.parseLong(evaluacionCabecera.get("propuestaSolicitud.id").toString()))));
        evaluacionSolicitudesCabecera.setFechaCreacion(evaluacionCabecera.get("fechaCreacion") == null ? null : new Timestamp(date.parse(evaluacionCabecera.get("fechaCreacion").toString()).getTime()));
        evaluacionSolicitudesCabecera.setFechaModificacion(evaluacionCabecera.get("fechaModificacion") == null ? null : new Timestamp(date.parse(evaluacionCabecera.get("fechaModificacion").toString()).getTime()));
        evaluacionSolicitudesCabecera.setIdUsuarioCreacion(evaluacionCabecera.get("idUsuarioCreacion") == null ? null : Long.parseLong(evaluacionCabecera.get("idUsuarioCreacion").toString()));
        evaluacionSolicitudesCabecera.setIdUsuarioModificacion(evaluacionCabecera.get("idUsuarioModificacion") == null ? null : Long.parseLong(evaluacionCabecera.get("idUsuarioModificacion").toString()));

        EvaluacionSolicitudesDetalles ejDetalle = new EvaluacionSolicitudesDetalles();
        ejDetalle.setEvaluacionSolicitudCabecera(new EvaluacionSolicitudesCabecera(evaluacionSolicitudesCabecera.getId()));

        evaluacionSolicitudesCabecera.setDetalles(evaluacionSolicitudesDetallesManager.list(ejDetalle));

        return evaluacionSolicitudesCabecera;
    }

    @Override
    public EvaluacionSolicitudesCabecera guardar(EvaluacionSolicitudesCabecera evaluacionSolicitudes, Long idFuncionario) throws Exception {
        EvaluacionSolicitudesCabecera evaluacionSolicitudesCabecera = null;

        if (idFuncionario == null
                || evaluacionSolicitudes == null
                || evaluacionSolicitudes.getId() == null) {
            return null;
        }
        
        evaluacionSolicitudesCabecera = this.get(evaluacionSolicitudes.getId());
        
        evaluacionSolicitudesCabecera.setEstado(evaluacionSolicitudes.getEstado());
        
        if(evaluacionSolicitudes.getEstado().getId() != 1
                || evaluacionSolicitudes.getEstado().getId() != 2){
            //Actualizar estado de la propuesta
            PropuestaSolicitud propuesta = propuestaSolicitudManager.get(evaluacionSolicitudesCabecera.getPropuestaSolicitud().getId());
            
            propuesta.setEstado(evaluacionSolicitudes.getEstado());
            propuesta.setFechaEstado(new Date(System.currentTimeMillis()));
            propuesta.setHoraEstado(dateFormatHHMM.format(new Date(System.currentTimeMillis())));
            propuesta.setIdUsuarioModificacion(idFuncionario);
            propuesta.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            
            if(evaluacionSolicitudes.getEstado().getId() == 4){
                propuesta.setFechaRechazo(new Date(System.currentTimeMillis()));
            }
            
            if(evaluacionSolicitudes.getEstado().getId() == 6){
                evaluacionSolicitudesCabecera.setFuncionarioAprobacion(new Funcionarios(idFuncionario));
                evaluacionSolicitudesCabecera.setObsApro(evaluacionSolicitudes.getObsApro());
                evaluacionSolicitudesCabecera.setMontoAprobado(evaluacionSolicitudes.getMontoAprobado());
                
                propuesta.setFechaAprobacion(new Date(System.currentTimeMillis()));
            }
            
            propuestaSolicitudManager.update(propuesta);
            
            evaluacionSolicitudesCabecera.setFechaFinAnalisis(new Timestamp(System.currentTimeMillis()));
               
            if(evaluacionSolicitudes.getEstado().getId() == 5){
                evaluacionSolicitudesCabecera.setObservacionRetransferencia(evaluacionSolicitudes.getObservacionRetransferencia());
            }
            
            if(evaluacionSolicitudesCabecera.getFechaPrimeraAprobRech() == null){
                evaluacionSolicitudesCabecera.setFechaPrimeraAprobRech(new Timestamp(System.currentTimeMillis()));
            }else{
                evaluacionSolicitudesCabecera.setFechaSegundaAprobRech(new Timestamp(System.currentTimeMillis()));
            }
        }        
        
        evaluacionSolicitudesCabecera.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
        evaluacionSolicitudesCabecera.setObservacion(evaluacionSolicitudes.getObservacion());
        evaluacionSolicitudesCabecera.setObservacionRecomendacion(evaluacionSolicitudes.getObservacionRecomendacion());
        
        this.update(evaluacionSolicitudesCabecera);
        
        EvaluacionSolicitudesDetalles detalles;
        for(EvaluacionSolicitudesDetalles rpc: evaluacionSolicitudes.getDetalles()){
            
            detalles = evaluacionSolicitudesDetallesManager.get(rpc.getId());
            
            detalles.setAntiguedadLaboral(BigDecimal.ZERO);
            detalles.setCalificacionCredCanc(rpc.getCalificacionCredCanc());
            detalles.setCalificacionCreditosActual(rpc.getCalificacionCreditosActual());
            detalles.setCantidadHijos(rpc.getCantidadHijos());
            detalles.setCapacidadPago(rpc.getCapacidadPago());
            detalles.setCapitalNoMensual(rpc.getCapitalNoMensual());
            detalles.setConcluido(rpc.getConcluido());
            detalles.setDatosRelevantes(rpc.getDatosRelevantes());
            detalles.setDescripcionProfesion(rpc.getDescripcionProfesion());
            detalles.setDeudaCuotaCtralRgo(rpc.getDeudaCuotaCtralRgo());
            detalles.setDeudaCuotaReferencia(rpc.getDeudaCuotaReferencia());
            detalles.setDeudaTotalCtralRgo(rpc.getDeudaTotalCtralRgo());
            detalles.setDeudaTotalReferencia(rpc.getDeudaTotalReferencia());
            detalles.setEdad(rpc.getEdad());
            detalles.setEgresosTotal(rpc.getEgresosTotal());
            detalles.setEstadoCivil(rpc.getEstadoCivil());
            detalles.setFajaInformconf(rpc.getFajaInformconf());
            detalles.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            detalles.setIdUsuarioModificacion(idFuncionario);
            detalles.setInformconf(rpc.getInformconf());
            detalles.setIngresoTotal(rpc.getIngresoTotal());
            detalles.setIngresosOtros(rpc.getIngresosOtros());
            detalles.setLimiteTarjeta(rpc.getLimiteTarjeta());
            detalles.setMontoCapacidadPago(rpc.getMontoCapacidadPago());
            detalles.setMontoDeuda(rpc.getMontoDeuda());
            detalles.setMontoDeudaConyuge(rpc.getMontoDeudaConyuge());
            detalles.setMontoDeudaCuotas(rpc.getMontoDeudaCuotas());
            detalles.setMontoDeudaCuotasConyuge(rpc.getMontoDeudaConyuge());
            detalles.setMontoDeudaDescuento(rpc.getMontoDeudaDescuento());
            detalles.setMontoDeudaDescuentoCuotas(rpc.getMontoDeudaDescuentoCuotas());
            detalles.setMontoDeudaDescuentoTotal(rpc.getMontoDeudaDescuentoTotal());
            detalles.setMontoDeudaSolicitud(rpc.getMontoDeudaSolicitud());
            detalles.setMontoDeudaSolicitudCuotaTotal(rpc.getMontoDeudaSolicitudCuotaTotal());
            detalles.setMontoDeudaSolicitudCuotas(rpc.getMontoDeudaSolicitudCuotas());
            detalles.setMontoDeudaSolicitudTotal(rpc.getMontoDeudaSolicitudTotal());
            detalles.setMontoDeudaTarjeta(rpc.getMontoDeudaTarjeta());
            detalles.setMontoDeudaTarjetaConyuge(rpc.getMontoDeudaTarjetaConyuge());
            detalles.setMontoDeudaTarjetaMinimo(rpc.getMontoDeudaTarjetaMinimo());
            detalles.setMontoDeudaTarjetaMinimoConyuge(rpc.getMontoDeudaTarjetaMinimoConyuge());
            detalles.setMontoDeudaTotal(rpc.getMontoDeudaTotal());
            detalles.setMontoDeudaTotalCuota(rpc.getMontoDeudaTotalCuota());
            detalles.setMontoTotalIngresos(rpc.getMontoTotalIngresos());
            detalles.setMontoTotalIngresosPorcentaje(rpc.getMontoTotalIngresosPorcentaje());
            detalles.setPorcentajeCapacidad(rpc.getPorcentajeCapacidad());
            detalles.setPorcentajeCapacidadOtros(rpc.getPorcentajeCapacidadOtros());
            detalles.setSaldoTotalGarantia(rpc.getSaldoTotalGarantia());
            detalles.setGarantiasVigente(rpc.getGarantiasVigente());
            detalles.setCreditoVigente(rpc.getCreditoVigente());
            detalles.setSaldoTotalSolicitud(rpc.getSaldoTotalSolicitud());
            detalles.setTotalDeudaCuotaExterior(rpc.getTotalDeudaCuotaExterior());
            detalles.setTotalDeudaExterior(rpc.getTotalDeudaExterior());
            detalles.setTotalDiferenciaIngEgr(rpc.getTotalDiferenciaIngEgr());
            
            evaluacionSolicitudesDetallesManager.update(detalles);
        }
        
        EvaluacionSolicitudesDetalles ejDetalle = new EvaluacionSolicitudesDetalles();
        ejDetalle.setEvaluacionSolicitudCabecera(new EvaluacionSolicitudesCabecera(evaluacionSolicitudesCabecera.getId()));

        evaluacionSolicitudesCabecera.setDetalles(evaluacionSolicitudesDetallesManager.list(ejDetalle));

        return evaluacionSolicitudesCabecera;

    }
}
