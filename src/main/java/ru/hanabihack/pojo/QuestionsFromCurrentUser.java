package ru.sberhomework.pojo;

public class QuestionsFromCurrentUser {
    private int questionId;
    private String question;
    private String subject;

    public QuestionsFromCurrentUser() {
    }

    public QuestionsFromCurrentUser(int questionId, String question, String subject) {
        this.questionId = questionId;
        this.question = question;
        this.subject = subject;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
