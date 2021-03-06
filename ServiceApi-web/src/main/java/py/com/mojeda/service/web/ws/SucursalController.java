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
import py.com.mojeda.service.ejb.entity.Paises;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.utils.ResponseDTO;
import py.com.mojeda.service.ejb.utils.ResponseListDTO;
import py.com.mojeda.service.web.spring.config.User;
import py.com.mojeda.service.web.utils.FilterDTO;
import py.com.mojeda.service.web.utils.ReglaDTO;

/**
 *
 * @author miguel.ojeda
 */
@Controller
@RequestMapping(value = "/sucursales")
public class SucursalController extends BaseController {

    String atributos = "id,nombre,codigoSucursal,descripcion,direccion,telefono,fax,telefonoMovil,email,"
            + "observacion,activo,longitud,latitud";
    String atributos_departamento = "id,alias,nombreArea,descripcionArea,activo";

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

        Sucursales model = new Sucursales();
        model.setActivo("S");
        model.setEmpresa(new Empresas(userDetail.getIdEmpresa()));

        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarSucursalManager();
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
                total = sucursalManager.total(model, "id", "desc");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = sucursalManager.listAtributos(model, atributos.split(","), todos, inicio, cantidad,
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
            retorno.setStatus(500);
            retorno.setMessage("Error interno del servidor.");
        }

