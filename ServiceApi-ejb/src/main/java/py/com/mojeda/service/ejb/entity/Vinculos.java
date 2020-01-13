/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "VINCULOS", schema = "PUBLIC")
public class Vinculos extends Base {

    private static final long serialVersionUID = -9149680520407250259L;
    private static final String SECUENCIA = "seq_vinculo_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @NotNull(message = "Ingrese Tipo Vinculo")
    @Column(name = "TIPO_VINCULO")
    private String tipoVinculo;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    private Personas persona;
    
    @ManyToOne
    @JoinColumn(name = "ID_VINCULO", referencedColumnName = "id")
    private Personas personaVinculo;
    
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "id")
//    private Empresas empresa;
    
    @Transient
    private List<OcupacionPersona> ocupaciones;
    
    public Vinculos() {

    }

    public Vinculos(Long id) {
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
     * @return the personaVinculo
     */
    public Personas getPersonaVinculo() {
        return personaVinculo;
    }

    /**
     * @param personaVinculo the personaVinculo to set
     */
    public void setPersonaVinculo(Personas personaVinculo) {
        this.personaVinculo = personaVinculo;
    }

    /**
     * @return the tipoVinculo
     */
    public String getTipoVinculo() {
        return tipoVinculo;
    }

    /**
     * @param tipoVinculo the tipoVinculo to set
     */
    public void setTipoVinculo(String tipoVinculo) {
        this.tipoVinculo = tipoVinculo;
    }

    public List<OcupacionPersona> getOcupaciones() {
        return ocupaciones;
    }

    public void setOcupaciones(List<OcupacionPersona> ocupaciones) {
        this.ocupaciones = ocupaciones;
    }
    
    
}
