/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.PeriodoCapital;
import py.com.mojeda.service.ejb.manager.PeriodoCapitalManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class PeriodoCapitalManagerImpl extends GenericDaoImpl<PeriodoCapital, Long>
        implements PeriodoCapitalManager {

    @Override
    protected Class<PeriodoCapital> getEntityBeanType() {
        return PeriodoCapital.class;
    }
}
