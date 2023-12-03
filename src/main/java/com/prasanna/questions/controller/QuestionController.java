package com.prasanna.questions.controller;

import com.prasanna.questions.dto.RapidQuizDTO;
import com.prasanna.questions.model.Question;
import com.prasanna.questions.dto.QuizAnswer;
import com.prasanna.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer id) {
        return questionService.getQuestionById(id);
    }
    @GetMapping("difficulty/{difficulty}")
    public ResponseEntity<List<Question>> getQuestionsByDifficulty(@PathVariable String difficulty) {
        return questionService.getQuestionsByDifficulty(difficulty);
    }

    @GetMapping("mark/{mark}")
    public ResponseEntity<List<Question>> getQuestionsByMark(@PathVariable Integer mark) {
        return questionService.getQuestionsByMark(mark);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("quiz")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String difficulty, @RequestParam Integer numQ) {
        return questionService.getQuestionsForQuiz(difficulty, numQ);
    }

    @PostMapping("ids")
    public ResponseEntity<List<RapidQuizDTO>> getQuestionsByIds(@RequestBody List<Integer> questionIds) {
        return questionService.getQuestionsByIds(questionIds);
    }

    @PostMapping("submit")
    public ResponseEntity<Integer> calculateResult(@RequestBody List<QuizAnswer> quizAnswers) {
        return questionService.calculateResult(quizAnswers);
    }
}
