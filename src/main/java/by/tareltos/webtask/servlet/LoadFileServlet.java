package by.tareltos.webtask.servlet;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import java.io.*;

@WebServlet(name = "LoadFileServlet", urlPatterns = "/loadFile")
@MultipartConfig
public class LoadFileServlet extends HttpServlet {
    final static Logger LOGGER = LogManager.getLogger();
    private static final String UPLOAD_DIR = "files";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        LOGGER.log(Level.INFO, "Upload File Directory = " + fileSaveDir.getAbsolutePath());
        for (Part part : request.getParts()) {
            if (part.getSubmittedFileName() != null) {
                part.write(uploadFilePath + File.separator + part.getSubmittedFileName());

                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/page/infoPage.jsp");
                request.setAttribute("uploadInfo", part.getSubmittedFileName() + " upload successfully");
                dispatcher.forward(request, response);
            }

        }

    }
}
