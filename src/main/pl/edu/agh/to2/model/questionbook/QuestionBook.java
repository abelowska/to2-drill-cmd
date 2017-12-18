package pl.edu.agh.to2.model.questionbook;

import pl.edu.agh.to2.model.question.Question;

public interface QuestionBook {
    void initQuestions();

    Question nextQuestion();

    boolean hasNextQuestion();
}
