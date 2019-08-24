/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.utils;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import py.com.mojeda.service.ejb.entity.Documentos;

/**
 *
 * @author miguel.ojeda
 */
public class Files extends Documentos {

    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
    
    
}
