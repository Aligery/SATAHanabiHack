package ru.sberhomework.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private int userId;
    private int parrentQuestionId;
    private String fname;
    private String sname;
    private String email;

    public User(String fname, String sname, String email) {
        this.fname = fname;
        this.sname = sname;
        this.email = email;
    }

    public User(int userId, int parrentQuestionId, String fname, String sname, String email) {
        this.userId = userId;
        this.parrentQuestionId = parrentQuestionId;
        this.fname = fname;
        this.sname = sname;
        this.email = email;
    }
    public User(int parrentQuestionId, String fname, String sname, String email) {
        this.parrentQuestionId = parrentQuestionId;
        this.fname = fname;
        this.sname = sname;
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

    public User(String fname, String sname) {
        this.fname = fname;
        this.sname = sname;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setParrentQuestionId(int parrentQuestionId) {
        this.parrentQuestionId = parrentQuestionId;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getUserId() {
        return userId;
    }

    public int getParrentQuestionId() {
        return parrentQuestionId;
    }

    public String getFname() {
        return fname;
    }

    public String getSname() {
        return sname;
    }
}
