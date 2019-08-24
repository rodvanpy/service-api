/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.List;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.ReferenciasSolicitudes;


/**
 *
 * @author Miguel
 */
@Local
public interface ReferenciaSolicitudesManager extends GenericDao<ReferenciasSolicitudes, Long>{   
    
    public ReferenciasSolicitudes guardarReferenciaSolicitud(ReferenciasSolicitudes referencias) throws Exception;
    
    public ReferenciasSolicitudes editarReferenciaSolicitud(ReferenciasSolicitudes referencias) throws Exception;
    
    public ReferenciasSolicitudes getReferenciaSolicitud(ReferenciasSolicitudes referencias) throws Exception;
    
    public List<ReferenciasSolicitudes> getListReferenciaSolicitud(ReferenciasSolicitudes referencias);
    
}
