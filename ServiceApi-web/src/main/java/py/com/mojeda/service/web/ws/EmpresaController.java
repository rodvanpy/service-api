/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import com.google.gson.Gson;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.mojeda.service.ejb.entity.Barrios;
import py.com.mojeda.service.ejb.entity.Ciudades;
import py.com.mojeda.service.ejb.entity.DepartamentosPais;
import py.com.mojeda.service.ejb.entity.DepartamentosSucursal;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Documentos;
import py.com.mojeda.service.ejb.entity.Paises;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.TipoDocumentos;
import py.com.mojeda.service.ejb.entity.Funcionarios;
import py.com.mojeda.service.ejb.utils.ResponseDTO;
import py.com.mojeda.service.ejb.utils.ResponseListDTO;
import py.com.mojeda.service.web.spring.config.User;
import py.com.mojeda.service.ejb.utils.Base64Bytes;
import py.com.mojeda.service.web.utils.FilterDTO;
import py.com.mojeda.service.web.utils.ReglaDTO;
import static py.com.mojeda.service.web.ws.BaseController.logger;

/**
 *
 * @author miguel.ojeda
 */
@Controller
@RequestMapping(value = "/empresas")
public class EmpresaController extends BaseController {

    String atributos = "id,nombre,nombreFantasia,descripcion,ruc,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo";
    String atributos_sucursales = "id,nombre,codigoSucursal,descripcion,direccion,telefono"
            + ",fax,telefonoMovil,email,observacion,longitud,latitud,activo,pais.id,ciudad.id,departamento.id,barrio.id";

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

