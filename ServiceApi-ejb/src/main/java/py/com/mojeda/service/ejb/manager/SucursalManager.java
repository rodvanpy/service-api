/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Sucursales;


/**
 *
 * @author Miguel
 */
@Local
public interface SucursalManager extends GenericDao<Sucursales, Long>{   
    
    public Map<String, Object> guardar(Sucursales sucursal) throws Exception;
    
    public Map<String, Object> editar(Sucursales sucursal) throws Exception;
        
    public Map<String, Object> getSucursal(Sucursales sucursal) throws Exception;
}
