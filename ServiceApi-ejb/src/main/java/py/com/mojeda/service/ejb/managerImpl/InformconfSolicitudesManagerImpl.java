/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.tomcat.util.codec.binary.Base64;
import py.com.mojeda.service.ejb.entity.Ciudades;
import py.com.mojeda.service.ejb.entity.DepartamentosPais;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.EstadosSolicitud;
import py.com.mojeda.service.ejb.entity.EvaluacionSolicitudesCabecera;
import py.com.mojeda.service.ejb.entity.InformconfSolicitudes;
import py.com.mojeda.service.ejb.entity.Nacionalidades;
import py.com.mojeda.service.ejb.entity.Paises;
import py.com.mojeda.service.ejb.entity.Parametros;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.Profesiones;
import py.com.mojeda.service.ejb.entity.PropuestaSolicitud;
import py.com.mojeda.service.ejb.informconf.InformconfPersonas;
import py.com.mojeda.service.ejb.manager.CiudadesManager;
import py.com.mojeda.service.ejb.manager.DepartamentosPaisManager;
import py.com.mojeda.service.ejb.manager.EvaluacionSolicitudesCabeceraManager;
import py.com.mojeda.service.ejb.manager.InformconfSolicitudesManager;
import py.com.mojeda.service.ejb.manager.NacionalidadesManager;
import py.com.mojeda.service.ejb.manager.PaisesManager;
import py.com.mojeda.service.ejb.manager.ParametrosManager;
import py.com.mojeda.service.ejb.manager.PersonaManager;
import py.com.mojeda.service.ejb.manager.ProfesionesManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;
import py.com.mojeda.service.ejb.utils.ResponseDTO;
import py.com.mojeda.service.ejb.utils.Utils;

/**
 *
 * @author Miguel
 */
