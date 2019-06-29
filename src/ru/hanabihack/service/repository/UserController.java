package ru.hanabihack.service.repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
public class UserController {
    private final static String INSERT = ")";
    private final static String QUERY = "";
    private final static String GET_USERS_QUESTION = "";

    @Autowired
    private ComboPooledDataSource comboPooledDataSource;

    @RequestMapping(value = "/users/get/questions", method = RequestMethod.GET) //выводит все вопросы заданного пользователя
    public ResponseEntity getUsersQuestion(@RequestParam(value="email", required = false) String email) {

        return ResponseEntity.ok(email);
    }

    @RequestMapping(value = "users/get", method = RequestMethod.GET)
    public ResponseEntity getUser(@RequestParam(value="counter", defaultValue = "1") int counter)  {
        return ResponseEntity.ok(counter);
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody String string) {

        return ResponseEntity.status(500).body("string");
    }
}

