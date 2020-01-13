/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.informconf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author miguel.ojeda
 */
public class IfcEQuiebrasResumenDb {

    private Long id;

    private String ruc;

    private BigDecimal cantTotalQuiebras;

    private BigDecimal cantQuiebrasNoFiniquitadas;

    private BigDecimal cantQuiebrasSiFiniquitadas;

    private String menorFechaSolicitud;

    private String mayorFechaSolicitud;

    private String lote;

    public IfcEQuiebrasResumenDb() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
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

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
}
