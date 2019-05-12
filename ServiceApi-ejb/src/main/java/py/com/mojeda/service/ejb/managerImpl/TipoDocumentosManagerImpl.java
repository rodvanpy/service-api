/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoDocumentos;
import py.com.mojeda.service.ejb.manager.TipoDocumentosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoDocumentosManagerImpl extends GenericDaoImpl<TipoDocumentos, Long>
        implements TipoDocumentosManager {

    @Override
    protected Class<TipoDocumentos> getEntityBeanType() {
        return TipoDocumentos.class;
    }
}
