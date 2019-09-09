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
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.entity.BienesSolicitudes;
import py.com.mojeda.service.ejb.entity.Ciudades;
import py.com.mojeda.service.ejb.entity.DepartamentosPais;
import py.com.mojeda.service.ejb.entity.IngresosEgresos;
import py.com.mojeda.service.ejb.entity.Paises;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.TipoIngresosEgresos;
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
@RequestMapping(value = "/inmuebles")
public class InmueblesController extends BaseController {

    String atributos = "id,numeroFinca,cuentaCatastral,distrito,escriturado,edificado,hipotecado,fechaHipoteca,vencimientoHipoteca,lugarHipoteca"
            + ",fechaDeclaracion,cuotaMensual,valorActual,saldo,direccion,marca,modeloAnio,fechaVenta,numeroMatricula,pais.id"
            + ",departamento.id,ciudad.id,barrio,activo";

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

        Bienes model = new Bienes();
        model.setActivo("S");
        model.setTipoBien("INMUEBLE");
        model.setPersona(new Personas(id));

        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarBienesManager();
            inicializarPaisesManager();
            inicializarBarriosManager();
            inicializarCiudadesManager();
            inicializarDepartamentosPaisManager();

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
                total = Long.parseLong(bienesManager.list(model).size() + "");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = bienesManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
                    ordenarPor.split(","), sentidoOrdenamiento.split(","), true, true, camposFiltros, valorFiltro,
                    null, null, null, null, null, null, null, null, true);

            for (Map<String, Object> rpc : listMapGrupos) {
                Map<String, Object> pais = paisesManager.getAtributos(new Paises(Long.parseLong(rpc.get("pais.id") == null ? "0" : rpc.get("pais.id").toString())), "id,nombre,activo".split(","));
                rpc.put("pais", pais);
                rpc.remove("pais.id");

                Map<String, Object> departamento = departamentosPaisManager.getAtributos(new DepartamentosPais(Long.parseLong(rpc.get("departamento.id") == null ? "0" : rpc.get("departamento.id").toString())), "id,nombre,activo".split(","));
                rpc.put("departamento", departamento);
                rpc.remove("departamento.id");

                Map<String, Object> ciudad = ciudadesManager.getAtributos(new Ciudades(Long.parseLong(rpc.get("ciudad.id") == null ? "0" : rpc.get("ciudad.id").toString())), "id,nombre,activo".split(","));
                rpc.put("ciudad", ciudad);
                rpc.remove("ciudad.id");
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
            inicializarBienesManager();

            Bienes model = bienesManager.getBienes(new Bienes(id));

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
            @RequestBody @Valid Bienes model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        IngresosEgresos ejIngresosEgresos = new IngresosEgresos();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            inicializarBienesManager();

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
            @RequestBody @Valid Bienes model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            inicializarBienesManager();

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
        Bienes model = null;
        BienesSolicitudes bienes = null;
        try {
            inicializarBienesManager();
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
