package pl.edu.agh.to2.grader;

import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.Question;

import java.util.List;

public class Score {
    private Question question;
    private List<Answer> userAnswers;
    private float percentage;

    public Score(Question question, List<Answer> answerIndexes, float percentage) {
        this.question = question;
        this.userAnswers = answerIndexes;
        this.percentage = percentage;
    }

    public Question getQuestion() {
        return question;
    }

    public List<Answer> getAnswearIndexes() {
        return userAnswers;
    }

    public float getPercentage() {
        return percentage;
    }
}
