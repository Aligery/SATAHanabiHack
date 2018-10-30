package ru.sberhomework.pojo;

import java.util.ArrayList;
import java.util.List;

public class ListQuestion {
    private List<Question> listOfQuestions = new ArrayList<>();
    public ListQuestion() {

    }
    public void addListOfQuestions(Question question)
    {
        this.listOfQuestions.add(question);
    }
    public void setListOfQuestions(List<Question> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
    }

    public List<Question> getListOfQuestions() {
        return listOfQuestions;
    }
}
