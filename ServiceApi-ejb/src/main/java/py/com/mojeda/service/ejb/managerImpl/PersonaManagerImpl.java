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

        return ejPersona;
    }

    @Override
    public Personas editar(Personas persona) throws Exception {
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
            ejPersona.setEmpresa(persona.getEmpresa());

            ejPersona = this.get(ejPersona);
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

        return ejPersona;
    }

    @Override
    public Map<String, Object> getPersona(Personas personas) throws Exception {

        String atributos = "id,primerNombre,segundoNombre,primerApellido,segundoApellido,documento,ruc,"
                + "fechaNacimiento,tipoPersona,sexo,numeroHijos,numeroDependientes,estadoCivil,separacionBienes,"
                + "email,telefonoParticular,telefonoSecundario,direccionParticular,direccionDetallada,activo,"
                + "observacion,latitud,longitud,nacionalidad.id,pais.id,departamento.id,ciudad.id,barrio.id,profesion.id,imagePath";

        Map<String, Object> persona = this.getAtributos(personas, atributos.split(","));
        if (persona != null) {

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

        }
        return persona;
    }

    @Override
    public Personas getPersona(Personas persona, String included) throws Exception {
        included = included == null ? "" : included;
        
        Personas personaMap = this.get(persona);
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
                
                personaMap.setVinculos(vinculoManager.list(ejVinculos));
            }


        }
        return personaMap;
    }

}
