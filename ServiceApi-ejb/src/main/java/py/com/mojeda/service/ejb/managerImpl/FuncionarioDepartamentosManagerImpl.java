/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.FuncionariosDepartamentos;
import py.com.mojeda.service.ejb.manager.FuncionarioDepartamentosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class FuncionarioDepartamentosManagerImpl extends GenericDaoImpl<FuncionariosDepartamentos, Long>
        implements FuncionarioDepartamentosManager {

    @Override
    protected Class<FuncionariosDepartamentos> getEntityBeanType() {
        return FuncionariosDepartamentos.class;
    }
}
