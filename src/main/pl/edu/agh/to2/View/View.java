package pl.edu.agh.to2.View;

import pl.edu.agh.to2.Question;
import pl.edu.agh.to2.builder.Settings;

import java.util.List;

public interface View {

    List<Integer> askQuestion(Question question);

    Settings askForSettings();

    void showStatistics(int score);

}
