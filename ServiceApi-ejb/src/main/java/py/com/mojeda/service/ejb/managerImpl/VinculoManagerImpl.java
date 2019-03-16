/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Vinculo;
import py.com.mojeda.service.ejb.manager.VinculoManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class VinculoManagerImpl extends GenericDaoImpl<Vinculo, Long>
        implements VinculoManager {

    @Override
    protected Class<Vinculo> getEntityBeanType() {
        return Vinculo.class;
    }
}
