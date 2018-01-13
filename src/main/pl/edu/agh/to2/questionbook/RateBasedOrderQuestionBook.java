package pl.edu.agh.to2.questionbook;

import pl.edu.agh.to2.model.Question;
import pl.edu.agh.to2.parser.ParseException;
import pl.edu.agh.to2.parser.Parser;

import java.util.*;

public class RateBasedOrderQuestionBook implements QuestionBook{

    private Parser parser;
    private List<Question> questions;
    private Iterator<Question> it;

    public RateBasedOrderQuestionBook(Parser parser) {
        this.parser = parser;
        questions = new ArrayList<>();
    }

    @Override
    public void initQuestions() {

        try {
            questions = parser.parseQuestions();
            QuestionBook.assignRates(questions);
            Collections.sort(questions, (o1, o2) ->
                    -Double.compare(o1.getRate().getAverageRate(), o2.getRate().getAverageRate()));
        } catch (ParseException e) {
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
