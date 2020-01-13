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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.tomcat.util.codec.binary.Base64;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.EstadosSolicitud;
import py.com.mojeda.service.ejb.entity.EvaluacionSolicitudesCabecera;
import py.com.mojeda.service.ejb.entity.InformconfSolicitudes;
import py.com.mojeda.service.ejb.entity.Parametros;
import py.com.mojeda.service.ejb.entity.PropuestaSolicitud;
import py.com.mojeda.service.ejb.informconf.InformconfPersonas;
import py.com.mojeda.service.ejb.manager.EvaluacionSolicitudesCabeceraManager;
import py.com.mojeda.service.ejb.manager.InformconfSolicitudesManager;
import py.com.mojeda.service.ejb.manager.ParametrosManager;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;
import py.com.mojeda.service.ejb.utils.ResponseDTO;

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
    protected static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static final String ALGORITHM = "RSA";
    final long EXPIRATIONTIME = 50 * 60 * 1000; 		// 15 minutes

    @EJB(mappedName = "java:app/ServiceApi-ejb/ParametrosManagerImpl")
    private ParametrosManager parametrosManager;
    @EJB(mappedName = "java:app/ServiceApi-ejb/EvaluacionSolicitudesCabeceraManagerImpl")
    private EvaluacionSolicitudesCabeceraManager evaluacionSolicitudesCabeceraManager;

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
            stringBuilder.append(dateFormat.format(informconfSolicitudes.getFechaSolicitud()));
            stringBuilder.append("&codLote=");
            stringBuilder.append(informconfSolicitudes.getLote());
            stringBuilder.append("&estado=");
            stringBuilder.append(estado);
            stringBuilder.append("&idPersona=");
            stringBuilder.append(idPersona);

            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("codFuncionario", idFuncionario);
            claims.put("codEmpresa", parametros.getCodEmpresaInf());

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

                response.setStatus(200);
                response.setMessage("Solicitud procesada.");
                response.setModel(gson.fromJson(gson.toJson(responseService.getModel()), InformconfPersonas.class));
                
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
            stringBuilder.append(dateFormat.format(informconfSolicitudes.getFechaSolicitud()));
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
