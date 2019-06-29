package ru.hanabihack;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.hanabihack.mapper",
        "ru.hanabihack.repository",
        "ru.hanabihack.service"})
//@Import(UserMapper.class)
public class MainApplictaion {

    public static void main(String[] args) {
        SpringApplication.run(MainApplictaion.class, args);

    }

}
