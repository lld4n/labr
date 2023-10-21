package ru.kpfu.itis.lldan.Servlet;

import ru.kpfu.itis.lldan.Dao.PostDao;
import ru.kpfu.itis.lldan.Dao.UserDao;
import ru.kpfu.itis.lldan.Dto.PostDto;
import ru.kpfu.itis.lldan.Dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "addPostServlet", urlPatterns = "/addpost")
public class AddPostServlet extends HttpServlet {
    public String email;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String email = (String) session.getAttribute("email");
        if (email == null) {
            resp.sendRedirect("error.html");
        } else {
            this.email = email;
            resp.sendRedirect("addPost.html");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        UserDto user = UserDao.getUser(this.email);
        if (user != null) {
            boolean added = PostDao.addPost(title, content, user.getUser_id());
            if (added) {
                resp.sendRedirect("posts");
            } else {
                resp.sendRedirect("error.html");
            }
        } else {
            resp.sendRedirect("error.html");
        }

    }
}
