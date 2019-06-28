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
    @JoinColumn(name = "ID_SUCURSAL_ALTA", referencedColumnName = "id")
    private Sucursales sucursal;
        
    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    @Valid
    private Personas persona;
    
    @Transient
    private List<Bienes> bienesInmuebles;
    
    @Transient
    private List<Bienes> bienesVehiculo;
    
    @Transient
    private List<Referencias> referencias;
    
    @Transient
    private List<IngresosEgresos> egresos;
    
    @Transient
    private List<IngresosEgresos> ingresos;
    
    @Transient
    private List<OcupacionPersona> ocupaciones;
    
    @Transient
    private List<Vinculos> vinculos;

    
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
     * @param personas
     *            el id de Usuario
     */
    public Clientes(Personas personas) {
            this.setPersona(personas);
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
     * @return the bienesInmuebles
     */
    public List<Bienes> getBienesInmuebles() {
        return bienesInmuebles;
    }

    /**
     * @param bienesInmuebles the bienesInmuebles to set
     */
    public void setBienesInmuebles(List<Bienes> bienesInmuebles) {
        this.bienesInmuebles = bienesInmuebles;
    }

    /**
     * @return the bienesVehiculo
     */
    public List<Bienes> getBienesVehiculo() {
        return bienesVehiculo;
    }

    /**
     * @param bienesVehiculo the bienesVehiculo to set
     */
    public void setBienesVehiculo(List<Bienes> bienesVehiculo) {
        this.bienesVehiculo = bienesVehiculo;
    }

    /**
     * @return the referencias
     */
    public List<Referencias> getReferencias() {
        return referencias;
    }

    /**
     * @param referencias the referencias to set
     */
    public void setReferencias(List<Referencias> referencias) {
        this.referencias = referencias;
    }

    /**
     * @return the egresos
     */
    public List<IngresosEgresos> getEgresos() {
        return egresos;
    }

    /**
     * @param egresos the egresos to set
     */
    public void setEgresos(List<IngresosEgresos> egresos) {
        this.egresos = egresos;
    }

    /**
     * @return the ingresos
     */
    public List<IngresosEgresos> getIngresos() {
        return ingresos;
    }

    /**
     * @param ingresos the ingresos to set
     */
    public void setIngresos(List<IngresosEgresos> ingresos) {
        this.ingresos = ingresos;
    }

    /**
     * @return the ocupaciones
     */
    public List<OcupacionPersona> getOcupaciones() {
        return ocupaciones;
    }

    /**
     * @param ocupaciones the ocupaciones to set
     */
    public void setOcupaciones(List<OcupacionPersona> ocupaciones) {
        this.ocupaciones = ocupaciones;
    }

    public List<Vinculos> getVinculos() {
        return vinculos;
    }

    public void setVinculos(List<Vinculos> vinculos) {
        this.vinculos = vinculos;
    }
        
           
}
