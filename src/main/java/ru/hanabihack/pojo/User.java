package ru.sberhomework.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private int userId;
    private String fName;
    private String sName;
    private String email;

    public User(String fName, String sName, String email) {
        this.fName = fName;
        this.sName = sName;
        this.email = email;
    }

    public User(int userId, String fName, String sName, String email) {
        this.userId = userId;
        this.fName = fName;
        this.sName = sName;
        this.email = email;
    }
    public User()
    {

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String fName, String sName) {
        this.fName = fName;
        this.sName = sName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getUserId() {
        return userId;
    }

    public String getfName() {
        return fName;
    }

    public String getsName() {
        return sName;
    }
}
