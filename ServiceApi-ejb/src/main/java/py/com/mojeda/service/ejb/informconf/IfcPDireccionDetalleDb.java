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

public class IfcPDireccionDetalleDb {

    private Long id;

    private Date fechaActualizacion;

    private String cedulaIdentidad;

    private String calle;

    private String ciuda;

    private String telefono;

    private String barrio;

    private String fechaPrimeraReferencia;

    private String fechaUltimaReferencia;

    private String lote;
    
    public IfcPDireccionDetalleDb() {
    }

    public IfcPDireccionDetalleDb(Long id) {
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

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiuda() {
        return ciuda;
    }

    public void setCiuda(String ciuda) {
        this.ciuda = ciuda;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
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
        if (!(object instanceof IfcPDireccionDetalleDb)) {
            return false;
        }
        IfcPDireccionDetalleDb other = (IfcPDireccionDetalleDb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.IfcPDireccionDetalleDb[ id=" + id + " ]";
    }
    
}
