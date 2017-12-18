package pl.edu.agh.to2.model;

import pl.edu.agh.to2.model.grader.Grader;
import pl.edu.agh.to2.model.grader.Statistics;
import pl.edu.agh.to2.model.parser.Parser;
import pl.edu.agh.to2.model.questionbook.QuestionBook;

public class Provider {
    private Parser parser;
    private Grader grader;
    private QuestionBook questionBook;
    private Statistics statistics;

    public Parser getParser() {
        return parser;
    }

    public Grader getGrader() {
        return grader;
    }

    public QuestionBook getQuestionBook() {
        return questionBook;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setGrader(Grader grader) {
        this.grader = grader;
    }

    public void setQuestionBook(QuestionBook questionBook) {
        this.questionBook = questionBook;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
}
