/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author Miguel
 */
@Entity
public class RolPermiso{
    
    private static final long serialVersionUID = 7986185608L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "empresa", referencedColumnName = "id")
    private Empresas empresa;
   
    @ManyToOne(optional = false)
    @JoinColumn(name = "permiso", referencedColumnName = "id")
    private Permisos permiso; 
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "rol", referencedColumnName = "id")
    private Rol rol;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    /**
     * @return the empresa
     */
    public Empresas getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the permiso
     */
    public Permisos getPermiso() {
        return permiso;
    }

    /**
     * @param permiso the permiso to set
     */
    public void setPermiso(Permisos permiso) {
        this.permiso = permiso;
    }

    /**
     * @return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }   

    
}
