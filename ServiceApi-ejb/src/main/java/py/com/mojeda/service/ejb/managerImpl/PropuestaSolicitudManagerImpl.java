/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.entity.BienesSolicitudes;
import py.com.mojeda.service.ejb.entity.Clientes;
import py.com.mojeda.service.ejb.entity.EstadosSolicitud;
import py.com.mojeda.service.ejb.entity.Funcionarios;
import py.com.mojeda.service.ejb.entity.Garantias;
import py.com.mojeda.service.ejb.entity.IngresosEgresos;
import py.com.mojeda.service.ejb.entity.IngresosEgresosSolicitudes;
import py.com.mojeda.service.ejb.entity.Modalidades;
import py.com.mojeda.service.ejb.entity.Monedas;
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.entity.OcupacionSolicitudes;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Profesiones;
import py.com.mojeda.service.ejb.entity.PropuestaSolicitud;
import py.com.mojeda.service.ejb.entity.Referencias;
import py.com.mojeda.service.ejb.entity.ReferenciasSolicitudes;
import py.com.mojeda.service.ejb.entity.Solicitantes;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.TipoCalculos;
import py.com.mojeda.service.ejb.entity.TipoDesembolsos;
import py.com.mojeda.service.ejb.entity.TipoDestinos;
import py.com.mojeda.service.ejb.entity.TipoGarantias;
import py.com.mojeda.service.ejb.entity.TipoIngresosEgresos;
import py.com.mojeda.service.ejb.entity.TipoPagos;
import py.com.mojeda.service.ejb.entity.Vinculos;
import py.com.mojeda.service.ejb.manager.BienesManager;
import py.com.mojeda.service.ejb.manager.BienesSolicitudesManager;
import py.com.mojeda.service.ejb.manager.ClientesManager;
import py.com.mojeda.service.ejb.manager.EstadosSolicitudManager;
import py.com.mojeda.service.ejb.manager.FuncionariosManager;
import py.com.mojeda.service.ejb.manager.GarantiasManager;
import py.com.mojeda.service.ejb.manager.IngresosEgresosManager;
import py.com.mojeda.service.ejb.manager.IngresosEgresosSolicitudesManager;
import py.com.mojeda.service.ejb.manager.ModalidadesManager;
import py.com.mojeda.service.ejb.manager.OcupacionPersonaManager;
import py.com.mojeda.service.ejb.manager.OcupacionSolicitudesManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.PropuestaSolicitudManager;
import py.com.mojeda.service.ejb.manager.ReferenciaManager;
import py.com.mojeda.service.ejb.manager.ReferenciaSolicitudesManager;
import py.com.mojeda.service.ejb.manager.SolicitantesManager;
import py.com.mojeda.service.ejb.manager.SucursalManager;
import py.com.mojeda.service.ejb.manager.TipoCalculosManager;
import py.com.mojeda.service.ejb.manager.TipoDesembolsosManager;
import py.com.mojeda.service.ejb.manager.TipoDestinosManager;
import py.com.mojeda.service.ejb.manager.TipoGarantiasManager;
import py.com.mojeda.service.ejb.manager.TipoPagosManager;
import py.com.mojeda.service.ejb.manager.VinculoManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;

/**
 *
 * @author Miguel
 */
