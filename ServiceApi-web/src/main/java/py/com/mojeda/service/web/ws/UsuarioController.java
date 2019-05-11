/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import com.google.gson.Gson;
import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Rol;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.Usuarios;
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
@RequestMapping(value = "/usuarios")
public class UsuarioController extends BaseController {
    
    String atributos = "id,alias,superUsuario,expirationTimeTokens,persona.id,rol.id,persona.sucursal.id,persona.sucursal.empresa.id,activo";
    
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

        Usuarios model = new Usuarios();
        model.setActivo("S");
        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarUsuarioManager();
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
                total = Long.parseLong(usuarioManager.list(model).size() + "");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = usuarioManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
                    ordenarPor.split(","), sentidoOrdenamiento.split(","), true, true, camposFiltros, valorFiltro,
                    null, null, null, null, null, null, null, null, true);
            
            for(Map<String, Object> rpm: listMapGrupos){
                Map<String, Object> persona = personaManager.getAtributos(new Personas(Long.parseLong(rpm.get("persona.id").toString())),
                        "primerNombre,segundoNombre,primerApellido,segundoApellido,documento,ruc,fechaNacimiento,tipoPersona,sexo,numeroHijos,numeroDependientes,estadoCivil,separacionBienes,email".split(","));
                rpm.put("persona", persona);
                rpm.remove("persona.id");
                rpm.remove("rol.id");
                rpm.remove("persona.sucursal.id");
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
     * @return
     */
    @GetMapping("/{id}")
    public @ResponseBody
    ResponseDTO getObject(
            @ModelAttribute("id") Long id) {        
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarUsuarioManager();
            inicializarPersonaManager();
            inicializarRolManager();
            inicializarSucursalManager();
            inicializarEmpresaManager();
            
            Map<String, Object> model = usuarioManager.getAtributos(new Usuarios(id),atributos.split(","));
            
            Map<String, Object> persona = personaManager.getAtributos(new Personas(Long.parseLong(model.get("persona.id").toString())),
                        "id,primerNombre,segundoNombre,primerApellido,segundoApellido,documento,ruc,fechaNacimiento,tipoPersona,sexo,numeroHijos,numeroDependientes,estadoCivil,separacionBienes,email,telefonoParticular,telefonoSecundario,direccionParticular,direccionDetallada,observacion,latitud,longitud".split(","));               
            
            Map<String, Object> sucursal = sucursalManager.getAtributos(new Sucursales(Long.parseLong(model.get("persona.sucursal.id").toString())),
                        "id,codigoSucursal,nombre,descripcion,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo".split(",")); 
            
            Map<String, Object> empresa = empresaManager.getAtributos(new Empresas(Long.parseLong(model.get("persona.sucursal.empresa.id").toString())),
                        "id,nombre,ruc,nombreFantasia,descripcion,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo".split(",")); 
            sucursal.put("empresa", empresa);     
            
            persona.put("sucursal", sucursal);                
            model.put("persona", persona);  
            
            model.remove("persona.id");
            model.remove("persona.sucursal.id");
            model.remove("persona.sucursal.empresa.id");
            
            Map<String, Object> rol = rolManager.getAtributos(new Rol(Long.parseLong(model.get("rol.id").toString())),
                        "id,nombre,activo".split(",")); 
            model.put("rol", rol);
            model.remove("rol.id");
            
            response.setModel(model);
            response.setStatus(model == null ? 404 : 200);
            response.setMessage(model == null ? "Registro no encontrado" : "OK");
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
            @Valid Usuarios model,
            Errors errors) {
        
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        Usuarios ejUsuario = new Usuarios();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            inicializarUsuarioManager();
            inicializarImagenManager();
            
            if(errors.hasErrors()){
                
                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(",")));
                return response;
            }
            
            
            model.setActivo("S");
            model.setClaveAcceso(passwordEncoder.encode(model.getClaveAcceso()));
            model.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.setIdUsuarioCreacion(userDetail.getId());
            model.setIdUsuarioModificacion(userDetail.getId());
            
            model = usuarioManager.guardar(model);
            
            response.setModel(model);
            response.setStatus(200);
            response.setMessage("Usuario creado con exito");
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
            @Valid Usuarios model,
            Errors errors) {
        
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            inicializarUsuarioManager();
            
            if(errors.hasErrors()){
                
                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(",")));
                return response;
            }
            
            
            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.setIdUsuarioModificacion(userDetail.getId());
            
            usuarioManager.update(model);
            
            response.setStatus(200);
            response.setMessage("OK");
        } catch (Exception e) {
            logger.error("Error: ",e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
    
    
}
