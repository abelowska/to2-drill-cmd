package pl.edu.agh.to2.test;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.to2.model.Question;
import pl.edu.agh.to2.parser.ParseException;
import pl.edu.agh.to2.parser.TxtParser;
import pl.edu.agh.to2.questionbook.RandomOrderQuestionBook;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestRandomQuestionBook {

    @Test
    public void testRandomQuestionBook() throws ParseException {
        Question q1 = new Question(1, "?", true, Collections.emptyList());
        Question q2 = new Question(2, "?", true, Collections.emptyList());

        TxtParser parser = mock(TxtParser.class);
        when(parser.parseQuestions()).thenReturn(Arrays.asList(q1, q2));
        RandomOrderQuestionBook randomBook = new RandomOrderQuestionBook(parser);

        randomBook.initQuestions();

        Assert.assertTrue(randomBook.getQuestions().size() == 2);

        }
}
