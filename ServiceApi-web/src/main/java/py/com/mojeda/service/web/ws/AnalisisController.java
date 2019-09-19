/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import com.google.gson.Gson;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.mojeda.service.ejb.entity.Clientes;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.EstadosAnalisis;
import py.com.mojeda.service.ejb.entity.EvaluacionSolicitudesCabecera;
import py.com.mojeda.service.ejb.entity.Funcionarios;
import py.com.mojeda.service.ejb.entity.PropuestaSolicitud;
import py.com.mojeda.service.ejb.entity.Sucursales;
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

    String atributos = "id,fechaInicioAnalisis,fechaFinAnalisis,fechaPrimeraAprobRech,fechaSegundaAprobRech,funcionarioAprobacion.id,estado.id,"
            + "montoAprobado,funcionarioAnalisis.id,funcionarioVerificador.id,obsApro,propuestaSolicitud.id,activo";

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

        //Buscar usuario por empresa
        Sucursales ejSucursales = new Sucursales();
        ejSucursales.setEmpresa(new Empresas(userDetail.getIdEmpresa()));

        PropuestaSolicitud propuestaSolicitud = new PropuestaSolicitud();
        propuestaSolicitud.setSucursal(ejSucursales);

        EvaluacionSolicitudesCabecera model = new EvaluacionSolicitudesCabecera();
        model.setPropuestaSolicitud(propuestaSolicitud);

        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarPropuestaSolicitudManager();
            inicializarFuncionarioManager();
            inicializarEvaluacionSolicitudesCabeceraManager();
            inicializarEstadosAnalisisManager();

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
                rpm.put("estado", estadosAnalisisManager.getEstadosSolicitud(new EstadosAnalisis(Long.parseLong(rpm.get("estado.id").toString()))));
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

        //Buscar usuario por empresa
        Sucursales ejSucursales = new Sucursales();
        ejSucursales.setEmpresa(new Empresas(userDetail.getIdEmpresa()));

        PropuestaSolicitud propuestaSolicitud = new PropuestaSolicitud();
        propuestaSolicitud.setSucursal(ejSucursales);

        EvaluacionSolicitudesCabecera model = new EvaluacionSolicitudesCabecera();
        model.setPropuestaSolicitud(propuestaSolicitud);
        model.setFuncionarioAnalisis(new Funcionarios(userDetail.getId()));

        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarPropuestaSolicitudManager();
            inicializarFuncionarioManager();
            inicializarEvaluacionSolicitudesCabeceraManager();
            inicializarEstadosAnalisisManager();

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
                rpm.put("estado", estadosAnalisisManager.getEstadosSolicitud(new EstadosAnalisis(Long.parseLong(rpm.get("estado.id").toString()))));
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
}
