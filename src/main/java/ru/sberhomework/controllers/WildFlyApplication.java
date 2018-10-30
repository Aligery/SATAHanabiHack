package ru.sberhomework.controllers;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;


@ComponentScan
@EnableAutoConfiguration
public class WildFlyApplication  {


    //Очень много Hard кода в контроллерах, можно зарефакторить на просто красивый класс с перегрузками (при желание)
    public static void main(String[] args)
    {
        final String Username = "postgres";
        final String password = "root";
        final String HOSTNAME = "jdbc:postgresql://localhost:5432/HomeWork";
        SpringApplication.run(WildFlyApplication.class, args);

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
//        try {
//            connection = cpds.getConnection();
//        }
//        catch(SQLException e) {
//            e.printStackTrace();
    }
}
