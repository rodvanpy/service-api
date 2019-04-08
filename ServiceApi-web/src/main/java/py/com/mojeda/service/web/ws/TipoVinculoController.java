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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.TipoReferencias;
import py.com.mojeda.service.ejb.entity.TipoVinculos;
import py.com.mojeda.service.ejb.entity.Usuarios;
import py.com.mojeda.service.ejb.utils.ResponseDTO;
import py.com.mojeda.service.ejb.utils.ResponseListDTO;
import py.com.mojeda.service.web.spring.config.User;

/**
 *
 * @author miguel.ojeda
 */
@Controller
@RequestMapping(value = "/tipos-vinculos")
public class TipoVinculoController extends BaseController {
    
    String atributos = "id,nombre";
    
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

        TipoVinculos model = new TipoVinculos();
        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarTipoVinculoManager();
            Gson gson = new Gson();
            String camposFiltros = null;
            String valorFiltro = null;

//            if (filtrar) {
//                FilterDTO filtro = gson.fromJson(filtros, FilterDTO.class);
//                if (filtro.getGroupOp().compareToIgnoreCase("OR") == 0) {
//                    for (ReglaDTO regla : filtro.getRules()) {
//                        if (camposFiltros == null) {
//                            camposFiltros = regla.getField();
//                            valorFiltro = regla.getData();
//                        } else {
//                            camposFiltros += "," + regla.getField();
//                        }
//                    }
//                } else {
//                    //ejemplo = generarEjemplo(filtro, ejemplo);
//                }
//
//            }
            // ejemplo.setActivo("S");

            pagina = pagina != null ? pagina : 1;
            Long total = 0L;

            if (!todos) {
                total = Long.parseLong(tipoVinculoManager.list(model).size() + "");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = tipoVinculoManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
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
     * Mapping para el metodo GET de la vista visualizar.(visualizar Empresa)
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
            inicializarTipoVinculoManager();
                        
            TipoVinculos model = tipoVinculoManager.get(id);
               
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
     * Mapping para el metodo POST de la vista crear.(crear Empresa)
     *
     * @param model entidad Usuario recibida de la vista
     * @param errors
     * @return
     */
    @PostMapping
    @CrossOrigin(origins = "http://localhost:4599")
    public @ResponseBody
    ResponseDTO create(
            @RequestBody @Valid TipoVinculos model,
            Errors errors) {
        
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarTipoVinculoManager();
            
            if(errors.hasErrors()){
                
                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(",")));
                return response;
            }
            
            model.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            
            tipoVinculoManager.save(model);
            
            TipoVinculos empresa = new TipoVinculos();
            empresa.setNombre(model.getNombre());
            
            response.setStatus(200);
            response.setMessage("El Tipo Vinculo ha sido guardado");           
            response.setModel(tipoVinculoManager.get(empresa));
            
        } catch (Exception e) {
            logger.error("Error: ",e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
    /**
     * Mapping para el metodo PUT de la vista actualizar.(actualizar Empresa)
     *
     * @param id de la entidad
     * @param model entidad Usuario recibida de la vista
     * @param errors
     * @return
     */
    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4599")
    public @ResponseBody
    ResponseDTO update(
            @ModelAttribute("id") Long id,
            @RequestBody @Valid TipoVinculos model,
            Errors errors) {
        
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarTipoVinculoManager();
            
            if(errors.hasErrors()){
                
                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(",")));
                return response;
            }
            
            model.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            
            tipoVinculoManager.update(model);
            
            response.setStatus(200);
            response.setMessage("El Tipo Vinculo ha sido guardado");
        } catch (Exception e) {
            logger.error("Error: ",e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
    
    
}
