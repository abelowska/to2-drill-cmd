package pl.edu.agh.to2.view;

import pl.edu.agh.to2.model.Provider;
import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.Question;
import pl.edu.agh.to2.grader.Statistics;
import pl.edu.agh.to2.model.QuestionRate;

import java.util.List;

public interface View {

    List<Answer> askQuestion(Question question);

    Provider.Builder askForSettings();

    void showStatistics(Statistics statistics);

    QuestionRate askForRate();

}
