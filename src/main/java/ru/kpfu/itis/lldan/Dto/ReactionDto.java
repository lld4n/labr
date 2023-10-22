package ru.kpfu.itis.lldan.Dto;

import ru.kpfu.itis.lldan.Dao.ReactionDao;
import ru.kpfu.itis.lldan.Dao.UserDao;

import java.sql.Timestamp;
import java.util.Objects;

public class ReactionDto {

    public String reaction_url;
    private int user_id;
    private int reaction_id;

    private Timestamp created;

    public String username;

    private String normal_created;

    public ReactionDto() {
    }

    public ReactionDto(String reaction_url, int user_id, int reaction_id, Timestamp created, String username, String normal_created) {
        this.reaction_url = reaction_url;
        this.user_id = user_id;
        this.reaction_id = reaction_id;
        this.created = created;
        this.username = username;
        this.normal_created = normal_created;
    }


    public String getReaction_url() {
        return reaction_url;
    }

    public void setReaction_url(String reaction_url) {
        this.reaction_url = reaction_url;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
        UserDto user = UserDao.getUser(user_id);
        if (user != null) {
            this.username = user.getUsername();
        } else {
            this.username = "Аноним";
        }
    }

    @Override
    public String toString() {
        return "ReactionDto{" +
                "reaction_url='" + reaction_url + '\'' +
                ", user_id=" + user_id +
                ", reaction_id=" + reaction_id +
                ", created=" + created +
                ", username='" + username + '\'' +
                ", normal_created='" + normal_created + '\'' +
                '}';
    }

    public int getReaction_id() {
        return reaction_id;
    }

    public void setReaction_id(int reaction_id) {
        this.reaction_id = reaction_id;
        this.setUser_id(Objects.requireNonNull(UserDao.getUser(ReactionDao.getUserId(this.reaction_id))).getUser_id());
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNormal_created() {
        return normal_created;
    }

    public void setNormal_created(String normal_created) {
        this.normal_created = normal_created;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created = created;
        normalCreated();
    }
    private void normalCreated() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(this.created.getHours());
        buffer.append(":");
        buffer.append(this.created.getMinutes());
        this.normal_created = buffer.toString();
    }
}
