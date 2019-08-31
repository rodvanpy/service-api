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
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.entity.OcupacionSolicitudes;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.TipoOcupaciones;
import py.com.mojeda.service.ejb.manager.OcupacionSolicitudesManager;
import py.com.mojeda.service.ejb.manager.TipoOcupacionesManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;

/**
 *
 * @author Miguel
 */
@Stateless
public class OcupacionSolicitudesManagerImpl extends GenericDaoImpl<OcupacionSolicitudes, Long>
        implements OcupacionSolicitudesManager {

    @Override
    protected Class<OcupacionSolicitudes> getEntityBeanType() {
        return OcupacionSolicitudes.class;
    }

    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();
    protected static final DateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoOcupacionesManagerImpl")
    private TipoOcupacionesManager tipoOcupacionesManager;

    @Override
    public OcupacionSolicitudes guardarOcupacionSolicitudes(OcupacionSolicitudes ocupacionSolicitudes) throws Exception {
        OcupacionSolicitudes object = null;
        try {
            OcupacionSolicitudes ejOcupacionPersona = new OcupacionSolicitudes();
            ejOcupacionPersona.setPersona(new Personas(ocupacionSolicitudes.getPersona().getId()));
            ejOcupacionPersona.setPropuestaSolicitud(ocupacionSolicitudes.getPropuestaSolicitud());
            ejOcupacionPersona.setTipoOcupacion(new TipoOcupaciones(ocupacionSolicitudes.getTipoOcupacion().getId()));
            ejOcupacionPersona.setActivo("S");
            ejOcupacionPersona.setEmpresa(ocupacionSolicitudes.getEmpresa());

            ejOcupacionPersona = this.get(ejOcupacionPersona);

            if (ejOcupacionPersona != null) {
                ejOcupacionPersona.setCargo(ocupacionSolicitudes.getCargo());
                ejOcupacionPersona.setDireccion(ocupacionSolicitudes.getDireccion());
                ejOcupacionPersona.setEmpresa(ocupacionSolicitudes.getEmpresa());
                ejOcupacionPersona.setFechaIngreso(ocupacionSolicitudes.getFechaIngreso());
                ejOcupacionPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejOcupacionPersona.setFechaSalida(ocupacionSolicitudes.getFechaSalida());
                ejOcupacionPersona.setIdUsuarioModificacion(ocupacionSolicitudes.getIdUsuarioModificacion());
                ejOcupacionPersona.setIngresosMensuales(ocupacionSolicitudes.getIngresosMensuales());
                ejOcupacionPersona.setInterno(ocupacionSolicitudes.getInterno());
                ejOcupacionPersona.setTelefonoPrincipal(ocupacionSolicitudes.getTelefonoPrincipal());
                ejOcupacionPersona.setTelefonoSecundario(ocupacionSolicitudes.getTelefonoSecundario());
                ejOcupacionPersona.setTipoTrabajo(ocupacionSolicitudes.getTipoTrabajo());

                this.update(ejOcupacionPersona);
            } else {

                ocupacionSolicitudes.setActivo("S");
                ocupacionSolicitudes.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                ocupacionSolicitudes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

                this.save(ocupacionSolicitudes);
            }
            
            ejOcupacionPersona = new OcupacionSolicitudes();
            ejOcupacionPersona.setPersona(new Personas(ocupacionSolicitudes.getPersona().getId()));
            ejOcupacionPersona.setTipoOcupacion(new TipoOcupaciones(ocupacionSolicitudes.getTipoOcupacion().getId()));
            ejOcupacionPersona.setActivo("S");
            ejOcupacionPersona.setPropuestaSolicitud(ocupacionSolicitudes.getPropuestaSolicitud());
            ejOcupacionPersona.setEmpresa(ocupacionSolicitudes.getEmpresa());
            
            object = this.get(ejOcupacionPersona);

        } catch (Exception e) {
            logger.error("Error al guardar ocupacion", e);
        }
        return object;
    }

    @Override
    public OcupacionSolicitudes editarOcupacionSolicitudes(OcupacionSolicitudes ocupacionSolicitudes) throws Exception {
        OcupacionSolicitudes object = null;
        try {
            OcupacionSolicitudes ejOcupacionPersona = new OcupacionSolicitudes();
            if (ocupacionSolicitudes.getId() == null) {
                
                ejOcupacionPersona.setPropuestaSolicitud(ocupacionSolicitudes.getPropuestaSolicitud());
                ejOcupacionPersona.setPersona(new Personas(ocupacionSolicitudes.getPersona().getId()));
                ejOcupacionPersona.setTipoOcupacion(new TipoOcupaciones(ocupacionSolicitudes.getTipoOcupacion().getId()));
                ejOcupacionPersona.setActivo("S");
                ejOcupacionPersona.setEmpresa(ocupacionSolicitudes.getEmpresa());

                ejOcupacionPersona = this.get(ejOcupacionPersona);

                if (ejOcupacionPersona != null) {
                    ejOcupacionPersona.setCargo(ocupacionSolicitudes.getCargo());
                    ejOcupacionPersona.setDireccion(ocupacionSolicitudes.getDireccion());
                    ejOcupacionPersona.setEmpresa(ocupacionSolicitudes.getEmpresa());
                    ejOcupacionPersona.setFechaIngreso(ocupacionSolicitudes.getFechaIngreso());
                    ejOcupacionPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejOcupacionPersona.setFechaSalida(ocupacionSolicitudes.getFechaSalida());
                    ejOcupacionPersona.setIdUsuarioModificacion(ocupacionSolicitudes.getIdUsuarioModificacion());
                    ejOcupacionPersona.setIngresosMensuales(ocupacionSolicitudes.getIngresosMensuales());
                    ejOcupacionPersona.setInterno(ocupacionSolicitudes.getInterno());
                    ejOcupacionPersona.setTelefonoPrincipal(ocupacionSolicitudes.getTelefonoPrincipal());
                    ejOcupacionPersona.setTelefonoSecundario(ocupacionSolicitudes.getTelefonoSecundario());
                    ejOcupacionPersona.setTipoTrabajo(ocupacionSolicitudes.getTipoTrabajo());

                    this.update(ejOcupacionPersona);
                } else {

                    ocupacionSolicitudes.setActivo("S");
                    ocupacionSolicitudes.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    ocupacionSolicitudes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

                    this.save(ocupacionSolicitudes);
                }
            } else {

                ejOcupacionPersona = this.get(ocupacionSolicitudes.getId());

                ejOcupacionPersona.setCargo(ocupacionSolicitudes.getCargo());
                ejOcupacionPersona.setDireccion(ocupacionSolicitudes.getDireccion());
                ejOcupacionPersona.setEmpresa(ocupacionSolicitudes.getEmpresa());
                ejOcupacionPersona.setFechaIngreso(ocupacionSolicitudes.getFechaIngreso());
                ejOcupacionPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejOcupacionPersona.setFechaSalida(ocupacionSolicitudes.getFechaSalida());
                ejOcupacionPersona.setIdUsuarioModificacion(ocupacionSolicitudes.getIdUsuarioModificacion());
                ejOcupacionPersona.setIngresosMensuales(ocupacionSolicitudes.getIngresosMensuales());
                ejOcupacionPersona.setInterno(ocupacionSolicitudes.getInterno());
                ejOcupacionPersona.setTelefonoPrincipal(ocupacionSolicitudes.getTelefonoPrincipal());
                ejOcupacionPersona.setTelefonoSecundario(ocupacionSolicitudes.getTelefonoSecundario());
                ejOcupacionPersona.setTipoTrabajo(ocupacionSolicitudes.getTipoTrabajo());

                this.update(ejOcupacionPersona);
            }
            
            ejOcupacionPersona = new OcupacionSolicitudes();
            ejOcupacionPersona.setPropuestaSolicitud(ocupacionSolicitudes.getPropuestaSolicitud());
            ejOcupacionPersona.setPersona(new Personas(ocupacionSolicitudes.getPersona().getId()));
            ejOcupacionPersona.setTipoOcupacion(new TipoOcupaciones(ocupacionSolicitudes.getTipoOcupacion().getId()));
            ejOcupacionPersona.setActivo("S");
            ejOcupacionPersona.setEmpresa(ocupacionSolicitudes.getEmpresa());
            
            object = this.get(ejOcupacionPersona);

        } catch (Exception e) {
            logger.error("Error al editar ocupacion", e);
        }
        return object;
    }

    @Override
    public OcupacionSolicitudes getOcupacionSolicitud(OcupacionSolicitudes ocupacionPersona) throws Exception {
        OcupacionSolicitudes model = null;
        String atributos = "id,cargo,empresa,tipoTrabajo,direccion,telefonoPrincipal,"
                + "telefonoSecundario,fechaIngreso,fechaSalida,interno,ingresosMensuales,tipoOcupacion.id";

        Map<String, Object> ocupacion = this.getAtributos(ocupacionPersona, atributos.split(","));

        if (ocupacion != null) {
            
            model = new OcupacionSolicitudes();
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
    public List<OcupacionSolicitudes> getListOcupacionSolicitudes(OcupacionSolicitudes ocupacionPersona) {
        List<OcupacionSolicitudes> object = new ArrayList<>();
        try {
            List<Map<String, Object>> inmueblesMap = this.listAtributos(ocupacionPersona, "id".split(","));
            for (Map<String, Object> rpm : inmueblesMap) {
                OcupacionSolicitudes map = this.getOcupacionSolicitud(new OcupacionSolicitudes(Long.parseLong(rpm.get("id").toString())));
                object.add(map);
            }
        } catch (Exception e) {
            logger.error("Error al  obtener registros", e);
        }
        return object;
    }

}
