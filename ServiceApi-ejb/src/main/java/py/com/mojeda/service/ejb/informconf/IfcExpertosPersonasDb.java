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
public class IfcExpertosPersonasDb{

    private Long id;

    private Date fechaActualizacion;

    private String tipoDocumento;

    private String documento;

    private String integrante;

    private String explicacion;

    private String cantAbiertasC30;

    private String diasAtrasoC30;

    private String saldoAbiertasGsC30;

    private String saldoAbiertasUsdC30;

    private String saldoAbierGsAfiC30;

    private String saldoAbierUsdAfiC30;

    private String cantAbierNoAfiC30;

    private String cantAbierAfiC30;

    private String diasAtrNoAfiC30;

    private String diasAtrAfiC30;

    private String saldoAbierGsNoAfiC30;

    private String saldoAbierUsdNoAfiC30;

    private String cantidadAbiertas;

    private String diasAtraso;

    private String saldoAbiertasGs;

    private String saldoAbiertasUsd;

    private String lote;

    public IfcExpertosPersonasDb() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the fechaActualizacion
     */
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the integrante
     */
    public String getIntegrante() {
        return integrante;
    }

    /**
     * @param integrante the integrante to set
     */
    public void setIntegrante(String integrante) {
        this.integrante = integrante;
    }

    /**
     * @return the explicacion
     */
    public String getExplicacion() {
        return explicacion;
    }

    /**
     * @param explicacion the explicacion to set
     */
    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    /**
     * @return the cantAbiertasC30
     */
    public String getCantAbiertasC30() {
        return cantAbiertasC30;
    }

    /**
     * @param cantAbiertasC30 the cantAbiertasC30 to set
     */
    public void setCantAbiertasC30(String cantAbiertasC30) {
        this.cantAbiertasC30 = cantAbiertasC30;
    }

    /**
     * @return the diasAtrasoC30
     */
    public String getDiasAtrasoC30() {
        return diasAtrasoC30;
    }

    /**
     * @param diasAtrasoC30 the diasAtrasoC30 to set
     */
    public void setDiasAtrasoC30(String diasAtrasoC30) {
        this.diasAtrasoC30 = diasAtrasoC30;
    }

    /**
     * @return the saldoAbiertasGsC30
     */
    public String getSaldoAbiertasGsC30() {
        return saldoAbiertasGsC30;
    }

    /**
     * @param saldoAbiertasGsC30 the saldoAbiertasGsC30 to set
     */
    public void setSaldoAbiertasGsC30(String saldoAbiertasGsC30) {
        this.saldoAbiertasGsC30 = saldoAbiertasGsC30;
    }

    /**
     * @return the saldoAbiertasUsdC30
     */
    public String getSaldoAbiertasUsdC30() {
        return saldoAbiertasUsdC30;
    }

    /**
     * @param saldoAbiertasUsdC30 the saldoAbiertasUsdC30 to set
     */
    public void setSaldoAbiertasUsdC30(String saldoAbiertasUsdC30) {
        this.saldoAbiertasUsdC30 = saldoAbiertasUsdC30;
    }

    /**
     * @return the saldoAbierGsAfiC30
     */
    public String getSaldoAbierGsAfiC30() {
        return saldoAbierGsAfiC30;
    }

    /**
     * @param saldoAbierGsAfiC30 the saldoAbierGsAfiC30 to set
     */
    public void setSaldoAbierGsAfiC30(String saldoAbierGsAfiC30) {
        this.saldoAbierGsAfiC30 = saldoAbierGsAfiC30;
    }

    /**
     * @return the saldoAbierUsdAfiC30
     */
    public String getSaldoAbierUsdAfiC30() {
        return saldoAbierUsdAfiC30;
    }

    /**
     * @param saldoAbierUsdAfiC30 the saldoAbierUsdAfiC30 to set
     */
    public void setSaldoAbierUsdAfiC30(String saldoAbierUsdAfiC30) {
        this.saldoAbierUsdAfiC30 = saldoAbierUsdAfiC30;
    }

