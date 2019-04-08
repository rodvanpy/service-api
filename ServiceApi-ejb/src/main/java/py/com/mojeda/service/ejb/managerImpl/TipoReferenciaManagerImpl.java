/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoReferencias;
import py.com.mojeda.service.ejb.manager.TipoReferenciaManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoReferenciaManagerImpl extends GenericDaoImpl<TipoReferencias, Long>
        implements TipoReferenciaManager {

    @Override
    protected Class<TipoReferencias> getEntityBeanType() {
        return TipoReferencias.class;
    }
}
