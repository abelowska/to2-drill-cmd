package pl.edu.agh.to2.view;

import pl.edu.agh.to2.grader.Score;
import pl.edu.agh.to2.provider.Provider;
import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.Question;
import pl.edu.agh.to2.grader.Statistics;
import pl.edu.agh.to2.model.QuestionRate;

import java.util.List;

public interface View {

    List<Answer> askQuestion(Question question);

    void showQuestionScore(Score score);

    void askForSettings(Provider.Builder builder);

    void askForFilePath (Provider.Builder builder);

    void showStatistics(Statistics statistics);

    void showAllStatistics(List <Statistics> statistics);

    Boolean askForConfirmation(String message);

    QuestionRate askForRate();

}
