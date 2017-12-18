package pl.edu.agh.to2.test;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.to2.model.question.Answer;
import pl.edu.agh.to2.model.question.Question;
import pl.edu.agh.to2.model.question.QuestionBuilder;

public class TestQuestionBuilder {


    @Test
    public void testQuestion() {
        QuestionBuilder qb = new QuestionBuilder();

        qb.setTitle("Title");
        qb.setMultipleChoice(true);
        Answer answer = new Answer("Answer", true);
        qb.addAnswer(answer);
        Question q = qb.constructQuestion();

        Assert.assertTrue(q.isMultipleChoice());
        Assert.assertEquals("Title", q.getTitle());
        Assert.assertEquals(1, q.getAnswers().size());
        Assert.assertEquals(0, q.getId());
        Assert.assertTrue(q.getAnswers().stream().anyMatch(a -> "Answer".equals(a.getContent())));
        Assert.assertEquals(1, q.getValidAnswerCount());
    }

}
