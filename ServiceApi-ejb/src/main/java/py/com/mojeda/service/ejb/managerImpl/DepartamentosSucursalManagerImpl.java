/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.DepartamentosSucursal;
import py.com.mojeda.service.ejb.manager.DepartamentosSucursalManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class DepartamentosSucursalManagerImpl extends GenericDaoImpl<DepartamentosSucursal, Long>
        implements DepartamentosSucursalManager {

    @Override
    protected Class<DepartamentosSucursal> getEntityBeanType() {
        return DepartamentosSucursal.class;
    }
}
