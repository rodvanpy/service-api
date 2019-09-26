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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Barrios;
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.entity.Ciudades;
import py.com.mojeda.service.ejb.entity.DepartamentosPais;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Estudios;
import py.com.mojeda.service.ejb.entity.IngresosEgresos;
import py.com.mojeda.service.ejb.entity.Nacionalidades;
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.entity.Paises;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Profesiones;
import py.com.mojeda.service.ejb.entity.Referencias;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.TipoIngresosEgresos;
import py.com.mojeda.service.ejb.entity.Vinculos;
import py.com.mojeda.service.ejb.manager.BarriosManager;
import py.com.mojeda.service.ejb.manager.BienesManager;
import py.com.mojeda.service.ejb.manager.CiudadesManager;
import py.com.mojeda.service.ejb.manager.DepartamentosPaisManager;
import py.com.mojeda.service.ejb.manager.EmpresaManager;
import py.com.mojeda.service.ejb.manager.EstudiosManager;
import py.com.mojeda.service.ejb.manager.IngresosEgresosManager;
import py.com.mojeda.service.ejb.manager.NacionalidadesManager;
import py.com.mojeda.service.ejb.manager.OcupacionPersonaManager;
import py.com.mojeda.service.ejb.manager.PaisesManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.ProfesionesManager;
import py.com.mojeda.service.ejb.manager.ReferenciaManager;
import py.com.mojeda.service.ejb.manager.SucursalManager;
import py.com.mojeda.service.ejb.manager.VinculoManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;
import py.com.mojeda.service.ejb.utils.Base64Bytes;
import static py.com.mojeda.service.ejb.utils.Constants.CONTENT_PATH;

/**
 *
 * @author Miguel
 */
