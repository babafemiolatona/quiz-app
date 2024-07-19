package com.springboot.quiz_app.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.quiz_app.model.Question;
import com.springboot.quiz_app.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getQuestion(@PathVariable Integer id) {
        return questionService.getQuestion(id);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question, @PathVariable Integer id) {
        return questionService.updateQuestion(question, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestion(id);        
    }
}
