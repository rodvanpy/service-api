/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Nacionalidades;
import py.com.mojeda.service.ejb.manager.NacionalidadesManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class NacionalidadesManagerImpl extends GenericDaoImpl<Nacionalidades, Long>
        implements NacionalidadesManager {

    @Override
    protected Class<Nacionalidades> getEntityBeanType() {
        return Nacionalidades.class;
    }
}
