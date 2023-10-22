package ru.kpfu.itis.lldan.Dto;

import ru.kpfu.itis.lldan.Dao.UserDao;

import java.sql.Timestamp;
import java.util.Objects;

public class MessageDto {
//    message_id SERIAL primary key,
//    user_id int references usr(user_id),
//    message_text text,
//    anonym boolean,
//    created timestamp default current_timestamp

    public int message_id;
    public int user_id;
    public String message_text;
    public boolean anonym;
    public Timestamp created;
    public String normal_created;

    public String user;

    public String getNormal_created() {
        return normal_created;
    }

    public void setNormal_created(String normal_created) {
        this.normal_created = normal_created;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "message_id=" + message_id +
                ", user_id=" + user_id +
                ", message_text='" + message_text + '\'' +
                ", anonym=" + anonym +
                ", created=" + created +
                ", normal_created='" + normal_created + '\'' +
                ", user=" + user +
                '}';
    }

    public void setCreated(Timestamp created) {
        this.created = created;
        normalCreated();
    }

    private void normalCreated() {
        String buffer = this.created.getHours() +
                ":" +
                this.created.getMinutes();
        this.normal_created = buffer;
    }

    public MessageDto() {
    }

    public MessageDto(int message_id, int user_id, String message_text, boolean anonym, Timestamp created) {
        this.message_id = message_id;
        this.user_id = user_id;
        this.message_text = message_text;
        this.anonym = anonym;
        this.created = created;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
        UserDto user = UserDao.getUser(user_id);
        if (user != null) {
            this.user = user.getUsername();
        } else {
            this.user = "Аноним";
        }

    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public boolean isAnonym() {
        return anonym;
    }

    public void setAnonym(boolean anonym) {
        this.anonym = anonym;
    }

    public Timestamp getCreated() {
        return created;
    }


}
