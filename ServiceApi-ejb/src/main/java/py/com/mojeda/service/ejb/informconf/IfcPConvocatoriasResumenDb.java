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
public class IfcPConvocatoriasResumenDb {

    private Long id;

    private Date fechaActualizacion;

    private String cedulaIdentidad;

    private BigDecimal cantTotalConvocatorias;

    private BigDecimal cantConvocatoriasNoFiniquit;

    private BigDecimal cantConvocatoriasSiFiniquit;

    private String menorFechaAdmision;

    private String mayorFechaAdmision;

    private String lote;
    

    public IfcPConvocatoriasResumenDb() {
    }

    public IfcPConvocatoriasResumenDb(Long id) {
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

    public BigDecimal getCantTotalConvocatorias() {
        return cantTotalConvocatorias;
    }

    public void setCantTotalConvocatorias(BigDecimal cantTotalConvocatorias) {
        this.cantTotalConvocatorias = cantTotalConvocatorias;
    }

    public BigDecimal getCantConvocatoriasNoFiniquit() {
        return cantConvocatoriasNoFiniquit;
    }

    public void setCantConvocatoriasNoFiniquit(BigDecimal cantConvocatoriasNoFiniquit) {
        this.cantConvocatoriasNoFiniquit = cantConvocatoriasNoFiniquit;
    }

    public BigDecimal getCantConvocatoriasSiFiniquit() {
        return cantConvocatoriasSiFiniquit;
    }

    public void setCantConvocatoriasSiFiniquit(BigDecimal cantConvocatoriasSiFiniquit) {
        this.cantConvocatoriasSiFiniquit = cantConvocatoriasSiFiniquit;
    }

    public String getMenorFechaAdmision() {
        return menorFechaAdmision;
    }

    public void setMenorFechaAdmision(String menorFechaAdmision) {
        this.menorFechaAdmision = menorFechaAdmision;
    }

    public String getMayorFechaAdmision() {
        return mayorFechaAdmision;
    }

    public void setMayorFechaAdmision(String mayorFechaAdmision) {
        this.mayorFechaAdmision = mayorFechaAdmision;
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
        if (!(object instanceof IfcPConvocatoriasResumenDb)) {
            return false;
        }
        IfcPConvocatoriasResumenDb other = (IfcPConvocatoriasResumenDb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.IfcPConvocatoriasResumenDb[ id=" + id + " ]";
    }
    
}
