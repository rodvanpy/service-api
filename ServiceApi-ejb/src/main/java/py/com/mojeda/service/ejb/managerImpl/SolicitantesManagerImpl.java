/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Solicitantes;
import py.com.mojeda.service.ejb.manager.SolicitantesManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class SolicitantesManagerImpl extends GenericDaoImpl<Solicitantes, Long>
        implements SolicitantesManager {

    @Override
    protected Class<Solicitantes> getEntityBeanType() {
        return Solicitantes.class;
    }
}