    /**
     * @return the cantAbierNoAfiC30
     */
    public String getCantAbierNoAfiC30() {
        return cantAbierNoAfiC30;
    }

    /**
     * @param cantAbierNoAfiC30 the cantAbierNoAfiC30 to set
     */
    public void setCantAbierNoAfiC30(String cantAbierNoAfiC30) {
        this.cantAbierNoAfiC30 = cantAbierNoAfiC30;
    }

    /**
     * @return the cantAbierAfiC30
     */
    public String getCantAbierAfiC30() {
        return cantAbierAfiC30;
    }

    /**
     * @param cantAbierAfiC30 the cantAbierAfiC30 to set
     */
    public void setCantAbierAfiC30(String cantAbierAfiC30) {
        this.cantAbierAfiC30 = cantAbierAfiC30;
    }

    /**
     * @return the diasAtrNoAfiC30
     */
    public String getDiasAtrNoAfiC30() {
        return diasAtrNoAfiC30;
    }

    /**
     * @param diasAtrNoAfiC30 the diasAtrNoAfiC30 to set
     */
    public void setDiasAtrNoAfiC30(String diasAtrNoAfiC30) {
        this.diasAtrNoAfiC30 = diasAtrNoAfiC30;
    }

    /**
     * @return the diasAtrAfiC30
     */
    public String getDiasAtrAfiC30() {
        return diasAtrAfiC30;
    }

    /**
     * @param diasAtrAfiC30 the diasAtrAfiC30 to set
     */
    public void setDiasAtrAfiC30(String diasAtrAfiC30) {
        this.diasAtrAfiC30 = diasAtrAfiC30;
    }

    /**
     * @return the saldoAbierGsNoAfiC30
     */
    public String getSaldoAbierGsNoAfiC30() {
        return saldoAbierGsNoAfiC30;
    }

    /**
     * @param saldoAbierGsNoAfiC30 the saldoAbierGsNoAfiC30 to set
     */
    public void setSaldoAbierGsNoAfiC30(String saldoAbierGsNoAfiC30) {
        this.saldoAbierGsNoAfiC30 = saldoAbierGsNoAfiC30;
    }

    /**
     * @return the saldoAbierUsdNoAfiC30
     */
    public String getSaldoAbierUsdNoAfiC30() {
        return saldoAbierUsdNoAfiC30;
    }

    /**
     * @param saldoAbierUsdNoAfiC30 the saldoAbierUsdNoAfiC30 to set
     */
    public void setSaldoAbierUsdNoAfiC30(String saldoAbierUsdNoAfiC30) {
        this.saldoAbierUsdNoAfiC30 = saldoAbierUsdNoAfiC30;
    }

    /**
     * @return the cantidadAbiertas
     */
    public String getCantidadAbiertas() {
        return cantidadAbiertas;
    }

    /**
     * @param cantidadAbiertas the cantidadAbiertas to set
     */
    public void setCantidadAbiertas(String cantidadAbiertas) {
        this.cantidadAbiertas = cantidadAbiertas;
    }

    /**
     * @return the diasAtraso
     */
    public String getDiasAtraso() {
        return diasAtraso;
    }

    /**
     * @param diasAtraso the diasAtraso to set
     */
    public void setDiasAtraso(String diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    /**
     * @return the saldoAbiertasGs
     */
    public String getSaldoAbiertasGs() {
        return saldoAbiertasGs;
    }

    /**
     * @param saldoAbiertasGs the saldoAbiertasGs to set
     */
    public void setSaldoAbiertasGs(String saldoAbiertasGs) {
        this.saldoAbiertasGs = saldoAbiertasGs;
    }

    /**
     * @return the saldoAbiertasUsd
     */
    public String getSaldoAbiertasUsd() {
        return saldoAbiertasUsd;
    }

    /**
     * @param saldoAbiertasUsd the saldoAbiertasUsd to set
     */
    public void setSaldoAbiertasUsd(String saldoAbiertasUsd) {
        this.saldoAbiertasUsd = saldoAbiertasUsd;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
}
