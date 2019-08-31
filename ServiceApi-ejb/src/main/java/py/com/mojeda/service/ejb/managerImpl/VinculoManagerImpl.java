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
import py.com.mojeda.service.ejb.entity.Documentos;
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Vinculos;
import py.com.mojeda.service.ejb.manager.OcupacionPersonaManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.VinculoManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;

/**
 *
 * @author Miguel
 */
@Stateless
public class VinculoManagerImpl extends GenericDaoImpl<Vinculos, Long>
        implements VinculoManager {
    
    private static final ApplicationLogger logger = ApplicationLogger.getInstance();

    @EJB(mappedName = "java:app/ServiceApi-ejb/PersonaManagerImpl")
    private PersonaManager personaManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/OcupacionPersonaManagerImpl")
    private OcupacionPersonaManager ocupacionPersonaManager;

    @Override
    protected Class<Vinculos> getEntityBeanType() {
        return Vinculos.class;
    }

    @Override
    public Map<String, Object> guardar(Vinculos vinculo) throws Exception {
        Map<String, Object> object = null;
        if (vinculo != null
                && vinculo.getPersona() != null && vinculo.getPersonaVinculo() != null) {

            if (vinculo.getId() == null) {

                vinculo.setPersonaVinculo(new Personas(vinculo.getPersonaVinculo().getId()));

                Vinculos ejVinculo;
                if (vinculo.getTipoVinculo().compareToIgnoreCase("CONYUGE") == 0) {
                    ejVinculo = new Vinculos();
                    ejVinculo.setPersona(new Personas(vinculo.getPersonaVinculo().getId()));
                    ejVinculo.setPersonaVinculo(new Personas(vinculo.getPersona().getId()));
                    ejVinculo.setTipoVinculo(vinculo.getTipoVinculo());

                    ejVinculo = this.get(ejVinculo);
                    if (ejVinculo != null) {

                        ejVinculo.setActivo("S");
                        ejVinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                        this.update(ejVinculo);

                    } else {

                        ejVinculo = new Vinculos();
                        ejVinculo.setPersona(new Personas(vinculo.getPersonaVinculo().getId()));
                        ejVinculo.setPersonaVinculo(new Personas(vinculo.getPersona().getId()));
                        ejVinculo.setTipoVinculo(vinculo.getTipoVinculo());
                        ejVinculo.setActivo("S");
                        ejVinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                        ejVinculo.setIdUsuarioCreacion(vinculo.getIdUsuarioCreacion());

                        this.save(ejVinculo);
                    }
                }

                ejVinculo = new Vinculos();
                ejVinculo.setPersona(new Personas(vinculo.getPersona().getId()));
                ejVinculo.setPersonaVinculo(new Personas(vinculo.getPersonaVinculo().getId()));
                ejVinculo.setTipoVinculo(vinculo.getTipoVinculo());

                ejVinculo = this.get(ejVinculo);

                if (ejVinculo != null) {
                    ejVinculo.setActivo("S");
                    ejVinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejVinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                    this.update(ejVinculo);
                } else {
                    this.save(vinculo);
                }

                object = this.getVinculo(new Vinculos(vinculo.getId() == null ? ejVinculo.getId() : vinculo.getId()));

                return object;

            } else {

                if (vinculo.getTipoVinculo().compareToIgnoreCase("CONYUGE") == 0) {
                    Vinculos ejVinculo = new Vinculos();
                    ejVinculo.setPersona(new Personas(vinculo.getPersonaVinculo().getId()));
                    ejVinculo.setPersonaVinculo(new Personas(vinculo.getPersona().getId()));
                    ejVinculo.setTipoVinculo(vinculo.getTipoVinculo());

                    ejVinculo = this.get(ejVinculo);
                    if (ejVinculo != null) {

                        ejVinculo.setActivo("S");
                        ejVinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                        this.update(ejVinculo);

                    } else {

                        ejVinculo = new Vinculos();
                        ejVinculo.setPersona(new Personas(vinculo.getPersonaVinculo().getId()));
                        ejVinculo.setPersonaVinculo(new Personas(vinculo.getPersona().getId()));
                        ejVinculo.setTipoVinculo(vinculo.getTipoVinculo());
                        ejVinculo.setActivo("S");
                        ejVinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                        ejVinculo.setIdUsuarioCreacion(vinculo.getIdUsuarioCreacion());

                        this.save(ejVinculo);
                    }
                }

                vinculo.setPersonaVinculo(new Personas(vinculo.getPersonaVinculo().getId()));
                vinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                vinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());

                this.update(vinculo);

                object = this.getVinculo(new Vinculos(vinculo.getId()));

                return object;
            }

        }
        return object;
    }

    @Override
    public Map<String, Object> editar(Vinculos vinculo) throws Exception {
        Map<String, Object> object = null;
        if (vinculo != null
                && vinculo.getPersona() != null && vinculo.getPersonaVinculo() != null) {

            if (vinculo.getId() == null) {
                
                vinculo.setPersonaVinculo(new Personas(vinculo.getPersonaVinculo().getId()));

                Vinculos ejVinculo;
                if (vinculo.getTipoVinculo().compareToIgnoreCase("CONYUGE") == 0) {
                    ejVinculo = new Vinculos();
                    ejVinculo.setPersona(new Personas(vinculo.getPersonaVinculo().getId()));
                    ejVinculo.setPersonaVinculo(new Personas(vinculo.getPersona().getId()));
                    ejVinculo.setTipoVinculo(vinculo.getTipoVinculo());

                    ejVinculo = this.get(ejVinculo);
                    if (ejVinculo != null) {

                        ejVinculo.setActivo("S");
                        ejVinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                        this.update(ejVinculo);

                    } else {

                        ejVinculo = new Vinculos();
                        ejVinculo.setPersona(new Personas(vinculo.getPersonaVinculo().getId()));
                        ejVinculo.setPersonaVinculo(new Personas(vinculo.getPersona().getId()));
                        ejVinculo.setTipoVinculo(vinculo.getTipoVinculo());
                        ejVinculo.setActivo("S");
                        ejVinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                        ejVinculo.setIdUsuarioCreacion(vinculo.getIdUsuarioCreacion());

                        this.save(ejVinculo);
                    }
                }

                ejVinculo = new Vinculos();
                ejVinculo.setPersona(new Personas(vinculo.getPersona().getId()));
                ejVinculo.setPersonaVinculo(new Personas(vinculo.getPersonaVinculo().getId()));
                ejVinculo.setTipoVinculo(vinculo.getTipoVinculo());

                ejVinculo = this.get(ejVinculo);

                if (ejVinculo != null) {
                    ejVinculo.setActivo("S");
                    ejVinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejVinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                    this.update(ejVinculo);
                } else {
                    this.save(vinculo);
                }

                object = this.getVinculo(new Vinculos(vinculo.getId() == null ? ejVinculo.getId() : vinculo.getId()));                

                return object;

            } else {

                if (vinculo.getTipoVinculo().compareToIgnoreCase("CONYUGE") == 0) {
                    Vinculos ejVinculo = new Vinculos();
                    ejVinculo.setPersona(new Personas(vinculo.getPersonaVinculo().getId()));
                    ejVinculo.setPersonaVinculo(new Personas(vinculo.getPersona().getId()));
                    ejVinculo.setTipoVinculo(vinculo.getTipoVinculo());

                    ejVinculo = this.get(ejVinculo);
                    if (ejVinculo != null) {

                        ejVinculo.setActivo("S");
                        ejVinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                        this.update(ejVinculo);

                    } else {

                        ejVinculo = new Vinculos();
                        ejVinculo.setPersona(new Personas(vinculo.getPersonaVinculo().getId()));
                        ejVinculo.setPersonaVinculo(new Personas(vinculo.getPersona().getId()));
                        ejVinculo.setTipoVinculo(vinculo.getTipoVinculo());
                        ejVinculo.setActivo("S");
                        ejVinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                        ejVinculo.setIdUsuarioCreacion(vinculo.getIdUsuarioCreacion());

                        this.save(ejVinculo);
                    }
                }

                vinculo.setPersonaVinculo(new Personas(vinculo.getPersonaVinculo().getId()));
                vinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                vinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());

                this.update(vinculo);

                object = this.getVinculo(new Vinculos(vinculo.getId()));

                return object;
            }

        }
        return object;
    }

    @Override
    public Map<String, Object> getVinculo(Vinculos vinculo) throws Exception {
        Map<String, Object> model = this.getAtributos(vinculo, "id,tipoVinculo,personaVinculo.id,activo".split(","));

        if (model != null) {
            //Map<String, Object> persona = personaManager.getPersona(new Personas(Long.parseLong(model.get("persona.id").toString())));
            //model.put("persona", persona);
            //model.remove("persona.id");
            model.put("personaVinculo", personaManager.getPersona(new Personas(Long.parseLong(model.get("personaVinculo.id").toString()))));
            model.remove("personaVinculo.id");
        }
        return model;
    }
    
    @Override
    public List<Map<String, Object>> getListVinculos(Vinculos vinculo) {
        List<Map<String, Object>> object = new ArrayList<>();
        try {
            List<Map<String, Object>> vinculoMap = this.listAtributos(vinculo, "id".split(","));
            for(Map<String, Object> rpm: vinculoMap){
                Map<String, Object> map = this.getVinculo(new Vinculos(Long.parseLong(rpm.get("id").toString())));
                object.add(map);
            }
        } catch (Exception e) {
            logger.error("Error al  obtener registros", e);
        }
        return object;
    }
}
