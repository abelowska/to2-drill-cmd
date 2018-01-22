package pl.edu.agh.to2.serialization;

import pl.edu.agh.to2.grader.Statistics;
import pl.edu.agh.to2.model.AverageQuestionRate;

import java.util.List;

public interface Serializer {

    void saveStatistics(Statistics stats, String pathToFile);
    void saveRatings(List<AverageQuestionRate> rates, String pathToFile);
    List<AverageQuestionRate> readRates(String pathToFile);
}
