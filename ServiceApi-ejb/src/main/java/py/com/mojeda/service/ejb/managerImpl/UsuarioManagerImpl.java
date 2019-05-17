/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.DepartamentosSucursal;
import py.com.mojeda.service.ejb.entity.Documentos;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Rol;
import py.com.mojeda.service.ejb.entity.Sucursales;
import py.com.mojeda.service.ejb.entity.TipoDocumentos;
import py.com.mojeda.service.ejb.entity.UsuarioDepartamentos;
import py.com.mojeda.service.ejb.entity.Usuarios;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.UsuarioManager;
import py.com.mojeda.service.ejb.utils.Base64Bytes;
import py.com.mojeda.service.ejb.manager.DocumentoManager;
import py.com.mojeda.service.ejb.manager.TipoDocumentosManager;
import py.com.mojeda.service.ejb.manager.UsuarioDepartamentosManager;
import static py.com.mojeda.service.ejb.utils.Constants.CONTENT_PATH;

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
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/UsuarioDepartamentosManagerImpl")
    private UsuarioDepartamentosManager usuarioDepartamentosManager;

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
                ejPersona.setFechaNacimiento(usuario.getPersona().getFechaNacimiento());
                ejPersona.setDireccionDetallada(usuario.getPersona().getDireccionDetallada());
                ejPersona.setObservacion(usuario.getPersona().getObservacion());
                ejPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejPersona.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                ejPersona.setSucursal(new Sucursales(usuario.getPersona().getSucursal().getId()));

                if (usuario.getPersona().getAvatar() != null) {

                    Files.createDirectories(Paths.get(CONTENT_PATH + ejPersona.getClassName() + "/" + ejPersona.getId()));
                    String path = ejPersona.getClassName() + "/" + ejPersona.getId() + "/" + usuario.getPersona().getAvatar().getFilename();
                    FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
                    fos.write(Base64Bytes.decode(usuario.getPersona().getAvatar().getValue()));
                    fos.close();

                    ejPersona.setImagePath(ejDocumentos == null ? null : ejDocumentos.getPath());
                }

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

                    Files.createDirectories(Paths.get(CONTENT_PATH + ejPersona.getClassName() + "/" + ejPersona.getId()));
                    String path = ejPersona.getClassName() + "/" + ejPersona.getId() + "/" + usuario.getPersona().getAvatar().getFilename();
                    FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
                    fos.write(Base64Bytes.decode(usuario.getPersona().getAvatar().getValue()));
                    fos.close();

                    ejPersona.setImagePath(ejDocumentos == null ? null : ejDocumentos.getPath());
                    
                    personaManager.update(ejPersona);
                }
            }

            usuario.setAlias(usuario.getAlias().toUpperCase());
            usuario.setPersona(new Personas(ejPersona.getId()));
            usuario.setRol(new Rol(usuario.getRol().getId()));

            this.save(usuario);

            object = this.get(usuario);
            UsuarioDepartamentos usuarioDepartamentos;
            for(Map<String,Object> rpm: usuario.getDepartamentos()){
                usuarioDepartamentos = new UsuarioDepartamentos();
                usuarioDepartamentos.setUsuario(usuario);
                usuarioDepartamentos.setDepartamento(new DepartamentosSucursal(Long.parseLong(rpm.get("id").toString())));
                
                usuarioDepartamentosManager.save(usuarioDepartamentos);
            }
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
                ejPersona.setFechaNacimiento(usuario.getPersona().getFechaNacimiento());
                ejPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejPersona.setIdUsuarioModificacion(usuario.getIdUsuarioModificacion());
                ejPersona.setSucursal(new Sucursales(usuario.getPersona().getSucursal().getId()));

                if (usuario.getPersona().getAvatar() != null) {

                    Files.createDirectories(Paths.get(CONTENT_PATH + ejPersona.getClassName() + "/" + ejPersona.getId()));
                    String path = ejPersona.getClassName() + "/" + ejPersona.getId() + "/" + usuario.getPersona().getAvatar().getFilename();
                    FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
                    fos.write(Base64Bytes.decode(usuario.getPersona().getAvatar().getValue()));
                    fos.close();

                    ejPersona.setImagePath(ejDocumentos == null ? null : ejDocumentos.getPath());
                }

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

                    Files.createDirectories(Paths.get(CONTENT_PATH + ejPersona.getClassName() + "/" + ejPersona.getId()));
                    String path = ejPersona.getClassName() + "/" + ejPersona.getId() + "/" + usuario.getPersona().getAvatar().getFilename();
                    FileOutputStream fos = new FileOutputStream(CONTENT_PATH + path);
                    fos.write(Base64Bytes.decode(usuario.getPersona().getAvatar().getValue()));
                    fos.close();

                    ejPersona.setImagePath(ejDocumentos == null ? null : ejDocumentos.getPath());
                    
                    personaManager.update(ejPersona);
                }

            }

            usuario.setAlias(usuario.getAlias().toUpperCase());
            usuario.setPersona(new Personas(ejPersona.getId()));
            usuario.setRol(new Rol(usuario.getRol().getId()));

            this.update(usuario);

            object = this.get(usuario);
            
            UsuarioDepartamentos usuarioDepartamentos = new UsuarioDepartamentos();
            usuarioDepartamentos.setUsuario(usuario);
            
            List<UsuarioDepartamentos> list = usuarioDepartamentosManager.list(usuarioDepartamentos);
            for(UsuarioDepartamentos rpc : list){
                usuarioDepartamentosManager.delete(rpc.getId());
            }
            
            for(Map<String,Object> rpm: usuario.getDepartamentos()){
                usuarioDepartamentos = new UsuarioDepartamentos();
                usuarioDepartamentos.setUsuario(usuario);
                usuarioDepartamentos.setDepartamento(new DepartamentosSucursal(Long.parseLong(rpm.get("id").toString())));
                
                usuarioDepartamentosManager.save(usuarioDepartamentos);
            }
        }
        return object;
    }
}
