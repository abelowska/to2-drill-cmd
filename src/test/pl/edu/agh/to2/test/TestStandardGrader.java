package pl.edu.agh.to2.test;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.to2.grader.Score;
import pl.edu.agh.to2.grader.StandardGrader;
import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.Question;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestStandardGrader {

    @Test
    public void testStandardGraderFalseAnswer() {

        StandardGrader grader = new StandardGrader();

        List<Answer> userAnswers = new ArrayList<>();

        Question question = mock(Question.class);
        when(question.getValidAnswerCount()).thenReturn(3);

        Answer a1 = mock(Answer.class);
        when(a1.isRight()).thenReturn(true);
        userAnswers.add(a1);

        Answer a2 = mock(Answer.class);
        when(a2.isRight()).thenReturn(true);
        userAnswers.add(a2);

        Answer a3 = mock(Answer.class);
        when(a3.isRight()).thenReturn(true);
        userAnswers.add(a3);

        Answer a4 = mock(Answer.class);
        when(a4.isRight()).thenReturn(false);
        userAnswers.add(a4);


        Score score = grader.getScore(question, userAnswers);

        Assert.assertEquals(BigDecimal.ZERO, score.getPercentage());

    }

    @Test
    public void testStandardGraderRightAnswers() {

        StandardGrader grader = new StandardGrader();

        List<Answer> userAnswers = new ArrayList<>();

        Question question = mock(Question.class);
        when(question.getValidAnswerCount()).thenReturn(5);

        Answer a1 = mock(Answer.class);
        when(a1.isRight()).thenReturn(true);
        userAnswers.add(a1);

        Answer a2 = mock(Answer.class);
        when(a2.isRight()).thenReturn(true);
        userAnswers.add(a2);


        Score score = grader.getScore(question, userAnswers);

        Assert.assertEquals(new BigDecimal("0.4"), score.getPercentage());

    }

}
