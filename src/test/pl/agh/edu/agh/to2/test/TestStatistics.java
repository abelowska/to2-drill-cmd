package pl.edu.agh.to2.test;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.to2.grader.StandardGrader;
import pl.edu.agh.to2.grader.Statistics;
import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.Question;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestStatistics {

    @Test
    public void testStatistics(){

        StandardGrader grader = new StandardGrader();

       Statistics statistics = new Statistics(grader);

        List<Answer> userAnswers1 = new ArrayList<>();

        Question question1 = mock(Question.class);
        when(question1.getValidAnswerCount()).thenReturn(5);

        Question question2 = mock(Question.class);
        when(question2.getValidAnswerCount()).thenReturn(3);


        Answer a1 = mock(Answer.class);
        when(a1.isRight()).thenReturn(true);
        userAnswers1.add(a1);

        Answer a2 = mock(Answer.class);
        when(a2.isRight()).thenReturn(true);
        userAnswers1.add(a2);

        Answer a3 = mock(Answer.class);
        when(a3.isRight()).thenReturn(true);
        userAnswers1.add(a3);


        statistics.gradeQuestion(question1, userAnswers1);
        statistics.gradeQuestion(question2, userAnswers1);


        Assert.assertEquals(0.8, statistics.getOverallScore(), 0.01 );

    }
}
