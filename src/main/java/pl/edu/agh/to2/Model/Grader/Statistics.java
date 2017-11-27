package java.pl.edu.agh.to2.Model.Grader;

import java.util.ArrayList;
import java.util.List;

public class Statistics
{
    private List<Score> scoreList;
    private float overalScore;

    public Statistics()
    {
        scoreList = new ArrayList<>();
        overalScore = 1;
    }

    public float getOveralScore()
    {
        return overalScore;
    }
}
