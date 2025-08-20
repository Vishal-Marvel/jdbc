package com.training.entity;

public class Login {
    private int id;
    private String userName;
    private String password;
    private int userId;

    public Login(int id, String userName, String password, int userId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userId = userId;
    }

    public Login() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
