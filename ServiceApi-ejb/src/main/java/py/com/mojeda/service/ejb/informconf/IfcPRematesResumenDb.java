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

public class IfcPRematesResumenDb {

    private Long id;

    private Date fechaActualizacion;

    private String cedulaIdentidad;

    private BigDecimal cantTotalRemates;

    private BigDecimal cantRematesNoFiniquitadas;

    private BigDecimal cantRematesSiFiniquitadas;

    private String menorFechaRemate;

    private String mayorFechaRemate;

    private String lote;
    
    public IfcPRematesResumenDb() {
    }

    public IfcPRematesResumenDb(Long id) {
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

    public BigDecimal getCantTotalRemates() {
        return cantTotalRemates;
    }

    public void setCantTotalRemates(BigDecimal cantTotalRemates) {
        this.cantTotalRemates = cantTotalRemates;
    }

    public BigDecimal getCantRematesNoFiniquitadas() {
        return cantRematesNoFiniquitadas;
    }

    public void setCantRematesNoFiniquitadas(BigDecimal cantRematesNoFiniquitadas) {
        this.cantRematesNoFiniquitadas = cantRematesNoFiniquitadas;
    }

    public BigDecimal getCantRematesSiFiniquitadas() {
        return cantRematesSiFiniquitadas;
    }

    public void setCantRematesSiFiniquitadas(BigDecimal cantRematesSiFiniquitadas) {
        this.cantRematesSiFiniquitadas = cantRematesSiFiniquitadas;
    }

    public String getMenorFechaRemate() {
        return menorFechaRemate;
    }

    public void setMenorFechaRemate(String menorFechaRemate) {
        this.menorFechaRemate = menorFechaRemate;
    }

    public String getMayorFechaRemate() {
        return mayorFechaRemate;
    }

    public void setMayorFechaRemate(String mayorFechaRemate) {
        this.mayorFechaRemate = mayorFechaRemate;
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
        if (!(object instanceof IfcPRematesResumenDb)) {
            return false;
        }
        IfcPRematesResumenDb other = (IfcPRematesResumenDb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.IfcPRematesResumenDb[ id=" + id + " ]";
    }
    
}
