/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Modalidades;
import py.com.mojeda.service.ejb.manager.ModalidadesManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class ModalidadesManagerImpl extends GenericDaoImpl<Modalidades, Long>
        implements ModalidadesManager {

    @Override
    protected Class<Modalidades> getEntityBeanType() {
        return Modalidades.class;
    }
}
