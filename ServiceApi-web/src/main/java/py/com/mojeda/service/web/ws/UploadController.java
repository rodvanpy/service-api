/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.web.ws;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import static py.com.mojeda.service.web.ws.BaseController.logger;


/**
 *
 * @author miguel.ojeda
 */
public class UploadController {

    @RequestMapping(method = RequestMethod.POST, headers = "content-type=multipart/form-data", value = "/uploader")
    @ResponseBody
    public String fileUploader(@RequestParam("file") MultipartFile uploader) throws IOException {
        Reader in = new InputStreamReader(uploader.getInputStream());
        try {
            logger.info(uploader.getOriginalFilename());
        } catch (Exception e) {
            logger.error("Error: ", e);
        }        
        return "file";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uploader")
    @ResponseBody
    public String getFileUploader() throws IOException {
        return "something";
    }
}
