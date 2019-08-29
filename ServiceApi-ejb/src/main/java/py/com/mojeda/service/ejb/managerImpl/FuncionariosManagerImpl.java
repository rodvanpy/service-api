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
import py.com.mojeda.service.ejb.entity.DepartamentosSucursal;
import py.com.mojeda.service.ejb.entity.Documentos;
import py.com.mojeda.service.ejb.entity.Estudios;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Rol;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.Funcionarios;
import py.com.mojeda.service.ejb.entity.FuncionariosDepartamentos;
import py.com.mojeda.service.ejb.entity.Referencias;
import py.com.mojeda.service.ejb.entity.TipoFuncionarios;
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
    public Funcionarios guardar(Funcionarios usuario) throws Exception {
        Funcionarios object = null;
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

            FuncionariosDepartamentos usuarioDepartamentos;
            for (DepartamentosSucursal rpm : usuario.getDepartamentos()) {
                usuarioDepartamentos = new FuncionariosDepartamentos();
                usuarioDepartamentos.setFuncionario(usuario);
                usuarioDepartamentos.setDepartamento(new DepartamentosSucursal(rpm.getId()));

                funcionariosDepartamentosManager.save(usuarioDepartamentos);
            }

//            Funcionarios model = new Funcionarios();
//            model.setAlias(usuario.getAlias());
//
//            object = this.getUsuario(model, "referencias,estudios");
        }
        return object;
    }

    @Override
    public Funcionarios editar(Funcionarios usuario) throws Exception {
        Funcionarios object = null;
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


            FuncionariosDepartamentos usuarioDepartamentos = new FuncionariosDepartamentos();
            usuarioDepartamentos.setFuncionario(usuario);

            List<FuncionariosDepartamentos> list = funcionariosDepartamentosManager.list(usuarioDepartamentos);
            for (FuncionariosDepartamentos rpc : list) {
                funcionariosDepartamentosManager.delete(rpc.getId());
            }

            for (DepartamentosSucursal rpm : usuario.getDepartamentos()) {
                usuarioDepartamentos = new FuncionariosDepartamentos();
                usuarioDepartamentos.setFuncionario(usuario);
                usuarioDepartamentos.setDepartamento(new DepartamentosSucursal(rpm.getId()));

                funcionariosDepartamentosManager.save(usuarioDepartamentos);
            }           

//            Funcionarios model = new Funcionarios();
//            model.setAlias(usuario.getAlias());
//
//            object = this.getUsuario(model,"referencias,estudios");
        }

        return object;
    }

    @Override
    public Funcionarios getUsuario(Funcionarios usuario, String included) throws Exception {

        Funcionarios model = this.get(usuario);
        if (model != null) {
            Personas persona = personaManager.getPersona(new Personas(model.getPersona().getId()),included);

            model.setPersona(persona);

            FuncionariosDepartamentos ejUsuarioDepartamentos = new FuncionariosDepartamentos();
            ejUsuarioDepartamentos.setFuncionario(new Funcionarios(model.getId()));

            List<FuncionariosDepartamentos> funcionariosDepartamentos = funcionariosDepartamentosManager.list(ejUsuarioDepartamentos, true);
            List<DepartamentosSucursal> departamentos = new ArrayList<>();
            for (FuncionariosDepartamentos rpm : funcionariosDepartamentos) {
                departamentos.add(rpm.getDepartamento());
            }
            model.setDepartamentos(departamentos);
            
        }

        return model;
    }
}
