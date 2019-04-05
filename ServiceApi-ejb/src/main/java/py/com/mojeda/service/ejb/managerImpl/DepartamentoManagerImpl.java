/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Departamento;
import py.com.mojeda.service.ejb.manager.DepartamentoManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class DepartamentoManagerImpl extends GenericDaoImpl<Departamento, Long>
        implements DepartamentoManager {

    @Override
    protected Class<Departamento> getEntityBeanType() {
        return Departamento.class;
    }
}
