/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.manager.SucursalManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class SucursalManagerImpl extends GenericDaoImpl<Sucursales, Long>
        implements SucursalManager {

    @Override
    protected Class<Sucursales> getEntityBeanType() {
        return Sucursales.class;
    }
}
