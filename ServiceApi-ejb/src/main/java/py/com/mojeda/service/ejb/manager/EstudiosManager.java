/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Estudios;


/**
 *
 * @author Miguel
 */
@Local
public interface EstudiosManager extends GenericDao<Estudios, Long>{   
    
    public Map<String, Object> guardarEstudios(Estudios estudios) throws Exception;
    
    public Map<String, Object> editarEstudios(Estudios estudios) throws Exception;
    
    public Map<String, Object> getEstudios(Estudios estudios) throws Exception;
    
    public List<Map<String, Object>> getListEstudios(Estudios estudios);
}
