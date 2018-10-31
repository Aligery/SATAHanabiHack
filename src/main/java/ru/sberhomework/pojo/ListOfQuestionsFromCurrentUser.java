package ru.sberhomework.pojo;

import java.util.ArrayList;
import java.util.List;

public class ListOfQuestionsFromCurrentUser {
    public List<QuestionsFromCurrentUser> getListOfQuestionsFromCurrentUser() {
        return listOfQuestionsFromCurrentUser;
    }

    public void setListOfQuestionsFromCurrentUser(List<QuestionsFromCurrentUser> listOfQuestionsFromCurrentUser) {
        this.listOfQuestionsFromCurrentUser = listOfQuestionsFromCurrentUser;
    }

    private List<QuestionsFromCurrentUser> listOfQuestionsFromCurrentUser = new ArrayList<>();
    public ListOfQuestionsFromCurrentUser() {

    }
    public void addListOfQuestions(QuestionsFromCurrentUser question)
    {
        this.listOfQuestionsFromCurrentUser.add(question);
    }

}
