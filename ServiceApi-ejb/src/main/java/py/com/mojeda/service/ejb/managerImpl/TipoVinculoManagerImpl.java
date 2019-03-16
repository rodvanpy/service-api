/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoVinculo;
import py.com.mojeda.service.ejb.manager.TipoVinculoManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoVinculoManagerImpl extends GenericDaoImpl<TipoVinculo, Long>
        implements TipoVinculoManager {

    @Override
    protected Class<TipoVinculo> getEntityBeanType() {
        return TipoVinculo.class;
    }
}
