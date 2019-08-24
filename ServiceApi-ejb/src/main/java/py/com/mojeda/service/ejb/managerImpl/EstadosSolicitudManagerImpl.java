/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.util.Map;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.EstadosSolicitud;
import py.com.mojeda.service.ejb.manager.EstadosSolicitudManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class EstadosSolicitudManagerImpl extends GenericDaoImpl<EstadosSolicitud, Long>
        implements EstadosSolicitudManager {

    @Override
    protected Class<EstadosSolicitud> getEntityBeanType() {
        return EstadosSolicitud.class;
    }

    @Override
    public Map<String, Object> getEstadosSolicitud(EstadosSolicitud estadosSolicitud) throws Exception {
        return this.getAtributos(estadosSolicitud, "id,nombre,codigo".split(","));
    }
}
