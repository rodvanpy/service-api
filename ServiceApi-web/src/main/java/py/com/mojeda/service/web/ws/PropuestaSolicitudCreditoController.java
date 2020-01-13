/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import py.com.mojeda.service.ejb.entity.Cuotas;
import py.com.mojeda.service.ejb.entity.Empresas;
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
import static py.com.mojeda.service.web.ws.BaseController.logger;

/**
 *
 * @author miguel.ojeda
 */
@Controller
@RequestMapping(value = "/solicitud_creditos")
public class PropuestaSolicitudCreditoController extends BaseController {

    String atributos = "id,fechaPresentacion,horaPresentacion,estado,fechaEstado,puntaje,montoSolicitado,tipoCredito,tasaInteres,tipoDesembolso.id,cliente.id,entidad,"
            + "tipoCalculoImporte.id,tipoInteres,ordenCheque,formaPago,plazo,montoSolicitadoOriginal,sucursal.id,sucursal.empresa.id,desembolsado,fechaDesembolso,estado.id,activo";

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

        PropuestaSolicitud model = new PropuestaSolicitud();
        model.setActivo("S");
        model.setIdEmpresa(userDetail.getIdEmpresa());
        model.setTipoSolicitud(new TipoSolicitudes(1L));
        //Listar solo las propuestas del funcionario
        if (!userDetail.tienePermiso("ROLE_SOLICITUDE.LISTALL")) {
            model.setFuncionario(new Funcionarios(userDetail.getId()));
        }
        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarPropuestaSolicitudManager();
            inicializarClientesManager();
            inicializarSucursalManager();
            inicializarEstadosSolicitudManager();
            inicializarEvaluacionSolicitudesCabeceraManager();
            
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
                    generarEjemplo(filtro, model);
                }

            }
            // ejemplo.setActivo("S");

            pagina = pagina != null ? pagina : 1;
            Long total = 0L;

            if (!todos) {
                total = propuestaSolicitudManager.total(model,"id", "desc");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = propuestaSolicitudManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
                    ordenarPor.split(","), sentidoOrdenamiento.split(","), true, true, camposFiltros, valorFiltro,
                    null, null, null, null, null, null, null, null, true);

            for (Map<String, Object> rpm : listMapGrupos) {

                Clientes cliente = clientesManager.getCliente(new Clientes(Long.parseLong(rpm.get("cliente.id").toString())), userDetail.getIdEmpresa(),
                        null);

                Map<String, Object> sucursal = sucursalManager.getAtributos(new Sucursales(Long.parseLong(rpm.get("sucursal.id").toString())),
                        "id,codigoSucursal,nombre,descripcion,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo".split(","));
                
                EvaluacionSolicitudesCabecera evaluacion = new EvaluacionSolicitudesCabecera();
                evaluacion.setPropuestaSolicitud(new PropuestaSolicitud(Long.parseLong(rpm.get("id").toString())));
                
                Map<String, Object> analisisCabecera = evaluacionSolicitudesCabeceraManager.getAtributos(evaluacion,
                        "id,estado.id".split(","),false,false);

                rpm.put("sucursal", sucursal);
                rpm.put("cliente", cliente);
                rpm.put("estado", estadosSolicitudManager.get(new EstadosSolicitud(Long.parseLong(rpm.get("estado.id").toString()))));
                rpm.put("estadoAnalisis", analisisCabecera == null ? new EstadosSolicitud() : estadosSolicitudManager.get(new EstadosSolicitud(Long.parseLong(analisisCabecera.get("estado.id").toString()))));
                rpm.remove("estado.id");
                rpm.remove("cliente.id");
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
            inicializarPropuestaSolicitudManager();

            PropuestaSolicitud model = propuestaSolicitudManager.getPropuestaSolicitud(new PropuestaSolicitud(id));

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
     * Mapping para el metodo GET de la vista transferir.(transferir propuesta)
     *
     * @param id de la entidad
     * @return
     */
    @PutMapping("/transferir/{id}")
    public @ResponseBody
    ResponseDTO transferir(
            @ModelAttribute("id") Long id) {
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        Gson gson = new Gson();
        try {
            inicializarPropuestaSolicitudManager();

            propuestaSolicitudManager.tranferirPropuesta(id, userDetail.getId(), userDetail.getIdEmpresa());

            response.setStatus(200);
            response.setMessage("Propuesta tranferida para analisis");
        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
    /**
     * Mapping para el metodo GET de la vista abandonar.(abandonar propuesta)
     *
     * @param id de la entidad
     * @return
     */
    @PutMapping("/abandonar/{id}")
    public @ResponseBody
    ResponseDTO abandonar(
            @ModelAttribute("id") Long id) {
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarPropuestaSolicitudManager();
            
            Map<String, Object> sucursal = propuestaSolicitudManager.getAtributos(new PropuestaSolicitud(id),
                        "estado.codigo".split(","));
            
            if(sucursal != null
                    && sucursal.get("estado.codigo").toString().compareToIgnoreCase("APR") == 0){
                response.setStatus(205);
                response.setMessage("La solicitud ya fue aprobada.");
                return response;
                
            }
            
            if(sucursal != null
                    && sucursal.get("estado.codigo").toString().compareToIgnoreCase("REC") == 0){
                response.setStatus(205);
                response.setMessage("La solicitud fue rechazada.");
                return response;
                
            }
            
            if(sucursal != null
                    && sucursal.get("estado.codigo").toString().compareToIgnoreCase("ABA") == 0){
                response.setStatus(205);
                response.setMessage("La solicitud ya fue abandonada.");
                return response;
                
            }
            
            propuestaSolicitudManager.abandonarPropuesta(id, userDetail.getId(), userDetail.getIdEmpresa());

            response.setStatus(200);
            response.setMessage("Propuesta abandonada correctamente.");
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
     * Mapping para el metodo POST de la vista crear.(crear Usuario)
     *
     * @param model entidad Usuario recibida de la vista
     * @param errors
     * @return
     */
    @PostMapping
    public @ResponseBody
    ResponseDTO create(
            @RequestBody @Valid PropuestaSolicitud model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        Funcionarios ejUsuario = new Funcionarios();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Gson gson = new Gson();
        try {
            inicializarPropuestaSolicitudManager();
            logger.info(gson.toJson(model));
            if (errors.hasErrors()) {

                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
                        .stream()
                        .map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
                return response;
            }
            //Buscar cliente con estado pendiente
            Personas ejPersonas = new Personas();
            ejPersonas.setDocumento(model.getCliente().getPersona().getDocumento());

            Clientes ejCliente = new Clientes();
            ejCliente.setPersona(ejPersonas);

            PropuestaSolicitud ejPropuestaSolicitud = new PropuestaSolicitud();
            ejPropuestaSolicitud.setCliente(ejCliente);
            ejPropuestaSolicitud.setActivo("S");
            ejPropuestaSolicitud.setEstado(new EstadosSolicitud(1L));

            if (propuestaSolicitudManager.total(ejPropuestaSolicitud) > 0) {
                response.setStatus(205);
                response.setMessage("El cliente cuenta con una solicitud en estado pendiente.");
                return response;
            }
            
            ejPropuestaSolicitud = new PropuestaSolicitud();
            ejPropuestaSolicitud.setCliente(ejCliente);
            ejPropuestaSolicitud.setActivo("S");
            ejPropuestaSolicitud.setEstado(new EstadosSolicitud(2L));

            if (propuestaSolicitudManager.total(ejPropuestaSolicitud) > 0) {
                response.setStatus(205);
                response.setMessage("El cliente cuenta con una solicitud en analisis.");
                return response;
            }

            model.setIdUsuarioCreacion(userDetail.getId());
            model.setIdUsuarioModificacion(userDetail.getId());
            model.setFuncionario(new Funcionarios(userDetail.getId()));
            
            model = propuestaSolicitudManager.guardar(model, userDetail.getIdSusursal(), userDetail.getIdEmpresa());
            
            response.setModel(model);
            response.setStatus(model == null ? 404 : 200);
            response.setMessage(model == null ? "Error al crear registro" : "Registro creado con exito");
            
        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }

    /**
     * Mapping para el metodo PUT de la vista actualizar.(actualizar
     * PropuestaSolicitud)
     *
     * @param id de la entidad
     * @param model entidad PropuestaSolicitud recibida de la vista
     * @param errors
     * @return
     */
    @PutMapping("/{id}")
    public @ResponseBody
    ResponseDTO update(
            @ModelAttribute("id") Long id,
            @RequestBody @Valid PropuestaSolicitud model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        Gson gson = new Gson();
        try {
            inicializarPropuestaSolicitudManager();
            logger.info(gson.toJson(model));
            if (errors.hasErrors()) {

                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
                        .stream()
                        .map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
                return response;
            }
            
            Map<String, Object> modelMaps = propuestaSolicitudManager.getAtributos(new PropuestaSolicitud(id), "estado.codigo,montoSolicitado".split(","));

            if (modelMaps != null
                    && modelMaps.get("estado.codigo") != null
                    && modelMaps.get("estado.codigo").toString().compareToIgnoreCase("PEN") != 0 
                    && modelMaps.get("estado.codigo").toString().compareToIgnoreCase("RET") != 0) {
                response.setModel(null);
                response.setStatus(404);
                response.setMessage("La propuesta ya no puede ser modificada.");
                
                return response;
            }
            
            if (modelMaps != null 
                    && modelMaps.get("estado.codigo") != null
                    && modelMaps.get("estado.codigo").toString().compareToIgnoreCase("RET") == 0
                    && new BigDecimal(modelMaps.get("montoSolicitado").toString()).longValue() > 0
                    && new BigDecimal(modelMaps.get("montoSolicitado").toString()).longValue() < model.getMontoSolicitado().longValue()) {
                
                response.setModel(null);
                response.setStatus(404);
                response.setMessage("El monto solicitado no puede ser mayor a la solicitada anteriormente.");
                
                return response;
            }

            //Buscar usuario por empresa
            model.setIdUsuarioCreacion(userDetail.getId());
            model.setIdUsuarioModificacion(userDetail.getId());

            model = propuestaSolicitudManager.editar(model, userDetail.getIdSusursal(), userDetail.getIdEmpresa());

            response.setModel(model);
            response.setStatus(model == null ? 404 : 200);
            response.setMessage(model == null ? "Error al modificar registro" : "Registro modificado con exito");
            
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

                Map<String, Object> clienteMaps = personaManager.getAtributos(ejPersona, "id".split(","),false,false);
                if (clienteMaps != null
                        && clienteMaps.get("id").toString().compareToIgnoreCase(model.getId().toString()) != 0) {
                    response.setStatus(205);
                    response.setMessage("Ya existe una persona con el mismo documento.");
                    return response;
                }
            }

            if (model.getRuc() != null
                    && model.getRuc().trim().compareToIgnoreCase("") != 0
                    && model.getRuc().contains("-")
                    && model.getRuc().length() > 5) {

                ejPersona = new Personas();
                ejPersona.setRuc(model.getRuc());
                ejPersona.setEmpresa(new Empresas(userDetail.getIdEmpresa()));

                Map<String, Object> clienteMaps = personaManager.getAtributos(ejPersona, "id".split(","),false, false);
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
            e.printStackTrace();
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

            propuestaSolicitudManager.delete(id,userDetail.getId());
            
            response.setStatus(200);
            response.setMessage("Registro eliminado con exito.");
        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }
    
    public void generarEjemplo(FilterDTO filtro, PropuestaSolicitud model) {
        PropuestaSolicitud entidad;
        
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
        if(filtro.getData() != null){
            
            entidad = gson.fromJson(filtro.getData(), PropuestaSolicitud.class);
            
            if(entidad.getId() != null){
                model.setId(entidad.getId());
            }
            
            if(entidad.getCliente() != null){
                model.setCliente(entidad.getCliente());
            }
            
            if(entidad.getFuncionario() != null){
                model.setFuncionario(new Funcionarios(entidad.getFuncionario().getId()));
            }
            
            if(entidad.getEstado() != null){
                model.setEstado(entidad.getEstado());
            }
            
            if(entidad.getSucursal() != null){
                model.setSucursal(entidad.getSucursal());
            }
        }
    }

}
