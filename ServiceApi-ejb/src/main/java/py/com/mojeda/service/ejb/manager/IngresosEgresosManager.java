/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.IngresosEgresos;


/**
 *
 * @author Miguel
 */
@Local
public interface IngresosEgresosManager extends GenericDao<IngresosEgresos, Long>{   
    
    public IngresosEgresos guardarIngresosEgresos(IngresosEgresos ingresosEgresos) throws Exception;
    
    public IngresosEgresos editarIngresosEgresos(IngresosEgresos ingresosEgresos) throws Exception;
    
    public IngresosEgresos getIngresosEgresos(IngresosEgresos ingresosEgresos) throws Exception;
    
    public List<IngresosEgresos> getListIngresosEgresos(IngresosEgresos ingresosEgresos);
}
