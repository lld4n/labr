package ru.kpfu.itis.lldan.Servlet;

import org.mindrot.jbcrypt.BCrypt;
import ru.kpfu.itis.lldan.Dao.UserDao;
import ru.kpfu.itis.lldan.Utils.ConnectionDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;


@WebServlet(name = "registrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);
        String email = (String) session.getAttribute("email");
        if (email == null) {
            resp.sendRedirect("registration.html");

        } else {
            resp.sendRedirect("user");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String username = req.getParameter("username");


        boolean added = UserDao.addUser(username, email, password);
        if (added) {
            HttpSession session = req.getSession(true);
            session.setAttribute("email", email);
            resp.sendRedirect("user");
        } else {
            resp.sendRedirect("error.html");
        }


    }
}
