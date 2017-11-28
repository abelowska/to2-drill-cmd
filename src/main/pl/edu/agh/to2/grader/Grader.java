package pl.edu.agh.to2.grader;

import pl.edu.agh.to2.Question;

import java.util.List;

public interface Grader
{
    Score getScore(Question question, List<Integer> userAnswers);
}
