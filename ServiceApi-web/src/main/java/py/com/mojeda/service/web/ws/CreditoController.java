/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import com.google.gson.Gson;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
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
import py.com.mojeda.service.ejb.entity.Creditos;
import py.com.mojeda.service.ejb.entity.Cuotas;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.EstadosCredito;
import py.com.mojeda.service.ejb.entity.EstadosSolicitud;
import py.com.mojeda.service.ejb.entity.EvaluacionSolicitudesCabecera;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.PropuestaSolicitud;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.Funcionarios;
import py.com.mojeda.service.ejb.entity.Modalidades;
import py.com.mojeda.service.ejb.entity.TipoCalculos;
import py.com.mojeda.service.ejb.entity.TipoDesembolsos;
import py.com.mojeda.service.ejb.entity.TipoSolicitudes;
import py.com.mojeda.service.ejb.utils.ResponseDTO;
import py.com.mojeda.service.ejb.utils.ResponseListDTO;
import py.com.mojeda.service.web.spring.config.User;
import py.com.mojeda.service.web.utils.FilterDTO;
import py.com.mojeda.service.web.utils.ReglaDTO;
import static py.com.mojeda.service.web.ws.BaseController.date;
import static py.com.mojeda.service.web.ws.BaseController.logger;

/**
 *
 * @author miguel.ojeda
 */
@Controller
@RequestMapping(value = "/creditos")
public class CreditoController extends BaseController {

    String atributos = "id,montoCapital,montoInteres,saldoCapital,saldoInteres,ordenCheque,idFunsionarioDesembolso,operacion,totalDevengado,idCreditoCancelado,fechaVencimiento,"
            + "fechaEstado,periodoGracia,periodoInteres,periodoCapital,tasaInteres,gastosAdministrativos,estado.id,fechaGeneracion,fechaCalificacion,plazoOperacion,"
            + "totalDesembolsado,cambioVencimientos,fechaUltCalculoSaldo,sucursal.id,sucursal.nombre,propuestaSolicitud.id,"
            + "propuestaSolicitud.fechaPresentacion,propuestaSolicitud.montoSolicitado,propuestaSolicitud.tipoSolicitud.nombre,propuestaSolicitud.tipoSolicitud.id";

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

