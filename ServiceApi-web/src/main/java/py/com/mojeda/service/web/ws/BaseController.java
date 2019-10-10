/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import py.com.mojeda.service.ejb.manager.BarriosManager;
import py.com.mojeda.service.ejb.manager.BienesManager;
import py.com.mojeda.service.ejb.manager.BienesSolicitudesManager;
import py.com.mojeda.service.ejb.manager.CiudadesManager;
import py.com.mojeda.service.ejb.manager.ClientesManager;
import py.com.mojeda.service.ejb.manager.CreditosManager;
import py.com.mojeda.service.ejb.manager.CuotasManager;
import py.com.mojeda.service.ejb.manager.DepartamentosPaisManager;
import py.com.mojeda.service.ejb.manager.EmpresaManager;
import py.com.mojeda.service.ejb.manager.ModalidadesManager;
import py.com.mojeda.service.ejb.manager.ReferenciaManager;
import py.com.mojeda.service.ejb.manager.RolManager;
import py.com.mojeda.service.ejb.manager.SucursalManager;
import py.com.mojeda.service.ejb.manager.TipoCalculosManager;
import py.com.mojeda.service.ejb.manager.TipoDesembolsosManager;
import py.com.mojeda.service.ejb.manager.TipoDestinosManager;
import py.com.mojeda.service.ejb.manager.TipoGarantiasManager;
import py.com.mojeda.service.ejb.manager.TipoPagosManager;
import py.com.mojeda.service.ejb.manager.TipoReferenciaManager;
import py.com.mojeda.service.ejb.manager.TipoVinculoManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;
import py.com.mojeda.service.ejb.manager.DepartamentosSucursalManager;
import py.com.mojeda.service.ejb.manager.PermisoManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.RolPermisoManager;
import py.com.mojeda.service.ejb.manager.DocumentoManager;
import py.com.mojeda.service.ejb.manager.EstadosCreditoManager;
import py.com.mojeda.service.ejb.manager.EstadosSolicitudManager;
import py.com.mojeda.service.ejb.manager.EstudiosManager;
import py.com.mojeda.service.ejb.manager.EvaluacionSolicitudesCabeceraManager;
import py.com.mojeda.service.ejb.manager.EvaluacionSolicitudesDetallesManager;
import py.com.mojeda.service.ejb.manager.IngresosEgresosManager;
import py.com.mojeda.service.ejb.manager.NacionalidadesManager;
import py.com.mojeda.service.ejb.manager.OcupacionPersonaManager;
import py.com.mojeda.service.ejb.manager.PaisesManager;
import py.com.mojeda.service.ejb.manager.PeriodoCapitalManager;
import py.com.mojeda.service.ejb.manager.ProfesionesManager;
import py.com.mojeda.service.ejb.manager.PropuestaSolicitudManager;
import py.com.mojeda.service.ejb.manager.TipoDocumentosManager;
import py.com.mojeda.service.ejb.manager.TipoIngresosEgresosManager;
import py.com.mojeda.service.ejb.manager.TipoOcupacionesManager;
import py.com.mojeda.service.ejb.manager.VinculoManager;
import py.com.mojeda.service.ejb.manager.FuncionarioDepartamentosManager;
import py.com.mojeda.service.ejb.manager.FuncionariosManager;
import py.com.mojeda.service.ejb.manager.IngresosEgresosSolicitudesManager;
import py.com.mojeda.service.ejb.manager.OcupacionSolicitudesManager;
import py.com.mojeda.service.ejb.manager.ReferenciaSolicitudesManager;
import py.com.mojeda.service.ejb.manager.TipoCargosManager;
import py.com.mojeda.service.ejb.manager.TipoEstudiosManager;
import py.com.mojeda.service.ejb.manager.TipoFuncionariosManager;
import py.com.mojeda.service.ejb.manager.TipoHorariosManager;
import py.com.mojeda.service.ejb.manager.TipoMotivosRetiroManager;

/**
 *
 * @author miguel.ojeda
 */
public class BaseController {
    
    protected static final String CONTENT_IMAGE = "C:\\imagen\\";
    
    protected static final DateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    private Context context;
    
    protected FuncionariosManager funcionarioManager;
    
    protected ClientesManager clientesManager;
    
    protected PersonaManager personaManager;
    
    protected OcupacionPersonaManager ocupacionPersonaManager;
    
    protected IngresosEgresosManager ingresosEgresosManager;
    
    protected BienesManager bienesManager;
    
    protected EmpresaManager empresaManager;
    
    protected SucursalManager sucursalManager;
    
    protected DepartamentosSucursalManager departamentosSucursalManager;
    
    protected FuncionarioDepartamentosManager usuarioDepartamentosManager;
    
    protected ReferenciaManager referenciaManager;
    
    protected ReferenciaSolicitudesManager referenciaSolicitudesManager;
    
    protected TipoReferenciaManager tipoReferenciaManager;
    
    protected TipoVinculoManager tipoVinculoManager;
    
    protected RolManager rolManager;
    
    protected RolPermisoManager rolPermisoManager;
    
    protected ModalidadesManager modalidadesManager;
            
