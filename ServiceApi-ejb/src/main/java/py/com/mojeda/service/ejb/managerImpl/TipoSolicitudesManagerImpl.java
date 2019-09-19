/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoSolicitudes;
import py.com.mojeda.service.ejb.manager.TipoSolicitudesManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoSolicitudesManagerImpl extends GenericDaoImpl<TipoSolicitudes, Long>
        implements TipoSolicitudesManager {

    @Override
    protected Class<TipoSolicitudes> getEntityBeanType() {
        return TipoSolicitudes.class;
    }
}
