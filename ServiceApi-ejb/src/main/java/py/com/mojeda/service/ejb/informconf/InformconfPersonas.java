/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.informconf;

import java.util.List;

/**
 *
 * @author miguel.ojeda
 */
public class InformconfPersonas {
    
    private IfcPScoringDb scoring;
    
    private IfcPDatosPersonalesDb datosPersonales;
    
    private IfcPDireccionDb ultimaDireccion;
    
    private List<IfcPDireccionDetalleDb> historialDirecciones;
    
    private IfcPLugarTrabajoDb ultimoLugarTrabajo;
    
    private List<IfcPLugarTrabajoDetalleDb> historialLugarTrabajo;
    
    private IfcPDatosConyugeDb datosConyuge;
    
    private IfcPInhibicionesResumenDb inhibicionesResumen;
    
    private IfcPDemandasResumenDb demandasResumen;
    
    private IfcPConvocatoriasResumenDb convocatoriasResumen;
    
    private IfcPQuiebrasResumenDb quiebras;
    
    private IfcPRematesResumenDb rematesResumen;
    
    private List<IfcPMorosidadesceActividDb> morosidadesActividadAdefi;
    
    private IfcPInhabilitacionResumeDb inhabilitacionesResumen;
    
    private List<IfcPSolicitudesDetalleDb> solicitudesDetalle;
    
    private List<IfcPMorosidadesActividadDb> morosidadesActividad;

    /**
     * @return the scoring
     */
    public IfcPScoringDb getScoring() {
        return scoring;
    }

    /**
     * @param scoring the scoring to set
     */
    public void setScoring(IfcPScoringDb scoring) {
        this.scoring = scoring;
    }

    /**
     * @return the datosPersonales
     */
    public IfcPDatosPersonalesDb getDatosPersonales() {
        return datosPersonales;
    }

    /**
     * @param datosPersonales the datosPersonales to set
     */
    public void setDatosPersonales(IfcPDatosPersonalesDb datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    /**
     * @return the ultimaDireccion
     */
    public IfcPDireccionDb getUltimaDireccion() {
        return ultimaDireccion;
    }

    /**
     * @param ultimaDireccion the ultimaDireccion to set
     */
    public void setUltimaDireccion(IfcPDireccionDb ultimaDireccion) {
        this.ultimaDireccion = ultimaDireccion;
    }

    /**
     * @return the historialDirecciones
     */
    public List<IfcPDireccionDetalleDb> getHistorialDirecciones() {
        return historialDirecciones;
    }

    /**
     * @param historialDirecciones the historialDirecciones to set
     */
    public void setHistorialDirecciones(List<IfcPDireccionDetalleDb> historialDirecciones) {
        this.historialDirecciones = historialDirecciones;
    }

    /**
     * @return the ultimoLugarTrabajo
     */
    public IfcPLugarTrabajoDb getUltimoLugarTrabajo() {
        return ultimoLugarTrabajo;
    }

    /**
     * @param ultimoLugarTrabajo the ultimoLugarTrabajo to set
     */
    public void setUltimoLugarTrabajo(IfcPLugarTrabajoDb ultimoLugarTrabajo) {
        this.ultimoLugarTrabajo = ultimoLugarTrabajo;
    }

    /**
     * @return the historialLugarTrabajo
     */
    public List<IfcPLugarTrabajoDetalleDb> getHistorialLugarTrabajo() {
        return historialLugarTrabajo;
    }

    /**
     * @param historialLugarTrabajo the historialLugarTrabajo to set
     */
    public void setHistorialLugarTrabajo(List<IfcPLugarTrabajoDetalleDb> historialLugarTrabajo) {
        this.historialLugarTrabajo = historialLugarTrabajo;
    }

    /**
     * @return the datosConyuge
     */
    public IfcPDatosConyugeDb getDatosConyuge() {
        return datosConyuge;
    }

    /**
     * @param datosConyuge the datosConyuge to set
     */
    public void setDatosConyuge(IfcPDatosConyugeDb datosConyuge) {
        this.datosConyuge = datosConyuge;
    }

    /**
     * @return the inhibicionesResumen
     */
    public IfcPInhibicionesResumenDb getInhibicionesResumen() {
        return inhibicionesResumen;
    }

    /**
     * @param inhibicionesResumen the inhibicionesResumen to set
     */
    public void setInhibicionesResumen(IfcPInhibicionesResumenDb inhibicionesResumen) {
        this.inhibicionesResumen = inhibicionesResumen;
    }

    /**
     * @return the demandasResumen
     */
    public IfcPDemandasResumenDb getDemandasResumen() {
        return demandasResumen;
    }

    /**
     * @param demandasResumen the demandasResumen to set
     */
    public void setDemandasResumen(IfcPDemandasResumenDb demandasResumen) {
        this.demandasResumen = demandasResumen;
    }

    /**
     * @return the convocatoriasResumen
     */
    public IfcPConvocatoriasResumenDb getConvocatoriasResumen() {
        return convocatoriasResumen;
    }

    /**
     * @param convocatoriasResumen the convocatoriasResumen to set
     */
    public void setConvocatoriasResumen(IfcPConvocatoriasResumenDb convocatoriasResumen) {
        this.convocatoriasResumen = convocatoriasResumen;
    }

    /**
     * @return the quiebras
     */
    public IfcPQuiebrasResumenDb getQuiebras() {
        return quiebras;
    }

    /**
     * @param quiebras the quiebras to set
     */
    public void setQuiebras(IfcPQuiebrasResumenDb quiebras) {
        this.quiebras = quiebras;
    }

    /**
     * @return the rematesResumen
     */
    public IfcPRematesResumenDb getRematesResumen() {
        return rematesResumen;
    }

    /**
     * @param rematesResumen the rematesResumen to set
     */
    public void setRematesResumen(IfcPRematesResumenDb rematesResumen) {
        this.rematesResumen = rematesResumen;
    }

    

    /**
     * @return the inhabilitacionesResumen
     */
    public IfcPInhabilitacionResumeDb getInhabilitacionesResumen() {
        return inhabilitacionesResumen;
    }

    /**
     * @param inhabilitacionesResumen the inhabilitacionesResumen to set
     */
    public void setInhabilitacionesResumen(IfcPInhabilitacionResumeDb inhabilitacionesResumen) {
        this.inhabilitacionesResumen = inhabilitacionesResumen;
    }

    /**
     * @return the solicitudesDetalle
     */
    public List<IfcPSolicitudesDetalleDb> getSolicitudesDetalle() {
        return solicitudesDetalle;
    }

    /**
     * @param solicitudesDetalle the solicitudesDetalle to set
     */
    public void setSolicitudesDetalle(List<IfcPSolicitudesDetalleDb> solicitudesDetalle) {
        this.solicitudesDetalle = solicitudesDetalle;
    }

    public List<IfcPMorosidadesceActividDb> getMorosidadesActividadAdefi() {
        return morosidadesActividadAdefi;
    }

    public void setMorosidadesActividadAdefi(List<IfcPMorosidadesceActividDb> morosidadesActividadAdefi) {
        this.morosidadesActividadAdefi = morosidadesActividadAdefi;
    }

    public List<IfcPMorosidadesActividadDb> getMorosidadesActividad() {
        return morosidadesActividad;
    }

    public void setMorosidadesActividad(List<IfcPMorosidadesActividadDb> morosidadesActividad) {
        this.morosidadesActividad = morosidadesActividad;
    }
    
    
}
