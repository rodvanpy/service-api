/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.util.Map;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoCalculos;
import py.com.mojeda.service.ejb.manager.TipoCalculosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoCalculosManagerImpl extends GenericDaoImpl<TipoCalculos, Long>
        implements TipoCalculosManager {

    @Override
    protected Class<TipoCalculos> getEntityBeanType() {
        return TipoCalculos.class;
    }

    @Override
    public Map<String, Object> getTipoCalculo(TipoCalculos tipoCalculos) throws Exception {       
        return this.getAtributos(tipoCalculos, "id,nombre,codigo".split(","));
    }
}
