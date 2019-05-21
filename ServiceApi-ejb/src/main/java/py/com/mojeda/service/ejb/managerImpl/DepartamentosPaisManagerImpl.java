/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.DepartamentosPais;
import py.com.mojeda.service.ejb.manager.DepartamentosPaisManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class DepartamentosPaisManagerImpl extends GenericDaoImpl<DepartamentosPais, Long>
        implements DepartamentosPaisManager {

    @Override
    protected Class<DepartamentosPais> getEntityBeanType() {
        return DepartamentosPais.class;
    }
}
