/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.sql.Timestamp;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.IngresosEgresos;
import py.com.mojeda.service.ejb.entity.TipoIngresosEgresos;
import py.com.mojeda.service.ejb.manager.IngresosEgresosManager;
import py.com.mojeda.service.ejb.manager.TipoIngresosEgresosManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;


/**
 *
 * @author Miguel
 */
@Stateless
public class IngresosEgresosManagerImpl extends GenericDaoImpl<IngresosEgresos, Long>
        implements IngresosEgresosManager {

    @Override
    protected Class<IngresosEgresos> getEntityBeanType() {
        return IngresosEgresos.class;
    }
    
    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoIngresosEgresosManagerImpl")
    private TipoIngresosEgresosManager tipoIngresosEgresosManager;

    @Override
    public Map<String, Object> guardarIngresosEgresos(IngresosEgresos ingresosEgresos) throws Exception {
        Map<String, Object> object = null;
        try {
            IngresosEgresos ejIngresosEgresos = new IngresosEgresos();
            if (ingresosEgresos.getId() == null) {
                ejIngresosEgresos.setPersona(ingresosEgresos.getPersona());
                ejIngresosEgresos.setTipoIngresosEgresos(ingresosEgresos.getTipoIngresosEgresos());
                ejIngresosEgresos = this.get(ejIngresosEgresos);
                if (ejIngresosEgresos != null) {
                    ejIngresosEgresos.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejIngresosEgresos.setIdUsuarioModificacion(ingresosEgresos.getIdUsuarioModificacion());
                    ejIngresosEgresos.setMonto(ingresosEgresos.getMonto());
                    
                    this.update(ejIngresosEgresos);
                } else {
                    this.save(ejIngresosEgresos);
                }
            } else {

                ejIngresosEgresos = this.get(ingresosEgresos.getId());
                ejIngresosEgresos.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejIngresosEgresos.setIdUsuarioModificacion(ingresosEgresos.getIdUsuarioModificacion());
                ejIngresosEgresos.setMonto(ingresosEgresos.getMonto());

                this.update(ejIngresosEgresos);
            }
            
            ejIngresosEgresos = new IngresosEgresos();
            ejIngresosEgresos.setPersona(ingresosEgresos.getPersona());
            ejIngresosEgresos.setTipoIngresosEgresos(ingresosEgresos.getTipoIngresosEgresos());
            
            object = this.getIngresosEgresos(ejIngresosEgresos);
            
        } catch (Exception e) {
            logger.error("Error al guardar ingreso/egreso", e);
        }
        return object;
    }

    @Override
    public Map<String, Object> editarIngresosEgresos(IngresosEgresos ingresosEgresos) throws Exception {
        Map<String, Object> object = null;
        try {
            IngresosEgresos ejIngresosEgresos = new IngresosEgresos();
            if (ingresosEgresos.getId() == null) {
                ejIngresosEgresos.setPersona(ingresosEgresos.getPersona());
                ejIngresosEgresos.setTipoIngresosEgresos(ingresosEgresos.getTipoIngresosEgresos());
                ejIngresosEgresos = this.get(ejIngresosEgresos);
                if (ejIngresosEgresos != null) {
                    ejIngresosEgresos.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejIngresosEgresos.setIdUsuarioModificacion(ingresosEgresos.getIdUsuarioModificacion());
                    ejIngresosEgresos.setMonto(ingresosEgresos.getMonto());
                    
                    this.update(ejIngresosEgresos);
                } else {
                    this.save(ejIngresosEgresos);
                }
            } else {

                ejIngresosEgresos = this.get(ingresosEgresos.getId());
                ejIngresosEgresos.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejIngresosEgresos.setIdUsuarioModificacion(ingresosEgresos.getIdUsuarioModificacion());
                ejIngresosEgresos.setMonto(ingresosEgresos.getMonto());

                this.update(ejIngresosEgresos);
            }
            
            ejIngresosEgresos = new IngresosEgresos();
            ejIngresosEgresos.setPersona(ingresosEgresos.getPersona());
            ejIngresosEgresos.setTipoIngresosEgresos(ingresosEgresos.getTipoIngresosEgresos());
            
            object = this.getIngresosEgresos(ejIngresosEgresos);
            
        } catch (Exception e) {
            logger.error("Error al guardar ingreso/egreso", e);
        }
        return object;
    }

    @Override
    public Map<String, Object> getIngresosEgresos(IngresosEgresos ingresosEgresos) throws Exception {
        String atributos = "id,monto,activo,tipoIngresosEgresos.id";

        Map<String, Object> ingresosEgresosMap = this.getAtributos(ingresosEgresos, atributos.split(","));

        if (ingresosEgresosMap != null) {
            Map<String, Object> tipoOcupaciones = tipoIngresosEgresosManager.getAtributos(new TipoIngresosEgresos(Long.parseLong(ingresosEgresosMap.get("tipoIngresosEgresos.id").toString())),
                    "id,nombre,activo".split(","));
            ingresosEgresosMap.put("tipoIngresosEgresos", tipoOcupaciones);
            ingresosEgresosMap.remove("tipoIngresosEgresos.id");
        }
        return ingresosEgresosMap;
    }
}
