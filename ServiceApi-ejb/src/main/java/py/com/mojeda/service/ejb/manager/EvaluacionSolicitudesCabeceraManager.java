/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.EvaluacionSolicitudesCabecera;


/**
 *
 * @author Miguel
 */
@Local
public interface EvaluacionSolicitudesCabeceraManager extends GenericDao<EvaluacionSolicitudesCabecera, Long>{
    
    public EvaluacionSolicitudesCabecera guardar(EvaluacionSolicitudesCabecera evaluacionSolicitudesCabecera, Long idFuncionario) throws Exception;
    
    public EvaluacionSolicitudesCabecera evaluar(Long idFuncionario, Long idEvaluacion) throws Exception;
    
    public EvaluacionSolicitudesCabecera getEvaluacion(EvaluacionSolicitudesCabecera evaluacion) throws Exception;
}
