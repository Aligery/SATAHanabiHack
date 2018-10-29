package ru.sberhomework.connectionpool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker { //Отдельный класс который хранит настройки подключения и делает подключение.
    private final String Username = "postgres";
    private final String password = "root";
    private final String HOSTNAME = "jdbc:postgresql://localhost:5432/HomeWork";
    private ComboPooledDataSource cpds;
    private Connection connection;
    // Реализовать пул бассейнов
    public Connection getConnection() {
        return connection;
    }

    public DBWorker() //В ДБВоркере инциализируем бассейн
    // открываем и записываем в connection объект из бассейна
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
    }
    }
}
