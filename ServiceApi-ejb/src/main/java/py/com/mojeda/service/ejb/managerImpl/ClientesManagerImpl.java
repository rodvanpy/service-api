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
import py.com.mojeda.service.ejb.entity.Ciudades;
import py.com.mojeda.service.ejb.entity.Clientes;
import py.com.mojeda.service.ejb.entity.DepartamentosPais;
import py.com.mojeda.service.ejb.entity.DepartamentosSucursal;
import py.com.mojeda.service.ejb.entity.Documentos;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Nacionalidades;
import py.com.mojeda.service.ejb.entity.Paises;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Rol;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.TipoDocumentos;
import py.com.mojeda.service.ejb.entity.UsuarioDepartamentos;
import py.com.mojeda.service.ejb.entity.Usuarios;
import py.com.mojeda.service.ejb.manager.BarriosManager;
import py.com.mojeda.service.ejb.manager.CiudadesManager;
import py.com.mojeda.service.ejb.manager.ClientesManager;
import py.com.mojeda.service.ejb.manager.DepartamentosPaisManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.UsuarioManager;
import py.com.mojeda.service.ejb.utils.Base64Bytes;
import py.com.mojeda.service.ejb.manager.DocumentoManager;
import py.com.mojeda.service.ejb.manager.EmpresaManager;
import py.com.mojeda.service.ejb.manager.NacionalidadesManager;
import py.com.mojeda.service.ejb.manager.PaisesManager;
import py.com.mojeda.service.ejb.manager.RolManager;
import py.com.mojeda.service.ejb.manager.SucursalManager;
import py.com.mojeda.service.ejb.manager.TipoDocumentosManager;
import py.com.mojeda.service.ejb.manager.UsuarioDepartamentosManager;
import static py.com.mojeda.service.ejb.utils.Constants.CONTENT_PATH;

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
    public Clientes guardar(Clientes cliente) throws Exception {
        Clientes object = null;
        Documentos ejDocumentos = null;
        if (cliente != null
                && cliente.getPersona() != null) {

            Personas ejPersona = new Personas();
            ejPersona.setDocumento(cliente.getPersona().getDocumento());

            ejPersona = personaManager.get(ejPersona);

            if (ejPersona != null) {

                ejPersona.setPrimerNombre(cliente.getPersona().getPrimerNombre());
                ejPersona.setSegundoNombre(cliente.getPersona().getSegundoNombre());
                ejPersona.setPrimerApellido(cliente.getPersona().getPrimerApellido());
                ejPersona.setSegundoApellido(cliente.getPersona().getSegundoApellido());
                ejPersona.setEmail(cliente.getPersona().getEmail());
                ejPersona.setEstadoCivil(cliente.getPersona().getEstadoCivil());
                ejPersona.setNumeroHijos(cliente.getPersona().getNumeroHijos());
                ejPersona.setTelefonoParticular(cliente.getPersona().getTelefonoParticular());
                ejPersona.setTelefonoSecundario(cliente.getPersona().getTelefonoSecundario());
                ejPersona.setTipoPersona(cliente.getPersona().getTipoPersona());
                ejPersona.setDireccionParticular(cliente.getPersona().getDireccionParticular());
                ejPersona.setFechaNacimiento(cliente.getPersona().getFechaNacimiento());
                ejPersona.setDireccionDetallada(cliente.getPersona().getDireccionDetallada());
                ejPersona.setObservacion(cliente.getPersona().getObservacion());
                ejPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejPersona.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                ejPersona.setSucursal(new Sucursales(cliente.getPersona().getSucursal().getId()));
                ejPersona.setNacionalidad(new Nacionalidades(cliente.getPersona().getNacionalidad().getId()));
                ejPersona.setPais(new Paises(cliente.getPersona().getPais().getId()));
                ejPersona.setDepartamento(new DepartamentosPais(cliente.getPersona().getDepartamento().getId()));
                ejPersona.setCiudad(new Ciudades(cliente.getPersona().getCiudad().getId()));
                ejPersona.setBarrio((cliente.getPersona().getBarrio() != null && cliente.getPersona().getBarrio().getId() != null) ? new Barrios(cliente.getPersona().getBarrio().getId()) : null);

            } else {

                cliente.getPersona().setActivo("S");
                cliente.getPersona().setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                cliente.getPersona().setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                cliente.getPersona().setIdUsuarioCreacion(cliente.getIdUsuarioCreacion());
                cliente.getPersona().setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                cliente.getPersona().setSucursal(new Sucursales(cliente.getPersona().getSucursal().getId()));

                personaManager.save(cliente.getPersona());

                ejPersona = personaManager.get(cliente.getPersona());

            }

            cliente.setPersona(new Personas(ejPersona.getId()));

            this.save(cliente);

            object = this.get(cliente);

            if (cliente.getPersona().getAvatar() != null
                    && cliente.getPersona().getAvatar().getValue() != null) {

                Files.createDirectories(Paths.get(CONTENT_PATH + ejPersona.getClassName() + "/" + ejPersona.getId()));
                String path = ejPersona.getClassName() + "/" + ejPersona.getId() + "/" + cliente.getPersona().getAvatar().getFilename();
                FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
                fos.write(Base64Bytes.decode(cliente.getPersona().getAvatar().getValue()));
                fos.close();

                ejPersona.setImagePath(CONTENT_PATH + path);
            }

            personaManager.update(ejPersona);
        }
        return object;
    }

    @Override
    public Clientes editar(Clientes cliente) throws Exception {
        Clientes object = null;
        Documentos ejDocumentos = null;
        if (cliente != null
                && cliente.getPersona() != null) {

            Personas ejPersona = new Personas();
            ejPersona.setDocumento(cliente.getPersona().getDocumento());

            ejPersona = personaManager.get(ejPersona);

            if (ejPersona != null) {

                ejPersona.setPrimerNombre(cliente.getPersona().getPrimerNombre());
                ejPersona.setSegundoNombre(cliente.getPersona().getSegundoNombre());
                ejPersona.setPrimerApellido(cliente.getPersona().getPrimerApellido());
                ejPersona.setSegundoApellido(cliente.getPersona().getSegundoApellido());
                ejPersona.setEmail(cliente.getPersona().getEmail());
                ejPersona.setEstadoCivil(cliente.getPersona().getEstadoCivil());
                ejPersona.setNumeroHijos(cliente.getPersona().getNumeroHijos());
                ejPersona.setTelefonoParticular(cliente.getPersona().getTelefonoParticular());
                ejPersona.setTelefonoSecundario(cliente.getPersona().getTelefonoSecundario());
                ejPersona.setTipoPersona(cliente.getPersona().getTipoPersona());
                ejPersona.setDireccionParticular(cliente.getPersona().getDireccionParticular());
                ejPersona.setDireccionDetallada(cliente.getPersona().getDireccionDetallada());
                ejPersona.setObservacion(cliente.getPersona().getObservacion());
                ejPersona.setFechaNacimiento(cliente.getPersona().getFechaNacimiento());
                ejPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejPersona.setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                ejPersona.setSucursal(new Sucursales(cliente.getPersona().getSucursal().getId()));
                ejPersona.setNacionalidad(new Nacionalidades(cliente.getPersona().getNacionalidad().getId()));
                ejPersona.setPais(new Paises(cliente.getPersona().getPais().getId()));
                ejPersona.setDepartamento(new DepartamentosPais(cliente.getPersona().getDepartamento().getId()));
                ejPersona.setCiudad(new Ciudades(cliente.getPersona().getCiudad().getId()));
                ejPersona.setBarrio((cliente.getPersona().getBarrio() != null && cliente.getPersona().getBarrio().getId() != null) ? new Barrios(cliente.getPersona().getBarrio().getId()) : null);

            } else {

                cliente.getPersona().setActivo("S");
                cliente.getPersona().setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                cliente.getPersona().setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                cliente.getPersona().setIdUsuarioCreacion(cliente.getIdUsuarioCreacion());
                cliente.getPersona().setIdUsuarioModificacion(cliente.getIdUsuarioModificacion());
                cliente.getPersona().setSucursal(new Sucursales(cliente.getPersona().getSucursal().getId()));

                personaManager.save(cliente.getPersona());

                ejPersona = personaManager.get(cliente.getPersona());

            }

            if (cliente.getPersona().getAvatar() != null
                    && cliente.getPersona().getAvatar().getValue() != null) {

                Files.createDirectories(Paths.get(CONTENT_PATH + ejPersona.getClassName() + "/" + ejPersona.getId()));
                String path = ejPersona.getClassName() + "/" + ejPersona.getId() + "/" + cliente.getPersona().getAvatar().getFilename();
                FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
                fos.write(Base64Bytes.decode(cliente.getPersona().getAvatar().getValue()));
                fos.close();

                ejPersona.setImagePath(CONTENT_PATH + path);
            }

            cliente.setPersona(new Personas(ejPersona.getId()));

            this.update(cliente);

            object = this.get(cliente);
        }
        return object;
    }

    @Override
    public Map<String, Object> getCliente(Clientes cliente) throws Exception {

        Map<String, Object> model = this.getAtributos(cliente, "id,persona.id,persona.sucursal.id,persona.sucursal.empresa.id,activo".split(","));
        if (model != null) {
            Map<String, Object> persona = personaManager.getAtributos(new Personas(Long.parseLong(model.get("persona.id").toString())),
                    "id,nacionalidad.id,pais.id,departamento.id,ciudad.id,barrio.id,imagePath,primerNombre,segundoNombre,primerApellido,segundoApellido,documento,ruc,fechaNacimiento,tipoPersona,sexo,numeroHijos,numeroDependientes,estadoCivil,separacionBienes,email,telefonoParticular,telefonoSecundario,direccionParticular,direccionDetallada,observacion,latitud,longitud".split(","));

            Map<String, Object> nacionalidad = nacionalidadesManager.getAtributos(new Nacionalidades(Long.parseLong(persona.get("nacionalidad.id") == null ? "0" : persona.get("nacionalidad.id").toString())), "id,nombre,codigo,activo".split(","));
            persona.put("nacionalidad", nacionalidad);
            persona.remove("nacionalidad.id");

            Map<String, Object> pais = paisesManager.getAtributos(new Paises(Long.parseLong(persona.get("pais.id") == null ? "0" : persona.get("pais.id").toString())), "id,nombre,activo".split(","));
            persona.put("pais", pais);
            persona.remove("pais.id");

            Map<String, Object> departamento = departamentosPaisManager.getAtributos(new DepartamentosPais(Long.parseLong(persona.get("departamento.id") == null ? "0" : persona.get("departamento.id").toString())), "id,nombre,activo".split(","));
            persona.put("departamento", departamento);
            persona.remove("departamento.id");

            Map<String, Object> ciudad = ciudadesManager.getAtributos(new Ciudades(Long.parseLong(persona.get("ciudad.id") == null ? "0" : persona.get("ciudad.id").toString())), "id,nombre,activo".split(","));
            persona.put("ciudad", ciudad);
            persona.remove("ciudad.id");

            Map<String, Object> barrio = barriosManager.getAtributos(new Barrios(Long.parseLong(persona.get("barrio.id") == null ? "0" : persona.get("barrio.id").toString())), "id,nombre,activo".split(","));
            persona.put("barrio", barrio);
            persona.remove("barrio.id");

            Map<String, Object> sucursal = sucursalManager.getAtributos(new Sucursales(Long.parseLong(model.get("persona.sucursal.id").toString())),
                    "id,codigoSucursal,nombre,descripcion,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo".split(","));

            persona.put("sucursal", sucursal);
            model.put("persona", persona);

            model.remove("persona.id");
            model.remove("persona.sucursal.id");
            model.remove("persona.sucursal.empresa.id");

        }

        return model;
    }
}