    protected TipoCalculosManager tipoCalculosManager;
    
    protected TipoOcupacionesManager tipoOcupacionesManager;
            
    protected TipoDesembolsosManager tipoDesembolsosManager;
            
    protected TipoDestinosManager tipoDestinosManager;
            
    protected TipoGarantiasManager tipoGarantiasManager;
            
    protected TipoIngresosEgresosManager tipoEgresosManager;
    
    protected TipoPagosManager tipoPagosManager;
    
    protected TipoFuncionariosManager tipoFuncionariosManager;
    
    protected TipoEstudiosManager tipoEstudiosManager;
    
    protected EstudiosManager estudiosManager;
    
    protected TipoHorariosManager tipoHorariosManager;
    
    protected TipoCargosManager tipoCargosManager;
    
    protected TipoMotivosRetiroManager tipoMotivosRetiroManager;
    
    protected DocumentoManager documentoManager;
    
    protected TipoDocumentosManager tipoDocumentosManager;
    
    protected PermisoManager permisoManager;
    
    protected NacionalidadesManager nacionalidadesManager;
    
    protected PaisesManager paisesManager;
    
    protected DepartamentosPaisManager departamentosPaisManager;
    
    protected CiudadesManager ciudadesManager;
    
    protected BarriosManager barriosManager;
    
    protected ProfesionesManager profesionesManager;
    
    protected PropuestaSolicitudManager propuestaSolicitudManager;
    
    protected PeriodoCapitalManager periodoCapitalManager;
    
    protected VinculoManager vinculoManager;
    
    protected EstadosSolicitudManager estadosSolicitudManager; 
    
    protected EstadosCreditoManager estadosCreditoManager;
    
    protected OcupacionSolicitudesManager ocupacionSolicitudesManager;
    
    protected BienesSolicitudesManager bienesSolicitudesManager;
    
    protected IngresosEgresosSolicitudesManager ingresosEgresosSolicitudesManager;
    
    protected EvaluacionSolicitudesCabeceraManager evaluacionSolicitudesCabeceraManager;
    
    protected EvaluacionSolicitudesDetallesManager evaluacionSolicitudesDetallesManager;
    
    protected CuotasManager cuotasManager;
    
    protected CreditosManager creditosManager;
    
            
    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();
    
