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
import py.com.mojeda.service.ejb.entity.Estudios;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.TipoEstudios;
import py.com.mojeda.service.ejb.manager.EstudiosManager;
import py.com.mojeda.service.ejb.manager.TipoEstudiosManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;

/**
 *
 * @author Miguel
 */
@Stateless
public class EstudiosManagerImpl extends GenericDaoImpl<Estudios, Long>
        implements EstudiosManager {

    @Override
    protected Class<Estudios> getEntityBeanType() {
        return Estudios.class;
    }
    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoEstudiosManagerImpl")
    private TipoEstudiosManager tipoEstudiosManager;

    @Override
    public Map<String, Object> guardarEstudios(Estudios estudios) throws Exception {
        Map<String, Object> object = null;
        try {
            Estudios ejEstudios = new Estudios();
            ejEstudios.setPersona(new Personas(estudios.getPersona().getId()));
            ejEstudios.setTipoEstudio(new TipoEstudios(estudios.getTipoEstudio().getId()));
            ejEstudios.setActivo("S");

            ejEstudios = this.get(ejEstudios);

            if (ejEstudios != null) {

                ejEstudios.setConcluido(estudios.getConcluido());
                ejEstudios.setFechaFin(estudios.getFechaFin());
                ejEstudios.setFechaInicio(estudios.getFechaInicio());
                ejEstudios.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejEstudios.setIdUsuarioModificacion(estudios.getIdUsuarioModificacion());
                ejEstudios.setNombre(estudios.getNombre());
                ejEstudios.setNumeroRegistro(estudios.getNumeroRegistro());
                ejEstudios.setSemestre(estudios.getSemestre());
                ejEstudios.setTitulo(estudios.getTitulo());

                this.update(ejEstudios);
            } else {

                estudios.setActivo("S");
                estudios.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                estudios.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

                this.save(estudios);
            }
            
            ejEstudios = new Estudios();
            ejEstudios.setPersona(new Personas(estudios.getPersona().getId()));
            ejEstudios.setTipoEstudio(new TipoEstudios(estudios.getTipoEstudio().getId()));
            ejEstudios.setActivo("S");

            object = this.getEstudios(ejEstudios);
            
        } catch (Exception e) {
            logger.error("Error al guardar estudios", e);
        }
        return object;
    }

    @Override
    public Map<String, Object> editarEstudios(Estudios estudios) throws Exception {
        Map<String, Object> object = null;
        try {
            Estudios ejEstudios = new Estudios();
            ejEstudios.setPersona(new Personas(estudios.getPersona().getId()));
            ejEstudios.setTipoEstudio(new TipoEstudios(estudios.getTipoEstudio().getId()));
            ejEstudios.setActivo("S");

            ejEstudios = this.get(ejEstudios);

            if (ejEstudios != null) {

                ejEstudios.setConcluido(estudios.getConcluido());
                ejEstudios.setFechaFin(estudios.getFechaFin());
                ejEstudios.setFechaInicio(estudios.getFechaInicio());
                ejEstudios.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejEstudios.setIdUsuarioModificacion(estudios.getIdUsuarioModificacion());
                ejEstudios.setNombre(estudios.getNombre());
                ejEstudios.setNumeroRegistro(estudios.getNumeroRegistro());
                ejEstudios.setSemestre(estudios.getSemestre());
                ejEstudios.setTitulo(estudios.getTitulo());

                this.update(ejEstudios);
            } else {

                estudios.setActivo("S");
                estudios.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                estudios.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

                this.save(estudios);
            }

            ejEstudios = new Estudios();
            ejEstudios.setPersona(new Personas(estudios.getPersona().getId()));
            ejEstudios.setTipoEstudio(new TipoEstudios(estudios.getTipoEstudio().getId()));
            ejEstudios.setActivo("S");

            object = this.getEstudios(ejEstudios);
            
        } catch (Exception e) {
            logger.error("Error al editar estudios", e);
        }
        return object;
    }

    @Override
    public Map<String, Object> getEstudios(Estudios estudios) throws Exception {
        String atributos = "id,fechaInicio,fechaFin,titulo,concluido,numeroRegistro,"
                + "semestre,nombre,tipoEstudio.id,activo";

        Map<String, Object> estudiosMaps = this.getAtributos(estudios, atributos.split(","));

        if (estudiosMaps != null) {
            Map<String, Object> tipoOcupaciones = tipoEstudiosManager.getAtributos(new TipoEstudios(Long.parseLong(estudiosMaps.get("tipoEstudio.id").toString())),
                    "id,nombre,descripcion,activo".split(","));
            estudiosMaps.put("tipoEstudio", tipoOcupaciones);
            estudiosMaps.remove("tipoEstudio.id");
        }
        return estudiosMaps;
    }

    @Override
    public List<Map<String, Object>> getListEstudios(Estudios estudios) {
        List<Map<String, Object>> object = new ArrayList<>();
        try {
            List<Map<String, Object>> inmueblesMap = this.listAtributos(estudios, "id".split(","));
            for(Map<String, Object> rpm: inmueblesMap){
                Map<String, Object> map = this.getEstudios(new Estudios(Long.parseLong(rpm.get("id").toString())));
                object.add(map);
            }
        } catch (Exception e) {
            logger.error("Error al  obtener registros", e);
        }
        return object;
    }
}
