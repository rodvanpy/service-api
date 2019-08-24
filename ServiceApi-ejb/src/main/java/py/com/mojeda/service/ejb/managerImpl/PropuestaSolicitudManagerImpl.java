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
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.entity.OcupacionSolicitudes;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.PropuestaSolicitud;
import py.com.mojeda.service.ejb.entity.Solicitantes;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.TipoCalculos;
import py.com.mojeda.service.ejb.entity.TipoDesembolsos;
import py.com.mojeda.service.ejb.entity.TipoDestinos;
import py.com.mojeda.service.ejb.entity.TipoGarantias;
import py.com.mojeda.service.ejb.entity.TipoPagos;
import py.com.mojeda.service.ejb.manager.BienesManager;
import py.com.mojeda.service.ejb.manager.BienesSolicitudesManager;
import py.com.mojeda.service.ejb.manager.ClientesManager;
import py.com.mojeda.service.ejb.manager.FuncionariosManager;
import py.com.mojeda.service.ejb.manager.GarantiasManager;
import py.com.mojeda.service.ejb.manager.IngresosEgresosManager;
import py.com.mojeda.service.ejb.manager.IngresosEgresosSolicitudesManager;
import py.com.mojeda.service.ejb.manager.ModalidadesManager;
import py.com.mojeda.service.ejb.manager.OcupacionPersonaManager;
import py.com.mojeda.service.ejb.manager.OcupacionSolicitudesManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.PropuestaSolicitudManager;
import py.com.mojeda.service.ejb.manager.SolicitantesManager;
import py.com.mojeda.service.ejb.manager.SucursalManager;
import py.com.mojeda.service.ejb.manager.TipoCalculosManager;
import py.com.mojeda.service.ejb.manager.TipoDesembolsosManager;
import py.com.mojeda.service.ejb.manager.TipoDestinosManager;
import py.com.mojeda.service.ejb.manager.TipoGarantiasManager;
import py.com.mojeda.service.ejb.manager.TipoPagosManager;
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

    @Override
    public PropuestaSolicitud guardar(PropuestaSolicitud propuestaSolicitud, Long idSucursal) throws Exception {

        if (idSucursal != null
                && propuestaSolicitud != null) {
            //Cargar la sucursal
            propuestaSolicitud.setSucursal(new Sucursales(idSucursal));

            //Crear Cliente
            if (propuestaSolicitud.getCliente().getId() == null) {
                Clientes cliente = clientesManager.guardar(propuestaSolicitud.getCliente());

                propuestaSolicitud.getCliente().setId(cliente.getId());
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
                montoEntregar = propuestaSolicitud.getMontoSolicitadoOriginal().longValue() + descuentos;
                propuestaSolicitud.setImporteEntregar(montoEntregar);
                if (new BigDecimal(montoEntregar) != propuestaSolicitud.getMontoSolicitado()) {
                    propuestaSolicitud.setMontoSolicitado(new BigDecimal(montoEntregar));
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

            propuestaSolicitud = this.getPropuestaSolicitud(propuestaSolicitud);

            if (propuestaSolicitud != null && propuestaSolicitud.getId() != null) {
                //Cargar Solicitantes de la propuesta
                //GUARDAR SOLICITUD/GARANTIA DEUDOR
                Solicitantes ejSolicitantes = new Solicitantes();
                ejSolicitantes.setBarrio(propuestaSolicitud.getCliente().getPersona().getBarrio());
                ejSolicitantes.setCiudad(propuestaSolicitud.getCliente().getPersona().getCiudad());
                ejSolicitantes.setDepartamento(propuestaSolicitud.getCliente().getPersona().getDepartamento());
                ejSolicitantes.setPais(propuestaSolicitud.getCliente().getPersona().getPais());
                ejSolicitantes.setDireccionParticular(propuestaSolicitud.getCliente().getPersona().getDireccionParticular());
                ejSolicitantes.setFechaDireccion(new Date(System.currentTimeMillis()));
                ejSolicitantes.setLatitud(propuestaSolicitud.getCliente().getPersona().getLatitud());
                ejSolicitantes.setLongitud(propuestaSolicitud.getCliente().getPersona().getLongitud());
                ejSolicitantes.setCantHijos(propuestaSolicitud.getCliente().getPersona().getNumeroHijos());
                ejSolicitantes.setCantPersonasACargo(propuestaSolicitud.getCliente().getPersona().getNumeroDependientes());
                ejSolicitantes.setEstadoCivil(propuestaSolicitud.getCliente().getPersona().getEstadoCivil());
                ejSolicitantes.setFechaNacimiento(propuestaSolicitud.getCliente().getPersona().getFechaNacimiento());
                ejSolicitantes.setIdPersona(new Personas(propuestaSolicitud.getCliente().getPersona().getId()));
                ejSolicitantes.setProfesiones(propuestaSolicitud.getCliente().getPersona().getProfesion());
                ejSolicitantes.setPropuestaSolicitud(new PropuestaSolicitud(propuestaSolicitud.getId()));
                ejSolicitantes.setTelefonoParticular(propuestaSolicitud.getCliente().getPersona().getTelefonoParticular() + " / " + propuestaSolicitud.getCliente().getPersona().getTelefonoSecundario());
                ejSolicitantes.setTipoGarantias(propuestaSolicitud.getTipoGarantia());
                ejSolicitantes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejSolicitantes.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                ejSolicitantes.setTipoRelacion("DEUDOR");

                solicitantesManager.save(ejSolicitantes);

                Garantias ejGarantias = new Garantias();
                ejGarantias.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                ejGarantias.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejGarantias.setFechaEstado(new Timestamp(System.currentTimeMillis()));
                ejGarantias.setPersona(new Personas(propuestaSolicitud.getCliente().getPersona().getId()));
                ejGarantias.setPropuestaSolicitud(new PropuestaSolicitud(propuestaSolicitud.getId()));
                ejGarantias.setRiesgoAsumido(propuestaSolicitud.getMontoSolicitado());
                ejGarantias.setTipoGarantias(propuestaSolicitud.getTipoGarantia());
                ejGarantias.setTipoRelacion("DEUDOR");

                garantiasManager.save(ejGarantias);

                //GUARDAR BIENES
                Bienes ejBienes = new Bienes();
                ejBienes.setActivo("S");
                ejBienes.setPersona(new Personas(propuestaSolicitud.getCliente().getPersona().getId()));
                //ejBienes.setTipoBien("INMUEBLE");
                this.guardarBienes(bienesManager.list(ejBienes), propuestaSolicitud.getId(), propuestaSolicitud.getFuncionario().getId());

                //GUARDAR INGRESOS/EGRESOS
                IngresosEgresos ejIngresosEgresos = new IngresosEgresos();
                ejIngresosEgresos.setActivo("S");
                ejIngresosEgresos.setPersona(new Personas(propuestaSolicitud.getCliente().getPersona().getId()));
                this.guardarIngresosEgresosSolicitud(ingresosEgresosManager.list(ejIngresosEgresos), propuestaSolicitud.getId(), propuestaSolicitud.getFuncionario().getId());

                //GUARDAR OCUPACIONES
                OcupacionPersona ejOcupacionPersona = new OcupacionPersona();
                ejOcupacionPersona.setPersona(new Personas(propuestaSolicitud.getCliente().getPersona().getId()));
                ejOcupacionPersona.setActivo("S");
                this.guardarOcupaciones(ocupacionPersonaManager.list(ejOcupacionPersona), propuestaSolicitud.getId(), propuestaSolicitud.getFuncionario().getId());

                if (propuestaSolicitud.getCodeudor() != null
                        && propuestaSolicitud.getCodeudor().getId() != null
                        && propuestaSolicitud.getTipoGarantia().getCodigo().equals("TG-2")) {
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
                    ejSolicitantes.setTelefonoParticular(propuestaSolicitud.getCodeudor().getTelefonoParticular() + " / " + propuestaSolicitud.getCodeudor().getTelefonoSecundario());
                    ejSolicitantes.setTipoGarantias(propuestaSolicitud.getTipoGarantia());
                    ejSolicitantes.setTipoRelacion("CODEUDOR");
                    ejSolicitantes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejSolicitantes.setFechaCreacion(new Timestamp(System.currentTimeMillis()));

                    solicitantesManager.save(ejSolicitantes);

                    ejGarantias = new Garantias();
                    ejGarantias.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    ejGarantias.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
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

        propuestaSolicitud = this.get(propuestaSolicitud);

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

            ocupacionSolicitudesManager.guardarOcupacionSolicitudes(ejOcupacionSolicitudes);
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

}
