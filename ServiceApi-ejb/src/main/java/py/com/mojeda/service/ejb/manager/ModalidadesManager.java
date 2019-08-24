/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Modalidades;


/**
 *
 * @author Miguel
 */
@Local
public interface ModalidadesManager extends GenericDao<Modalidades, Long>{   
    public Map<String, Object> getModalidad(Modalidades modalidades) throws Exception;
}
