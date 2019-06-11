/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Permisos;
import py.com.mojeda.service.ejb.entity.Rol;
import py.com.mojeda.service.ejb.entity.RolPermiso;
import py.com.mojeda.service.ejb.manager.RolManager;
import py.com.mojeda.service.ejb.manager.RolPermisoManager;


/**
 *
 * @author Miguel
 */
@Stateless
public class RolManagerImpl extends GenericDaoImpl<Rol, Long>
        implements RolManager {

    @Override
    protected Class<Rol> getEntityBeanType() {
        return Rol.class;
    }
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/RolPermisoManagerImpl")
    private RolPermisoManager rolPermisoManager;

    @Override
    public Rol guardar(Rol rol) throws Exception {
        Rol object = null;
        if(rol != null){
            if(rol.getId() != null){
                object = this.get(new Rol(rol.getId()));
                object.setNombre(rol.getNombre().toUpperCase());            
                this.update(rol);
            }else{
                rol.setNombre(rol.getNombre().toUpperCase());            
                this.save(rol);
            }
                        
            object = new Rol();
            object = this.get(rol);
            
            RolPermiso rolPermiso = new RolPermiso();
            rolPermiso.setRol(new Rol(object.getId()));
            
            List<RolPermiso>  authoList = rolPermisoManager.list(rolPermiso);
            for(RolPermiso rpm : authoList) {
                rolPermisoManager.delete(rpm.getId());
            }
            
            if(object != null){
               for(Permisos rpm : rol.getAuthorities()) {
                    rolPermiso = new RolPermiso();
                    rolPermiso.setEmpresa(object.getEmpresa());
                    rolPermiso.setRol(new Rol(object.getId()));
                    rolPermiso.setPermiso(new Permisos(rpm.getId()));
                    
                    rolPermisoManager.save(rolPermiso);
               }              
            }
            
            object.setAuthorities(rol.getAuthorities());
        }
      
        return object;
    }

    @Override
    public Rol editar(Rol rol) throws Exception {
        Rol object = null;
        if(rol != null
                && rol.getId() != null){
            
            object = this.get(rol.getId());
            
            object.setNombre(rol.getNombre().toUpperCase());
            object.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            object.setIdUsuarioModificacion(rol.getIdUsuarioModificacion());
       
            this.update(object);
            
            RolPermiso rolPermiso = new RolPermiso();
            rolPermiso.setRol(new Rol(object.getId()));
            
            List<RolPermiso>  authoList = rolPermisoManager.list(rolPermiso);
            for(RolPermiso rpm : authoList) {
                rolPermisoManager.delete(rpm.getId());
            }
            
            if(object != null){
               for(Permisos rpm : rol.getAuthorities()) {
                    rolPermiso = new RolPermiso();
                    rolPermiso.setEmpresa(object.getEmpresa());
                    rolPermiso.setRol(new Rol(object.getId()));
                    rolPermiso.setPermiso(new Permisos(rpm.getId()));
                    
                    rolPermisoManager.save(rolPermiso);
               }              
            }
            
            object.setAuthorities(rol.getAuthorities());
        }
      
        return object;
    }
}
