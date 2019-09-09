/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.BienesSolicitudes;
import py.com.mojeda.service.ejb.entity.Ciudades;
import py.com.mojeda.service.ejb.entity.DepartamentosPais;
import py.com.mojeda.service.ejb.entity.Paises;
import py.com.mojeda.service.ejb.manager.BienesSolicitudesManager;
import py.com.mojeda.service.ejb.manager.CiudadesManager;
import py.com.mojeda.service.ejb.manager.DepartamentosPaisManager;
import py.com.mojeda.service.ejb.manager.PaisesManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;

/**
 *
 * @author Miguel
 */
@Stateless
public class BienesSolicitudesManagerImpl extends GenericDaoImpl<BienesSolicitudes, Long>
        implements BienesSolicitudesManager {

    @Override
    protected Class<BienesSolicitudes> getEntityBeanType() {
        return BienesSolicitudes.class;
    }

    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();
    protected static final DateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy HH:mm");
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/PaisesManagerImpl")
    private PaisesManager paisesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/DepartamentosPaisManagerImpl")
    private DepartamentosPaisManager departamentosPaisManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/CiudadesManagerImpl")
    private CiudadesManager ciudadesManager;

    @Override
    public BienesSolicitudes guardarBienesSolicitud(BienesSolicitudes bienes) throws Exception{
        BienesSolicitudes object = null;
        try {
            if (bienes.getTipoBien().compareToIgnoreCase("INMUEBLE") == 0) {
                if (bienes.getId() != null) {
                    bienes.setMarca(bienes.getMarca() == null ? null : bienes.getMarca().toUpperCase());
                    bienes.setDireccion(bienes.getDireccion() == null ? null : bienes.getDireccion().toUpperCase());
                    bienes.setLugarHipoteca(bienes.getLugarHipoteca() == null ? null : bienes.getLugarHipoteca().toUpperCase());
                    this.update(bienes);
                } else {
                    if (bienes.getMarca() != null) {
                        bienes.setMarca(bienes.getMarca() == null ? null : bienes.getMarca().toUpperCase());
                        bienes.setDireccion(bienes.getDireccion() == null ? null : bienes.getDireccion().toUpperCase());
                        bienes.setLugarHipoteca(bienes.getLugarHipoteca() == null ? null : bienes.getLugarHipoteca().toUpperCase());
                        this.save(bienes);
                    }
                }
            } else {
                if (bienes.getId() != null) {
                    bienes.setMarca(bienes.getMarca() == null ? null : bienes.getMarca().toUpperCase());
                    bienes.setDireccion(bienes.getDireccion() == null ? null : bienes.getDireccion().toUpperCase());
                    bienes.setLugarHipoteca(bienes.getLugarHipoteca() == null ? null : bienes.getLugarHipoteca().toUpperCase());
                    this.update(bienes);
                } else {
                    bienes.setMarca(bienes.getMarca() == null ? null : bienes.getMarca().toUpperCase());
                    bienes.setDireccion(bienes.getDireccion() == null ? null : bienes.getDireccion().toUpperCase());
                    bienes.setLugarHipoteca(bienes.getLugarHipoteca() == null ? null : bienes.getLugarHipoteca().toUpperCase());
                    this.save(bienes);
                }
            }
            object = this.getBienesSolicitud(bienes);
        } catch (Exception e) {
            logger.error("Error al guardar registro", e);
        }
        return object;
    }

    @Override
    public BienesSolicitudes editarBienesSolicitud(BienesSolicitudes bienes) throws Exception {
        BienesSolicitudes object = null;
        try {
            if (bienes.getTipoBien().compareToIgnoreCase("INMUEBLE") == 0) {
                if (bienes.getId() != null) {
                    bienes.setMarca(bienes.getMarca() == null ? null : bienes.getMarca().toUpperCase());
                    bienes.setDireccion(bienes.getDireccion() == null ? null : bienes.getDireccion().toUpperCase());
                    bienes.setLugarHipoteca(bienes.getLugarHipoteca() == null ? null : bienes.getLugarHipoteca().toUpperCase());
                    this.update(bienes);
                } else {
                    bienes.setMarca(bienes.getMarca() == null ? null : bienes.getMarca().toUpperCase());
                    bienes.setDireccion(bienes.getDireccion() == null ? null : bienes.getDireccion().toUpperCase());
                    bienes.setLugarHipoteca(bienes.getLugarHipoteca() == null ? null : bienes.getLugarHipoteca().toUpperCase());
                    this.save(bienes);
                }
            } else {
                if (bienes.getId() != null) {
                    bienes.setMarca(bienes.getMarca() == null ? null : bienes.getMarca().toUpperCase());
                    bienes.setDireccion(bienes.getDireccion() == null ? null : bienes.getDireccion().toUpperCase());
                    bienes.setLugarHipoteca(bienes.getLugarHipoteca() == null ? null : bienes.getLugarHipoteca().toUpperCase());
                    this.update(bienes);
                } else {
                    bienes.setMarca(bienes.getMarca() == null ? null : bienes.getMarca().toUpperCase());
                    bienes.setDireccion(bienes.getDireccion() == null ? null : bienes.getDireccion().toUpperCase());
                    bienes.setLugarHipoteca(bienes.getLugarHipoteca() == null ? null : bienes.getLugarHipoteca().toUpperCase());
                    this.save(bienes);
                }
            }
            object = this.getBienesSolicitud(bienes);
        } catch (Exception e) {
            logger.error("Error al  editar registro", e);
        }
        return object;
    }

    @Override
    public BienesSolicitudes getBienesSolicitud(BienesSolicitudes bienes) throws Exception {
        BienesSolicitudes model = null;
        String atributos = "id,numeroFinca,cuentaCatastral,distrito,escriturado,edificado,hipotecado,fechaHipoteca,vencimientoHipoteca,"
                + "lugarHipoteca,fechaDeclaracion,cuotaMensual,valorActual,saldo,direccion,marca,modeloAnio,fechaVenta,numeroMatricula,"
                + "pais.id,departamento.id,ciudad.id,barrio,tipoBien,latitud,longitud,activo,idBien";

        Map<String, Object> bienesMap = this.getAtributos(bienes, atributos.split(","));

        if (bienesMap != null) {
                        
            model = new BienesSolicitudes();
            model.setId(Long.parseLong(bienesMap.get("id").toString()));
            model.setIdBien(bienesMap.get("idBien") == null ? null : Long.parseLong(bienesMap.get("idBien").toString()));
            model.setNumeroFinca(bienesMap.get("numeroFinca") == null ? "" : bienesMap.get("numeroFinca").toString());
            model.setCuentaCatastral(bienesMap.get("cuentaCatastral") == null ? "" : bienesMap.get("cuentaCatastral").toString());
            model.setDistrito(bienesMap.get("distrito") == null ? "" : bienesMap.get("distrito").toString());
            model.setEscriturado(bienesMap.get("escriturado") == null ? false : Boolean.parseBoolean(bienesMap.get("escriturado").toString()));
            model.setEdificado(bienesMap.get("edificado") == null ? false : Boolean.parseBoolean(bienesMap.get("edificado").toString()));
            model.setHipotecado(bienesMap.get("hipotecado") == null ? false : Boolean.parseBoolean(bienesMap.get("hipotecado").toString()));
            model.setFechaHipoteca(bienesMap.get("fechaHipoteca") == null ? null : dateFormat.parse(bienesMap.get("fechaHipoteca").toString()));
            model.setVencimientoHipoteca(bienesMap.get("vencimientoHipoteca") == null ? null : dateFormat.parse(bienesMap.get("vencimientoHipoteca").toString()));
            model.setLugarHipoteca(bienesMap.get("lugarHipoteca") == null ? "" : bienesMap.get("lugarHipoteca").toString());
            model.setFechaDeclaracion(bienesMap.get("fechaDeclaracion") == null ? null : dateFormat.parse(bienesMap.get("fechaDeclaracion").toString()));
            model.setCuotaMensual(bienesMap.get("cuotaMensual") == null ? 0.0 : Double.parseDouble(bienesMap.get("cuotaMensual").toString()));
            model.setValorActual(bienesMap.get("valorActual") == null ? 0.0 : Double.parseDouble(bienesMap.get("valorActual").toString()));
            model.setSaldo(bienesMap.get("saldo") == null ? 0.0 : Double.parseDouble(bienesMap.get("saldo").toString()));
            model.setDireccion(bienesMap.get("direccion") == null ? "" : bienesMap.get("direccion").toString());
            model.setMarca(bienesMap.get("marca") == null ? "" : bienesMap.get("marca").toString());
            model.setModeloAnio(bienesMap.get("modeloAnio") == null ? "" : bienesMap.get("modeloAnio").toString());
            model.setFechaVenta(bienesMap.get("fechaVenta") == null ? null : dateFormat.parse(bienesMap.get("fechaVenta").toString()));
            model.setNumeroMatricula(bienesMap.get("numeroMatricula") == null ? "" : bienesMap.get("numeroMatricula").toString());
            model.setBarrio(bienesMap.get("barrio") == null ? "" : bienesMap.get("barrio").toString());
            model.setTipoBien(bienesMap.get("tipoBien") == null ? "" : bienesMap.get("tipoBien").toString());
            model.setLatitud(bienesMap.get("latitud") == null ? null : Double.parseDouble(bienesMap.get("latitud").toString()));
            model.setLongitud(bienesMap.get("longitud") == null ? null : Double.parseDouble(bienesMap.get("longitud").toString()));
            model.setActivo(bienesMap.get("activo") == null ? "" : bienesMap.get("activo").toString());            
            model.setPais(bienesMap.get("pais.id") == null ? null:  paisesManager.get(Long.parseLong(bienesMap.get("pais.id").toString())));
            model.setDepartamento(bienesMap.get("departamento.id") == null ? null :  departamentosPaisManager.get(Long.parseLong(bienesMap.get("departamento.id").toString())));
            model.setCiudad(bienesMap.get("ciudad.id") == null ? null :  ciudadesManager.get(Long.parseLong(bienesMap.get("ciudad.id").toString())));
        }
        return model;
    }

    @Override
    public List<BienesSolicitudes> getListBienesSolicitud(BienesSolicitudes bienes) {
        List<BienesSolicitudes> object = new ArrayList<>();
        try {
            List<Map<String, Object>> inmueblesMap = this.listAtributos(bienes, "id".split(","));
            for(Map<String, Object> rpm: inmueblesMap){
                BienesSolicitudes map = this.getBienesSolicitud(new BienesSolicitudes(Long.parseLong(rpm.get("id").toString())));
                object.add(map);
            }
        } catch (Exception e) {
            logger.error("Error al  obtener registros", e);
        }
        return object;
    }
}
