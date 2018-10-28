package Bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private int user_id;
    private int question_id;
    private String fname;
    private String sname;
    private String email;

    public User(String fname, String sname, String email) {
        this.fname = fname;
        this.sname = sname;
        this.email = email;
    }

    public User(int user_id, int question_id, String fname, String sname, String email) {
        this.user_id = user_id;
        this.question_id = question_id;
        this.fname = fname;
        this.sname = sname;
        this.email = email;
    }
    public User(int question_id, String fname, String sname, String email) {
        this.question_id = question_id;
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

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public String getFname() {
        return fname;
    }

    public String getSname() {
        return sname;
    }
}
