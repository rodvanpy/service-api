/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.EstadosCredito;


/**
 *
 * @author Miguel
 */
@Local
public interface EstadosCreditoManager extends GenericDao<EstadosCredito, Long>{   
    public Map<String, Object> getEstadoCredito(EstadosCredito estadosCredito) throws Exception;
}
