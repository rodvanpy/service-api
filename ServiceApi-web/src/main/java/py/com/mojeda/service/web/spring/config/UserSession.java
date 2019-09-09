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
import py.com.mojeda.service.ejb.entity.Funcionarios;
import py.com.mojeda.service.ejb.manager.RolPermisoManager;
import py.com.mojeda.service.ejb.manager.FuncionariosManager;

/**
 *
 * @author Miguel
 */
public class UserSession implements AuthenticationProvider {

    private Context context;

    protected FuncionariosManager usuarioManager;

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
            
            String cod = passwordEncoder.encode(passwordLogin);
            
            Funcionarios ejObjeto = new Funcionarios();
            ejObjeto.setAlias(userLogin);

            Map<String, Object> objetoMap = usuarioManager.getAtributos(ejObjeto,"id,alias,claveAcceso,nroLegajo,persona.id,persona.primerNombre,persona.segundoNombre,persona.primerApellido,persona.segundoApellido,persona.imagePath,sucursal.id,sucursal.empresa.id,rol.id,rol.nombre".split(","));           
            if (objetoMap != null) {

                if (passwordEncoder.matches(passwordLogin, objetoMap.get("claveAcceso").toString())) {

                    User userDetails = new User();
                    userDetails.setId(Long.parseLong(objetoMap.get("id").toString()));
                    
                    String nombre = (objetoMap.get("persona.primerNombre") == null ? "" : objetoMap.get("persona.primerNombre"))
                    + " " + (objetoMap.get("persona.segundoNombre") == null ? "" : objetoMap.get("persona.segundoNombre"));
                    
                    String apellido =(objetoMap.get("persona.primerApellido") == null ? "" : objetoMap.get("persona.primerApellido"))
                    + " " + (objetoMap.get("persona.segundoApellido") == null ? "" : objetoMap.get("persona.segundoApellido"));
                    
                    userDetails.setApellido(apellido);
                    userDetails.setNombre(nombre);
                    userDetails.setIdEmpresa(Long.parseLong(objetoMap.get("sucursal.empresa.id").toString()));
                    userDetails.setIdSusursal(Long.parseLong(objetoMap.get("sucursal.id").toString()));
                    userDetails.setNombreRol(objetoMap.get("rol.nombre").toString());
                    userDetails.setUsername(objetoMap.get("alias").toString());
                    userDetails.setImagePath(objetoMap.get("persona.imagePath") + "");
                    
                    RolPermiso ejRolPermiso = new RolPermiso();
                    ejRolPermiso.setRol(new Rol(Long.parseLong(objetoMap.get("rol.id").toString())));

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

                usuarioManager = (FuncionariosManager) context.lookup("java:app/ServiceApi-ejb/FuncionariosManagerImpl");
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
