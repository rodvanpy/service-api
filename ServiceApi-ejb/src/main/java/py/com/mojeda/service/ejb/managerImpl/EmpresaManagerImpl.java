/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Barrios;
import py.com.mojeda.service.ejb.entity.Ciudades;
import py.com.mojeda.service.ejb.entity.DepartamentosPais;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Paises;
import py.com.mojeda.service.ejb.manager.BarriosManager;
import py.com.mojeda.service.ejb.manager.CiudadesManager;
import py.com.mojeda.service.ejb.manager.DepartamentosPaisManager;
import py.com.mojeda.service.ejb.manager.EmpresaManager;
import py.com.mojeda.service.ejb.manager.PaisesManager;
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

    @EJB(mappedName = "java:app/ServiceApi-ejb/PaisesManagerImpl")
    private PaisesManager paisesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/DepartamentosPaisManagerImpl")
    private DepartamentosPaisManager departamentosPaisManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/CiudadesManagerImpl")
    private CiudadesManager ciudadesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/BarriosManagerImpl")
    private BarriosManager barriosManager;

    @Override
    public Map<String, Object> guardar(Empresas empresa) throws Exception {
        Empresas object = null;
        Map<String, Object> model = null;
        if (empresa != null) {

            this.save(empresa);

            object = this.get(empresa);

            if (empresa.getAvatar() != null
                    && empresa.getAvatar().getValue() != null) {
                Files.createDirectories(Paths.get(CONTENT_PATH + object.getClassName() + "/" + object.getId()));
                String path = object.getClassName() + "/" + object.getId() + "/" + empresa.getAvatar().getFilename();
                FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
                fos.write(Base64Bytes.decode(empresa.getAvatar().getValue()));
                fos.close();

                object.setImagePath(CONTENT_PATH + path);

                this.update(object);
            }
            model = this.getEmpresa(new Empresas(object.getId()));
        }
        return model;
    }

    @Override
    public Map<String, Object> editar(Empresas empresa) throws Exception {
        Map<String, Object> object = null;
        if (empresa != null) {
            if (empresa.getAvatar() != null
                    && empresa.getAvatar().getValue() != null) {
                Files.createDirectories(Paths.get(CONTENT_PATH + empresa.getClassName() + "/" + empresa.getId()));
                String path = empresa.getClassName() + "/" + empresa.getId() + "/" + empresa.getAvatar().getFilename();
                FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
                fos.write(Base64Bytes.decode(empresa.getAvatar().getValue()));
                fos.close();

                empresa.setImagePath(CONTENT_PATH + path);
            }

            this.update(empresa);

            object = this.getEmpresa(new Empresas(empresa.getId()));
        }
        return object;
    }

    @Override
    public Map<String, Object> getEmpresa(Empresas empresa) throws Exception {
        String atributos = "id,nombre,nombreFantasia,descripcion,ruc,direccion,telefono,fax,telefonoMovil,email,observacion,"
                + "latitud,longitud,activo,pais.id,departamento.id,ciudad.id,barrio.id,imagePath,montoVerificacionCredito";
        Map<String, Object> model = this.getAtributos(empresa, atributos.split(","));
        if (model != null) {
            Map<String, Object> pais = paisesManager.getAtributos(new Paises(Long.parseLong(model.get("pais.id") == null ? "0" : model.get("pais.id").toString())), "id,nombre,activo".split(","));            
            if (pais != null) {
                model.put("pais", pais);
            }
            model.remove("pais.id");

            Map<String, Object> departamento = departamentosPaisManager.getAtributos(new DepartamentosPais(Long.parseLong(model.get("departamento.id") == null ? "0" : model.get("departamento.id").toString())), "id,nombre,activo".split(","));
            if (departamento != null) {
                model.put("departamento", departamento);
            }            
            model.remove("departamento.id");

            Map<String, Object> ciudad = ciudadesManager.getAtributos(new Ciudades(Long.parseLong(model.get("ciudad.id") == null ? "0" : model.get("ciudad.id").toString())), "id,nombre,activo".split(","));           
            if (ciudad != null) {
                model.put("ciudad", ciudad);
            }
            model.remove("ciudad.id");

            Map<String, Object> barrio = barriosManager.getAtributos(new Barrios(Long.parseLong(model.get("barrio.id") == null ? "0" : model.get("barrio.id").toString())), "id,nombre,activo".split(","));
            if (barrio != null) {
                model.put("barrio", barrio);
            }
            model.remove("barrio.id");

        }
        return model;
    }
}
