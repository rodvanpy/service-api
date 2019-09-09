/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.manager;


import javax.ejb.Local;
import org.springframework.web.multipart.MultipartFile;
import py.com.mojeda.service.ejb.entity.Documentos;
import py.com.mojeda.service.ejb.utils.Avatar;

/**
 *
 * @author Miguel
 */
@Local
public interface DocumentoManager extends GenericDao<Documentos, Long> {
         
    public Documentos guardar(Documentos documento, MultipartFile file) throws Exception;
    
    public Documentos editar(Documentos documento, MultipartFile file) throws Exception;
}
