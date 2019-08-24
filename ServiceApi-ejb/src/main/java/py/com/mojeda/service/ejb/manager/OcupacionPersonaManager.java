/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.OcupacionPersona;

/**
 *
 * @author Miguel
 */
@Local
public interface OcupacionPersonaManager extends GenericDao<OcupacionPersona, Long>{   
    
    public OcupacionPersona guardarOcupacion(OcupacionPersona ocupacionPersona) throws Exception;
    
    public OcupacionPersona editarOcupacion(OcupacionPersona ocupacionPersona) throws Exception;
    
    public OcupacionPersona getOcupacion(OcupacionPersona ocupacionPersona) throws Exception;
    
    public List<OcupacionPersona> getListOcupaciones(OcupacionPersona ocupacionPersona);
    
}
