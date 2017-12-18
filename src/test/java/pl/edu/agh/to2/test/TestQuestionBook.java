package pl.edu.agh.to2.test;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.to2.model.parser.ParseException;
import pl.edu.agh.to2.model.parser.TxtParser;
import pl.edu.agh.to2.model.question.Question;
import pl.edu.agh.to2.model.questionbook.StandardOrderQuestionBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestQuestionBook {

    @Test
    public void testQuestionBook() throws ParseException {
        Question q1 = new Question(1, "?", true, Collections.emptyList());
        Question q2 = new Question(2, "?", true, Collections.emptyList());

        TxtParser parser = mock(TxtParser.class);
        when(parser.parseQuestions()).thenReturn(Arrays.asList(q1, q2));
        StandardOrderQuestionBook standardBook = new StandardOrderQuestionBook(parser);

        standardBook.initQuestions();

        Assert.assertTrue(standardBook.hasNextQuestion());
        Assert.assertEquals(1, standardBook.nextQuestion().getId());
        Assert.assertEquals(2, standardBook.nextQuestion().getId());
        Assert.assertFalse(standardBook.hasNextQuestion());

    }
}
