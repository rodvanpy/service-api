/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import py.com.mojeda.service.ejb.entity.Vinculos;
import py.com.mojeda.service.ejb.manager.BienesManager;
import py.com.mojeda.service.ejb.manager.ClientesManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.DocumentoManager;
import py.com.mojeda.service.ejb.manager.IngresosEgresosManager;
import py.com.mojeda.service.ejb.manager.OcupacionPersonaManager;
import py.com.mojeda.service.ejb.manager.ReferenciaManager;
import py.com.mojeda.service.ejb.manager.SucursalManager;
import py.com.mojeda.service.ejb.manager.VinculoManager;

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

    @EJB(mappedName = "java:app/ServiceApi-ejb/VinculoManagerImpl")
    private VinculoManager vinculoManager;

    @Override
    public Clientes guardar(Clientes cliente, Long idSucursal, Long idEmpresa) throws Exception {
        Clientes object = null;
        Documentos ejDocumentos = null;
        if (cliente != null
                && cliente.getPersona() != null) {

            cliente.getPersona().setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
            cliente.getPersona().setIdUsuarioCreacion(cliente.getIdUsuarioCreacion());

            Personas ejPersona = personaManager.guardar(cliente.getPersona());

            cliente.setPersona(new Personas(ejPersona.getId()));
            cliente.setIdEmpresa(idEmpresa);
            cliente.setSucursal(new Sucursales(idSucursal));
            
            this.save(cliente);
                       
            object = this.getCliente(new Clientes(cliente.getId()),idEmpresa,"inmuebles,vehiculos,referencias,ingresos,egresos,ocupaciones");

        }
        return object;
    }

    @Override
    public Clientes editar(Clientes cliente, Long idSucursal, Long idEmpresa) throws Exception {
        Clientes object = null;
        Documentos ejDocumentos = null;
        if (cliente != null
                && cliente.getPersona() != null) {

            cliente.getPersona().setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
            cliente.getPersona().setIdUsuarioCreacion(cliente.getIdUsuarioCreacion());

            Personas ejPersona = personaManager.editar(cliente.getPersona());

            cliente.setPersona(new Personas(ejPersona.getId()));
            cliente.setSucursal(new Sucursales(idSucursal));
            
            this.update(cliente);
                     
            object = this.getCliente(new Clientes(cliente.getId()),idEmpresa,"inmuebles,vehiculos,referencias,ingresos,egresos,ocupaciones");

        }
        return object;
    }

    @Override
    public Clientes getCliente(Clientes cliente, Long idEmpresa, String included) throws Exception {
        Clientes model = null;
        Map<String, Object> modelMaps = this.getAtributos(cliente, "id,persona.id,sucursal.id,activo".split(","));       
        
        if (modelMaps != null) {
            model = new Clientes();
            model.setActivo(modelMaps.get("activo").toString());
            model.setId(Long.parseLong(modelMaps.get("id").toString()));
            
            model.setPersona(personaManager.getPersona(new Personas(Long.parseLong(modelMaps.get("persona.id").toString())),included));
            model.setSucursal(sucursalManager.get(new Sucursales(Long.parseLong(modelMaps.get("sucursal.id").toString()))));
            //model.getSucursal().setEmpresa(null);
        }

        return model;
    }
}
