/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    protected static final DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoOcupacionesManagerImpl")
    private TipoOcupacionesManager tipoOcupacionesManager;

    @Override
    public OcupacionPersona guardarOcupacion(OcupacionPersona ocupacionPersona) throws Exception {
        OcupacionPersona object = null;
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

            object = this.getOcupacion(new OcupacionPersona(ocupacionPersona.getId() == null ? ejOcupacionPersona.getId() : ocupacionPersona.getId()));

        } catch (Exception e) {
            logger.error("Error al guardar ocupacion", e);
        }
        return object;
    }

    @Override
    public OcupacionPersona editarOcupacion(OcupacionPersona ocupacionPersona) throws Exception {
        OcupacionPersona object = null;
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

            object = this.getOcupacion(new OcupacionPersona(ocupacionPersona.getId() == null ? ejOcupacionPersona.getId() : ocupacionPersona.getId()));

        } catch (Exception e) {
            logger.error("Error al editar ocupacion", e);
        }
        return object;
    }

    @Override
    public OcupacionPersona getOcupacion(OcupacionPersona ocupacionPersona) throws Exception {
        OcupacionPersona model = null;
        String atributos = "id,cargo,empresa,tipoTrabajo,direccion,telefonoPrincipal,"
                + "telefonoSecundario,fechaIngreso,fechaSalida,interno,ingresosMensuales,tipoOcupacion.id";

        Map<String, Object> ocupacion = this.getAtributos(ocupacionPersona, atributos.split(","));

        if (ocupacion != null) {
            
            model = new OcupacionPersona();
            model.setTipoOcupacion(tipoOcupacionesManager.get(new TipoOcupaciones(Long.parseLong(ocupacion.get("tipoOcupacion.id").toString()))));
            model.setId(Long.parseLong(ocupacion.get("id").toString()));
            model.setIngresosMensuales(ocupacion.get("ingresosMensuales") == null ? null : new BigDecimal(ocupacion.get("ingresosMensuales").toString()));
            model.setCargo(ocupacion.get("cargo") == null ? "" : ocupacion.get("cargo").toString());
            model.setDireccion(ocupacion.get("direccion") == null ? "" : ocupacion.get("direccion").toString());
            model.setEmpresa(ocupacion.get("empresa") == null ? "" : ocupacion.get("empresa").toString());
            model.setFechaIngreso(ocupacion.get("fechaIngreso") == null ? null : dateFormat.parse(ocupacion.get("fechaIngreso").toString()));
            model.setFechaSalida(ocupacion.get("fechaSalida") == null ? null : dateFormat.parse(ocupacion.get("fechaSalida").toString()));
            model.setInterno(ocupacion.get("interno") == null ? "" : ocupacion.get("interno").toString());
            model.setTelefonoPrincipal(ocupacion.get("telefonoPrincipal") == null ? "" : ocupacion.get("telefonoPrincipal").toString());
            model.setTelefonoSecundario(ocupacion.get("telefonoSecundario") == null ? "" : ocupacion.get("telefonoSecundario").toString());
            model.setTipoTrabajo(ocupacion.get("tipoTrabajo") == null ? "" : ocupacion.get("tipoTrabajo").toString());
            model.setActivo(ocupacion.get("activo") == null ? "" : ocupacion.get("activo").toString());
        }
        return model;
    }

    @Override
    public List<OcupacionPersona> getListOcupaciones(OcupacionPersona ocupacionPersona) {
        List<OcupacionPersona> object = new ArrayList<>();
        try {
            List<Map<String, Object>> inmueblesMap = this.listAtributos(ocupacionPersona, "id".split(","));
            for (Map<String, Object> rpm : inmueblesMap) {
                OcupacionPersona map = this.getOcupacion(new OcupacionPersona(Long.parseLong(rpm.get("id").toString())));
                object.add(map);
            }
        } catch (Exception e) {
            logger.error("Error al  obtener registros", e);
        }
        return object;
    }
}
