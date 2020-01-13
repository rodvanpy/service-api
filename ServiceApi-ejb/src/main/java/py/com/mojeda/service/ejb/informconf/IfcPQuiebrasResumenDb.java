/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.informconf;

import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author miguel.ojeda
 */
public class IfcPQuiebrasResumenDb {

    private Long id;

    private Date fechaActualizacion;

    private String cedulaIdentidad;

    private BigDecimal cantTotalQuiebras;

    private BigDecimal cantQuiebrasNoFiniquitadas;

    private BigDecimal cantQuiebrasSiFiniquitadas;

    private String menorFechaSolicitud;

    private String mayorFechaSolicitud;

    private String lote;

    public IfcPQuiebrasResumenDb() {
    }

    public IfcPQuiebrasResumenDb(Long id) {
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

    public BigDecimal getCantTotalQuiebras() {
        return cantTotalQuiebras;
    }

    public void setCantTotalQuiebras(BigDecimal cantTotalQuiebras) {
        this.cantTotalQuiebras = cantTotalQuiebras;
    }

    public BigDecimal getCantQuiebrasNoFiniquitadas() {
        return cantQuiebrasNoFiniquitadas;
    }

    public void setCantQuiebrasNoFiniquitadas(BigDecimal cantQuiebrasNoFiniquitadas) {
        this.cantQuiebrasNoFiniquitadas = cantQuiebrasNoFiniquitadas;
    }

    public BigDecimal getCantQuiebrasSiFiniquitadas() {
        return cantQuiebrasSiFiniquitadas;
    }

    public void setCantQuiebrasSiFiniquitadas(BigDecimal cantQuiebrasSiFiniquitadas) {
        this.cantQuiebrasSiFiniquitadas = cantQuiebrasSiFiniquitadas;
    }

    public String getMenorFechaSolicitud() {
        return menorFechaSolicitud;
    }

    public void setMenorFechaSolicitud(String menorFechaSolicitud) {
        this.menorFechaSolicitud = menorFechaSolicitud;
    }

    public String getMayorFechaSolicitud() {
        return mayorFechaSolicitud;
    }

    public void setMayorFechaSolicitud(String mayorFechaSolicitud) {
        this.mayorFechaSolicitud = mayorFechaSolicitud;
    }


    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IfcPQuiebrasResumenDb)) {
            return false;
        }
        IfcPQuiebrasResumenDb other = (IfcPQuiebrasResumenDb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.IfcPQuiebrasResumenDb[ id=" + id + " ]";
    }
    
}
