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
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.manager.EmpresaManager;
import py.com.mojeda.service.ejb.utils.Base64Bytes;
import static py.com.mojeda.service.ejb.utils.Constants.CONTENT_PATH;

/**
 *
 * @author Miguel
 */
@Stateless
public class EmpresaManagerImpl extends GenericDaoImpl<Empresas, Long>
        implements EmpresaManager {

    @Override
    protected Class<Empresas> getEntityBeanType() {
        return Empresas.class;
    }

    @Override
    public Empresas guardar(Empresas empresa) throws Exception {
        Empresas object = null;
        if (empresa != null) {

            this.save(empresa);

            object = this.get(empresa);

            if (empresa.getAvatar() != null) {
                Files.createDirectories(Paths.get(CONTENT_PATH + object.getClassName() + "/" + object.getId()));
                String path = object.getClassName() + "/" + object.getId() + "/" + empresa.getAvatar().getFilename();
                FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
                fos.write(Base64Bytes.decode(empresa.getAvatar().getValue()));
                fos.close();

                object.setImagePath(CONTENT_PATH + path);

                this.update(object);
            }
        }
        return object;
    }

    @Override
    public Empresas editar(Empresas empresa) throws Exception {
        Empresas object = null;
        if (empresa != null) {
            if (empresa.getAvatar() != null) {
                Files.createDirectories(Paths.get(CONTENT_PATH + empresa.getClassName() + "/" + empresa.getId()));
                String path = empresa.getClassName() + "/" + empresa.getId() + "/" + empresa.getAvatar().getFilename();
                FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
                fos.write(Base64Bytes.decode(empresa.getAvatar().getValue()));
                fos.close();

                empresa.setImagePath(CONTENT_PATH + path);
            }

            this.update(empresa);
            object = this.get(empresa);
        }
        return object;
    }
}
