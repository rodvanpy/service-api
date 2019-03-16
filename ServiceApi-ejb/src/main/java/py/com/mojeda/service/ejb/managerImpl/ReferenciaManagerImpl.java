/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Referencia;
import py.com.mojeda.service.ejb.manager.ReferenciaManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class ReferenciaManagerImpl extends GenericDaoImpl<Referencia, Long>
        implements ReferenciaManager {

    @Override
    protected Class<Referencia> getEntityBeanType() {
        return Referencia.class;
    }
}
