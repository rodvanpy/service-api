/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Profesiones;
import py.com.mojeda.service.ejb.manager.ProfesionesManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class ProfesionesManagerImpl extends GenericDaoImpl<Profesiones, Long>
        implements ProfesionesManager {

    @Override
    protected Class<Profesiones> getEntityBeanType() {
        return Profesiones.class;
    }
}
