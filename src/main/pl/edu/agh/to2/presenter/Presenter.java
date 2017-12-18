package pl.edu.agh.to2.presenter;

import pl.edu.agh.to2.model.Provider;
import pl.edu.agh.to2.model.question.Question;
import pl.edu.agh.to2.view.ConsoleView;
import pl.edu.agh.to2.view.View;
import pl.edu.agh.to2.model.builder.Builder;
import pl.edu.agh.to2.model.builder.Settings;
import pl.edu.agh.to2.model.grader.StandardGrader;
import pl.edu.agh.to2.model.parser.TxtParser;
import pl.edu.agh.to2.model.questionbook.StandardOrderQuestionBook;

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

            if (!provider.getQuestionBook().hasNextQuestion())
                isExit = true;
        }

        this.showStatistics();
    }

    private boolean areSettingsNeeded() {
        return true;
    }

    private void showStatistics() {
        view.showStatistics(provider.getStatistics());
    }

}
