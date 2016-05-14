package com.epam.app.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Almas_Doskozhin on 5/14/2016.
 */
@Controller
public class FileDownloadController {
    private final Logger logger = LoggerFactory.getLogger(FileDownloadController.class);
    private static final String FILE="C://docs/TermsAndConditions.pdf";

    @RequestMapping(value="/downloadPdf", method = RequestMethod.GET)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        logBrowser(request);
        File file = new File(FILE);

        if(!file.exists()){
            String errorMessage = "The file does not exist";
            logger.error(errorMessage);
            throw new IOException(errorMessage);
        }

        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
        logger.info("downloadFile implemented in "+ (System.currentTimeMillis()-startTime) + "ms");
    }

    //log browser
    private void logBrowser(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        logger.info(userAgent.getBrowser().getName() + " " + userAgent.getBrowserVersion());
    }
}
