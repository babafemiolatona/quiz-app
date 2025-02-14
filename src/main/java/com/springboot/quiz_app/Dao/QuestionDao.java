package com.springboot.quiz_app.Dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.springboot.quiz_app.model.Question;

public interface QuestionDao extends JpaRepository<Question, Integer> {

    public List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    public List<Question> findRandomQuestionsByCategory(String category, int numQ);

}
