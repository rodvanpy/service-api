/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Usuarios;


/**
 *
 * @author Miguel
 */
@Local
public interface UsuarioManager extends GenericDao<Usuarios, Long>{  
    
    public Map<String, Object> guardar(Usuarios usuario) throws Exception;
    
    public Map<String, Object> editar(Usuarios usuario) throws Exception;
    
    public Map<String, Object> getUsuario(Usuarios usuario) throws Exception;
    
}
