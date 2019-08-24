/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;

import java.util.List;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.BienesSolicitudes;


/**
 *
 * @author Miguel
 */
@Local
public interface BienesSolicitudesManager extends GenericDao<BienesSolicitudes, Long>{ 
    
    public BienesSolicitudes guardarBienesSolicitud(BienesSolicitudes bienes) throws Exception;
    
    public BienesSolicitudes editarBienesSolicitud(BienesSolicitudes bienes) throws Exception;
    
    public BienesSolicitudes getBienesSolicitud(BienesSolicitudes bienes) throws Exception;
    
    public List<BienesSolicitudes> getListBienesSolicitud(BienesSolicitudes bienes);
    
}
