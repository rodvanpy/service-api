/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Rol;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.Usuarios;
import py.com.mojeda.service.ejb.manager.ImagenManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.UsuarioManager;
import py.com.mojeda.service.ejb.utils.Base64Bytes;

/**
 *
 * @author Miguel
 */
@Stateless
public class UsuarioManagerImpl extends GenericDaoImpl<Usuarios, Long>
        implements UsuarioManager {

    @Override
    protected Class<Usuarios> getEntityBeanType() {
        return Usuarios.class;
    }

    @EJB(mappedName = "java:app/ServiceApi-ejb/PersonaManagerImpl")
    private PersonaManager personaManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/ImagenManagerImpl")
    private ImagenManager imagenManager;

    @Override
    public Usuarios guardar(Usuarios usuario) throws Exception {
        Usuarios object = null;
        if (usuario != null
                && usuario.getPersona() != null) {

            Personas ejPersona = new Personas();
            ejPersona.setDocumento(usuario.getPersona().getDocumento());

            ejPersona = personaManager.get(ejPersona);

            if (ejPersona != null) {

                ejPersona.setPrimerNombre(usuario.getPersona().getPrimerNombre());
                ejPersona.setSegundoNombre(usuario.getPersona().getSegundoNombre());
                ejPersona.setPrimerApellido(usuario.getPersona().getPrimerApellido());
                ejPersona.setSegundoApellido(usuario.getPersona().getSegundoApellido());
                ejPersona.setEmail(usuario.getPersona().getEmail());
                ejPersona.setEstadoCivil(usuario.getPersona().getEstadoCivil());
                ejPersona.setNumeroHijos(usuario.getPersona().getNumeroHijos());
                ejPersona.setTelefonoParticular(usuario.getPersona().getTelefonoParticular());
                ejPersona.setTelefonoSecundario(usuario.getPersona().getTelefonoSecundario());
                ejPersona.setTipoPersona(usuario.getPersona().getTipoPersona());
                ejPersona.setDireccionParticular(usuario.getPersona().getDireccionParticular());
                ejPersona.setDireccionDetallada(usuario.getPersona().getDireccionDetallada());
                ejPersona.setObservacion(usuario.getPersona().getObservacion());
                ejPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejPersona.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());

                personaManager.update(ejPersona);

            } else {

                usuario.getPersona().setActivo("S");
                usuario.getPersona().setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                usuario.getPersona().setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                usuario.getPersona().setIdUsuarioCreacion(usuario.getIdUsuarioCreacion());
                usuario.getPersona().setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                usuario.getPersona().setSucursal(new Sucursales(usuario.getPersona().getSucursal().getId()));

                personaManager.save(usuario.getPersona());

                ejPersona = personaManager.get(usuario.getPersona());

                if (usuario.getPersona().getAvatar() != null) {
                    Boolean imagenBoolean = imagenManager.guardar(Base64Bytes.decode(usuario.getPersona().getAvatar().getValue()), usuario.getPersona().getAvatar().getFilename(),
                        usuario.getPersona().getAvatar().getFiletype(), usuario.getPersona().getClassName(), usuario.getPersona().getId(), usuario.getIdUsuarioCreacion(), usuario.getPersona().getSucursal().getId());
                }
            }

            usuario.setAlias(usuario.getAlias().toUpperCase());
            usuario.setPersona(new Personas(ejPersona.getId()));
            usuario.setRol(new Rol(usuario.getRol().getId()));
            //usuario.setSucursal(new Sucursales(usuario.getSucursal().getId()));

            this.save(usuario);

            object = this.get(usuario);
        }
        return object;
    }

    @Override
    public Usuarios editar(Usuarios usuario) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
