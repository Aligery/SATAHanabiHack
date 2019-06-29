package ru.hanabihack.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hanabihack.mapper.UserMapper;


@RestController
public class UserController {
    private final static String INSERT = ")";
    private final static String QUERY = "";
    private final static String GET_USERS_QUESTION = "";

    @Autowired
    UserMapper userMapper;


    @RequestMapping(value = "/users/get", method = RequestMethod.GET)
    public ResponseEntity getUser(@RequestParam(value="counter", defaultValue = "1") int counter)  {
        return ResponseEntity.ok(userMapper.findAll());
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody String string) {

        return ResponseEntity.status(500).body("string");
    }
}

