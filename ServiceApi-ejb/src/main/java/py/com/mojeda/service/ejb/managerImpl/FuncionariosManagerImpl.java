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
import java.util.ArrayList;
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
import py.com.mojeda.service.ejb.entity.Profesiones;
import py.com.mojeda.service.ejb.entity.Rol;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.Funcionarios;
import py.com.mojeda.service.ejb.entity.FuncionariosDepartamentos;
import py.com.mojeda.service.ejb.entity.Vinculos;
import py.com.mojeda.service.ejb.manager.BarriosManager;
import py.com.mojeda.service.ejb.manager.CiudadesManager;
import py.com.mojeda.service.ejb.manager.DepartamentosPaisManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.FuncionariosManager;
import py.com.mojeda.service.ejb.manager.DocumentoManager;
import py.com.mojeda.service.ejb.manager.EmpresaManager;
import py.com.mojeda.service.ejb.manager.FuncionarioDepartamentosManager;
import py.com.mojeda.service.ejb.manager.NacionalidadesManager;
import py.com.mojeda.service.ejb.manager.PaisesManager;
import py.com.mojeda.service.ejb.manager.ProfesionesManager;
import py.com.mojeda.service.ejb.manager.RolManager;
import py.com.mojeda.service.ejb.manager.SucursalManager;
import py.com.mojeda.service.ejb.manager.TipoDocumentosManager;
import py.com.mojeda.service.ejb.manager.VinculoManager;

/**
 *
 * @author Miguel
 */
