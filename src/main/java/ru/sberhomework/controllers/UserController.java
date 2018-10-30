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
import ru.sberhomework.connectionpool.DBWorker;

import java.sql.*;

@RestController
public class UserController {
    private final static String insert = "INSERT INTO users (user_id, fname, sname, email, parent_question_id) VALUES (DEFAULT, ?, ?, ?, ?)";
    private final static String query = "SELECT * FROM users WHERE user_id < ?";
    @Autowired
    private ComboPooledDataSource jdbcpool;

    @RequestMapping(value = "/users/get", method = RequestMethod.GET)
    public ListUsers getUser(@RequestParam(value="counter", defaultValue = "1") int counter) //выводит по 50, 1*50, 2*50, 3*50 и т.д.
    {
        ListUsers listUsers = new ListUsers();
        ApplicationContext context = new AnnotationConfigApplicationContext(DBWorker.class); //Неправильно написан context
        try (Connection connection = context.getBean(Connection.class)) { //Неправильно написан context
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, counter * 50);
                ResultSet resultSet = preparedStatement.executeQuery();
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
//            return ResponseEntity.badRequest().body("Bad request");
            //возвращать 500 ошибку
        }
        return listUsers;
    }
    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody User user) //Попробовать изменить с Void на statement
            //если все хорошо возвращаем 200
            //Плохо Bad Request
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(DBWorker.class);
        try (Connection connection = context.getBean(Connection.class))
        {
            try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
                preparedStatement.setString(1, user.getfName());
                preparedStatement.setString(2, user.getsName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setInt(4, user.getParentQuestionId());
                preparedStatement.execute();
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

