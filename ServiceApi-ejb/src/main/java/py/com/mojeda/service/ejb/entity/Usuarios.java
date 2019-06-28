/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import java.util.List;
import java.util.Map;
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
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "USUARIOS", uniqueConstraints = @UniqueConstraint(name = "usuario_alias_uq", columnNames = {"alias"}))
public class Usuarios extends Base {

    private static final long serialVersionUID = 8538760347986185608L;
    private static final String SECUENCIA = "seq_usuario_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    //@NotNull(message = "Ingrese Alias")
    @NotEmpty(message = "Ingrese Alias")
    @Column(name = "ALIAS")
    private String alias;

    //@NotNull(message = " Ingrese Clave Acceso")
    @NotEmpty(message = "Ingrese Clave Acceso")
    @Column(name = "CLAVE_ACCESO")
    private String claveAcceso;

    @Column(name = "SUPER_USUARIO")
    private Boolean superUsuario;

    @NotNull(message = "Ingrese Tiempo de Expiracion del Tokens")
    @Column(name = "EXPIRATION_TIME_TOKENS")
    private Long expirationTimeTokens;

    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    @Valid
    private Personas persona;

    @ManyToOne
    @JoinColumn(name = "ID_SUCURSAL", referencedColumnName = "id")
    private Sucursales sucursal;
    

    @NotNull(message = "Ingrese Rol")
    @ManyToOne
    @JoinColumn(name = "ID_ROL", referencedColumnName = "id")
    private Rol rol;

    @Transient
    private List<Map<String, Object>> departamentos;
    
    @Transient
    private List<Vinculos> vinculos;

    public Usuarios() {

    }

    /**
     * @param id el id de Usuario
     */
    public Usuarios(Long id) {
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

    public Long getExpirationTimeTokens() {
        return expirationTimeTokens;
    }

    public void setExpirationTimeTokens(Long expirationTimeTokens) {
        this.expirationTimeTokens = expirationTimeTokens;
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    public List<Map<String, Object>> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Map<String, Object>> departamentos) {
        this.departamentos = departamentos;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    public List<Vinculos> getVinculos() {
        return vinculos;
    }

    public void setVinculos(List<Vinculos> vinculos) {
        this.vinculos = vinculos;
    }
    
    

}
