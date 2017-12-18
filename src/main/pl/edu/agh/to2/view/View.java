package pl.edu.agh.to2.view;

import pl.edu.agh.to2.Answer;
import pl.edu.agh.to2.Question;
import pl.edu.agh.to2.builder.Settings;

import java.util.List;

public interface View {

    List<Answer> askQuestion(Question question);

    Settings askForSettings();

    void showStatistics(int score);

}
