/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.utils;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author mojeda
 */
public class Password {
    
    @NotEmpty(message = "Ingrese Alias")
    private String claveAcceso;
    @NotEmpty(message = "Ingrese Alias")
    private String nuevaClaveAcceso;
    @NotEmpty(message = "Ingrese Alias")
    private String confirmarClaveAcceso;

    /**
     * @return the claveAcceso
     */
    public String getClaveAcceso() {
        return claveAcceso;
    }

    /**
     * @param claveAcceso the claveAcceso to set
     */
    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    /**
     * @return the nuevaClaveAcceso
     */
    public String getNuevaClaveAcceso() {
        return nuevaClaveAcceso;
    }

    /**
     * @param nuevaClaveAcceso the nuevaClaveAcceso to set
     */
    public void setNuevaClaveAcceso(String nuevaClaveAcceso) {
        this.nuevaClaveAcceso = nuevaClaveAcceso;
    }

    /**
     * @return the confirmarClaveAcceso
     */
    public String getConfirmarClaveAcceso() {
        return confirmarClaveAcceso;
    }

    /**
     * @param confirmarClaveAcceso the confirmarClaveAcceso to set
     */
    public void setConfirmarClaveAcceso(String confirmarClaveAcceso) {
        this.confirmarClaveAcceso = confirmarClaveAcceso;
    }
    
    
    
}
