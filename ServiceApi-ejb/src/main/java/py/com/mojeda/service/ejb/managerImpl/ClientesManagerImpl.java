/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Barrios;
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.entity.Ciudades;
import py.com.mojeda.service.ejb.entity.Clientes;
import py.com.mojeda.service.ejb.entity.DepartamentosPais;
import py.com.mojeda.service.ejb.entity.DepartamentosSucursal;
import py.com.mojeda.service.ejb.entity.Documentos;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.IngresosEgresos;
import py.com.mojeda.service.ejb.entity.Nacionalidades;
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.entity.Paises;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Referencias;
import py.com.mojeda.service.ejb.entity.Rol;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.UsuarioDepartamentos;
import py.com.mojeda.service.ejb.manager.BarriosManager;
import py.com.mojeda.service.ejb.manager.CiudadesManager;
import py.com.mojeda.service.ejb.manager.ClientesManager;
import py.com.mojeda.service.ejb.manager.DepartamentosPaisManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.DocumentoManager;
import py.com.mojeda.service.ejb.manager.EmpresaManager;
import py.com.mojeda.service.ejb.manager.NacionalidadesManager;
import py.com.mojeda.service.ejb.manager.PaisesManager;
import py.com.mojeda.service.ejb.manager.RolManager;
import py.com.mojeda.service.ejb.manager.SucursalManager;
import py.com.mojeda.service.ejb.manager.TipoDocumentosManager;
import py.com.mojeda.service.ejb.manager.UsuarioDepartamentosManager;

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

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoDocumentosManagerImpl")
    private TipoDocumentosManager tipoDocumentosManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/SucursalManagerImpl")
    private SucursalManager sucursalManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/UsuarioDepartamentosManagerImpl")
    private UsuarioDepartamentosManager usuarioDepartamentosManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/EmpresaManagerImpl")
    private EmpresaManager empresaManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/RolManagerImpl")
    private RolManager rolManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/NacionalidadesManagerImpl")
    private NacionalidadesManager nacionalidadesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/PaisesManagerImpl")
    private PaisesManager paisesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/DepartamentosPaisManagerImpl")
    private DepartamentosPaisManager departamentosPaisManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/CiudadesManagerImpl")
    private CiudadesManager ciudadesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/BarriosManagerImpl")
    private BarriosManager barriosManager;

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
                    
                    
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
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
                    
                    
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
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
                    
                    
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
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
                    
                    
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
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
                    
                    
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
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
                    
                    
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
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

            Personas ejPersona = personaManager.editar(cliente.getPersona());
            
            cliente.setPersona(new Personas(ejPersona.getId()));

            this.update(cliente);

            object = this.getCliente(cliente);
        }
        return object;
    }

    @Override
    public Map<String, Object> getCliente(Clientes cliente) throws Exception {

        Map<String, Object> model = this.getAtributos(cliente, "id,persona.id,sucursal.id,activo".split(","));
        if (model != null) {
            Map<String, Object> persona = personaManager.getPersona(new Personas(Long.parseLong(model.get("persona.id").toString())));           

            Map<String, Object> sucursal = sucursalManager.getAtributos(new Sucursales(Long.parseLong(model.get("persona.sucursal.id").toString())),
                    "id,codigoSucursal,nombre,descripcion,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo".split(","));

            model.put("sucursal", sucursal);
            model.put("persona", persona);
            model.remove("persona.id");
            model.remove("sucursal.id");

        }

        return model;
    }
}
