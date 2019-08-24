/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import com.google.gson.Gson;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.IngresosEgresos;
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Referencias;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.Funcionarios;
import py.com.mojeda.service.ejb.utils.ResponseDTO;
import py.com.mojeda.service.ejb.utils.ResponseListDTO;
import py.com.mojeda.service.web.spring.config.User;
import py.com.mojeda.service.web.utils.FilterDTO;
import py.com.mojeda.service.web.utils.PersonasDTO;
import py.com.mojeda.service.web.utils.ReglaDTO;

/**
 *
 * @author miguel.ojeda
 */
@Controller
@RequestMapping(value = "/personas")
public class PersonaController extends BaseController {
    
    String atributos = "id,primerNombre,segundoNombre,primerApellido,segundoApellido,documento,ruc,fechaNacimiento,tipoPersona,sexo"
            + ",numeroHijos,numeroDependientes,estadoCivil,email,activo";
    
    @GetMapping
    public @ResponseBody
    ResponseListDTO listar(@ModelAttribute("_search") boolean filtrar,
            @ModelAttribute("filters") String filtros,
            @ModelAttribute("page") Integer pagina,
            @ModelAttribute("rows") Integer cantidad,
            @ModelAttribute("sidx") String ordenarPor,
            @ModelAttribute("sord") String sentidoOrdenamiento,
            @ModelAttribute("all") boolean todos) {

        ResponseListDTO retorno = new ResponseListDTO();
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Personas model = new Personas();
        model.setActivo("S");
        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarPersonaManager();
            
            Gson gson = new Gson();
            String camposFiltros = null;
            String valorFiltro = null;

            if (filtrar) {
                FilterDTO filtro = gson.fromJson(filtros, FilterDTO.class);
                if (filtro.getGroupOp().compareToIgnoreCase("OR") == 0) {
                    for (ReglaDTO regla : filtro.getRules()) {
                        if (camposFiltros == null) {
                            camposFiltros = regla.getField();
                            valorFiltro = regla.getData();
                        } else {
                            camposFiltros += "," + regla.getField();
                        }
                    }
                } else {
                    //ejemplo = generarEjemplo(filtro, ejemplo);
                }

            }
            // ejemplo.setActivo("S");

            pagina = pagina != null ? pagina : 1;
            Long total = 0L;

            if (!todos) {
                total = Long.parseLong(personaManager.list(model).size() + "");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = personaManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
                    ordenarPor.split(","), sentidoOrdenamiento.split(","), true, true, camposFiltros, valorFiltro,
                    null, null, null, null, null, null, null, null, true);
            
            
            if (todos) {
                total = Long.parseLong(listMapGrupos.size() + "");
            }
            
            
            Integer totalPaginas = Integer.parseInt(total.toString()) / cantidad;

            retorno.setRecords(total);
            retorno.setTotal(totalPaginas + 1);
            retorno.setRows(listMapGrupos);
            retorno.setPage(pagina);
            retorno.setStatus(200);
            retorno.setMessage("OK");
            
        } catch (Exception e) {
            logger.error("Error: ",e);
            retorno.setStatus(500);
            retorno.setMessage("Error interno del servidor.");
        }

