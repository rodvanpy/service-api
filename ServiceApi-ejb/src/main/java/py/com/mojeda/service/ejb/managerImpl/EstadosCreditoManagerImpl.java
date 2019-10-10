/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.util.Map;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.EstadosCredito;
import py.com.mojeda.service.ejb.manager.EstadosCreditoManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class EstadosCreditoManagerImpl extends GenericDaoImpl<EstadosCredito, Long>
        implements EstadosCreditoManager {

    @Override
    protected Class<EstadosCredito> getEntityBeanType() {
        return EstadosCredito.class;
    }

    @Override
    public Map<String, Object> getEstadoCredito(EstadosCredito estadosCredito) throws Exception {
        return this.getAtributos(estadosCredito, "id,nombre,codigo".split(","));
    }
}
