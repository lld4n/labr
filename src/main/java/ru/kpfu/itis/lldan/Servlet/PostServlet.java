package ru.kpfu.itis.lldan.Servlet;

import ru.kpfu.itis.lldan.Dao.CommentDao;
import ru.kpfu.itis.lldan.Dao.PostDao;
import ru.kpfu.itis.lldan.Dao.UserDao;
import ru.kpfu.itis.lldan.Dto.CommentDto;
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
import java.util.List;

@WebServlet(name = "postServlet", urlPatterns = "/post")
public class PostServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Integer post_id = (Integer) session.getAttribute("post");
        PostDto post = PostDao.getPost(post_id);
        List<CommentDto> comments = CommentDao.getPostComments(post_id);
        String email = (String) session.getAttribute("email");

        boolean auth = email != null;
        if (post == null) {
            resp.sendRedirect("error.html");
        } else {
            System.out.println(post.created.getMinutes());
            UserDto user = UserDao.getUser(post.author_id);
            if (user == null) {
                resp.sendRedirect("error.html");
            } else {
                req.setAttribute("user", user);
                req.setAttribute("auth", auth);
                req.setAttribute("post", post);
                req.setAttribute("comments", comments);
                RequestDispatcher dispatcher = req.getRequestDispatcher("post.ftl");
                dispatcher.forward(req, resp);
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String comment = req.getParameter("comment");
        HttpSession session = req.getSession(true);
        Integer post_id = (Integer) session.getAttribute("post");
        String email = (String) session.getAttribute("email");
        UserDto user = UserDao.getUser(email);
        if (user != null && comment != null) {
            boolean added = CommentDao.addComment(user.getUser_id(), post_id, comment);
            if (!added ) {
                resp.sendRedirect("error.html");
            } else {
                resp.sendRedirect("post");
            }
        } else {
            resp.sendRedirect("error.html");
        }
    }
}
