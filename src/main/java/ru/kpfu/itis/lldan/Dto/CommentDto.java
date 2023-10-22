package ru.kpfu.itis.lldan.Dto;

import java.sql.Timestamp;

public class CommentDto {

    public int user_id;
    public int post_id;
    public int comment_id;
    public String comment_text;

    public Timestamp created_at;
    public String normal_created;
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
        normalCreated();
    }

    private void normalCreated() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(this.created_at.getHours());
        buffer.append(":");
        buffer.append(this.created_at.getMinutes());
        this.normal_created = buffer.toString();
    }
    public CommentDto() {

    }
    public CommentDto(int user_id, int post_id, int comment_id, String comment_text, Timestamp created_at) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.comment_id = comment_id;
        this.comment_text = comment_text;
        this.created_at = created_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }


}
