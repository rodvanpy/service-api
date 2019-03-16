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
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Miguel
 */
@Entity
public class Usuario extends Base{
    
    private static final long serialVersionUID = 8538760347986185608L;
    private static final String SECUENCIA = "seq_usuario_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @NotNull
    @NotEmpty
    @Column(name = "ALIAS")
    private String alias;

    @NotNull
    @NotEmpty
    @Column(name = "CLAVE_ACCESO")
    private String claveAcceso;
    
    @Column(name = "SUPER_USUARIO")
    private Boolean superUsuario; 
    
    @Column(name = "EXPIRATION_TIME_TOKENS")
    private Long expirationTimeTokens;
    
    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    private Persona persona;
    
    @ManyToOne
    @JoinColumn(name = "ID_ROL", referencedColumnName = "id")
    private Rol rol;
    
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "id")
    private Empresa empresa;
    
    public Usuario() {

    }

    /**
     * @param id
     *            el id de Usuario
     */
    public Usuario(Long id) {
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
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

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
     * @return the superUsuario
     */
    public Boolean getSuperUsuario() {
        return superUsuario;
    }

    /**
     * @param superUsuario the superUsuario to set
     */
    public void setSuperUsuario(Boolean superUsuario) {
        this.superUsuario = superUsuario;
    }

    /**
     * @return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Long getExpirationTimeTokens() {
        return expirationTimeTokens;
    }

    public void setExpirationTimeTokens(Long expirationTimeTokens) {
        this.expirationTimeTokens = expirationTimeTokens;
    }
           
}
