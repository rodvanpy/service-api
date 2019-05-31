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
@Table(name = "FUNCIONARIOS", uniqueConstraints = @UniqueConstraint(name = "usuario_alias_uq", columnNames = { "alias" }) )
public class Funcionarios extends Base{
    
    private static final long serialVersionUID = 8538760347986185608L;
    private static final String SECUENCIA = "seq_funcionario_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @NotEmpty(message = "Ingrese Nro. Legajo")
    @Column(name = "NRO_LEGAJO")
    private String nroLegajo;

    @NotNull(message = "Ingrese Salario")
    @Column(name = "SALARIO")
    private Double salario;
    
    @Column(name = "COMISION")
    private Double comision; 
    
    @NotNull(message = "Ingrese CARGO")
    @Column(name = "CARGO")
    private String cargo;
        
    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    @Valid
    private Personas persona;   
    
    @Transient
    private List<Map<String,Object>> departamentos;
        
    
    public Funcionarios() {

    }

    /**
     * @param id
     *            el id de Usuario
     */
    public Funcionarios(Long id) {
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

    
    
           
}
