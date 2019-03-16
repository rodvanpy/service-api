/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Empresa;
import py.com.mojeda.service.ejb.manager.EmpresaManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class EmpresaManagerImpl extends GenericDaoImpl<Empresa, Long>
        implements EmpresaManager {

    @Override
    protected Class<Empresa> getEntityBeanType() {
        return Empresa.class;
    }
}
