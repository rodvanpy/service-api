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
public class UsuarioManagerImpl extends GenericDaoImpl<Usuarios, Long>
        implements UsuarioManager {

    @Override
    protected Class<Usuarios> getEntityBeanType() {
        return Usuarios.class;
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
    public Usuarios guardar(Usuarios usuario) throws Exception {
        Usuarios object = null;
        Documentos ejDocumentos = null;
        if (usuario != null
                && usuario.getPersona() != null) {

            Personas ejPersona = new Personas();
            ejPersona.setDocumento(usuario.getPersona().getDocumento());

            ejPersona = personaManager.get(ejPersona);

            if (ejPersona != null) {

                ejPersona.setPrimerNombre(usuario.getPersona().getPrimerNombre());
                ejPersona.setSegundoNombre(usuario.getPersona().getSegundoNombre());
                ejPersona.setPrimerApellido(usuario.getPersona().getPrimerApellido());
                ejPersona.setSegundoApellido(usuario.getPersona().getSegundoApellido());
                ejPersona.setEmail(usuario.getPersona().getEmail());
                ejPersona.setEstadoCivil(usuario.getPersona().getEstadoCivil());
                ejPersona.setNumeroHijos(usuario.getPersona().getNumeroHijos());
                ejPersona.setTelefonoParticular(usuario.getPersona().getTelefonoParticular());
                ejPersona.setTelefonoSecundario(usuario.getPersona().getTelefonoSecundario());
                ejPersona.setTipoPersona(usuario.getPersona().getTipoPersona());
                ejPersona.setDireccionParticular(usuario.getPersona().getDireccionParticular());
                ejPersona.setFechaNacimiento(usuario.getPersona().getFechaNacimiento());
                ejPersona.setDireccionDetallada(usuario.getPersona().getDireccionDetallada());
                ejPersona.setObservacion(usuario.getPersona().getObservacion());
                ejPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejPersona.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                ejPersona.setSucursal(new Sucursales(usuario.getPersona().getSucursal().getId()));
                ejPersona.setNacionalidad(new Nacionalidades(usuario.getPersona().getNacionalidad().getId()));
                ejPersona.setPais(new Paises(usuario.getPersona().getPais().getId()));
                ejPersona.setDepartamento(new DepartamentosPais(usuario.getPersona().getDepartamento().getId()));
                ejPersona.setCiudad(new Ciudades(usuario.getPersona().getCiudad().getId()));
                ejPersona.setBarrio((usuario.getPersona().getBarrio() != null && usuario.getPersona().getBarrio().getId() != null) ? new Barrios(usuario.getPersona().getCiudad().getId()) : null);

            } else {

                usuario.getPersona().setActivo("S");
                usuario.getPersona().setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                usuario.getPersona().setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                usuario.getPersona().setIdUsuarioCreacion(usuario.getIdUsuarioCreacion());
                usuario.getPersona().setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                usuario.getPersona().setSucursal(new Sucursales(usuario.getPersona().getSucursal().getId()));

                personaManager.save(usuario.getPersona());

                ejPersona = personaManager.get(usuario.getPersona());

            }

            usuario.setAlias(usuario.getAlias().toUpperCase());
            usuario.setPersona(new Personas(ejPersona.getId()));
            usuario.setRol(new Rol(usuario.getRol().getId()));

            this.save(usuario);
            
            object = this.get(usuario);

            if (usuario.getPersona().getAvatar() != null
                    && usuario.getPersona().getAvatar().getValue() != null) {

                Files.createDirectories(Paths.get(CONTENT_PATH + ejPersona.getClassName() + "/" + ejPersona.getId()));
                String path = ejPersona.getClassName() + "/" + ejPersona.getId() + "/" + usuario.getPersona().getAvatar().getFilename();
                FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
                fos.write(Base64Bytes.decode(usuario.getPersona().getAvatar().getValue()));
                fos.close();

                ejPersona.setImagePath(CONTENT_PATH + path);
            }
            
            ejPersona.setIdUsuario(object.getId());
            personaManager.update(ejPersona);
            
            UsuarioDepartamentos usuarioDepartamentos;
            for (Map<String, Object> rpm : usuario.getDepartamentos()) {
                usuarioDepartamentos = new UsuarioDepartamentos();
                usuarioDepartamentos.setUsuario(usuario);
                usuarioDepartamentos.setDepartamento(new DepartamentosSucursal(Long.parseLong(rpm.get("id").toString())));

                usuarioDepartamentosManager.save(usuarioDepartamentos);
            }
        }
        return object;
    }

    @Override
    public Usuarios editar(Usuarios usuario) throws Exception {
        Usuarios object = null;
        Documentos ejDocumentos = null;
        if (usuario != null
                && usuario.getPersona() != null) {

            Personas ejPersona = new Personas();
            ejPersona.setDocumento(usuario.getPersona().getDocumento());

            ejPersona = personaManager.get(ejPersona);

            if (ejPersona != null) {

                ejPersona.setPrimerNombre(usuario.getPersona().getPrimerNombre());
                ejPersona.setSegundoNombre(usuario.getPersona().getSegundoNombre());
                ejPersona.setPrimerApellido(usuario.getPersona().getPrimerApellido());
                ejPersona.setSegundoApellido(usuario.getPersona().getSegundoApellido());
                ejPersona.setEmail(usuario.getPersona().getEmail());
                ejPersona.setEstadoCivil(usuario.getPersona().getEstadoCivil());
                ejPersona.setNumeroHijos(usuario.getPersona().getNumeroHijos());
                ejPersona.setTelefonoParticular(usuario.getPersona().getTelefonoParticular());
                ejPersona.setTelefonoSecundario(usuario.getPersona().getTelefonoSecundario());
                ejPersona.setTipoPersona(usuario.getPersona().getTipoPersona());
                ejPersona.setDireccionParticular(usuario.getPersona().getDireccionParticular());
                ejPersona.setDireccionDetallada(usuario.getPersona().getDireccionDetallada());
                ejPersona.setObservacion(usuario.getPersona().getObservacion());
                ejPersona.setFechaNacimiento(usuario.getPersona().getFechaNacimiento());
                ejPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejPersona.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                ejPersona.setSucursal(new Sucursales(usuario.getPersona().getSucursal().getId()));
                ejPersona.setNacionalidad(new Nacionalidades(usuario.getPersona().getNacionalidad().getId()));
                ejPersona.setPais(new Paises(usuario.getPersona().getPais().getId()));
                ejPersona.setDepartamento(new DepartamentosPais(usuario.getPersona().getDepartamento().getId()));
                ejPersona.setCiudad(new Ciudades(usuario.getPersona().getCiudad().getId()));
                ejPersona.setBarrio((usuario.getPersona().getBarrio() != null && usuario.getPersona().getBarrio().getId() != null) ? new Barrios(usuario.getPersona().getCiudad().getId()) : null);

            } else {

                usuario.getPersona().setActivo("S");
                usuario.getPersona().setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                usuario.getPersona().setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                usuario.getPersona().setIdUsuarioCreacion(usuario.getIdUsuarioCreacion());
                usuario.getPersona().setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                usuario.getPersona().setSucursal(new Sucursales(usuario.getPersona().getSucursal().getId()));

                personaManager.save(usuario.getPersona());

                ejPersona = personaManager.get(usuario.getPersona());

            }

            if (usuario.getPersona().getAvatar() != null
                    && usuario.getPersona().getAvatar().getValue() != null) {

                Files.createDirectories(Paths.get(CONTENT_PATH + ejPersona.getClassName() + "/" + ejPersona.getId()));
                String path = ejPersona.getClassName() + "/" + ejPersona.getId() + "/" + usuario.getPersona().getAvatar().getFilename();
                FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
                fos.write(Base64Bytes.decode(usuario.getPersona().getAvatar().getValue()));
                fos.close();

                ejPersona.setImagePath(CONTENT_PATH + path);
            }

            usuario.setAlias(usuario.getAlias().toUpperCase());
            usuario.setPersona(new Personas(ejPersona.getId()));
            usuario.setRol(new Rol(usuario.getRol().getId()));

            this.update(usuario);

            ejPersona.setIdUsuario(usuario.getId());
            personaManager.update(ejPersona);

            UsuarioDepartamentos usuarioDepartamentos = new UsuarioDepartamentos();
            usuarioDepartamentos.setUsuario(usuario);

            List<UsuarioDepartamentos> list = usuarioDepartamentosManager.list(usuarioDepartamentos);
            for (UsuarioDepartamentos rpc : list) {
                usuarioDepartamentosManager.delete(rpc.getId());
            }

            for (Map<String, Object> rpm : usuario.getDepartamentos()) {
                usuarioDepartamentos = new UsuarioDepartamentos();
                usuarioDepartamentos.setUsuario(usuario);
                usuarioDepartamentos.setDepartamento(new DepartamentosSucursal(Long.parseLong(rpm.get("id").toString())));

                usuarioDepartamentosManager.save(usuarioDepartamentos);
            }

            object = this.get(usuario);
        }
        return object;
    }

    @Override
    public Map<String, Object> getUsuario(Long id) throws Exception {

        Map<String, Object> model = this.getAtributos(new Usuarios(id), "id,alias,claveAcceso,superUsuario,expirationTimeTokens,persona.id,rol.id,persona.sucursal.id,persona.sucursal.empresa.id,activo".split(","));

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

        Map<String, Object> empresa = empresaManager.getAtributos(new Empresas(Long.parseLong(model.get("persona.sucursal.empresa.id").toString())),
                "id,nombre,ruc,nombreFantasia,descripcion,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo".split(","));
        sucursal.put("empresa", empresa);

        persona.put("sucursal", sucursal);
        model.put("persona", persona);

        model.remove("persona.id");
        model.remove("persona.sucursal.id");
        model.remove("persona.sucursal.empresa.id");

        Map<String, Object> rol = rolManager.getAtributos(new Rol(Long.parseLong(model.get("rol.id").toString())),
                "id,nombre,activo".split(","));
        model.put("rol", rol);
        model.remove("rol.id");

        UsuarioDepartamentos ejUsuarioDepartamentos = new UsuarioDepartamentos();
        ejUsuarioDepartamentos.setUsuario(new Usuarios(id));

        List<Map<String, Object>> departamentos = usuarioDepartamentosManager.listAtributos(ejUsuarioDepartamentos, "departamento.id,departamento.alias,departamento.nombreArea,departamento.descripcionArea".split(","), true);

        for (Map<String, Object> rpm : departamentos) {
            rpm.put("id", Long.parseLong(rpm.get("departamento.id").toString()));
            rpm.put("alias", rpm.get("departamento.alias").toString());
            rpm.put("nombreArea", rpm.get("departamento.nombreArea").toString());
            rpm.put("descripcionArea", rpm.get("departamento.descripcionArea").toString());

            rpm.remove("departamento.id");
            rpm.remove("departamento.alias");
            rpm.remove("departamento.nombreArea");
            rpm.remove("departamento.descripcionArea");
        }
        model.put("departamentos", departamentos);

        return model;
    }
}
