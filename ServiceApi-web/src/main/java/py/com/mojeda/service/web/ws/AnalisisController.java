/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.EstadosSolicitud;
import py.com.mojeda.service.ejb.entity.EvaluacionSolicitudesCabecera;
import py.com.mojeda.service.ejb.entity.Funcionarios;
import py.com.mojeda.service.ejb.entity.PropuestaSolicitud;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.TipoSolicitudes;
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
@RequestMapping(value = "/analisis_solicitudes")
public class AnalisisController extends BaseController {

    String atributos = "id,fechaInicioAnalisis,fechaFinAnalisis,fechaPrimeraAprobRech,fechaSegundaAprobRech,funcionarioAprobacion.id,funcionarioAprobacion.alias,estado.id,"
            + "montoAprobado,funcionarioAnalisis.id,funcionarioAnalisis.alias,funcionarioVerificador.id,funcionarioVerificador.alias,obsApro,propuestaSolicitud.id,"
            + "propuestaSolicitud.fechaPresentacion,propuestaSolicitud.montoSolicitado,propuestaSolicitud.tipoSolicitud.nombre,propuestaSolicitud.tipoSolicitud.id,"
            + "propuestaSolicitud.sucursal.nombre,propuestaSolicitud.montoSolicitadoOriginal,activo";

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

        EvaluacionSolicitudesCabecera model = new EvaluacionSolicitudesCabecera();
        model.setIdEmpresa(userDetail.getIdEmpresa());

        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarPropuestaSolicitudManager();
            inicializarFuncionarioManager();
            inicializarEvaluacionSolicitudesCabeceraManager();
            inicializarEstadosSolicitudManager();
            
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
            logger.info("INICIO-- "+ new Date(System.currentTimeMillis()));
            if (!todos) {
                total = evaluacionSolicitudesCabeceraManager.total(model,"id", "desc");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }
            
