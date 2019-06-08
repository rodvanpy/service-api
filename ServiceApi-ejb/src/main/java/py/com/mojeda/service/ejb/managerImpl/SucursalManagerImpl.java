/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Barrios;
import py.com.mojeda.service.ejb.entity.Ciudades;
import py.com.mojeda.service.ejb.entity.DepartamentosPais;
import py.com.mojeda.service.ejb.entity.DepartamentosSucursal;
import py.com.mojeda.service.ejb.entity.Paises;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.manager.BarriosManager;
import py.com.mojeda.service.ejb.manager.CiudadesManager;
import py.com.mojeda.service.ejb.manager.DepartamentosPaisManager;
import py.com.mojeda.service.ejb.manager.DepartamentosSucursalManager;
import py.com.mojeda.service.ejb.manager.PaisesManager;
import py.com.mojeda.service.ejb.manager.SucursalManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class SucursalManagerImpl extends GenericDaoImpl<Sucursales, Long>
        implements SucursalManager {

    @Override
    protected Class<Sucursales> getEntityBeanType() {
        return Sucursales.class;
    }
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/PaisesManagerImpl")
    private PaisesManager paisesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/DepartamentosPaisManagerImpl")
    private DepartamentosPaisManager departamentosPaisManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/CiudadesManagerImpl")
    private CiudadesManager ciudadesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/BarriosManagerImpl")
    private BarriosManager barriosManager;
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/DepartamentosSucursalManagerImpl")
    private DepartamentosSucursalManager departamentosSucursalManager;

    @Override
    public Map<String, Object> guardar(Sucursales sucursal) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Object> editar(Sucursales sucursal) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Object> getSucursal(Sucursales sucursal) throws Exception {
        String atributos = "id,nombre,codigoSucursal,descripcion,direccion,telefono,fax,telefonoMovil,email,"
            + "observacion,activo,longitud,latitud,pais.id,departamento.id,ciudad.id,barrio.id";

        Map<String, Object> sucursalMap = this.getAtributos(sucursal, atributos.split(","));
        if (sucursalMap != null) {
            Map<String, Object> pais = paisesManager.getAtributos(new Paises(Long.parseLong(sucursalMap.get("pais.id") == null ? "0" : sucursalMap.get("pais.id").toString())), "id,nombre,activo".split(","));
            sucursalMap.put("pais", pais);
            sucursalMap.remove("pais.id");

            Map<String, Object> departamento = departamentosPaisManager.getAtributos(new DepartamentosPais(Long.parseLong(sucursalMap.get("departamento.id") == null ? "0" : sucursalMap.get("departamento.id").toString())), "id,nombre,activo".split(","));
            sucursalMap.put("departamento", departamento);
            sucursalMap.remove("departamento.id");

            Map<String, Object> ciudad = ciudadesManager.getAtributos(new Ciudades(Long.parseLong(sucursalMap.get("ciudad.id") == null ? "0" : sucursalMap.get("ciudad.id").toString())), "id,nombre,activo".split(","));
            sucursalMap.put("ciudad", ciudad);
            sucursalMap.remove("ciudad.id");

            Map<String, Object> barrio = barriosManager.getAtributos(new Barrios(Long.parseLong(sucursalMap.get("barrio.id") == null ? "0" : sucursalMap.get("barrio.id").toString())), "id,nombre,activo".split(","));
            sucursalMap.put("barrio", barrio);
            sucursalMap.remove("barrio.id");
            
            
            DepartamentosSucursal ejDepSuc = new DepartamentosSucursal();
            ejDepSuc.setSucursal(new Sucursales(Long.parseLong(sucursalMap.get("id").toString())));
            ejDepSuc.setActivo("S");
            
            List<Map<String, Object>> listDep = departamentosSucursalManager.listAtributos(ejDepSuc, "id,alias,nombreArea,descripcionArea,activo".split(","));
            
            sucursalMap.put("departamentos",listDep);
        }
        return sucursalMap;
    }
}
