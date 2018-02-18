package by.tareltos.webtask.servlet;

import by.tareltos.webtask.entity.User;
import by.tareltos.webtask.mailsender.EmailSender;
import by.tareltos.webtask.wherehouse.UserWherehouse;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@WebServlet(name = "RegistratoinServlet", urlPatterns = "/doRegistration")
public class DoRegistratoinServlet extends HttpServlet {

    final Random random = new Random();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Properties properties = new Properties();
        ServletContext context = getServletContext();
        String filename = context.getInitParameter("mail");
        properties.load(context.getResourceAsStream(filename));

        String mail = request.getParameter("mail");
        String fName = request.getParameter("fname");
        String lName = request.getParameter("lname");
        String password = fName + random.nextInt() + lName;
        User user = new User(mail, fName, lName, password);
        UserWherehouse.getInstance().put(mail, user);
        EmailSender.sent(user.getMail(), "Password: " + user.getPassword(), properties);
        request.setAttribute("name", user.getfName());
        request.setAttribute("mail", user.getMail());
        request.getRequestDispatcher("/WEB-INF/page/sucsessfulReg.jsp").forward(request, response);
    }
}
