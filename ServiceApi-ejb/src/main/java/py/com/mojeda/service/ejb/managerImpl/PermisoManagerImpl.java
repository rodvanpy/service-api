/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Permisos;
import py.com.mojeda.service.ejb.manager.PermisoManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class PermisoManagerImpl extends GenericDaoImpl<Permisos, Long>
        implements PermisoManager {

    @Override
    protected Class<Permisos> getEntityBeanType() {
        return Permisos.class;
    }
}
