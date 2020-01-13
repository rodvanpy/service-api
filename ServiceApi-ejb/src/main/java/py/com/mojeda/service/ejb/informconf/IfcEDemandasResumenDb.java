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

public class IfcEDemandasResumenDb{

    private Long id;
 
    private String ruc;

    private BigDecimal cantTotalDemandas;

    private BigDecimal cantDemandasNoFiniquitadas;

    private BigDecimal cantDemandasSiFiniquitadas;

    private String menorFechaDemandaSiFiniqui;

    private String mayorFechaDemandaSiFiniqui;

    private String menorFechaDemandaNoFiniqui;
  
    private String mayorFechaDemandaNoFiniqui;

    private String lote;

    public IfcEDemandasResumenDb() {
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
    
    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
    
}
