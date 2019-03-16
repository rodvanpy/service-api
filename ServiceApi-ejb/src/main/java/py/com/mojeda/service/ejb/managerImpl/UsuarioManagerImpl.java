/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Usuario;
import py.com.mojeda.service.ejb.manager.UsuarioManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class UsuarioManagerImpl extends GenericDaoImpl<Usuario, Long>
        implements UsuarioManager {

    @Override
    protected Class<Usuario> getEntityBeanType() {
        return Usuario.class;
    }
}
