package by.tareltos.webtask.servlet;

import by.tareltos.webtask.entity.User;
import by.tareltos.webtask.mailsender.EmailSender;
import by.tareltos.webtask.wherehouse.UserWherehouse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;

@WebServlet(name = "LoadFileServlet", urlPatterns = "/loadFile")
@MultipartConfig
public class LoadFileServlet extends HttpServlet {
    final static Logger LOGGER = LogManager.getLogger();
    private static final String UPLOAD_DIR = "files";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
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
                //   part.write("d:\\temp\\" + part. getSubmittedFileName());
                response.getWriter().print(part.getSubmittedFileName() + " upload successfully");
            }

        }

    }
}
