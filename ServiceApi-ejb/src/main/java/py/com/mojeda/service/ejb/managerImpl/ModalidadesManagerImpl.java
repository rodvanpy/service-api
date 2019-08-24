/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Modalidades;
import py.com.mojeda.service.ejb.entity.TipoCalculos;
import py.com.mojeda.service.ejb.manager.ModalidadesManager;
import py.com.mojeda.service.ejb.manager.TipoCalculosManager;

/**
 *
 * @author Miguel
 */
@Stateless
public class ModalidadesManagerImpl extends GenericDaoImpl<Modalidades, Long>
        implements ModalidadesManager {

    @Override
    protected Class<Modalidades> getEntityBeanType() {
        return Modalidades.class;
    }

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoCalculosManagerImpl")
    private TipoCalculosManager tipoCalculosManager;

    @Override
    public Map<String, Object> getModalidad(Modalidades modalidades) throws Exception {
        Map<String, Object> modalidad = this.getAtributos(modalidades, "id,nombre,codigo,montoMaximo,montoMinimo,interes,descripcion,tipoCalculos.id".split(","));
        if (modalidad != null) {
            modalidad.put("tipoCalculos", tipoCalculosManager.getTipoCalculo(new TipoCalculos(Long.parseLong(modalidad.get("tipoCalculos.id") + ""))));
            modalidad.remove("tipoCalculos.id");
        }
        return modalidad;
    }
}
