/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoCargos;
import py.com.mojeda.service.ejb.manager.TipoCargosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoCargosManagerImpl extends GenericDaoImpl<TipoCargos, Long>
        implements TipoCargosManager {

    @Override
    protected Class<TipoCargos> getEntityBeanType() {
        return TipoCargos.class;
    }
}
