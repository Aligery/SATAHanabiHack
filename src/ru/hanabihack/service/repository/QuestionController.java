package ru.hanabihack.service.repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionController {

    private final ComboPooledDataSource cpds;

    @Autowired
    public QuestionController(ComboPooledDataSource cpds) {
        this.cpds = cpds;
    }

    @RequestMapping(value = "/questions/get", method = RequestMethod.GET)
    public ResponseEntity getQuestion(@RequestParam(value="counter", defaultValue = "1") int counter)  {

        return ResponseEntity.ok(counter);
    }

    @RequestMapping(value = "/questions/add", method = RequestMethod.POST)
    public ResponseEntity addQuestion(@RequestBody String question) {
        return ResponseEntity.ok(question);
    }
}