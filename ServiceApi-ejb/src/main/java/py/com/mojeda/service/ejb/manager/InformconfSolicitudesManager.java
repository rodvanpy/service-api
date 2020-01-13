/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.InformconfSolicitudes;
import py.com.mojeda.service.ejb.informconf.InformconfPersonas;
import py.com.mojeda.service.ejb.utils.ResponseDTO;


/**
 *
 * @author Miguel
 */
@Local
public interface InformconfSolicitudesManager extends GenericDao<InformconfSolicitudes, Long>{   
    
    public ResponseDTO<InformconfPersonas> get(String tipo, String documento, Long nroSolicitud, Long idPersona,
            Long idEmpresa, Long idFuncionario, String estado, boolean reload)throws Exception;
    
    public void asyncService(String tipo, String documento, Long nroSolicitud, Long idPersona,
            Long idEmpresa, Long idFuncionario, String estado, Long idSolicitud)throws Exception;
}
