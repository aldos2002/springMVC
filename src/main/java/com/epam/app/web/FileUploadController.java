package com.epam.app.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Almas_Doskozhin
 * on 5/14/2016.
 */
@Controller
public class FileUploadController {
    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping(value = "/movies/uploadform", method = RequestMethod.GET)
    public String displayForm(HttpServletRequest req) {
        logBrowser(req);
        return "uploadform";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("uploadFile") MultipartFile uploadFile, Model model,
            Model map, HttpServletRequest req) {
        long startTime = System.currentTimeMillis();
        logBrowser(req);
        logger.info("Upload file started.");
        if (!uploadFile.isEmpty()) {
            try {
                byte[] bytes = uploadFile.getBytes();
                File file = new File("C://docs/TermsAndConditions.pdf");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bytes);
                fos.close();
            } catch (IOException e) {
                model.addAttribute("msg", "Error occured: Pdf file not Uploaded");
                logger.error("File upload failed", e);
                return "uploadform";
            }
        }

        model.addAttribute("msg", "Pdf file uploaded successfully");
        logger.info("\"save\" implemented in "+ (System.currentTimeMillis()-startTime) + "ms");
        return "uploadform";
    }

    //log browser
    private void logBrowser(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        logger.info(userAgent.getBrowser().getName() + " " + userAgent.getBrowserVersion());
    }
}
