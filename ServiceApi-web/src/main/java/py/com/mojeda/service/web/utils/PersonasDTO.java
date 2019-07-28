/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.utils;

import java.util.List;
import javax.persistence.Transient;
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.entity.IngresosEgresos;
import py.com.mojeda.service.ejb.entity.OcupacionPersona;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Referencias;
import py.com.mojeda.service.ejb.entity.Vinculos;

/**
 *
 * @author mojeda
 */
public class PersonasDTO {
    
    private Personas persona;
    
    private List<Bienes> bienesInmuebles;
    
    private List<Bienes> bienesVehiculo;
    
    private List<Referencias> referencias;
    
    private List<IngresosEgresos> egresos;
    
    private List<IngresosEgresos> ingresos;
    
    private List<OcupacionPersona> ocupaciones;
    
    private List<Vinculos> vinculos;

    /**
     * @return the persona
     */
    public Personas getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    /**
     * @return the bienesInmuebles
     */
    public List<Bienes> getBienesInmuebles() {
        return bienesInmuebles;
    }

    /**
     * @param bienesInmuebles the bienesInmuebles to set
     */
    public void setBienesInmuebles(List<Bienes> bienesInmuebles) {
        this.bienesInmuebles = bienesInmuebles;
    }

    /**
     * @return the bienesVehiculo
     */
    public List<Bienes> getBienesVehiculo() {
        return bienesVehiculo;
    }

    /**
     * @param bienesVehiculo the bienesVehiculo to set
     */
    public void setBienesVehiculo(List<Bienes> bienesVehiculo) {
        this.bienesVehiculo = bienesVehiculo;
    }

    /**
     * @return the referencias
     */
    public List<Referencias> getReferencias() {
        return referencias;
    }

    /**
     * @param referencias the referencias to set
     */
    public void setReferencias(List<Referencias> referencias) {
        this.referencias = referencias;
    }

    /**
     * @return the egresos
     */
    public List<IngresosEgresos> getEgresos() {
        return egresos;
    }

    /**
     * @param egresos the egresos to set
     */
    public void setEgresos(List<IngresosEgresos> egresos) {
        this.egresos = egresos;
    }

    /**
     * @return the ingresos
     */
    public List<IngresosEgresos> getIngresos() {
        return ingresos;
    }

    /**
     * @param ingresos the ingresos to set
     */
    public void setIngresos(List<IngresosEgresos> ingresos) {
        this.ingresos = ingresos;
    }

    /**
     * @return the ocupaciones
     */
    public List<OcupacionPersona> getOcupaciones() {
        return ocupaciones;
    }

    /**
     * @param ocupaciones the ocupaciones to set
     */
    public void setOcupaciones(List<OcupacionPersona> ocupaciones) {
        this.ocupaciones = ocupaciones;
    }

    /**
     * @return the vinculos
     */
    public List<Vinculos> getVinculos() {
        return vinculos;
    }

    /**
     * @param vinculos the vinculos to set
     */
    public void setVinculos(List<Vinculos> vinculos) {
        this.vinculos = vinculos;
    }
    
    
    
}
