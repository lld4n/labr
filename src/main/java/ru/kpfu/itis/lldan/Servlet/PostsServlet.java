package ru.kpfu.itis.lldan.Servlet;

import ru.kpfu.itis.lldan.Dao.PostDao;
import ru.kpfu.itis.lldan.Dto.PostDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "postsServlet", urlPatterns = "/posts")
public class PostsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String email = (String) session.getAttribute("email");
        boolean auth = email != null;
        List<PostDto> list = PostDao.getAllPosts();
//        if (list.isEmpty()) {
//            resp.sendRedirect("index.html");
//        } else {
        req.setAttribute("auth", auth);
            req.setAttribute("list", list);
            RequestDispatcher dispatcher = req.getRequestDispatcher("posts.jsp");
            dispatcher.forward(req, resp);
//        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
