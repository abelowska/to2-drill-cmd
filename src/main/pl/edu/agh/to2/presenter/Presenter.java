package pl.edu.agh.to2.presenter;

import pl.edu.agh.to2.grader.Score;
import pl.edu.agh.to2.model.AverageQuestionRate;
import pl.edu.agh.to2.model.IdGenerator;
import pl.edu.agh.to2.provider.Provider;
import pl.edu.agh.to2.model.Question;
import pl.edu.agh.to2.serialization.Serializer;
import pl.edu.agh.to2.serialization.SerializerFactory;
import pl.edu.agh.to2.view.ConsoleView;
import pl.edu.agh.to2.view.View;
import java.util.List;
import java.util.stream.Collectors;

import static pl.edu.agh.to2.provider.Provider.Builder.STATS_FILE;

public class Presenter {

    public static final String GAME_MESSAGE = "Next game?";
    public static final String STATS_MESSAGE = "Show statistics history?";

    private Provider provider;
    private Serializer serializer = SerializerFactory.createJsonSerializer();

    View view = new ConsoleView(this);

    public void startTest() {

        Provider.Builder builder = Provider.construct();

        view.askForFilePath(builder);

        boolean toContinue = true;

        while(toContinue) {

            if(view.askForConfirmation(STATS_MESSAGE)){
                view.showAllStatistics(serializer.readStatistics(STATS_FILE));
            }

            view.askForSettings(builder);
            this.provider = builder.buildProvider();

            runTest();

            toContinue = view.askForConfirmation(GAME_MESSAGE);
            IdGenerator.getGenerator().reset();
        }
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

        serializer.saveRatings(rates, Provider.Builder.RATES_FILE);
        serializer.saveStatistics(provider.getStatistics(), STATS_FILE);


    }


    private void showStatistics() {
        view.showStatistics(provider.getStatistics());
    }

}
