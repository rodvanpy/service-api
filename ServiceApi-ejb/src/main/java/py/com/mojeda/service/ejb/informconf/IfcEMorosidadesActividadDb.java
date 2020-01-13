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
public class IfcEMorosidadesActividadDb {

    private Long id;

    private String ruc;

    private String actividadAfiliado;

    private BigDecimal cantTotalMorosidades;

    private String menorFechaVencimientoPendie;

    private String mayorFechaVencimientoPendie;

    private BigDecimal sumaSaldoAcreedor;

    private String lote;

    public IfcEMorosidadesActividadDb() {
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

    public String getActividadAfiliado() {
        return actividadAfiliado;
    }

    public void setActividadAfiliado(String actividadAfiliado) {
        this.actividadAfiliado = actividadAfiliado;
    }

    public BigDecimal getCantTotalMorosidades() {
        return cantTotalMorosidades;
    }

    public void setCantTotalMorosidades(BigDecimal cantTotalMorosidades) {
        this.cantTotalMorosidades = cantTotalMorosidades;
    }

    public String getMenorFechaVencimientoPendie() {
        return menorFechaVencimientoPendie;
    }

    public void setMenorFechaVencimientoPendie(String menorFechaVencimientoPendie) {
        this.menorFechaVencimientoPendie = menorFechaVencimientoPendie;
    }

    public String getMayorFechaVencimientoPendie() {
        return mayorFechaVencimientoPendie;
    }

    public void setMayorFechaVencimientoPendie(String mayorFechaVencimientoPendie) {
        this.mayorFechaVencimientoPendie = mayorFechaVencimientoPendie;
    }

    public BigDecimal getSumaSaldoAcreedor() {
        return sumaSaldoAcreedor;
    }

    public void setSumaSaldoAcreedor(BigDecimal sumaSaldoAcreedor) {
        this.sumaSaldoAcreedor = sumaSaldoAcreedor;
    }
    
    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    
}
