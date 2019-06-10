/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.entity.Clientes;
import py.com.mojeda.service.ejb.entity.Documentos;
import py.com.mojeda.service.ejb.entity.IngresosEgresos;
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Referencias;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.TipoIngresosEgresos;
import py.com.mojeda.service.ejb.manager.BienesManager;
import py.com.mojeda.service.ejb.manager.ClientesManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.DocumentoManager;
import py.com.mojeda.service.ejb.manager.IngresosEgresosManager;
import py.com.mojeda.service.ejb.manager.OcupacionPersonaManager;
import py.com.mojeda.service.ejb.manager.ReferenciaManager;
import py.com.mojeda.service.ejb.manager.SucursalManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class ClientesManagerImpl extends GenericDaoImpl<Clientes, Long>
        implements ClientesManager {

    @Override
    protected Class<Clientes> getEntityBeanType() {
        return Clientes.class;
    }

    @EJB(mappedName = "java:app/ServiceApi-ejb/PersonaManagerImpl")
    private PersonaManager personaManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/DocumentoManagerImpl")
    private DocumentoManager documentoManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/SucursalManagerImpl")
    private SucursalManager sucursalManager;
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/BienesManagerImpl")
    private BienesManager bienesManager;
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/OcupacionPersonaManagerImpl")
    private OcupacionPersonaManager ocupacionPersonaManager;
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/ReferenciaManagerImpl")
    private ReferenciaManager referenciaManager;
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/IngresosEgresosManagerImpl")
    private IngresosEgresosManager ingresosEgresosManager;
  

    @Override
    public Map<String, Object> guardar(Clientes cliente) throws Exception {
        Map<String, Object> object = null;
        Documentos ejDocumentos = null;
        if (cliente != null
                && cliente.getPersona() != null) {

            cliente.getPersona().setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
            cliente.getPersona().setIdUsuarioCreacion(cliente.getIdUsuarioCreacion());

            Personas ejPersona = personaManager.guardar(cliente.getPersona());

            cliente.setPersona(new Personas(ejPersona.getId()));

            this.save(cliente);
            
            for (Bienes rpm : cliente.getBienesInmuebles()) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(cliente.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    Map<String, Object> responseMaps = bienesManager.guardarBienes(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    
                    Map<String, Object> responseMaps = bienesManager.editarBienes(rpm);
                }                
            }
            
            for (Bienes rpm : cliente.getBienesVehiculo()) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(cliente.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    Map<String, Object> responseMaps = bienesManager.guardarBienes(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    
                    Map<String, Object> responseMaps = bienesManager.editarBienes(rpm);
                }                
            }
            
            for (IngresosEgresos rpm : cliente.getEgresos()) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(cliente.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    Map<String, Object> responseMaps = ingresosEgresosManager.guardarIngresosEgresos(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    
                    Map<String, Object> responseMaps = ingresosEgresosManager.editarIngresosEgresos(rpm);
                }                
            }
            
            for (IngresosEgresos rpm : cliente.getIngresos()) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(cliente.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    Map<String, Object> responseMaps = ingresosEgresosManager.guardarIngresosEgresos(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    
                    Map<String, Object> responseMaps = ingresosEgresosManager.editarIngresosEgresos(rpm);
                }                
            }
            
            for (OcupacionPersona rpm : cliente.getOcupaciones()) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(cliente.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    Map<String, Object> responseMaps = ocupacionPersonaManager.guardarOcupacion(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    
                    Map<String, Object> responseMaps = ocupacionPersonaManager.editarOcupacion(rpm);
                }                
            }
            
            for (Referencias rpm : cliente.getReferencias()) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(cliente.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    Map<String, Object> responseMaps = referenciaManager.guardarReferencia(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    
                    Map<String, Object> responseMaps = referenciaManager.editarReferencia(rpm);
                }                
            }

            object = this.getCliente(cliente);
           
        }
        return object;
    }

    @Override
    public Map<String, Object> editar(Clientes cliente) throws Exception {
        Map<String, Object> object = null;
        Documentos ejDocumentos = null;
        if (cliente != null
                && cliente.getPersona() != null) {

            cliente.getPersona().setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
            cliente.getPersona().setIdUsuarioCreacion(cliente.getIdUsuarioCreacion());

            Personas ejPersona = personaManager.editar(cliente.getPersona());

            cliente.setPersona(new Personas(ejPersona.getId()));

            this.update(cliente);
            
            for (Bienes rpm : cliente.getBienesInmuebles()) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setTipoBien("INMUEBLE");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(cliente.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    bienesManager.guardarBienes(rpm);
                }else{
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    rpm.setTipoBien("INMUEBLE");
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    
                    bienesManager.editarBienes(rpm);
                }                
            }
            
            for (Bienes rpm : cliente.getBienesVehiculo()) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setTipoBien("VEHICULO");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(cliente.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    bienesManager.guardarBienes(rpm);
                }else{
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    rpm.setTipoBien("VEHICULO");
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    
                    bienesManager.editarBienes(rpm);
                }                
            }
            
            for (IngresosEgresos rpm : cliente.getEgresos()) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(cliente.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    ingresosEgresosManager.guardarIngresosEgresos(rpm);
                }else{
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    
                    ingresosEgresosManager.editarIngresosEgresos(rpm);
                }                
            }
            
            for (IngresosEgresos rpm : cliente.getIngresos()) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(cliente.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    ingresosEgresosManager.guardarIngresosEgresos(rpm);
                }else{
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    
                    ingresosEgresosManager.editarIngresosEgresos(rpm);
                }                
            }
            
            for (OcupacionPersona rpm : cliente.getOcupaciones()) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(cliente.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    ocupacionPersonaManager.guardarOcupacion(rpm);
                }else{
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    
                    ocupacionPersonaManager.editarOcupacion(rpm);
                }                
            }
            
            for (Referencias rpm : cliente.getReferencias()) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(cliente.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    referenciaManager.guardarReferencia(rpm);
                }else{
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                    
                    referenciaManager.editarReferencia(rpm);
                }                
            }
            
            object = this.getCliente(cliente);
           
        }
        return object;
    }

    @Override
    public Map<String, Object> getCliente(Clientes cliente) throws Exception {
        
        Map<String, Object> model = this.getAtributos(cliente, "id,persona.id,sucursal.id,activo".split(","));
        
        if (model != null) {
            Map<String, Object> persona = personaManager.getPersona(new Personas(Long.parseLong(model.get("persona.id").toString())));           

            Map<String, Object> sucursal = sucursalManager.getAtributos(new Sucursales(Long.parseLong(model.get("sucursal.id").toString())),
                    "id,codigoSucursal,nombre,descripcion,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo".split(","));
            
//            Bienes ejBienes = new Bienes();
//            ejBienes.setPersona(new Personas(Long.parseLong(persona.get("id").toString())));
//            ejBienes.setActivo("S");
//            ejBienes.setTipoBien("INMUEBLE");
//            
//            List<Map<String, Object>> inmueblesMap = bienesManager.getListBienes(ejBienes);
//            model.put("bienesInmuebles", inmueblesMap);
//            
//            ejBienes = new Bienes();
//            ejBienes.setPersona(new Personas(Long.parseLong(persona.get("id").toString())));
//            ejBienes.setActivo("S");
//            ejBienes.setTipoBien("VEHICULO");
//            
//            List<Map<String, Object>> vehiculosMap = bienesManager.getListBienes(ejBienes);
//            model.put("bienesVehiculo", vehiculosMap);
//            
//            Referencias ejReferencia = new Referencias();
//            ejReferencia.setPersona(new Personas(Long.parseLong(persona.get("id").toString())));
//            ejReferencia.setActivo("S");
            
//            List<Map<String, Object>> referenciasMap = referenciaManager.getListReferencias(ejReferencia);
//            model.put("referencias", referenciasMap);
            
//            TipoIngresosEgresos ejTipoIngresosEgresos = new TipoIngresosEgresos();
//            ejTipoIngresosEgresos.setTipo("I");
//            
//            IngresosEgresos ejIngresosEgresos = new IngresosEgresos();
//            ejIngresosEgresos.setPersona(new Personas(Long.parseLong(persona.get("id").toString())));
//            ejIngresosEgresos.setActivo("S");
//            ejIngresosEgresos.setTipoIngresosEgresos(ejTipoIngresosEgresos);
//            
//            List<Map<String, Object>> ingresosMap = ingresosEgresosManager.getListIngresosEgresos(ejIngresosEgresos);
//            model.put("ingresos", ingresosMap);
            
//            ejTipoIngresosEgresos = new TipoIngresosEgresos();
//            ejTipoIngresosEgresos.setTipo("E");
//            
//            ejIngresosEgresos = new IngresosEgresos();
//            ejIngresosEgresos.setPersona(new Personas(Long.parseLong(persona.get("id").toString())));
//            ejIngresosEgresos.setActivo("S");
//            ejIngresosEgresos.setTipoIngresosEgresos(ejTipoIngresosEgresos);
//            
//            List<Map<String, Object>> egresosMap = ingresosEgresosManager.getListIngresosEgresos(ejIngresosEgresos);
//            model.put("egresos", egresosMap);
            
//            OcupacionPersona ejOcupaciones = new OcupacionPersona();
//            ejOcupaciones.setPersona(new Personas(Long.parseLong(persona.get("id").toString())));
//            ejOcupaciones.setActivo("S");
//            
//            List<Map<String, Object>> ocupacionPersonaMap = ocupacionPersonaManager.getListOcupaciones(ejOcupaciones);
            //model.put("ocupaciones", ocupacionPersonaMap);
            
            model.put("sucursal", sucursal);
            model.put("persona", persona);
            model.remove("persona.id");
            model.remove("sucursal.id");

        }

        return model;
    }
}
