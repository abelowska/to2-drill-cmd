package pl.edu.agh.to2.grader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.Question;

import java.io.*;
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

    public void saveAsJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        File f = new File("stats.json");
        PrintWriter out = null;
        try {
            if (f.exists() && !f.isDirectory()) {
                out = new PrintWriter(new FileOutputStream(f, true));
            } else {
                out = new PrintWriter("stats.json");
            }
            out.append(gson.toJson(this));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                out.close();
            }
        }
    }
}
