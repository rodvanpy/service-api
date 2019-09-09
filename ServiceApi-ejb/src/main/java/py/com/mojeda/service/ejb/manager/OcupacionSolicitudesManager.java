/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.List;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.OcupacionSolicitudes;

/**
 *
 * @author Miguel
 */
@Local
public interface OcupacionSolicitudesManager extends GenericDao<OcupacionSolicitudes, Long>{   
    
    public OcupacionSolicitudes guardarOcupacionSolicitudes(OcupacionSolicitudes ocupacionSolicitudes) throws Exception;
    
    public OcupacionSolicitudes editarOcupacionSolicitudes(OcupacionSolicitudes ocupacionSolicitudes) throws Exception;
    
    public OcupacionSolicitudes getOcupacionSolicitud(OcupacionSolicitudes ocupacionPersona) throws Exception;
    
    public List<OcupacionSolicitudes> getListOcupacionSolicitudes(OcupacionSolicitudes ocupacionPersona);
}
