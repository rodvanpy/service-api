/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.TipoFuncionarios;
import py.com.mojeda.service.ejb.manager.TipoFuncionariosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class TipoFuncionariosManagerImpl extends GenericDaoImpl<TipoFuncionarios, Long>
        implements TipoFuncionariosManager {

    @Override
    protected Class<TipoFuncionarios> getEntityBeanType() {
        return TipoFuncionarios.class;
    }
}
