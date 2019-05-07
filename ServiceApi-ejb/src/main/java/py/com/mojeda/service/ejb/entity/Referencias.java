/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

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
@Table(name = "REFERENCIAS")
public class Referencias extends Base {

    private static final long serialVersionUID = -9149680520407250259L;
    private static final String SECUENCIA = "seq_referencia_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @NotEmpty(message = "Ingrese Nombre Contacto")
    @Column(name = "NOMBRE_CONTACTO")
    private String nombreContacto;
    
    @Column(name = "TELEFONO")
    private String telefono;
    
    @Column(name = "TELEFONO_CELULAR")
    private String telefonoCelular;
    
    @ManyToOne
    @NotNull(message = "Ingrese Tipo Referencia")
    @JoinColumn(name = "ID_TIPO_REFERENCIA", referencedColumnName = "id")
    private TipoReferencias tipoReferencia;
    
    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    private Personas persona;
    
    public Referencias() {

    }

    public Referencias(Long id) {
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
     * @return the nombreContacto
     */
    public String getNombreContacto() {
        return nombreContacto;
    }

    /**
     * @param nombreContacto the nombreContacto to set
     */
    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the telefonoCelular
     */
    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    /**
     * @param telefonoCelular the telefonoCelular to set
     */
    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    /**
     * @return the tipoReferencia
     */
    public TipoReferencias getTipoReferencia() {
        return tipoReferencia;
    }

    /**
     * @param tipoReferencia the tipoReferencia to set
     */
    public void setTipoReferencia(TipoReferencias tipoReferencia) {
        this.tipoReferencia = tipoReferencia;
    }

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
    
    public String getClassName() {
        return this.getClass().getSimpleName();
    }
}
