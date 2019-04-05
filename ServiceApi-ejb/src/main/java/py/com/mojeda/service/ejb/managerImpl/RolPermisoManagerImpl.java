/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.RolPermiso;
import py.com.mojeda.service.ejb.manager.RolPermisoManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class RolPermisoManagerImpl extends GenericDaoImpl<RolPermiso, Long>
        implements RolPermisoManager {

    @Override
    protected Class<RolPermiso> getEntityBeanType() {
        return RolPermiso.class;
    }
}
