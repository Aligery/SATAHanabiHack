package Bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {
    private int question_id;
    private String question;
    private int user_id;
    public Question()
    {

    }
    public Question(int question_id, String question, int userID) {
        this.question_id = question_id;
        this.question = question;
        user_id = userID;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public String getQuestion() {
        return question;
    }

    public int getUser_id() {
        return user_id;
    }

    @Override
    public String toString() {
        return "question{" +
                "question_id=" + question_id +
                ", question='" + question + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
