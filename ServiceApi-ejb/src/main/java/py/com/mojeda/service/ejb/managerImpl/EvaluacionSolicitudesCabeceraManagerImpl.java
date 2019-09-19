/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.EstadosAnalisis;
import py.com.mojeda.service.ejb.entity.EvaluacionSolicitudesCabecera;
import py.com.mojeda.service.ejb.entity.EvaluacionSolicitudesDetalles;
import py.com.mojeda.service.ejb.entity.Funcionarios;
import py.com.mojeda.service.ejb.entity.PropuestaSolicitud;
import py.com.mojeda.service.ejb.manager.EstadosAnalisisManager;
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

    @EJB(mappedName = "java:app/ServiceApi-ejb/FuncionariosManagerImpl")
    private FuncionariosManager funcionariosManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/PropuestaSolicitudManagerImpl")
    private PropuestaSolicitudManager propuestaSolicitudManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/EstadosAnalisisManagerImpl")
    private EstadosAnalisisManager estadosAnalisisManager;

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

            evaluacionSolicitudesCabecera.setEstado(new EstadosAnalisis(2L));
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
        evaluacionSolicitudesCabecera.setEstado(evaluacionCabecera.get("estado.id") == null ? null : estadosAnalisisManager.get(Long.parseLong(evaluacionCabecera.get("estado.id").toString())));
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
}