            logger.info("INICIO LISTAR-- "+ new Date(System.currentTimeMillis()));
            listMapGrupos = evaluacionSolicitudesCabeceraManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
                    ordenarPor.split(","), sentidoOrdenamiento.split(","), true, true, camposFiltros, valorFiltro,
                    null, null, null, null, null, null, null, null, true);
            logger.info("FIN LISTAR-- "+ new Date(System.currentTimeMillis()));
            PropuestaSolicitud propuesta;
            TipoSolicitudes tipoSolicitud;
            Funcionarios funcionarios;
            for (Map<String, Object> rpm : listMapGrupos) {
                
                propuesta = new PropuestaSolicitud();
                propuesta.setFechaPresentacion(rpm.get("propuestaSolicitud.fechaPresentacion") == null ? null : date.parse(rpm.get("propuestaSolicitud.fechaPresentacion").toString()));
                propuesta.setId(rpm.get("propuestaSolicitud.id") == null ? null : Long.parseLong(rpm.get("propuestaSolicitud.id").toString()));
                propuesta.setMontoSolicitado(rpm.get("propuestaSolicitud.montoSolicitado") == null ? null : new BigDecimal(rpm.get("propuestaSolicitud.montoSolicitado").toString()));
                propuesta.setMontoSolicitadoOriginal(rpm.get("propuestaSolicitud.montoSolicitadoOriginal") == null ? null : new BigDecimal(rpm.get("propuestaSolicitud.montoSolicitadoOriginal").toString()));               
                
                tipoSolicitud = new TipoSolicitudes();
                tipoSolicitud.setId(rpm.get("propuestaSolicitud.tipoSolicitud.id") == null ? null : Long.parseLong(rpm.get("propuestaSolicitud.tipoSolicitud.id").toString()));
                tipoSolicitud.setNombre(rpm.get("propuestaSolicitud.tipoSolicitud.nombre") == null ? null : rpm.get("propuestaSolicitud.tipoSolicitud.nombre").toString());
                propuesta.setTipoSolicitud(tipoSolicitud);                
                rpm.put("propuestaSolicitud", propuesta); 
                rpm.put("sucursal", rpm.get("propuestaSolicitud.sucursal.nombre"));
                
                funcionarios = new Funcionarios();
                funcionarios.setId(rpm.get("funcionarioAprobacion.id") == null ? null : Long.parseLong(rpm.get("funcionarioAprobacion.id").toString()));
                funcionarios.setAlias(rpm.get("funcionarioAprobacion.alias") == null ? null : rpm.get("funcionarioAprobacion.alias").toString());                
                rpm.put("funcionarioAprobacion", funcionarios.getId() == null ? null : funcionarios);
                
                funcionarios = new Funcionarios();
                funcionarios.setId(rpm.get("funcionarioAnalisis.id") == null ? null : Long.parseLong(rpm.get("funcionarioAnalisis.id").toString()));
                funcionarios.setAlias(rpm.get("funcionarioAnalisis.alias") == null ? null : rpm.get("funcionarioAnalisis.alias").toString());                
                rpm.put("funcionarioAnalisis", funcionarios.getId() == null ? null : funcionarios);
                
                funcionarios = new Funcionarios();
                funcionarios.setId(rpm.get("funcionarioVerificador.id") == null ? null : Long.parseLong(rpm.get("funcionarioVerificador.id").toString()));
                funcionarios.setAlias(rpm.get("funcionarioVerificador.alias") == null ? null : rpm.get("funcionarioVerificador.alias").toString());
                rpm.put("funcionarioVerificador", funcionarios.getId() == null ? null : funcionarios);
                
                rpm.put("estado", estadosSolicitudManager.getEstadosSolicitud(new EstadosSolicitud(Long.parseLong(rpm.get("estado.id").toString()))));
                
                rpm.remove("funcionarioAnalisis.id");
                rpm.remove("funcionarioVerificador.id");
                rpm.remove("funcionarioAprobacion.id");
                rpm.remove("propuestaSolicitud.id");
                rpm.remove("estado.id");
                rpm.remove("propuestaSolicitud.fechaPresentacion");
                rpm.remove("propuestaSolicitud.montoSolicitado");
                rpm.remove("propuestaSolicitud.montoSolicitadoOriginal");
                rpm.remove("propuestaSolicitud.tipoSolicitud.nombre");
                rpm.remove("propuestaSolicitud.sucursal.nombre");
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
            logger.info("FIN-- "+ new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            logger.error("Error: ", e);
            retorno.setStatus(500);
            retorno.setMessage("Error interno del servidor.");
        }

        return retorno;
    }

    @GetMapping("/mis-analisis")
    public @ResponseBody
    ResponseListDTO listarMisAnalisis(@ModelAttribute("_search") boolean filtrar,
            @ModelAttribute("filters") String filtros,
            @ModelAttribute("page") Integer pagina,
            @ModelAttribute("rows") Integer cantidad,
            @ModelAttribute("sidx") String ordenarPor,
            @ModelAttribute("sord") String sentidoOrdenamiento,
            @ModelAttribute("all") boolean todos) {

        ResponseListDTO retorno = new ResponseListDTO();
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        EvaluacionSolicitudesCabecera model = new EvaluacionSolicitudesCabecera();
        model.setIdEmpresa(userDetail.getIdEmpresa());
        model.setFuncionarioAnalisis(new Funcionarios(userDetail.getId()));

        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarPropuestaSolicitudManager();
            inicializarFuncionarioManager();
            inicializarEvaluacionSolicitudesCabeceraManager();
            inicializarEstadosSolicitudManager();
            
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
                total = evaluacionSolicitudesCabeceraManager.total(model,"id", "desc");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = evaluacionSolicitudesCabeceraManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
                    ordenarPor.split(","), sentidoOrdenamiento.split(","), true, true, camposFiltros, valorFiltro,
                    null, null, null, null, null, null, null, null, true);
            
            PropuestaSolicitud propuesta;
            TipoSolicitudes tipoSolicitud;
            Funcionarios funcionarios;
            for (Map<String, Object> rpm : listMapGrupos) {
                propuesta = new PropuestaSolicitud();
                propuesta.setFechaPresentacion(rpm.get("propuestaSolicitud.fechaPresentacion") == null ? null : date.parse(rpm.get("propuestaSolicitud.fechaPresentacion").toString()));
                propuesta.setId(rpm.get("propuestaSolicitud.id") == null ? null : Long.parseLong(rpm.get("propuestaSolicitud.id").toString()));
                propuesta.setMontoSolicitado(rpm.get("propuestaSolicitud.montoSolicitado") == null ? null : new BigDecimal(rpm.get("propuestaSolicitud.montoSolicitado").toString()));
                propuesta.setMontoSolicitadoOriginal(rpm.get("propuestaSolicitud.montoSolicitadoOriginal") == null ? null : new BigDecimal(rpm.get("propuestaSolicitud.montoSolicitadoOriginal").toString()));               
                tipoSolicitud = new TipoSolicitudes();
                tipoSolicitud.setId(rpm.get("propuestaSolicitud.tipoSolicitud.id") == null ? null : Long.parseLong(rpm.get("propuestaSolicitud.tipoSolicitud.id").toString()));
                tipoSolicitud.setNombre(rpm.get("propuestaSolicitud.tipoSolicitud.nombre") == null ? null : rpm.get("propuestaSolicitud.tipoSolicitud.nombre").toString());
                propuesta.setTipoSolicitud(tipoSolicitud);                
                rpm.put("propuestaSolicitud", propuesta); 
                rpm.put("sucursal", rpm.get("propuestaSolicitud.sucursal.nombre"));
                
                funcionarios = new Funcionarios();
                funcionarios.setId(rpm.get("funcionarioAprobacion.id") == null ? null : Long.parseLong(rpm.get("funcionarioAprobacion.id").toString()));
                funcionarios.setAlias(rpm.get("funcionarioAprobacion.alias") == null ? null : rpm.get("funcionarioAprobacion.alias").toString());                
                rpm.put("funcionarioAprobacion", funcionarios.getId() == null ? null : funcionarios);
                
                funcionarios = new Funcionarios();
                funcionarios.setId(rpm.get("funcionarioAnalisis.id") == null ? null : Long.parseLong(rpm.get("funcionarioAnalisis.id").toString()));
                funcionarios.setAlias(rpm.get("funcionarioAnalisis.alias") == null ? null : rpm.get("funcionarioAnalisis.alias").toString());                
                rpm.put("funcionarioAnalisis", funcionarios.getId() == null ? null : funcionarios);
                
                funcionarios = new Funcionarios();
                funcionarios.setId(rpm.get("funcionarioVerificador.id") == null ? null : Long.parseLong(rpm.get("funcionarioVerificador.id").toString()));
                funcionarios.setAlias(rpm.get("funcionarioVerificador.alias") == null ? null : rpm.get("funcionarioVerificador.alias").toString());
                rpm.put("funcionarioVerificador", funcionarios.getId() == null ? null : funcionarios);
                rpm.put("estado", estadosSolicitudManager.getEstadosSolicitud(new EstadosSolicitud(Long.parseLong(rpm.get("estado.id").toString()))));
                
                rpm.remove("funcionarioAnalisis.id");
                rpm.remove("funcionarioVerificador.id");
                rpm.remove("funcionarioAprobacion.id");
                rpm.remove("propuestaSolicitud.id");
                rpm.remove("estado.id");
                rpm.remove("propuestaSolicitud.fechaPresentacion");
                rpm.remove("propuestaSolicitud.montoSolicitado");
                rpm.remove("propuestaSolicitud.montoSolicitadoOriginal");
                rpm.remove("propuestaSolicitud.tipoSolicitud.nombre");
                rpm.remove("propuestaSolicitud.sucursal.nombre");
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
    
    @GetMapping("/pre-aprobados")
    public @ResponseBody
    ResponseListDTO listarPreAprobados(@ModelAttribute("_search") boolean filtrar,
            @ModelAttribute("filters") String filtros,
            @ModelAttribute("page") Integer pagina,
            @ModelAttribute("rows") Integer cantidad,
            @ModelAttribute("sidx") String ordenarPor,
            @ModelAttribute("sord") String sentidoOrdenamiento,
            @ModelAttribute("all") boolean todos) {

        ResponseListDTO retorno = new ResponseListDTO();
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
        EvaluacionSolicitudesCabecera model = new EvaluacionSolicitudesCabecera();
        model.setIdEmpresa(userDetail.getIdEmpresa());
        model.setEstado(new EstadosSolicitud(3L));
        
        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarPropuestaSolicitudManager();
            inicializarFuncionarioManager();
            inicializarEvaluacionSolicitudesCabeceraManager();
            inicializarEstadosSolicitudManager();
            
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
                total = Long.parseLong(evaluacionSolicitudesCabeceraManager.list(model).size() + "");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = evaluacionSolicitudesCabeceraManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
                    ordenarPor.split(","), sentidoOrdenamiento.split(","), true, true, camposFiltros, valorFiltro,
                    null, null, null, null, null, null, null, null, true);

            for (Map<String, Object> rpm : listMapGrupos) {

                rpm.put("propuestaSolicitud", rpm.get("propuestaSolicitud.id") == null ? null : propuestaSolicitudManager.getPropuestaSolicitud(new PropuestaSolicitud(Long.parseLong(rpm.get("propuestaSolicitud.id").toString()))));
                rpm.put("funcionarioAprobacion", rpm.get("funcionarioAprobacion.id") == null ? null : funcionarioManager.getUsuario(new Funcionarios(Long.parseLong(rpm.get("funcionarioAprobacion.id").toString())), null));
                rpm.put("funcionarioAnalisis", rpm.get("funcionarioAnalisis.id") == null ? null : funcionarioManager.getUsuario(new Funcionarios(Long.parseLong(rpm.get("funcionarioAnalisis.id").toString())), null));
                rpm.put("funcionarioVerificador", rpm.get("funcionarioVerificador.id") == null ? null : funcionarioManager.getUsuario(new Funcionarios(Long.parseLong(rpm.get("funcionarioVerificador.id").toString())), null));
                rpm.put("estado", estadosSolicitudManager.getEstadosSolicitud(new EstadosSolicitud(Long.parseLong(rpm.get("estado.id").toString()))));
                rpm.remove("funcionarioAnalisis.id");
                rpm.remove("funcionarioVerificador.id");
                rpm.remove("funcionarioAprobacion.id");
                rpm.remove("propuestaSolicitud.id");
                rpm.remove("estado.id");
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
     * Mapping para el metodo GET de la vista visualizar.(visualizar Usuario)
     *
     * @param id de la entidad
     * @return
     */
    @GetMapping("/analizar/{id}")
    public @ResponseBody
    ResponseDTO getObjectReview(
            @ModelAttribute("id") Long id) {
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarEvaluacionSolicitudesCabeceraManager();

            Map<String, Object> modelMaps = evaluacionSolicitudesCabeceraManager.getAtributos(new EvaluacionSolicitudesCabecera(id), "funcionarioAnalisis.id".split(","));

            if (modelMaps != null
                    && modelMaps.get("funcionarioAnalisis.id") != null
                    && !modelMaps.get("funcionarioAnalisis.id").equals(userDetail.getId())) {
                response.setModel(null);
                response.setStatus(404);
                response.setMessage("El analisis ya fue asignado a otro funcionario.");
                
                return response;
            }
            
            EvaluacionSolicitudesCabecera model = evaluacionSolicitudesCabeceraManager.evaluar(userDetail.getId(), id);

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
     * Mapping para el metodo GET de la vista visualizar.(visualizar Analisis)
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
            inicializarEvaluacionSolicitudesCabeceraManager();
            
            EvaluacionSolicitudesCabecera model = evaluacionSolicitudesCabeceraManager.getEvaluacion(new EvaluacionSolicitudesCabecera(id));

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
     * Mapping para el metodo PUT de la vista actualizar.(actualizar Analisis)
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
            @RequestBody @Valid EvaluacionSolicitudesCabecera model,
            Errors errors) {
        
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarEvaluacionSolicitudesCabeceraManager();
            
            if(errors.hasErrors()){
                
                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(",")));
                return response;
            }            
            
            Map<String, Object> modelMaps = evaluacionSolicitudesCabeceraManager.getAtributos(new EvaluacionSolicitudesCabecera(id),
                    "estado.id,propuestaSolicitud.montoSolicitado".split(","));

            if (modelMaps != null
                    && modelMaps.get("estado.id") != null
                    && modelMaps.get("estado.id").toString().compareToIgnoreCase("2") != 0) {
                response.setModel(null);
                response.setStatus(404);
                response.setMessage("El analisis ya no puede ser modificado.");
                
                return response;
            }
            
            if (modelMaps != null
                    && modelMaps.get("propuestaSolicitud.montoSolicitado") != null
                    && model.getEstado().getId() == 6
                    && model.getMontoAprobado() != null
                    && new BigDecimal(modelMaps.get("propuestaSolicitud.montoSolicitado").toString()).longValue() < model.getMontoAprobado()) {
                response.setModel(null);
                response.setStatus(404);
                response.setMessage("El monto aprobado no puede ser mayor al solicitado.");
                
                return response;
            }
                       
            model = evaluacionSolicitudesCabeceraManager.guardar(model, userDetail.getId(), userDetail.getIdEmpresa());
            
            response.setModel(model);
            response.setStatus(model == null ? 404 : 200);
            response.setMessage(model == null ? "Error al modificar registro" : "Registro modificado con exito");
            
        } catch (Exception e) {
            logger.error("Error: ",e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
}
