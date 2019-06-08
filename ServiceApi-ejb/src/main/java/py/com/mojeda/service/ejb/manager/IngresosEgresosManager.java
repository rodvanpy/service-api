/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.IngresosEgresos;


/**
 *
 * @author Miguel
 */
@Local
public interface IngresosEgresosManager extends GenericDao<IngresosEgresos, Long>{   
    public Map<String, Object> guardarIngresosEgresos(IngresosEgresos ingresosEgresos) throws Exception;
    
    public Map<String, Object> editarIngresosEgresos(IngresosEgresos ingresosEgresos) throws Exception;
    
    public Map<String, Object> getIngresosEgresos(IngresosEgresos ingresosEgresos) throws Exception;
}
