package ru.sberhomework.controllers;

import org.springframework.web.bind.annotation.*;
import ru.sberhomework.beans.Question;
import ru.sberhomework.connectionpool.DBWorker;

import java.sql.*;
import java.util.ArrayList;

@RestController
public class QuestionController {
    private final String query = "SELECT*FROM question WHERE question_id<?";
    private final String insert = "INSERT INTO question (question_id, question, user_id) VALUES (DEFAULT, ?, ?)";
    @RequestMapping("/Question/get")
    public ArrayList<Question> getQuestion(@RequestParam(value="counter", defaultValue = "1") int counter)
    {
        ArrayList<Question> QuestionArray = new ArrayList<>();
        DBWorker WorkerWithDB = new DBWorker();
        try (Connection connection = WorkerWithDB.getConnection()) {
            try (PreparedStatement preparedStatement = WorkerWithDB.getConnection().prepareStatement(query)) {

                preparedStatement.setInt(1, counter*50);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Question QuestionFromDB = new Question(
                            resultSet.getInt("question_id"), //Айди вопроса
                            resultSet.getString("question"), //вопрос
                            resultSet.getInt("user_id") //АЙди пользователя который её создал
                    );
                    QuestionArray.add(QuestionFromDB);
                }

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return QuestionArray;
    }
    @RequestMapping(value = "/Question/add", method = RequestMethod.POST)
    public void addQuestion(@RequestBody Question question) {
        System.out.println("Something happening");
        //Теперь весь массив сгружаем в БД
        DBWorker WorkerWithDB = new DBWorker();
        try (Connection connection = WorkerWithDB.getConnection()) {
            try (PreparedStatement preparedStatement = WorkerWithDB.getConnection().prepareStatement(insert)) {
                preparedStatement.setString(1, question.getQuestion());
                preparedStatement.setInt(2, question.getUser_id());
                preparedStatement.execute();
            }
        }
        catch(SQLException e)
            {
                e.printStackTrace();
            }
    }
}