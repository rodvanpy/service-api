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

public class IfcEConvocatoriasResumenDb {

    private Long id;

    private String ruc;

    private BigDecimal cantTotalConvocatorias;

    private BigDecimal cantConvocatoriasNoFiniquit;

    private BigDecimal cantConvocatoriasSiFiniquit;

    private String menorFechaAdmision;

    private String mayorFechaAdmision;

    private String lote;


    public IfcEConvocatoriasResumenDb() {
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
    
    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
    
}
