/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Paises;
import py.com.mojeda.service.ejb.manager.PaisesManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class PaisesManagerImpl extends GenericDaoImpl<Paises, Long>
        implements PaisesManager {

    @Override
    protected Class<Paises> getEntityBeanType() {
        return Paises.class;
    }
}
