package ru.kpfu.itis.lldan.Servlet;

import ru.kpfu.itis.lldan.Dao.PostDao;
import ru.kpfu.itis.lldan.Dao.UserDao;
import ru.kpfu.itis.lldan.Dto.PostDto;
import ru.kpfu.itis.lldan.Dto.UserDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "postServlet", urlPatterns = "/post")
public class PostServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Integer post_id = (Integer) session.getAttribute("post");
        PostDto post = PostDao.getPost(post_id);
        String email = (String) session.getAttribute("email");
        boolean auth = email != null;
        if (post == null) {
            resp.sendRedirect("error.html");
        } else {
            UserDto user = UserDao.getUser(post.author_id);
            if (user == null) {
                resp.sendRedirect("error.html");
            } else {
                req.setAttribute("user", user);
                req.setAttribute("auth", auth);
                req.setAttribute("post", post);
                RequestDispatcher dispatcher = req.getRequestDispatcher("post.ftl");
                dispatcher.forward(req, resp);
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
