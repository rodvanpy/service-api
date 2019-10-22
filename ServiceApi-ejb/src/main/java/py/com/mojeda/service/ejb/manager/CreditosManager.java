/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Creditos;


/**
 *
 * @author Miguel
 */
@Local
public interface CreditosManager extends GenericDao<Creditos, Long>{   
    
    public void generarCredito(Long idSolicitud, Long idEvaluacionCabecera,Long idPersona, Long idEmpresa) throws Exception;
}
