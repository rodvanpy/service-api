/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Imagen;
import py.com.mojeda.service.ejb.manager.ImagenManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;
import py.com.mojeda.service.ejb.utils.Avatar;

/**
 *
 * @author Miguel
 */
@Stateless
public class ImagenManagerImpl extends GenericDaoImpl<Imagen, Long> implements
        ImagenManager {

    @Override
    protected Class<Imagen> getEntityBeanType() {
        return Imagen.class;
    }
    private static final ApplicationLogger logger = ApplicationLogger.getInstance();
    private static final String CONTENT = "C:\\imagen\\";

    @Override
    public boolean guardar(byte[] archivo, String fileName, String fileType,
            String entidad, Long idEntidad, Long idUsuario, Long idEmpresa) {
        try {           
            
            Files.createDirectories(Paths.get(CONTENT + entidad + "\\" + idEntidad));
            String path = entidad + "\\" + idEntidad + "\\" + fileName;
            FileOutputStream fos = new FileOutputStream(CONTENT + path);
            fos.write(archivo);
            fos.close();
            
            Imagen imagen = new Imagen();
            imagen.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            imagen.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            imagen.setIdEntidad(idEntidad);
            imagen.setNombreTabla(entidad);
            imagen.setActivo("S");
            imagen.setPath(CONTENT + path);
            imagen.setNombreArchivo(fileName);
            imagen.setTipoArchivo(fileType);
            imagen.setEmpresa(new Empresas(idEmpresa));
            imagen.setIdUsuarioCreacion(idUsuario);
            
            this.save(imagen);
            
        } catch (Exception e) {
            logger.error("Guardar Imagen: ", e);
        }
        return true;
    }

}
