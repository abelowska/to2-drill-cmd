package pl.edu.agh.to2.grader;

import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.Question;

import java.math.BigDecimal;
import java.util.List;

public class EasyGrader implements Grader{
    @Override
    public Score getScore(Question question, List<Answer> userAnswers) {
        int validAnswerCount = 0;
        for (Answer answer : userAnswers) {
            if (answer.isRight()) {
                validAnswerCount++;
            } else {
                if(validAnswerCount != 0)
                    validAnswerCount--;
                break;
            }
        }

        return new Score(question, userAnswers, (new BigDecimal(validAnswerCount)).divide(new BigDecimal(question.getValidAnswerCount())));
    }
}
