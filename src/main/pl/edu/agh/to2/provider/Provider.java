package pl.edu.agh.to2.provider;

import pl.edu.agh.to2.grader.EasyGrader;
import pl.edu.agh.to2.grader.Grader;
import pl.edu.agh.to2.grader.StandardGrader;
import pl.edu.agh.to2.grader.Statistics;
import pl.edu.agh.to2.parser.Parser;
import pl.edu.agh.to2.parser.TxtParser;
import pl.edu.agh.to2.questionbook.QuestionBook;
import pl.edu.agh.to2.questionbook.RandomOrderQuestionBook;
import pl.edu.agh.to2.questionbook.RateBasedOrderQuestionBook;
import pl.edu.agh.to2.questionbook.StandardOrderQuestionBook;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Provider {
    private Parser parser;
    private Grader grader;
    private QuestionBook questionBook;
    private Statistics statistics;

    public static Builder construct() {
        return new Builder();
    }

    private Provider(Parser parser, Grader grader, QuestionBook questionBook, Statistics statistics) {
        this.parser = parser;
        this.grader = grader;
        this.questionBook = questionBook;
        this.statistics = statistics;
    }

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

    public static class Builder{
        public static final String FILENAME = "staff.json";

        public static final List<Class<? extends Grader>> validGraders = Stream.of(StandardGrader.class, EasyGrader.class).collect(Collectors.toList());
        public static final List<Class<? extends Parser>> validParsers = Stream.of(TxtParser.class).collect(Collectors.toList());
        public static final List<Class<? extends QuestionBook>> validQuestionBooks = Stream.of(StandardOrderQuestionBook.class, RandomOrderQuestionBook.class, RateBasedOrderQuestionBook.class).collect(Collectors.toList());

        private Class<? extends Parser> parserClass;
        private Class<? extends Grader> graderClass;
        private Class<? extends QuestionBook> questionBookClass;
        private String filename;

        public Builder parserClass(Class<? extends Parser> parserClass) {
            this.parserClass = parserClass;
            return this;
        }

        public Builder graderClass(Class<? extends Grader> graderClass) {
            this.graderClass = graderClass;
            return this;
        }

        public Builder questionBookClass(Class<? extends QuestionBook> questionBookClass) {
            this.questionBookClass = questionBookClass;
            return this;
        }

        public Builder filename(String filename) {
            this.filename = filename;
            return this;
        }

        public Class<? extends Parser> getParserClass() {
            return parserClass;
        }

        public Class<? extends Grader> getGraderClass() {
            return graderClass;
        }

        public Class<? extends QuestionBook> getQuestionBookClass() {
            return questionBookClass;
        }

        public String getFilename() {
            return filename;
        }

        public Provider buildProvider() {

            Grader grader;
            Parser parser;
            QuestionBook questionBook;
            Statistics statistics;
            Provider provider = null;
            try {
                grader = this.getGraderClass().newInstance();
                parser = this.getParserClass().getDeclaredConstructor(String.class).newInstance(this.getFilename());
                questionBook = this.getQuestionBookClass().getDeclaredConstructor(Parser.class).newInstance(parser);
                statistics = new Statistics(grader);
                provider = new Provider(parser, grader, questionBook, statistics);
                provider.getQuestionBook().initQuestions();

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            return provider;
        }

    }
}
