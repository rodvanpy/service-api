/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "TOKENS")
public class Tokens implements Serializable {
    private static long serialVersionUID = 85387603479861808L;
    private static final String SECUENCIA = "seq_token_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "TOKEN")
    private String token;    
    
    @Basic(optional = false)
    @Column(name = "UPDATE_TOKEN")
    private String updateToken;
    
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private long idUsuario;
    
    @Basic(optional = false)
    @Column(name = "USUARIO")
    private String usuario;

    public Tokens() {

    }

    /**
     * @param id el id de Rol
     */
    public Tokens(Long id) {
        this.id = id;
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
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the idUsuario
     */
    public long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUpdateToken() {
        return updateToken;
    }

    public void setUpdateToken(String updateToken) {
        this.updateToken = updateToken;
    }
       
}
