/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Vinculos;


/**
 *
 * @author Miguel
 */
@Local
public interface VinculoManager extends GenericDao<Vinculos, Long>{ 
    
    public Map<String, Object> guardar(Vinculos vinculo) throws Exception;
    
    public Map<String, Object> editar(Vinculos vinculo) throws Exception;
    
    public Map<String, Object> getVinculo(Vinculos vinculo) throws Exception;
    
}
