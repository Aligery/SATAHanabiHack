package ru.sberhomework.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private int userID;
    private int questionID;
    private String fname;
    private String sname;
    private String email;

    public User(String fname, String sname, String email) {
        this.fname = fname;
        this.sname = sname;
        this.email = email;
    }

    public User(int userID, int questionID, String fname, String sname, String email) {
        this.userID = userID;
        this.questionID = questionID;
        this.fname = fname;
        this.sname = sname;
        this.email = email;
    }
    public User(int questionID, String fname, String sname, String email) {
        this.questionID = questionID;
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

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getUserID() {
        return userID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public String getFname() {
        return fname;
    }

    public String getSname() {
        return sname;
    }
}
