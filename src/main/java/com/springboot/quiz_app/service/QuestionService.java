package com.springboot.quiz_app.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.springboot.quiz_app.Dao.QuestionDao;
import com.springboot.quiz_app.model.Question;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add question", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateQuestion(Question question, Integer id) {
        try {
            if (questionDao.existsById(id)) {
                question.setId(id);
                questionDao.save(question);
                return new ResponseEntity<>("Question updated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot update question", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            if (questionDao.existsById(id)) {
                questionDao.deleteById(id);
                return new ResponseEntity<>("Question deleted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);              
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete question", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> getQuestion(Integer id) {
        try {
            return new ResponseEntity<>(questionDao.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot retrieve question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