@Stateless
public class FuncionariosManagerImpl extends GenericDaoImpl<Funcionarios, Long>
        implements FuncionariosManager {

    @Override
    protected Class<Funcionarios> getEntityBeanType() {
        return Funcionarios.class;
    }

    @EJB(mappedName = "java:app/ServiceApi-ejb/PersonaManagerImpl")
    private PersonaManager personaManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/DocumentoManagerImpl")
    private DocumentoManager documentoManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoDocumentosManagerImpl")
    private TipoDocumentosManager tipoDocumentosManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/SucursalManagerImpl")
    private SucursalManager sucursalManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/FuncionarioDepartamentosManagerImpl")
    private FuncionarioDepartamentosManager funcionariosDepartamentosManager;

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

    @EJB(mappedName = "java:app/ServiceApi-ejb/ProfesionesManagerImpl")
    private ProfesionesManager profesionesManager;
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/VinculoManagerImpl")
    private VinculoManager vinculoManager;

    @Override
    public Map<String, Object> guardar(Funcionarios usuario) throws Exception {
        Map<String, Object> object = null;
        Documentos ejDocumentos = null;
        if (usuario != null
                && usuario.getPersona() != null) {

            usuario.getPersona().setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());

            Personas ejPersona = personaManager.guardar(usuario.getPersona());

            usuario.setSucursal(new Sucursales(usuario.getSucursal().getId()));
            usuario.setAlias(usuario.getAlias().toUpperCase());
            usuario.setPersona(new Personas(ejPersona.getId()));
            usuario.setRol(new Rol(usuario.getRol().getId()));

            this.save(usuario);
            
            for (Vinculos rpm : (usuario.getVinculos()== null ? new ArrayList<Vinculos> (): usuario.getVinculos())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(usuario.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    Map<String, Object> responseMaps = vinculoManager.guardar(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    Map<String, Object> responseMaps = vinculoManager.editar(rpm);
                }                
            }

            FuncionariosDepartamentos usuarioDepartamentos;
            for (Map<String, Object> rpm : usuario.getDepartamentos()) {
                usuarioDepartamentos = new FuncionariosDepartamentos();
                usuarioDepartamentos.setFuncionario(usuario);
                usuarioDepartamentos.setDepartamento(new DepartamentosSucursal(Long.parseLong(rpm.get("id").toString())));

                funcionariosDepartamentosManager.save(usuarioDepartamentos);
            }

            Funcionarios model = new Funcionarios();
            model.setAlias(usuario.getAlias());

            object = this.getUsuario(model);
        }
        return object;
    }

    @Override
    public Map<String, Object> editar(Funcionarios usuario) throws Exception {
        Map<String, Object> object = null;
        Documentos ejDocumentos = null;
        if (usuario != null
                && usuario.getPersona() != null) {
            usuario.getPersona().setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());

            Personas ejPersona = personaManager.editar(usuario.getPersona());

            usuario.setSucursal(new Sucursales(usuario.getSucursal().getId()));
            usuario.setAlias(usuario.getAlias().toUpperCase());
            usuario.setPersona(new Personas(ejPersona.getId()));
            usuario.setRol(new Rol(usuario.getRol().getId()));

            this.update(usuario);
            
            for (Vinculos rpm : (usuario.getVinculos()== null ? new ArrayList<Vinculos> (): usuario.getVinculos())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(usuario.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    Map<String, Object> responseMaps = vinculoManager.guardar(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    Map<String, Object> responseMaps = vinculoManager.editar(rpm);
                }                
            }

            FuncionariosDepartamentos usuarioDepartamentos = new FuncionariosDepartamentos();
            usuarioDepartamentos.setFuncionario(usuario);

            List<FuncionariosDepartamentos> list = funcionariosDepartamentosManager.list(usuarioDepartamentos);
            for (FuncionariosDepartamentos rpc : list) {
                funcionariosDepartamentosManager.delete(rpc.getId());
            }

            for (Map<String, Object> rpm : usuario.getDepartamentos()) {
                usuarioDepartamentos = new FuncionariosDepartamentos();
                usuarioDepartamentos.setFuncionario(usuario);
                usuarioDepartamentos.setDepartamento(new DepartamentosSucursal(Long.parseLong(rpm.get("id").toString())));

                funcionariosDepartamentosManager.save(usuarioDepartamentos);
            }

            Funcionarios model = new Funcionarios();
            model.setAlias(usuario.getAlias());

            object = this.getUsuario(model);
        }

        return object;
    }

    @Override
    public Map<String, Object> getUsuario(Funcionarios usuario) throws Exception {

        Map<String, Object> model = this.getAtributos(usuario, "id,alias,claveAcceso,expirationTimeTokens,persona.id,rol.id,sucursal.id,sucursal.empresa.id,activo".split(","));
        if (model != null) {
            Map<String, Object> persona = personaManager.getAtributos(new Personas(Long.parseLong(model.get("persona.id").toString())),
                    "id,nacionalidad.id,profesion.id,pais.id,departamento.id,ciudad.id,barrio.id,imagePath,primerNombre,segundoNombre,primerApellido,segundoApellido,documento,ruc,fechaNacimiento,tipoPersona,sexo,numeroHijos,numeroDependientes,estadoCivil,separacionBienes,email,telefonoParticular,telefonoSecundario,direccionParticular,direccionDetallada,observacion,latitud,longitud,activo".split(","));

            Map<String, Object> profesion = profesionesManager.getAtributos(new Profesiones(Long.parseLong(persona.get("profesion.id") == null ? "0" : persona.get("profesion.id").toString())), "id,nombre,activo".split(","));
            persona.put("profesion", profesion);
            persona.remove("profesion.id");

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

            Map<String, Object> sucursal = sucursalManager.getAtributos(new Sucursales(Long.parseLong(model.get("sucursal.id").toString())),
                    "id,codigoSucursal,nombre,descripcion,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo".split(","));

            Map<String, Object> empresa = empresaManager.getAtributos(new Empresas(Long.parseLong(model.get("sucursal.empresa.id").toString())),
                    "id,nombre,ruc,nombreFantasia,descripcion,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo".split(","));
            sucursal.put("empresa", empresa);

            model.put("sucursal", sucursal);
            model.put("persona", persona);

            model.remove("persona.id");
            model.remove("sucursal.id");
            model.remove("sucursal.empresa.id");

            Map<String, Object> rol = rolManager.getAtributos(new Rol(Long.parseLong(model.get("rol.id").toString())),
                    "id,nombre,activo".split(","));
            model.put("rol", rol);
            model.remove("rol.id");

            FuncionariosDepartamentos ejUsuarioDepartamentos = new FuncionariosDepartamentos();
            ejUsuarioDepartamentos.setFuncionario(new Funcionarios(Long.parseLong(model.get("id").toString())));

            List<Map<String, Object>> departamentos = funcionariosDepartamentosManager.listAtributos(ejUsuarioDepartamentos, "departamento.id,departamento.alias,departamento.nombreArea,departamento.descripcionArea".split(","), true);

            for (Map<String, Object> rpm : departamentos) {
                rpm.put("id", rpm.get("departamento.id"));
                rpm.put("alias", rpm.get("departamento.alias"));
                rpm.put("nombreArea", rpm.get("departamento.nombreArea"));
                rpm.put("descripcionArea", rpm.get("departamento.descripcionArea"));

                rpm.remove("departamento.id");
                rpm.remove("departamento.alias");
                rpm.remove("departamento.nombreArea");
                rpm.remove("departamento.descripcionArea");
            }
            model.put("departamentos", departamentos);
        }

        return model;
    }
}
