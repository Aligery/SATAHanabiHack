package ru.sberhomework.pojo;

import java.util.ArrayList;
import java.util.List;

public class ListUsers {
    private List<User> listOfUser = new ArrayList<>();
    public ListUsers() {

    }
    public void addListOfUser(User user)
    {
        this.listOfUser.add(user);
    }
    public void setListOfUser(List<User> listOfUser) {
        this.listOfUser = listOfUser;
    }

    public List<User> getListOfUser() {
        return listOfUser;
    }
}
