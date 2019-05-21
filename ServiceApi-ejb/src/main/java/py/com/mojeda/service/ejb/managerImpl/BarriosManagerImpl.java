/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Barrios;
import py.com.mojeda.service.ejb.manager.BarriosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class BarriosManagerImpl extends GenericDaoImpl<Barrios, Long>
        implements BarriosManager {

    @Override
    protected Class<Barrios> getEntityBeanType() {
        return Barrios.class;
    }
}
