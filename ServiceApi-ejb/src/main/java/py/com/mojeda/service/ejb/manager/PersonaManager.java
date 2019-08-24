/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.entity.IngresosEgresos;
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Referencias;


/**
 *
 * @author Miguel
 */
@Local
public interface PersonaManager extends GenericDao<Personas, Long>{
    
    public Personas guardar(Personas persona) throws Exception;
    
    public Personas editar(Personas persona) throws Exception;
        
    public Map<String, Object> getPersona(Personas persona) throws Exception;
    
    public Personas getPersona(Personas persona, String include) throws Exception;
    
}
