package ru.sberhomework.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberhomework.pojo.ListQuestion;
import ru.sberhomework.pojo.Question;
import ru.sberhomework.connectionpool.DBWorker;

import java.sql.*;

@RestController
public class QuestionController {
    private final String query = "SELECT*FROM question WHERE question_id < ?";
    private final String insert = "INSERT INTO question (question_id, question, user_id) VALUES (DEFAULT, ?, ?)";
    @RequestMapping(value = "/questions/get", method = RequestMethod.GET)
    public ListQuestion getQuestion(@RequestParam(value="counter", defaultValue = "1") int counter)
    {
        ListQuestion listQuestion = new ListQuestion();
        ApplicationContext context = new AnnotationConfigApplicationContext(DBWorker.class);
        try (Connection connection = context.getBean(Connection.class)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, counter*50);
                ResultSet resultSet = preparedStatement.executeQuery();
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
            e.printStackTrace();
            //drop 500 error
        }
        return listQuestion;
    }
    @RequestMapping(value = "/questions/add", method = RequestMethod.POST)
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DBWorker.class);
        try (Connection connection = context.getBean(Connection.class)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
                preparedStatement.setString(1, question.getQuestion());
                preparedStatement.setInt(2, question.getUserId());
                preparedStatement.execute();
                return ResponseEntity.status(HttpStatus.OK).body("question added");
            }
        }
        catch(SQLException e)
            {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("question doesn't added");
                //drop 500 error
            }
    }
}