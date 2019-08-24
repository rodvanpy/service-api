/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.util.Map;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoDestinos;
import py.com.mojeda.service.ejb.manager.TipoDestinosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoDestinosManagerImpl extends GenericDaoImpl<TipoDestinos, Long>
        implements TipoDestinosManager {

    @Override
    protected Class<TipoDestinos> getEntityBeanType() {
        return TipoDestinos.class;
    }

    @Override
    public Map<String, Object> getTipoDestino(TipoDestinos tipoDestinos) throws Exception {
        return this.getAtributos(tipoDestinos, "id,nombre,codigo".split(","));
    }
}
