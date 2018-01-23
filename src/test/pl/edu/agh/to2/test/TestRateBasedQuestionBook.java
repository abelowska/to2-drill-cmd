package pl.edu.agh.to2.test;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.to2.model.AverageQuestionRate;
import pl.edu.agh.to2.model.Question;
import pl.edu.agh.to2.model.QuestionRate;
import pl.edu.agh.to2.parser.ParseException;
import pl.edu.agh.to2.parser.TxtParser;
import pl.edu.agh.to2.questionbook.RateBasedOrderQuestionBook;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestRateBasedQuestionBook {

    @Test
    public void testRateBasedQuestionBook() throws ParseException {
        Question q1 = new Question(1, "?", true, Collections.emptyList());
        AverageQuestionRate rate = new AverageQuestionRate(1);
        rate.updateAverageRate(new QuestionRate(1));
        q1.setRate(rate);

        Question q2 = new Question(2, "?", true, Collections.emptyList());
        AverageQuestionRate rate2 = new AverageQuestionRate(2);
        rate2.updateAverageRate(new QuestionRate(3));
        q2.setRate(rate2);


        TxtParser parser = mock(TxtParser.class);
        when(parser.parseQuestions()).thenReturn(Arrays.asList(q1, q2));
        RateBasedOrderQuestionBook rateBook = new RateBasedOrderQuestionBook(parser);

        rateBook.initQuestions();

        Assert.assertTrue(rateBook.hasNextQuestion());
        Assert.assertEquals(2, rateBook.nextQuestion().getId());
        Assert.assertEquals(1, rateBook.nextQuestion().getId());
        Assert.assertFalse(rateBook.hasNextQuestion());

    }
}
