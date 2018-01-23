package pl.edu.agh.to2.questionbook;

import pl.edu.agh.to2.model.AverageQuestionRate;
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
            RatesAssigner.assignRates(questions);
            Collections.sort(questions, this::compareByRates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        it = questions.iterator();
    }

    private int compareByRates(Question q1, Question q2) {
        AverageQuestionRate rate1 = q1.getRate();
        AverageQuestionRate rate2 = q2.getRate();

        return -Double.compare(rate1 != null ? rate1.getAverageRate() : 0.0, rate2 != null ? rate2.getAverageRate() : 0.0);

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
