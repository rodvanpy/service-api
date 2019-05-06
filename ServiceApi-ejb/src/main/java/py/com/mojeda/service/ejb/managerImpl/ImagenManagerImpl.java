/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Imagen;
import py.com.mojeda.service.ejb.manager.ImagenManager;
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

    @Override
    public boolean guardar(Avatar avatar) {
        try {
            Files.createDirectories(Paths.get(""));

            FileOutputStream fos = new FileOutputStream("");
            //fos.write(archivo);
            fos.close();
        } catch (Exception e) {
        }
        return false;
    }

}
