package pl.edu.agh.to2.presenter;

import com.google.gson.Gson;
import pl.edu.agh.to2.grader.Score;
import pl.edu.agh.to2.provider.Provider;
import pl.edu.agh.to2.model.Question;
import pl.edu.agh.to2.view.ConsoleView;
import pl.edu.agh.to2.view.View;
import pl.edu.agh.to2.grader.StandardGrader;
import pl.edu.agh.to2.parser.TxtParser;
import pl.edu.agh.to2.questionbook.StandardOrderQuestionBook;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class Presenter {
    private Provider provider;
    View view = new ConsoleView(this);

    public void startTest() {
        Provider.Builder builder;

        if (areSettingsNeeded()) {
            builder = view.askForSettings();
        } else {
            builder = Provider.construct();
            builder.graderClass(StandardGrader.class)
                    .parserClass(TxtParser.class)
                    .questionBookClass(StandardOrderQuestionBook.class)
                    .filename("filename.txt");
        }

        this.provider = builder.buildProvider();

        runTest();
    }

    private void runTest() {
        boolean isExit = false;

        while (!isExit) {
            Question question = provider.getQuestionBook().nextQuestion();
            Score score = provider.getGrader().getScore(question, view.askQuestion(question));
            view.showQuestionScore(score);
            provider.getStatistics().updateStatistics(score);
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

        try(FileWriter writer = new FileWriter(Provider.Builder.FILENAME)) {
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
