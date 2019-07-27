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
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.entity.Ciudades;
import py.com.mojeda.service.ejb.entity.DepartamentosPais;
import py.com.mojeda.service.ejb.entity.DepartamentosSucursal;
import py.com.mojeda.service.ejb.entity.Documentos;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Estudios;
import py.com.mojeda.service.ejb.entity.Nacionalidades;
import py.com.mojeda.service.ejb.entity.Paises;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Profesiones;
import py.com.mojeda.service.ejb.entity.Rol;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.Funcionarios;
import py.com.mojeda.service.ejb.entity.FuncionariosDepartamentos;
import py.com.mojeda.service.ejb.entity.IngresosEgresos;
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.entity.Referencias;
import py.com.mojeda.service.ejb.entity.TipoCargos;
import py.com.mojeda.service.ejb.entity.TipoEstudios;
import py.com.mojeda.service.ejb.entity.TipoFuncionarios;
import py.com.mojeda.service.ejb.entity.TipoIngresosEgresos;
import py.com.mojeda.service.ejb.entity.Vinculos;
import py.com.mojeda.service.ejb.manager.BarriosManager;
import py.com.mojeda.service.ejb.manager.BienesManager;
import py.com.mojeda.service.ejb.manager.CiudadesManager;
import py.com.mojeda.service.ejb.manager.DepartamentosPaisManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.FuncionariosManager;
import py.com.mojeda.service.ejb.manager.DocumentoManager;
import py.com.mojeda.service.ejb.manager.EmpresaManager;
import py.com.mojeda.service.ejb.manager.EstudiosManager;
import py.com.mojeda.service.ejb.manager.FuncionarioDepartamentosManager;
import py.com.mojeda.service.ejb.manager.IngresosEgresosManager;
import py.com.mojeda.service.ejb.manager.NacionalidadesManager;
import py.com.mojeda.service.ejb.manager.OcupacionPersonaManager;
import py.com.mojeda.service.ejb.manager.PaisesManager;
import py.com.mojeda.service.ejb.manager.ProfesionesManager;
import py.com.mojeda.service.ejb.manager.ReferenciaManager;
import py.com.mojeda.service.ejb.manager.RolManager;
import py.com.mojeda.service.ejb.manager.SucursalManager;
import py.com.mojeda.service.ejb.manager.TipoCargosManager;
import py.com.mojeda.service.ejb.manager.TipoDocumentosManager;
import py.com.mojeda.service.ejb.manager.TipoEstudiosManager;
import py.com.mojeda.service.ejb.manager.TipoFuncionariosManager;
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

    @EJB(mappedName = "java:app/ServiceApi-ejb/EstudiosManagerImpl")
    private EstudiosManager estudiosManager;
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoFuncionariosManagerImpl")
    private TipoFuncionariosManager tipoFuncionariosManager;
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoCargosManagerImpl")
    private TipoCargosManager tipoCargosManager;
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/ReferenciaManagerImpl")
    private ReferenciaManager referenciaManager;
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/BienesManagerImpl")
    private BienesManager bienesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/OcupacionPersonaManagerImpl")
    private OcupacionPersonaManager ocupacionPersonaManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/IngresosEgresosManagerImpl")
    private IngresosEgresosManager ingresosEgresosManager;


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

            for (Vinculos rpm : (usuario.getVinculos() == null ? new ArrayList<Vinculos>() : usuario.getVinculos())) {
                if (rpm.getId() == null) {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(usuario.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    Map<String, Object> responseMaps = vinculoManager.guardar(rpm);
                } else {
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

            for (Estudios rpm : usuario.getEstudios()) {

                if (rpm.getId() == null) {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(usuario.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    estudiosManager.save(rpm);
                } else {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    estudiosManager.update(rpm);
                }
            }
            
            for (Referencias rpm : (usuario.getReferencias() == null ? new ArrayList<Referencias> (): usuario.getReferencias())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(usuario.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    Map<String, Object> responseMaps = referenciaManager.guardarReferencia(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    
                    Map<String, Object> responseMaps = referenciaManager.editarReferencia(rpm);
                }                
            }

            Funcionarios model = new Funcionarios();
            model.setAlias(usuario.getAlias());

            object = this.getUsuario(model, "referencias,estudios");
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

            for (Vinculos rpm : (usuario.getVinculos() == null ? new ArrayList<Vinculos>() : usuario.getVinculos())) {
                if (rpm.getId() == null) {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(usuario.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    Map<String, Object> responseMaps = vinculoManager.guardar(rpm);
                } else {
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
            
            for (Estudios rpm : usuario.getEstudios()) {

                if (rpm.getId() == null) {
                    
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(usuario.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    estudiosManager.guardarEstudios(rpm);
                } else {
                    
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    estudiosManager.editarEstudios(rpm);
                }
            }
            
            for (Referencias rpm : (usuario.getReferencias() == null ? new ArrayList<Referencias> (): usuario.getReferencias())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(usuario.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));
                    
                    Map<String, Object> responseMaps = referenciaManager.guardarReferencia(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                    
                    Map<String, Object> responseMaps = referenciaManager.editarReferencia(rpm);
                }                
            }

            Funcionarios model = new Funcionarios();
            model.setAlias(usuario.getAlias());

            object = this.getUsuario(model,"referencias,estudios");
        }

        return object;
    }

    @Override
    public Map<String, Object> getUsuario(Funcionarios usuario, String included) throws Exception {

        Map<String, Object> model = this.getAtributos(usuario, "id,alias,claveAcceso,expirationTimeTokens,persona.id,rol.id,nroLegajo,sucursal.id,fechaIngreso,fechaEgreso,cargo.id,tipoFuncionario.id,tipoMotivoRetiro.id,activo".split(","));
        if (model != null) {
            Map<String, Object> persona = personaManager.getPersona(new Personas(Long.parseLong(model.get("persona.id").toString())),included);
            model.put("persona", persona);
            model.remove("persona.id");
            
            Map<String, Object> tipoFuncionario = tipoFuncionariosManager.getAtributos(new TipoFuncionarios(Long.parseLong(model.get("tipoFuncionario.id") == null ? "0" : model.get("tipoFuncionario.id").toString())), "id,nombre,codigo,activo".split(","));
            model.put("tipoFuncionario", tipoFuncionario);
            model.remove("tipoFuncionario.id");

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
            
            if (included.contains("sucursal")) {
                Map<String, Object> sucursal = sucursalManager.getAtributos(new Sucursales(Long.parseLong(model.get("sucursal.id").toString())),
                    "id,codigoSucursal,nombre,descripcion,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo".split(","));
                
                model.put("sucursal", sucursal);
                model.remove("sucursal.id");
            }
            
        }

        return model;
    }
}
