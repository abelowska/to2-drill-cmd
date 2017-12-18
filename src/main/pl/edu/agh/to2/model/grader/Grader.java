package pl.edu.agh.to2.model.grader;

import pl.edu.agh.to2.model.question.Answer;
import pl.edu.agh.to2.model.question.Question;

import java.util.List;

public interface Grader {
    Score getScore(Question question, List<Answer> userAnswers);
}
