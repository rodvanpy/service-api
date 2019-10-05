/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "SITUACIONES_CREDITO")
public class SituacionesCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SECUENCIA = "seq_situacion_credito_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "DIAS")
    private Short dias;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PORCENTAJE_PREVISIONES")
    private BigDecimal porcentajePrevisiones;
    
    @Column(name = "PRIORIDAD_COBRO")
    private String prioridadCobro;

    public SituacionesCredito() {
    }

    public SituacionesCredito(Long id) {
        this.id = id;
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
     * @return the dias
     */
    public Short getDias() {
        return dias;
    }

    /**
     * @param dias the dias to set
     */
    public void setDias(Short dias) {
        this.dias = dias;
    }

    /**
     * @return the porcentajePrevisiones
     */
    public BigDecimal getPorcentajePrevisiones() {
        return porcentajePrevisiones;
    }

    /**
     * @param porcentajePrevisiones the porcentajePrevisiones to set
     */
    public void setPorcentajePrevisiones(BigDecimal porcentajePrevisiones) {
        this.porcentajePrevisiones = porcentajePrevisiones;
    }

    /**
     * @return the prioridadCobro
     */
    public String getPrioridadCobro() {
        return prioridadCobro;
    }

    /**
     * @param prioridadCobro the prioridadCobro to set
     */
    public void setPrioridadCobro(String prioridadCobro) {
        this.prioridadCobro = prioridadCobro;
    }

    
}
