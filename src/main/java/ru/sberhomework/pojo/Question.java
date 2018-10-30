package ru.sberhomework.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {
    private int questionId;
    private String question;
    private int userId;
    public Question()
    {

    }
    public Question(int question_id, String question, int userID) {
        this.questionId = question_id;
        this.question = question;
        userId = userID;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "question{" +
                "question_id=" + questionId +
                ", question='" + question + '\'' +
                ", user_id=" + userId +
                '}';
    }

    public void setQuestionId(int question_id) {
        this.questionId = question_id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }
}
