/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoHorarios;
import py.com.mojeda.service.ejb.manager.TipoHorariosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoHorariosManagerImpl extends GenericDaoImpl<TipoHorarios, Long>
        implements TipoHorariosManager {

    @Override
    protected Class<TipoHorarios> getEntityBeanType() {
        return TipoHorarios.class;
    }
}
