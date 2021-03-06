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
import py.com.mojeda.service.ejb.entity.Estudios;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.TipoEstudios;
import py.com.mojeda.service.ejb.utils.ResponseDTO;
import py.com.mojeda.service.ejb.utils.ResponseListDTO;
import py.com.mojeda.service.web.spring.config.User;
import py.com.mojeda.service.web.utils.FilterDTO;
import py.com.mojeda.service.web.utils.ReglaDTO;
import static py.com.mojeda.service.web.ws.BaseController.logger;

/**
 *
 * @author miguel.ojeda
 */
@Controller
@RequestMapping(value = "/estudios")
public class EstudiosController extends BaseController {

    String atributos = "id,fechaInicio,fechaFin,titulo,concluido,numeroRegistro,semestre,nombre,tipoEstudio.id,activo";

    @GetMapping
    public @ResponseBody
    ResponseListDTO listar(@ModelAttribute("fkModel") Long id,
            @ModelAttribute("_search") boolean filtrar,
            @ModelAttribute("filters") String filtros,
            @ModelAttribute("page") Integer pagina,
            @ModelAttribute("rows") Integer cantidad,
            @ModelAttribute("sidx") String ordenarPor,
            @ModelAttribute("sord") String sentidoOrdenamiento,
            @ModelAttribute("all") boolean todos) {

        ResponseListDTO retorno = new ResponseListDTO();
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Estudios model = new Estudios();
        model.setActivo("S");
        model.setPersona(new Personas(id));

        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarTipoEstudiosManager();
            inicializarEstudiosManager();
            
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
                total = Long.parseLong(estudiosManager.list(model).size() + "");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = estudiosManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
                    ordenarPor.split(","), sentidoOrdenamiento.split(","), true, true, camposFiltros, valorFiltro,
                    null, null, null, null, null, null, null, null, true);

            for (Map<String, Object> rpc : listMapGrupos) {
                Map<String, Object> tipoEstudio = tipoEstudiosManager.getAtributos(new TipoEstudios(Long.parseLong(rpc.get("tipoEstudio.id") == null ? "0" : rpc.get("tipoEstudio.id").toString())), "id,nombre,activo".split(","));
                rpc.put("tipoEstudio", tipoEstudio);
                rpc.remove("tipoEstudio.id");

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
            logger.error("Error: ", e);
            retorno.setStatus(500);
            retorno.setMessage("Error interno del servidor.");
        }

        return retorno;
    }

    /**
     * Mapping para el metodo GET de la vista visualizar.(visualizar Persona)
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
            inicializarTipoEstudiosManager();
            inicializarEstudiosManager();

            Estudios model = estudiosManager.get(id);

            response.setModel(model);
            response.setStatus(model == null ? 404 : 200);
            response.setMessage(model == null ? "Registro no encontrado" : "Registro encontrado");
        } catch (Exception e) {
            logger.error("Error: ", e);
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
            @RequestBody @Valid Estudios model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        Estudios ejEstudios = new Estudios();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            inicializarTipoEstudiosManager();
            inicializarEstudiosManager();

//            if(errors.hasErrors()){
//                
//                response.setStatus(400);
//                response.setMessage(errors.getAllErrors()
//				.stream()
//				.map(x -> x.getDefaultMessage())
//				.collect(Collectors.joining(",")));
//                return response;
//            }
//
//            Personas ejPersona = new Personas();
//            ejPersona.setEmpresa(new Empresas(userDetail.getIdEmpresa()));        
//            ejPersona.setDocumento(model.getDocumento());
//            
//            Map<String, Object> personaMaps = ocupacionPersonaManager.getLike(ejPersona,"id".split(","));
//            
//            if(personaMaps != null){
//                response.setStatus(205);
//                response.setMessage("Ya existe una persona con el mismo documento.");                          
//                return response;
//            }
//            
//            model.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
//            model.setActivo("S");
//            model.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
//            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
//            model.setIdUsuarioCreacion(userDetail.getId());
//            model.setIdUsuarioModificacion(userDetail.getId());
//            
//            //model = usuarioManager.guardar(model);
//            
//            response.setModel(model);
            response.setStatus(200);
            response.setMessage("Persona creado/a con exito");
        } catch (Exception e) {
            logger.error("Error: ", e);
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
            @RequestBody @Valid Estudios model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            inicializarTipoEstudiosManager();
            inicializarEstudiosManager();

//            if(errors.hasErrors()){
//                
//                response.setStatus(400);
//                response.setMessage(errors.getAllErrors()
//				.stream()
//				.map(x -> x.getDefaultMessage())
//				.collect(Collectors.joining(",")));
//                return response;
//            }     
//            
//            Personas ejPersona = new Personas();
//            model.setEmpresa(new Empresas(userDetail.getIdEmpresa()));            
//            ejPersona.setDocumento(model.getDocumento());
//            
//            Map<String, Object> personaMaps = personaManager.getLike(ejPersona, "id".split(","));
//            if (personaMaps != null
//                    && personaMaps.get("id").toString().compareToIgnoreCase(model.getId().toString()) != 0) {
//                response.setStatus(205);
//                response.setMessage("Ya existe una persona con el mismo documento.");
//                return response;
//            }           
//            
//            model.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
//            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
//            model.setIdUsuarioModificacion(userDetail.getId());
            //usuarioManager.editar(model);
            response.setStatus(200);
            response.setMessage("Persona modificado/a con exito");
        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
    /**
     * Mapping para el metodo DELETE de la vista.(eliminar IngresosEgresos)
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
            inicializarTipoEstudiosManager();
            inicializarEstudiosManager();

            Estudios model = estudiosManager.get(id);
            model.setActivo("N");
            model.setIdUsuarioEliminacion(userDetail.getId());
            model.setFechaEliminacion(new Timestamp(System.currentTimeMillis()));

            estudiosManager.update(model);

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