        return retorno;
    }
    
    /**
     * Mapping para el metodo GET de la vista visualizar.(visualizar Persona)
     *
     * @param id de la entidad
     * @param included
     * @return
     */
    @GetMapping("/{id}")
    public @ResponseBody
    ResponseDTO getObject(
            @ModelAttribute("id") Long id,
            @ModelAttribute("included") String included) {        
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarPersonaManager();
            
            Personas model = personaManager.getPersona(new Personas(id), included);            
            
            response.setModel(model);
            response.setStatus(model == null ? 404 : 200);
            response.setMessage(model == null ? "Registro no encontrado" : "Registro encontrado");
        } catch (Exception e) {
            logger.error("Error: ",e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
    /**
     * Mapping para el metodo GET de la vista visualizar.(visualizar Persona)
     *
     * @param documento de la entidad
     * @param included
     * @return
     */
    @GetMapping("/documento/{documento}")
    public @ResponseBody
    ResponseDTO getObjectCi(
            @ModelAttribute("documento") String documento,
            @ModelAttribute("included") String included) {        
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarPersonaManager();
            
            Personas ejPersonas = new Personas();
            ejPersonas.setDocumento(documento);
            ejPersonas.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            
            ejPersonas = personaManager.getPersona(ejPersonas, included);            
            
            response.setModel(ejPersonas);
            response.setStatus(ejPersonas == null ? 404 : 200);
            response.setMessage(ejPersonas == null ? "Registro no encontrado" : "Registro encontrado");
        } catch (Exception e) {
            logger.error("Error: ",e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
    /**
     * Mapping para el metodo GET de la vista visualizar.(visualizar Persona)
     *
     * @param documento de la entidad
     * @return
     */
    @GetMapping("/ruc/{documento}")
    public @ResponseBody
    ResponseDTO getObjectRuc(
            @ModelAttribute("documento") String documento) {        
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarPersonaManager();
            
            Personas ejPersonas = new Personas();
            ejPersonas.setRuc(documento);
            ejPersonas.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            
            Map<String, Object> modelMaps = personaManager.getPersona(ejPersonas);            
            
            response.setModel(modelMaps);
            response.setStatus(modelMaps == null ? 404 : 200);
            response.setMessage(modelMaps == null ? "Registro no encontrado" : "Registro encontrado");
        } catch (Exception e) {
            logger.error("Error: ",e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }

    /**
     * Mapping para el metodo POST de la vista crear.(crear Persona)
     *
     * @param model entidad Persona recibida de la vista
     * @param errors
     * @return
     */
    @PostMapping
    public @ResponseBody
    ResponseDTO create(
            @RequestBody @Valid PersonasDTO model,
            Errors errors) {
        
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        Funcionarios ejUsuario = new Funcionarios();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            inicializarPersonaManager();
            inicializarBienesManager();
            inicializarIngresosEgresosManager();
            inicializarOcupacionPersonaManager();
            inicializarReferenciaManager();
            
            if(errors.hasErrors()){
                
                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(",")));
                return response;
            }

            Personas ejPersona = new Personas();
            ejPersona.setEmpresa(new Empresas(userDetail.getIdEmpresa()));        
            ejPersona.setDocumento(model.getPersona().getDocumento());
            
            Map<String, Object> personaMaps = personaManager.getLike(ejPersona,"id".split(","));
            
            if(personaMaps != null){
                response.setStatus(205);
                response.setMessage("Ya existe una persona con el mismo documento.");                          
                return response;
            }
            
            model.getPersona().setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            model.getPersona().setActivo("S");
            model.getPersona().setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            model.getPersona().setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.getPersona().setIdUsuarioCreacion(userDetail.getId());
            model.getPersona().setIdUsuarioModificacion(userDetail.getId());
            
            model.setPersona(personaManager.guardar(model.getPersona()));
            
            for (Bienes rpm : (model.getBienesInmuebles() == null ? new ArrayList<Bienes> (): model.getBienesInmuebles())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    rpm.setIdUsuarioCreacion(userDetail.getId());
                    rpm.setPersona(new Personas(model.getPersona().getId()));
                    
                    rpm = bienesManager.guardarBienes(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    
                    rpm = bienesManager.editarBienes(rpm);
                }                
            }
            
            for (Bienes rpm : (model.getBienesVehiculo()  == null ? new ArrayList<Bienes> (): model.getBienesVehiculo())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    rpm.setIdUsuarioCreacion(userDetail.getId());
                    rpm.setPersona(new Personas(model.getPersona().getId()));
                    
                    rpm = bienesManager.guardarBienes(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    
                    rpm = bienesManager.editarBienes(rpm);
                }                
            }
            
            for (IngresosEgresos rpm : (model.getEgresos() == null ? new ArrayList<IngresosEgresos> (): model.getEgresos())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    rpm.setIdUsuarioCreacion(userDetail.getId());
                    rpm.setPersona(new Personas(model.getPersona().getId()));
                    
                    rpm = ingresosEgresosManager.guardarIngresosEgresos(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    
                    rpm = ingresosEgresosManager.editarIngresosEgresos(rpm);
                }                
            }
            
            for (IngresosEgresos rpm : (model.getIngresos() == null ? new ArrayList<IngresosEgresos> (): model.getIngresos())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    rpm.setIdUsuarioCreacion(userDetail.getId());
                    rpm.setPersona(new Personas(model.getPersona().getId()));
                    
                    rpm = ingresosEgresosManager.guardarIngresosEgresos(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    
                    rpm = ingresosEgresosManager.editarIngresosEgresos(rpm);
                }                
            }
            
            for (OcupacionPersona rpm : (model.getOcupaciones() == null ? new ArrayList<OcupacionPersona> (): model.getOcupaciones())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    rpm.setIdUsuarioCreacion(userDetail.getId());
                    rpm.setPersona(new Personas(model.getPersona().getId()));
                    
                    rpm = ocupacionPersonaManager.guardarOcupacion(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    
                    rpm = ocupacionPersonaManager.editarOcupacion(rpm);
                }                
            }
            
            for (Referencias rpm : (model.getReferencias() == null ? new ArrayList<Referencias> (): model.getReferencias())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    rpm.setIdUsuarioCreacion(userDetail.getId());
                    rpm.setPersona(new Personas(model.getPersona().getId()));
                    
                    rpm = referenciaManager.guardarReferencia(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    
                    rpm = referenciaManager.editarReferencia(rpm);
                }                
            }
            
            response.setModel(personaManager.getPersona(ejPersona, "inmuebles,vehiculos,referencias,ingresos,egresos,ocupaciones"));
            response.setStatus(200);
            response.setMessage("Persona creado/a con exito");
        } catch (Exception e) {
            logger.error("Error: ",e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
    /**
     * Mapping para el metodo PUT de la vista actualizar.(actualizar Persona)
     *
     * @param id de la entidad
     * @param model entidad Persona recibida de la vista
     * @param errors
     * @return
     */
    @PutMapping("/{id}")
    public @ResponseBody
    ResponseDTO update(
            @ModelAttribute("id") Long id,
            @RequestBody @Valid PersonasDTO model,
            Errors errors) {
        
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            inicializarPersonaManager();
            inicializarBienesManager();
            inicializarIngresosEgresosManager();
            inicializarOcupacionPersonaManager();
            inicializarReferenciaManager();
            
            if(errors.hasErrors()){
                
                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(",")));
                return response;
            }     
            
            Personas ejPersona = new Personas();
            ejPersona.setEmpresa(new Empresas(userDetail.getIdEmpresa()));            
            ejPersona.setDocumento(model.getPersona().getDocumento());
            
            Map<String, Object> personaMaps = personaManager.getLike(ejPersona, "id".split(","));
            if (personaMaps != null
                    && personaMaps.get("id").toString().compareToIgnoreCase(model.getPersona().getId().toString()) != 0) {
                response.setStatus(205);
                response.setMessage("Ya existe una persona con el mismo documento.");
                return response;
            }           
            
            model.getPersona().setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            model.getPersona().setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.getPersona().setIdUsuarioModificacion(userDetail.getId());
            
            model.setPersona(personaManager.editar(model.getPersona()));
            
            for (Bienes rpm : (model.getBienesInmuebles() == null ? new ArrayList<Bienes> (): model.getBienesInmuebles())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    rpm.setIdUsuarioCreacion(userDetail.getId());
                    rpm.setPersona(new Personas(model.getPersona().getId()));
                    
                    rpm = bienesManager.guardarBienes(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    
                    rpm = bienesManager.editarBienes(rpm);
                }                
            }
            
            for (Bienes rpm : (model.getBienesVehiculo()  == null ? new ArrayList<Bienes> (): model.getBienesVehiculo())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    rpm.setIdUsuarioCreacion(userDetail.getId());
                    rpm.setPersona(new Personas(model.getPersona().getId()));
                    
                    rpm = bienesManager.guardarBienes(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    
                    rpm = bienesManager.editarBienes(rpm);
                }                
            }
            
            for (IngresosEgresos rpm : (model.getEgresos() == null ? new ArrayList<IngresosEgresos> (): model.getEgresos())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    rpm.setIdUsuarioCreacion(userDetail.getId());
                    rpm.setPersona(new Personas(model.getPersona().getId()));
                    
                    rpm = ingresosEgresosManager.guardarIngresosEgresos(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    
                    rpm = ingresosEgresosManager.editarIngresosEgresos(rpm);
                }                
            }
            
            for (IngresosEgresos rpm : (model.getIngresos() == null ? new ArrayList<IngresosEgresos> (): model.getIngresos())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    rpm.setIdUsuarioCreacion(userDetail.getId());
                    rpm.setPersona(new Personas(model.getPersona().getId()));
                    
                    rpm = ingresosEgresosManager.guardarIngresosEgresos(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    
                    rpm = ingresosEgresosManager.editarIngresosEgresos(rpm);
                }                
            }
            
            for (OcupacionPersona rpm : (model.getOcupaciones() == null ? new ArrayList<OcupacionPersona> (): model.getOcupaciones())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    rpm.setIdUsuarioCreacion(userDetail.getId());
                    rpm.setPersona(new Personas(model.getPersona().getId()));
                    
                    rpm = ocupacionPersonaManager.guardarOcupacion(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    
                    rpm = ocupacionPersonaManager.editarOcupacion(rpm);
                }                
            }
            
            for (Referencias rpm : (model.getReferencias() == null ? new ArrayList<Referencias> (): model.getReferencias())) {
                if(rpm.getId() == null){
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    rpm.setIdUsuarioCreacion(userDetail.getId());
                    rpm.setPersona(new Personas(model.getPersona().getId()));
                    
                    rpm = referenciaManager.guardarReferencia(rpm);
                }else{
                    rpm.setActivo("S");
                    rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    rpm.setIdUsuarioModificacion(userDetail.getId());
                    
                    rpm = referenciaManager.editarReferencia(rpm);
                }                
            }
            
            response.setModel(personaManager.getPersona(ejPersona, "inmuebles,vehiculos,referencias,ingresos,egresos,ocupaciones"));
            
            response.setStatus(200);
            response.setMessage("Persona modificado/a con exito");
        } catch (Exception e) {
            logger.error("Error: ",e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
    
    
}
