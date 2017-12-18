package pl.edu.agh.to2.model.grader;

import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.Question;

import java.util.List;

public class StandardGrader implements Grader {
    @Override
    public Score getScore(Question question, List<Answer> userAnswers) {
        List<Answer> answers = question.getAnswers();

        int validAnswerCount = 0;
        for (Answer answer : userAnswers) {
            if (answer.isRight()) {
                validAnswerCount++;
            } else {
                validAnswerCount = 0;
                break;
            }
        }

        return new Score(question, userAnswers, ((float) validAnswerCount) / (float) question.getValidAnswerCount());
    }
}
