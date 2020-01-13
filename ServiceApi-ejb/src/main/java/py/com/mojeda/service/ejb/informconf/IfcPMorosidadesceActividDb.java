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

public class IfcPMorosidadesceActividDb {

    private Long id;

    private Date fechaActualizacion;

    private String cedulaIdentidad;

    private String actividadAfiliado;

    private BigDecimal cantTotalMorosidades;

    private String menorFechaVencimientoPendie;

    private String mayorFechaVencimientoPendie;

    private BigDecimal sumaSaldoAcreedor;

    private String moneda;

    private String lote;
    
    public IfcPMorosidadesceActividDb() {
    }

    public IfcPMorosidadesceActividDb(Long id) {
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

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
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
        if (!(object instanceof IfcPMorosidadesceActividDb)) {
            return false;
        }
        IfcPMorosidadesceActividDb other = (IfcPMorosidadesceActividDb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.IfcPMorosidadesceActividDb[ id=" + id + " ]";
    }
    
}
