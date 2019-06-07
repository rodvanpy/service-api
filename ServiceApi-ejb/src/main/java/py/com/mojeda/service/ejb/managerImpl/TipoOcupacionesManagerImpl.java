/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoOcupaciones;
import py.com.mojeda.service.ejb.manager.TipoOcupacionesManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoOcupacionesManagerImpl extends GenericDaoImpl<TipoOcupaciones, Long>
        implements TipoOcupacionesManager {

    @Override
    protected Class<TipoOcupaciones> getEntityBeanType() {
        return TipoOcupaciones.class;
    }
}
