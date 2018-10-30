package ru.sberhomework.controllers;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;


@ComponentScan
@EnableAutoConfiguration
@Configuration
public class WildFlyApplication  {
    private final String Username = "postgres";
    private final String password = "root";
    private final String HOSTNAME = "jdbc:postgresql://localhost:5432/HomeWork";
    //Переписать с использованием .properties и @Value.
    @Bean
    public ComboPooledDataSource getPool(){
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
        System.out.println(">>> Configured and run POOL of connection");
        return cpds;
    }
    //Очень много Hard кода в контроллерах, можно зарефакторить на просто красивый класс с перегрузками (при желание)
    public static void main(String[] args)
    {
        SpringApplication.run(WildFlyApplication.class, args);

    }
}
