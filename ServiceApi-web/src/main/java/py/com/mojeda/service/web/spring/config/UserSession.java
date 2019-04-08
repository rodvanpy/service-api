/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.spring.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import py.com.mojeda.service.ejb.entity.Rol;
import py.com.mojeda.service.ejb.entity.RolPermiso;
import py.com.mojeda.service.ejb.entity.Usuarios;
import py.com.mojeda.service.ejb.manager.RolPermisoManager;
import py.com.mojeda.service.ejb.manager.UsuarioManager;

/**
 *
 * @author Miguel
 */
public class UserSession implements AuthenticationProvider {

    private Context context;

    protected UsuarioManager usuarioManager;

    protected RolPermisoManager rolPermisoManager;

    static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserSession.class.getName());

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        List<GrantedAuthority> autoridades = new ArrayList<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            inicializarUsuarioManager();
            inicializarRolPermisoManager();

            String userLogin = authentication.getPrincipal().toString();
            String passwordLogin = authentication.getCredentials().toString();

            Usuarios ejObjeto = new Usuarios();
            ejObjeto.setAlias(userLogin);

            ejObjeto = usuarioManager.get(ejObjeto);
            String cod = passwordEncoder.encode(passwordLogin);
            if (ejObjeto != null) {

                if (passwordEncoder.matches(passwordLogin, ejObjeto.getClaveAcceso())) {

                    User userDetails = new User();
                    userDetails.setId(ejObjeto.getId());
                    userDetails.setApellido(ejObjeto.getPersona().getPrimerApellido() + " " + ejObjeto.getPersona().getSegundoApellido() == null ? "" : ejObjeto.getPersona().getSegundoApellido());
                    userDetails.setNombre(ejObjeto.getPersona().getPrimerNombre() + " " + ejObjeto.getPersona().getSegundoNombre() == null ? "" : ejObjeto.getPersona().getSegundoNombre());
                    userDetails.setIdEmpresa(ejObjeto.getSucursal().getEmpresa().getId());
                    userDetails.setIdSusursal(ejObjeto.getSucursal().getId());
                    userDetails.setNombreRol(ejObjeto.getRol().getNombre());
                    userDetails.setUsername(ejObjeto.getAlias());

                    RolPermiso ejRolPermiso = new RolPermiso();
                    ejRolPermiso.setRol(ejObjeto.getRol());

                    List<Map<String, Object>> listMapRol = rolPermisoManager.listAtributos(ejRolPermiso, "permiso.nombre".split(","));

                    if (listMapRol != null) {
                        for (Map<String, Object> rpm : listMapRol) {
                            autoridades.add(new SimpleGrantedAuthority(rpm.get("permiso.nombre").toString()));
                        }
                    } else {
                        System.out.println("El rol no posee ningun permiso asignado.");
                        throw new BadCredentialsException("Usuario o ContraseÃ±a InvÃ¡lidos.");
                    }
                    
                    userDetails.setAuthorities(autoridades);
                    
                    Authentication customAuthentication = new UsernamePasswordAuthenticationToken(userDetails,
                            passwordLogin, autoridades);

                    return customAuthentication;
                } else {
                    throw new BadCredentialsException("Usuario o Contraseña Inválidos.");
                }

            } else {
                throw new BadCredentialsException("Usuario o Contraseña Inválidos.");
            }

        } catch (Exception ex) {
            log.error("Error en el login " + ex);
            throw new BadCredentialsException("Usuario o Contraseña Inválidos.");
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    protected void inicializarUsuarioManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (usuarioManager == null) {
            try {

                usuarioManager = (UsuarioManager) context.lookup("java:app/ServiceApi-ejb/UsuarioManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }

    protected void inicializarRolPermisoManager() throws Exception {
        if (context == null) {
            try {
                context = new InitialContext();
            } catch (NamingException e1) {
                throw new RuntimeException("No se puede inicializar el contexto", e1);
            }
        }
        if (rolPermisoManager == null) {
            try {

                rolPermisoManager = (RolPermisoManager) context.lookup("java:app/ServiceApi-ejb/RolPermisoManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }

}
