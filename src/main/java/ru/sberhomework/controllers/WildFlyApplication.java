package ru.sberhomework.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@EnableAutoConfiguration
public class WildFlyApplication  {

    //Очень много Hard кода в контроллерах, можно зарефакторить на просто красивый класс с перегрузками (при желание)
    public static void main(String[] args)
    {
        SpringApplication.run(WildFlyApplication.class, args);
    }
}
