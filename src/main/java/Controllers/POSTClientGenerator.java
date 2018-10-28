package Controllers;

import Bean.Question;
import Bean.User;
import JDBCWorker.DBWorker;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
public class POSTClientGenerator {
    @RequestMapping(value = "/GenerateQuestionFromClient", method = RequestMethod.POST)
    public void GenerateQuestion(@RequestBody Question question)
    {
        System.out.println("Something happening");
        //Теперь весь массив сгружаем в БД
        DBWorker WorkerWithDB = new DBWorker();
        String insert = "INSERT INTO question (question_id, question, user_id) VALUES (DEFAULT, ?, ?)";
        try (PreparedStatement preparedStatement = WorkerWithDB.getConnection().prepareStatement(insert))
        {

            preparedStatement.setString(1,question.getQuestion());
            preparedStatement.setInt(2,question.getUser_id());
            preparedStatement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    //Генерируем на стороне клиента POST запрос и кидаем в сервер, после чего заставляем его загрузить все это в БД
    @RequestMapping(value = "/GenerateUsersFromClient", method = RequestMethod.POST)
    public void GenerateUsers(@RequestBody User user)
    {
        System.out.println("Something happening");
        //Теперь весь массив сгружаем в БД
        DBWorker WorkerWithDB = new DBWorker();
        String insert = "INSERT INTO usertable (user_id, fname, sname, email, parrent_question_id) VALUES (DEFAULT, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = WorkerWithDB.getConnection().prepareStatement(insert))
        {
            preparedStatement.setString(1, user.getFname());
            preparedStatement.setString(2, user.getSname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getQuestion_id());
            preparedStatement.execute();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        System.out.println("Данные подгрузились");

    }
}
