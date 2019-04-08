/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.TipoGarantias;


/**
 *
 * @author Miguel
 */
@Local
public interface TipoGarantiasManager extends GenericDao<TipoGarantias, Long>{   
    
}
