/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.informconf;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author miguel.ojeda
 */
@Embeddable
public class ControlCarteraInformconfPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "CEDULA_IDENTIDAD")
    private String cedulaIdentidad;

    public ControlCarteraInformconfPK() {
    }

    public ControlCarteraInformconfPK(Date fecha, String cedulaIdentidad) {
        this.fecha = fecha;
        this.cedulaIdentidad = cedulaIdentidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCedulaIdentidad() {
        return cedulaIdentidad;
    }

    public void setCedulaIdentidad(String cedulaIdentidad) {
        this.cedulaIdentidad = cedulaIdentidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (cedulaIdentidad != null ? cedulaIdentidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlCarteraInformconfPK)) {
            return false;
        }
        ControlCarteraInformconfPK other = (ControlCarteraInformconfPK) object;
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if ((this.cedulaIdentidad == null && other.cedulaIdentidad != null) || (this.cedulaIdentidad != null && !this.cedulaIdentidad.equals(other.cedulaIdentidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication4.ControlCarteraInformconfPK[ fecha=" + fecha + ", cedulaIdentidad=" + cedulaIdentidad + " ]";
    }
    
}
