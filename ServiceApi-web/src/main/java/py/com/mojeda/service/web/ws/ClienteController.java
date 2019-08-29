/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import com.google.gson.Gson;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.mojeda.service.ejb.entity.Clientes;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.utils.ResponseDTO;
import py.com.mojeda.service.ejb.utils.ResponseListDTO;
import py.com.mojeda.service.web.spring.config.User;
import py.com.mojeda.service.web.utils.FilterDTO;
import py.com.mojeda.service.web.utils.ReglaDTO;

/**
 *
 * @author miguel.ojeda
 */
@Controller
@RequestMapping(value = "/clientes")
public class ClienteController extends BaseController {
    
    String atributos = "id,persona.id,sucursal.id,activo";
    
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
        
        //Buscar por empresa
        Sucursales ejSucursales = new Sucursales();
        ejSucursales.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
        
        Clientes model = new Clientes();
        model.setActivo("S");
        model.setSucursal(ejSucursales);
        
        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarClientesManager();
            inicializarPersonaManager();
            inicializarSucursalManager();
            
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
                total = Long.parseLong(clientesManager.list(model).size() + "");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = clientesManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
                    ordenarPor.split(","), sentidoOrdenamiento.split(","), true, true, camposFiltros, valorFiltro,
                    null, null, null, null, null, null, null, null, true);
            
            for(Map<String, Object> rpm: listMapGrupos){
                Map<String, Object> persona = personaManager.getAtributos(new Personas(Long.parseLong(rpm.get("persona.id").toString())),
                        "primerNombre,segundoNombre,primerApellido,segundoApellido,documento,ruc,fechaNacimiento,tipoPersona,sexo,numeroHijos,numeroDependientes,estadoCivil,separacionBienes,email".split(","));
                
                Map<String, Object> sucursal = sucursalManager.getAtributos(new Sucursales(Long.parseLong(rpm.get("sucursal.id").toString())),
                "id,codigoSucursal,nombre,descripcion,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo".split(","));
                
                rpm.put("sucursal", sucursal);
                rpm.put("persona", persona);
                rpm.remove("persona.id");
                rpm.remove("sucursal.id");
            }
            
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
     * Mapping para el metodo GET de la vista visualizar.(visualizar Usuario)
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
            inicializarClientesManager();
            
            Clientes model = clientesManager.getCliente(new Clientes(id), included);            
            
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
     * Mapping para el metodo GET de la vista visualizar.(visualizar Cliente)
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
            inicializarClientesManager();
            inicializarPersonaManager();
            
            
            Personas ejPersonas = new Personas();          
            ejPersonas.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            
            if(documento.contains("-")){
                ejPersonas.setRuc(documento);
            }else{
                ejPersonas.setDocumento(documento);
            }
            
            Clientes ejCliente = new Clientes();
            ejCliente.setPersona(ejPersonas);
            
            ejCliente =clientesManager.getCliente(ejCliente, included);
            if(ejCliente == null){
               ejCliente = new Clientes();
               ejCliente.setPersona(personaManager.getPersona(ejPersonas, included));
            }       
            
            response.setModel(ejCliente);
            response.setStatus(ejCliente == null ? 404 : 200);
            response.setMessage(ejCliente == null ? "Registro no encontrado" : "Registro encontrado");
        } catch (Exception e) {
            logger.error("Error: ",e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
       

    /**
     * Mapping para el metodo POST de la vista crear.(crear Usuario)
     *
     * @param model entidad Usuario recibida de la vista
     * @param errors
     * @return
     */
    @PostMapping
    public @ResponseBody
    ResponseDTO create(
            @RequestBody @Valid Clientes model,
            Errors errors) {
        
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        Clientes ejUsuario = new Clientes();
        Gson gson = new Gson();
        try {
            inicializarClientesManager();
            
            if(errors.hasErrors()){
                
                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(",")));
                return response;
            }
            //Buscar cliente por empresa
            Sucursales ejSucursales = new Sucursales();
            ejSucursales.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
           
            Personas ejPersona = new Personas();
            ejPersona.setDocumento(model.getPersona().getDocumento());
            
            Clientes ejCliente = new Clientes();
            ejCliente.setSucursal(ejSucursales);
            ejCliente.setPersona(ejPersona);
            
            Map<String,Object> usuarioMaps = clientesManager.getLike(ejCliente,"id".split(","));
            
            if(usuarioMaps != null){
                response.setStatus(205);
                response.setMessage("Ya existe un cliente con el mismo documento.");                          
                return response;
            }
            
            model.getPersona().setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            model.setSucursal(ejSucursales);
            model.setActivo("S");
            model.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.setIdUsuarioCreacion(userDetail.getId());
            model.setIdUsuarioModificacion(userDetail.getId());
            
            
            model = clientesManager.guardar(model, userDetail.getIdSusursal());
                        
            response.setModel(model);
            response.setStatus(200);
            response.setMessage("Registro creado con exito");
        } catch (Exception e) {
            logger.error("Error: ",e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
    /**
     * Mapping para el metodo PUT de la vista actualizar.(actualizar Usuario)
     *
     * @param id de la entidad
     * @param model entidad Usuario recibida de la vista
     * @param errors
     * @return
     */
    @PutMapping("/{id}")
    public @ResponseBody
    ResponseDTO update(
            @ModelAttribute("id") Long id,
            @RequestBody @Valid Clientes model,
            Errors errors) {
        
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarClientesManager();
            
            if(errors.hasErrors()){
                
                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(",")));
                return response;
            }            
            
            Personas ejPersona = new Personas();
            ejPersona.setDocumento(model.getPersona().getDocumento());
            
            //Buscar cliente por empresa
            Sucursales ejSucursales = new Sucursales();
            ejSucursales.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            
            Clientes ejCliente = new Clientes();
            ejCliente.setPersona(ejPersona);
            ejCliente.setSucursal(ejSucursales);
            
            Map<String,Object> clienteMaps = clientesManager.getLike(ejCliente, "id".split(","));
            if (clienteMaps != null
                    && clienteMaps.get("id").toString().compareToIgnoreCase(model.getId().toString()) != 0) {
                response.setStatus(205);
                response.setMessage("Ya existe un cliente con el mismo documento.");
                return response;
            }           
            
            clienteMaps = clientesManager.getLike(new Clientes(id), "sucursal.id".split(","));
                     
            model.getPersona().setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.setIdUsuarioModificacion(userDetail.getId());
            
            model = clientesManager.editar(model, Long.parseLong(clienteMaps.get("sucursal.id").toString()));
            
            response.setModel(model);
            response.setStatus(200);
            response.setMessage("Registro modificado con exito");
        } catch (Exception e) {
            logger.error("Error: ",e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
    /**
     * Mapping para el metodo DELETE de la vista.(eliminar Cliente)
     *
     * @param id de la entidad
     * @return
     */
    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseDTO deleteObject(
            @ModelAttribute("id") Long id) {
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarClientesManager();

            Clientes model = clientesManager.get(id);
            model.setActivo("N");
            model.setIdUsuarioEliminacion(userDetail.getId());
            model.setFechaEliminacion(new Timestamp(System.currentTimeMillis()));

            clientesManager.update(model);

            response.setModel(model);
            response.setStatus(200);
            response.setMessage("Registro eliminado con exito.");
        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
    
    
}
