/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.PropuestaSolicitud;
import py.com.mojeda.service.ejb.manager.PropuestaSolicitudManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class PropuestaSolicitudManagerImpl extends GenericDaoImpl<PropuestaSolicitud, Long>
        implements PropuestaSolicitudManager {

    @Override
    protected Class<PropuestaSolicitud> getEntityBeanType() {
        return PropuestaSolicitud.class;
    }
}
