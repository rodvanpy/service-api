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
    public Map<String, Object> getPersona(Personas persona, String included) throws Exception {
        String atributos = "id,primerNombre,segundoNombre,primerApellido,segundoApellido,documento,ruc,"
                + "fechaNacimiento,tipoPersona,sexo,numeroHijos,numeroDependientes,estadoCivil,separacionBienes,profesion.id,"
                + "email,telefonoParticular,telefonoSecundario,direccionParticular,direccionDetallada,activo,empresa.id,"
                + "observacion,latitud,longitud,nacionalidad.id,pais.id,departamento.id,ciudad.id,barrio.id,profesion.id,imagePath";

        Map<String, Object> personaMap = this.getAtributos(persona, atributos.split(","));
        if (personaMap != null) {

            Map<String, Object> profesion = profesionesManager.getAtributos(new Profesiones(Long.parseLong(personaMap.get("profesion.id") == null ? "0" : personaMap.get("profesion.id").toString())), "id,nombre,activo".split(","));
            personaMap.put("profesion", profesion);
            personaMap.remove("profesion.id");

            Map<String, Object> nacionalidad = nacionalidadesManager.getAtributos(new Nacionalidades(Long.parseLong(personaMap.get("nacionalidad.id") == null ? "0" : personaMap.get("nacionalidad.id").toString())), "id,nombre,codigo,activo".split(","));
            personaMap.put("nacionalidad", nacionalidad);
            personaMap.remove("nacionalidad.id");

            Map<String, Object> pais = paisesManager.getAtributos(new Paises(Long.parseLong(personaMap.get("pais.id") == null ? "0" : personaMap.get("pais.id").toString())), "id,nombre,activo".split(","));
            personaMap.put("pais", pais);
            personaMap.remove("pais.id");

            Map<String, Object> departamento = departamentosPaisManager.getAtributos(new DepartamentosPais(Long.parseLong(personaMap.get("departamento.id") == null ? "0" : personaMap.get("departamento.id").toString())), "id,nombre,activo".split(","));
            personaMap.put("departamento", departamento);
            personaMap.remove("departamento.id");

            Map<String, Object> ciudad = ciudadesManager.getAtributos(new Ciudades(Long.parseLong(personaMap.get("ciudad.id") == null ? "0" : personaMap.get("ciudad.id").toString())), "id,nombre,activo".split(","));
            personaMap.put("ciudad", ciudad);
            personaMap.remove("ciudad.id");

            Map<String, Object> barrio = barriosManager.getAtributos(new Barrios(Long.parseLong(personaMap.get("barrio.id") == null ? "0" : personaMap.get("barrio.id").toString())), "id,nombre,activo".split(","));
            personaMap.put("barrio", barrio);
            personaMap.remove("barrio.id");
            
            if (included.contains("inmuebles")) {
                Bienes ejBienes = new Bienes();
                ejBienes.setPersona(new Personas(Long.parseLong(personaMap.get("id").toString())));
                ejBienes.setActivo("S");
                ejBienes.setTipoBien("INMUEBLE");

                List<Map<String, Object>> inmueblesMap = bienesManager.getListBienes(ejBienes);
                personaMap.put("bienesInmuebles", inmueblesMap);
            }

            if (included.contains("vehiculos")) {
                Bienes ejBienes = new Bienes();
                ejBienes.setPersona(new Personas(Long.parseLong(personaMap.get("id").toString())));
                ejBienes.setActivo("S");
                ejBienes.setTipoBien("VEHICULO");

                List<Map<String, Object>> vehiculosMap = bienesManager.getListBienes(ejBienes);
                personaMap.put("bienesVehiculo", vehiculosMap);
            }

            if (included.contains("referencias")) {
                Referencias ejReferencia = new Referencias();
                ejReferencia.setPersona(new Personas(Long.parseLong(personaMap.get("id").toString())));
                ejReferencia.setActivo("S");

                List<Map<String, Object>> referenciasMap = referenciaManager.getListReferencias(ejReferencia);
                personaMap.put("referencias", referenciasMap);
            }

            if (included.contains("ingresos")) {
                TipoIngresosEgresos ejTipoIngresosEgresos = new TipoIngresosEgresos();
                ejTipoIngresosEgresos.setTipo("I");

                IngresosEgresos ejIngresosEgresos = new IngresosEgresos();
                ejIngresosEgresos.setPersona(new Personas(Long.parseLong(personaMap.get("id").toString())));
                ejIngresosEgresos.setActivo("S");
                ejIngresosEgresos.setTipoIngresosEgresos(ejTipoIngresosEgresos);

                List<Map<String, Object>> ingresosMap = ingresosEgresosManager.getListIngresosEgresos(ejIngresosEgresos);
                personaMap.put("ingresos", ingresosMap);
            }

            if (included.contains("egresos")) {
                TipoIngresosEgresos ejTipoIngresosEgresos = new TipoIngresosEgresos();
                ejTipoIngresosEgresos.setTipo("E");

                IngresosEgresos ejIngresosEgresos = new IngresosEgresos();
                ejIngresosEgresos.setPersona(new Personas(Long.parseLong(personaMap.get("id").toString())));
                ejIngresosEgresos.setActivo("S");
                ejIngresosEgresos.setTipoIngresosEgresos(ejTipoIngresosEgresos);

                List<Map<String, Object>> egresosMap = ingresosEgresosManager.getListIngresosEgresos(ejIngresosEgresos);
                personaMap.put("egresos", egresosMap);
            }

            if (included.contains("ocupaciones")) {
                OcupacionPersona ejOcupaciones = new OcupacionPersona();
                ejOcupaciones.setPersona(new Personas(Long.parseLong(personaMap.get("id").toString())));
                ejOcupaciones.setActivo("S");

                List<Map<String, Object>> ocupacionPersonaMap = ocupacionPersonaManager.getListOcupaciones(ejOcupaciones);
                personaMap.put("ocupaciones", ocupacionPersonaMap);
            }
            
            if (included.contains("estudios")) {
                Estudios ejEstudios = new Estudios();
                ejEstudios.setPersona(new Personas(Long.parseLong(personaMap.get("id").toString())));
                ejEstudios.setActivo("S");

                List<Map<String, Object>> ocupacionPersonaMap = estudiosManager.getListEstudios(ejEstudios);
                personaMap.put("estudios", ocupacionPersonaMap);
            }                       
            
            if (included.contains("empresa")) {
                Map<String, Object> empresa = empresaManager.getAtributos(new Empresas(Long.parseLong(personaMap.get("empresa.id").toString())),
                    "id,nombre,ruc,nombreFantasia,descripcion,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo".split(","));
                
                personaMap.put("empresa", empresa);
                personaMap.remove("empresa.id");
            }

        }
        return personaMap;
    }
    
}
