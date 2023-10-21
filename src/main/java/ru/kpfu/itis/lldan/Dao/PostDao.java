package ru.kpfu.itis.lldan.Dao;

import ru.kpfu.itis.lldan.Dto.PostDto;
import ru.kpfu.itis.lldan.Dto.UserDto;
import ru.kpfu.itis.lldan.Utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    public static boolean addPost(String title, String post_content, int author_id) {
        try {
            Connection con = ConnectionDB.getConnection();
            String sql = "INSERT INTO Posts (title, post_content, created, author_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, post_content);
            statement.setTimestamp(3,  new Timestamp(System.currentTimeMillis()));
            statement.setInt(4, author_id);
            int rowsInserted = statement.executeUpdate();
            statement.close();

            return rowsInserted > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static PostDto getPost(int post_id) {
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM Posts WHERE post_id = (?)";
        PostDto post = null;
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, post_id);
            ResultSet resultSet =statement.executeQuery();
            if (resultSet.next()) {
                post = new PostDto();
                post.setPost_id(resultSet.getInt("post_id"));
                post.setTitle(resultSet.getString("title"));
                post.setPost_content(resultSet.getString("post_content"));
                post.setAuthor_id(resultSet.getInt("author_id"));
                post.setCreated(resultSet.getTimestamp("created"));

            }
            return post;
        } catch (SQLException e) {
            return null;
        }
    }

    public static List<PostDto> getAllPosts() {
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM Posts";
        List<PostDto> list = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()) {
                PostDto post = new PostDto();
                post.setPost_id(resultSet.getInt("post_id"));
                post.setTitle(resultSet.getString("title"));
                post.setPost_content(resultSet.getString("post_content"));
                post.setAuthor_id(resultSet.getInt("author_id"));
                post.setCreated(resultSet.getTimestamp("created"));
                list.add(post);
            }
            return list;
        } catch (SQLException e) {
            return list;
        }
    }
}
