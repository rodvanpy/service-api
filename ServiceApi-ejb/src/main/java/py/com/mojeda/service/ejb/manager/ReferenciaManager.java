/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Referencias;


/**
 *
 * @author Miguel
 */
@Local
public interface ReferenciaManager extends GenericDao<Referencias, Long>{   
    
    public Map<String, Object> guardarReferencia(Referencias referencias) throws Exception;
    
    public Map<String, Object> editarReferencia(Referencias referencias) throws Exception;
    
    public Map<String, Object> getReferencia(Referencias referencias) throws Exception;
}
