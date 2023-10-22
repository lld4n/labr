package ru.kpfu.itis.lldan.Servlet;

import ru.kpfu.itis.lldan.Dao.MessageDao;
import ru.kpfu.itis.lldan.Dao.UserDao;
import ru.kpfu.itis.lldan.Dto.MessageDto;
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

@WebServlet(name = "chatServlet", urlPatterns = "/chat")
public class ChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MessageDto> list = MessageDao.getMessages();
        req.setAttribute("list", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("chat.ftl");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message_text = req.getParameter("message");
        HttpSession session = req.getSession(true);
        String email = (String) session.getAttribute("email");
        if (email != null) {
            UserDto user = UserDao.getUser(email);
            if (user != null) {
                boolean added = MessageDao.addMessage(user.getUser_id(), message_text);


                if (added) {
                    resp.sendRedirect("chat");
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
