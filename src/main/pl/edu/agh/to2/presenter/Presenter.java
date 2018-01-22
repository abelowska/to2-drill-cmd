package pl.edu.agh.to2.presenter;

import com.google.gson.Gson;
import pl.edu.agh.to2.grader.Score;
import pl.edu.agh.to2.model.AverageQuestionRate;
import pl.edu.agh.to2.provider.Provider;
import pl.edu.agh.to2.model.Question;
import pl.edu.agh.to2.serialization.Serializer;
import pl.edu.agh.to2.serialization.SerializerFactory;
import pl.edu.agh.to2.view.ConsoleView;
import pl.edu.agh.to2.view.View;
import pl.edu.agh.to2.grader.StandardGrader;
import pl.edu.agh.to2.parser.TxtParser;
import pl.edu.agh.to2.questionbook.StandardOrderQuestionBook;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static pl.edu.agh.to2.provider.Provider.Builder.STATS_FILE;

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
        List<AverageQuestionRate> rates = provider.getQuestionBook().getQuestions().stream().map(question -> question.getRate()).collect(Collectors.toList());
        Serializer serializer = SerializerFactory.createJsonSerializer();

        serializer.saveRatings(rates, Provider.Builder.RATES_FILE);
        serializer.saveStatistics(provider.getStatistics(), STATS_FILE);
    }


    private boolean areSettingsNeeded() {
        return true;
    }

    private void showStatistics() {
        view.showStatistics(provider.getStatistics());
    }

}
