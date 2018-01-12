package pl.edu.agh.to2.model.questionbook;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import pl.edu.agh.to2.model.parser.ParseException;
import pl.edu.agh.to2.model.parser.Parser;
import pl.edu.agh.to2.model.question.Question;

import java.util.ArrayList;
import java.util.Collections;
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
    public void initQuestions() {

        try {
            questions = parser.parseQuestions();
            QuestionBook.assignRates(questions);
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

    @Override
    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }


}
