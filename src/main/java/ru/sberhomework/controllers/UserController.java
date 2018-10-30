package ru.sberhomework.controllers;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberhomework.pojo.ListUsers;
import ru.sberhomework.pojo.User;

import java.sql.*;

@RestController
public class UserController {
    private final static String insert = "INSERT INTO users (user_id, fname, sname, email, parent_question_id) VALUES (DEFAULT, ?, ?, ?, ?)";
    private final static String query = "SELECT * FROM users WHERE user_id < ?";
    @Autowired
    private ComboPooledDataSource comboPooledDataSource;

    @RequestMapping(value = "/users/get", method = RequestMethod.GET)
    public ResponseEntity getUser(@RequestParam(value="counter", defaultValue = "1") int counter) //выводит по 50, 1*50, 2*50, 3*50 и т.д.
    {
        ListUsers listUsers = new ListUsers();

        try (Connection connection = comboPooledDataSource.getConnection()) {
            System.out.println(">>> get connection in /users/get");
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                System.out.println(">>> get connection in prepared statement in users get");
                preparedStatement.setInt(1, counter * 50);
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println(">>> users/get completed executed");
                while (resultSet.next()) {
                    User userFromDB = new User(
                            resultSet.getInt("user_id"), //UserID
                            resultSet.getInt("parent_question_id"), //QuestionID
                            resultSet.getString("fname"), //fname
                            resultSet.getString("sname"), // lname
                            resultSet.getString("email") // email
                    );
                    listUsers.addListOfUser(userFromDB);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Bad request");
            //возвращать 500 ошибку
        }
        return ResponseEntity.ok(listUsers);
    }
    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody User user) //Попробовать изменить с Void на statement
            //если все хорошо возвращаем 200
            //Плохо Bad Request
    {
        try (Connection connection = comboPooledDataSource.getConnection())
        {
            System.out.println(">>> get connection in users/add");
            try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
                System.out.println(">>> get statement in users/add");
                preparedStatement.setString(1, user.getfName());
                preparedStatement.setString(2, user.getsName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setInt(4, user.getParentQuestionId());
                preparedStatement.execute();
                System.out.println(">>> users/add completed executed");
                return ResponseEntity.status(HttpStatus.OK).body("User added");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User don't added");
//            HttpStatus.BAD_REQUEST;
            //500 ошибка
        }
    }
}

