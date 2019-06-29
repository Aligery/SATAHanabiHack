package ru.hanabihack.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hanabihack.mapper.UserMapper;

@RestController
public class QuestionController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/questions/get", method = RequestMethod.GET)
    public ResponseEntity getQuestion() {
        userMapper.findAll();
        return ResponseEntity.ok(userMapper.findAll());
    }

    @RequestMapping(value = "/questions/add", method = RequestMethod.POST)
    public ResponseEntity addQuestion(@RequestBody String question) {
        return ResponseEntity.ok(question);
    }
}