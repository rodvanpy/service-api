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

public class IfcPControladosDb {

    private Long id;   

    private Date fechaActualizacion;

    private String cedulaIdentidad;

    private String documento;

    private String fechaInicioControl;

    private String fechaFinalControl;

    private String lote;

    public IfcPControladosDb() {
    }

    public IfcPControladosDb(Long id) {
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFechaInicioControl() {
        return fechaInicioControl;
    }

    public void setFechaInicioControl(String fechaInicioControl) {
        this.fechaInicioControl = fechaInicioControl;
    }

    public String getFechaFinalControl() {
        return fechaFinalControl;
    }

    public void setFechaFinalControl(String fechaFinalControl) {
        this.fechaFinalControl = fechaFinalControl;
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
        if (!(object instanceof IfcPControladosDb)) {
            return false;
        }
        IfcPControladosDb other = (IfcPControladosDb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.IfcPControladosDb[ id=" + id + " ]";
    }
    
}