        return retorno;
    }

    @GetMapping("/{id}/departamentos")
    public @ResponseBody
    ResponseListDTO listarDepartamentos(@ModelAttribute("id") Long id,
            @ModelAttribute("_search") boolean filtrar,
            @ModelAttribute("filters") String filtros,
            @ModelAttribute("page") Integer pagina,
            @ModelAttribute("rows") Integer cantidad,
            @ModelAttribute("sidx") String ordenarPor,
            @ModelAttribute("sord") String sentidoOrdenamiento,
            @ModelAttribute("all") boolean todos) {

        ResponseListDTO retorno = new ResponseListDTO();
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        DepartamentosSucursal model = new DepartamentosSucursal();
        model.setSucursal(new Sucursales(id));
        model.setActivo("S");

        List<Map<String, Object>> listMapGrupos = null;
        try {
            inicializarDepartamentosSucursalManager();
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
                total = Long.parseLong(departamentosSucursalManager.list(model).size() + "");
            }

            Integer inicio = ((pagina - 1) < 0 ? 0 : pagina - 1) * cantidad;

            if (total < inicio) {
                inicio = Integer.parseInt(total.toString()) - Integer.parseInt(total.toString()) % cantidad;
                pagina = Integer.parseInt(total.toString()) / cantidad;
            }

            listMapGrupos = departamentosSucursalManager.listAtributos(model, atributos_departamento.split(","), todos, inicio, cantidad,
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
            inicializarSucursalManager();
            inicializarDepartamentosSucursalManager();
            inicializarPaisesManager();
            inicializarDepartamentosPaisManager();
            inicializarCiudadesManager();
            inicializarBarriosManager();

            Map<String, Object> model = sucursalManager.getAtributos(new Sucursales(id),
                    "id,codigoSucursal,nombre,descripcion,direccion,telefono,fax,telefonoMovil,email,observacion,latitud,longitud,activo,empresa.id,pais.id,ciudad.id,departamento.id,barrio.id".split(","));

            DepartamentosSucursal ejDepSuc = new DepartamentosSucursal();
            ejDepSuc.setSucursal(new Sucursales(id));
            ejDepSuc.setActivo("S");

            List<Map<String, Object>> listDep = departamentosSucursalManager.listAtributos(ejDepSuc, "id,alias,nombreArea,descripcionArea,activo".split(","));

            Empresas empresa = new Empresas();
            empresa.setId(Long.parseLong(model.get("id").toString()));

            model.put("empresa", empresa);
            model.remove("empresa.id");
            model.put("departamentos", listDep);

            Map<String, Object> pais = paisesManager.getAtributos(new Paises(Long.parseLong(model.get("pais.id") == null ? "0" : model.get("pais.id").toString())), "id,nombre,activo".split(","));
            if (pais != null) {
                model.put("pais", pais);
            }
            model.remove("pais.id");

            Map<String, Object> departamento = departamentosPaisManager.getAtributos(new DepartamentosPais(Long.parseLong(model.get("departamento.id") == null ? "0" : model.get("departamento.id").toString())), "id,nombre,activo".split(","));
            if (departamento != null) {
                model.put("departamento", departamento);
            }
            model.remove("departamento.id");

            Map<String, Object> ciudad = ciudadesManager.getAtributos(new Ciudades(Long.parseLong(model.get("ciudad.id") == null ? "0" : model.get("ciudad.id").toString())), "id,nombre,activo".split(","));
            if (ciudad != null) {
                model.put("ciudad", ciudad);
            }
            model.remove("ciudad.id");

            Map<String, Object> barrio = barriosManager.getAtributos(new Barrios(Long.parseLong(model.get("barrio.id") == null ? "0" : model.get("barrio.id").toString())), "id,nombre,activo".split(","));
            if (barrio != null) {
                model.put("barrio", barrio);
            }           
            model.remove("barrio.id");

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
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody
    ResponseDTO create(
            @RequestBody @Valid Sucursales model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
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
            sucursal.setActivo("S");
            sucursal.setEmpresa(new Empresas(userDetail.getIdEmpresa()));

            Map<String, Object> sucursalMaps = sucursalManager.getLike(sucursal, "nombre".split(","));
            if (sucursalMaps != null) {
                response.setStatus(205);
                response.setMessage("Ya existe una sucursal con el mismo nombre.");
                return response;
            }

            String[] codigo = model.getNombre().split(" ");
            String codigoNombre = "";
            for (int i = 0; i < codigo.length; i++) {
                codigoNombre = codigoNombre + codigo[i].substring(0, 1);
            }
            sucursal = new Sucursales();
            sucursal.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            //Numero Sucursal
            Integer numeroSucursal = sucursalManager.total(sucursal) + 1;
            //Cantidad Sucursales
            Integer cantidadSucursal = sucursalManager.total(new Sucursales()) + 1;

            model.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            model.setCodigoSucursal(codigoNombre + "-" + numeroSucursal + "-" + cantidadSucursal);
            model.setActivo("S");
            model.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.setIdUsuarioCreacion(userDetail.getId());
            model.setIdUsuarioModificacion(userDetail.getId());

            sucursalManager.save(model);

            sucursal = new Sucursales();
            sucursal.setCodigoSucursal(model.getCodigoSucursal());

            sucursal = sucursalManager.get(sucursal);

            for (DepartamentosSucursal rpm : model.getDepartamentos()) {
                rpm.setActivo("S");
                rpm.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioCreacion(userDetail.getId());
                rpm.setIdUsuarioModificacion(userDetail.getId());
                rpm.setSucursal(sucursal);
                rpm.setNombreArea(rpm.getNombreArea().toUpperCase());
                rpm.setAlias(rpm.getAlias().toUpperCase());

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
     * Mapping para el metodo PUT de la vista actualizar.(actualizar Empresa)
     *
     * @param id de la entidad
     * @param model entidad Usuario recibida de la vista
     * @param errors
     * @return
     */
    @PutMapping("/{id}")
    //@CrossOrigin(origins = "http://localhost:4599")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public @ResponseBody
    ResponseDTO update(
            @ModelAttribute("id") Long id,
            @RequestBody @Valid Sucursales model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
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

            Sucursales dato = sucursalManager.get(id);

            Sucursales sucursal = new Sucursales();
            sucursal.setActivo("S");
            sucursal.setNombre(model.getNombre());
            sucursal.setEmpresa(dato.getEmpresa());

            Map<String, Object> sucursalMaps = sucursalManager.getLike(sucursal, "id,nombre".split(","));
            if (sucursalMaps != null
                    && sucursalMaps.get("id").toString().compareToIgnoreCase(dato.getId().toString()) != 0) {
                response.setStatus(205);
                response.setMessage("Ya existe una sucursal con el mismo nombre.");
                return response;
            }

            model.setCodigoSucursal(dato.getCodigoSucursal());
            model.setEmpresa(dato.getEmpresa());
            model.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            model.setIdUsuarioModificacion(userDetail.getId());
            model.setFechaCreacion(dato.getFechaCreacion());
            model.setIdUsuarioCreacion(dato.getIdUsuarioCreacion());
            model.setIdUsuarioEliminacion(dato.getIdUsuarioEliminacion());

            sucursalManager.update(model);

            for (DepartamentosSucursal rpm : model.getDepartamentos() == null ? new ArrayList<DepartamentosSucursal>() : model.getDepartamentos()) {
                rpm.setActivo("S");
                rpm.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                rpm.setIdUsuarioModificacion(userDetail.getId());
                rpm.setSucursal(new Sucursales(id));
                rpm.setNombreArea(rpm.getNombreArea().toUpperCase());
                rpm.setAlias(rpm.getAlias().toUpperCase());
                if (rpm.getId() != null) {
                    departamentosSucursalManager.update(rpm);
                } else {
                    departamentosSucursalManager.save(rpm);
                }
            }
            response.setModel(sucursalManager.getSucursal(new Sucursales(id)));
            response.setStatus(200);
            response.setMessage("La sucursal ha sido guardada");
        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }

    /**
     * Mapping para el metodo DELETE de la vista.(eliminar Sucursal)
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
            inicializarSucursalManager();

            Sucursales model = sucursalManager.get(id);
            model.setActivo("N");
            model.setIdUsuarioEliminacion(userDetail.getId());
            model.setFechaEliminacion(new Timestamp(System.currentTimeMillis()));

            sucursalManager.update(model);

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
