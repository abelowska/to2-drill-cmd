package pl.edu.agh.to2.view;

import pl.edu.agh.to2.model.question.Answer;
import pl.edu.agh.to2.model.question.Question;
import pl.edu.agh.to2.model.builder.Settings;
import pl.edu.agh.to2.model.grader.Statistics;

import java.util.List;

public interface View {

    List<Answer> askQuestion(Question question);

    Settings askForSettings();

    void showStatistics(Statistics statistics);

}
