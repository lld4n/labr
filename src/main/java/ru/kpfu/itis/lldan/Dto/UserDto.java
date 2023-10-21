package ru.kpfu.itis.lldan.Dto;

public class UserDto {
    private String username;
    private String email;

    private int user_id;
    private String password_hash;
    public UserDto() {

    }
    public UserDto(String username, String email, int user_id, String password_hash) {
        this.username = username;
        this.email = email;
        this.user_id = user_id;
        this.password_hash = password_hash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
}
