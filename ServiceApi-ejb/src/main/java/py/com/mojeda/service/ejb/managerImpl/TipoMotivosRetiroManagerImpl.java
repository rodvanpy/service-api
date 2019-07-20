/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoMotivosRetiro;
import py.com.mojeda.service.ejb.manager.TipoMotivosRetiroManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoMotivosRetiroManagerImpl extends GenericDaoImpl<TipoMotivosRetiro, Long>
        implements TipoMotivosRetiroManager {

    @Override
    protected Class<TipoMotivosRetiro> getEntityBeanType() {
        return TipoMotivosRetiro.class;
    }
}
