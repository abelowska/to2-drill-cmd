package pl.edu.agh.to2;

import pl.edu.agh.to2.View.ConsoleView;
import pl.edu.agh.to2.View.View;
import pl.edu.agh.to2.builder.Builder;
import pl.edu.agh.to2.builder.Settings;
import pl.edu.agh.to2.grader.StandardGrader;
import pl.edu.agh.to2.parser.TxtParser;
import pl.edu.agh.to2.questionbook.StandardOrderQuestionBook;

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
                    .filename("SampleTest.txt");
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

            if (!provider.getQuestionBook().hasNextQuestion())
                isExit = true;
        }

        this.showStatistics();
    }

    private boolean areSettingsNeeded() {
        return true;
    }

    private void showStatistics() {
        view.showStatistics((int) (provider.getStatistics().getOverallScore() * 100));
    }

}
