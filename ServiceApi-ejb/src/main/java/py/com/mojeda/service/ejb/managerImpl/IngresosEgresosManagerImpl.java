/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.IngresosEgresos;
import py.com.mojeda.service.ejb.manager.IngresosEgresosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class IngresosEgresosManagerImpl extends GenericDaoImpl<IngresosEgresos, Long>
        implements IngresosEgresosManager {

    @Override
    protected Class<IngresosEgresos> getEntityBeanType() {
        return IngresosEgresos.class;
    }
}
