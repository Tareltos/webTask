package by.tareltos.webtask.servlet;

import by.tareltos.webtask.entity.User;
import by.tareltos.webtask.utils.Creator;
import by.tareltos.webtask.wherehouse.UserWherehouse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DoAuthenticationServlet", urlPatterns = "/doAuth")
public class DoAuthenticationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mail = request.getParameter("mail");
        String pass = request.getParameter("password");
        Creator.createUser();
        User user = UserWherehouse.getInstance().get(mail);
        if (user != null && user.getPassword().equals(pass)) {
            request.setAttribute("mail", user.getMail());
            request.setAttribute("fname", user.getfName());
            request.setAttribute("lname", user.getlName());
            request.getRequestDispatcher("/WEB-INF/page/infoPage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }
}
