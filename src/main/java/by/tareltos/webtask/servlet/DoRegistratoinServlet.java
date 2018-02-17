package by.tareltos.webtask.servlet;

import by.tareltos.webtask.entity.EmailSender;
import by.tareltos.webtask.entity.User;
import by.tareltos.webtask.wherehouse.UserWherehouse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "RegistratoinServlet", urlPatterns = "/doRegistration")
@WebInitParam(name = "Mail", value = "")
public class DoRegistratoinServlet extends HttpServlet {

    final Random random = new Random();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mail =  request.getParameter("mail");
        String fName =  request.getParameter("fname");
        String lName =  request.getParameter("lname");
        String password = fName + random.nextInt() + lName;
        User user = new User(mail, fName, lName, password);
        UserWherehouse.getInstance().put(mail, user);
        EmailSender.sent(user.getMail(), "Password: "+ user.getPassword());
        request.setAttribute("name", user.getfName());
        request.setAttribute("mail", user.getMail());
        request.getRequestDispatcher("/WEB-INF/page/sucsessfulReg.jsp").forward(request, response);
    }
}
