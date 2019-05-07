/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.manager;


import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Imagen;
import py.com.mojeda.service.ejb.utils.Avatar;

/**
 *
 * @author Miguel
 */
@Local
public interface ImagenManager extends GenericDao<Imagen, Long> {
         
    public boolean guardar(byte[] archivo, String fileName, String fileType,
            String entidad, Long idEntidad, Long idUsuario, Long idEmpresa);
}
