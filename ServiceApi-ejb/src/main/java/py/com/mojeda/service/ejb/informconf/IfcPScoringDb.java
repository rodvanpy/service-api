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

public class IfcPScoringDb {

    private Long id;

    private Date fechaActualizacion;

    private String cedulaIdentidad;

    private String faja;

    private BigDecimal probabilidadInicial;

    private BigDecimal probabilidadFinal;

    private BigDecimal rangoScoreInicial;

    private BigDecimal rangoScoreFinal;

    private String lote;

    public IfcPScoringDb() {
    }

    public IfcPScoringDb(Long id) {
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

    public String getFaja() {
        return faja;
    }

    public void setFaja(String faja) {
        this.faja = faja;
    }

    public BigDecimal getProbabilidadInicial() {
        return probabilidadInicial;
    }

    public void setProbabilidadInicial(BigDecimal probabilidadInicial) {
        this.probabilidadInicial = probabilidadInicial;
    }

    public BigDecimal getProbabilidadFinal() {
        return probabilidadFinal;
    }

    public void setProbabilidadFinal(BigDecimal probabilidadFinal) {
        this.probabilidadFinal = probabilidadFinal;
    }

    public BigDecimal getRangoScoreInicial() {
        return rangoScoreInicial;
    }

    public void setRangoScoreInicial(BigDecimal rangoScoreInicial) {
        this.rangoScoreInicial = rangoScoreInicial;
    }

    public BigDecimal getRangoScoreFinal() {
        return rangoScoreFinal;
    }

    public void setRangoScoreFinal(BigDecimal rangoScoreFinal) {
        this.rangoScoreFinal = rangoScoreFinal;
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
        if (!(object instanceof IfcPScoringDb)) {
            return false;
        }
        IfcPScoringDb other = (IfcPScoringDb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.IfcPScoringDb[ id=" + id + " ]";
    }
    
}
