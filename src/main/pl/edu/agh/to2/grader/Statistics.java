package pl.edu.agh.to2.grader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.Question;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private List<Score> scoreList;
    private BigDecimal overallScore;

    public Statistics() {
        scoreList = new ArrayList<>();
        overallScore = BigDecimal.ZERO;
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void updateStatistics(Score score) {
        overallScore = (score.getPercentage().subtract(overallScore).divide(new BigDecimal(scoreList.size() + 1))).add(overallScore);
        scoreList.add(score);
    }

    public BigDecimal getOverallScore() {
        return overallScore;
    }
}