@Stateless
public class InformconfSolicitudesManagerImpl extends GenericDaoImpl<InformconfSolicitudes, Long>
        implements InformconfSolicitudesManager {

    @Override
    protected Class<InformconfSolicitudes> getEntityBeanType() {
        return InformconfSolicitudes.class;
    }
    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();
    protected static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    protected static final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    public static final String ALGORITHM = "RSA";
    final long EXPIRATIONTIME = 50 * 60 * 1000; 		// 15 minutes

    @EJB(mappedName = "java:app/ServiceApi-ejb/ParametrosManagerImpl")
    private ParametrosManager parametrosManager;
    @EJB(mappedName = "java:app/ServiceApi-ejb/EvaluacionSolicitudesCabeceraManagerImpl")
    private EvaluacionSolicitudesCabeceraManager evaluacionSolicitudesCabeceraManager;
    @EJB(mappedName = "java:app/ServiceApi-ejb/PersonaManagerImpl")
    private PersonaManager personaManager;
    @EJB(mappedName = "java:app/ServiceApi-ejb/NacionalidadesManagerImpl")
    private NacionalidadesManager nacionalidadesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/PaisesManagerImpl")
    private PaisesManager paisesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/DepartamentosPaisManagerImpl")
    private DepartamentosPaisManager departamentosPaisManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/CiudadesManagerImpl")
    private CiudadesManager ciudadesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/ProfesionesManagerImpl")
    private ProfesionesManager profesionesManager;

    @Override
    public ResponseDTO<InformconfPersonas> get(String tipo, String documento, Long nroSolicitud, Long idPersona,
            Long idEmpresa, Long idFuncionario, String estado, boolean reload) throws Exception {

        ResponseDTO<InformconfPersonas> response = new ResponseDTO<>();
        InformconfSolicitudes informconfSolicitudes;
        Gson gson = new Gson();
        try {
            //NO ES NECESARIO VOLVER A LLAMAR AL SERVICIO
            logger.info("********************* REGISTRO INFORMCONF ***********************");
            logger.info("nroSolicitud : " + nroSolicitud);
            logger.info("tipo : " + tipo);
            informconfSolicitudes = new InformconfSolicitudes();
            informconfSolicitudes.setIdSolicitud(nroSolicitud);
            informconfSolicitudes.setTipoSolicitud(tipo);
            informconfSolicitudes.setEmpresa(new Empresas(idEmpresa));

            informconfSolicitudes = this.get(informconfSolicitudes);

            if (!reload) {
                if (informconfSolicitudes != null) {

                    response.setStatus(200);
                    response.setMessage("Solicitud procesada.");
                    response.setModel(gson.fromJson(informconfSolicitudes.getModel(), InformconfPersonas.class));

                    return response;
                }
            }

            if (informconfSolicitudes == null) {
                informconfSolicitudes = new InformconfSolicitudes();
                informconfSolicitudes.setIdSolicitud(nroSolicitud);
                informconfSolicitudes.setTipoSolicitud(tipo);
                informconfSolicitudes.setActivo("S");
                informconfSolicitudes.setDocumento(documento);
                informconfSolicitudes.setIdUsuarioCreacion(idFuncionario);
                informconfSolicitudes.setIdUsuarioModificacion(idFuncionario);
                informconfSolicitudes.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                informconfSolicitudes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                informconfSolicitudes.setFechaSolicitud(new Timestamp(System.currentTimeMillis()));
                informconfSolicitudes.setLote(informconfSolicitudes.getFechaSolicitud().getTime() + "-" + nroSolicitud);
                informconfSolicitudes.setEmpresa(new Empresas(idEmpresa));
            }

            Parametros parametros = parametrosManager.get(new Parametros(new Empresas(idEmpresa)));

            StringBuilder stringBuilder = new StringBuilder(parametros.getUrl1() + "/servicios/persona-fisica");
            stringBuilder.append("?documento=");
            stringBuilder.append(documento);
            stringBuilder.append("&fechaConsulta=");
            stringBuilder.append(informconfSolicitudes.getFechaSolicitud().getTime());
            stringBuilder.append("&codLote=");
            stringBuilder.append(informconfSolicitudes.getLote());
            stringBuilder.append("&estado=");
            stringBuilder.append(estado);
            stringBuilder.append("&idPersona=");
            stringBuilder.append(idPersona);

            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("codFuncionario", idFuncionario);
            claims.put("codEmpresa", parametros.getCodEmpresaInf());

            logger.info("URL: " + stringBuilder.toString());

            URL restServiceURL = new URL(stringBuilder.toString());
            HttpURLConnection connection = (HttpURLConnection) restServiceURL.openConnection();
            //URLConnection connection = restServiceURL.openConnection();
            connection.setDoOutput(false);
            connection.setRequestProperty("Authorization", "Bearer-" + parametros.getCodAfiliado() + " " + generate(claims, idEmpresa));
            connection.setRequestProperty("Accept", "*/*");
            connection.setUseCaches(false);
//            connection.setHostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession sslSession) {
//                    return true;
//                }
//            });

            BufferedReader responseBuffer;
            String output;
            StringBuilder sb = new StringBuilder();
            logger.info("getResponseCode: " + connection.getResponseCode());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                responseBuffer = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                while ((output = responseBuffer.readLine()) != null) {
                    sb.append(output);
                }

                logger.info("RESPONSE: " + sb.toString());
                ResponseDTO<InformconfPersonas> responseService = gson.fromJson(sb.toString(), ResponseDTO.class);

                if (responseService.getStatus() == 200) {
                    if (informconfSolicitudes.getId() == null) {
                        informconfSolicitudes.setModel(gson.toJson(responseService.getModel()));
                        this.save(informconfSolicitudes);
                    } else {
                        informconfSolicitudes.setModel(gson.toJson(responseService.getModel()));
                        informconfSolicitudes.setIdUsuarioModificacion(idFuncionario);
                        informconfSolicitudes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        this.update(informconfSolicitudes);
                    }

                }

                InformconfPersonas registros = gson.fromJson(gson.toJson(responseService.getModel()), InformconfPersonas.class);

                Collections.sort(registros.getSolicitudesDetalle(), Utils.fechaInformconf);

                response.setStatus(200);
                response.setMessage("Solicitud procesada.");
                response.setModel(registros);

            } else {
                response.setStatus(305);
                response.setMessage("Error al procesar instruccion en el cliente.");
            }

        } catch (Exception e) {
            logger.error("error" + e);
            response.setStatus(300);
            response.setMessage("Error al procesar instruccion.");
            //e.printStackTrace();
        }
        logger.info("RESPONSE: " + gson.toJson(response));
        return response;
    }

    @Override
    public ResponseDTO<InformconfPersonas> getReport(String tipoReporte, String tipoDocumento, String documento, Long idEmpresa, Long idFuncionario, String estado) throws Exception {
        ResponseDTO<InformconfPersonas> response = new ResponseDTO<>();
        List<String> atributoInicio = new ArrayList<>();
        atributoInicio.add("fechaSolicitud");
        List<Object> valorInicio = new ArrayList<>();
        InformconfSolicitudes informconfSolicitudes;
        Gson gson = new Gson();
        try {
            //VERIFICAR SI EL REGISTRO YA NO FUE CONSULTADO   
            LocalDate localDate = LocalDate.now().minusDays(5);
            valorInicio.add(java.util.Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

            logger.info("********************* REGISTRO INFORMCONF ***********************");
            logger.info("tipo : " + tipoReporte);

            informconfSolicitudes = new InformconfSolicitudes();
            informconfSolicitudes.setDocumento(documento);
            informconfSolicitudes.setEmpresa(new Empresas(idEmpresa));

            List<InformconfSolicitudes> listObject = this.list(informconfSolicitudes, true,
                    0, 0, "id".split(","), "desc".split(","), true, true,
                    null, null, null, atributoInicio, valorInicio, null, null, null, true);

            if (!listObject.isEmpty()) {
                response.setStatus(200);
                response.setMessage("Solicitud procesada.");
                response.setModel(gson.fromJson(listObject.get(0).getModel(), InformconfPersonas.class));

                return response;
            }

            informconfSolicitudes = new InformconfSolicitudes();
            informconfSolicitudes.setTipoSolicitud(tipoReporte);
            informconfSolicitudes.setActivo("S");
            informconfSolicitudes.setDocumento(documento);
            informconfSolicitudes.setIdUsuarioCreacion(idFuncionario);
            informconfSolicitudes.setIdUsuarioModificacion(idFuncionario);
            informconfSolicitudes.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            informconfSolicitudes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            informconfSolicitudes.setFechaSolicitud(new Timestamp(System.currentTimeMillis()));
            informconfSolicitudes.setLote(informconfSolicitudes.getFechaSolicitud().getTime() + "-" + tipoReporte);
            informconfSolicitudes.setEmpresa(new Empresas(idEmpresa));

            Parametros parametros = parametrosManager.get(new Parametros(new Empresas(idEmpresa)));

            StringBuilder stringBuilder = new StringBuilder(parametros.getUrl1() + "/servicios/persona-fisica");
            stringBuilder.append("?documento=");
            stringBuilder.append(documento);
            stringBuilder.append("&fechaConsulta=");
            stringBuilder.append(informconfSolicitudes.getFechaSolicitud().getTime());
            stringBuilder.append("&codLote=");
            stringBuilder.append(informconfSolicitudes.getLote());
            stringBuilder.append("&estado=");
            stringBuilder.append(estado);
            stringBuilder.append("&idPersona=");
            stringBuilder.append(0L);

            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("codFuncionario", idFuncionario);
            claims.put("codEmpresa", parametros.getCodEmpresaInf());

            logger.info("URL: " + stringBuilder.toString());

            URL restServiceURL = new URL(stringBuilder.toString());
            HttpURLConnection connection = (HttpURLConnection) restServiceURL.openConnection();
            //URLConnection connection = restServiceURL.openConnection();
            connection.setDoOutput(false);
            connection.setRequestProperty("Authorization", "Bearer-" + parametros.getCodAfiliado() + " " + generate(claims, idEmpresa));
            connection.setRequestProperty("Accept", "*/*");
            connection.setUseCaches(false);

            BufferedReader responseBuffer;
            String output;
            StringBuilder sb = new StringBuilder();
            logger.info("getResponseCode: " + connection.getResponseCode());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                responseBuffer = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                while ((output = responseBuffer.readLine()) != null) {
                    sb.append(output);
                }

                logger.info("RESPONSE: " + sb.toString());
                ResponseDTO<InformconfPersonas> responseService = gson.fromJson(sb.toString(), ResponseDTO.class);

                if (responseService.getStatus() == 200) {
                    if (informconfSolicitudes.getId() == null) {
                        informconfSolicitudes.setModel(gson.toJson(responseService.getModel()));
                        this.save(informconfSolicitudes);
                    } else {
                        informconfSolicitudes.setModel(gson.toJson(responseService.getModel()));
                        informconfSolicitudes.setIdUsuarioModificacion(idFuncionario);
                        informconfSolicitudes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                        this.update(informconfSolicitudes);
                    }

                }

                InformconfPersonas registros = gson.fromJson(gson.toJson(responseService.getModel()), InformconfPersonas.class);

                //CARGAR LA PERSONA
                Personas ejPersona = new Personas();
                ejPersona.setDocumento(documento);
                if (personaManager.total(ejPersona) == 0) {
                    logger.info("***************** INSERTAR PERSONA *******************");
                    this.insertarPersona(registros, idFuncionario, idEmpresa);
                }

                Collections.sort(registros.getSolicitudesDetalle(), Utils.fechaInformconf);

                response.setStatus(200);
                response.setMessage("Solicitud procesada.");
                response.setModel(registros);

            } else {
                response.setStatus(305);
                response.setMessage("Error al procesar instruccion en el cliente.");
            }

        } catch (Exception e) {
            logger.error("error" + e);
            response.setStatus(300);
            response.setMessage("Error al procesar instruccion.");
            //e.printStackTrace();
        }
        logger.info("RESPONSE: " + gson.toJson(response));
        return response;
    }

    @Override
    @Asynchronous
    public void asyncService(String tipo, String documento, Long nroSolicitud, Long idPersona, Long idEmpresa,
            Long idFuncionario, String estado, Long idEvaluacion) throws Exception {
        InformconfSolicitudes informconfSolicitudes;
        Gson gson = new Gson();
        try {
            logger.info("***************** INICIAR REPORTE INFORMCONF *******************");
            //NO ES NECESARIO VOLVER A LLAMAR AL SERVICIO
            informconfSolicitudes = new InformconfSolicitudes();
            informconfSolicitudes.setIdSolicitud(nroSolicitud);
            informconfSolicitudes.setTipoSolicitud(tipo);
            informconfSolicitudes.setEmpresa(new Empresas(idEmpresa));

            informconfSolicitudes = this.get(informconfSolicitudes);

            if (informconfSolicitudes == null) {
                informconfSolicitudes = new InformconfSolicitudes();
                informconfSolicitudes.setIdSolicitud(nroSolicitud);
                informconfSolicitudes.setTipoSolicitud(tipo);
                informconfSolicitudes.setActivo("S");
                informconfSolicitudes.setDocumento(documento);
                informconfSolicitudes.setIdUsuarioCreacion(idFuncionario);
                informconfSolicitudes.setIdUsuarioModificacion(idFuncionario);
                informconfSolicitudes.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                informconfSolicitudes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                informconfSolicitudes.setFechaSolicitud(new Timestamp(System.currentTimeMillis()));
                informconfSolicitudes.setLote(informconfSolicitudes.getFechaSolicitud().getTime() + "-" + nroSolicitud);
                informconfSolicitudes.setEmpresa(new Empresas(idEmpresa));
            }

            Parametros parametros = parametrosManager.get(new Parametros(new Empresas(idEmpresa)));

            StringBuilder stringBuilder = new StringBuilder(parametros.getUrl1() + "/servicios/persona-fisica");
            stringBuilder.append("?documento=");
            stringBuilder.append(documento);
            stringBuilder.append("&fechaConsulta=");
            stringBuilder.append(informconfSolicitudes.getFechaSolicitud().getTime());
            stringBuilder.append("&codLote=");
            stringBuilder.append(informconfSolicitudes.getLote());
            stringBuilder.append("&estado=");
            stringBuilder.append(estado);
            stringBuilder.append("&idPersona=");
            stringBuilder.append(idPersona);

            try {
                URL restServiceURL = new URL(stringBuilder.toString());
                HttpURLConnection connection = (HttpURLConnection) restServiceURL.openConnection();

                Map<String, Object> claims = new HashMap<String, Object>();
                claims.put("codFuncionario", idFuncionario);
                claims.put("codEmpresa", parametros.getCodEmpresaInf());

                connection.setDoOutput(false);
                connection.setReadTimeout(parametros.getTimeout().intValue());
                connection.setConnectTimeout(parametros.getTimeout().intValue());
                connection.setRequestProperty("Authorization", "Bearer-" + parametros.getCodAfiliado() + " " + generate(claims, idEmpresa));
                connection.setRequestProperty("Accept", "*/*");
                connection.setUseCaches(false);
//            connection.setHostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession sslSession) {
//                    return true;
//                }
//            });

                BufferedReader responseBuffer;
                String output;
                StringBuilder sb = new StringBuilder();

                logger.info("getResponseCode() : " + connection.getResponseCode());

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    responseBuffer = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                    while ((output = responseBuffer.readLine()) != null) {
                        sb.append(output);
                    }

                    logger.info("RESPONSE: " + sb.toString());
                    ResponseDTO<InformconfPersonas> responseService = gson.fromJson(sb.toString(), ResponseDTO.class);

                    if (responseService.getStatus() == 200) {
                        if (informconfSolicitudes.getId() == null) {
                            informconfSolicitudes.setModel(gson.toJson(responseService.getModel()));
                            this.save(informconfSolicitudes);
                        } else {
                            informconfSolicitudes.setModel(gson.toJson(responseService.getModel()));
                            informconfSolicitudes.setIdUsuarioModificacion(idFuncionario);
                            informconfSolicitudes.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                            this.update(informconfSolicitudes);
                        }

                    }
                }
            } catch (Exception e) {
                logger.error("Exception " + e);
                e.printStackTrace();
            }

            //ACTUALIZAR ESTADO PARA ANALIZAR
            EvaluacionSolicitudesCabecera evaluacionCabecera = evaluacionSolicitudesCabeceraManager.get(idEvaluacion);
            evaluacionCabecera.setEstado(new EstadosSolicitud(1L));

            evaluacionSolicitudesCabeceraManager.update(evaluacionCabecera);
        } catch (Exception e) {
            logger.error("Exception " + e);
            e.printStackTrace();
        }
    }

    public void insertarPersona(InformconfPersonas reporteInformconf, Long idFuncionario, Long idEmpresa) throws Exception {
        try {
            if (reporteInformconf != null
                    && reporteInformconf.getDatosPersonales() != null) {

                if (reporteInformconf.getDatosPersonales().getPrimerNombre() != null
                        && reporteInformconf.getDatosPersonales().getPrimerApellido() != null) {

                    Personas ejPersona = new Personas();
                    ejPersona.setTipoPersona("FISICA");
                    ejPersona.setDocumento(reporteInformconf.getDatosPersonales().getCedulaIdentidad().replace(".", ""));
                    ejPersona.setPrimerNombre(reporteInformconf.getDatosPersonales().getPrimerNombre());
                    ejPersona.setSegundoNombre(reporteInformconf.getDatosPersonales().getSegundoNombre());
                    ejPersona.setPrimerApellido(reporteInformconf.getDatosPersonales().getPrimerApellido());
                    ejPersona.setSegundoApellido(reporteInformconf.getDatosPersonales().getSegundoApellido());

                    if (reporteInformconf.getDatosPersonales().getFechaNacimiento() != null
                            && reporteInformconf.getDatosPersonales().getFechaNacimiento().contains("/")) {
                        ejPersona.setFechaNacimiento(new Timestamp(format.parse(reporteInformconf.getDatosPersonales().getFechaNacimiento()).getTime()));

                    } else {
                        ejPersona.setFechaNacimiento(new Timestamp(System.currentTimeMillis()));
                    }

                    if (reporteInformconf.getDatosPersonales().getEstadoCivil() != null) {
                        if (reporteInformconf.getDatosPersonales().getEstadoCivil().equalsIgnoreCase("Soltero(a)")) {
                            ejPersona.setEstadoCivil("SOLTERO/A");
                        } else if (reporteInformconf.getDatosPersonales().getEstadoCivil().equalsIgnoreCase("Casado(a)")) {
                            ejPersona.setEstadoCivil("CASADO/A");
                        } else if (reporteInformconf.getDatosPersonales().getEstadoCivil().equalsIgnoreCase("Divorciado(a)")) {
                            ejPersona.setEstadoCivil("DIVORCIADO/A");
                        } else if (reporteInformconf.getDatosPersonales().getEstadoCivil().equalsIgnoreCase("Separado(a) de bienes")) {
                            ejPersona.setEstadoCivil("CASADO/A");
                        } else if (reporteInformconf.getDatosPersonales().getEstadoCivil().equalsIgnoreCase("Otros")) {
                            ejPersona.setEstadoCivil("OTROS");
                        } else {
                            ejPersona.setEstadoCivil("SOLTERO/A");
                        }
                    } else {
                        ejPersona.setEstadoCivil("SOLTERO/A");
                    }

                    if (reporteInformconf.getDatosPersonales().getSexo() != null) {
                        if (reporteInformconf.getDatosPersonales().getSexo().equalsIgnoreCase("Masculino")) {
                            ejPersona.setSexo("MASCULINO");
                        } else {
                            ejPersona.setSexo("FEMENINO");
                        }
                    } else {
                        ejPersona.setSexo("MASCULINO");
                    }

                    if (reporteInformconf.getDatosPersonales().getNacionalidad() != null
                            && reporteInformconf.getDatosPersonales().getNacionalidad().compareToIgnoreCase("") != 0) {

                        Nacionalidades nacionalidad = new Nacionalidades();
                        nacionalidad.setNombre(reporteInformconf.getDatosPersonales().getNacionalidad());

                        Map<String, Object> nacMap = nacionalidadesManager.getAtributos(nacionalidad, "id".split(","), true, true);

                        if (nacMap != null) {
                            ejPersona.setNacionalidad(new Nacionalidades(Long.parseLong(nacMap.get("id").toString())));
                        } else {
                            ejPersona.setNacionalidad(new Nacionalidades(2L));
                        }
                    } else {
                        ejPersona.setNacionalidad(new Nacionalidades(2L));
                    }

                    if (reporteInformconf.getDatosPersonales().getProfesion() != null
                            && reporteInformconf.getDatosPersonales().getProfesion().compareToIgnoreCase("") != 0) {

                        Profesiones profesion = new Profesiones();
                        profesion.setNombre(reporteInformconf.getDatosPersonales().getProfesion());

                        Map<String, Object> nacMap = profesionesManager.getAtributos(profesion, "id".split(","), true, true);

                        if (nacMap != null) {
                            ejPersona.setProfesion(new Profesiones(Long.parseLong(nacMap.get("id").toString())));
                        }
                    }

                    if (reporteInformconf.getUltimaDireccion() != null) {
                        if (reporteInformconf.getUltimaDireccion().getCiudad() != null
                                && reporteInformconf.getUltimaDireccion().getCiudad().compareToIgnoreCase("") != 0) {

                            Ciudades ciudad = new Ciudades();
                            ciudad.setNombre(reporteInformconf.getUltimaDireccion().getCiudad());

                            ciudad = ciudadesManager.get(ciudad);

                            if (ciudad != null) {
                                ejPersona.setPais(ciudad.getDepartamentoPais().getPais());
                                ejPersona.setDepartamento(new DepartamentosPais(ciudad.getDepartamentoPais().getId()));
                                ejPersona.setCiudad(ciudad);
                                ejPersona.setDireccionParticular(reporteInformconf.getUltimaDireccion().getCalle() == null ? "SIN DATO" : reporteInformconf.getUltimaDireccion().getCalle());
                                ejPersona.setTelefonoParticular(reporteInformconf.getUltimaDireccion().getTelefono());
                            } else {
                                ejPersona.setPais(new Paises(999L));
                                ejPersona.setDepartamento(new DepartamentosPais(999999L));
                                ejPersona.setCiudad(new Ciudades(999999999999L));
                                ejPersona.setDireccionParticular("SIN DATO");
                            }
                        } else {
                            ejPersona.setPais(new Paises(999L));
                            ejPersona.setDepartamento(new DepartamentosPais(999999L));
                            ejPersona.setCiudad(new Ciudades(999999999999L));
                            ejPersona.setDireccionParticular(reporteInformconf.getUltimaDireccion().getCalle() == null ? "SIN DATO" : reporteInformconf.getUltimaDireccion().getCalle());
                            ejPersona.setTelefonoParticular(reporteInformconf.getUltimaDireccion().getTelefono());
                        }
                    } else {
                        ejPersona.setPais(new Paises(999L));
                        ejPersona.setDepartamento(new DepartamentosPais(999999L));
                        ejPersona.setCiudad(new Ciudades(999999999999L));
                        ejPersona.setDireccionParticular("SIN DATO");
                    }

                    ejPersona.setActivo("S");
                    ejPersona.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                    ejPersona.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                    ejPersona.setIdUsuarioCreacion(idFuncionario);
                    ejPersona.setIdUsuarioModificacion(idFuncionario);
                    ejPersona.setEmpresa(new Empresas(idEmpresa));

                    personaManager.save(ejPersona);

                }

            }
        } catch (Exception e) {
            logger.error("Exception " + e);
        }
    }

    public String generate(Map<String, Object> claims, Long idEmpresa) throws Exception {
        Parametros parametros = parametrosManager.get(new Parametros(new Empresas(idEmpresa)));
        KeyPair kp = this.createKeyPairFromEncodedKeys(Base64.decodeBase64(parametros.getPublicKey()), Base64.decodeBase64(parametros.getPrivateKey()));
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "RSA");
        header.put("typ", "JWT");
        //TOKENS
        String JWT = Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.RS256, kp.getPrivate())
                .compact();
        return JWT;

    }

    public static KeyPair createKeyPairFromEncodedKeys(byte[] publicKeyBytes, byte[] privateKeyBytes) throws NoSuchAlgorithmException {
        KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
        // Generate specs
        final X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        final PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        try {
            // Create PublicKey and PrivateKey interfaces using the factory
            final PrivateKey privateKey = kf.generatePrivate(privateKeySpec);
            final PublicKey publicKey = kf.generatePublic(publicKeySpec);

            return new KeyPair(publicKey, privateKey);
        } catch (InvalidKeySpecException ex) {

        }
        return null;
    }

}
