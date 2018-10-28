package Controllers;

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
public class UserController {

    @RequestMapping("/SimpleTest")
    public User greeting(@RequestParam(value="counter", defaultValue = "1") int counter)
    {

        return new User("ilya", "lepa");
    }

//    @RequestMapping("/AddUsers")
//    public ArrayList<User> generateUsers(@RequestParam(value="counter", defaultValue = "1") int counter)
//    {
//        ArrayList<User> RandomUsers = new ArrayList<>();
//        for (int i = 0; i<counter; i++){ //Генерируем случайно пользователей
//            User RandomUser = new User(Integer.toString(i)+"firstname",
//                    Integer.toString(i)+"Pupkin",
//                     Integer.toString(i)+"@mail.ru");
//            RandomUsers.add(RandomUser);
//        }
//        //Теперь весь массив сгружаем в БД
//        String query = "";
//        System.out.println("Данные подгрузились");
//        return RandomUsers;
//    }

    @RequestMapping("/ShowUsersFromDB")
    public ArrayList<User> ShowUsersFromDB(@RequestParam(value="counter", defaultValue = "1") int counter) //выводит по 50, 1*50, 2*50, 3*50 и т.д.
    {
        //тут надо реализовать подключение к БД через JDBC и выведение пользователей в зависимости от количества
        ArrayList<User> UsersFromDB= new ArrayList<>();
        DBWorker WorkerWithDB = new DBWorker();
        String Query = "select * from usertable where user_id<"+counter*50;
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
            if (!resultSet.next())
            {
                statement.getConnection().close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return UsersFromDB;
    }
    @RequestMapping("/ConnectDataBase")
    public void ConnectDataBase()
    {
        //Соеденить две датабазы в 1 inner join хуёин я хз
    }
}
