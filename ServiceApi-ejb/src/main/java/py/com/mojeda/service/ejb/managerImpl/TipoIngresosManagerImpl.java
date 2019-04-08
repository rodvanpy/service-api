/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoIngresos;
import py.com.mojeda.service.ejb.manager.TipoIngresosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoIngresosManagerImpl extends GenericDaoImpl<TipoIngresos, Long>
        implements TipoIngresosManager {

    @Override
    protected Class<TipoIngresos> getEntityBeanType() {
        return TipoIngresos.class;
    }
}
