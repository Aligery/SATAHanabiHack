package ru.sberhomework.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {
    private int questionId;
    private String question;
    private int userId;
    private String subject;
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
    public Question()
    {

    }

    public Question(String subject, String question) {
        this.question = question;
        this.subject = subject;
    }

    public Question(int questionId, String question, int userId, String subject) {
        this.questionId = questionId;
        this.question = question;
        this.userId = userId;
        this.subject = subject;
    }

    public Question(String subject, String question, int userId) {
        this.question = question;
        this.userId = userId;
        this.subject = subject;
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