        Empresas model = new Empresas();
        model.setActivo("S");
        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarEmpresaManager();
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
                total = empresaManager.total(model,"id", "desc");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = empresaManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
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
            logger.error("Error: ", e);
            retorno.setStatus(500);
            retorno.setMessage("Error interno del servidor.");
        }

        return retorno;
    }

    @GetMapping("/{id}/sucursales")
    public @ResponseBody
    ResponseListDTO listarSucursalesEmpresa(@ModelAttribute("_search") boolean filtrar,
            @ModelAttribute("filters") String filtros,
            @ModelAttribute("page") Integer pagina,
            @ModelAttribute("rows") Integer cantidad,
            @ModelAttribute("sidx") String ordenarPor,
            @ModelAttribute("sord") String sentidoOrdenamiento,
            @ModelAttribute("id") Long id,
            @ModelAttribute("all") boolean todos) {

        ResponseListDTO retorno = new ResponseListDTO();
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Sucursales model = new Sucursales();
        model.setActivo("S");
        model.setEmpresa(new Empresas(id));

        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarSucursalManager();
            inicializarCiudadesManager();
            inicializarDepartamentosPaisManager();
            inicializarBarriosManager();
            inicializarPaisesManager();
            inicializarDepartamentosSucursalManager();
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
                total = Long.parseLong(sucursalManager.list(model).size() + "");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = sucursalManager.listAtributos(model, atributos_sucursales.split(","), todos, inicio, cantidad,
                    ordenarPor.split(","), sentidoOrdenamiento.split(","), true, true, camposFiltros, valorFiltro,
                    null, null, null, null, null, null, null, null, true);

            if (todos) {
                total = Long.parseLong(listMapGrupos.size() + "");
            }

            Integer totalPaginas = Integer.parseInt(total.toString()) / cantidad;

            for (Map<String, Object> rpm : listMapGrupos) {
                DepartamentosSucursal ejDepSucursal = new DepartamentosSucursal();
                ejDepSucursal.setActivo("S");
                ejDepSucursal.setSucursal(new Sucursales(Long.parseLong(rpm.get("id").toString())));
                List<Map<String, Object>> listMapDepart = departamentosSucursalManager.listAtributos(ejDepSucursal, "id,alias,nombreArea,descripcionArea,activo".split(","));
                
                rpm.put("departamentos", listMapDepart);
                
                Map<String, Object> pais = paisesManager.getAtributos(new Paises(Long.parseLong(rpm.get("pais.id") == null ? "0" : rpm.get("pais.id").toString())), "id,nombre,activo".split(","));
                rpm.put("pais", pais);
                rpm.remove("pais.id");

                Map<String, Object> departamento = departamentosPaisManager.getAtributos(new DepartamentosPais(Long.parseLong(rpm.get("departamento.id") == null ? "0" : rpm.get("departamento.id").toString())), "id,nombre,activo".split(","));
                rpm.put("departamento", departamento);
                rpm.remove("departamento.id");

                Map<String, Object> ciudad = ciudadesManager.getAtributos(new Ciudades(Long.parseLong(rpm.get("ciudad.id") == null ? "0" : rpm.get("ciudad.id").toString())), "id,nombre,activo".split(","));
                rpm.put("ciudad", ciudad);
                rpm.remove("ciudad.id");

                Map<String, Object> barrio = barriosManager.getAtributos(new Barrios(Long.parseLong(rpm.get("barrio.id") == null ? "0" : rpm.get("barrio.id").toString())), "id,nombre,activo".split(","));
                rpm.put("barrio", barrio);
                rpm.remove("barrio.id");
            }

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
     * Mapping para el metodo POST de la vista crear.(crear Empresa)
     *
     * @param id
     * @param model entidad Usuario recibida de la vista
     * @param errors
     * @return
     */
    @PostMapping("/{id}/sucursales")
    //@CrossOrigin(origins = "http://localhost:4599")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody
    ResponseDTO create(
            @ModelAttribute("id") Long id,
            @RequestBody @Valid Sucursales model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();

        model.setEmpresa(new Empresas(id));
        try {
            inicializarSucursalManager();
            inicializarDepartamentosSucursalManager();

            if (errors.hasErrors()) {

                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
                        .stream()
                        .map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
                return response;
            }

            model.setNombre(model.getNombre().toUpperCase());

            Sucursales sucursal = new Sucursales();
            sucursal.setNombre(model.getNombre());
            sucursal.setEmpresa(model.getEmpresa());

            Map<String, Object> sucursalMaps = sucursalManager.getLike(sucursal, "nombre".split(","));
            if (sucursalMaps != null) {
                response.setStatus(200);
                response.setMessage("Ya existe una sucursal con el mismo nombre.");
                response.setModel(sucursalManager.get(sucursal));
                return response;
            }

            String[] codigo = model.getNombre().split(" ");
            String codigoNombre = "";
            for (int i = 0; i < codigo.length; i++) {
                codigoNombre = codigoNombre + codigo[i].substring(0, 1);
            }
            sucursal = new Sucursales();
            sucursal.setEmpresa(model.getEmpresa());
            //Numero Sucursal
            Integer numeroSucursal = sucursalManager.total(sucursal) + 1;
            //Cantidad Sucursales
            Integer cantidadSucursal = sucursalManager.total(new Sucursales()) + 1;

            model.setCodigoSucursal(codigoNombre + "-" + numeroSucursal + "-" + cantidadSucursal);
            model.setActivo("S");
            model.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.setIdUsuarioCreacion(userDetail.getId());
            model.setIdUsuarioModificacion(userDetail.getId());

            sucursalManager.save(model);

            sucursal = new Sucursales();
            sucursal.setNombre(model.getNombre());
            sucursal.setCodigoSucursal(model.getCodigoSucursal());

            sucursal = sucursalManager.get(sucursal);

            logger.info("getId: " + sucursal.getId());
            logger.info("getCodigoSucursal: " + sucursal.getCodigoSucursal());
            logger.info("getNombre: " + sucursal.getNombre());

            for (DepartamentosSucursal rpm : model.getDepartamentos() == null ? new ArrayList<DepartamentosSucursal>() : model.getDepartamentos()) {

                rpm.setNombreArea(rpm.getNombreArea().toUpperCase());
                rpm.setAlias(rpm.getAlias().toUpperCase());
                rpm.setActivo("S");
                rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioCreacion(userDetail.getId());
                rpm.setIdUsuarioModificacion(userDetail.getId());
                rpm.setSucursal(sucursal);

                departamentosSucursalManager.save(rpm);
            }

            response.setStatus(200);
            response.setMessage("La sucursal ha sido guardada");
            response.setModel(sucursalManager.getSucursal(sucursal));

        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
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
            inicializarEmpresaManager();

            Map<String, Object> model = empresaManager.getEmpresa(new Empresas(id));                      

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
     * Mapping para el metodo POST de la vista crear.(crear Empresa)
     *
     * @param model entidad Usuario recibida de la vista
     * @param errors
     * @return
     */
    @PostMapping
    //@CrossOrigin(origins = "http://localhost:4599")
    //@CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody
    ResponseDTO create(
            @RequestBody @Valid Empresas model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        Documentos ejDocumentos = null;
        try {
            inicializarEmpresaManager();
            inicializarDocumentoManager();
            inicializarTipoDocumentosManager();

            if (errors.hasErrors()) {

                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
                        .stream()
                        .map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
                return response;
            }

            model.setNombre(model.getNombre().toUpperCase());
            model.setNombreFantasia(model.getNombreFantasia().toUpperCase());

            Empresas empresa = new Empresas();
            empresa.setNombre(model.getNombre());

            Map<String, Object> sucursalMaps = empresaManager.getLike(empresa, "nombre".split(","));
            if (sucursalMaps != null) {
                response.setStatus(205);
                response.setMessage("Ya existe una empresa con el mismo nombre.");
                return response;
            }

            empresa = new Empresas();
            empresa.setNombreFantasia(model.getNombreFantasia());

            sucursalMaps = empresaManager.getLike(empresa, "nombre".split(","));
            if (sucursalMaps != null) {
                response.setStatus(205);
                response.setMessage("Ya existe una empresa con el mismo nombre de fantasia.");
                return response;
            }

            empresa = new Empresas();
            empresa.setRuc(model.getRuc());

            sucursalMaps = empresaManager.getLike(empresa, "nombre".split(","));
            if (sucursalMaps != null) {
                response.setStatus(205);
                response.setMessage("Ya existe una empresa con el mismo ruc.");
                return response;
            }

            model.setActivo("S");
            model.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.setIdUsuarioCreacion(userDetail.getId());
            model.setIdUsuarioModificacion(userDetail.getId());

            Map<String, Object> empresaMap = empresaManager.guardar(model);

            response.setMessage("La empresa ha sido guardada");
            response.setModel(empresaMap);
            response.setStatus(200);

        } catch (Exception e) {
            logger.error("Error: ", e);
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
    //@CrossOrigin(origins = "http://localhost:4599")
    //@CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody
    ResponseDTO update(
            @ModelAttribute("id") Long id,
            @RequestBody @Valid Empresas model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        Documentos ejDocumentos = null;
        try {
            inicializarEmpresaManager();
            inicializarTipoDocumentosManager();
            inicializarDocumentoManager();

            if (errors.hasErrors()) {

                response.setStatus(400);
                response.setMessage(errors.getAllErrors()
                        .stream()
                        .map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
                return response;
            }

            Empresas dato = empresaManager.get(id);

            Empresas ejEmpresas = new Empresas();
            ejEmpresas.setNombre(model.getNombre());

            Map<String, Object> empresaMaps = empresaManager.getLike(ejEmpresas, "id,nombre".split(","));
            if (empresaMaps != null
                    && empresaMaps.get("id").toString().compareToIgnoreCase(dato.getId().toString()) != 0) {
                response.setStatus(205);
                response.setMessage("Ya existe una empresa con el mismo nombre.");
                return response;
            }

            ejEmpresas = new Empresas();
            ejEmpresas.setNombreFantasia(model.getNombreFantasia());

            empresaMaps = empresaManager.getLike(ejEmpresas, "id,nombre".split(","));
            if (empresaMaps != null
                    && empresaMaps.get("id").toString().compareToIgnoreCase(dato.getId().toString()) != 0) {
                response.setStatus(205);
                response.setMessage("Ya existe una empresa con el mismo nombre de fantasia.");
                return response;
            }

            ejEmpresas = new Empresas();
            ejEmpresas.setRuc(model.getRuc());

            empresaMaps = empresaManager.getLike(ejEmpresas, "id,nombre".split(","));
            if (empresaMaps != null
                    && empresaMaps.get("id").toString().compareToIgnoreCase(dato.getId().toString()) != 0) {
                response.setStatus(205);
                response.setMessage("Ya existe una empresa con el mismo ruc.");
                return response;
            }

            model.setNombre(model.getNombre().toUpperCase());
            model.setNombreFantasia(model.getNombreFantasia().toUpperCase());
            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.setIdUsuarioModificacion(userDetail.getId());
            model.setFechaCreacion(dato.getFechaCreacion());
            model.setIdUsuarioCreacion(dato.getIdUsuarioCreacion());
            model.setIdUsuarioEliminacion(dato.getIdUsuarioEliminacion());

            Map<String, Object> empresaMap = empresaManager.editar(model);

            response.setModel(empresaMap);
            response.setStatus(200);
            response.setMessage("La empresa ha sido guardada");
        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }

    /**
     * Mapping para el metodo GET de la vista visualizar.(visualizar Empresa)
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
            inicializarEmpresaManager();

            Empresas model = empresaManager.get(id);
            model.setActivo("N");
            model.setIdUsuarioEliminacion(userDetail.getId());
            model.setFechaEliminacion(new Timestamp(System.currentTimeMillis()));

            empresaManager.update(model);

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
