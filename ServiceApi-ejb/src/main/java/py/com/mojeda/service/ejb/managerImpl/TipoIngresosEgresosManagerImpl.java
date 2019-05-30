/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoIngresosEgresos;
import py.com.mojeda.service.ejb.manager.TipoIngresosEgresosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoIngresosEgresosManagerImpl extends GenericDaoImpl<TipoIngresosEgresos, Long>
        implements TipoIngresosEgresosManager {

    @Override
    protected Class<TipoIngresosEgresos> getEntityBeanType() {
        return TipoIngresosEgresos.class;
    }
}
