package ru.kpfu.itis.lldan.Dao;

import org.mindrot.jbcrypt.BCrypt;
import ru.kpfu.itis.lldan.Dto.UserDto;
import ru.kpfu.itis.lldan.Utils.ConnectionDB;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static boolean addUser(String username, String email, String password) {
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

            return rowsInserted > 0;
        } catch (Exception e) {
            return false;
        }
    }
    public static UserDto getUser(int user_id) {
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM Usr WHERE user_id = (?)";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sql);
            statement.setInt(1, user_id);

            ResultSet resultSet = statement.executeQuery();
            UserDto user = null;
            if (resultSet.next()) {
                user = new UserDto();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword_hash(resultSet.getString("password_hash"));
            }


            resultSet.close();
            statement.close();
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

    public static UserDto getUser(String email) {
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM Usr WHERE email = (?)";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            UserDto user = null;
            if (resultSet.next()) {
                user = new UserDto();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword_hash(resultSet.getString("password_hash"));
            }


            resultSet.close();
            statement.close();
            return user;
        } catch (SQLException e) {
            return null;
        }


    }

    public static boolean checkUser(String email, String password) {

        try {
            UserDto user = getUser(email);

            if (user == null) {
                return false;
            }


            if (user.getEmail().equals(email)) {
                return BCrypt.checkpw(password, user.getPassword_hash());
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

}
