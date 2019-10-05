/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.SituacionesCredito;
import py.com.mojeda.service.ejb.manager.SituacionesCreditoManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class SituacionesCreditoManagerImpl extends GenericDaoImpl<SituacionesCredito, Long>
        implements SituacionesCreditoManager {

    @Override
    protected Class<SituacionesCredito> getEntityBeanType() {
        return SituacionesCredito.class;
    }
}
