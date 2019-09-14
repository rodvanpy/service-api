/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;

import java.util.List;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.IngresosEgresosSolicitudes;


/**
 *
 * @author Miguel
 */
@Local
public interface IngresosEgresosSolicitudesManager extends GenericDao<IngresosEgresosSolicitudes, Long>{   
    
    public IngresosEgresosSolicitudes guardarIngresosEgresos(IngresosEgresosSolicitudes ingresosEgresos) throws Exception;
    
    public IngresosEgresosSolicitudes editarIngresosEgresos(IngresosEgresosSolicitudes ingresosEgresos) throws Exception;
    
    public IngresosEgresosSolicitudes getIngresosEgresos(IngresosEgresosSolicitudes ingresosEgresos) throws Exception;
    
    public List<IngresosEgresosSolicitudes> getListIngresosEgresos(IngresosEgresosSolicitudes ingresosEgresos);
}
