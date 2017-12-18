package pl.edu.agh.to2.questionbook;

import pl.edu.agh.to2.Question;

public interface QuestionBook {
    void initQuestions();

    Question nextQuestion();

    boolean hasNextQuestion();
}
