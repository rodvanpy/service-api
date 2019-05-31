/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.entity;

import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "CLIENTES", uniqueConstraints = @UniqueConstraint(name = "clientes_persona_uq", columnNames = { "ID_PERSONA" }) )
public class Clientes extends Base{
    
    private static final long serialVersionUID = 8538760347986185608L;
    private static final String SECUENCIA = "seq_cliente_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
        
    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    @Valid
    private Personas persona;

    
    public Clientes() {

    }

    /**
     * @param id
     *            el id de Usuario
     */
    public Clientes(Long id) {
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
     * @return the persona
     */
    public Personas getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Personas persona) {
        this.persona = persona;
    }  
    
    public String getClassName() {
        return this.getClass().getSimpleName();
    }
    
           
}
