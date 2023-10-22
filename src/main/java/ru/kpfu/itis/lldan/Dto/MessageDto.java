package ru.kpfu.itis.lldan.Dto;

import ru.kpfu.itis.lldan.Dao.UserDao;

import java.sql.Timestamp;

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

    public UserDto user;

    public void setCreated(Timestamp created) {
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
        if (!this.anonym) {
            this.user = UserDao.getUser(user_id);
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
