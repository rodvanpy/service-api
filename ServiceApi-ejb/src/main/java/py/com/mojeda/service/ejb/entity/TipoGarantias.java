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
@Table(name = "TIPO_GARANTIAS")
public class TipoGarantias extends Base{

    private static final long serialVersionUID = 1574657L;
    private static final String SECUENCIA = "seq_tipo_garantia_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @NotEmpty(message = "Ingrese Nombre")
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    
    @NotEmpty(message = "Ingrese Codigo")
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;
    
    @NotNull(message = "Campo Codeudor Obligarorio")
    @Basic(optional = false)
    @Column(name = "CODEUDOR")
    private Boolean codeudor;
    
    @NotNull(message = "Campo Hipoteca Obligarorio")
    @Basic(optional = false)
    @Column(name = "HIPOTECA")
    private Boolean hipoteca;
    
    
    public TipoGarantias() {
    }

    public TipoGarantias(Long id) {
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

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codeudor
     */
    public Boolean getCodeudor() {
        return codeudor;
    }

    /**
     * @param codeudor the codeudor to set
     */
    public void setCodeudor(Boolean codeudor) {
        this.codeudor = codeudor;
    }

    /**
     * @return the hipoteca
     */
    public Boolean getHipoteca() {
        return hipoteca;
    }

    /**
     * @param hipoteca the hipoteca to set
     */
    public void setHipoteca(Boolean hipoteca) {
        this.hipoteca = hipoteca;
    }

    
    
    
}
