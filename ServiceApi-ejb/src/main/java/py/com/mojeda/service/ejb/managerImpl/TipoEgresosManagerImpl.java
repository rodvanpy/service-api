/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoEgresos;
import py.com.mojeda.service.ejb.manager.TipoEgresosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoEgresosManagerImpl extends GenericDaoImpl<TipoEgresos, Long>
        implements TipoEgresosManager {

    @Override
    protected Class<TipoEgresos> getEntityBeanType() {
        return TipoEgresos.class;
    }
}
