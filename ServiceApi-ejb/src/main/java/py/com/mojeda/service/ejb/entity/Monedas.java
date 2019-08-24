/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

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
@Table(name = "MONEDAS")
public class Monedas extends Base{

    private static final long serialVersionUID = 1L;
    private static final String SECUENCIA = "seq_monedas_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "DECIMALES")
    private short decimales;
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "ENTIDAD")
    private String entidad = "MONEDAS";

    public Monedas() {
    }

    /**
     * @return the SECUENCIA
     */
    public static String getSECUENCIA() {
        return SECUENCIA;
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
     * @return the decimales
     */
    public short getDecimales() {
        return decimales;
    }

    /**
     * @param decimales the decimales to set
     */
    public void setDecimales(short decimales) {
        this.decimales = decimales;
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
     * @return the entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * @param entidad the entidad to set
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }
}
