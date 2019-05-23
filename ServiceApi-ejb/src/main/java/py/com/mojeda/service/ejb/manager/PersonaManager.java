/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Personas;


/**
 *
 * @author Miguel
 */
@Local
public interface PersonaManager extends GenericDao<Personas, Long>{
    
    public Personas guardar(Personas usuario) throws Exception;
    
    public Personas editar(Personas usuario) throws Exception;
    
    public Map<String, Object> getPersona(Long id) throws Exception;
    
}
