/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Bienes;


/**
 *
 * @author Miguel
 */
@Local
public interface BienesManager extends GenericDao<Bienes, Long>{ 
    
    public Map<String, Object> guardarBienes(Bienes bien) throws Exception;
    
    public Map<String, Object> editarBienes(Bienes bien) throws Exception;
    
    public Map<String, Object> getBienes(Bienes bien) throws Exception;
}
