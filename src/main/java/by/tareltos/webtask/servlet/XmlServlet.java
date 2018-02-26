package by.tareltos.webtask.servlet;

import by.tareltos.webtask.builder.*;
import by.tareltos.webtask.entity.Candies;
import by.tareltos.webtask.entity.Candy;
import by.tareltos.webtask.validator.ValidatorSAXXSD;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Task 1 Chapter A
 * Created by Vitali Tarelko on 21.02.2018.
 * tareltos@gmail.com; skype: tareltos
 */
@WebServlet(urlPatterns = {"/loadXML"})
public class XmlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    final static Logger LOGGER = LogManager.getLogger();
    private String fileName = "/files/candies.xml";
    final String SCHEMA_NAME = "/WEB-INF/classes/candiesdscr.xsd";

    public XmlServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.log(Level.DEBUG, request.getServletContext().getRealPath(fileName));
        ValidatorSAXXSD.validateXml(request.getServletContext().getRealPath(fileName), request.getServletContext().getRealPath(SCHEMA_NAME));
       AbstractCandiesFactory candiesFactory = new AbstractCandiesFactory();
       candiesFactory.setSchemaName(request.getServletContext().getRealPath(SCHEMA_NAME));
        AbstractCandiesBuilder builder = candiesFactory.createCandyBuilder(request.getParameter("parser"));
        builder.buildSetCandies(request.getServletContext().getRealPath(fileName));
        request.setAttribute("list", builder.getCandies());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/page/xmlView.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}