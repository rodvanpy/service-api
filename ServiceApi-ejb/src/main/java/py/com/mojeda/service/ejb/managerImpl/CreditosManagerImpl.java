/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Creditos;
import py.com.mojeda.service.ejb.entity.EstadosCredito;
import py.com.mojeda.service.ejb.entity.EvaluacionSolicitudesCabecera;
import py.com.mojeda.service.ejb.entity.Modalidades;
import py.com.mojeda.service.ejb.entity.Monedas;
import py.com.mojeda.service.ejb.entity.PropuestaSolicitud;
import py.com.mojeda.service.ejb.entity.SituacionesCredito;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.TipoCalculos;
import py.com.mojeda.service.ejb.entity.TipoDesembolsos;
import py.com.mojeda.service.ejb.entity.TipoDestinos;
import py.com.mojeda.service.ejb.manager.CreditosManager;
import py.com.mojeda.service.ejb.manager.EvaluacionSolicitudesCabeceraManager;
import py.com.mojeda.service.ejb.manager.PropuestaSolicitudManager;

/**
 *
 * @author Miguel
 */
@Stateless
public class CreditosManagerImpl extends GenericDaoImpl<Creditos, Long>
        implements CreditosManager {

    @Override
    protected Class<Creditos> getEntityBeanType() {
        return Creditos.class;
    }

    @EJB(mappedName = "java:app/ServiceApi-ejb/PropuestaSolicitudManagerImpl")
    private PropuestaSolicitudManager propuestaSolicitudManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/EvaluacionSolicitudesCabeceraManagerImpl")
    private EvaluacionSolicitudesCabeceraManager evaluacionSolicitudesCabeceraManager;

    @Override
    public void generarCredito(Long idSolicitud, Long idEvaluacionCabecera, Long idPersona) throws Exception {
        
        String atributos = "id,montoSolicitado,tipoCredito,tipoDescuento,tasaInteres,"
                + "tipoDesembolso.id,tipoCalculoImporte.id,tipoGarantia.id,gastosAdministrativos,vencimientoInteres,"
                + "tipoPago.id,tipoDestino.id,tipoInteres,ordenCheque,formaPago,plazo,periodoGracia,periodoCapital,periodoInteres,plazoOperacion,idAhorroDebito,"
                + "idAhorroDesembolso,montoSolicitadoOriginal,"
                + "importeCuota,importeEntregar,beneficiarioCheque,tipoEnvioSolicitud,moneda.id,modalidad.id,"
                + "cliente.id,cliente.persona.id,codeudor.id,funcionario.id,sucursal.id,tipoSolicitud.id,activo";  
        
        Map<String, Object> modelMaps = propuestaSolicitudManager.getAtributos(new PropuestaSolicitud(idSolicitud), atributos.split(","));
        //Cargar credito
        Creditos credito = new Creditos();
        credito.setEstado(new EstadosCredito("PEN"));
        credito.setFechaEstado(new Date(System.currentTimeMillis()));
        credito.setFechaGeneracion(new Date(System.currentTimeMillis()));
        credito.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
        credito.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
        credito.setActivo("S");
        credito.setIdUsuarioCreacion(idPersona);
        credito.setIdUsuarioModificacion(idPersona);
        credito.setModalidad(new Modalidades(Long.parseLong(modelMaps.get("modalidad.id").toString())));
        credito.setMoneda(modelMaps.get("moneda.id") == null ? null : new Monedas(Long.parseLong(modelMaps.get("moneda.id").toString())));
        credito.setMontoCapital(modelMaps.get("montoSolicitado") == null ? null : new BigDecimal(modelMaps.get("montoSolicitado").toString()));
        credito.setSaldoCapital(modelMaps.get("montoSolicitado") == null ? null : new BigDecimal(modelMaps.get("montoSolicitado").toString()));
        credito.setPeriodoCapital(modelMaps.get("periodoCapital") == null ? null : Short.parseShort(modelMaps.get("periodoCapital").toString()));
        credito.setPeriodoGracia(modelMaps.get("periodoGracia") == null ? null : Short.parseShort(modelMaps.get("periodoGracia").toString()));
        credito.setPeriodoInteres(modelMaps.get("periodoInteres") == null ? null : Short.parseShort(modelMaps.get("periodoInteres").toString()));
        credito.setPlazoOperacion(modelMaps.get("plazo") == null ? null : Short.parseShort(modelMaps.get("plazo").toString()));
        credito.setSucursal(new Sucursales(Long.parseLong(modelMaps.get("sucursal.id").toString())));
        credito.setTipoCalculoImporte(new TipoCalculos(Long.parseLong(modelMaps.get("tipoCalculoImporte.id").toString())));
        credito.setTipoDesembolso(new TipoDesembolsos(Long.parseLong(modelMaps.get("tipoDesembolso.id").toString())));
        credito.setTipoDestino(new TipoDestinos(Long.parseLong(modelMaps.get("tipoDestino.id").toString())));
        credito.setTipoInteres(modelMaps.get("tipoInteres").toString());
        credito.setTasaInteres(new BigDecimal(modelMaps.get("tasaInteres").toString()));
        credito.setGastosAdministrativos(modelMaps.get("gastosAdministrativos") == null ? null : new BigDecimal(modelMaps.get("gastosAdministrativos").toString()));
        credito.setPropuestaSolicitud(new PropuestaSolicitud(idSolicitud));
        credito.setSituacionCredito(new SituacionesCredito(1L));
        //Calcular el monto del interes         
        Long montoInteres = propuestaSolicitudManager.calcularMontoInteres(new Long(modelMaps.get("plazo").toString()).doubleValue(),
                credito.getPeriodoCapital().longValue(), Long.parseLong(modelMaps.get("vencimientoInteres").toString()),
                credito.getTasaInteres().doubleValue(), credito.getMontoCapital().doubleValue(),
                credito.getTipoCalculoImporte(), credito.getGastosAdministrativos().doubleValue());    
        
        credito.setMontoInteres(new BigDecimal(montoInteres));
        credito.setSaldoInteres(new BigDecimal(montoInteres));
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
