/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoGarantias;
import py.com.mojeda.service.ejb.manager.TipoGarantiasManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoGarantiasManagerImpl extends GenericDaoImpl<TipoGarantias, Long>
        implements TipoGarantiasManager {

    @Override
    protected Class<TipoGarantias> getEntityBeanType() {
        return TipoGarantias.class;
    }
}
