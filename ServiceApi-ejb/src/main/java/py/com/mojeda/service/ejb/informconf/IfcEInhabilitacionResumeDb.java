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
public class IfcEInhabilitacionResumeDb {

    private Long id;

    private String ruc;

    private BigDecimal cantInhabilitacionesCuentas;

    private String menorFechaInhabilitacion;

    private String mayorFechaInhabilitacion;

    private BigDecimal mayorPlazoInhabilitacion;

    private String lote;


    public IfcEInhabilitacionResumeDb() {
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

    public BigDecimal getCantInhabilitacionesCuentas() {
        return cantInhabilitacionesCuentas;
    }

    public void setCantInhabilitacionesCuentas(BigDecimal cantInhabilitacionesCuentas) {
        this.cantInhabilitacionesCuentas = cantInhabilitacionesCuentas;
    }

    public String getMenorFechaInhabilitacion() {
        return menorFechaInhabilitacion;
    }

    public void setMenorFechaInhabilitacion(String menorFechaInhabilitacion) {
        this.menorFechaInhabilitacion = menorFechaInhabilitacion;
    }

    public String getMayorFechaInhabilitacion() {
        return mayorFechaInhabilitacion;
    }

    public void setMayorFechaInhabilitacion(String mayorFechaInhabilitacion) {
        this.mayorFechaInhabilitacion = mayorFechaInhabilitacion;
    }

    public BigDecimal getMayorPlazoInhabilitacion() {
        return mayorPlazoInhabilitacion;
    }

    public void setMayorPlazoInhabilitacion(BigDecimal mayorPlazoInhabilitacion) {
        this.mayorPlazoInhabilitacion = mayorPlazoInhabilitacion;
    }
    
    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    
}
