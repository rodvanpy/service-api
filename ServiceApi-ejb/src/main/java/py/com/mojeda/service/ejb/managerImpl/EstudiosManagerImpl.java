/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Estudios;
import py.com.mojeda.service.ejb.manager.EstudiosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class EstudiosManagerImpl extends GenericDaoImpl<Estudios, Long>
        implements EstudiosManager {

    @Override
    protected Class<Estudios> getEntityBeanType() {
        return Estudios.class;
    }
}
