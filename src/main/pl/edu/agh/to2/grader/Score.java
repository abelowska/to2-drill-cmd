package pl.edu.agh.to2.grader;

import pl.edu.agh.to2.Question;

import java.util.List;

public class Score
{
    private Question question;
    private List<Integer> answerIndexes;
    private float percentage;

    public Score(Question question, List<Integer> answerIndexes, float percentage)
    {
        this.question = question;
        this.answerIndexes = answerIndexes;
        this.percentage = percentage;
    }

    public Question getQuestion()
    {
        return question;
    }

    public List<Integer> getAnswearIndexes()
    {
        return answerIndexes;
    }

    public float getPercentage()
    {
        return percentage;
    }
}
