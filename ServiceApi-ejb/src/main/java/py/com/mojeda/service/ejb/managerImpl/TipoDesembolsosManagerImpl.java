/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.util.Map;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoDesembolsos;
import py.com.mojeda.service.ejb.manager.TipoDesembolsosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoDesembolsosManagerImpl extends GenericDaoImpl<TipoDesembolsos, Long>
        implements TipoDesembolsosManager {

    @Override
    protected Class<TipoDesembolsos> getEntityBeanType() {
        return TipoDesembolsos.class;
    }

    @Override
    public Map<String, Object> getTipoDesembolso(TipoDesembolsos tipoDesembolsos) throws Exception {
        return this.getAtributos(tipoDesembolsos, "id,nombre,codigo".split(","));
    }
}
