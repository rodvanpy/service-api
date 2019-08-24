/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
    public IngresosEgresos guardarIngresosEgresos(IngresosEgresos ingresosEgresos) throws Exception {
        IngresosEgresos object = null;
        try {
            IngresosEgresos ejIngresosEgresos = new IngresosEgresos();
            if (ingresosEgresos.getId() == null) {
                ejIngresosEgresos.setActivo("S");
                ejIngresosEgresos.setPersona(ingresosEgresos.getPersona());
                ejIngresosEgresos.setTipoIngresosEgresos(ingresosEgresos.getTipoIngresosEgresos());
                
                ejIngresosEgresos = this.get(ejIngresosEgresos);
                
                if (ejIngresosEgresos != null) {
                    ejIngresosEgresos.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejIngresosEgresos.setIdUsuarioModificacion(ingresosEgresos.getIdUsuarioModificacion());
                    ejIngresosEgresos.setMonto(ingresosEgresos.getMonto());
                    
                    this.update(ejIngresosEgresos);
                } else {
                    this.save(ingresosEgresos);
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
    public IngresosEgresos editarIngresosEgresos(IngresosEgresos ingresosEgresos) throws Exception {
        IngresosEgresos object = null;
        try {
            IngresosEgresos ejIngresosEgresos = new IngresosEgresos();
            if (ingresosEgresos.getId() == null) {
                ejIngresosEgresos.setActivo("S");
                ejIngresosEgresos.setPersona(ingresosEgresos.getPersona());
                ejIngresosEgresos.setTipoIngresosEgresos(ingresosEgresos.getTipoIngresosEgresos());
                
                ejIngresosEgresos = this.get(ejIngresosEgresos);
                
                if (ejIngresosEgresos != null) {
                    ejIngresosEgresos.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejIngresosEgresos.setIdUsuarioModificacion(ingresosEgresos.getIdUsuarioModificacion());
                    ejIngresosEgresos.setMonto(ingresosEgresos.getMonto());
                    
                    this.update(ejIngresosEgresos);
                } else {
                    this.save(ingresosEgresos);
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
    public IngresosEgresos getIngresosEgresos(IngresosEgresos ingresosEgresos) throws Exception {
        IngresosEgresos model = null;
        String atributos = "id,monto,activo,tipoIngresosEgresos.id";

        Map<String, Object> ingresosEgresosMap = this.getAtributos(ingresosEgresos, atributos.split(","));

        if (ingresosEgresosMap != null) {

            model = new IngresosEgresos();
            model.setTipoIngresosEgresos(tipoIngresosEgresosManager.get(new TipoIngresosEgresos(Long.parseLong(ingresosEgresosMap.get("tipoIngresosEgresos.id").toString()))));
            model.setId(Long.parseLong(ingresosEgresosMap.get("id").toString()));
            model.setMonto(ingresosEgresosMap.get("monto") == null ? 0.0 : Double.parseDouble(ingresosEgresosMap.get("monto").toString()));
            model.setActivo(ingresosEgresosMap.get("activo") == null ? "" : ingresosEgresosMap.get("activo").toString());
        }
        return model;
    }

    @Override
    public List<IngresosEgresos> getListIngresosEgresos(IngresosEgresos ingresosEgresos) {
        List<IngresosEgresos> object = new ArrayList<>();
        try {
            List<Map<String, Object>> inmueblesMap = this.listAtributos(ingresosEgresos, "id".split(","));
            for(Map<String, Object> rpm: inmueblesMap){
                IngresosEgresos map = this.getIngresosEgresos(new IngresosEgresos(Long.parseLong(rpm.get("id").toString())));
                object.add(map);
            }
        } catch (Exception e) {
            logger.error("Error al  obtener registros", e);
        }
        return object;
    }
}
