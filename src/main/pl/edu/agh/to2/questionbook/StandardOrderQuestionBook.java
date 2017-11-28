package pl.edu.agh.to2.questionbook;

import pl.edu.agh.to2.parser.Parser;
import pl.edu.agh.to2.Question;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StandardOrderQuestionBook implements QuestionBook
{
    private Parser parser;
    private List<Question> questions;
    private Iterator<Question> it;

    public StandardOrderQuestionBook(Parser parser)
    {
        this.parser = parser;
        questions = new ArrayList<>();
    }

    @Override
    public void getQuestions()
    {

        questions = parser.parseQuestions();
        it = questions.iterator();
    }

    @Override
    public Question nextQuestion()
    {
        if(it.hasNext())
            return it.next();
        else
            return null;
    }

    @Override
    public boolean hasNextQuestion()
    {
        return it.hasNext();
    }


}
