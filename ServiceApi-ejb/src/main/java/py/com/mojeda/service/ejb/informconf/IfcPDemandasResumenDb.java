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
public class IfcPDemandasResumenDb {

    private Long id;

    private Date fechaActualizacion;

    private String cedulaIdentidad;

    private BigDecimal cantTotalDemandas;

    private BigDecimal cantDemandasNoFiniquitadas;

    private BigDecimal cantDemandasSiFiniquitadas;

    private String menorFechaDemandaSiFiniqui;

    private String mayorFechaDemandaSiFiniqui;

    private String menorFechaDemandaNoFiniqui;

    private String mayorFechaDemandaNoFiniqui;

    private String lote;
    
    public IfcPDemandasResumenDb() {
    }

    public IfcPDemandasResumenDb(Long id) {
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

    public BigDecimal getCantTotalDemandas() {
        return cantTotalDemandas;
    }

    public void setCantTotalDemandas(BigDecimal cantTotalDemandas) {
        this.cantTotalDemandas = cantTotalDemandas;
    }

    public BigDecimal getCantDemandasNoFiniquitadas() {
        return cantDemandasNoFiniquitadas;
    }

    public void setCantDemandasNoFiniquitadas(BigDecimal cantDemandasNoFiniquitadas) {
        this.cantDemandasNoFiniquitadas = cantDemandasNoFiniquitadas;
    }

    public BigDecimal getCantDemandasSiFiniquitadas() {
        return cantDemandasSiFiniquitadas;
    }

    public void setCantDemandasSiFiniquitadas(BigDecimal cantDemandasSiFiniquitadas) {
        this.cantDemandasSiFiniquitadas = cantDemandasSiFiniquitadas;
    }

    public String getMenorFechaDemandaSiFiniqui() {
        return menorFechaDemandaSiFiniqui;
    }

    public void setMenorFechaDemandaSiFiniqui(String menorFechaDemandaSiFiniqui) {
        this.menorFechaDemandaSiFiniqui = menorFechaDemandaSiFiniqui;
    }

    public String getMayorFechaDemandaSiFiniqui() {
        return mayorFechaDemandaSiFiniqui;
    }

    public void setMayorFechaDemandaSiFiniqui(String mayorFechaDemandaSiFiniqui) {
        this.mayorFechaDemandaSiFiniqui = mayorFechaDemandaSiFiniqui;
    }

    public String getMenorFechaDemandaNoFiniqui() {
        return menorFechaDemandaNoFiniqui;
    }

    public void setMenorFechaDemandaNoFiniqui(String menorFechaDemandaNoFiniqui) {
        this.menorFechaDemandaNoFiniqui = menorFechaDemandaNoFiniqui;
    }

    public String getMayorFechaDemandaNoFiniqui() {
        return mayorFechaDemandaNoFiniqui;
    }

    public void setMayorFechaDemandaNoFiniqui(String mayorFechaDemandaNoFiniqui) {
        this.mayorFechaDemandaNoFiniqui = mayorFechaDemandaNoFiniqui;
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
        if (!(object instanceof IfcPDemandasResumenDb)) {
            return false;
        }
        IfcPDemandasResumenDb other = (IfcPDemandasResumenDb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.IfcPDemandasResumenDb[ id=" + id + " ]";
    }
    
}
