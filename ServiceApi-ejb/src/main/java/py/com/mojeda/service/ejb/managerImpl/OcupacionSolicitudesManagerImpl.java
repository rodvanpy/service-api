/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.sql.Timestamp;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.OcupacionSolicitudes;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.TipoOcupaciones;
import py.com.mojeda.service.ejb.manager.OcupacionSolicitudesManager;
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

}
