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
import py.com.mojeda.service.ejb.entity.ReferenciasSolicitudes;
import py.com.mojeda.service.ejb.entity.TipoReferencias;
import py.com.mojeda.service.ejb.manager.ReferenciaSolicitudesManager;
import py.com.mojeda.service.ejb.manager.TipoReferenciaManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;

/**
 *
 * @author Miguel
 */
@Stateless
public class ReferenciaSolicitudesManagerImpl extends GenericDaoImpl<ReferenciasSolicitudes, Long>
        implements ReferenciaSolicitudesManager {

    @Override
    protected Class<ReferenciasSolicitudes> getEntityBeanType() {
        return ReferenciasSolicitudes.class;
    }

    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoReferenciaManagerImpl")
    private TipoReferenciaManager tipoReferenciaManager;

    @Override
    public ReferenciasSolicitudes guardarReferenciaSolicitud(ReferenciasSolicitudes referencias) throws Exception {
        ReferenciasSolicitudes object = null;
        try {
            ReferenciasSolicitudes ejReferencias = new ReferenciasSolicitudes();
            if (referencias.getId() == null) {
                
                ejReferencias.setActivo("S");
                ejReferencias.setPersona(referencias.getPersona());
                ejReferencias.setPropuestaSolicitud(referencias.getPropuestaSolicitud());
                ejReferencias.setNombreContacto(referencias.getNombreContacto());
                ejReferencias.setTipoReferencia(referencias.getTipoReferencia());
                ejReferencias = this.get(ejReferencias);
                
                if (ejReferencias != null) {
                    ejReferencias.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejReferencias.setIdUsuarioModificacion(referencias.getIdUsuarioModificacion());
                    ejReferencias.setNombreContacto(referencias.getNombreContacto()  == null ? null : referencias.getNombreContacto().toUpperCase());
                    ejReferencias.setTelefono(referencias.getTelefono());
                    ejReferencias.setTelefonoCelular(referencias.getTelefonoCelular());

                    this.update(ejReferencias);
                } else {
                    referencias.setNombreContacto(referencias.getNombreContacto()  == null ? null : referencias.getNombreContacto().toUpperCase());
                    this.save(referencias);
                }
            } else {

                ejReferencias = this.get(referencias.getId());
                ejReferencias.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejReferencias.setIdUsuarioModificacion(referencias.getIdUsuarioModificacion());
                ejReferencias.setNombreContacto(referencias.getNombreContacto()  == null ? null : referencias.getNombreContacto().toUpperCase());
                ejReferencias.setTelefono(referencias.getTelefono());
                ejReferencias.setTelefonoCelular(referencias.getTelefonoCelular());

                this.update(referencias);
            }
            
            ejReferencias = new ReferenciasSolicitudes();
            ejReferencias.setActivo("S");
            ejReferencias.setPersona(referencias.getPersona());
            ejReferencias.setPropuestaSolicitud(referencias.getPropuestaSolicitud());
            ejReferencias.setNombreContacto(referencias.getNombreContacto());
            ejReferencias.setTipoReferencia(referencias.getTipoReferencia());
            
            object = this.getReferenciaSolicitud(ejReferencias);
            
        } catch (Exception e) {
            logger.error("Error al guardar referencia", e);
        }
        return object;
    }

    @Override
    public ReferenciasSolicitudes editarReferenciaSolicitud(ReferenciasSolicitudes referencias) throws Exception {
        ReferenciasSolicitudes object = null;
        try {
            ReferenciasSolicitudes ejReferencias = new ReferenciasSolicitudes();
            if (referencias.getId() == null) {
                
                ejReferencias.setActivo("S");
                ejReferencias.setPersona(referencias.getPersona());
                ejReferencias.setNombreContacto(referencias.getNombreContacto());
                ejReferencias.setTipoReferencia(referencias.getTipoReferencia());
                
                ejReferencias = this.get(ejReferencias);
                
                if (ejReferencias != null) {
                    ejReferencias.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejReferencias.setIdUsuarioModificacion(referencias.getIdUsuarioModificacion());
                    ejReferencias.setNombreContacto(referencias.getNombreContacto()  == null ? null : referencias.getNombreContacto().toUpperCase());
                    ejReferencias.setTelefono(referencias.getTelefono());
                    ejReferencias.setTelefonoCelular(referencias.getTelefonoCelular());

                    this.update(ejReferencias);
                } else {
                    referencias.setNombreContacto(referencias.getNombreContacto()  == null ? null : referencias.getNombreContacto().toUpperCase());
                    this.save(referencias);
                }
            } else {

                ejReferencias = this.get(referencias.getId());
                ejReferencias.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejReferencias.setIdUsuarioModificacion(referencias.getIdUsuarioModificacion());
                ejReferencias.setNombreContacto(referencias.getNombreContacto()  == null ? null : referencias.getNombreContacto().toUpperCase());
                ejReferencias.setTelefono(referencias.getTelefono());
                ejReferencias.setTelefonoCelular(referencias.getTelefonoCelular());

                this.update(referencias);
            }
            
            ejReferencias = new ReferenciasSolicitudes();
            ejReferencias.setActivo("S");
            ejReferencias.setPersona(referencias.getPersona());
            ejReferencias.setPropuestaSolicitud(referencias.getPropuestaSolicitud());
            ejReferencias.setNombreContacto(referencias.getNombreContacto());
            ejReferencias.setTipoReferencia(referencias.getTipoReferencia());
            
            object = this.getReferenciaSolicitud(ejReferencias);
            
        } catch (Exception e) {
            logger.error("Error al guardar referencia", e);
        }
        return object;
    }

    @Override
    public ReferenciasSolicitudes getReferenciaSolicitud(ReferenciasSolicitudes referencias) throws Exception {
        ReferenciasSolicitudes model = null;
        String atributos = "id,nombreContacto,telefono,telefonoCelular,activo,tipoReferencia.id";

        Map<String, Object> ocupacion = this.getAtributos(referencias, atributos.split(","));

        if (ocupacion != null) {
            Map<String, Object> tipoOcupaciones = tipoReferenciaManager.getAtributos(new TipoReferencias(Long.parseLong(ocupacion.get("tipoReferencia.id").toString())),
                    "id,nombre,activo".split(","));
            
            TipoReferencias ejTipoReferencia = new TipoReferencias();
            ejTipoReferencia.setId(Long.parseLong(tipoOcupaciones.get("id").toString()));
            ejTipoReferencia.setNombre(tipoOcupaciones.get("nombre") == null ? "" : tipoOcupaciones.get("nombre").toString());
            ejTipoReferencia.setActivo(tipoOcupaciones.get("activo") == null ? "" : tipoOcupaciones.get("activo").toString());
            
            model = new ReferenciasSolicitudes();
            model.setTipoReferencia(ejTipoReferencia);
            model.setId(Long.parseLong(ocupacion.get("id").toString()));
            model.setNombreContacto(ocupacion.get("nombreContacto") == null ? "" : ocupacion.get("nombreContacto").toString());
            model.setTelefono(ocupacion.get("telefono") == null ? "" : ocupacion.get("telefono").toString());
            model.setTelefonoCelular(ocupacion.get("telefonoCelular") == null ? "" : ocupacion.get("telefonoCelular").toString());
            model.setActivo(ocupacion.get("activo") == null ? "" : ocupacion.get("activo").toString());            
            
        }
        return model;
    }

    @Override
    public List<ReferenciasSolicitudes> getListReferenciaSolicitud(ReferenciasSolicitudes referencias) {
        List<ReferenciasSolicitudes> object = new ArrayList<>();
        try {
            List<Map<String, Object>> inmueblesMap = this.listAtributos(referencias, "id".split(","));
            for(Map<String, Object> rpm: inmueblesMap){
                ReferenciasSolicitudes map = this.getReferenciaSolicitud(new ReferenciasSolicitudes(Long.parseLong(rpm.get("id").toString())));
                object.add(map);
            }
        } catch (Exception e) {
            logger.error("Error al  obtener registros", e);
        }
        return object;
    }

}
