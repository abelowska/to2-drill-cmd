package pl.edu.agh.to2.model.grader;

import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.Question;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private List<Score> scoreList;
    private float overallScore;
    private Grader grader;


    public Statistics(Grader grader) {
        scoreList = new ArrayList<>();
        overallScore = 0;
        this.grader = grader;
    }

    public void gradeQuestion(Question question, List<Answer> userAnswers) {
        Score score = grader.getScore(question, userAnswers);

        overallScore += (score.getPercentage() - overallScore) / (scoreList.size() + 1);
        scoreList.add(score);
    }

    public float getOverallScore() {
        return overallScore;
    }
}
