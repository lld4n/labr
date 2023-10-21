package ru.kpfu.itis.lldan.Servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import ru.kpfu.itis.lldan.Dao.UserDao;
import ru.kpfu.itis.lldan.Dto.UserDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "userServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String email = (String) session.getAttribute("email");
        if (email == null) {
            resp.sendRedirect("error.html");
        }
        UserDto user = UserDao.getUser(email);
        if (user != null) {
            req.setAttribute("user", user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user.ftl");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("error.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        session.removeAttribute("email");
        resp.sendRedirect("index.html");
    }
}
