package com.prasanna.questions.dao;

import com.prasanna.questions.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByDifficulty(String difficulty);

    List<Question> findByMark(Integer mark);

    @Query(value = "select q.id from question q where q.difficulty =:difficulty order by RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Integer> findWithDifficultyAndNumQ(String difficulty, Integer numQ);
}
