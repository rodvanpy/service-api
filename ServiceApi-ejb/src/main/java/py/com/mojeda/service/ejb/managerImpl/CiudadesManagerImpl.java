/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Ciudades;
import py.com.mojeda.service.ejb.manager.CiudadesManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class CiudadesManagerImpl extends GenericDaoImpl<Ciudades, Long>
        implements CiudadesManager {

    @Override
    protected Class<Ciudades> getEntityBeanType() {
        return Ciudades.class;
    }
}