@Stateless
public class PersonaManagerImpl extends GenericDaoImpl<Personas, Long>
        implements PersonaManager {

    @Override
    protected Class<Personas> getEntityBeanType() {
        return Personas.class;
    }

    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();
    protected static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @EJB(mappedName = "java:app/ServiceApi-ejb/SucursalManagerImpl")
    private SucursalManager sucursalManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/EmpresaManagerImpl")
    private EmpresaManager empresaManager;

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

    @EJB(mappedName = "java:app/ServiceApi-ejb/ReferenciaManagerImpl")
    private ReferenciaManager referenciaManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/BienesManagerImpl")
    private BienesManager bienesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/OcupacionPersonaManagerImpl")
    private OcupacionPersonaManager ocupacionPersonaManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/IngresosEgresosManagerImpl")
    private IngresosEgresosManager ingresosEgresosManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/EstudiosManagerImpl")
    private EstudiosManager estudiosManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/VinculoManagerImpl")
    private VinculoManager vinculoManager;

    @Override
    public Personas guardar(Personas persona) throws Exception {
        Personas ejPersona = new Personas();
        ejPersona.setDocumento(persona.getDocumento());
        ejPersona.setEmpresa(persona.getEmpresa());

        ejPersona = this.get(ejPersona);

        if (ejPersona != null) {

            ejPersona.setPrimerNombre(persona.getPrimerNombre() == null ? null : persona.getPrimerNombre().toUpperCase());
            ejPersona.setSegundoNombre(persona.getSegundoNombre() == null ? null : persona.getSegundoNombre().toUpperCase());
            ejPersona.setPrimerApellido(persona.getPrimerApellido() == null ? null : persona.getPrimerApellido().toUpperCase());
            ejPersona.setSegundoApellido(persona.getSegundoApellido() == null ? null : persona.getSegundoApellido().toUpperCase());
            ejPersona.setEmail(persona.getEmail());
            ejPersona.setSexo(persona.getSexo());
            ejPersona.setEstadoCivil(persona.getEstadoCivil());
            ejPersona.setNumeroHijos(persona.getNumeroHijos());
            ejPersona.setNumeroDependientes(persona.getNumeroDependientes());
            ejPersona.setSeparacionBienes(persona.getSeparacionBienes());
            ejPersona.setTelefonoParticular(persona.getTelefonoParticular());
            ejPersona.setTelefonoSecundario(persona.getTelefonoSecundario());
            ejPersona.setTipoPersona(persona.getTipoPersona());
            ejPersona.setDireccionParticular(persona.getDireccionParticular());
            ejPersona.setFechaNacimiento(persona.getFechaNacimiento());
            ejPersona.setDireccionDetallada(persona.getDireccionDetallada());
            ejPersona.setObservacion(persona.getObservacion());
            ejPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            ejPersona.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
            ejPersona.setNacionalidad(new Nacionalidades(persona.getNacionalidad().getId()));
            ejPersona.setPais(new Paises(persona.getPais().getId()));
            ejPersona.setDepartamento(new DepartamentosPais(persona.getDepartamento().getId()));
            ejPersona.setCiudad(new Ciudades(persona.getCiudad().getId()));
            ejPersona.setBarrio((persona.getBarrio() != null && persona.getBarrio().getId() != null) ? new Barrios(persona.getBarrio().getId()) : null);
            ejPersona.setProfesion((persona.getProfesion() != null && persona.getProfesion().getId() != null) ? new Profesiones(persona.getProfesion().getId()) : null);

        } else {
            persona.setPrimerNombre(persona.getPrimerNombre() == null ? null : persona.getPrimerNombre().toUpperCase());
            persona.setSegundoNombre(persona.getSegundoNombre() == null ? null : persona.getSegundoNombre().toUpperCase());
            persona.setPrimerApellido(persona.getPrimerApellido() == null ? null : persona.getPrimerApellido().toUpperCase());
            persona.setSegundoApellido(persona.getSegundoApellido() == null ? null : persona.getSegundoApellido().toUpperCase());
            persona.setActivo("S");
            persona.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            persona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

            this.save(persona);

            ejPersona = new Personas();
            ejPersona.setDocumento(persona.getDocumento());

            ejPersona = this.get(ejPersona);
        }

        if (persona.getConyuge() != null) {
            Personas ejConyuge = new Personas();
            ejConyuge.setDocumento(persona.getConyuge().getDocumento());

            Map<String, Object> personaMap = this.getAtributos(ejConyuge, "id".split(","));

            if (personaMap != null) {

                Vinculos ejVinculo = new Vinculos();
                ejVinculo.setActivo("S");
                ejVinculo.setPersona(new Personas(persona.getId()));
                ejVinculo.setTipoVinculo("CONYUGE");

                ejVinculo = vinculoManager.get(ejVinculo);

                if (ejVinculo == null) {
                    ejVinculo = new Vinculos();
                    ejVinculo.setActivo("S");
                    ejVinculo.setPersona(new Personas(persona.getId()));
                    ejVinculo.setTipoVinculo("CONYUGE");
                    ejVinculo.setPersonaVinculo(new Personas(Long.parseLong(personaMap.get("id").toString())));
                    ejVinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejVinculo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    ejVinculo.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                    ejVinculo.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());

                    vinculoManager.save(ejVinculo);
                }

            }
        }

        for (Vinculos rpm : (persona.getVinculos() == null ? new ArrayList<Vinculos>() : persona.getVinculos())) {
            if (rpm.getId() == null) {
                rpm.setActivo("S");
                rpm.getPersonaVinculo().setEmpresa(ejPersona.getEmpresa());
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                rpm.setPersona(new Personas(ejPersona.getId()));

                Map<String, Object> responseMaps = vinculoManager.guardar(rpm);
            } else {
                rpm.setActivo("S");
                rpm.getPersonaVinculo().setEmpresa(ejPersona.getEmpresa());
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                rpm.setPersona(new Personas(ejPersona.getId()));

                Map<String, Object> responseMaps = vinculoManager.editar(rpm);
            }
        }

        for (Bienes rpm : (persona.getBienesInmuebles() == null ? new ArrayList<Bienes>() : persona.getBienesInmuebles())) {
            if (rpm.getId() == null) {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                rpm.setPersona(new Personas(ejPersona.getId()));

                rpm = bienesManager.guardarBienes(rpm);
            } else {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());

                rpm = bienesManager.editarBienes(rpm);
            }
        }

        for (Bienes rpm : (persona.getBienesVehiculo() == null ? new ArrayList<Bienes>() : persona.getBienesVehiculo())) {
            if (rpm.getId() == null) {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                rpm.setPersona(new Personas(ejPersona.getId()));

                rpm = bienesManager.guardarBienes(rpm);
            } else {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());

                rpm = bienesManager.editarBienes(rpm);
            }
        }

        for (IngresosEgresos rpm : (persona.getEgresos() == null ? new ArrayList<IngresosEgresos>() : persona.getEgresos())) {
            if (rpm.getId() == null) {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                rpm.setPersona(new Personas(ejPersona.getId()));

                rpm = ingresosEgresosManager.guardarIngresosEgresos(rpm);
            } else {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());

                rpm = ingresosEgresosManager.editarIngresosEgresos(rpm);
            }
        }

        for (IngresosEgresos rpm : (persona.getIngresos() == null ? new ArrayList<IngresosEgresos>() : persona.getIngresos())) {
            if (rpm.getId() == null) {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                rpm.setPersona(new Personas(ejPersona.getId()));

                rpm = ingresosEgresosManager.guardarIngresosEgresos(rpm);
            } else {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());

                rpm = ingresosEgresosManager.editarIngresosEgresos(rpm);
            }
        }

        for (OcupacionPersona rpm : (persona.getOcupaciones() == null ? new ArrayList<OcupacionPersona>() : persona.getOcupaciones())) {
            if (rpm.getId() == null) {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                rpm.setPersona(new Personas(ejPersona.getId()));

                rpm = ocupacionPersonaManager.guardarOcupacion(rpm);
            } else {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());

                rpm = ocupacionPersonaManager.editarOcupacion(rpm);
            }
        }

        for (Referencias rpm : (persona.getReferencias() == null ? new ArrayList<Referencias>() : persona.getReferencias())) {
            if (rpm.getId() == null) {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                rpm.setPersona(new Personas(ejPersona.getId()));

                rpm = referenciaManager.guardarReferencia(rpm);
            } else {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());

                rpm = referenciaManager.editarReferencia(rpm);
            }
        }

        for (Estudios rpm : (persona.getEstudios() == null ? new ArrayList<Estudios>() : persona.getEstudios())) {

            if (rpm.getId() == null) {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                rpm.setPersona(new Personas(ejPersona.getId()));

                estudiosManager.save(rpm);
            } else {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                rpm.setPersona(new Personas(ejPersona.getId()));

                estudiosManager.update(rpm);
            }
        }

        if (persona.getAvatar() != null
                && persona.getAvatar().getValue() != null) {

            Files.createDirectories(Paths.get(CONTENT_PATH + ejPersona.getClassName() + "/" + ejPersona.getId()));
            String path = ejPersona.getClassName() + "/" + ejPersona.getId() + "/" + persona.getAvatar().getFilename();
            FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
            fos.write(Base64Bytes.decode(persona.getAvatar().getValue()));
            fos.close();

            ejPersona.setImagePath(CONTENT_PATH + path);
        }

        this.update(ejPersona);

        return this.getPersona(new Personas(ejPersona.getId()), null);
    }

    @Override
    public Personas editar(Personas persona) throws Exception {
        Personas ejPersona = new Personas();
        try {
            if (persona.getDocumento() != null
                    && persona.getDocumento().compareToIgnoreCase("") != 0) {
                ejPersona.setDocumento(persona.getDocumento());
            }

            if (persona.getId() != null) {
                ejPersona.setId(persona.getId());
            }

            ejPersona.setEmpresa(persona.getEmpresa());

            ejPersona = this.get(ejPersona);

            if (ejPersona != null) {

                ejPersona.setPrimerNombre(persona.getPrimerNombre() == null ? null : persona.getPrimerNombre().toUpperCase());
                ejPersona.setSegundoNombre(persona.getSegundoNombre() == null ? null : persona.getSegundoNombre().toUpperCase());
                ejPersona.setPrimerApellido(persona.getPrimerApellido() == null ? null : persona.getPrimerApellido().toUpperCase());
                ejPersona.setSegundoApellido(persona.getSegundoApellido() == null ? null : persona.getSegundoApellido().toUpperCase());
                ejPersona.setEmail(persona.getEmail());
                ejPersona.setSexo(persona.getSexo());
                ejPersona.setEstadoCivil(persona.getEstadoCivil());
                ejPersona.setNumeroHijos(persona.getNumeroHijos());
                ejPersona.setNumeroDependientes(persona.getNumeroDependientes());
                ejPersona.setSeparacionBienes(persona.getSeparacionBienes());
                ejPersona.setTelefonoParticular(persona.getTelefonoParticular());
                ejPersona.setTelefonoSecundario(persona.getTelefonoSecundario());
                //ejPersona.setTipoPersona(persona.getTipoPersona());
                ejPersona.setDireccionParticular(persona.getDireccionParticular());
                ejPersona.setFechaNacimiento(persona.getFechaNacimiento());
                ejPersona.setDireccionDetallada(persona.getDireccionDetallada());
                ejPersona.setObservacion(persona.getObservacion());
                ejPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejPersona.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                ejPersona.setNacionalidad(new Nacionalidades(persona.getNacionalidad().getId()));
                ejPersona.setPais(new Paises(persona.getPais().getId()));
                ejPersona.setDepartamento(new DepartamentosPais(persona.getDepartamento().getId()));
                ejPersona.setCiudad(new Ciudades(persona.getCiudad().getId()));
                ejPersona.setBarrio((persona.getBarrio() != null && persona.getBarrio().getId() != null) ? new Barrios(persona.getBarrio().getId()) : null);
                ejPersona.setProfesion((persona.getProfesion() != null && persona.getProfesion().getId() != null) ? new Profesiones(persona.getProfesion().getId()) : null);

                this.update(ejPersona);

            } else {
                persona.setPrimerNombre(persona.getPrimerNombre() == null ? null : persona.getPrimerNombre().toUpperCase());
                persona.setSegundoNombre(persona.getSegundoNombre() == null ? null : persona.getSegundoNombre().toUpperCase());
                persona.setPrimerApellido(persona.getPrimerApellido() == null ? null : persona.getPrimerApellido().toUpperCase());
                persona.setSegundoApellido(persona.getSegundoApellido() == null ? null : persona.getSegundoApellido().toUpperCase());
                persona.setActivo("S");
                persona.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                persona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

                this.save(persona);

                ejPersona = new Personas();
                ejPersona.setDocumento(persona.getDocumento());
                ejPersona.setEmpresa(persona.getEmpresa());

                ejPersona = this.get(ejPersona);
            }

            //Guardar Conyuge
            if (persona.getConyuge() != null) {
                Personas ejConyuge = new Personas();
                ejConyuge.setDocumento(persona.getConyuge().getDocumento());

                Map<String, Object> personaMap = this.getAtributos(ejConyuge, "id".split(","));

                if (personaMap != null) {

                    Vinculos ejVinculo = new Vinculos();
                    ejVinculo.setActivo("S");
                    ejVinculo.setPersona(new Personas(persona.getId()));
                    ejVinculo.setTipoVinculo("CONYUGE");

                    ejVinculo = vinculoManager.get(ejVinculo);
                    if (ejVinculo == null) {
                        ejVinculo = new Vinculos();
                        ejVinculo.setActivo("S");
                        ejVinculo.setPersona(new Personas(persona.getId()));
                        ejVinculo.setTipoVinculo("CONYUGE");
                        ejVinculo.setPersonaVinculo(new Personas(Long.parseLong(personaMap.get("id").toString())));
                        ejVinculo.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                        ejVinculo.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                        ejVinculo.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());

                        vinculoManager.save(ejVinculo);
                    }

                }
            }

            for (Vinculos rpm : (persona.getVinculos() == null ? new ArrayList<Vinculos>() : persona.getVinculos())) {
                if (rpm.getId() == null) {
                    rpm.setActivo("S");
                    rpm.getPersonaVinculo().setEmpresa(ejPersona.getEmpresa());
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    Map<String, Object> responseMaps = vinculoManager.guardar(rpm);
                } else {
                    rpm.setActivo("S");
                    rpm.getPersonaVinculo().setEmpresa(ejPersona.getEmpresa());
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    Map<String, Object> responseMaps = vinculoManager.editar(rpm);
                }
            }

            for (Bienes rpm : (persona.getBienesInmuebles() == null ? new ArrayList<Bienes>() : persona.getBienesInmuebles())) {
                if (rpm.getId() == null) {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    rpm = bienesManager.guardarBienes(rpm);
                } else {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());

                    rpm = bienesManager.editarBienes(rpm);
                }
            }

            for (Bienes rpm : (persona.getBienesVehiculo() == null ? new ArrayList<Bienes>() : persona.getBienesVehiculo())) {
                if (rpm.getId() == null) {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    rpm = bienesManager.guardarBienes(rpm);
                } else {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());

                    rpm = bienesManager.editarBienes(rpm);
                }
            }

            for (IngresosEgresos rpm : (persona.getEgresos() == null ? new ArrayList<IngresosEgresos>() : persona.getEgresos())) {
                if (rpm.getId() == null) {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    rpm = ingresosEgresosManager.guardarIngresosEgresos(rpm);
                } else {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());

                    rpm = ingresosEgresosManager.editarIngresosEgresos(rpm);
                }
            }

            for (IngresosEgresos rpm : (persona.getIngresos() == null ? new ArrayList<IngresosEgresos>() : persona.getIngresos())) {
                if (rpm.getId() == null) {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    rpm = ingresosEgresosManager.guardarIngresosEgresos(rpm);
                } else {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());

                    rpm = ingresosEgresosManager.editarIngresosEgresos(rpm);
                }
            }

            for (OcupacionPersona rpm : (persona.getOcupaciones() == null ? new ArrayList<OcupacionPersona>() : persona.getOcupaciones())) {
                if (rpm.getId() == null) {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    rpm = ocupacionPersonaManager.guardarOcupacion(rpm);
                } else {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());

                    rpm = ocupacionPersonaManager.editarOcupacion(rpm);
                }
            }

            for (Referencias rpm : (persona.getReferencias() == null ? new ArrayList<Referencias>() : persona.getReferencias())) {
                if (rpm.getId() == null) {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    rpm = referenciaManager.guardarReferencia(rpm);
                } else {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());

                    rpm = referenciaManager.editarReferencia(rpm);
                }
            }

            for (Estudios rpm : (persona.getEstudios() == null ? new ArrayList<Estudios>() : persona.getEstudios())) {

                if (rpm.getId() == null) {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                    rpm.setIdUsuarioCreacion(persona.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    estudiosManager.save(rpm);
                } else {
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(persona.getIdUsuarioModificacion());
                    rpm.setPersona(new Personas(ejPersona.getId()));

                    estudiosManager.update(rpm);
                }
            }

            if (persona.getAvatar() != null
                    && persona.getAvatar().getValue() != null) {

                Files.createDirectories(Paths.get(CONTENT_PATH + ejPersona.getClassName() + "/" + ejPersona.getId()));
                String path = ejPersona.getClassName() + "/" + ejPersona.getId() + "/" + persona.getAvatar().getFilename();
                FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
                fos.write(Base64Bytes.decode(persona.getAvatar().getValue()));
                fos.close();

                ejPersona.setImagePath(CONTENT_PATH + path);
            }

            this.update(ejPersona);

        } catch (Exception e) {
            logger.error("ERROR", e);
        } finally {
            try {
                this.finalize();
            } catch (Throwable ex) {
                logger.error("ERROR", ex);
            }
        }
        return ejPersona;
    }

    @Override
    public Personas getPersona(Personas personas) throws Exception {
        Personas model = null;
        String atributos = "id,primerNombre,segundoNombre,primerApellido,segundoApellido,documento,ruc,"
                + "fechaNacimiento,tipoPersona,sexo,numeroHijos,numeroDependientes,estadoCivil,separacionBienes,"
                + "email,telefonoParticular,telefonoSecundario,direccionParticular,direccionDetallada,activo,"
                + "observacion,latitud,longitud,nacionalidad.id,pais.id,departamento.id,ciudad.id,barrio.id,profesion.id,imagePath";

        Map<String, Object> persona = this.getAtributos(personas, atributos.split(","));
        if (persona != null) {
            model = new Personas();
            model.setActivo(persona.get("activo") == null ? "" : persona.get("activo").toString());
            model.setDireccionDetallada(persona.get("direccionDetallada") == null ? "" : persona.get("direccionDetallada").toString());
            model.setDireccionParticular(persona.get("direccionParticular") == null ? "" : persona.get("direccionParticular").toString());
            model.setDocumento(persona.get("documento") == null ? "" : persona.get("documento").toString());
            model.setEmail(persona.get("email") == null ? "" : persona.get("email").toString());
            model.setEstadoCivil(persona.get("estadoCivil") == null ? "" : persona.get("estadoCivil").toString());
            model.setFechaNacimiento(persona.get("fechaNacimiento") == null ? null : new Timestamp(dateFormat.parse(persona.get("fechaNacimiento").toString()).getTime()));
            model.setId(persona.get("id") == null ? null : Long.parseLong(persona.get("id").toString()));
            model.setImagePath(persona.get("imagePath") == null ? null : persona.get("imagePath").toString());
            model.setLatitud(persona.get("latitud") == null ? null : Double.parseDouble(persona.get("latitud").toString()));
            model.setLongitud(persona.get("longitud") == null ? null : Double.parseDouble(persona.get("longitud").toString()));
            model.setPrimerApellido(persona.get("primerApellido") == null ? "" : persona.get("primerApellido").toString());
            model.setPrimerNombre(persona.get("primerNombre") == null ? "" : persona.get("primerNombre").toString());
            model.setSegundoApellido(persona.get("segundoApellido") == null ? "" : persona.get("segundoApellido").toString());
            model.setSegundoNombre(persona.get("segundoNombre") == null ? "" : persona.get("segundoNombre").toString());
            model.setNombre(
                    (model.getPrimerNombre() == null ? "" : model.getPrimerNombre())
                    + " " + (model.getSegundoNombre() == null ? "" : model.getSegundoNombre())
                    + " " + (model.getPrimerApellido() == null ? "" : model.getPrimerApellido())
                    + " " + (model.getSegundoApellido() == null ? "" : model.getSegundoApellido())
            );
            model.setNumeroDependientes(persona.get("numeroDependientes") == null ? null : Integer.parseInt(persona.get("numeroDependientes").toString()));
            model.setNumeroHijos(persona.get("numeroHijos") == null ? null : Integer.parseInt(persona.get("numeroHijos").toString()));
            model.setObservacion(persona.get("observacion") == null ? "" : persona.get("observacion").toString());
            model.setRuc(persona.get("ruc") == null ? "" : persona.get("ruc").toString());
            model.setSeparacionBienes(persona.get("separacionBienes") == null ? null : Boolean.parseBoolean(persona.get("separacionBienes").toString()));
            model.setSexo(persona.get("sexo") == null ? "" : persona.get("sexo").toString());
            model.setTelefonoParticular(persona.get("telefonoParticular") == null ? "" : persona.get("telefonoParticular").toString());
            model.setTelefonoSecundario(persona.get("telefonoSecundario") == null ? "" : persona.get("telefonoSecundario").toString());
            model.setTipoPersona(persona.get("tipoPersona") == null ? "" : persona.get("tipoPersona").toString());
            model.setProfesion(profesionesManager.get(new Profesiones(Long.parseLong(persona.get("profesion.id") == null ? "0" : persona.get("profesion.id").toString()))));
            model.setNacionalidad(nacionalidadesManager.get(new Nacionalidades(Long.parseLong(persona.get("nacionalidad.id") == null ? "0" : persona.get("nacionalidad.id").toString()))));
            model.setPais(paisesManager.get(new Paises(Long.parseLong(persona.get("pais.id") == null ? "0" : persona.get("pais.id").toString()))));
            model.setDepartamento(departamentosPaisManager.get(new DepartamentosPais(Long.parseLong(persona.get("departamento.id") == null ? "0" : persona.get("departamento.id").toString()))));
            model.setBarrio(barriosManager.get(new Barrios(Long.parseLong(persona.get("barrio.id") == null ? "0" : persona.get("barrio.id").toString()))));
            model.setCiudad(ciudadesManager.get(new Ciudades(Long.parseLong(persona.get("ciudad.id") == null ? "0" : persona.get("ciudad.id").toString()))));

            if (model.getEstadoCivil().compareToIgnoreCase("CASADO/A") == 0) {
                Vinculos ejVinculo = new Vinculos();
                ejVinculo.setActivo("S");
                ejVinculo.setPersona(new Personas(model.getId()));
                ejVinculo.setTipoVinculo("CONYUGE");

                Map<String, Object> conyugeMap = vinculoManager.getAtributos(ejVinculo, "personaVinculo.id".split(","));
                if (conyugeMap != null) {
                    Personas conyuge = this.get(new Personas(Long.parseLong(conyugeMap.get("personaVinculo.id").toString())));
                    if (conyuge != null) {
                        conyuge.setNombre(
                                (conyuge.getPrimerNombre() == null ? "" : conyuge.getPrimerNombre())
                                + " " + (conyuge.getSegundoNombre() == null ? "" : conyuge.getSegundoNombre())
                                + " " + (conyuge.getPrimerApellido() == null ? "" : conyuge.getPrimerApellido())
                                + " " + (conyuge.getSegundoNombre() == null ? "" : conyuge.getSegundoNombre())
                        );
                        model.setConyuge(conyuge);
                    }
                } else {
                    model.setConyuge(new Personas());
                }
            }else{
                model.setConyuge(new Personas());
            }

        }
        return model;
    }

    @Override
    public Personas getPersona(Personas persona, String included) throws Exception {
        included = included == null ? "" : included;

        Personas personaMap = this.get(persona);
        if (personaMap == null) {
            return null;
        }
        personaMap.setNombre(
                (personaMap.getPrimerNombre() == null ? "" : personaMap.getPrimerNombre())
                + " " + (personaMap.getSegundoNombre() == null ? "" : personaMap.getSegundoNombre())
                + " " + (personaMap.getPrimerApellido() == null ? "" : personaMap.getPrimerApellido())
                + " " + (personaMap.getSegundoApellido() == null ? "" : personaMap.getSegundoApellido())
        );
        if (personaMap != null) {
            if (included.contains("inmuebles")) {
                Bienes ejBienes = new Bienes();
                ejBienes.setPersona(new Personas(personaMap.getId()));
                ejBienes.setActivo("S");
                ejBienes.setTipoBien("INMUEBLE");

                personaMap.setBienesInmuebles(bienesManager.getListBienes(ejBienes));
            }

            if (included.contains("vehiculos")) {
                Bienes ejBienes = new Bienes();
                ejBienes.setPersona(new Personas(personaMap.getId()));
                ejBienes.setActivo("S");
                ejBienes.setTipoBien("VEHICULO");

                personaMap.setBienesVehiculo(bienesManager.getListBienes(ejBienes));
            }

            if (included.contains("referencias")) {
                Referencias ejReferencia = new Referencias();
                ejReferencia.setPersona(new Personas(personaMap.getId()));
                ejReferencia.setActivo("S");

                personaMap.setReferencias(referenciaManager.getListReferencias(ejReferencia));
            }

            if (included.contains("ingresos")) {
                TipoIngresosEgresos ejTipoIngresosEgresos = new TipoIngresosEgresos();
                ejTipoIngresosEgresos.setTipo("I");

                IngresosEgresos ejIngresosEgresos = new IngresosEgresos();
                ejIngresosEgresos.setPersona(new Personas(personaMap.getId()));
                ejIngresosEgresos.setActivo("S");
                ejIngresosEgresos.setTipoIngresosEgresos(ejTipoIngresosEgresos);

                personaMap.setIngresos(ingresosEgresosManager.getListIngresosEgresos(ejIngresosEgresos));
            }

            if (included.contains("egresos")) {
                TipoIngresosEgresos ejTipoIngresosEgresos = new TipoIngresosEgresos();
                ejTipoIngresosEgresos.setTipo("E");

                IngresosEgresos ejIngresosEgresos = new IngresosEgresos();
                ejIngresosEgresos.setPersona(new Personas(personaMap.getId()));
                ejIngresosEgresos.setActivo("S");
                ejIngresosEgresos.setTipoIngresosEgresos(ejTipoIngresosEgresos);

                personaMap.setEgresos(ingresosEgresosManager.getListIngresosEgresos(ejIngresosEgresos));
            }

            if (included.contains("ocupaciones")) {
                OcupacionPersona ejOcupaciones = new OcupacionPersona();
                ejOcupaciones.setPersona(new Personas(personaMap.getId()));
                ejOcupaciones.setActivo("S");

                personaMap.setOcupaciones(ocupacionPersonaManager.getListOcupaciones(ejOcupaciones));
            }

            if (included.contains("estudios")) {
                Estudios ejEstudios = new Estudios();
                ejEstudios.setPersona(new Personas(personaMap.getId()));
                ejEstudios.setActivo("S");

                personaMap.setEstudios(estudiosManager.list(ejEstudios));
            }

            if (included.contains("vinculos")) {
                Vinculos ejVinculos = new Vinculos();
                ejVinculos.setPersona(new Personas(personaMap.getId()));
                ejVinculos.setActivo("S");

                List<Object> notIn = new ArrayList<>();
                notIn.add("CONYUGE");

                personaMap.setVinculos(vinculoManager.list(ejVinculos, true, "tipoVinculo", notIn, "NOT_IN"));
            }
            
            if (personaMap.getEstadoCivil().compareToIgnoreCase("CASADO/A") == 0) {
                
                Vinculos ejVinculo = new Vinculos();
                ejVinculo.setActivo("S");
                ejVinculo.setPersona(new Personas(personaMap.getId()));
                ejVinculo.setTipoVinculo("CONYUGE");

                Map<String, Object> conyugeMap = vinculoManager.getAtributos(ejVinculo, "personaVinculo.id".split(","));
                if (conyugeMap != null) {
                    Personas conyuge = this.get(new Personas(Long.parseLong(conyugeMap.get("personaVinculo.id").toString())));
                    if (conyuge != null) {
                        conyuge.setNombre(
                                (conyuge.getPrimerNombre() == null ? "" : conyuge.getPrimerNombre())
                                + " " + (conyuge.getSegundoNombre() == null ? "" : conyuge.getSegundoNombre())
                                + " " + (conyuge.getPrimerApellido() == null ? "" : conyuge.getPrimerApellido())
                                + " " + (conyuge.getSegundoNombre() == null ? "" : conyuge.getSegundoNombre())
                        );
                        personaMap.setConyuge(conyuge);
                    }
                } else {
                    personaMap.setConyuge(new Personas());
                }
            }else{
                personaMap.setConyuge(new Personas());
            }

        }
        return personaMap;
    }

}
