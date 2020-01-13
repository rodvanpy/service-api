/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.informconf;

import java.util.Date;
import javax.persistence.EmbeddedId;


/**
 *
 * @author miguel.ojeda
 */
public class ControlCarteraInformconf {
    
    protected ControlCarteraInformconfPK controlCarteraInformconfPK;
    
    private String primerNombre;

    private String primerApellido;
    
    private short tiempo;

    private String documento;

    private String primerNombreS;

    private String primerApellidoS;

    private Date fechaFinal;

    private String mensaje;

    private int estado;

    private Date fechaEstado;

    private int numeroLegajo;

    private String tipoPersona;

    private String lote;

    public ControlCarteraInformconf() {
    }

    public ControlCarteraInformconf(ControlCarteraInformconfPK controlCarteraInformconfPK) {
        this.controlCarteraInformconfPK = controlCarteraInformconfPK;
    }

    public ControlCarteraInformconf(ControlCarteraInformconfPK controlCarteraInformconfPK, String primerNombre, short tiempo, short estado, Date fechaEstado, int numeroLegajo, String tipoPersona) {
        this.controlCarteraInformconfPK = controlCarteraInformconfPK;
        this.primerNombre = primerNombre;
        this.tiempo = tiempo;
        this.estado = estado;
        this.fechaEstado = fechaEstado;
        this.numeroLegajo = numeroLegajo;
        this.tipoPersona = tipoPersona;
    }

    public ControlCarteraInformconf(Date fecha, String cedulaIdentidad) {
        this.controlCarteraInformconfPK = new ControlCarteraInformconfPK(fecha, cedulaIdentidad);
    }

    public ControlCarteraInformconfPK getControlCarteraInformconfPK() {
        return controlCarteraInformconfPK;
    }

    public void setControlCarteraInformconfPK(ControlCarteraInformconfPK controlCarteraInformconfPK) {
        this.controlCarteraInformconfPK = controlCarteraInformconfPK;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public short getTiempo() {
        return tiempo;
    }

    public void setTiempo(short tiempo) {
        this.tiempo = tiempo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPrimerNombreS() {
        return primerNombreS;
    }

    public void setPrimerNombreS(String primerNombreS) {
        this.primerNombreS = primerNombreS;
    }

    public String getPrimerApellidoS() {
        return primerApellidoS;
    }

    public void setPrimerApellidoS(String primerApellidoS) {
        this.primerApellidoS = primerApellidoS;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public int getNumeroLegajo() {
        return numeroLegajo;
    }

    public void setNumeroLegajo(int numeroLegajo) {
        this.numeroLegajo = numeroLegajo;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
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
        hash += (controlCarteraInformconfPK != null ? controlCarteraInformconfPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlCarteraInformconf)) {
            return false;
        }
        ControlCarteraInformconf other = (ControlCarteraInformconf) object;
        if ((this.controlCarteraInformconfPK == null && other.controlCarteraInformconfPK != null) || (this.controlCarteraInformconfPK != null && !this.controlCarteraInformconfPK.equals(other.controlCarteraInformconfPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication4.ControlCarteraInformconf[ controlCarteraInformconfPK=" + controlCarteraInformconfPK + " ]";
    }
    
}
