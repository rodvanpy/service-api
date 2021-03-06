/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoVinculos;
import py.com.mojeda.service.ejb.manager.TipoVinculoManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoVinculoManagerImpl extends GenericDaoImpl<TipoVinculos, Long>
        implements TipoVinculoManager {

    @Override
    protected Class<TipoVinculos> getEntityBeanType() {
        return TipoVinculos.class;
    }
}
