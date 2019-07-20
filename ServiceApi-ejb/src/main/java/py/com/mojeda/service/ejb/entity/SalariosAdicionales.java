/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "SALARIOS_ADICIONALES")
public class SalariosAdicionales extends Base {

    private static final long serialVersionUID = 1574657L;
    private static final String SECUENCIA = "seq_salario_adicional_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "FECHA_INICIO")
    private Timestamp fechaInicio;
    
    @Column(name = "FECHA_FIN")
    private Timestamp fechaFin;
    
    @Column(name = "ADICIONAL_TITULO")
    private BigDecimal adicionalTitulo;
    
    @Column(name = "ADICIONAL_CARGO")
    private BigDecimal adicionalCargo;
    
    @Column(name = "IMPORTE_ADICIONAL")
    private BigDecimal importeCargo;
    
    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONARIO", referencedColumnName = "id")
    private Funcionarios usuario;
    
    public SalariosAdicionales() {
    }

    public SalariosAdicionales(Long id) {
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
     * @return the fechaInicio
     */
    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Timestamp getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the adicionalTitulo
     */
    public BigDecimal getAdicionalTitulo() {
        return adicionalTitulo;
    }

    /**
     * @param adicionalTitulo the adicionalTitulo to set
     */
    public void setAdicionalTitulo(BigDecimal adicionalTitulo) {
        this.adicionalTitulo = adicionalTitulo;
    }

    /**
     * @return the adicionalCargo
     */
    public BigDecimal getAdicionalCargo() {
        return adicionalCargo;
    }

    /**
     * @param adicionalCargo the adicionalCargo to set
     */
    public void setAdicionalCargo(BigDecimal adicionalCargo) {
        this.adicionalCargo = adicionalCargo;
    }

    /**
     * @return the importeCargo
     */
    public BigDecimal getImporteCargo() {
        return importeCargo;
    }

    /**
     * @param importeCargo the importeCargo to set
     */
    public void setImporteCargo(BigDecimal importeCargo) {
        this.importeCargo = importeCargo;
    }

    /**
     * @return the usuario
     */
    public Funcionarios getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Funcionarios usuario) {
        this.usuario = usuario;
    }

    

    
    
}
