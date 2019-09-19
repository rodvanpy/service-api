/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.EvaluacionSolicitudesDetalles;
import py.com.mojeda.service.ejb.manager.EvaluacionSolicitudesDetallesManager;



/**
 *
 * @author mojeda
 */
@Stateless
public class EvaluacionSolicitudesDetallesManagerImpl extends GenericDaoImpl<EvaluacionSolicitudesDetalles, Long>
        implements EvaluacionSolicitudesDetallesManager {

    @Override
    protected Class<EvaluacionSolicitudesDetalles> getEntityBeanType() {
        return EvaluacionSolicitudesDetalles.class;
    }

//    @Override
//    public List<EvaluacionSolicitudesDetalles> getListEvaluacionDetalles(EvaluacionSolicitudesDetalles evaluacion) throws Exception {
//        List<EvaluacionSolicitudesDetalles> evaluacionSolicitudesDetalles = null;
//
//        String atributos = "id,fechaInicioAnalisis,fechaFinAnalisis,fechaPrimeraAprobRech,fechaSegundaAprobRech,funcionarioAprobacion.id,estado.id,"
//                + "montoAprobado,funcionarioAnalisis.id,funcionarioVerificador.id,obsApro,propuestaSolicitud.id,fechaCreacion,"
//                + "idUsuarioCreacion,fechaModificacion,idUsuarioModificacion,activo";
//
//        List<Map<String, Object>> evaluacionList = this.listAtributos(evaluacion, "id".split(","));
//        
//    }
//
//    @Override
//    public EvaluacionSolicitudesDetalles getEvaluacionDetalle(EvaluacionSolicitudesDetalles evaluacion) throws Exception {
//        EvaluacionSolicitudesDetalles evaluacionSolicitudesDetalles = null;
//
//        String atributos = "id,fechaInicioAnalisis,fechaFinAnalisis,fechaPrimeraAprobRech,fechaSegundaAprobRech,funcionarioAprobacion.id,estado.id,"
//                + "montoAprobado,funcionarioAnalisis.id,funcionarioVerificador.id,obsApro,propuestaSolicitud.id,fechaCreacion,"
//                + "idUsuarioCreacion,fechaModificacion,idUsuarioModificacion,activo";
//
//        Map<String, Object> evaluacionMaps = this.getAtributos(evaluacion, atributos.split(","));
//        
//        evaluacionSolicitudesDetalles = new EvaluacionSolicitudesDetalles();
//        evaluacionSolicitudesDetalles.setActivo(atributos);
//        evaluacionSolicitudesDetalles.setAntiguedadLaboral(BigDecimal.ZERO);
//        evaluacionSolicitudesDetalles.setCalificacionCredCanc(atributos);
//        evaluacionSolicitudesDetalles.setCalificacionCreditosActual(atributos);
//        evaluacionSolicitudesDetalles.setCantidadHijos(Short.MIN_VALUE);
//        evaluacionSolicitudesDetalles.setCapacidadPago(Short.MIN_VALUE);
//        evaluacionSolicitudesDetalles.setCapitalNoMensual(Long.MAX_VALUE);
//        evaluacionSolicitudesDetalles.setConcluido(atributos);
//        evaluacionSolicitudesDetalles.setDescripcionProfesion(atributos);
//        evaluacionSolicitudesDetalles.setEdad(Integer.SIZE);
//        evaluacionSolicitudesDetalles.setEgresosTotal(Long.MIN_VALUE);
//        evaluacionSolicitudesDetalles.setEstado(atributos);
//        evaluacionSolicitudesDetalles.setEstadoCivil(atributos);
//        evaluacionSolicitudesDetalles.setFajaInformconf(atributos);
//        evaluacionSolicitudesDetalles.setFechaReunion(fechaReunion);
//        evaluacionSolicitudesDetalles.setGarantiasVigente(atributos);
//        evaluacionSolicitudesDetalles.setGeneraTarjeta(atributos);
//        evaluacionSolicitudesDetalles.setId(Long.MIN_VALUE);
//        evaluacionSolicitudesDetalles.setIdConyuge(Long.MIN_VALUE);
//        evaluacionSolicitudesDetalles.setIdReunion(Integer.SIZE);
//        evaluacionSolicitudesDetalles.setInformconf(atributos);
//        evaluacionSolicitudesDetalles.setIngresoTotal(Long.MIN_VALUE);
//        evaluacionSolicitudesDetalles.setIngresosOtros(Long.MIN_VALUE);
//        evaluacionSolicitudesDetalles.setLimiteTarjeta(BigInteger.ZERO);
//    }
}
