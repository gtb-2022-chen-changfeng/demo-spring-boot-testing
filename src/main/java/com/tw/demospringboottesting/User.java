package com.tw.demospringboottesting;

/**
 * @author 80415
 */
public class User {
    private String userName;
    private long id;

    public User() {
    }

    public User(long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
