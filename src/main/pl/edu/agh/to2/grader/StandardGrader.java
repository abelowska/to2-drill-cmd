package pl.edu.agh.to2.grader;

import pl.edu.agh.to2.Pair;
import pl.edu.agh.to2.Question;

import java.util.List;

public class StandardGrader implements Grader
{
    @Override
    public Score getScore(Question question, List<Integer> userAnswers)
    {
        List<Pair<Boolean, String>> answers = question.getAnswers();

        int validAnswerCount = 0;
        for(Integer answerIndex: userAnswers)
        {
            if(answers.get(answerIndex.intValue()).getFirst())
            {
                validAnswerCount++;
            }
            else
            {
                validAnswerCount = 0;
                break;
            }
        }

        return new Score(question, userAnswers, ((float)validAnswerCount)/(float)question.getValidAnswerCount());
    }
}
