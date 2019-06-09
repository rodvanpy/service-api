/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.entity.Ciudades;
import py.com.mojeda.service.ejb.entity.DepartamentosPais;
import py.com.mojeda.service.ejb.entity.Paises;
import py.com.mojeda.service.ejb.manager.BienesManager;
import py.com.mojeda.service.ejb.manager.CiudadesManager;
import py.com.mojeda.service.ejb.manager.DepartamentosPaisManager;
import py.com.mojeda.service.ejb.manager.PaisesManager;
import static py.com.mojeda.service.ejb.managerImpl.OcupacionPersonaManagerImpl.logger;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;


/**
 *
 * @author Miguel
 */
@Stateless
public class BienesManagerImpl extends GenericDaoImpl<Bienes, Long>
        implements BienesManager {

    @Override
    protected Class<Bienes> getEntityBeanType() {
        return Bienes.class;
    }
    
    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/PaisesManagerImpl")
    private PaisesManager paisesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/DepartamentosPaisManagerImpl")
    private DepartamentosPaisManager departamentosPaisManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/CiudadesManagerImpl")
    private CiudadesManager ciudadesManager;

    @Override
    public Map<String, Object> guardarBienes(Bienes bienes) throws Exception {
        Map<String, Object> object = null;
        try {
            if(bienes.getTipoBien().compareToIgnoreCase("INMUEBLE") == 0){
                if(bienes.getId() != null){
                    this.update(bienes);
                }else{
                    this.save(bienes);
                }
            }else{
                if(bienes.getId() != null){
                    this.update(bienes);
                }else{
                    this.save(bienes);
                }
            }
            object = this.getBienes(bienes);
        } catch (Exception e) {
            logger.error("Error al guardar registro", e);
        }
        return object;
    }

    @Override
    public Map<String, Object> editarBienes(Bienes bienes) throws Exception {
        Map<String, Object> object = null;
        try {
            if(bienes.getTipoBien().compareToIgnoreCase("INMUEBLE") == 0){
                if(bienes.getId() != null){
                    this.update(bienes);
                }else{
                    this.save(bienes);
                }
            }else{
                if(bienes.getId() != null){
                    this.update(bienes);
                }else{
                    this.save(bienes);
                }
            }
            object = this.getBienes(bienes);
        } catch (Exception e) {
            logger.error("Error al  editar registro", e);
        }
        return object;
    }

    @Override
    public Map<String, Object> getBienes(Bienes bienes){
        String atributos = "id,numeroFinca,cuentaCatastral,distrito,escriturado,edificado,hipotecado,fechaHipoteca,vencimientoHipoteca,"
                + "lugarHipoteca,fechaDeclaracion,cuotaMensual,valorActual,saldo,direccion,marca,modeloAnio,fechaVenta,numeroMatricula,"
                + "pais.id,departamento.id,ciudad.id,barrio,tipoBien,activo";

        Map<String, Object> bienMap = this.getAtributos(bienes, atributos.split(","));
        if (bienMap != null) {
            Map<String, Object> pais = paisesManager.getAtributos(new Paises(Long.parseLong(bienMap.get("pais.id") == null ? "0" : bienMap.get("pais.id").toString())), "id,nombre,activo".split(","));
            bienMap.put("pais", pais);
            bienMap.remove("pais.id");

            Map<String, Object> departamento = departamentosPaisManager.getAtributos(new DepartamentosPais(Long.parseLong(bienMap.get("departamento.id") == null ? "0" : bienMap.get("departamento.id").toString())), "id,nombre,activo".split(","));
            bienMap.put("departamento", departamento);
            bienMap.remove("departamento.id");

            Map<String, Object> ciudad = ciudadesManager.getAtributos(new Ciudades(Long.parseLong(bienMap.get("ciudad.id") == null ? "0" : bienMap.get("ciudad.id").toString())), "id,nombre,activo".split(","));
            bienMap.put("ciudad", ciudad);
            bienMap.remove("ciudad.id");
        }
        return bienMap;
    }

    @Override
    public List<Map<String, Object>> getListBienes(Bienes bienes){
        List<Map<String, Object>> object = new ArrayList<>();
        try {
            List<Map<String, Object>> inmueblesMap = this.listAtributos(bienes, "id".split(","));
            for(Map<String, Object> rpm: inmueblesMap){
                Map<String, Object> map = this.getBienes(new Bienes(Long.parseLong(rpm.get("id").toString())));
                object.add(map);
            }
        } catch (Exception e) {
            logger.error("Error al  obtener registros", e);
        }
        return object;
        
    }
}
