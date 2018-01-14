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

    Provider.Builder askForSettings();

    void showStatistics(Statistics statistics);

    QuestionRate askForRate();

}
