package pl.edu.agh.to2.View;

import pl.edu.agh.to2.Question;
import pl.edu.agh.to2.settings.Settings;

import java.util.List;

public interface View {

    List<Integer> askQuestion(Question question);

    void askForSettings(Settings settings);

    void askForFile();

    void showStatistics(int score);

}
