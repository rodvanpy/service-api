/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.informconf;

import java.math.BigDecimal;


/**
 *
 * @author miguel.ojeda
 */
public class IfcEInhibicionesResumenDb {

    private Long id;

    private String ruc;
 
    private BigDecimal cantTotalInhibiciones;

    private BigDecimal cantInhibicionesNoFiniquita;

    private BigDecimal cantInhibicionesSiFiniquita;

    private String menorFechaDemandaSiFiniqui;

    private String mayorFechaDemandaSiFiniqui;

    private String menorFechaDemandaNoFiniqui;

    private String mayorFechaDemandaNoFiniqui;

    private String lote;

    public IfcEInhibicionesResumenDb() {
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

    public BigDecimal getCantTotalInhibiciones() {
        return cantTotalInhibiciones;
    }

    public void setCantTotalInhibiciones(BigDecimal cantTotalInhibiciones) {
        this.cantTotalInhibiciones = cantTotalInhibiciones;
    }

    public BigDecimal getCantInhibicionesNoFiniquita() {
        return cantInhibicionesNoFiniquita;
    }

    public void setCantInhibicionesNoFiniquita(BigDecimal cantInhibicionesNoFiniquita) {
        this.cantInhibicionesNoFiniquita = cantInhibicionesNoFiniquita;
    }

    public BigDecimal getCantInhibicionesSiFiniquita() {
        return cantInhibicionesSiFiniquita;
    }

    public void setCantInhibicionesSiFiniquita(BigDecimal cantInhibicionesSiFiniquita) {
        this.cantInhibicionesSiFiniquita = cantInhibicionesSiFiniquita;
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
