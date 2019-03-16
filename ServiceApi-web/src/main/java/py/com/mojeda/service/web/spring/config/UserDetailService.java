package py.com.mojeda.service.web.spring.config;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AccountStatusUserDetailsChecker;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import py.com.mojeda.service.ejb.entity.Usuario;
import py.com.mojeda.service.ejb.manager.UsuarioManager;


@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {

    private Context context;
    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
    public static final org.slf4j.Logger logger = LoggerFactory
            .getLogger("service_documenta");

    protected UsuarioManager usuarioManager;

    @Override
    public User loadUserByUsername(String idUser) throws UsernameNotFoundException {
        try {
            inicializarUsuarioManager();
        } catch (Exception ex) {
            Logger.getLogger(UserDetailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user = new User();

        Usuario ejObjeto = usuarioManager.get(Long.parseLong(idUser));
        if (ejObjeto != null) {
            user.setId(ejObjeto.getId());
            //user.setUsername(ejObjeto.getUsuario());
            user.setPassword(ejObjeto.getClaveAcceso());
            user.setAuthorities(Collections.EMPTY_LIST);
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        detailsChecker.check(user);

        return user;

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

                usuarioManager = (UsuarioManager) context.lookup("java:app/ServiceApi-web/UsuarioManagerImpl");
            } catch (NamingException ne) {
                throw new RuntimeException("No se encuentra EJB valor Manager: ", ne);
            }
        }
    }

}
