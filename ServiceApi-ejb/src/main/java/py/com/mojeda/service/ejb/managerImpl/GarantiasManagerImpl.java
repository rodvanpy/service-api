/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Garantias;
import py.com.mojeda.service.ejb.manager.GarantiasManager;



/**
 *
 * @author mojeda
 */
@Stateless
public class GarantiasManagerImpl extends GenericDaoImpl<Garantias, Long>
        implements GarantiasManager {

    @Override
    protected Class<Garantias> getEntityBeanType() {
        return Garantias.class;
    }
}
