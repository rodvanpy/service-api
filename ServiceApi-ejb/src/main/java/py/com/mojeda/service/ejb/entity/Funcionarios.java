/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.entity;

import java.sql.Timestamp;
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
@Table(name = "FUNCIONARIOS", uniqueConstraints = @UniqueConstraint(name = "funcionario_persona_uq", columnNames = { "ID_PERSONA" }) )
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
    
    @NotNull(message = "Ingrese Fecha Ingreso")
    @Column(name = "FECHA_INGRESO")
    private Timestamp fechaIngreso;
    
    @Column(name = "FECHA_EGRESO")
    private Timestamp fechaEgreso;

    @NotNull(message = "Ingrese Salario")
    @Column(name = "SALARIO")
    private Double salario;
    
    @Column(name = "COMISION")
    private Double comision; 
    
    @NotNull(message = "Ingrese Cargo")
    @ManyToOne
    @JoinColumn(name = "ID_CARGO", referencedColumnName = "id")
    @Valid
    private Cargos cargo;
    
    @NotNull(message = "Ingrese Tipo Funcionario")
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_FUNCIONARIO", referencedColumnName = "id")
    @Valid
    private TipoFuncionarios tipoFuncionario;
    
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_MOTI_RETIRO", referencedColumnName = "id")
    private TipoMotivosRetiro tipoMotivoRetiro;
        
    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    @Valid
    private Personas persona; 
    
    @ManyToOne
    @JoinColumn(name = "ID_SUCURSAL", referencedColumnName = "id")
    private Sucursales sucursal;
    
    @Transient
    private List<Map<String,Object>> departamentos;
    
    @Transient
    private List<Vinculos> vinculos;
        
    
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

    /**
     * @return the nroLegajo
     */
    public String getNroLegajo() {
        return nroLegajo;
    }

    /**
     * @param nroLegajo the nroLegajo to set
     */
    public void setNroLegajo(String nroLegajo) {
        this.nroLegajo = nroLegajo;
    }

    /**
     * @return the fechaIngreso
     */
    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the fechaEgreso
     */
    public Timestamp getFechaEgreso() {
        return fechaEgreso;
    }

    /**
     * @param fechaEgreso the fechaEgreso to set
     */
    public void setFechaEgreso(Timestamp fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    /**
     * @return the salario
     */
    public Double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(Double salario) {
        this.salario = salario;
    }

    /**
     * @return the comision
     */
    public Double getComision() {
        return comision;
    }

    /**
     * @param comision the comision to set
     */
    public void setComision(Double comision) {
        this.comision = comision;
    }

    /**
     * @return the cargo
     */
    public Cargos getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(Cargos cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the tipoFuncionario
     */
    public TipoFuncionarios getTipoFuncionario() {
        return tipoFuncionario;
    }

    /**
     * @param tipoFuncionario the tipoFuncionario to set
     */
    public void setTipoFuncionario(TipoFuncionarios tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    /**
     * @return the tipoMotivoRetiro
     */
    public TipoMotivosRetiro getTipoMotivoRetiro() {
        return tipoMotivoRetiro;
    }

    /**
     * @param tipoMotivoRetiro the tipoMotivoRetiro to set
     */
    public void setTipoMotivoRetiro(TipoMotivosRetiro tipoMotivoRetiro) {
        this.tipoMotivoRetiro = tipoMotivoRetiro;
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

    /**
     * @return the sucursal
     */
    public Sucursales getSucursal() {
        return sucursal;
    }

    /**
     * @param sucursal the sucursal to set
     */
    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * @return the departamentos
     */
    public List<Map<String,Object>> getDepartamentos() {
        return departamentos;
    }

    /**
     * @param departamentos the departamentos to set
     */
    public void setDepartamentos(List<Map<String,Object>> departamentos) {
        this.departamentos = departamentos;
    }

    /**
     * @return the vinculos
     */
    public List<Vinculos> getVinculos() {
        return vinculos;
    }

    /**
     * @param vinculos the vinculos to set
     */
    public void setVinculos(List<Vinculos> vinculos) {
        this.vinculos = vinculos;
    }

    
    
           
}
