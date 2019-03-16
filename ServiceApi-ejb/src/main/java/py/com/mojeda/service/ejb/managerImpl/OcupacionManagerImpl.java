/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Ocupacion;
import py.com.mojeda.service.ejb.manager.OcupacionManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class OcupacionManagerImpl extends GenericDaoImpl<Ocupacion, Long>
        implements OcupacionManager {

    @Override
    protected Class<Ocupacion> getEntityBeanType() {
        return Ocupacion.class;
    }
}
