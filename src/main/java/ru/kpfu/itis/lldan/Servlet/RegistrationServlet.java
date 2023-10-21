package ru.kpfu.itis.lldan.Servlet;

import org.mindrot.jbcrypt.BCrypt;
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
        resp.sendRedirect("registration.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String salt = BCrypt.gensalt();
        String hashPassword = BCrypt.hashpw(password, salt);

        try {
            Connection con = ConnectionDB.getConnection();
            String sql = "INSERT INTO Usr (username, email, password_hash) VALUES (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, hashPassword);
            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                HttpSession session = req.getSession(true);
                session.setAttribute("email", email);
                resp.sendRedirect("user");
            } else {
                resp.sendRedirect("error.html");
            }
        } catch (Exception e) {
            resp.sendRedirect("error.html");
        }


    }
}
