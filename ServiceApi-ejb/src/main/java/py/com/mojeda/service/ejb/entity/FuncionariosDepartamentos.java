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
import javax.persistence.Table;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "FUNCIONARIO_DEPARTAMENTOS", schema = "PUBLIC")
public class FuncionariosDepartamentos{
    
    private static final long serialVersionUID = 7986185608L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "id")
    private DepartamentosSucursal departamento;
   
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_FUNCIONARIO", referencedColumnName = "id")
    private Funcionarios funcionario; 
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the departamento
     */
    public DepartamentosSucursal getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(DepartamentosSucursal departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the funcionario
     */
    public Funcionarios getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionarios funcionario) {
        this.funcionario = funcionario;
    }

    
    
    

    
}
