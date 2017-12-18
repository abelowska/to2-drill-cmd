package pl.edu.agh.to2.view;

import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.Question;
import pl.edu.agh.to2.model.builder.Settings;
import pl.edu.agh.to2.model.grader.Statistics;

import java.util.List;

public interface View {

    List<Answer> askQuestion(Question question);

    Settings askForSettings();

    void showStatistics(Statistics statistics);

}
