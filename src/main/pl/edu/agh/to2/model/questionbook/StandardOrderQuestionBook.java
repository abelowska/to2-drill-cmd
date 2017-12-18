package pl.edu.agh.to2.model.questionbook;

import pl.edu.agh.to2.model.parser.ParseException;
import pl.edu.agh.to2.model.parser.Parser;
import pl.edu.agh.to2.model.Question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StandardOrderQuestionBook implements QuestionBook {
    private Parser parser;
    private List<Question> questions;
    private Iterator<Question> it;

    public StandardOrderQuestionBook(Parser parser) {
        this.parser = parser;
        questions = new ArrayList<>();
    }

    @Override
    public void getQuestions() {

        try {
            questions = parser.parseQuestions();
        } catch (ParseException e) {
            //TODO What to do with this exception
            e.printStackTrace();
        }
        it = questions.iterator();
    }

    @Override
    public Question nextQuestion() {
        if (it.hasNext())
            return it.next();
        else
            return null;
    }

    @Override
    public boolean hasNextQuestion() {
        return it.hasNext();
    }


}
