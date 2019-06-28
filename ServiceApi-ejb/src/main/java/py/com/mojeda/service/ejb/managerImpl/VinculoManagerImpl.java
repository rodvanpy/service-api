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

/**
 *
 * @author Miguel
 */
@Stateless
public class VinculoManagerImpl extends GenericDaoImpl<Vinculos, Long>
        implements VinculoManager {

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
                Personas ejPersonaVinculo = personaManager.guardar(vinculo.getPersonaVinculo());
                vinculo.setPersonaVinculo(new Personas(ejPersonaVinculo.getId()));

                Vinculos ejVinculo;
                if (vinculo.getTipoVinculo().compareToIgnoreCase("CONYUGE") == 0) {
                    ejVinculo = new Vinculos();
                    ejVinculo.setPersona(new Personas(ejPersonaVinculo.getId()));
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
                        ejVinculo.setPersona(new Personas(ejPersonaVinculo.getId()));
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
                ejVinculo.setPersonaVinculo(new Personas(ejPersonaVinculo.getId()));
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

                object = this.getVinculo(vinculo);

                for (OcupacionPersona rpm : (vinculo.getOcupaciones() == null ? new ArrayList<OcupacionPersona>() : vinculo.getOcupaciones())) {
                    if (rpm.getId() == null) {
                        rpm.setActivo("S");
                        rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                        rpm.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                        rpm.setIdUsuarioCreacion(vinculo.getIdUsuarioModificacion());
                        rpm.setPersona(new Personas(vinculo.getPersonaVinculo().getId()));

                        Map<String, Object> responseMaps = ocupacionPersonaManager.guardarOcupacion(rpm);
                    } else {
                        rpm.setActivo("S");
                        rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        rpm.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());

                        Map<String, Object> responseMaps = ocupacionPersonaManager.editarOcupacion(rpm);
                    }
                }

                return object;

            } else {
                Personas ejPersonaVinculo = personaManager.guardar(vinculo.getPersonaVinculo());

                if (vinculo.getTipoVinculo().compareToIgnoreCase("CONYUGE") == 0) {
                    Vinculos ejVinculo = new Vinculos();
                    ejVinculo.setPersona(new Personas(ejPersonaVinculo.getId()));
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
                        ejVinculo.setPersona(new Personas(ejPersonaVinculo.getId()));
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

                vinculo.setPersonaVinculo(new Personas(ejPersonaVinculo.getId()));
                vinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                vinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());

                this.update(vinculo);

                object = this.getVinculo(vinculo);

                for (OcupacionPersona rpm : (vinculo.getOcupaciones() == null ? new ArrayList<OcupacionPersona>() : vinculo.getOcupaciones())) {
                    if (rpm.getId() == null) {
                        rpm.setActivo("S");
                        rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                        rpm.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                        rpm.setIdUsuarioCreacion(vinculo.getIdUsuarioModificacion());
                        rpm.setPersona(new Personas(vinculo.getPersonaVinculo().getId()));

                        Map<String, Object> responseMaps = ocupacionPersonaManager.guardarOcupacion(rpm);
                    } else {
                        rpm.setActivo("S");
                        rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        rpm.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());

                        Map<String, Object> responseMaps = ocupacionPersonaManager.editarOcupacion(rpm);
                    }
                }

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
                Personas ejPersonaVinculo = personaManager.guardar(vinculo.getPersonaVinculo());
                vinculo.setPersonaVinculo(new Personas(ejPersonaVinculo.getId()));

                Vinculos ejVinculo;
                if (vinculo.getTipoVinculo().compareToIgnoreCase("CONYUGE") == 0) {
                    ejVinculo = new Vinculos();
                    ejVinculo.setPersona(new Personas(ejPersonaVinculo.getId()));
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
                        ejVinculo.setPersona(new Personas(ejPersonaVinculo.getId()));
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
                ejVinculo.setPersonaVinculo(new Personas(ejPersonaVinculo.getId()));
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

                object = this.getVinculo(vinculo);
                
                for (OcupacionPersona rpm : (vinculo.getOcupaciones() == null ? new ArrayList<OcupacionPersona>() : vinculo.getOcupaciones())) {
                    if (rpm.getId() == null) {
                        rpm.setActivo("S");
                        rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                        rpm.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                        rpm.setIdUsuarioCreacion(vinculo.getIdUsuarioModificacion());
                        rpm.setPersona(new Personas(vinculo.getPersonaVinculo().getId()));

                        Map<String, Object> responseMaps = ocupacionPersonaManager.guardarOcupacion(rpm);
                    } else {
                        rpm.setActivo("S");
                        rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        rpm.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());

                        Map<String, Object> responseMaps = ocupacionPersonaManager.editarOcupacion(rpm);
                    }
                }

                return object;

            } else {
                Personas ejPersonaVinculo = personaManager.guardar(vinculo.getPersonaVinculo());

                if (vinculo.getTipoVinculo().compareToIgnoreCase("CONYUGE") == 0) {
                    Vinculos ejVinculo = new Vinculos();
                    ejVinculo.setPersona(new Personas(ejPersonaVinculo.getId()));
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
                        ejVinculo.setPersona(new Personas(ejPersonaVinculo.getId()));
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

                vinculo.setPersonaVinculo(new Personas(ejPersonaVinculo.getId()));
                vinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                vinculo.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());

                this.update(vinculo);

                object = this.getVinculo(vinculo);
                
                for (OcupacionPersona rpm : (vinculo.getOcupaciones() == null ? new ArrayList<OcupacionPersona>() : vinculo.getOcupaciones())) {
                    if (rpm.getId() == null) {
                        rpm.setActivo("S");
                        rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                        rpm.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());
                        rpm.setIdUsuarioCreacion(vinculo.getIdUsuarioModificacion());
                        rpm.setPersona(new Personas(vinculo.getPersonaVinculo().getId()));

                        Map<String, Object> responseMaps = ocupacionPersonaManager.guardarOcupacion(rpm);
                    } else {
                        rpm.setActivo("S");
                        rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        rpm.setIdUsuarioModificacion(vinculo.getIdUsuarioModificacion());

                        Map<String, Object> responseMaps = ocupacionPersonaManager.editarOcupacion(rpm);
                    }
                }

                return object;
            }

        }
        return object;
    }

    @Override
    public Map<String, Object> getVinculo(Vinculos vinculo) throws Exception {
        Map<String, Object> model = this.getAtributos(vinculo, "id,tipoVinculo,persona.id,personaVinculo.id,activo".split(","));

        if (model != null) {
            Map<String, Object> persona = personaManager.getPersona(new Personas(Long.parseLong(model.get("persona.id").toString())));

            Map<String, Object> personaVinculo = personaManager.getPersona(new Personas(Long.parseLong(model.get("personaVinculo.id").toString())));

            OcupacionPersona ejOcupacion = new OcupacionPersona();
            ejOcupacion.setPersona(new Personas(Long.parseLong(model.get("personaVinculo.id").toString())));
            List< Map<String, Object>> ocupaciones = ocupacionPersonaManager.getListOcupaciones(ejOcupacion);

            model.put("ocupaciones", ocupaciones);
            model.put("persona", persona);
            model.remove("persona.id");
            model.put("personaVinculo", personaVinculo);
            model.remove("personaVinculo.id");
        }
        return model;
    }
}
