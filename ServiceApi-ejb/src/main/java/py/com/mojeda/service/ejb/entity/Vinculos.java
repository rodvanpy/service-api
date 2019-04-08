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
@Table(name = "VINCULOS")
public class Vinculos extends Base {

    private static final long serialVersionUID = -9149680520407250259L;
    private static final String SECUENCIA = "seq_vinculo_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
    @NotNull(message = "Ingrese Tipo Vinculo")
    @JoinColumn(name = "ID_TIPO_VINCULO", referencedColumnName = "id")
    private TipoVinculos tipoVinculo;
    
    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    private Personas persona;
    
    @ManyToOne
    @JoinColumn(name = "ID_VINCULO", referencedColumnName = "id")
    private Personas personaVinculo;
    
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "id")
    private Empresas empresa;
    
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
     * @return the tipoVinculo
     */
    public TipoVinculos getTipoVinculo() {
        return tipoVinculo;
    }

    /**
     * @param tipoVinculo the tipoVinculo to set
     */
    public void setTipoVinculo(TipoVinculos tipoVinculo) {
        this.tipoVinculo = tipoVinculo;
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
     * @return the empresa
     */
    public Empresas getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }
    
    
}
