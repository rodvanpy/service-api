/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import com.google.gson.Gson;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.mojeda.service.ejb.entity.Barrios;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.InformconfSolicitudes;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.informconf.InformconfPersonas;
import py.com.mojeda.service.ejb.utils.ResponseDTO;
import py.com.mojeda.service.ejb.utils.Utils;
import py.com.mojeda.service.web.spring.config.User;

/**
 *
 * @author miguel.ojeda
 */
@Controller
@RequestMapping(value = "/informconf")
public class InformconfController extends BaseController {

    String atributos = "id,nombre,activo";

    @GetMapping("/solicitud")
    public @ResponseBody
    ResponseDTO reporteSolicitud(@ModelAttribute("tipo") String tipo,
            @ModelAttribute("historico") boolean historico,
            @ModelAttribute("documento") String documento,
            @ModelAttribute("nroSolicitud") Long nroSolicitud) {
        ResponseDTO retorno = new ResponseDTO();
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        InformconfSolicitudes model = new InformconfSolicitudes();
        List<Map<String, Object>> listMapGrupos = null;
        Gson gson = new Gson();
        try {
            inicializarInformconfSolicitudesManager();
            inicializarPersonaManager();

            model.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            model.setIdSolicitud(nroSolicitud);
            model.setDocumento(documento);
            model.setTipoSolicitud(tipo);

            model = informconfSolicitudesManager.get(model);

            InformconfPersonas registros = null;
            if (model != null) {
                registros = gson.fromJson(model.getModel(), InformconfPersonas.class);
                registros.getSolicitudesDetalle().sort(Comparator.comparing(o -> o.getFecha().getTime()));
            } else {
                Personas ejPersona = new Personas();
                ejPersona.setDocumento(documento);

                ejPersona = personaManager.get(ejPersona);

                ResponseDTO<InformconfPersonas> reporteInformconf = informconfSolicitudesManager.get(tipo,
                        documento, nroSolicitud, ejPersona.getId(),
                        userDetail.getIdEmpresa(), userDetail.getId(), "A", false);

                registros = reporteInformconf.getModel();
            }

            retorno.setModel(registros);
            retorno.setStatus(registros == null ? 404 : 200);
            retorno.setMessage(registros == null ? "Registro no encontrado" : "OK");
        } catch (Exception e) {
            logger.error("Error: ", e);
            retorno.setStatus(500);
            retorno.setMessage("Error interno del servidor.");
        }

        return retorno;
    }

    @GetMapping("/reporte")
    public @ResponseBody
    ResponseDTO reporte(@ModelAttribute("tipoPersona") String tipoPersona,
            @ModelAttribute("historico") boolean historico,
            @ModelAttribute("documento") String documento) {
        ResponseDTO retorno = new ResponseDTO();
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        InformconfSolicitudes model = new InformconfSolicitudes();
        List<Map<String, Object>> listMapGrupos = null;
        InformconfPersonas registros = null;
        Gson gson = new Gson();
        try {
            inicializarInformconfSolicitudesManager();
            inicializarPersonaManager();

            ResponseDTO<InformconfPersonas> reporteInformconf = informconfSolicitudesManager.getReport("REPORTE", tipoPersona, documento,
                    userDetail.getIdEmpresa(), userDetail.getId(), "A");

            registros = reporteInformconf.getModel();

            retorno.setModel(registros);
            retorno.setStatus(registros == null ? 404 : 200);
            retorno.setMessage(registros == null ? "Registro no encontrado" : "OK");
        } catch (Exception e) {
            logger.error("Error: ", e);
            retorno.setStatus(500);
            retorno.setMessage("Error interno del servidor.");
        }

        return retorno;
    }

    /**
     * Mapping para el metodo GET de la vista visualizar.(visualizar
     * InformconfSolicitudes)
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
            inicializarBarriosManager();

            Barrios model = barriosManager.get(id);

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

}
