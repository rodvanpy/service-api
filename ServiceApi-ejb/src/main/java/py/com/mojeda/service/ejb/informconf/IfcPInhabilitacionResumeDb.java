/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.informconf;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author miguel.ojeda
 */

public class IfcPInhabilitacionResumeDb{

    private Long id;

    private Date fechaActualizacion;

    private String cedulaIdentidad;

    private BigDecimal cantInhabilitacionesCuentas;

    private String menorFechaInhabilitacion;

    private String mayorFechaInhabilitacion;

    private BigDecimal mayorPlazoInhabilitacion;

    private String lote;

    public IfcPInhabilitacionResumeDb() {
    }

    public IfcPInhabilitacionResumeDb(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedulaIdentidad() {
        return cedulaIdentidad;
    }

    public void setCedulaIdentidad(String cedulaIdentidad) {
        this.cedulaIdentidad = cedulaIdentidad;
    }

    public BigDecimal getCantInhabilitacionesCuentas() {
        return cantInhabilitacionesCuentas;
    }

    public void setCantInhabilitacionesCuentas(BigDecimal cantInhabilitacionesCuentas) {
        this.cantInhabilitacionesCuentas = cantInhabilitacionesCuentas;
    }

    public String getMenorFechaInhabilitacion() {
        return menorFechaInhabilitacion;
    }

    public void setMenorFechaInhabilitacion(String menorFechaInhabilitacion) {
        this.menorFechaInhabilitacion = menorFechaInhabilitacion;
    }

    public String getMayorFechaInhabilitacion() {
        return mayorFechaInhabilitacion;
    }

    public void setMayorFechaInhabilitacion(String mayorFechaInhabilitacion) {
        this.mayorFechaInhabilitacion = mayorFechaInhabilitacion;
    }

    public BigDecimal getMayorPlazoInhabilitacion() {
        return mayorPlazoInhabilitacion;
    }

    public void setMayorPlazoInhabilitacion(BigDecimal mayorPlazoInhabilitacion) {
        this.mayorPlazoInhabilitacion = mayorPlazoInhabilitacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IfcPInhabilitacionResumeDb)) {
            return false;
        }
        IfcPInhabilitacionResumeDb other = (IfcPInhabilitacionResumeDb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.IfcPInhabilitacionResumeDb[ id=" + id + " ]";
    }
    
}
