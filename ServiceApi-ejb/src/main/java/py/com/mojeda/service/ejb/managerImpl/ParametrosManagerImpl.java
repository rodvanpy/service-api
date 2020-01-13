/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Parametros;
import py.com.mojeda.service.ejb.manager.ParametrosManager;



/**
 *
 * @author Miguel
 */
@Stateless()
public class ParametrosManagerImpl extends GenericDaoImpl<Parametros, Long>
        implements ParametrosManager {

    @Override
    protected Class<Parametros> getEntityBeanType() {
        return Parametros.class;
    }

    

}
