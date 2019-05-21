package py.com.mojeda.service.web.spring.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import py.com.mojeda.service.ejb.entity.RolPermiso;
import py.com.mojeda.service.ejb.entity.Usuarios;
import py.com.mojeda.service.ejb.manager.RolPermisoManager;
import py.com.mojeda.service.ejb.manager.UsuarioManager;

@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {

    private Context context;
    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
    public static final org.slf4j.Logger logger = LoggerFactory
            .getLogger("service_documenta");

    protected RolPermisoManager rolPermisoManager;
    protected UsuarioManager usuarioManager;

    @Override
    public User loadUserByUsername(String idUser) throws UsernameNotFoundException {
        try {
            inicializarUsuarioManager();
            inicializarRolPermisoManager();
        } catch (Exception ex) {
            Logger.getLogger(UserDetailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user = new User();
        List<GrantedAuthority> autoridades = new ArrayList<>();
        
        Usuarios ejObjeto = usuarioManager.get(Long.parseLong(idUser));
        if (ejObjeto != null) {
            user.setId(ejObjeto.getId());
            user.setId(ejObjeto.getId());
            user.setApellido(ejObjeto.getPersona().getPrimerApellido() + " " + ejObjeto.getPersona().getSegundoApellido() == null ? "" : ejObjeto.getPersona().getSegundoApellido());
            user.setNombre(ejObjeto.getPersona().getPrimerNombre() + " " + ejObjeto.getPersona().getSegundoNombre() == null ? "" : ejObjeto.getPersona().getSegundoNombre());
            user.setIdEmpresa(ejObjeto.getPersona().getSucursal().getEmpresa().getId());
            user.setIdSusursal(ejObjeto.getPersona().getSucursal().getId());
            user.setNombreRol(ejObjeto.getRol().getNombre());
            user.setUsername(ejObjeto.getAlias());
            user.setImagePath(ejObjeto.getPersona().getImagePath());

            RolPermiso ejRolPermiso = new RolPermiso();
            ejRolPermiso.setRol(ejObjeto.getRol());

            List<Map<String, Object>> listMapRol = rolPermisoManager.listAtributos(ejRolPermiso, "permiso.nombre".split(","));

            for (Map<String, Object> rpm : listMapRol) {
                autoridades.add(new SimpleGrantedAuthority(rpm.get("permiso.nombre").toString()));
            }
            user.setAuthorities(autoridades);
            
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