        Creditos model = new Creditos();
        model.setActivo("S");
        model.setIdEmpresa(userDetail.getIdEmpresa());

        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarCreditosManager();
            inicializarEstadosCreditoManager();
          
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
                total = creditosManager.total(model,"id", "desc");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = creditosManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
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
                rpm.put("sucursal", rpm.get("sucursal.nombre"));
                rpm.put("estado", estadosCreditoManager.get(new EstadosCredito(Long.parseLong(rpm.get("estado.id").toString()))));                
                rpm.remove("estado.id");
                rpm.remove("propuestaSolicitud.id");
                rpm.remove("propuestaSolicitud.fechaPresentacion");
                rpm.remove("propuestaSolicitud.montoSolicitado");
                rpm.remove("propuestaSolicitud.montoSolicitadoOriginal");
                rpm.remove("propuestaSolicitud.tipoSolicitud.nombre");
                rpm.remove("propuestaSolicitud.sucursal.nombre");
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
            logger.error("Error: ", e);
            retorno.setStatus(500);
            retorno.setMessage("Error interno del servidor.");
        }

        return retorno;
    }

    /**
     * Mapping para el metodo GET de la vista visualizar.(visualizar Credito)
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
            inicializarCreditosManager();

            Creditos model = creditosManager.get(id);

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
     * Mapping para el metodo GET de la vista visualizar.(visualizar Detalle Cuotas)
     *
     * @param montoCapital
     * @param montoInteres
     * @param plazo
     * @param periodoCapital
     * @param periodoInteres
     * @param periodoGracia
     * @param tipoCalculo
     * @param modalidad
     * @param tipoDesembolso
     * @return
     */
    @GetMapping("/detalle-cuotas")
    public @ResponseBody
    ResponseDTO getCuotas(
            @ModelAttribute("montoCapital") BigDecimal montoCapital,
            @ModelAttribute("montoInteres") BigDecimal montoInteres,
            @ModelAttribute("plazo") Short plazo,
            @ModelAttribute("periodoCapital") Short periodoCapital,
            @ModelAttribute("periodoInteres") Short periodoInteres,
            @ModelAttribute("periodoGracia") Short periodoGracia,
            @ModelAttribute("tipoCalculo") Long tipoCalculo,
            @ModelAttribute("modalidad") Long modalidad,
            @ModelAttribute("tipoDesembolso") Long tipoDesembolso) {
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarCuotasManager();

            List<Cuotas> listCuotas = cuotasManager.cuotasCredito(montoCapital, montoInteres, new Date(System.currentTimeMillis()),
                    5, plazo, periodoCapital, periodoInteres, periodoGracia, new TipoCalculos(tipoCalculo),
                    new Modalidades(modalidad), new TipoDesembolsos(tipoDesembolso), new Date(System.currentTimeMillis()));            

            response.setModel(listCuotas);
            response.setStatus(listCuotas == null ? 404 : 200);
            response.setMessage(listCuotas == null ? "Registro no encontrado" : "Registro encontrado");
        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    


    /**
     * Mapping para el metodo GET de la vista visualizar.(visualizar Usuario)
     *
     * @param idSolicitud
     * @param idPersona
     * @param tipoRelacion
     * @param included
     * @return
     */
    @GetMapping("/persona-solicitud")
    public @ResponseBody
    ResponseDTO getPersonaSolicitud(
            @ModelAttribute("idSolicitud") Long idSolicitud,
            @ModelAttribute("idPersona") Long idPersona,
            @ModelAttribute("tipoRelacion") String tipoRelacion,
            @ModelAttribute("included") String included) {
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        Gson gson = new Gson();
        try {
            inicializarPropuestaSolicitudManager();
            inicializarPersonaManager();

            //Personas modelPersona = personaManager.getPersona(new Personas(idPersona),included);
            logger.info(idPersona +" " + idSolicitud +" " + tipoRelacion);
            Personas model = propuestaSolicitudManager.getPersonaSolicitud(idSolicitud, idPersona, tipoRelacion);

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
     * Mapping para el metodo PUT de la vista actualizar.(actualizar Persona)
     *
     * @param id de la entidad
     * @param model entidad Persona recibida de la vista
     * @param errors
     * @return
     */
    @PutMapping("/personas/{id}")
    public @ResponseBody
    ResponseDTO updatePersonaSolicitud(
            @ModelAttribute("id") Long id,
            @RequestBody @Valid Personas model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            inicializarPersonaManager();
            inicializarPropuestaSolicitudManager();

            if (errors.hasErrors()) {

                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
                        .stream()
                        .map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
                return response;
            }

            Personas ejPersona = new Personas();
            if (model.getDocumento() != null
                    && model.getDocumento().trim().compareToIgnoreCase("") != 0) {

                ejPersona.setDocumento(model.getDocumento());
                ejPersona.setEmpresa(new Empresas(userDetail.getIdEmpresa()));

                Map<String, Object> clienteMaps = personaManager.getLike(ejPersona, "id".split(","));
                if (clienteMaps != null
                        && clienteMaps.get("id").toString().compareToIgnoreCase(model.getId().toString()) != 0) {
                    response.setStatus(205);
                    response.setMessage("Ya existe una persona con el mismo documento.");
                    return response;
                }
            }

            if (model.getRuc() != null
                    && model.getRuc().trim().compareToIgnoreCase("") != 0) {

                ejPersona = new Personas();
                ejPersona.setRuc(model.getRuc());
                ejPersona.setEmpresa(new Empresas(userDetail.getIdEmpresa()));

                Map<String, Object> clienteMaps = personaManager.getLike(ejPersona, "id".split(","));
                if (clienteMaps != null
                        && clienteMaps.get("id").toString().compareToIgnoreCase(model.getId().toString()) != 0) {
                    response.setStatus(205);
                    response.setMessage("Ya existe una persona con el mismo ruc.");
                    return response;
                }
            }

            model.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.setIdUsuarioModificacion(userDetail.getId());

            //VERIFICAR SI LA PERSONA PERTENECE A LA SOLICITUD
            propuestaSolicitudManager.editarPersonaSolicitud(model, id, userDetail.getId());
            response.setModel(propuestaSolicitudManager.getPersonaSolicitud(id, model.getId(),"D"));
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
     * Mapping para el metodo DELETE de la vista.(eliminar PropuestaSolicitud)
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
            inicializarPropuestaSolicitudManager();

            PropuestaSolicitud model = propuestaSolicitudManager.get(id);
            model.setActivo("N");
            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.setIdUsuarioModificacion(userDetail.getId());
            model.setIdUsuarioEliminacion(userDetail.getId());
            model.setFechaEliminacion(new Timestamp(System.currentTimeMillis()));

            propuestaSolicitudManager.update(model);

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
