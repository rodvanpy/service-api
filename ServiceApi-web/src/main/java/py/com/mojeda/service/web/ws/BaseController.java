/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import py.com.mojeda.service.ejb.manager.EmpresaManager;
import py.com.mojeda.service.ejb.manager.ModalidadesManager;
import py.com.mojeda.service.ejb.manager.ReferenciaManager;
import py.com.mojeda.service.ejb.manager.RolManager;
import py.com.mojeda.service.ejb.manager.SucursalManager;
import py.com.mojeda.service.ejb.manager.TipoCalculosManager;
import py.com.mojeda.service.ejb.manager.TipoDesembolsosManager;
import py.com.mojeda.service.ejb.manager.TipoDestinosManager;
import py.com.mojeda.service.ejb.manager.TipoEgresosManager;
import py.com.mojeda.service.ejb.manager.TipoGarantiasManager;
import py.com.mojeda.service.ejb.manager.TipoIngresosManager;
import py.com.mojeda.service.ejb.manager.TipoPagosManager;
import py.com.mojeda.service.ejb.manager.TipoReferenciaManager;
import py.com.mojeda.service.ejb.manager.TipoVinculoManager;
import py.com.mojeda.service.ejb.manager.UsuarioManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;
import py.com.mojeda.service.ejb.manager.DepartamentosSucursalManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;

/**
 *
 * @author miguel.ojeda
 */
public class BaseController {
    
    private Context context;
    
    protected UsuarioManager usuarioManager;
    
    protected PersonaManager personaManager;
    
    protected EmpresaManager empresaManager;
    
    protected SucursalManager sucursalManager;
    
    protected DepartamentosSucursalManager departamentosSucursalManager;
    
    protected ReferenciaManager referenciaManager;
    
    protected TipoReferenciaManager tipoReferenciaManager;
    
    protected TipoVinculoManager tipoVinculoManager;
    
    protected RolManager rolManager;
    
    protected ModalidadesManager modalidadesManager;
            
    protected TipoCalculosManager tipoCalculosManager;
            
    protected TipoDesembolsosManager tipoDesembolsosManager;
            
    protected TipoDestinosManager tipoDestinosManager;
    
    protected TipoIngresosManager tipoIngresosManager;
            
    protected TipoGarantiasManager tipoGarantiasManager;
            
    protected TipoEgresosManager tipoEgresosManager;
    
    protected TipoPagosManager tipoPagosManager;
            
    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();
    
    
    
    protected void inicializarPersonaManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (personaManager == null) {
            try {

                personaManager = (PersonaManager) context.lookup("java:app/ServiceApi-ejb/PersonaManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarDepartamentosSucursalManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (departamentosSucursalManager == null) {
            try {

                departamentosSucursalManager = (DepartamentosSucursalManager) context.lookup("java:app/ServiceApi-ejb/DepartamentosSucursalManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoEgresosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoEgresosManager == null) {
            try {

                tipoEgresosManager = (TipoEgresosManager) context.lookup("java:app/ServiceApi-ejb/TipoEgresosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoPagosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoPagosManager == null) {
            try {

                tipoPagosManager = (TipoPagosManager) context.lookup("java:app/ServiceApi-ejb/TipoPagosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoReferenciaManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoReferenciaManager == null) {
            try {

                tipoReferenciaManager = (TipoReferenciaManager) context.lookup("java:app/ServiceApi-ejb/TipoReferenciaManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    
    protected void inicializarModalidadesManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (modalidadesManager == null) {
            try {

                modalidadesManager = (ModalidadesManager) context.lookup("java:app/ServiceApi-ejb/ModalidadesManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoCalculosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoCalculosManager == null) {
            try {

                tipoCalculosManager = (TipoCalculosManager) context.lookup("java:app/ServiceApi-ejb/TipoCalculosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoDesembolsosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoDesembolsosManager == null) {
            try {

                tipoDesembolsosManager = (TipoDesembolsosManager) context.lookup("java:app/ServiceApi-ejb/TipoDesembolsosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoDestinosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoDestinosManager == null) {
            try {

                tipoDestinosManager = (TipoDestinosManager) context.lookup("java:app/ServiceApi-ejb/TipoDestinosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoIngresosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoIngresosManager == null) {
            try {

                tipoIngresosManager = (TipoIngresosManager) context.lookup("java:app/ServiceApi-ejb/TipoIngresosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    
    protected void inicializarTipoGarantiasManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoGarantiasManager == null) {
            try {

                tipoGarantiasManager = (TipoGarantiasManager) context.lookup("java:app/ServiceApi-ejb/TipoGarantiasManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoVinculoManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoVinculoManager == null) {
            try {

                tipoVinculoManager = (TipoVinculoManager) context.lookup("java:app/ServiceApi-ejb/TipoVinculoManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarUsuarioManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (usuarioManager == null) {
            try {

                usuarioManager = (UsuarioManager) context.lookup("java:app/ServiceApi-ejb/UsuarioManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarEmpresaManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (empresaManager == null) {
            try {

                empresaManager = (EmpresaManager) context.lookup("java:app/ServiceApi-ejb/EmpresaManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarSucursalManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (sucursalManager == null) {
            try {

                sucursalManager = (SucursalManager) context.lookup("java:app/ServiceApi-ejb/SucursalManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarReferenciaManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (referenciaManager == null) {
            try {

                referenciaManager = (ReferenciaManager) context.lookup("java:app/ServiceApi-ejb/ReferenciaManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarRolManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (rolManager == null) {
            try {

                rolManager = (RolManager) context.lookup("java:app/ServiceApi-ejb/RolManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
}
