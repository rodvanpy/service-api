/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.manager.BienesManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class BienesManagerImpl extends GenericDaoImpl<Bienes, Long>
        implements BienesManager {

    @Override
    protected Class<Bienes> getEntityBeanType() {
        return Bienes.class;
    }
}
