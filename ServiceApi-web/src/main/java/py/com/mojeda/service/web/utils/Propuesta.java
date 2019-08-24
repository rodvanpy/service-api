/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.utils;

import java.io.Serializable;
import java.util.List;

import py.com.mojeda.service.ejb.entity.Documentos;
import py.com.mojeda.service.ejb.entity.PropuestaSolicitud;
import py.com.mojeda.service.ejb.utils.Files;

/**
 *
 * @author miguel.ojeda
 */
public class Propuesta implements Serializable {
    
    private PropuestaSolicitud data;
    
    private List<Files> documentos;

    public PropuestaSolicitud getData() {
        return data;
    }

    public void setData(PropuestaSolicitud data) {
        this.data = data;
    }

    public List<Files> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Files> documentos) {
        this.documentos = documentos;
    }

    
    
}
