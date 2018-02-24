package by.tareltos.webtask.servlet;

import by.tareltos.webtask.entity.Candies;
import by.tareltos.webtask.entity.Candy;
import by.tareltos.webtask.xmlparser.CandiesDOMBuilder;
import by.tareltos.webtask.xmlparser.UnMarshalWithXSD;
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
    private String schemaName = "/WEB-INF/classes/candiesdscr.xsd";

    public XmlServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("parser");
        LOGGER.log(Level.DEBUG, type);
        switch(type){
            case "marsh":
                marsch(request, response);
            case "dom":
                dom(request, response);
        }
    }

    private void dom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOGGER.log(Level.DEBUG, request.getServletContext().getRealPath(fileName));
        ValidatorSAXXSD.validateXml(request.getServletContext().getRealPath(fileName), request.getServletContext().getRealPath(schemaName));
        CandiesDOMBuilder bl = new CandiesDOMBuilder();
        bl.buildSetCandies(request.getServletContext().getRealPath(fileName));

        request.setAttribute("list", bl.getCandies());

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/page/xmlView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void marsch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.log(Level.DEBUG, request.getServletContext().getRealPath(fileName));
        ValidatorSAXXSD.validateXml(request.getServletContext().getRealPath(fileName), request.getServletContext().getRealPath(schemaName));
        Candies c = UnMarshalWithXSD.unmarshall(request.getServletContext().getRealPath(schemaName), request.getServletContext().getRealPath(fileName));
        List<Candy> lis = new ArrayList<Candy>();
        LOGGER.log(Level.DEBUG, c.getCandy().size());
        for (int i = 0; i < c.getCandy().size(); i++) {
            lis.add(c.getCandy().get(i).getValue());
            LOGGER.log(Level.INFO, c.getCandy().get(i).getValue().getName());
        }
        request.setAttribute("list", lis);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/page/xmlView.jsp");
        dispatcher.forward(request, response);
    }
}