@Stateless
public class PropuestaSolicitudManagerImpl extends GenericDaoImpl<PropuestaSolicitud, Long>
        implements PropuestaSolicitudManager {

    @Override
    protected Class<PropuestaSolicitud> getEntityBeanType() {
        return PropuestaSolicitud.class;
    }

    private static final ApplicationLogger logger = ApplicationLogger.getInstance();
    protected static final DateFormat dateFormatHHMM = new SimpleDateFormat("HH:mm");
    protected static final DateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @EJB(mappedName = "java:app/ServiceApi-ejb/ClientesManagerImpl")
    private ClientesManager clientesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/PersonaManagerImpl")
    private PersonaManager personaManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoDesembolsosManagerImpl")
    private TipoDesembolsosManager tipoDesembolsosManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoCalculosManagerImpl")
    private TipoCalculosManager tipoCalculosManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoGarantiasManagerImpl")
    private TipoGarantiasManager tipoGarantiasManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoPagosManagerImpl")
    private TipoPagosManager tipoPagosManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoDestinosManagerImpl")
    private TipoDestinosManager tipoDestinosManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/ModalidadesManagerImpl")
    private ModalidadesManager modalidadesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/FuncionariosManagerImpl")
    private FuncionariosManager funcionariosManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/SucursalManagerImpl")
    private SucursalManager sucursalManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/SolicitantesManagerImpl")
    private SolicitantesManager solicitantesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/GarantiasManagerImpl")
    private GarantiasManager garantiasManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/BienesSolicitudesManagerImpl")
    private BienesSolicitudesManager bienesSolicitudesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/BienesManagerImpl")
    private BienesManager bienesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/IngresosEgresosManagerImpl")
    private IngresosEgresosManager ingresosEgresosManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/IngresosEgresosSolicitudesManagerImpl")
    private IngresosEgresosSolicitudesManager ingresosEgresosSolicitudesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/OcupacionSolicitudesManagerImpl")
    private OcupacionSolicitudesManager ocupacionSolicitudesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/OcupacionPersonaManagerImpl")
    private OcupacionPersonaManager ocupacionPersonaManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/ReferenciaManagerImpl")
    private ReferenciaManager referenciaManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/ReferenciaSolicitudesManagerImpl")
    private ReferenciaSolicitudesManager referenciaSolicitudesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/VinculoManagerImpl")
    private VinculoManager vinculoManager;
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/EstadosSolicitudManagerImpl")
    private EstadosSolicitudManager estadosSolicitudManager;

    @Override
    public PropuestaSolicitud guardar(PropuestaSolicitud propuestaSolicitud, Long idSucursal) throws Exception {

        if (idSucursal != null
                && propuestaSolicitud != null) {
            //Cargar la sucursal
            propuestaSolicitud.setSucursal(new Sucursales(idSucursal));

            //Crear Cliente
            if (propuestaSolicitud.getCliente().getId() == null) {

                Clientes ejCliente = new Clientes();
                ejCliente.setPersona(new Personas(propuestaSolicitud.getCliente().getPersona().getId()));
                ejCliente.setSucursal(new Sucursales(idSucursal));

                Map<String, Object> modelMaps = clientesManager.getAtributos(ejCliente, "id".split(","));

                if (modelMaps != null) {
                    propuestaSolicitud.getCliente().setId(Long.parseLong(modelMaps.get("id").toString()));
                } else {
                    ejCliente.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejCliente.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    ejCliente.setActivo("S");
                    ejCliente.setIdUsuarioCreacion(propuestaSolicitud.getIdUsuarioCreacion());
                    ejCliente.setIdUsuarioModificacion(propuestaSolicitud.getIdUsuarioCreacion());

                    clientesManager.save(ejCliente);

                    propuestaSolicitud.getCliente().setId(ejCliente.getId());
                }

            }

            //Calcular descuentos
            Long descuentos = propuestaSolicitud.getImpuestos() + propuestaSolicitud.getComision() + propuestaSolicitud.getGastosVarios() + propuestaSolicitud.getSeguros();
            Long montoEntregar;
            if (propuestaSolicitud.getTipoDescuento().equals("I-D")) {
                montoEntregar = propuestaSolicitud.getMontoSolicitadoOriginal().longValue() - descuentos;
                propuestaSolicitud.setImporteEntregar(montoEntregar);
                if (propuestaSolicitud.getMontoSolicitadoOriginal() != propuestaSolicitud.getMontoSolicitado()) {
                    propuestaSolicitud.setMontoSolicitado(propuestaSolicitud.getMontoSolicitadoOriginal());
                }
            } else {
                montoEntregar = (propuestaSolicitud.getMontoSolicitadoOriginal().longValue() + descuentos) - descuentos;
                propuestaSolicitud.setImporteEntregar(montoEntregar);
                if (new BigDecimal(montoEntregar) != propuestaSolicitud.getMontoSolicitado()) {
                    propuestaSolicitud.setMontoSolicitado(new BigDecimal(propuestaSolicitud.getMontoSolicitadoOriginal().longValue() + descuentos));
                }
            }

            //Calcular Cuota           
            Long cuota = this.calcularCuota(propuestaSolicitud.getModalidad().getInteres().longValue(), propuestaSolicitud.getPlazo(), propuestaSolicitud.getPeriodoCapital().longValue(), propuestaSolicitud.getVencimientoInteres(),
                    propuestaSolicitud.getTasaInteres().doubleValue(), propuestaSolicitud.getMontoSolicitado().longValue(), propuestaSolicitud.getTipoCalculoImporte(), propuestaSolicitud.getGastosAdministrativos().doubleValue());

            if (propuestaSolicitud.getImporteCuota() != cuota) {
                propuestaSolicitud.setImporteCuota(cuota);
            }

            propuestaSolicitud.setFechaGeneracion(new Date(System.currentTimeMillis()));
            propuestaSolicitud.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            propuestaSolicitud.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            propuestaSolicitud.setActivo("S");
            propuestaSolicitud.setFechaPresentacion(new Timestamp(System.currentTimeMillis()));
            propuestaSolicitud.setHoraPresentacion(dateFormatHHMM.format(new Timestamp(System.currentTimeMillis())));
            propuestaSolicitud.setFechaEstado(new Timestamp(System.currentTimeMillis()));
            propuestaSolicitud.setHoraEstado(dateFormatHHMM.format(new Timestamp(System.currentTimeMillis())));
            propuestaSolicitud.setEstado(new EstadosSolicitud(1L));

            this.save(propuestaSolicitud);

            if (propuestaSolicitud.getId() != null && propuestaSolicitud != null) {
                //Cargar Solicitantes de la propuesta
                //GUARDAR SOLICITUD/GARANTIA DEUDOR
                Personas deudor = personaManager.get(propuestaSolicitud.getCliente().getPersona());

                propuestaSolicitud.getCliente().setPersona(deudor);

                Solicitantes ejSolicitantes = new Solicitantes();
                ejSolicitantes.setBarrio(deudor.getBarrio());
                ejSolicitantes.setCiudad(deudor.getCiudad());
                ejSolicitantes.setDepartamento(deudor.getDepartamento());
                ejSolicitantes.setPais(deudor.getPais());
                ejSolicitantes.setDireccionParticular(deudor.getDireccionParticular());
                ejSolicitantes.setFechaDireccion(new Date(System.currentTimeMillis()));
                ejSolicitantes.setLatitud(deudor.getLatitud());
                ejSolicitantes.setLongitud(deudor.getLongitud());
                ejSolicitantes.setCantHijos(deudor.getNumeroHijos());
                ejSolicitantes.setCantPersonasACargo(deudor.getNumeroDependientes());
                ejSolicitantes.setEstadoCivil(deudor.getEstadoCivil());
                ejSolicitantes.setFechaNacimiento(deudor.getFechaNacimiento());
                ejSolicitantes.setIdPersona(new Personas(deudor.getId()));
                ejSolicitantes.setProfesiones(deudor.getProfesion());
                ejSolicitantes.setPropuestaSolicitud(new PropuestaSolicitud(propuestaSolicitud.getId()));
                ejSolicitantes.setTelefonoParticular(deudor.getTelefonoParticular() + " / " + (deudor.getTelefonoSecundario() == null ? "" : deudor.getTelefonoSecundario()));
                ejSolicitantes.setTipoGarantias(propuestaSolicitud.getTipoGarantia());
                ejSolicitantes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejSolicitantes.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                ejSolicitantes.setTipoRelacion("DEUDOR");

                if (deudor.getEstadoCivil().compareToIgnoreCase("CASADO/A") == 0
                        && deudor.getSeparacionBienes()) {

                    Vinculos ejVinculo = new Vinculos();
                    ejVinculo.setPersona(new Personas(deudor.getId()));
                    ejVinculo.setTipoVinculo("CONYUGE");
                    ejVinculo.setActivo("S");

                    Map<String, Object> conyugeMap = vinculoManager.getAtributos(ejVinculo, "personaVinculo.id,personaVinculo.profesion.id,personaVinculo.numeroHijos,personaVinculo.numeroDependientes".split(","));

                    if (conyugeMap != null) {
                        ejSolicitantes.setIdPersonaConyuge(new Personas(Long.parseLong(conyugeMap.get("personaVinculo.id").toString())));
                        ejSolicitantes.setConsiderarConyuge(true);
                        ejSolicitantes.setCantPersonasACargoCony(conyugeMap.get("numeroDependientes") == null ? null : Integer.parseInt(conyugeMap.get("numeroDependientes").toString()));
                        ejSolicitantes.setCantHijosCony(conyugeMap.get("numeroHijos") == null ? null : Integer.parseInt(conyugeMap.get("numeroHijos").toString()));
                        ejSolicitantes.setFechaDireccionCony(new Timestamp(System.currentTimeMillis()));
                        ejSolicitantes.setProfesionesCony(new Profesiones(Long.parseLong(conyugeMap.get("personaVinculo.profesion.id").toString())));
                    }
                }

                solicitantesManager.save(ejSolicitantes);

                Garantias ejGarantias = new Garantias();
                ejGarantias.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                ejGarantias.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejGarantias.setEstado("PENDIENTE");
                ejGarantias.setFechaEstado(new Timestamp(System.currentTimeMillis()));
                ejGarantias.setPersona(new Personas(deudor.getId()));
                ejGarantias.setPropuestaSolicitud(new PropuestaSolicitud(propuestaSolicitud.getId()));
                ejGarantias.setRiesgoAsumido(propuestaSolicitud.getMontoSolicitado());
                ejGarantias.setTipoGarantias(propuestaSolicitud.getTipoGarantia());
                ejGarantias.setTipoRelacion("DEUDOR");

                garantiasManager.save(ejGarantias);

                //GUARDAR BIENES
                Bienes ejBienes = new Bienes();
                ejBienes.setActivo("S");
                ejBienes.setPersona(new Personas(deudor.getId()));
                //ejBienes.setTipoBien("INMUEBLE");
                this.guardarBienes(bienesManager.list(ejBienes), propuestaSolicitud.getId(), propuestaSolicitud.getFuncionario().getId());

                //GUARDAR INGRESOS/EGRESOS
                IngresosEgresos ejIngresosEgresos = new IngresosEgresos();
                ejIngresosEgresos.setActivo("S");
                ejIngresosEgresos.setPersona(new Personas(deudor.getId()));
                this.guardarIngresosEgresosSolicitud(ingresosEgresosManager.list(ejIngresosEgresos), propuestaSolicitud.getId(), propuestaSolicitud.getFuncionario().getId());

                //GUARDAR OCUPACIONES
                OcupacionPersona ejOcupacionPersona = new OcupacionPersona();
                ejOcupacionPersona.setPersona(new Personas(deudor.getId()));
                ejOcupacionPersona.setActivo("S");
                this.guardarOcupaciones(ocupacionPersonaManager.list(ejOcupacionPersona), propuestaSolicitud.getId(), propuestaSolicitud.getFuncionario().getId());

                //GUARDAR REFERENCIAS
                Referencias ejReferencias = new Referencias();
                ejReferencias.setPersona(new Personas(deudor.getId()));
                ejReferencias.setActivo("S");
                this.guardarReferencias(referenciaManager.list(ejReferencias), propuestaSolicitud.getId(), propuestaSolicitud.getFuncionario().getId());

                if (propuestaSolicitud.getCodeudor() != null
                        && propuestaSolicitud.getCodeudor().getId() != null
                        && propuestaSolicitud.getTipoGarantia().getCodigo().equals("TG-2")) {

                    Personas codeudor = personaManager.get(propuestaSolicitud.getCodeudor());

                    propuestaSolicitud.setCodeudor(codeudor);

                    //GUARDAR SOLICITUD/GARANTIA CODEUDOR
                    ejSolicitantes = new Solicitantes();
                    ejSolicitantes.setBarrio(propuestaSolicitud.getCodeudor().getBarrio());
                    ejSolicitantes.setCiudad(propuestaSolicitud.getCodeudor().getCiudad());
                    ejSolicitantes.setDepartamento(propuestaSolicitud.getCodeudor().getDepartamento());
                    ejSolicitantes.setPais(propuestaSolicitud.getCodeudor().getPais());
                    ejSolicitantes.setDireccionParticular(propuestaSolicitud.getCodeudor().getDireccionParticular());
                    ejSolicitantes.setFechaDireccion(new Date(System.currentTimeMillis()));
                    ejSolicitantes.setLatitud(propuestaSolicitud.getCodeudor().getLatitud());
                    ejSolicitantes.setLongitud(propuestaSolicitud.getCodeudor().getLongitud());
                    ejSolicitantes.setCantHijos(propuestaSolicitud.getCodeudor().getNumeroHijos());
                    ejSolicitantes.setCantPersonasACargo(propuestaSolicitud.getCodeudor().getNumeroDependientes());
                    ejSolicitantes.setEstadoCivil(propuestaSolicitud.getCodeudor().getEstadoCivil());
                    ejSolicitantes.setFechaNacimiento(propuestaSolicitud.getCodeudor().getFechaNacimiento());
                    ejSolicitantes.setIdPersona(new Personas(propuestaSolicitud.getCodeudor().getId()));
                    ejSolicitantes.setProfesiones(propuestaSolicitud.getCodeudor().getProfesion());
                    ejSolicitantes.setPropuestaSolicitud(new PropuestaSolicitud(propuestaSolicitud.getId()));
                    ejSolicitantes.setTelefonoParticular(propuestaSolicitud.getCodeudor().getTelefonoParticular() + " / " + (propuestaSolicitud.getCodeudor().getTelefonoSecundario() == null ? "" : propuestaSolicitud.getCodeudor().getTelefonoSecundario()));
                    ejSolicitantes.setTipoGarantias(propuestaSolicitud.getTipoGarantia());
                    ejSolicitantes.setTipoRelacion("CODEUDOR");
                    ejSolicitantes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejSolicitantes.setFechaCreacion(new Timestamp(System.currentTimeMillis()));

                    if (propuestaSolicitud.getCodeudor().getEstadoCivil().compareToIgnoreCase("CASADO/A") == 0
                            && propuestaSolicitud.getCodeudor().getSeparacionBienes()) {

                        Vinculos ejVinculo = new Vinculos();
                        ejVinculo.setPersona(new Personas(propuestaSolicitud.getCodeudor().getId()));
                        ejVinculo.setTipoVinculo("CONYUGE");
                        ejVinculo.setActivo("S");

                        Map<String, Object> conyugeMap = vinculoManager.getAtributos(ejVinculo, "personaVinculo.id,personaVinculo.profesion.id,personaVinculo.numeroHijos,personaVinculo.numeroDependientes".split(","));

                        if (conyugeMap != null) {
                            ejSolicitantes.setIdPersonaConyuge(new Personas(Long.parseLong(conyugeMap.get("personaVinculo.id").toString())));
                            ejSolicitantes.setConsiderarConyuge(true);
                            ejSolicitantes.setCantPersonasACargoCony(conyugeMap.get("numeroDependientes") == null ? null : Integer.parseInt(conyugeMap.get("numeroDependientes").toString()));
                            ejSolicitantes.setCantHijosCony(conyugeMap.get("numeroHijos") == null ? null : Integer.parseInt(conyugeMap.get("numeroHijos").toString()));
                            ejSolicitantes.setFechaDireccionCony(new Timestamp(System.currentTimeMillis()));
                            ejSolicitantes.setProfesionesCony(new Profesiones(Long.parseLong(conyugeMap.get("personaVinculo.profesion.id").toString())));
                        }
                    }

                    solicitantesManager.save(ejSolicitantes);

                    ejGarantias = new Garantias();
                    ejGarantias.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    ejGarantias.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejGarantias.setEstado("PENDIENTE");
                    ejGarantias.setFechaEstado(new Timestamp(System.currentTimeMillis()));
                    ejGarantias.setPersona(new Personas(propuestaSolicitud.getCodeudor().getId()));
                    ejGarantias.setPropuestaSolicitud(new PropuestaSolicitud(propuestaSolicitud.getId()));
                    ejGarantias.setRiesgoAsumido(propuestaSolicitud.getMontoSolicitado());
                    ejGarantias.setTipoGarantias(propuestaSolicitud.getTipoGarantia());
                    ejGarantias.setTipoRelacion("CODEUDOR");

                    garantiasManager.save(ejGarantias);

                    //GUARDAR BIENES
                    ejBienes = new Bienes();
                    ejBienes.setActivo("S");
                    ejBienes.setPersona(new Personas(propuestaSolicitud.getCodeudor().getId()));
                    //ejBienes.setTipoBien("INMUEBLE");
                    this.guardarBienes(bienesManager.list(ejBienes), propuestaSolicitud.getId(), propuestaSolicitud.getFuncionario().getId());

                    //GUARDAR INGRESOS/EGRESOS
                    ejIngresosEgresos = new IngresosEgresos();
                    ejIngresosEgresos.setActivo("S");
                    ejIngresosEgresos.setPersona(new Personas(propuestaSolicitud.getCodeudor().getId()));
                    this.guardarIngresosEgresosSolicitud(ingresosEgresosManager.list(ejIngresosEgresos), propuestaSolicitud.getId(), propuestaSolicitud.getFuncionario().getId());

                    //GUARDAR OCUPACIONES
                    ejOcupacionPersona = new OcupacionPersona();
                    ejOcupacionPersona.setPersona(new Personas(propuestaSolicitud.getCodeudor().getId()));
                    ejOcupacionPersona.setActivo("S");
                    this.guardarOcupaciones(ocupacionPersonaManager.list(ejOcupacionPersona), propuestaSolicitud.getId(), propuestaSolicitud.getFuncionario().getId());

                    //GUARDAR REFERENCIAS
                    ejReferencias = new Referencias();
                    ejReferencias.setPersona(new Personas(propuestaSolicitud.getCodeudor().getId()));
                    ejReferencias.setActivo("S");
                    this.guardarReferencias(referenciaManager.list(ejReferencias), propuestaSolicitud.getId(), propuestaSolicitud.getFuncionario().getId());

                }

            }

        }
        return propuestaSolicitud;
    }

    @Override
    public PropuestaSolicitud editar(PropuestaSolicitud propuestaSolicitud, Long idSucursal) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PropuestaSolicitud getPropuestaSolicitud(PropuestaSolicitud propuestaSolicitud) throws Exception {
        
        String atributos = "id,fechaPresentacion,horaPresentacion,estado.id,fechaEstado,puntaje,montoSolicitado,tipoCredito,tipoDescuento,tasaInteres,"
                + "gastosAdministrativos,impuestos,comision,gastosVarios,seguros,vencimientoInteres,tipoDesembolso.id,tipoCalculoImporte.id,tipoGarantia.id,"
                + "tipoPago.id,tipoDestino.id,tipoInteres,ordenCheque,formaPago,plazo,periodoGracia,periodoCapital,periodoInteres,plazoOperacion,idAhorroDebito,"
                + "idAhorroDesembolso,fechaAnalisis,fechaAprobacion,fechaRechazo,observacionesDepartamento,fechaGeneracion,primerVencimiento,montoSolicitadoOriginal,"
                + "horaEstado,observacionFormalizador,importeCuota,importeEntregar,detalleDestino,beneficiarioCheque,tipoEnvioSolicitud,moneda.id,modalidad.id,"
                + "cliente.id,cliente.persona.id,codeudor.id,funcionario.id,";
        
        if(propuestaSolicitud == null
                || (propuestaSolicitud != null && propuestaSolicitud.getId() == null)){
            return null;
        }
        
        Map<String, Object> modelMaps = this.getAtributos(propuestaSolicitud, atributos.split(","));
        
        if(modelMaps == null){
            return null;
        }
        
        propuestaSolicitud = new PropuestaSolicitud();
        propuestaSolicitud.setActivo(atributos);
        propuestaSolicitud.setBeneficiarioCheque(atributos);
        
        if(modelMaps.get("cliente.id") == null){
            return null;
        }
        
        Clientes ejCliente = new Clientes();
        ejCliente.setPersona(personaManager.getPersona(new Personas(Long.parseLong(modelMaps.get("cliente.persona.id").toString()))));        
        propuestaSolicitud.setCliente(ejCliente);
        
        if(modelMaps.get("codeudor.id") != null){
            propuestaSolicitud.setCodeudor(personaManager.getPersona(new Personas(Long.parseLong(modelMaps.get("codeudor.id").toString()))));
        }
        
        propuestaSolicitud.setComision(modelMaps.get("comision") == null ? null:  Long.parseLong(modelMaps.get("comision").toString()));
        propuestaSolicitud.setDetalleDestino(modelMaps.get("detalleDestino") == null ? "" : modelMaps.get("detalleDestino").toString());
        propuestaSolicitud.setEstado(modelMaps.get("estado.id") == null ? null:  estadosSolicitudManager.get(Long.parseLong(modelMaps.get("estado.id").toString())));
        propuestaSolicitud.setFechaAnalisis(modelMaps.get("fechaAnalisis") == null ? null : dateFormat.parse(modelMaps.get("fechaAnalisis").toString()));
        propuestaSolicitud.setFechaAprobacion(modelMaps.get("fechaAprobacion") == null ? null : dateFormat.parse(modelMaps.get("fechaAprobacion").toString()));
        propuestaSolicitud.setFechaEstado(modelMaps.get("fechaEstado") == null ? null : dateFormat.parse(modelMaps.get("fechaEstado").toString()));
        propuestaSolicitud.setFechaGeneracion(modelMaps.get("fechaGeneracion") == null ? null : dateFormat.parse(modelMaps.get("fechaGeneracion").toString()));
        propuestaSolicitud.setFechaPresentacion(modelMaps.get("fechaPresentacion") == null ? null : dateFormat.parse(modelMaps.get("fechaPresentacion").toString()));
        propuestaSolicitud.setFechaRechazo(modelMaps.get("fechaRechazo") == null ? null : dateFormat.parse(modelMaps.get("fechaRechazo").toString()));   
        propuestaSolicitud.setFormaPago(modelMaps.get("formaPago") == null ? "" : modelMaps.get("formaPago").toString());
        if(modelMaps.get("funcionario.id") != null){
            propuestaSolicitud.setFuncionario(funcionariosManager.getUsuario(new Funcionarios(Long.parseLong(modelMaps.get("funcionario.id").toString())),null));
        }        
        propuestaSolicitud.setGastosAdministrativos(modelMaps.get("gastosAdministrativos") == null ? null : new BigDecimal(modelMaps.get("gastosAdministrativos").toString()));
        propuestaSolicitud.setGastosVarios(modelMaps.get("gastosVarios") == null ? null:  Long.parseLong(modelMaps.get("gastosVarios").toString()));
        propuestaSolicitud.setHoraEstado(modelMaps.get("horaEstado") == null ? "" : modelMaps.get("horaEstado").toString());
        propuestaSolicitud.setHoraPresentacion(modelMaps.get("horaPresentacion") == null ? "" : modelMaps.get("horaPresentacion").toString());
        propuestaSolicitud.setId(modelMaps.get("id") == null ? null:  Long.parseLong(modelMaps.get("id").toString()));
        propuestaSolicitud.setImporteCuota(modelMaps.get("importeCuota") == null ? null:  Long.parseLong(modelMaps.get("importeCuota").toString()));
        propuestaSolicitud.setImporteEntregar(modelMaps.get("importeEntregar") == null ? null:  Long.parseLong(modelMaps.get("importeEntregar").toString()));
        propuestaSolicitud.setImpuestos(modelMaps.get("impuestos") == null ? null:  Long.parseLong(modelMaps.get("impuestos").toString()));
        propuestaSolicitud.setModalidad(modelMaps.get("modalidad.id") == null ? null:  modalidadesManager.get(new Modalidades(Long.parseLong(modelMaps.get("modalidad.id").toString()))));
        propuestaSolicitud.setMoneda(modelMaps.get("moneda.id") == null ? null:  new Monedas(Long.parseLong(modelMaps.get("moneda.id").toString())));
        propuestaSolicitud.setMontoSolicitado(modelMaps.get("montoSolicitado") == null ? null : new BigDecimal(modelMaps.get("montoSolicitado").toString()));
        propuestaSolicitud.setMontoSolicitadoOriginal(modelMaps.get("montoSolicitadoOriginal") == null ? null : new BigDecimal(modelMaps.get("montoSolicitadoOriginal").toString()));
        //propuestaSolicitud.setNumeroLegajoAdm(modelMaps.get("pais.id") == null ? null:  Long.parseLong(modelMaps.get("pais.id").toString()));
        //propuestaSolicitud.setObservacionFormalizador(modelMaps.get("numeroFinca") == null ? "" : modelMaps.get("numeroFinca").toString());
        //propuestaSolicitud.setObservacionesComite(modelMaps.get("numeroFinca") == null ? "" : modelMaps.get("numeroFinca").toString());
        propuestaSolicitud.setObservacionesDepartamento(modelMaps.get("observacionesDepartamento") == null ? "" : modelMaps.get("observacionesDepartamento").toString());
        propuestaSolicitud.setOrdenCheque(modelMaps.get("ordenCheque") == null ? "" : modelMaps.get("ordenCheque").toString());
        propuestaSolicitud.setPeriodoCapital(modelMaps.get("periodoCapital") == null ? null:  Short.parseShort(modelMaps.get("periodoCapital").toString()));
        propuestaSolicitud.setPeriodoGracia(modelMaps.get("periodoGracia") == null ? null:  Short.parseShort(modelMaps.get("periodoGracia").toString()));
        propuestaSolicitud.setPeriodoInteres(modelMaps.get("periodoInteres") == null ? null:  Short.parseShort(modelMaps.get("periodoInteres").toString()));
        propuestaSolicitud.setPlazo(modelMaps.get("plazo") == null ? null:  Long.parseLong(modelMaps.get("plazo").toString()));
        propuestaSolicitud.setPlazoOperacion(modelMaps.get("plazoOperacion") == null ? null:  Short.parseShort(modelMaps.get("plazoOperacion").toString()));
        propuestaSolicitud.setPrimerVencimiento(modelMaps.get("primerVencimiento") == null ? null : dateFormat.parse(modelMaps.get("primerVencimiento").toString()));
        propuestaSolicitud.setPuntaje(modelMaps.get("puntaje") == null ? null:  Short.parseShort(modelMaps.get("puntaje").toString()));
        propuestaSolicitud.setSeguros(modelMaps.get("seguros") == null ? null:  Long.parseLong(modelMaps.get("seguros").toString()));
        propuestaSolicitud.setTasaInteres(modelMaps.get("tasaInteres") == null ? null : new BigDecimal(modelMaps.get("tasaInteres").toString()));
        propuestaSolicitud.setTipoCalculoImporte(modelMaps.get("tipoCalculoImporte.id") == null ? null:  tipoCalculosManager.get(Long.parseLong(modelMaps.get("tipoCalculoImporte.id").toString())));
        propuestaSolicitud.setTipoCredito(modelMaps.get("tipoCredito") == null ? null:  modelMaps.get("tipoCredito").toString());
        propuestaSolicitud.setTipoDescuento(modelMaps.get("tipoDescuento") == null ? null:  modelMaps.get("tipoDescuento").toString());
        propuestaSolicitud.setTipoDesembolso(modelMaps.get("tipoDesembolso.id") == null ? null: tipoDesembolsosManager.get(Long.parseLong(modelMaps.get("tipoDesembolso.id").toString())) );
        propuestaSolicitud.setTipoDestino(modelMaps.get("tipoDestino.id") == null ? null:  tipoDestinosManager.get(Long.parseLong(modelMaps.get("tipoDestino.id").toString())));
        propuestaSolicitud.setTipoEnvioSolicitud(modelMaps.get("tipoEnvioSolicitud") == null ? null:  modelMaps.get("tipoEnvioSolicitud").toString());
        propuestaSolicitud.setTipoGarantia(modelMaps.get("tipoGarantia.id") == null ? null:  tipoGarantiasManager.get(Long.parseLong(modelMaps.get("tipoGarantia.id").toString())));
        propuestaSolicitud.setTipoInteres(modelMaps.get("tipoInteres") == null ? null:  modelMaps.get("tipoInteres").toString());
        propuestaSolicitud.setTipoPago(modelMaps.get("tipoPago.id") == null ? null:  tipoPagosManager.get(Long.parseLong(modelMaps.get("tipoPago.id").toString())));
        propuestaSolicitud.setVencimientoInteres(modelMaps.get("vencimientoInteres") == null ? null:  Long.parseLong(modelMaps.get("vencimientoInteres").toString()));
        
        return propuestaSolicitud;
    }

    public Long calcularCuota(Long modalidad, Long plazo, Long periodoCapital, Long vencimientoInteres,
            Double tasaInteres, Long montoSolicitado, TipoCalculos tipoCalculoImporte, Double gastosAdministrativos) {
        try {
            if (tipoCalculoImporte != null && "TC-2".equals(tipoCalculoImporte.getCodigo())) {

                Double interes = (gastosAdministrativos == null ? 0 : gastosAdministrativos) + tasaInteres;

                Double valor_1 = ((montoSolicitado * interes) / 36500) * vencimientoInteres;

                Long valor_2 = Math.round(Math.pow((1 + ((interes / 36500) * vencimientoInteres)), plazo)) - 1;

                Long valor_3 = Math.round(Math.pow((1 + ((interes / 36500) * vencimientoInteres)), plazo));

                Double valor_4 = valor_1 / valor_2;

                //this.myForm.controls['importeCuota'].setValue(Math.round(valor_4 * valor_3));
                return (Math.round(valor_4 * valor_3));

            } else if (tipoCalculoImporte != null && "TC-4".equals(tipoCalculoImporte.getCodigo())) {

                //Interés simple (i) = Capital (c) x Tipo de Interés (r) x Tiempo (t)
                //• Si la duración es 3 años, t = 3
                //• Si la duración es 18 meses, t = 18 / 12 = 1,5
                //• Si la duración es 1 año, t = 1
                //• Si la duración es 6 meses, t = 6 / 12 = 0,5
                //• Si la duración es 1 día, t = 1 / 365
                Double interes = this.calcularInteres(gastosAdministrativos, tasaInteres);

                Double periodoInteres = this.periodoInteresSimple(plazo, interes, periodoCapital, vencimientoInteres);

                Long montoInteres = Math.round(montoSolicitado * periodoInteres * plazo);

                Long montoTotal = (montoSolicitado + montoInteres);

                Long montoCuota = montoTotal / plazo;

                //this.myForm.controls['importeCuota'].setValue();
                return montoCuota;

            } else if (tipoCalculoImporte != null && "TC-5".equals(tipoCalculoImporte.getCodigo())) {

                Double interes = this.calcularInteres(gastosAdministrativos, tasaInteres);

                Double periodoInteres = this.periodoInteresCompuesto(plazo, interes, periodoCapital, vencimientoInteres);

                Long montoInteres = Math.round(montoSolicitado * periodoInteres);

                Long montoTotal = montoSolicitado + montoInteres;

                Long montoCuota = montoTotal / plazo;

                //this.myForm.controls['importeCuota'].setValue(Math.round(montoCuota));
                return montoCuota;
            }
        } catch (Exception e) {
            logger.error("Error al calcularCuota", e);
        }
        return null;

    }

    public Double calcularInteres(Double tasaInteres, Double gastosAdministrativos) {
        try {
            Double interes = ((gastosAdministrativos == null ? 0 : gastosAdministrativos) + tasaInteres) / 100;

            return interes;
        } catch (Exception e) {
            logger.error("Error al calcularInteres", e);
        }
        return null;

    }

    public Double periodoInteresSimple(Long plazo, Double interes, Long periodoCapital, Long vencimientoInteres) {
        try {
            Double periodoInteres = 0.0;

            if (vencimientoInteres == 30) {

                periodoInteres = interes / 12;

            } else if (vencimientoInteres == 0) {

                if (periodoCapital == 60) {

                    periodoInteres = interes / 6;

                } else if (periodoCapital == 90) {

                    periodoInteres = interes / 4;

                } else if (periodoCapital == 180) {

                    periodoInteres = interes / 2;

                } else if (periodoCapital == 360) {

                    periodoInteres = interes / 1;

                } else if (periodoCapital == 15) {

                    periodoInteres = interes / 24;

                } else if (periodoCapital == 1) {

                    periodoInteres = interes / 36;

                } else if (periodoCapital == 30) {

                    periodoInteres = interes / 12;

                }
            }

            return periodoInteres;
        } catch (Exception e) {
            logger.error("Error al periodoInteresSimple", e);
        }
        return null;

    }

    public Double periodoInteresCompuesto(Long plazo, Double interes, Long periodoCapital, Long vencimientoInteres) {
        try {
            Double periodoInteres = 0.0;
            if (vencimientoInteres == 30) {

                periodoInteres = (Math.pow((1 + interes), plazo / 12)) - 1;

            } else if (vencimientoInteres == 0) {

                if (periodoCapital == 60) {

                    periodoInteres = (Math.pow((1 + (interes / 6)), ((plazo / 12) * 6))) - 1;

                } else if (periodoCapital == 90) {

                    periodoInteres = (Math.pow((1 + (interes / 4)), ((plazo / 12) * 4))) - 1;

                } else if (periodoCapital == 180) {

                    periodoInteres = (Math.pow((1 + (interes / 2)), ((plazo / 12) * 2))) - 1;

                } else if (periodoCapital == 360) {

                    periodoInteres = (Math.pow((1 + interes), plazo / 12)) - 1;

                } else if (periodoCapital == 15) {

                    periodoInteres = interes / 24;

                } else if (periodoCapital == 1) {

                    periodoInteres = (Math.pow((1 + (interes / 2)), ((plazo / 12) * 2))) - 1;

                } else if (periodoCapital == 30) {

                    periodoInteres = (Math.pow((1 + interes), plazo / 12)) - 1;

                }
            }
            return periodoInteres;
        } catch (Exception e) {
            logger.error("Error al periodoInteresSimple", e);
        }
        return null;

    }

    public void guardarIngresosEgresosSolicitud(List<IngresosEgresos> ingresosEgresos, Long idSolicitud, Long idUsuarioAlta) throws Exception {
        IngresosEgresosSolicitudes ejIngresosEgresosSolicitudes;
        for (IngresosEgresos rpm : (ingresosEgresos == null ? new ArrayList<IngresosEgresos>() : ingresosEgresos)) {

            ejIngresosEgresosSolicitudes = new IngresosEgresosSolicitudes();
            ejIngresosEgresosSolicitudes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            ejIngresosEgresosSolicitudes.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            ejIngresosEgresosSolicitudes.setIdUsuarioModificacion(idUsuarioAlta);
            ejIngresosEgresosSolicitudes.setIdUsuarioCreacion(idUsuarioAlta);
            ejIngresosEgresosSolicitudes.setActivo("S");
            ejIngresosEgresosSolicitudes.setPersona(rpm.getPersona());
            ejIngresosEgresosSolicitudes.setPropuestaSolicitud(new PropuestaSolicitud(idSolicitud));
            ejIngresosEgresosSolicitudes.setTipoIngresosEgresos(rpm.getTipoIngresosEgresos());
            ejIngresosEgresosSolicitudes.setMonto(rpm.getMonto());

            ingresosEgresosSolicitudesManager.guardarIngresosEgresos(ejIngresosEgresosSolicitudes);
        }
    }

    public void guardarOcupaciones(List<OcupacionPersona> ocupacionPersona, Long idSolicitud, Long idUsuarioAlta) throws Exception {
        OcupacionSolicitudes ejOcupacionSolicitudes;
        for (OcupacionPersona rpm : (ocupacionPersona == null ? new ArrayList<OcupacionPersona>() : ocupacionPersona)) {
            ejOcupacionSolicitudes = new OcupacionSolicitudes();
            ejOcupacionSolicitudes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            ejOcupacionSolicitudes.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            ejOcupacionSolicitudes.setIdUsuarioModificacion(idUsuarioAlta);
            ejOcupacionSolicitudes.setIdUsuarioCreacion(idUsuarioAlta);
            ejOcupacionSolicitudes.setActivo("S");
            ejOcupacionSolicitudes.setPersona(rpm.getPersona());
            ejOcupacionSolicitudes.setPropuestaSolicitud(new PropuestaSolicitud(idSolicitud));
            ejOcupacionSolicitudes.setCargo(rpm.getCargo());
            ejOcupacionSolicitudes.setDireccion(rpm.getDireccion());
            ejOcupacionSolicitudes.setEmpresa(rpm.getEmpresa());
            ejOcupacionSolicitudes.setFechaIngreso(rpm.getFechaIngreso());
            ejOcupacionSolicitudes.setFechaSalida(rpm.getFechaSalida());
            ejOcupacionSolicitudes.setIdUsuarioModificacion(rpm.getIdUsuarioModificacion());
            ejOcupacionSolicitudes.setIngresosMensuales(rpm.getIngresosMensuales());
            ejOcupacionSolicitudes.setInterno(rpm.getInterno());
            ejOcupacionSolicitudes.setTelefonoPrincipal(rpm.getTelefonoPrincipal());
            ejOcupacionSolicitudes.setTelefonoSecundario(rpm.getTelefonoSecundario());
            ejOcupacionSolicitudes.setTipoTrabajo(rpm.getTipoTrabajo());
            ejOcupacionSolicitudes.setTipoOcupacion(rpm.getTipoOcupacion());

            ocupacionSolicitudesManager.guardarOcupacionSolicitudes(ejOcupacionSolicitudes);
        }
    }

    public void guardarReferencias(List<Referencias> referencias, Long idSolicitud, Long idUsuarioAlta) throws Exception {
        ReferenciasSolicitudes ejReferenciasSolicitudes;
        for (Referencias rpm : (referencias == null ? new ArrayList<Referencias>() : referencias)) {
            ejReferenciasSolicitudes = new ReferenciasSolicitudes();
            ejReferenciasSolicitudes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            ejReferenciasSolicitudes.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            ejReferenciasSolicitudes.setIdUsuarioModificacion(idUsuarioAlta);
            ejReferenciasSolicitudes.setIdUsuarioCreacion(idUsuarioAlta);
            ejReferenciasSolicitudes.setActivo("S");
            ejReferenciasSolicitudes.setPropuestaSolicitud(new PropuestaSolicitud(idSolicitud));
            ejReferenciasSolicitudes.setPersona(rpm.getPersona());
            ejReferenciasSolicitudes.setNombreContacto(rpm.getNombreContacto());
            ejReferenciasSolicitudes.setTelefono(rpm.getTelefono());
            ejReferenciasSolicitudes.setTelefonoCelular(rpm.getTelefonoCelular());
            ejReferenciasSolicitudes.setTipoReferencia(rpm.getTipoReferencia());

            referenciaSolicitudesManager.guardarReferenciaSolicitud(ejReferenciasSolicitudes);
        }
    }

    public void guardarBienes(List<Bienes> bienesInmuebles, Long idSolicitud, Long idUsuarioAlta) throws Exception {
        BienesSolicitudes ejBienesSolicitudes;

        for (Bienes rpm : (bienesInmuebles == null ? new ArrayList<Bienes>() : bienesInmuebles)) {

            ejBienesSolicitudes = new BienesSolicitudes();
            ejBienesSolicitudes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            ejBienesSolicitudes.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            ejBienesSolicitudes.setIdUsuarioModificacion(idUsuarioAlta);
            ejBienesSolicitudes.setIdUsuarioCreacion(idUsuarioAlta);
            ejBienesSolicitudes.setActivo("S");
            ejBienesSolicitudes.setBarrio(rpm.getBarrio());
            ejBienesSolicitudes.setCiudad(rpm.getCiudad());
            ejBienesSolicitudes.setCuentaCatastral(rpm.getCuentaCatastral());
            ejBienesSolicitudes.setDepartamento(rpm.getDepartamento());
            ejBienesSolicitudes.setDireccion(rpm.getDireccion());
            ejBienesSolicitudes.setDistrito(rpm.getDistrito());
            ejBienesSolicitudes.setEdificado(rpm.getEdificado());
            ejBienesSolicitudes.setEscriturado(rpm.getEscriturado());
            ejBienesSolicitudes.setFechaDeclaracion(rpm.getFechaDeclaracion());
            ejBienesSolicitudes.setFechaHipoteca(rpm.getFechaHipoteca());
            ejBienesSolicitudes.setFechaVenta(rpm.getFechaVenta());
            ejBienesSolicitudes.setHipotecado(rpm.getHipotecado());
            ejBienesSolicitudes.setLugarHipoteca(rpm.getLugarHipoteca());
            ejBienesSolicitudes.setNumeroFinca(rpm.getNumeroFinca());
            ejBienesSolicitudes.setPais(rpm.getPais());
            ejBienesSolicitudes.setSaldo(rpm.getSaldo());
            ejBienesSolicitudes.setTipoBien(rpm.getTipoBien());
            ejBienesSolicitudes.setValorActual(rpm.getValorActual());
            ejBienesSolicitudes.setPersona(rpm.getPersona());
            ejBienesSolicitudes.setVencimientoHipoteca(rpm.getVencimientoHipoteca());
            ejBienesSolicitudes.setPropuestaSolicitud(new PropuestaSolicitud(idSolicitud));
            ejBienesSolicitudes.setSaldo(rpm.getSaldo());
            ejBienesSolicitudes.setCuotaMensual(rpm.getCuotaMensual());
            ejBienesSolicitudes.setMarca(rpm.getMarca());
            ejBienesSolicitudes.setModeloAnio(rpm.getModeloAnio());

            bienesSolicitudesManager.guardarBienesSolicitud(ejBienesSolicitudes);
        }

    }

    @Override
    public Personas getPersonaSolicitud(Long idSolicitud, Long idPersona) throws Exception {
        String included = "inmuebles,vehiculos,referencias,ingresos,egresos,ocupaciones";

        Personas personaMap = personaManager.get(new Personas(idPersona));

        if (personaMap == null) {
            return null;
        }
        personaMap.setNombre(
                (personaMap.getPrimerNombre() == null ? "" : personaMap.getPrimerNombre())
                + " " + (personaMap.getSegundoNombre() == null ? "" : personaMap.getSegundoNombre())
                + " " + (personaMap.getPrimerApellido() == null ? "" : personaMap.getPrimerApellido())
                + " " + (personaMap.getSegundoApellido() == null ? "" : personaMap.getSegundoApellido())
        );

        Solicitantes ejSolicitantes = new Solicitantes();
        ejSolicitantes.setPropuestaSolicitud(new PropuestaSolicitud(idSolicitud));
        ejSolicitantes.setIdPersona(new Personas(idPersona));

        ejSolicitantes = solicitantesManager.get(ejSolicitantes);

        if (ejSolicitantes == null) {
            return null;
        }

        personaMap.setBarrio(ejSolicitantes.getBarrio());
        personaMap.setCiudad(ejSolicitantes.getCiudad());
        personaMap.setDepartamento(ejSolicitantes.getDepartamento());
        personaMap.setPais(ejSolicitantes.getPais());
        personaMap.setDireccionParticular(ejSolicitantes.getDireccionParticular());
        personaMap.setLatitud(ejSolicitantes.getLatitud());
        personaMap.setLongitud(ejSolicitantes.getLongitud());
        personaMap.setNumeroHijos(ejSolicitantes.getCantHijos());
        personaMap.setNumeroDependientes(ejSolicitantes.getCantPersonasACargo());
        personaMap.setEstadoCivil(ejSolicitantes.getEstadoCivil());
        personaMap.setFechaNacimiento(new Timestamp(ejSolicitantes.getFechaNacimiento().getTime()));
        personaMap.setProfesion(ejSolicitantes.getProfesiones());
        personaMap.setTelefonoParticular(ejSolicitantes.getTelefonoParticular());
        personaMap.setConyuge(ejSolicitantes.getIdPersonaConyuge());

        if (included.contains("inmuebles")) {
            BienesSolicitudes ejBienes = new BienesSolicitudes();
            ejBienes.setPersona(new Personas(personaMap.getId()));
            ejBienes.setActivo("S");
            ejBienes.setTipoBien("INMUEBLE");
            ejBienes.setPropuestaSolicitud(new PropuestaSolicitud(idSolicitud));

            List<BienesSolicitudes> listBienes = bienesSolicitudesManager.getListBienesSolicitud(ejBienes);
            List<Bienes> bienes = new ArrayList();
            Bienes bienPersona;
            for (BienesSolicitudes rpm : listBienes) {
                bienPersona = new Bienes();
                bienPersona.setActivo(rpm.getActivo());
                bienPersona.setBarrio(rpm.getBarrio());
                bienPersona.setCiudad(rpm.getCiudad());
                bienPersona.setCuentaCatastral(rpm.getCuentaCatastral());
                bienPersona.setDepartamento(rpm.getDepartamento());
                bienPersona.setDireccion(rpm.getDireccion());
                bienPersona.setDistrito(rpm.getDistrito());
                bienPersona.setEdificado(rpm.getEdificado());
                bienPersona.setEscriturado(rpm.getEscriturado());
                bienPersona.setFechaDeclaracion(rpm.getFechaDeclaracion());
                bienPersona.setFechaHipoteca(rpm.getFechaHipoteca());
                bienPersona.setFechaVenta(rpm.getFechaVenta());
                bienPersona.setHipotecado(rpm.getHipotecado());
                bienPersona.setLugarHipoteca(rpm.getLugarHipoteca());
                bienPersona.setNumeroFinca(rpm.getNumeroFinca());
                bienPersona.setPais(rpm.getPais());
                bienPersona.setSaldo(rpm.getSaldo());
                bienPersona.setTipoBien(rpm.getTipoBien());
                bienPersona.setValorActual(rpm.getValorActual());
                bienPersona.setVencimientoHipoteca(rpm.getVencimientoHipoteca());
                bienPersona.setSaldo(rpm.getSaldo());
                bienPersona.setCuotaMensual(rpm.getCuotaMensual());
                bienPersona.setMarca(rpm.getMarca());
                bienPersona.setModeloAnio(rpm.getModeloAnio());

                bienes.add(bienPersona);
            }
            personaMap.setBienesInmuebles(bienes);
        }

        if (included.contains("vehiculos")) {
            BienesSolicitudes ejBienes = new BienesSolicitudes();
            ejBienes.setPersona(new Personas(personaMap.getId()));
            ejBienes.setActivo("S");
            ejBienes.setTipoBien("VEHICULO");
            ejBienes.setPropuestaSolicitud(new PropuestaSolicitud(idSolicitud));

            List<BienesSolicitudes> listBienes = bienesSolicitudesManager.getListBienesSolicitud(ejBienes);
            List<Bienes> bienes = new ArrayList();
            Bienes bienPersona;
            for (BienesSolicitudes rpm : listBienes) {
                bienPersona = new Bienes();
                bienPersona.setActivo(rpm.getActivo());
                bienPersona.setBarrio(rpm.getBarrio());
                bienPersona.setCiudad(rpm.getCiudad());
                bienPersona.setCuentaCatastral(rpm.getCuentaCatastral());
                bienPersona.setDepartamento(rpm.getDepartamento());
                bienPersona.setDireccion(rpm.getDireccion());
                bienPersona.setDistrito(rpm.getDistrito());
                bienPersona.setEdificado(rpm.getEdificado());
                bienPersona.setEscriturado(rpm.getEscriturado());
                bienPersona.setFechaDeclaracion(rpm.getFechaDeclaracion());
                bienPersona.setFechaHipoteca(rpm.getFechaHipoteca());
                bienPersona.setFechaVenta(rpm.getFechaVenta());
                bienPersona.setHipotecado(rpm.getHipotecado());
                bienPersona.setLugarHipoteca(rpm.getLugarHipoteca());
                bienPersona.setNumeroFinca(rpm.getNumeroFinca());
                bienPersona.setPais(rpm.getPais());
                bienPersona.setSaldo(rpm.getSaldo());
                bienPersona.setTipoBien(rpm.getTipoBien());
                bienPersona.setValorActual(rpm.getValorActual());
                bienPersona.setVencimientoHipoteca(rpm.getVencimientoHipoteca());
                bienPersona.setSaldo(rpm.getSaldo());
                bienPersona.setCuotaMensual(rpm.getCuotaMensual());
                bienPersona.setMarca(rpm.getMarca());
                bienPersona.setModeloAnio(rpm.getModeloAnio());

                bienes.add(bienPersona);
            }

            personaMap.setBienesVehiculo(bienes);
        }

        if (included.contains("referencias")) {
            ReferenciasSolicitudes ejReferenciasSolicitudes = new ReferenciasSolicitudes();
            ejReferenciasSolicitudes.setPersona(new Personas(personaMap.getId()));
            ejReferenciasSolicitudes.setActivo("S");
            ejReferenciasSolicitudes.setPropuestaSolicitud(new PropuestaSolicitud(idSolicitud));

            List<ReferenciasSolicitudes> listReferencias = referenciaSolicitudesManager.getListReferenciaSolicitud(ejReferenciasSolicitudes);
            List<Referencias> referencias = new ArrayList();
            Referencias ejReferencia;
            for (ReferenciasSolicitudes rpm : listReferencias) {
                ejReferencia = new Referencias();
                ejReferencia.setActivo(rpm.getActivo());
                ejReferencia.setPersona(rpm.getPersona());
                ejReferencia.setNombreContacto(rpm.getNombreContacto());
                ejReferencia.setTelefono(rpm.getTelefono());
                ejReferencia.setTelefonoCelular(rpm.getTelefonoCelular());
                ejReferencia.setTipoReferencia(rpm.getTipoReferencia());

                referencias.add(ejReferencia);
            }

            personaMap.setReferencias(referencias);
        }

        if (included.contains("ingresos")) {
            TipoIngresosEgresos ejTipoIngresosEgresos = new TipoIngresosEgresos();
            ejTipoIngresosEgresos.setTipo("I");

            IngresosEgresosSolicitudes ejIngresosEgresosSolicitudes = new IngresosEgresosSolicitudes();
            ejIngresosEgresosSolicitudes.setPersona(new Personas(personaMap.getId()));
            ejIngresosEgresosSolicitudes.setActivo("S");
            ejIngresosEgresosSolicitudes.setPropuestaSolicitud(new PropuestaSolicitud(idSolicitud));
            ejIngresosEgresosSolicitudes.setTipoIngresosEgresos(ejTipoIngresosEgresos);

            List<IngresosEgresosSolicitudes> listIngresosEgresos = ingresosEgresosSolicitudesManager.getListIngresosEgresos(ejIngresosEgresosSolicitudes);
            List<IngresosEgresos> ingresosEgresos = new ArrayList();
            IngresosEgresos ejIngresosEgresos;
            for (IngresosEgresosSolicitudes rpm : listIngresosEgresos) {
                ejIngresosEgresos = new IngresosEgresos();
                ejIngresosEgresos.setActivo(rpm.getActivo());
                ejIngresosEgresos.setTipoIngresosEgresos(rpm.getTipoIngresosEgresos());
                ejIngresosEgresos.setMonto(rpm.getMonto());

                ingresosEgresos.add(ejIngresosEgresos);
            }

            personaMap.setIngresos(ingresosEgresos);
        }

        if (included.contains("egresos")) {
            TipoIngresosEgresos ejTipoIngresosEgresos = new TipoIngresosEgresos();
            ejTipoIngresosEgresos.setTipo("E");

            IngresosEgresosSolicitudes ejIngresosEgresosSolicitudes = new IngresosEgresosSolicitudes();
            ejIngresosEgresosSolicitudes.setPersona(new Personas(personaMap.getId()));
            ejIngresosEgresosSolicitudes.setActivo("S");
            ejIngresosEgresosSolicitudes.setPropuestaSolicitud(new PropuestaSolicitud(idSolicitud));
            ejIngresosEgresosSolicitudes.setTipoIngresosEgresos(ejTipoIngresosEgresos);

            List<IngresosEgresosSolicitudes> listIngresosEgresos = ingresosEgresosSolicitudesManager.getListIngresosEgresos(ejIngresosEgresosSolicitudes);
            List<IngresosEgresos> ingresosEgresos = new ArrayList();
            IngresosEgresos ejIngresosEgresos;
            for (IngresosEgresosSolicitudes rpm : listIngresosEgresos) {
                ejIngresosEgresos = new IngresosEgresos();
                ejIngresosEgresos.setActivo(rpm.getActivo());
                ejIngresosEgresos.setTipoIngresosEgresos(rpm.getTipoIngresosEgresos());
                ejIngresosEgresos.setMonto(rpm.getMonto());

                ingresosEgresos.add(ejIngresosEgresos);
            }

            personaMap.setEgresos(ingresosEgresos);
        }

        if (included.contains("ocupaciones")) {
            OcupacionSolicitudes ejOcupacionSolicitudes = new OcupacionSolicitudes();
            ejOcupacionSolicitudes.setPersona(new Personas(personaMap.getId()));
            ejOcupacionSolicitudes.setActivo("S");
            ejOcupacionSolicitudes.setPropuestaSolicitud(new PropuestaSolicitud(idSolicitud));

            List<OcupacionSolicitudes> listOcupacionSolicitudes = ocupacionSolicitudesManager.getListOcupacionSolicitudes(ejOcupacionSolicitudes);
            List<OcupacionPersona> ocupacionPersona = new ArrayList();
            OcupacionPersona ejOcupacionPersona;
            for (OcupacionSolicitudes rpm : listOcupacionSolicitudes) {
                ejOcupacionPersona = new OcupacionPersona();
                ejOcupacionPersona.setActivo("S");
                ejOcupacionPersona.setCargo(rpm.getCargo());
                ejOcupacionPersona.setDireccion(rpm.getDireccion());
                ejOcupacionPersona.setEmpresa(rpm.getEmpresa());
                ejOcupacionPersona.setFechaIngreso(rpm.getFechaIngreso());
                ejOcupacionPersona.setFechaSalida(rpm.getFechaSalida());
                ejOcupacionPersona.setIdUsuarioModificacion(rpm.getIdUsuarioModificacion());
                ejOcupacionPersona.setIngresosMensuales(rpm.getIngresosMensuales());
                ejOcupacionPersona.setInterno(rpm.getInterno());
                ejOcupacionPersona.setTelefonoPrincipal(rpm.getTelefonoPrincipal());
                ejOcupacionPersona.setTelefonoSecundario(rpm.getTelefonoSecundario());
                ejOcupacionPersona.setTipoTrabajo(rpm.getTipoTrabajo());
                ejOcupacionPersona.setTipoOcupacion(rpm.getTipoOcupacion());
                
                ocupacionPersona.add(ejOcupacionPersona);
            }

            personaMap.setOcupaciones(ocupacionPersona);
        }

        return personaMap;
    }

}
