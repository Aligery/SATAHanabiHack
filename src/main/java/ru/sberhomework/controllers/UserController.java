package ru.sberhomework.controllers;

import org.springframework.web.bind.annotation.*;
import ru.sberhomework.beans.User;
import ru.sberhomework.connectionpool.DBWorker;

import java.sql.*;
import java.util.ArrayList;

@RestController
public class UserController {
    private final static String insert = "INSERT INTO usertable (user_id, fname, sname, email, parrent_question_id) VALUES (DEFAULT, ?, ?, ?, ?)";
    private final static String Query = "SELECT * FROM usertable WHERE user_id<?";
    @RequestMapping("/Users/get")
    public ArrayList<User> getUser(@RequestParam(value="counter", defaultValue = "1") int counter) //выводит по 50, 1*50, 2*50, 3*50 и т.д.
    {
        //тут надо реализовать подключение к БД через JDBC и выведение пользователей в зависимости от количества
        ArrayList<User> UsersFromDB= new ArrayList<>();
        DBWorker WorkerWithDB = new DBWorker();
        try {
            Statement statement = WorkerWithDB.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(Query);
            while(resultSet.next())
            {
                User UserFromDB = new User(
                        resultSet.getInt("user_id"), //UserID
                        resultSet.getInt("parrent_question_id"), //QuestionID
                        resultSet.getString("fname"), //fname
                        resultSet.getString("sname"), // lname
                        resultSet.getString("email") // email
                );
                UsersFromDB.add(UserFromDB);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return UsersFromDB;
    }
    @RequestMapping(value = "/Users/Add", method = RequestMethod.POST)
    public void addUser(@RequestBody User user)
    {
        //System.out.println("Something happening");
        //Теперь весь массив сгружаем в БД
        DBWorker WorkerWithDB = new DBWorker();
        try (Connection connection = WorkerWithDB.getConnection())
        {
            try (PreparedStatement preparedStatement = WorkerWithDB.getConnection().prepareStatement(insert)) {
                preparedStatement.setString(1, user.getFname());
                preparedStatement.setString(2, user.getSname());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setInt(4, user.getQuestion_id());
                preparedStatement.execute();
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

