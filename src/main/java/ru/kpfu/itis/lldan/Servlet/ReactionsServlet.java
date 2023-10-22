package ru.kpfu.itis.lldan.Servlet;

import ru.kpfu.itis.lldan.Dao.MessageDao;
import ru.kpfu.itis.lldan.Dao.ReactionDao;
import ru.kpfu.itis.lldan.Dao.UserDao;
import ru.kpfu.itis.lldan.Dto.MessageDto;
import ru.kpfu.itis.lldan.Dto.ReactionDto;
import ru.kpfu.itis.lldan.Dto.UserDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "reactionsServlet", urlPatterns = "/reactions")
public class ReactionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String email = (String) session.getAttribute("email");

        boolean auth = email != null;
        List<ReactionDto> list = ReactionDao.getReactions();
        System.out.println(list);
        req.setAttribute("auth", auth);
        req.setAttribute("list", list);
        System.out.println(list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("reactions.ftl");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");
        HttpSession session = req.getSession(true);
        String email = (String) session.getAttribute("email");
        if (email != null) {
            UserDto user = UserDao.getUser(email);
            if (user != null) {
                boolean added = ReactionDao.addReaction( url,user.getUser_id());
                if (added) {
                    resp.sendRedirect("reactions");
                } else {
                    resp.sendRedirect("error.html");
                }
            } else {
                resp.sendRedirect("error.html");
            }

        } else {
            resp.sendRedirect("error.html");
        }
    }
}
