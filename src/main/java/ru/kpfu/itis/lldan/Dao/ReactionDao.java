package ru.kpfu.itis.lldan.Dao;

import ru.kpfu.itis.lldan.Dto.MessageDto;
import ru.kpfu.itis.lldan.Dto.ReactionDto;
import ru.kpfu.itis.lldan.Dto.UserDto;
import ru.kpfu.itis.lldan.Utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReactionDao {

    public static boolean addReaction(String reaction_url, int user_id) {
        try {
            Connection con = ConnectionDB.getConnection();
            String sql = "INSERT INTO reaction (reaction_url) VALUES (?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, reaction_url);
            statement.executeUpdate();
            statement.close();

            String getSql = "SELECT lastval()";
            int reactionId;
            try (PreparedStatement lastInsert = con.prepareStatement(getSql)) {
                try (ResultSet resultSet = lastInsert.executeQuery()) {
                    resultSet.next();
                    reactionId = resultSet.getInt(1);
                    lastInsert.close();
                }
            }

            String adding = "INSERT INTO usr_reaction (usr_id, reaction_id) values (?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(adding)) {
                preparedStatement.setInt(1, user_id);
                preparedStatement.setInt(2, reactionId);
                preparedStatement.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static int getUserId(int reaction_id) {
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM usr_reaction WHERE reaction_id = (?)";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, reaction_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("usr_id");
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public static List<ReactionDto> getReactions() {
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM reaction ORDER BY created DESC LIMIT 100";
        List<ReactionDto> list = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ReactionDto reactionDto = new ReactionDto();
                reactionDto.setReaction_id(resultSet.getInt("reaction_id"));
                reactionDto.setCreated(resultSet.getTimestamp("created"));
                reactionDto.setReaction_url(resultSet.getString("reaction_url"));
                list.add(reactionDto);
            }


            return list;
        } catch (Exception e) {
            return list;
        }
    }



}
