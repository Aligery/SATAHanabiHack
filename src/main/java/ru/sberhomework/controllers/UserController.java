package ru.sberhomework.controllers;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberhomework.pojo.*;

import java.sql.*;

@RestController
public class UserController {
    private final static String INSERT = "INSERT INTO users_table (user_id, f_name, s_name, email) VALUES (DEFAULT, ?, ?, ?)";
    private final static String QUERY = "SELECT * FROM users_table ORDER BY user_id LIMIT ?";
    private final static String GET_USERS_QUESTION = "SELECT question_id, subject, question FROM questions_table INNER JOIN users_table ON (questions_table.user_id = users_table.user_id and email=?)"; //у нас в квешн тейбл нет email
    @Autowired
    private ComboPooledDataSource comboPooledDataSource;

    @RequestMapping(value = "/users/get/questions", method = RequestMethod.GET) //выводит все вопросы заданного пользователя
    public ResponseEntity getUsersQuestion(@RequestParam(value="email", required = false) String email) //выводит по 50, 1*50, 2*50, 3*50 и т.д.
    {
        ListOfQuestionsFromCurrentUser listOfQuestionFromCurrentUser = new ListOfQuestionsFromCurrentUser();
        try (Connection connection = comboPooledDataSource.getConnection()) {
            System.out.println(">>> get connection in /users/get");
            try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_QUESTION)) {
                System.out.println(">>> get connection in prepared statement in users get");
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println(">>> users/get completed executed");
                while (resultSet.next()) {
                    QuestionsFromCurrentUser question = new QuestionsFromCurrentUser(
                           resultSet.getInt("question_id"), //qId
                            resultSet.getString("question"), //q
                            resultSet.getString("subject") //Subject
                    );
                    listOfQuestionFromCurrentUser.addListOfQuestions(question);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Bad request");
            //возвращать 500 ошибку
        }
        return ResponseEntity.ok(listOfQuestionFromCurrentUser);
    }//Переделать с GET_USERS_QUESTION

    @RequestMapping(value = "users/get", method = RequestMethod.GET)
    public ResponseEntity getUser(@RequestParam(value="counter", defaultValue = "1") int counter) //выводит по 50, 1*50, 2*50, 3*50 и т.д.
    {
        ListUsers listUsers = new ListUsers();
        try (Connection connection = comboPooledDataSource.getConnection()) {
            System.out.println(">>> get connection in /users/get");
            try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
                System.out.println(">>> get connection in prepared statement in users get");
                preparedStatement.setInt(1, counter * 50);
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println(">>> users/get completed executed");
                while (resultSet.next()) {
                    User userFromDB = new User(
                            resultSet.getInt("user_id"), //UserID
                            resultSet.getString("f_name"), //f_name
                            resultSet.getString("s_name"), // s_name
                            resultSet.getString("email") // email
                    );
                    //Создавать USER через new User(); set, set set????
                    listUsers.addListOfUser(userFromDB);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Bad request");
            //возвращать 500 ошибку
        }
        return ResponseEntity.ok(listUsers);
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody User user)
    {
        try (Connection connection = comboPooledDataSource.getConnection())
        {
            System.out.println(">>> get connection in users/add");
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                System.out.println(">>> get statement in users/add");
                preparedStatement.setString(1, user.getfName());
                preparedStatement.setString(2, user.getsName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.execute();
                System.out.println(">>> users/add completed executed");
                return ResponseEntity.status(HttpStatus.OK).body("User added");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(500).body("User don't added");
        }
    }
}

