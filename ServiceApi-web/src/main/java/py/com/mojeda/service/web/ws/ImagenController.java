/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import py.com.mojeda.service.ejb.entity.Empresas;
import py.com.mojeda.service.ejb.entity.Documentos;

/**
 *
 * @author Miguel
 */
@Controller
public class ImagenController extends BaseController {

    @ResponseBody
    @RequestMapping(value = "/image/{idEmpresa}/{entidad}/{entidadId}", method = RequestMethod.GET)
    public void descagarImagen(
            @PathVariable("idEmpresa") Long idEmpresa,
            @PathVariable("entidad") String entidad,
            @PathVariable("entidadId") Long entidadId,
            HttpServletRequest request, HttpServletResponse response) {

        try {

            inicializarDocumentoManager();
            
            response.setHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");

            // Si es un agregar entidadId es 0
            if (entidadId != 0) { // Si es un editar
                
                Documentos imagen = new Documentos();
                imagen.setEntidad(entidad);
                imagen.setIdEntidad(entidadId);
                imagen.setEmpresa(new Empresas(idEmpresa));
                
                imagen = documentoManager.get(imagen);

                if (imagen != null) { // Si existe la imagen
                    File file;
                    if(imagen.getDocumento()!= null){
                        file = convertirImagen(imagen.getDocumento(), 640);
                    }else{
                        file = new File(imagen.getPath());
                    }
                    
                    BufferedImage bufferedImage = ImageIO.read(file);
                    OutputStream output = response.getOutputStream();
                    ImageIO.write(bufferedImage, "jpg", output);
                    output.flush();
                    output.close();
                } else { // Si no existe la imagen
                    ServletContext context = request.getServletContext();
                    File downloadFile = new File(context.getRealPath("") + "/WEB-INF/resources/img/" + entidad + ".jpg");
                    FileInputStream input = new FileInputStream(downloadFile);
                    BufferedImage image = ImageIO.read(input);
                    OutputStream output = response.getOutputStream();
                    ImageIO.write(image, "jpg", output);
                    output.flush();
                    output.close();
                }
            } else { // Si es un agregar
                Documentos imagen = new Documentos();
                imagen.setEntidad(entidad);
                imagen.setIdEntidad(0l);
                
                imagen = documentoManager.get(imagen);

                if (imagen != null) { // Si existe la imagen temporal
                    File file = convertirImagen(imagen.getDocumento(), 640);
                    BufferedImage bufferedImage = ImageIO.read(file);
                    OutputStream output = response.getOutputStream();
                    ImageIO.write(bufferedImage, "jpg", output);
                    output.flush();
                    output.close();
                } else { // Si no existe la imagen temporal
                    ServletContext context = request.getServletContext();
                    File downloadFile = new File(context.getRealPath("") + "/WEB-INF/resources/img/" + entidad + ".jpg");
                    FileInputStream input = new FileInputStream(downloadFile);
                    BufferedImage image = ImageIO.read(input);
                    OutputStream output = response.getOutputStream();
                    ImageIO.write(image, "jpg", output);
                    output.flush();
                    output.close();
                }
            }
        } catch (Exception e) {
            logger.error("descargar imagen", e);
        }
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

}
