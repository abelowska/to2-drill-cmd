package pl.edu.agh.to2;

import pl.edu.agh.to2.View.ConsoleView;
import pl.edu.agh.to2.View.View;
import pl.edu.agh.to2.grader.Grader;
import pl.edu.agh.to2.grader.Statistics;
import pl.edu.agh.to2.parser.Parser;
import pl.edu.agh.to2.parser.TxtParser;
import pl.edu.agh.to2.questionbook.QuestionBook;
import pl.edu.agh.to2.questionbook.StandardOrderQuestionBook;
import pl.edu.agh.to2.settings.Settings;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Presenter
{
    QuestionBook questionBook;
    Statistics statistics;
    boolean exit;
    View view = new ConsoleView(this);

    public void setQuestionBook(QuestionBook questionBook)
    {
        this.questionBook = questionBook;
    }

    public void setStatistics(Statistics statistics)
    {
        this.statistics = statistics;
    }

    public Settings getSettings()
    {
        System.out.println("Using default settings");
        return Settings.SETTINGS;
    }

    public boolean isExit()
    {
        return exit;
    }

    public void askNextQuestion()
    {
        Question question = questionBook.nextQuestion();
        statistics.gradeQuestion(question, view.askQuestion(question));

        if(!questionBook.hasNextQuestion()) {
            exit = true;
        }

    }

    public List<Class<? extends Grader>> getValidGraders() {
        return Stream.of(Grader.class).collect(Collectors.toList());
    }

    public void selectGrader( Class<? extends Grader> graderType) {
        //TODO
        throw new UnsupportedOperationException();
    }

    public List<Class<? extends Parser>> getValidParsers() {
        return Stream.of(TxtParser.class).collect(Collectors.toList());
    }

    public void selectParser(Class<? extends Parser> parserType) {
        //TODO
        throw new UnsupportedOperationException();
    }

    public List<Class<? extends QuestionBook>> getValidQuestionBookTypes() {
        return Stream.of(StandardOrderQuestionBook.class).collect(Collectors.toList());
    }

    public void selectQuestionBookType(Class<? extends QuestionBook> questionBookType) {
        //TODO
        throw new UnsupportedOperationException();
    }

    public void selectQuestionFile(Path questionFilePath) {
        Settings.SETTINGS.setFilename(questionFilePath.toAbsolutePath().toString());
    }

    public void startTest() {
        while (Settings.SETTINGS.getFilename().isEmpty() || !Files.exists(Paths.get(Settings.SETTINGS.getFilename()))) {
            view.askForFile();
        }

        if (areSettingsNeeded()) {
            view.askForSettings(Settings.SETTINGS);
        }

        Builder builder = new Builder(this.getSettings());
        builder.buildComponents();

        this.setQuestionBook(builder.getQuestionBook());
        this.setStatistics(builder.getStatistics());

        while(!this.isExit())
        {
            this.askNextQuestion();
        }

        this.showStatistics();
    }

    private boolean areSettingsNeeded() {
        return true;
    }

    private void showStatistics() {
        view.showStatistics((int)(statistics.getOverallScore()*100));
    }

}
