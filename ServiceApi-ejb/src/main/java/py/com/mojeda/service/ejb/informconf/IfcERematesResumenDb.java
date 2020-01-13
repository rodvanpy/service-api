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

public class IfcERematesResumenDb {

    private Long id;

    private String ruc;

    private BigDecimal cantTotalRemates;

    private BigDecimal cantRematesNoFiniquitadas;

    private BigDecimal cantRematesSiFiniquitadas;

    private String menorFechaRemate;

    private String mayorFechaRemate;

    private String lote;

    public IfcERematesResumenDb() {
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

    
    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
}
