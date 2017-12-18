package pl.edu.agh.to2.test;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.to2.model.parser.ParseException;
import pl.edu.agh.to2.model.parser.TxtParser;
import pl.edu.agh.to2.model.question.Question;

import java.nio.file.Paths;
import java.util.List;

public class TestTxtParser {

    @Test
    public void testParser() throws ParseException {
        TxtParser parser = new TxtParser("src/test/resources/example-questions.txt");

        List<Question> questions = parser.parseQuestions();

        Assert.assertEquals(2, questions.size());
        Assert.assertEquals(6, questions.get(0).getAnswers().size());
        Assert.assertEquals(2, questions.get(0).getValidAnswerCount());

        Assert.assertFalse(questions.get(1).isMultipleChoice());

    }

    @Test(expected = ParseException.class)
    public void testWrongFile() throws ParseException {
        TxtParser parser = new TxtParser("src/test/resources/bad-questions.txt");

        parser.parseQuestions();

    }

    @Test(expected = ParseException.class)
    public void testEmptyAnswer() throws ParseException{
        TxtParser parser = new TxtParser("src/test/resources/empty-answer.txt");

        parser.parseQuestions();

    }

}
