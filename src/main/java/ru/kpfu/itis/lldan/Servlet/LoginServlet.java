package ru.kpfu.itis.lldan.Servlet;

import ru.kpfu.itis.lldan.Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        boolean checked = UserDao.checkUser(email, password);
        if (checked) {
            HttpSession session = req.getSession(true);
            session.setAttribute("email", email);
            resp.sendRedirect("user");
        } else {
            resp.sendRedirect("error.html");
        }
    }
}
