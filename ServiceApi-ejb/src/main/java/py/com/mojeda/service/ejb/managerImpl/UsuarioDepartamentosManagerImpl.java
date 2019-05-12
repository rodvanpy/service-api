/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.UsuarioDepartamentos;
import py.com.mojeda.service.ejb.manager.UsuarioDepartamentosManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class UsuarioDepartamentosManagerImpl extends GenericDaoImpl<UsuarioDepartamentos, Long>
        implements UsuarioDepartamentosManager {

    @Override
    protected Class<UsuarioDepartamentos> getEntityBeanType() {
        return UsuarioDepartamentos.class;
    }
}
