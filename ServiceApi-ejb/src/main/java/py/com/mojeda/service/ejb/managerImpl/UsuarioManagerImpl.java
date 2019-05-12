/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Documentos;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Rol;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.TipoDocumentos;
import py.com.mojeda.service.ejb.entity.Usuarios;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.UsuarioManager;
import py.com.mojeda.service.ejb.utils.Base64Bytes;
import py.com.mojeda.service.ejb.manager.DocumentoManager;
import py.com.mojeda.service.ejb.manager.TipoDocumentosManager;

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

    @EJB(mappedName = "java:app/ServiceApi-ejb/DocumentoManagerImpl")
    private DocumentoManager documentoManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/TipoDocumentosManagerImpl")
    private TipoDocumentosManager tipoDocumentosManager;

    @Override
    public Usuarios guardar(Usuarios usuario) throws Exception {
        Usuarios object = null;
        Documentos ejDocumentos = null;
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

            }

            if (usuario.getPersona().getAvatar() != null) {

                ejDocumentos = new Documentos();
                ejDocumentos.setActivo("S");
                ejDocumentos.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                ejDocumentos.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejDocumentos.setIdUsuarioCreacion(usuario.getIdUsuarioCreacion());
                ejDocumentos.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                ejDocumentos.setDocumento(Base64Bytes.decode(usuario.getPersona().getAvatar().getValue()));
                ejDocumentos.setNombreDocumento(usuario.getPersona().getAvatar().getFilename());
                ejDocumentos.setTipoArchivo(usuario.getPersona().getAvatar().getFiletype());
                ejDocumentos.setNombreTabla(usuario.getPersona().getClassName());
                ejDocumentos.setIdEntidad(usuario.getPersona().getId());
                ejDocumentos.setEmpresa(new Empresas(usuario.getPersona().getSucursal().getEmpresa().getId()));

                TipoDocumentos ejTipoDocumento = new TipoDocumentos();
                ejTipoDocumento.setCodigo("I-U-200");
                ejTipoDocumento = tipoDocumentosManager.get(ejTipoDocumento);

                ejDocumentos.setTipoDocumento(ejTipoDocumento);
                ejDocumentos = documentoManager.guardar(ejDocumentos);
            }
            
            usuario.setImagePath(ejDocumentos == null ? null : ejDocumentos.getPath());
            usuario.setAlias(usuario.getAlias().toUpperCase());
            usuario.setPersona(new Personas(ejPersona.getId()));
            usuario.setRol(new Rol(usuario.getRol().getId()));

            this.save(usuario);

            object = this.get(usuario);
        }
        return object;
    }

    @Override
    public Usuarios editar(Usuarios usuario) throws Exception {
        Usuarios object = null;
        Documentos ejDocumentos = null;
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

            }

            if (usuario.getPersona().getAvatar() != null) {

                ejDocumentos = new Documentos();
                ejDocumentos.setActivo("S");
                ejDocumentos.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                ejDocumentos.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejDocumentos.setIdUsuarioCreacion(usuario.getIdUsuarioCreacion());
                ejDocumentos.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                ejDocumentos.setDocumento(Base64Bytes.decode(usuario.getPersona().getAvatar().getValue()));
                ejDocumentos.setNombreDocumento(usuario.getPersona().getAvatar().getFilename());
                ejDocumentos.setTipoArchivo(usuario.getPersona().getAvatar().getFiletype());
                ejDocumentos.setNombreTabla(usuario.getPersona().getClassName());
                ejDocumentos.setIdEntidad(usuario.getPersona().getId());
                ejDocumentos.setEmpresa(new Empresas(usuario.getPersona().getSucursal().getEmpresa().getId()));

                TipoDocumentos ejTipoDocumento = new TipoDocumentos();
                ejTipoDocumento.setCodigo("I-U-200");
                ejTipoDocumento = tipoDocumentosManager.get(ejTipoDocumento);

                ejDocumentos.setTipoDocumento(ejTipoDocumento);
                ejDocumentos = documentoManager.editar(ejDocumentos);
            }
            
            usuario.setImagePath(ejDocumentos == null ? null : ejDocumentos.getPath());
            usuario.setAlias(usuario.getAlias().toUpperCase());
            usuario.setPersona(new Personas(ejPersona.getId()));
            usuario.setRol(new Rol(usuario.getRol().getId()));

            this.update(usuario);

            object = this.get(usuario);
        }
        return object;
    }
}
