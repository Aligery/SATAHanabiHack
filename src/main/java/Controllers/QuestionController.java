package Controllers;

import Bean.Question;
import Bean.User;
import JDBCWorker.DBWorker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@RestController
public class QuestionController {
    @RequestMapping("/ShowQuestion")
    public ArrayList<Question> ShowQuestion(@RequestParam(value="counter", defaultValue = "1") int counter)
    {
        ArrayList<Question> QuestionArray = new ArrayList<>();
        DBWorker WorkerWithDB = new DBWorker();
        String query = "select * from question where question_id<"+counter*50;
        try (Statement statement = WorkerWithDB.getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                Question QuestionFromDB = new Question(
                        resultSet.getInt("question_id"), //Айди вопроса
                        resultSet.getString("question"), //вопрос
                        resultSet.getInt("user_id") //АЙди пользователя который её создал
                );
                QuestionArray.add(QuestionFromDB);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return QuestionArray;

    }
//    @RequestMapping("/GenerateQuestion")
//    public ArrayList<Question> generateQuestion(@RequestParam(value="counter", defaultValue = "1") int counter)
//    {
//
//        ArrayList<Question> RandomQuestions = new ArrayList<>();
//        for (int i = 0; i<counter; i++){
//            Question RandomQuestion = new Question(i, "QuestionLupaiPupa?", i);
//            RandomQuestions.add(RandomQuestion);
//        }
//        //Теперь весь массив сгружаем в БД
//        DBWorker WorkerWithDB = new DBWorker();
//
//        for (int i = 0; i<RandomQuestions.size(); i++)
//        {
//
//            try (Statement statement = WorkerWithDB.getConnection().createStatement())
//            {
//               // statement.executeUpdate("INSERT INTO question (question_id, question, user_id) VALUES (DEFAULT, '123', '123')");
//            }   catch (SQLException e)
//            {
//                e.printStackTrace();
//            }
//
//        }
//        System.out.println("Вопросы созданы");
//        return RandomQuestions;
//    }
}
