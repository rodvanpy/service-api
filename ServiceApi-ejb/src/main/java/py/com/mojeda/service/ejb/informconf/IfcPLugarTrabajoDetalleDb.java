/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.informconf;

import java.util.Date;


/**
 *
 * @author miguel.ojeda
 */

public class IfcPLugarTrabajoDetalleDb {

    private Long id;

    private Date fechaActualizacion;

    private String cedulaIdentidad;

    private String lugarTrabajo;

    private String cargo;

    private String telefono;

    private String fechaPrimeraReferencia;

    private String fechaUltimaReferencia;

    private String lote;
    
    public IfcPLugarTrabajoDetalleDb() {
    }

    public IfcPLugarTrabajoDetalleDb(Long id) {
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

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaPrimeraReferencia() {
        return fechaPrimeraReferencia;
    }

    public void setFechaPrimeraReferencia(String fechaPrimeraReferencia) {
        this.fechaPrimeraReferencia = fechaPrimeraReferencia;
    }

    public String getFechaUltimaReferencia() {
        return fechaUltimaReferencia;
    }

    public void setFechaUltimaReferencia(String fechaUltimaReferencia) {
        this.fechaUltimaReferencia = fechaUltimaReferencia;
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
        if (!(object instanceof IfcPLugarTrabajoDetalleDb)) {
            return false;
        }
        IfcPLugarTrabajoDetalleDb other = (IfcPLugarTrabajoDetalleDb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.IfcPLugarTrabajoDetalleDb[ id=" + id + " ]";
    }
    
}
