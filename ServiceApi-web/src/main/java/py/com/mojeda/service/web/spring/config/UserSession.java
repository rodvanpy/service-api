/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.spring.config;

import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import py.com.mojeda.service.ejb.entity.Usuario;
import py.com.mojeda.service.ejb.manager.UsuarioManager;


/**
 *
 * @author Miguel
 */
public class UserSession implements AuthenticationProvider {

    private Context context;

    protected UsuarioManager usuarioManager;

    static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserSession.class.getName());

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        List<GrantedAuthority> autoridades = new ArrayList<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            inicializarUsuarioManager();
            
            String userLogin = authentication.getPrincipal().toString();
            String passwordLogin = authentication.getCredentials().toString();
            
            Usuario ejObjeto = new Usuario();
            ejObjeto.setAlias(userLogin);
            
            ejObjeto = usuarioManager.get(ejObjeto);
            
            if (ejObjeto != null) {
                                
                User userDetails = new User();
                userDetails.setId(ejObjeto.getId());
                
                
                Authentication customAuthentication = new UsernamePasswordAuthenticationToken(userDetails,
                        passwordLogin, autoridades);
                
                return customAuthentication;
            } else {
                System.out.println("Usuario o Contraseña Inválidos.");
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
   
}
