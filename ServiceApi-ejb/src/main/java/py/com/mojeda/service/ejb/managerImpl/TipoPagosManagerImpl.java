/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.util.Map;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoPagos;
import py.com.mojeda.service.ejb.manager.TipoPagosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoPagosManagerImpl extends GenericDaoImpl<TipoPagos, Long>
        implements TipoPagosManager {

    @Override
    protected Class<TipoPagos> getEntityBeanType() {
        return TipoPagos.class;
    }

    @Override
    public Map<String, Object> getTipoPago(TipoPagos tipoPagos) throws Exception {
        return this.getAtributos(tipoPagos, "id,nombre,codigo".split(","));
    }
}
