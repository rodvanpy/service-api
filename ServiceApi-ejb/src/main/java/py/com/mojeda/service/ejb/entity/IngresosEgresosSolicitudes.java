/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import javax.persistence.Basic;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "INGRESOS_EGRESOS_SOLICITUDES", schema = "PUBLIC")
public class IngresosEgresosSolicitudes extends Base {

    private static final long serialVersionUID = 1654L;
    private static final String SECUENCIA = "seq_ingreso_egreso_sol_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "ID_INGRESOS_EGRESOS_PADRE")
    private Long idIngresoEgreso;
    
    @Basic(optional = false)
    @Column(name = "MONTO")
    private Double monto;

    
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Personas persona;
    
    @JoinColumn(name = "ID_PROPUESTA_SOLICITUD", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PropuestaSolicitud propuestaSolicitud;
    
    @JoinColumn(name = "ID_TIPO_INGRESO_EGRESO", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoIngresosEgresos tipoIngresosEgresos;

    public IngresosEgresosSolicitudes() {
    }

    public IngresosEgresosSolicitudes(Long id) {
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
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
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
     * @return the tipoIngresosEgresos
     */
    public TipoIngresosEgresos getTipoIngresosEgresos() {
        return tipoIngresosEgresos;
    }

    /**
     * @param tipoIngresosEgresos the tipoIngresosEgresos to set
     */
    public void setTipoIngresosEgresos(TipoIngresosEgresos tipoIngresosEgresos) {
        this.tipoIngresosEgresos = tipoIngresosEgresos;
    }

    /**
     * @return the propuestaSolicitud
     */
    public PropuestaSolicitud getPropuestaSolicitud() {
        return propuestaSolicitud;
    }

    /**
     * @param propuestaSolicitud the propuestaSolicitud to set
     */
    public void setPropuestaSolicitud(PropuestaSolicitud propuestaSolicitud) {
        this.propuestaSolicitud = propuestaSolicitud;
    }

    public Long getIdIngresoEgreso() {
        return idIngresoEgreso;
    }

    public void setIdIngresoEgreso(Long idIngresoEgreso) {
        this.idIngresoEgreso = idIngresoEgreso;
    }

    

}

