/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoEstudios;
import py.com.mojeda.service.ejb.manager.TipoEstudiosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoEstudiosManagerImpl extends GenericDaoImpl<TipoEstudios, Long>
        implements TipoEstudiosManager {

    @Override
    protected Class<TipoEstudios> getEntityBeanType() {
        return TipoEstudios.class;
    }
}
