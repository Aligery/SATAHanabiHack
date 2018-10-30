package ru.sberhomework.controllers;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberhomework.pojo.ListQuestion;
import ru.sberhomework.pojo.Question;

import javax.xml.ws.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class QuestionController {
    private final String query = "SELECT*FROM question WHERE question_id < ?";
    private final String insert = "INSERT INTO question (question_id, question, user_id) VALUES (DEFAULT, ?, ?)";
    @Autowired
    private ComboPooledDataSource cpds;


    @RequestMapping(value = "/questions/get", method = RequestMethod.GET)
    public ResponseEntity getQuestion(@RequestParam(value="counter", defaultValue = "1") int counter)
    {
        ListQuestion listQuestion = new ListQuestion();

        try (Connection connection = cpds.getConnection()) {
            System.out.println(">>> get connection from dbworker in /questions/get");
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                System.out.println(">>> get preparedstatement from connection from dbworker in /question/get");
                preparedStatement.setInt(1, counter*50);
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println(">>> question/get completed executed");
                while (resultSet.next()) {
                    Question QuestionFromDB = new Question(
                            resultSet.getInt("question_id"), //Айди вопроса
                            resultSet.getString("question"), //вопрос
                            //resultSet.getString("subject"), заготовка чтоб получать тему вопроса
                            resultSet.getInt("user_id") //АЙди пользователя который её создал
                    );
                    listQuestion.addListOfQuestions(QuestionFromDB);
                }

            }
        }
        catch (SQLException e)
        {
            System.out.println("error" + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка");
        }
        return ResponseEntity.ok(listQuestion);
    }
    @RequestMapping(value = "/questions/add", method = RequestMethod.POST)
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        try (Connection connection = cpds.getConnection()) {
            System.out.println(">>> get connection in /quesion/add");
            try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
                System.out.println(">>> get connection in prepared statement in /quesion/add");
                preparedStatement.setString(1, question.getQuestion());
                preparedStatement.setInt(2, question.getUserId());
                preparedStatement.execute();
                System.out.println("question/add completed executed");
                return ResponseEntity.status(HttpStatus.OK).body("question added");
            }
        }
        catch(SQLException e)
            {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("question doesn't added");
//                drop 500 error
            }
    }
}