/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Rol;
import py.com.mojeda.service.ejb.manager.RolManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class RolManagerImpl extends GenericDaoImpl<Rol, Long>
        implements RolManager {

    @Override
    protected Class<Rol> getEntityBeanType() {
        return Rol.class;
    }
}
