/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.TipoOcupaciones;
import py.com.mojeda.service.ejb.manager.OcupacionPersonaManager;
import py.com.mojeda.service.ejb.manager.TipoOcupacionesManager;
import static py.com.mojeda.service.ejb.managerImpl.BienesManagerImpl.logger;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;

/**
 *
 * @author Miguel
 */
@Stateless
public class OcupacionPersonaManagerImpl extends GenericDaoImpl<OcupacionPersona, Long>
        implements OcupacionPersonaManager {

    @Override
    protected Class<OcupacionPersona> getEntityBeanType() {
        return OcupacionPersona.class;
    }

    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoOcupacionesManagerImpl")
    private TipoOcupacionesManager tipoOcupacionesManager;

    @Override
    public Map<String, Object> guardarOcupacion(OcupacionPersona ocupacionPersona) throws Exception {
        Map<String, Object> object = null;
        try {
            OcupacionPersona ejOcupacionPersona = new OcupacionPersona();
            ejOcupacionPersona.setPersona(new Personas(ocupacionPersona.getPersona().getId()));
            ejOcupacionPersona.setTipoOcupacion(new TipoOcupaciones(ocupacionPersona.getTipoOcupacion().getId()));
            ejOcupacionPersona.setActivo("S");
            ejOcupacionPersona.setEmpresa(ocupacionPersona.getEmpresa());

            ejOcupacionPersona = this.get(ejOcupacionPersona);

            if (ejOcupacionPersona != null) {
                ejOcupacionPersona.setCargo(ocupacionPersona.getCargo());
                ejOcupacionPersona.setDireccion(ocupacionPersona.getDireccion());
                ejOcupacionPersona.setEmpresa(ocupacionPersona.getEmpresa());
                ejOcupacionPersona.setFechaIngreso(ocupacionPersona.getFechaIngreso());
                ejOcupacionPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejOcupacionPersona.setFechaSalida(ocupacionPersona.getFechaSalida());
                ejOcupacionPersona.setIdUsuarioModificacion(ocupacionPersona.getIdUsuarioModificacion());
                ejOcupacionPersona.setIngresosMensuales(ocupacionPersona.getIngresosMensuales());
                ejOcupacionPersona.setInterno(ocupacionPersona.getInterno());
                ejOcupacionPersona.setTelefonoPrincipal(ocupacionPersona.getTelefonoPrincipal());
                ejOcupacionPersona.setTelefonoSecundario(ocupacionPersona.getTelefonoSecundario());
                ejOcupacionPersona.setTipoTrabajo(ocupacionPersona.getTipoTrabajo());

                this.update(ejOcupacionPersona);
            } else {

                ocupacionPersona.setActivo("S");
                ocupacionPersona.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                ocupacionPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

                this.save(ocupacionPersona);
            }
            
            ejOcupacionPersona = new OcupacionPersona();
            ejOcupacionPersona.setPersona(new Personas(ocupacionPersona.getPersona().getId()));
            ejOcupacionPersona.setTipoOcupacion(new TipoOcupaciones(ocupacionPersona.getTipoOcupacion().getId()));
            ejOcupacionPersona.setActivo("S");
            ejOcupacionPersona.setEmpresa(ocupacionPersona.getEmpresa());
            
            object = this.getOcupacion(ejOcupacionPersona);

        } catch (Exception e) {
            logger.error("Error al guardar ocupacion", e);
        }
        return object;
    }

    @Override
    public Map<String, Object> getOcupacion(OcupacionPersona ocupacionPersona) throws Exception {
        String atributos = "id,cargo,empresa,tipoTrabajo,direccion,telefonoPrincipal,"
                + "telefonoSecundario,fechaIngreso,fechaSalida,interno,ingresosMensuales,tipoOcupacion.id";

        Map<String, Object> ocupacion = this.getAtributos(ocupacionPersona, atributos.split(","));

        if (ocupacion != null) {
            Map<String, Object> tipoOcupaciones = tipoOcupacionesManager.getAtributos(new TipoOcupaciones(Long.parseLong(ocupacion.get("tipoOcupacion.id").toString())),
                    "id,nombre,descripcion,activo".split(","));
            ocupacion.put("tipoOcupacion", tipoOcupaciones);
            ocupacion.remove("tipoOcupacion.id");
        }
        return ocupacion;
    }

    @Override
    public Map<String, Object> editarOcupacion(OcupacionPersona ocupacionPersona) throws Exception {
        Map<String, Object> object = null;
        try {
            OcupacionPersona ejOcupacionPersona = new OcupacionPersona();
            if (ocupacionPersona.getId() == null) {

                ejOcupacionPersona.setPersona(new Personas(ocupacionPersona.getPersona().getId()));
                ejOcupacionPersona.setTipoOcupacion(new TipoOcupaciones(ocupacionPersona.getTipoOcupacion().getId()));
                ejOcupacionPersona.setActivo("S");
                ejOcupacionPersona.setEmpresa(ocupacionPersona.getEmpresa());

                ejOcupacionPersona = this.get(ejOcupacionPersona);

                if (ejOcupacionPersona != null) {
                    ejOcupacionPersona.setCargo(ocupacionPersona.getCargo());
                    ejOcupacionPersona.setDireccion(ocupacionPersona.getDireccion());
                    ejOcupacionPersona.setEmpresa(ocupacionPersona.getEmpresa());
                    ejOcupacionPersona.setFechaIngreso(ocupacionPersona.getFechaIngreso());
                    ejOcupacionPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejOcupacionPersona.setFechaSalida(ocupacionPersona.getFechaSalida());
                    ejOcupacionPersona.setIdUsuarioModificacion(ocupacionPersona.getIdUsuarioModificacion());
                    ejOcupacionPersona.setIngresosMensuales(ocupacionPersona.getIngresosMensuales());
                    ejOcupacionPersona.setInterno(ocupacionPersona.getInterno());
                    ejOcupacionPersona.setTelefonoPrincipal(ocupacionPersona.getTelefonoPrincipal());
                    ejOcupacionPersona.setTelefonoSecundario(ocupacionPersona.getTelefonoSecundario());
                    ejOcupacionPersona.setTipoTrabajo(ocupacionPersona.getTipoTrabajo());

                    this.update(ejOcupacionPersona);
                } else {

                    ocupacionPersona.setActivo("S");
                    ocupacionPersona.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    ocupacionPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

                    this.save(ocupacionPersona);
                }
            } else {

                ejOcupacionPersona = this.get(ocupacionPersona.getId());

                ejOcupacionPersona.setCargo(ocupacionPersona.getCargo());
                ejOcupacionPersona.setDireccion(ocupacionPersona.getDireccion());
                ejOcupacionPersona.setEmpresa(ocupacionPersona.getEmpresa());
                ejOcupacionPersona.setFechaIngreso(ocupacionPersona.getFechaIngreso());
                ejOcupacionPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejOcupacionPersona.setFechaSalida(ocupacionPersona.getFechaSalida());
                ejOcupacionPersona.setIdUsuarioModificacion(ocupacionPersona.getIdUsuarioModificacion());
                ejOcupacionPersona.setIngresosMensuales(ocupacionPersona.getIngresosMensuales());
                ejOcupacionPersona.setInterno(ocupacionPersona.getInterno());
                ejOcupacionPersona.setTelefonoPrincipal(ocupacionPersona.getTelefonoPrincipal());
                ejOcupacionPersona.setTelefonoSecundario(ocupacionPersona.getTelefonoSecundario());
                ejOcupacionPersona.setTipoTrabajo(ocupacionPersona.getTipoTrabajo());

                this.update(ejOcupacionPersona);
            }
            
            ejOcupacionPersona = new OcupacionPersona();
            ejOcupacionPersona.setPersona(new Personas(ocupacionPersona.getPersona().getId()));
            ejOcupacionPersona.setTipoOcupacion(new TipoOcupaciones(ocupacionPersona.getTipoOcupacion().getId()));
            ejOcupacionPersona.setActivo("S");
            ejOcupacionPersona.setEmpresa(ocupacionPersona.getEmpresa());
            
            object = this.getOcupacion(ejOcupacionPersona);

        } catch (Exception e) {
            logger.error("Error al editar ocupacion", e);
        }
        return object;
    }

    @Override
    public List<Map<String, Object>> getListOcupaciones(OcupacionPersona ocupacionPersona) {
        List<Map<String, Object>> object = new ArrayList<>();
        try {
            List<Map<String, Object>> inmueblesMap = this.listAtributos(ocupacionPersona, "id".split(","));
            for(Map<String, Object> rpm: inmueblesMap){
                Map<String, Object> map = this.getOcupacion(new OcupacionPersona(Long.parseLong(rpm.get("id").toString())));
                object.add(map);
            }
        } catch (Exception e) {
            logger.error("Error al  obtener registros", e);
        }
        return object;
    }
}
