package ru.sberhomework.controllers;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberhomework.pojo.ListQuestion;
import ru.sberhomework.pojo.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class QuestionController {
    private final static String QUERY = "SELECT*FROM questions_table ORDER BY user_id LIMIT ?";
    private final static String INSERT = "INSERT INTO questions_table (question_id, subject, question, user_id) VALUES (DEFAULT, ?, ?, ?)";
    private final ComboPooledDataSource cpds;
    @Autowired
    public QuestionController(ComboPooledDataSource cpds) {
        this.cpds = cpds;
    }

    @RequestMapping(value = "/questions/get", method = RequestMethod.GET)
    public ResponseEntity getQuestion(@RequestParam(value="counter", defaultValue = "1") int counter)
    {
        ListQuestion listQuestion = new ListQuestion();

        try (Connection connection = cpds.getConnection()) {
            System.out.println(">>> get connection from dbworker in /questions/get");
            try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
                System.out.println(">>> get preparedstatement from connection from dbworker in /question/get");
                preparedStatement.setInt(1, counter*50);
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println(">>> question/get completed executed");
                while (resultSet.next()) {
                    Question QuestionFromDB = new Question(
                            resultSet.getInt("question_id"),
                            resultSet.getString("question"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("subject")
                    );
                    listQuestion.addListOfQuestions(QuestionFromDB);
                }

            }
        }
        catch (SQLException e)
        {
            System.out.println("error" + e.getMessage());
            return ResponseEntity.status(500).body("Ошибка");
        }
        return ResponseEntity.ok(listQuestion);
    }

    @RequestMapping(value = "/questions/add", method = RequestMethod.POST)
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        try (Connection connection = cpds.getConnection()) {
            System.out.println(">>> get connection in /quesion/add");
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                System.out.println(">>> get connection in prepared statement in /quesion/add");
                preparedStatement.setString(1, question.getSubject()); //subject
                preparedStatement.setString(2, question.getQuestion()); //question
                preparedStatement.setInt(3, question.getUserId()); //user_id
                preparedStatement.execute();
                System.out.println("question/add completed executed");
                return ResponseEntity.status(HttpStatus.OK).body("question added");
            }
        }
        catch(SQLException e)
            {
                e.printStackTrace();
                return ResponseEntity.status(500).body("question doesn't added");
//                drop 500 error
            }
    }
}