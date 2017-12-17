package pl.edu.agh.to2.builder;

import pl.edu.agh.to2.grader.Grader;
import pl.edu.agh.to2.grader.StandardGrader;
import pl.edu.agh.to2.parser.Parser;
import pl.edu.agh.to2.parser.TxtParser;
import pl.edu.agh.to2.questionbook.QuestionBook;
import pl.edu.agh.to2.questionbook.StandardOrderQuestionBook;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Settings {
    public static final List<Class<? extends Grader>> validGraders = Stream.of(StandardGrader.class).collect(Collectors.toList());
    public static final List<Class<? extends Parser>> validParsers = Stream.of(TxtParser.class).collect(Collectors.toList());
    public static final List<Class<? extends QuestionBook>> validQuestionBooks = Stream.of(StandardOrderQuestionBook.class).collect(Collectors.toList());

    private Class<? extends Parser> parserClass;
    private Class<? extends Grader> graderClass;
    private Class<? extends QuestionBook> questionBookClass;
    private String filename;

    public Settings parserClass(Class<? extends Parser> parserClass) {
        this.parserClass = parserClass;
        return this;
    }

    public Settings graderClass(Class<? extends Grader> graderClass) {
        this.graderClass = graderClass;
        return this;
    }

    public Settings questionBookClass(Class<? extends QuestionBook> questionBookClass) {
        this.questionBookClass = questionBookClass;
        return this;
    }

    public Settings filename(String filename) {
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
}
