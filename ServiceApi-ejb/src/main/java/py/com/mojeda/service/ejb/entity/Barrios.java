/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "BARRIOS")
public class Barrios extends Base{

    private static final long serialVersionUID = 1574657L;
    private static final String SECUENCIA = "seq_barrios_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @NotEmpty(message = "Ingrese Nombre")
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "LATITUD")
    private Double latitud;

    @Column(name = "LONGITUD")
    private Double longitud;
    
    @NotNull(message = "Ingrese Ciudad")
    @ManyToOne
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "id")
    private Ciudades ciudad;
    
    @Column(name = "ENTIDAD")
    private String entidad = "BARRIOS";
      
    public Barrios() {
    }

    public Barrios(Long id) {
            this.setId(id);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the latitud
     */
    public Double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public Double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * @param entidad the entidad to set
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    
}
