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
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.entity.BienesSolicitudes;
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Referencias;
import py.com.mojeda.service.ejb.entity.TipoReferencias;
import py.com.mojeda.service.ejb.entity.Vinculos;
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
@RequestMapping(value = "/vinculos")
public class VinculoController extends BaseController {

    String atributos = "id,tipoVinculo,persona.id,personaVinculo.id,activo";

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

        Vinculos model = new Vinculos();
        model.setPersona(new Personas(id));
        model.setActivo("S");

        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarVinculoManager();
            inicializarPersonaManager();
            inicializarOcupacionPersonaManager();

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
                total = Long.parseLong(vinculoManager.list(model).size() + "");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = vinculoManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
                    ordenarPor.split(","), sentidoOrdenamiento.split(","), true, true, camposFiltros, valorFiltro,
                    null, null, null, null, null, null, null, null, true);

            for (Map<String, Object> rpc : listMapGrupos) {

                OcupacionPersona ejOcupacion = new OcupacionPersona();
                ejOcupacion.setPersona(new Personas(Long.parseLong(rpc.get("personaVinculo.id").toString())));

                rpc.put("ocupaciones", ocupacionPersonaManager.getListOcupaciones(ejOcupacion));
                rpc.put("persona", personaManager.getPersona(new Personas(Long.parseLong(rpc.get("persona.id").toString()))));
                rpc.put("personaVinculo", personaManager.getPersona(new Personas(Long.parseLong(rpc.get("personaVinculo.id").toString()))));
                rpc.remove("persona.id");
                rpc.remove("personaVinculo.id");
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
     * Mapping para el metodo GET de la vista visualizar.(visualizar Referencia)
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
            inicializarVinculoManager();

            Map<String, Object> model = vinculoManager.getVinculo(new Vinculos(id));

            response.setModel(model);
            response.setStatus(model == null ? 404 : 200);
            response.setMessage(model == null ? "Registro no encontrado" : "OK");
        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }

    /**
     * Mapping para el metodo POST de la vista crear.(crear Referencia)
     *
     * @param model entidad Referencia recibida de la vista
     * @param errors
     * @return
     */
    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody
    ResponseDTO create(
            @Valid Vinculos model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarVinculoManager();

            if (errors.hasErrors()) {

                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
                        .stream()
                        .map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
                return response;
            }

            model.setActivo("S");
            model.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.setIdUsuarioCreacion(userDetail.getId());
            model.setIdUsuarioModificacion(userDetail.getId());

            vinculoManager.save(model);

            response.setStatus(200);
            response.setMessage("OK");
        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }

    /**
     * Mapping para el metodo PUT de la vista actualizar.(actualizar Referencia)
     *
     * @param id de la entidad
     * @param model entidad Referencia recibida de la vista
     * @param errors
     * @return
     */
    @PutMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody
    ResponseDTO update(
            @ModelAttribute("id") Long id,
            @Valid Vinculos model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarVinculoManager();

            if (errors.hasErrors()) {

                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
                        .stream()
                        .map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
                return response;
            }

            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.setIdUsuarioModificacion(userDetail.getId());

            vinculoManager.update(model);

            response.setStatus(200);
            response.setMessage("OK");
        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }

    /**
     * Mapping para el metodo DELETE de la vista.(eliminar Referencias)
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
        Bienes model = null;
        BienesSolicitudes bienes = null;
        try {
            inicializarVinculoManager();
            inicializarBienesSolicitudesManager();
            
            //Se verifica si el id pertenece a la referencia de una solicitud
            bienes = bienesSolicitudesManager.get(id);
            if (bienes == null) {
                
                model = bienesManager.get(id);

                model.setActivo("N");
                model.setIdUsuarioEliminacion(userDetail.getId());
                model.setFechaEliminacion(new Timestamp(System.currentTimeMillis()));
                model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                
                bienesManager.update(model);
                
            } else {
                bienes.setActivo("N");
                bienes.setIdUsuarioEliminacion(userDetail.getId());
                bienes.setFechaEliminacion(new Timestamp(System.currentTimeMillis()));
                bienes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                
                bienesSolicitudesManager.update(bienes);
                
                model = bienesManager.get(bienes.getIdBien());

                model.setActivo("N");
                model.setIdUsuarioEliminacion(userDetail.getId());
                model.setFechaEliminacion(new Timestamp(System.currentTimeMillis()));
                model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                
                bienesManager.update(model);
            }

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
