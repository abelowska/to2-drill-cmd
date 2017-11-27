package java.pl.edu.agh.to2.Model.Grader;

import java.pl.edu.agh.to2.Model.Question;
import java.util.List;

public interface Grader
{
    Score getScore(Question question, List<Integer> userAnswers);
}
