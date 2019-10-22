/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import com.google.gson.Gson;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import net.sourceforge.tess4j.Tesseract;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Documentos;
import static py.com.mojeda.service.ejb.utils.Constants.CONTENT_PATH;
import py.com.mojeda.service.ejb.utils.ResponseDTO;
import py.com.mojeda.service.ejb.utils.ResponseListDTO;
import py.com.mojeda.service.web.spring.config.User;

/**
 *
 * @author Miguel
 */
@Controller
@RequestMapping(value = "/archivos")
public class ArchivoController extends BaseController {

    @RequestMapping(
            value = ("/upload"),
            headers = "content-type=multipart/form-data",
            method = RequestMethod.POST)
    public @ResponseBody
    ResponseDTO uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("dataType") String documento) {
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        Gson gson = new Gson();
        try {
            inicializarDocumentoManager();

            Documentos ejDocumento = gson.fromJson(documento, Documentos.class);

            if (ejDocumento.getId() != null) {

                Documentos documentoUpdate = documentoManager.get(ejDocumento.getId());

                documentoUpdate.setIdUsuarioModificacion(userDetail.getId());
                documentoUpdate.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                documentoUpdate.setTipoDocumento(ejDocumento.getTipoDocumento());

                documentoManager.update(ejDocumento);

            } else {
                
                ejDocumento.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
                ejDocumento.setActivo("S");
                ejDocumento.setIdUsuarioCreacion(userDetail.getId());
                ejDocumento.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                ejDocumento.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
                ejDocumento.setTipoArchivo(file.getContentType());
                ejDocumento.setNombreDocumento(file.getOriginalFilename());
                ejDocumento.setSize(file.getSize());

                Files.createDirectories(Paths.get(CONTENT_PATH + ejDocumento.getEntidad() + "/" + ejDocumento.getIdEntidad() + "/" + ejDocumento.getTipoDocumento().getCodigo()));
                String path = ejDocumento.getEntidad() + "/" + ejDocumento.getIdEntidad() + "/" + ejDocumento.getTipoDocumento().getCodigo() + "/" + file.getOriginalFilename();

                File fos = new File(CONTENT_PATH + path);
                //Reducir Calidad Imagen en un 30%
                if (ejDocumento.getTipoArchivo().compareToIgnoreCase("image/jpeg") == 0) {
                    file.transferTo(fos);
                    
                    fos = this.reduceImageQuality(120000, CONTENT_PATH + path , CONTENT_PATH + path);
//                    BufferedImage originalImage = ImageIO.read(file.getInputStream());
//
//                    Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");
//                    ImageWriter writer = (ImageWriter) iter.next();
//                    ImageWriteParam iwp = writer.getDefaultWriteParam();
//
//                    iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//                    float quality = 0.5f;  // reduce quality by 30%  
//                    iwp.setCompressionQuality(quality);
//
//                    FileImageOutputStream output = new FileImageOutputStream(fos);
//                    writer.setOutput(output);
//
//                    IIOImage image = new IIOImage(originalImage, null, null);
//                    writer.write(null, image, iwp);
//                    writer.dispose();
//
//                    fos = new File(CONTENT_PATH + path);
                    ejDocumento.setSize(fos.length());
                } else {
                    file.transferTo(fos);
                }

                ejDocumento.setArchivo(null);
                ejDocumento.setPath(CONTENT_PATH + path);

                documentoManager.save(ejDocumento);
            }

            ejDocumento = documentoManager.get(ejDocumento.getId());

//            Tesseract tesseract = new Tesseract();
//            tesseract.setDatapath("/image/");
//            
//            String text = tesseract.doOCR(new File(ejDocumento.getPath()));
//            logger.info("TEXTO IMAGEN: " + text);
            response.setModel(ejDocumento);
            response.setStatus(ejDocumento == null ? 404 : 200);
            response.setMessage(ejDocumento == null ? "No se pudo guardar el registro." : "Registro creado/modificado con exito");

        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }
        return response;
    }

    @GetMapping("/{entidad}/{entidadId}")
    public @ResponseBody
    ResponseListDTO listar(
            @ModelAttribute("entidadId") Long id,
            @ModelAttribute("entidad") String entidad) {

        ResponseListDTO retorno = new ResponseListDTO();
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        //Buscar por empresa
        Documentos ejDocumentos = new Documentos();
        ejDocumentos.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
        ejDocumentos.setEntidad(entidad);
        ejDocumentos.setIdEntidad(id);
        ejDocumentos.setActivo("S");

        List<Documentos> listMapGrupos = null;
        try {
            inicializarDocumentoManager();

            Gson gson = new Gson();
            String camposFiltros = null;
            String valorFiltro = null;

            listMapGrupos = documentoManager.list(ejDocumentos);

            retorno.setRecords(Long.parseLong(listMapGrupos.size() + ""));
            retorno.setRows(listMapGrupos);
            retorno.setStatus(200);
            retorno.setMessage("OK");

        } catch (Exception e) {
            logger.error("Error: ", e);
            retorno.setStatus(500);
            retorno.setMessage("Error interno del servidor.");
        }

        return retorno;
    }

    /**
     * Mapping para el metodo PUT de la vista actualizar.(actualizar Documentos)
     *
     * @param id de la entidad
     * @param model entidad Documentos recibida de la vista
     * @param errors
     * @return
     */
    @PutMapping("/{id}")
    public @ResponseBody
    ResponseDTO update(
            @ModelAttribute("id") Long id,
            @RequestBody @Valid Documentos model,
            Errors errors) {

        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarDocumentoManager();

            Documentos documentoUpdate = documentoManager.get(model.getId());

            documentoUpdate.setIdUsuarioModificacion(userDetail.getId());
            documentoUpdate.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
            documentoUpdate.setTipoDocumento(model.getTipoDocumento());

            documentoManager.update(model);

            model = documentoManager.get(model.getId());

            response.setModel(model);
            response.setStatus(200);
            response.setMessage("Registro modificado con exito");
        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }

    /**
     * Mapping para el metodo GET de la vista visualizar.(visualizar Usuario)
     *
     * @param id de la entidad
     * @return
     */
    @GetMapping("/{id}")
    public @ResponseBody
    ResponseDTO getObject(
            @ModelAttribute("id") Long id) {
        //User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarDocumentoManager();

            List<Map<String, Object>> model = documentoManager.listAtributos(new Documentos(id), "path,tipoArchivo".split(","));
            List<String> listBase64 = new ArrayList<>();
            for (Map<String, Object> rpc : model) {
                if (rpc.get("tipoArchivo").toString().compareToIgnoreCase("image/jpeg") == 0) {

                }
                File f = new File(rpc.get("path").toString());

                String base64 = encodeFileToBase64Binary(f);
                listBase64.add(base64);
            }

            response.setModel(listBase64);
            response.setStatus(listBase64.size() == 0 ? 404 : 200);
            response.setMessage(listBase64.size() == 0 ? "Registro no encontrado" : "Registro encontrado");

            return response;
        } catch (Exception e) {
            logger.error("Error: ", e);
        }

        return null;
    }

    /**
     * Mapping para el metodo GET de la vista visualizar.(visualizar Usuario)
     *
     * @param id de la entidad
     * @param entidad
     * @return
     */
    @GetMapping("/all/{entidad}/{idEntidad}")
    public @ResponseBody
    ResponseDTO getObjectAll(
            @ModelAttribute("idEntidad") Long id,
            @ModelAttribute("entidad") String entidad) {
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarDocumentoManager();
            //Buscar por empresa
            Documentos ejDocumentos = new Documentos();
            ejDocumentos.setEmpresa(new Empresas(userDetail.getIdEmpresa()));
            ejDocumentos.setEntidad(entidad);
            ejDocumentos.setIdEntidad(id);
            ejDocumentos.setActivo("S");
            List<String> listBase64 = new ArrayList<>();
            List<Map<String, Object>> model = documentoManager.listAtributos(ejDocumentos, "path".split(","));
            for (Map<String, Object> rpc : model) {
                File f = new File(rpc.get("path").toString());
                String base64 = encodeFileToBase64Binary(f);
                listBase64.add(base64);
            }

            response.setModel(listBase64);
            response.setStatus(listBase64.size() == 0 ? 404 : 200);
            response.setMessage(listBase64.size() == 0 ? "Registro no encontrado" : "Registro encontrado");

            return response;
        } catch (Exception e) {
            logger.error("Error: ", e);
        }

        return null;
    }

    /**
     * Mapping para el metodo DELETE de la vista.(eliminar Cliente)
     *
     * @param id de la entidad
     * @return
     */
    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseDTO deleteObject(
            @ModelAttribute("id") Long id) {
        User userDetail = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ResponseDTO response = new ResponseDTO();
        try {
            inicializarDocumentoManager();

            Documentos model = documentoManager.get(id);
            model.setActivo("N");
            model.setIdUsuarioEliminacion(userDetail.getId());
            model.setFechaEliminacion(new Timestamp(System.currentTimeMillis()));

            documentoManager.update(model);

            response.setModel(model);
            response.setStatus(200);
            response.setMessage("Registro eliminado con exito.");
        } catch (Exception e) {
            logger.error("Error: ", e);
            response.setStatus(500);
            response.setMessage("Error interno del servidor.");
        }

        return response;
    }

    private static String encodeFileToBase64Binary(File file) {
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }

    public static File convertirImagen(byte[] bytes, Integer width) {

        File file = null;

        try {
            Image image = ImageIO.read(new ByteArrayInputStream(bytes));

            int height;
            if (width != null && width < image.getWidth(null)) {
                double aspectRatio = (double) image.getWidth(null) / (double) image.getHeight(null);
                height = (int) (width / aspectRatio);
            } else {
                width = image.getWidth(null);
                height = image.getHeight(null);
            }

            final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            final Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.setComposite(AlphaComposite.Src);
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.drawImage(image, 0, 0, width, height, null);
            graphics2D.dispose();

            file = File.createTempFile("temp", ".tmp");
            ImageIO.write(bufferedImage, "jpg", file);

            return file;

        } catch (IOException ex) {
            logger.error("convertir imagen", ex);
            return null;
        } finally {
            file.deleteOnExit();
        }
    }

    public File reduceImageQuality(int sizeThreshold, String srcImg, String destImg) throws Exception {
        float quality = 1.0f;

        File file = new File(srcImg);

        long fileSize = file.length();

        if (fileSize <= sizeThreshold) {
            System.out.println("Image file size is under threshold");
            return file;
        }

        Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");

        ImageWriter writer = (ImageWriter) iter.next();

        ImageWriteParam iwp = writer.getDefaultWriteParam();

        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

        FileInputStream inputStream = new FileInputStream(file);

        BufferedImage originalImage = ImageIO.read(inputStream);
        IIOImage image = new IIOImage(originalImage, null, null);

        float percent = 0.1f;   // 10% of 1  
        File fileOut2 = null;
        while (fileSize > sizeThreshold) {
            if (percent >= quality) {
                percent = percent * 0.1f;
            }

            quality -= percent;

            File fileOut = new File(destImg);
            if (fileOut.exists()) {
                fileOut.delete();
            }
            FileImageOutputStream output = new FileImageOutputStream(fileOut);

            writer.setOutput(output);

            iwp.setCompressionQuality(quality);

            writer.write(null, image, iwp);

            fileOut2 = new File(destImg);
            long newFileSize = fileOut2.length();
            if (newFileSize == fileSize) {
                // cannot reduce more, return  
                break;
            } else {
                fileSize = newFileSize;
            }
            output.close();
        }

        writer.dispose();
        
        return fileOut2;
    }

}
