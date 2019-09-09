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
import py.com.mojeda.service.ejb.entity.Funcionarios;
import py.com.mojeda.service.ejb.entity.Rol;
import py.com.mojeda.service.ejb.manager.RolPermisoManager;
import py.com.mojeda.service.ejb.manager.FuncionariosManager;

@Service("userDetailsService")
public class UserDetailService implements UserDetailsService {

    private Context context;
    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
    public static final org.slf4j.Logger logger = LoggerFactory
            .getLogger("service_documenta");

    protected RolPermisoManager rolPermisoManager;
    protected FuncionariosManager funcionarioManager;

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

        Map<String, Object> objetoMap = funcionarioManager.getAtributos(new Funcionarios(Long.parseLong(idUser)), "id,alias,claveAcceso,nroLegajo,persona.id,persona.primerNombre,persona.segundoNombre,persona.primerApellido,persona.segundoApellido,persona.imagePath,sucursal.id,sucursal.empresa.id,rol.id,rol.nombre".split(","));
        if (objetoMap != null) {
            user.setId(Long.parseLong(objetoMap.get("id").toString()));

            String nombre = (objetoMap.get("persona.primerNombre") == null ? "" : objetoMap.get("persona.primerNombre"))
                    + " " + (objetoMap.get("persona.segundoNombre") == null ? "" : objetoMap.get("persona.segundoNombre"));

            String apellido = (objetoMap.get("persona.primerApellido") == null ? "" : objetoMap.get("persona.primerApellido"))
                    + " " + (objetoMap.get("persona.segundoApellido") == null ? "" : objetoMap.get("persona.segundoApellido"));

            user.setApellido(apellido);
            user.setNombre(nombre);
            user.setIdEmpresa(Long.parseLong(objetoMap.get("sucursal.empresa.id").toString()));
            user.setIdSusursal(Long.parseLong(objetoMap.get("sucursal.id").toString()));
            user.setNombreRol(objetoMap.get("rol.nombre").toString());
            user.setUsername(objetoMap.get("alias").toString());
            user.setImagePath(objetoMap.get("persona.imagePath") + "");

            RolPermiso ejRolPermiso = new RolPermiso();
            ejRolPermiso.setRol(new Rol(Long.parseLong(objetoMap.get("rol.id").toString())));

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
        if (funcionarioManager == null) {
            try {

                funcionarioManager = (FuncionariosManager) context.lookup("java:app/ServiceApi-ejb/FuncionariosManagerImpl");
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
