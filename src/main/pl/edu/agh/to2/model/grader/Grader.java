package pl.edu.agh.to2.model.grader;

import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.Question;

import java.util.List;

public interface Grader {
    Score getScore(Question question, List<Answer> userAnswers);
}
