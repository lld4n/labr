package ru.kpfu.itis.lldan.Dao;

import ru.kpfu.itis.lldan.Dto.CommentDto;
import ru.kpfu.itis.lldan.Dto.MessageDto;
import ru.kpfu.itis.lldan.Utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    public static boolean addMessage(int user_id, String message_text) {
        try {
            Connection con = ConnectionDB.getConnection();
            String sql = "INSERT INTO messages (user_id, message_text) VALUES (?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, user_id);
            statement.setString(2, message_text);
            int rowsInserted = statement.executeUpdate();
            statement.close();

            return rowsInserted > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<MessageDto> getMessages() {
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM messages ORDER BY created DESC LIMIT 100";
        List<MessageDto> list = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MessageDto messageDto = new MessageDto();
                messageDto.setMessage_id(resultSet.getInt("message_id"));
                messageDto.setUser_id(resultSet.getInt("user_id"));
                messageDto.setMessage_text(resultSet.getString("message_text"));
                messageDto.setAnonym(resultSet.getBoolean("anonym"));
                messageDto.setCreated(resultSet.getTimestamp("created"));
                list.add(messageDto);
            }
            return list;
        } catch (SQLException e) {
            return list;
        }
    }

}
