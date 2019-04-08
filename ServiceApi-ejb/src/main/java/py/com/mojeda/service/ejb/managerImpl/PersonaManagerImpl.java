/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.manager.PersonaManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class PersonaManagerImpl extends GenericDaoImpl<Personas, Long>
        implements PersonaManager {

    @Override
    protected Class<Personas> getEntityBeanType() {
        return Personas.class;
    }
}
