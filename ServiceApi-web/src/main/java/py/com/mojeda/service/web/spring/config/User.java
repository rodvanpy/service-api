/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.web.spring.config;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 *
 * @author Miguel
 */
public class User implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;
    private String idMovil;
    private Long idEmpresa;
    private Long idSusursal;
    private Collection<GrantedAuthority> authorities;
    
    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreRol() {
        return rol;
    }

    public void setNombreRol(String nombreRol) {
        this.rol = nombreRol;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
     
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getIdMovil() {
        return idMovil;
    }

    public void setIdMovil(String idMovil) {
        this.idMovil = idMovil;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Long getIdSusursal() {
        return idSusursal;
    }

    public void setIdSusursal(Long idSusursal) {
        this.idSusursal = idSusursal;
    }
           
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    public boolean tienePermiso(String permiso) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (permiso == null || ( permiso != null && permiso.compareToIgnoreCase("") == 0)) {
            return false;
        }

        for(GrantedAuthority rpm : getAuthorities()){
            if(rpm.getAuthority().compareToIgnoreCase(permiso) == 0){
                return true;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javarevolutions.sistema.entities.Login[ usuario=" + username + " ]";
    }

    /**
     * Returns the authorities granted to the user. Cannot return
     * <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Set the authorities granted to the user.
     *
     * @param roles
     */
    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * Regresa el identificador único de un usuario.
     * @return 
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Establece el identificador único de un usuario.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Indicates whether the user's account has expired. An expired account
     * cannot be authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie
     * non-expired), <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code>
     * otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie
     * non-expired), <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot
     * be authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code>
     * otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
