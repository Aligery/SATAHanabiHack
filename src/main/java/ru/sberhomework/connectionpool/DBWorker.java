package ru.sberhomework.connectionpool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Configuration
public class DBWorker { //Отдельный класс который хранит настройки подключения и делает подключение.
    private final String Username = "postgres";
    private final String password = "root";
    private final String HOSTNAME = "jdbc:postgresql://localhost:5432/HomeWork";
    private ComboPooledDataSource cpds;
    private Connection connection;
    // Реализовать пул бассейнов
    @Bean
    @Scope ("singleton")
    public Connection getConnection() {
        return connection;
    }

    public DBWorker()
    {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(HOSTNAME);
        cpds.setUser(Username);
        cpds.setPassword(password);
        //доп настройки
        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(100);
        try {
            connection = cpds.getConnection();
        }
        catch(SQLException e) {
            e.printStackTrace();
            //drop 500
    }
    }
}
