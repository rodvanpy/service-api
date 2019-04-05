/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Miguel
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "departamento_alias_uq", columnNames = { "alias" }) )
public class Departamento extends Base{
    
    private static final long serialVersionUID = 8538760347986185608L;
    private static final String SECUENCIA = "seq_departamento_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    //@NotNull(message = "Ingrese Alias")
    @NotEmpty(message = "Ingrese Alias")
    @Column(name = "ALIAS")
    private String alias;

    //@NotNull(message = " Ingrese Clave Acceso")
    @NotEmpty(message = "Ingrese Nombre")
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "DESCRIPCION")
    private String descripcion; 
    
    @NotNull(message = "Ingrese Sucursal")
    @ManyToOne
    @JoinColumn(name = "ID_SUCURSAL", referencedColumnName = "id")
    private Sucursal sucursal;
    
    public Departamento() {

    }

    /**
     * @param id
     *            el id de Usuario
     */
    public Departamento(Long id) {
            this.setId(id);
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the sucursal
     */
    public Sucursal getSucursal() {
        return sucursal;
    }

    /**
     * @param sucursal the sucursal to set
     */
    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    
           
}
