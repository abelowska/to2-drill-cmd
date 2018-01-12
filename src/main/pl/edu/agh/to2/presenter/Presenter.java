package pl.edu.agh.to2.presenter;

import com.google.gson.Gson;
import pl.edu.agh.to2.model.Provider;
import pl.edu.agh.to2.model.question.Question;
import pl.edu.agh.to2.view.ConsoleView;
import pl.edu.agh.to2.view.View;
import pl.edu.agh.to2.model.builder.Builder;
import pl.edu.agh.to2.model.builder.Settings;
import pl.edu.agh.to2.model.grader.StandardGrader;
import pl.edu.agh.to2.model.parser.TxtParser;
import pl.edu.agh.to2.model.questionbook.StandardOrderQuestionBook;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class Presenter {
    private Provider provider;
    View view = new ConsoleView(this);

    public void startTest() {
        Settings settings;

        if (areSettingsNeeded()) {
            settings = view.askForSettings();
        } else {
            settings = new Settings();
            settings.graderClass(StandardGrader.class)
                    .parserClass(TxtParser.class)
                    .questionBookClass(StandardOrderQuestionBook.class)
                    .filename("filename.txt");
        }

        Builder builder = new Builder(settings);
        builder.buildComponents();
        this.provider = builder.getProvider();

        runTest();
    }

    private void runTest() {
        boolean isExit = false;

        while (!isExit) {
            Question question = provider.getQuestionBook().nextQuestion();
            provider.getStatistics().gradeQuestion(question, view.askQuestion(question));
            question.updateRate(view.askForRate(
            ));

            if (!provider.getQuestionBook().hasNextQuestion())
                isExit = true;
        }

        this.showStatistics();
        saveAsJson();
        provider.getStatistics().saveAsJson();

    }

    private void saveAsJson() {
        Gson gson = new Gson();

        try(FileWriter writer = new FileWriter(Settings.FILENAME)) {
            gson.toJson(provider.getQuestionBook().getQuestions()
                    .stream().map(q -> q.getRate()).collect(Collectors.toList()), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean areSettingsNeeded() {
        return true;
    }

    private void showStatistics() {
        view.showStatistics(provider.getStatistics());
    }

}
