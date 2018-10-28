package JDBCWorker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker { //Отдельный класс который хранит настройки подключения и делает подключение.
    private final String Username = "postgres";
    private final String password = "root";
    private final String HOSTNAME = "jdbc:postgresql://localhost:5432/HomeWork";
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public DBWorker()
    {
        try
        {
            connection = DriverManager.getConnection(HOSTNAME, Username, password); //Как работает Driver/DriverManager/Connection разобраться.
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
