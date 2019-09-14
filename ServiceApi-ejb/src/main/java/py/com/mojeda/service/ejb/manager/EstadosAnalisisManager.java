/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.EstadosAnalisis;


/**
 *
 * @author Miguel
 */
@Local
public interface EstadosAnalisisManager extends GenericDao<EstadosAnalisis, Long>{   
    public Map<String, Object> getEstadosSolicitud(EstadosAnalisis estadosSolicitud) throws Exception;
}
