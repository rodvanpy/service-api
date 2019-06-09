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
import py.com.mojeda.service.ejb.entity.Referencias;
import py.com.mojeda.service.ejb.entity.TipoReferencias;
import py.com.mojeda.service.ejb.manager.ReferenciaManager;
import py.com.mojeda.service.ejb.manager.TipoReferenciaManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;

/**
 *
 * @author Miguel
 */
@Stateless
public class ReferenciaManagerImpl extends GenericDaoImpl<Referencias, Long>
        implements ReferenciaManager {

    @Override
    protected Class<Referencias> getEntityBeanType() {
        return Referencias.class;
    }

    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoReferenciaManagerImpl")
    private TipoReferenciaManager tipoReferenciaManager;

    @Override
    public Map<String, Object> guardarReferencia(Referencias referencias) throws Exception {
        Map<String, Object> object = null;
        try {
            Referencias ejReferencias = new Referencias();
            if (referencias.getId() == null) {
                ejReferencias.setPersona(referencias.getPersona());
                ejReferencias.setNombreContacto(referencias.getNombreContacto());
                ejReferencias.setTipoReferencia(referencias.getTipoReferencia());
                ejReferencias = this.get(ejReferencias);
                if (ejReferencias != null) {
                    ejReferencias.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejReferencias.setIdUsuarioModificacion(referencias.getIdUsuarioModificacion());
                    ejReferencias.setTelefono(referencias.getTelefono());
                    ejReferencias.setTelefonoCelular(referencias.getTelefonoCelular());

                    this.update(referencias);
                } else {
                    this.save(referencias);
                }
            } else {

                ejReferencias = this.get(referencias.getId());
                ejReferencias.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejReferencias.setIdUsuarioModificacion(referencias.getIdUsuarioModificacion());
                ejReferencias.setTelefono(referencias.getTelefono());
                ejReferencias.setTelefonoCelular(referencias.getTelefonoCelular());

                this.update(referencias);
            }
            
            ejReferencias = new Referencias();
            ejReferencias.setPersona(referencias.getPersona());
            ejReferencias.setNombreContacto(referencias.getNombreContacto());
            ejReferencias.setTipoReferencia(referencias.getTipoReferencia());
            
            object = this.getReferencia(ejReferencias);
            
        } catch (Exception e) {
            logger.error("Error al guardar referencia", e);
        }
        return object;
    }

    @Override
    public Map<String, Object> editarReferencia(Referencias referencias) throws Exception {
        Map<String, Object> object = null;
        try {
            Referencias ejReferencias = new Referencias();
            if (referencias.getId() == null) {
                ejReferencias.setPersona(referencias.getPersona());
                ejReferencias.setNombreContacto(referencias.getNombreContacto());
                ejReferencias.setTipoReferencia(referencias.getTipoReferencia());
                ejReferencias = this.get(ejReferencias);
                if (ejReferencias != null) {
                    ejReferencias.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejReferencias.setIdUsuarioModificacion(referencias.getIdUsuarioModificacion());
                    ejReferencias.setTelefono(referencias.getTelefono());
                    ejReferencias.setTelefonoCelular(referencias.getTelefonoCelular());

                    this.update(referencias);
                } else {
                    this.save(referencias);
                }
            } else {

                ejReferencias = this.get(referencias.getId());
                ejReferencias.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejReferencias.setIdUsuarioModificacion(referencias.getIdUsuarioModificacion());
                ejReferencias.setTelefono(referencias.getTelefono());
                ejReferencias.setTelefonoCelular(referencias.getTelefonoCelular());

                this.update(referencias);
            }
            
            ejReferencias = new Referencias();
            ejReferencias.setPersona(referencias.getPersona());
            ejReferencias.setNombreContacto(referencias.getNombreContacto());
            ejReferencias.setTipoReferencia(referencias.getTipoReferencia());
            
            object = this.getReferencia(ejReferencias);
            
        } catch (Exception e) {
            logger.error("Error al guardar referencia", e);
        }
        return object;
    }

    @Override
    public Map<String, Object> getReferencia(Referencias referencias) throws Exception {
        String atributos = "id,nombreContacto,telefono,telefonoCelular,activo,tipoReferencia.id";

        Map<String, Object> ocupacion = this.getAtributos(referencias, atributos.split(","));

        if (ocupacion != null) {
            Map<String, Object> tipoOcupaciones = tipoReferenciaManager.getAtributos(new TipoReferencias(Long.parseLong(ocupacion.get("tipoReferencia.id").toString())),
                    "id,nombre,activo".split(","));
            ocupacion.put("tipoReferencia", tipoOcupaciones);
            ocupacion.remove("tipoReferencia.id");
        }
        return ocupacion;
    }

    @Override
    public List<Map<String, Object>> getListReferencias(Referencias referencias) {
        List<Map<String, Object>> object = new ArrayList<>();
        try {
            List<Map<String, Object>> inmueblesMap = this.listAtributos(referencias, "id".split(","));
            for(Map<String, Object> rpm: inmueblesMap){
                Map<String, Object> map = this.getReferencia(new Referencias(Long.parseLong(rpm.get("id").toString())));
                object.add(map);
            }
        } catch (Exception e) {
            logger.error("Error al  obtener registros", e);
        }
        return object;
    }

}
