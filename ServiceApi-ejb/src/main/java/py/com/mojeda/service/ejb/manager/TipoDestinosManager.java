/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.TipoDestinos;


/**
 *
 * @author Miguel
 */
@Local
public interface TipoDestinosManager extends GenericDao<TipoDestinos, Long>{   
    public Map<String, Object> getTipoDestino(TipoDestinos tipoDestinos) throws Exception;
}
