package com.prasanna.questions.service;

import com.prasanna.questions.dao.QuestionDao;
import com.prasanna.questions.dto.RapidQuizDTO;
import com.prasanna.questions.model.Question;
import com.prasanna.questions.dto.QuizAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> getQuestionById(Integer id) {
        Optional<Question> question = questionDao.findById(id);
        if (question.isPresent()) {
            return new ResponseEntity<>(question.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByDifficulty(String difficulty) {
        List<Question> questions = questionDao.findByDifficulty(difficulty.toUpperCase());
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestionsByMark(Integer mark) {
        return new ResponseEntity<>(questionDao.findByMark(mark), HttpStatus.OK);
    }
    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Successfully added question: " + question.getQuestion(), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String difficulty, Integer numQ) {
        System.out.println("Entered getQuestionsForQuiz");
        List<Integer> questionIds = questionDao.findWithDifficultyAndNumQ(difficulty.toUpperCase(), numQ);
        return new ResponseEntity<>(questionIds, HttpStatus.CREATED);
    }

    public ResponseEntity<List<RapidQuizDTO>> getQuestionsByIds(List<Integer> questionIds) {
        System.out.println("Entered getQuestionsByIds");
        List<RapidQuizDTO> quizQuestions = new ArrayList<>();
        for (Integer questionId : questionIds) {
            Optional<Question> question = questionDao.findById(questionId);
            if(question.isPresent()) {
                quizQuestions.add(new RapidQuizDTO(question.get().getId(), question.get().getQuestion(),
                        question.get().getOption1(), question.get().getOption2(), question.get().getOption3(),
                        question.get().getOption4(), question.get().getMark()));
            }
        }
        return new ResponseEntity<>(quizQuestions, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(List<QuizAnswer> quizAnswers) {
        System.out.println("Entered calculateResult");
        int result = 0;
        for(QuizAnswer quizAnswer : quizAnswers) {
            Optional<Question> question = questionDao.findById(quizAnswer.getId());
            if(!question.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(question.get().getAnswer().equals(quizAnswer.getResponse())) {
                result = result + question.get().getMark();
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