    protected void inicializarCreditosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (creditosManager == null) {
            try {
                creditosManager = (CreditosManager) context.lookup("java:app/ServiceApi-ejb/CreditosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarEstadosCreditoManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (estadosCreditoManager == null) {
            try {
                estadosCreditoManager = (EstadosCreditoManager) context.lookup("java:app/ServiceApi-ejb/EstadosCreditoManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarCuotasManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (cuotasManager == null) {
            try {
                cuotasManager = (CuotasManager) context.lookup("java:app/ServiceApi-ejb/CuotasManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarEvaluacionSolicitudesCabeceraManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (evaluacionSolicitudesCabeceraManager == null) {
            try {
                evaluacionSolicitudesCabeceraManager = (EvaluacionSolicitudesCabeceraManager) context.lookup("java:app/ServiceApi-ejb/EvaluacionSolicitudesCabeceraManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarEvaluacionSolicitudesDetallesManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (evaluacionSolicitudesDetallesManager == null) {
            try {
                evaluacionSolicitudesDetallesManager = (EvaluacionSolicitudesDetallesManager) context.lookup("java:app/ServiceApi-ejb/EvaluacionSolicitudesDetallesManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarIngresosEgresosSolicitudesManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (ingresosEgresosSolicitudesManager == null) {
            try {
                ingresosEgresosSolicitudesManager = (IngresosEgresosSolicitudesManager) context.lookup("java:app/ServiceApi-ejb/IngresosEgresosSolicitudesManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarBienesSolicitudesManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (bienesSolicitudesManager == null) {
            try {
                bienesSolicitudesManager = (BienesSolicitudesManager) context.lookup("java:app/ServiceApi-ejb/BienesSolicitudesManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarOcupacionSolicitudesManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (ocupacionSolicitudesManager == null) {
            try {
                ocupacionSolicitudesManager = (OcupacionSolicitudesManager) context.lookup("java:app/ServiceApi-ejb/OcupacionSolicitudesManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarEstadosSolicitudManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (estadosSolicitudManager == null) {
            try {
                estadosSolicitudManager = (EstadosSolicitudManager) context.lookup("java:app/ServiceApi-ejb/EstadosSolicitudManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarReferenciaSolicitudesManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (referenciaSolicitudesManager == null) {
            try {
                referenciaSolicitudesManager = (ReferenciaSolicitudesManager) context.lookup("java:app/ServiceApi-ejb/ReferenciaSolicitudesManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarPeriodoCapitalManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (periodoCapitalManager == null) {
            try {
                periodoCapitalManager = (PeriodoCapitalManager) context.lookup("java:app/ServiceApi-ejb/PeriodoCapitalManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarVinculoManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (vinculoManager == null) {
            try {
                vinculoManager = (VinculoManager) context.lookup("java:app/ServiceApi-ejb/VinculoManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarPropuestaSolicitudManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (propuestaSolicitudManager == null) {
            try {
                propuestaSolicitudManager = (PropuestaSolicitudManager) context.lookup("java:app/ServiceApi-ejb/PropuestaSolicitudManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarOcupacionPersonaManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (ocupacionPersonaManager == null) {
            try {
                ocupacionPersonaManager = (OcupacionPersonaManager) context.lookup("java:app/ServiceApi-ejb/OcupacionPersonaManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarIngresosEgresosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (ingresosEgresosManager == null) {
            try {
                ingresosEgresosManager = (IngresosEgresosManager) context.lookup("java:app/ServiceApi-ejb/IngresosEgresosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarBienesManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (bienesManager == null) {
            try {
                bienesManager = (BienesManager) context.lookup("java:app/ServiceApi-ejb/BienesManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoOcupacionesManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoOcupacionesManager == null) {
            try {
                tipoOcupacionesManager = (TipoOcupacionesManager) context.lookup("java:app/ServiceApi-ejb/TipoOcupacionesManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoCargosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoCargosManager == null) {
            try {
                tipoCargosManager = (TipoCargosManager) context.lookup("java:app/ServiceApi-ejb/TipoCargosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarProfesionesManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (profesionesManager == null) {
            try {
                profesionesManager = (ProfesionesManager) context.lookup("java:app/ServiceApi-ejb/ProfesionesManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarClientesManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (clientesManager == null) {
            try {
                clientesManager = (ClientesManager) context.lookup("java:app/ServiceApi-ejb/ClientesManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarBarriosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (barriosManager == null) {
            try {
                barriosManager = (BarriosManager) context.lookup("java:app/ServiceApi-ejb/BarriosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarCiudadesManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (ciudadesManager == null) {
            try {
                ciudadesManager = (CiudadesManager) context.lookup("java:app/ServiceApi-ejb/CiudadesManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarDepartamentosPaisManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (departamentosPaisManager == null) {
            try {
                departamentosPaisManager = (DepartamentosPaisManager) context.lookup("java:app/ServiceApi-ejb/DepartamentosPaisManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarPaisesManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (paisesManager == null) {
            try {
                paisesManager = (PaisesManager) context.lookup("java:app/ServiceApi-ejb/PaisesManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarNacionalidadesManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (nacionalidadesManager == null) {
            try {

                nacionalidadesManager = (NacionalidadesManager) context.lookup("java:app/ServiceApi-ejb/NacionalidadesManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarUsuarioDepartamentosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (usuarioDepartamentosManager == null) {
            try {

                usuarioDepartamentosManager = (FuncionarioDepartamentosManager) context.lookup("java:app/ServiceApi-ejb/UsuarioDepartamentosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoDocumentosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoDocumentosManager == null) {
            try {

                tipoDocumentosManager = (TipoDocumentosManager) context.lookup("java:app/ServiceApi-ejb/TipoDocumentosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarRolPermisoManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (rolPermisoManager == null) {
            try {

                rolPermisoManager = (RolPermisoManager) context.lookup("java:app/ServiceApi-ejb/RolPermisoManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarPermisoManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (permisoManager == null) {
            try {

                permisoManager = (PermisoManager) context.lookup("java:app/ServiceApi-ejb/PermisoManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarDocumentoManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (documentoManager == null) {
            try {

                documentoManager = (DocumentoManager) context.lookup("java:app/ServiceApi-ejb/DocumentoManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
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
    
    protected void inicializarTipoIngresosEgresosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoEgresosManager == null) {
            try {

                tipoEgresosManager = (TipoIngresosEgresosManager) context.lookup("java:app/ServiceApi-ejb/TipoIngresosEgresosManagerImpl");
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
    
    protected void inicializarTipoEstudiosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoEstudiosManager == null) {
            try {

                tipoEstudiosManager = (TipoEstudiosManager) context.lookup("java:app/ServiceApi-ejb/TipoEstudiosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarEstudiosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (estudiosManager == null) {
            try {

                estudiosManager = (EstudiosManager) context.lookup("java:app/ServiceApi-ejb/EstudiosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoHorariosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoHorariosManager == null) {
            try {

                tipoHorariosManager = (TipoHorariosManager) context.lookup("java:app/ServiceApi-ejb/TipoHorariosManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoMotivosRetiroManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoMotivosRetiroManager == null) {
            try {

                tipoMotivosRetiroManager = (TipoMotivosRetiroManager) context.lookup("java:app/ServiceApi-ejb/TipoMotivosRetiroManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }
    
    protected void inicializarTipoFuncionariosManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (tipoFuncionariosManager == null) {
            try {

                tipoFuncionariosManager = (TipoFuncionariosManager) context.lookup("java:app/ServiceApi-ejb/TipoFuncionariosManagerImpl");
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
    
    protected void inicializarFuncionarioManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (funcionarioManager == null) {
            try {

                funcionarioManager = (FuncionariosManager) context.lookup("java:app/ServiceApi-ejb/FuncionariosManagerImpl");
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
