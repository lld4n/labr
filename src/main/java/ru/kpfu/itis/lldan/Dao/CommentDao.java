package ru.kpfu.itis.lldan.Dao;

import ru.kpfu.itis.lldan.Dto.CommentDto;
import ru.kpfu.itis.lldan.Dto.PostDto;
import ru.kpfu.itis.lldan.Utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {

    public static boolean addComment(int user_id, int post_id, String comment_text) {
        try {
            Connection con = ConnectionDB.getConnection();
            String sql = "INSERT INTO comments (user_id, post_id, comment_text) VALUES (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, user_id);
            statement.setInt(2, post_id);
            statement.setString(3, comment_text);
            int rowsInserted = statement.executeUpdate();
            statement.close();

            return rowsInserted > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<CommentDto> getPostComments(int post_id) {
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM comments WHERE post_id = (?) ORDER BY created_at DESC";
        List<CommentDto> list = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, post_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CommentDto commentDto = new CommentDto();
                commentDto.setComment_id(resultSet.getInt("comment_id"));
                commentDto.setUser_id(resultSet.getInt("user_id"));
                commentDto.setPost_id(resultSet.getInt("post_id"));
                commentDto.setComment_text(resultSet.getString("comment_text"));
                commentDto.setCreated_at(resultSet.getTimestamp("created_at"));
                list.add(commentDto);
            }
            return list;
        } catch (SQLException e) {
            return list;
        }
    }
}
