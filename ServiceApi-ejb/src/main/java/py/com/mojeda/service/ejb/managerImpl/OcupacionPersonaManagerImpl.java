/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.manager.OcupacionPersonaManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class OcupacionPersonaManagerImpl extends GenericDaoImpl<OcupacionPersona, Long>
        implements OcupacionPersonaManager {

    @Override
    protected Class<OcupacionPersona> getEntityBeanType() {
        return OcupacionPersona.class;
    }
}
