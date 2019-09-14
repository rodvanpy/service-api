/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.util.Map;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.EstadosAnalisis;
import py.com.mojeda.service.ejb.manager.EstadosAnalisisManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class EstadosAnalisisManagerImpl extends GenericDaoImpl<EstadosAnalisis, Long>
        implements EstadosAnalisisManager {

    @Override
    protected Class<EstadosAnalisis> getEntityBeanType() {
        return EstadosAnalisis.class;
    }

    @Override
    public Map<String, Object> getEstadosSolicitud(EstadosAnalisis estadosSolicitud) throws Exception {
        return this.getAtributos(estadosSolicitud, "id,nombre,codigo".split(","));
    }
}
