/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Documentos;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;
import py.com.mojeda.service.ejb.utils.Avatar;
import py.com.mojeda.service.ejb.manager.DocumentoManager;

/**
 *
 * @author Miguel
 */
@Stateless
public class DocumentoManagerImpl extends GenericDaoImpl<Documentos, Long> implements
        DocumentoManager {

    @Override
    protected Class<Documentos> getEntityBeanType() {
        return Documentos.class;
    }
    private static final ApplicationLogger logger = ApplicationLogger.getInstance();
    private static final String CONTENT = "/home/image/";

    @Override
    public Documentos guardar(Documentos documento) throws Exception {
        Documentos retorno = null;
        if (documento != null) {
            Files.createDirectories(Paths.get(CONTENT + documento.getNombreTabla() + "/" + documento.getIdEntidad()+ "/" + documento.getTipoDocumento().getCodigo() ));
            String path = documento.getNombreTabla() + "/" + documento.getIdEntidad()+ "/" + documento.getTipoDocumento().getCodigo()  + "/" + documento.getNombreDocumento();
            FileOutputStream fos = new FileOutputStream(CONTENT + path);
            fos.write(documento.getDocumento());
            fos.close();

            documento.setDocumento(null);

            this.save(documento);

            retorno = this.get(documento);
        }

        return retorno;
    }

    @Override
    public Documentos editar(Documentos documento) throws Exception {
        Documentos retorno = null;
        if (documento != null) {
            
            byte[] archivo = documento.getDocumento();
            
            //Se desactiva el documento anterior
            documento.setActivo("N");
            documento.setDocumento(null);
            this.update(documento);
            
            //Nuevo Documento
            documento.setId(null);
            documento.setActivo("S");
            
            Files.createDirectories(Paths.get(CONTENT + documento.getNombreTabla() + "/" + documento.getIdEntidad()+ "/" + documento.getTipoDocumento().getCodigo() ));
            String path = documento.getNombreTabla() + "/" + documento.getIdEntidad()+ "/" + documento.getTipoDocumento().getCodigo()  + "/" + documento.getNombreDocumento();
            FileOutputStream fos = new FileOutputStream(CONTENT + path);
            fos.write(documento.getDocumento());
            fos.close();

            documento.setDocumento(null);

            this.save(documento);

            retorno = this.get(documento);
        }

        return retorno;
    }

}
