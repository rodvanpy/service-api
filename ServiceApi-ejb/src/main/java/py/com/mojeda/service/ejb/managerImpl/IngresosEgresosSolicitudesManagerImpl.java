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
import py.com.mojeda.service.ejb.entity.IngresosEgresosSolicitudes;
import py.com.mojeda.service.ejb.entity.TipoIngresosEgresos;
import py.com.mojeda.service.ejb.manager.IngresosEgresosSolicitudesManager;
import py.com.mojeda.service.ejb.manager.TipoIngresosEgresosManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;


/**
 *
 * @author Miguel
 */
@Stateless
public class IngresosEgresosSolicitudesManagerImpl extends GenericDaoImpl<IngresosEgresosSolicitudes, Long>
        implements IngresosEgresosSolicitudesManager {

    @Override
    protected Class<IngresosEgresosSolicitudes> getEntityBeanType() {
        return IngresosEgresosSolicitudes.class;
    }
    
    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoIngresosEgresosManagerImpl")
    private TipoIngresosEgresosManager tipoIngresosEgresosManager;

    @Override
    public IngresosEgresosSolicitudes guardarIngresosEgresos(IngresosEgresosSolicitudes ingresosEgresos) throws Exception {
        IngresosEgresosSolicitudes object = null;
        try {
            IngresosEgresosSolicitudes ejIngresosEgresos = new IngresosEgresosSolicitudes();
            if (ingresosEgresos.getId() == null) {
                
                ejIngresosEgresos.setActivo("S");
                ejIngresosEgresos.setPersona(ingresosEgresos.getPersona());
                ejIngresosEgresos.setPropuestaSolicitud(ingresosEgresos.getPropuestaSolicitud());
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
            
            ejIngresosEgresos = new IngresosEgresosSolicitudes();
            ejIngresosEgresos.setPersona(ingresosEgresos.getPersona());
            ejIngresosEgresos.setTipoIngresosEgresos(ingresosEgresos.getTipoIngresosEgresos());
            ejIngresosEgresos.setPropuestaSolicitud(ingresosEgresos.getPropuestaSolicitud());
            
            object = this.get(ejIngresosEgresos);
            
        } catch (Exception e) {
            logger.error("Error al guardar ingreso/egreso", e);
        }
        return object;
    }

    @Override
    public IngresosEgresosSolicitudes editarIngresosEgresos(IngresosEgresosSolicitudes ingresosEgresos) throws Exception {
        IngresosEgresosSolicitudes object = null;
        try {
            IngresosEgresosSolicitudes ejIngresosEgresos = new IngresosEgresosSolicitudes();
            if (ingresosEgresos.getId() == null) {
                
                ejIngresosEgresos.setActivo("S");
                ejIngresosEgresos.setPersona(ingresosEgresos.getPersona());
                ejIngresosEgresos.setPropuestaSolicitud(ingresosEgresos.getPropuestaSolicitud());
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
            
            ejIngresosEgresos = new IngresosEgresosSolicitudes();
            ejIngresosEgresos.setPropuestaSolicitud(ingresosEgresos.getPropuestaSolicitud());
            ejIngresosEgresos.setPersona(ingresosEgresos.getPersona());
            ejIngresosEgresos.setTipoIngresosEgresos(ingresosEgresos.getTipoIngresosEgresos());
            
            object = this.get(ejIngresosEgresos);
            
        } catch (Exception e) {
            logger.error("Error al guardar ingreso/egreso", e);
        }
        return object;
    }

    @Override
    public IngresosEgresosSolicitudes getIngresosEgresos(IngresosEgresosSolicitudes ingresosEgresos) throws Exception {
        IngresosEgresosSolicitudes model = null;
        String atributos = "id,monto,activo,tipoIngresosEgresos.id";

        Map<String, Object> ingresosEgresosMap = this.getAtributos(ingresosEgresos, atributos.split(","));

        if (ingresosEgresosMap != null) {
            model = new IngresosEgresosSolicitudes();
            model.setTipoIngresosEgresos(tipoIngresosEgresosManager.get(new TipoIngresosEgresos(Long.parseLong(ingresosEgresosMap.get("tipoIngresosEgresos.id").toString()))));
            model.setId(Long.parseLong(ingresosEgresosMap.get("id").toString()));
            model.setMonto(ingresosEgresosMap.get("monto") == null ? 0.0 : Double.parseDouble(ingresosEgresosMap.get("monto").toString()));
            model.setActivo(ingresosEgresosMap.get("activo") == null ? "" : ingresosEgresosMap.get("activo").toString());
        }
        return model;
    }

    @Override
    public List<IngresosEgresosSolicitudes> getListIngresosEgresos(IngresosEgresosSolicitudes ingresosEgresos) {
        List<IngresosEgresosSolicitudes> object = new ArrayList<>();
        try {
            List<Map<String, Object>> inmueblesMap = this.listAtributos(ingresosEgresos, "id".split(","));
            for(Map<String, Object> rpm: inmueblesMap){
                IngresosEgresosSolicitudes map = this.getIngresosEgresos(new IngresosEgresosSolicitudes(Long.parseLong(rpm.get("id").toString())));
                object.add(map);
            }
        } catch (Exception e) {
            logger.error("Error al  obtener registros", e);
        }
        return object;
    }

}
