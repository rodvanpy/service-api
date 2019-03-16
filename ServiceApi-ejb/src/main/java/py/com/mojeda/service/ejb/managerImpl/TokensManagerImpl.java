/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Tokens;
import py.com.mojeda.service.ejb.manager.TokensManager;



/**
 *
 * @author mojeda
 */
@Stateless
public class TokensManagerImpl extends GenericDaoImpl<Tokens, Long>
        implements TokensManager {

    @Override
    protected Class<Tokens> getEntityBeanType() {
        return Tokens.class;
    }
}
