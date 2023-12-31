package ru.kpfu.itis.lldan.Dto;

import java.sql.Timestamp;

public class PostDto {
    public String title;
    public String post_content;
    public int post_id;
    public int author_id;
    public Timestamp created;
    public String normal_created;

    public String getNormal_created() {
        return normal_created;
    }

    public void setNormal_created(String normal_created) {
        this.normal_created = normal_created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
        normalCreated();
    }

    private void normalCreated() {

        StringBuilder buffer = new StringBuilder();
        buffer.append(this.created.getDay());
        buffer.append(".");
        buffer.append(this.created.getMonth());
        buffer.append(" ");
        buffer.append(this.created.getHours());
        buffer.append(":");

        buffer.append(this.created.getMinutes());
        System.out.println(buffer);
        this.normal_created = buffer.toString();
    }

    public PostDto() {
    }

    public PostDto(String title, String post_content, int post_id, int author_id, Timestamp created) {
        this.title = title;
        this.post_content = post_content;
        this.post_id = post_id;
        this.author_id = author_id;
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public Timestamp getCreated() {
        return created;
    }


}
