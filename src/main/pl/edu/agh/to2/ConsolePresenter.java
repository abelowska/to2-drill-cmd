package pl.edu.agh.to2;

import pl.edu.agh.to2.grader.Statistics;
import pl.edu.agh.to2.questionbook.QuestionBook;
import pl.edu.agh.to2.settings.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsolePresenter
{
    QuestionBook questionBook;
    Statistics statistics;
    boolean exit;

    public void setQuestionBook(QuestionBook questionBook)
    {
        this.questionBook = questionBook;
    }

    public void setStatistics(Statistics statistics)
    {
        this.statistics = statistics;
    }

    public Settings getSettings()
    {
        System.out.println("Using default setings");
        return Settings.createSettings().build();
    }

    public boolean isExit()
    {
        return exit;
    }

    public void askNextQuestion()
    {
        Question question = questionBook.nextQuestion();
        Scanner scanner = new Scanner(System.in);
        System.out.println(question.getText());

        int answerCounter = 1;
        for(Pair<Boolean, String> answer: question.getAnswers())
        {
            System.out.println(answerCounter + ") " + answer.getSecond());
            answerCounter++;
        }

        List<Integer> userAnswers = new ArrayList<>();
        System.out.print("\n" + "Pick answers numbers(end with q): ");

        boolean parse = true;
        while(parse)
        {
            String str = scanner.next();

            if(str.contains("q"))
                parse = false;
            else
            {
                userAnswers.add(Integer.valueOf(str) - 1);
            }
        }

        statistics.gradeQuestion(question, userAnswers);


        if(!questionBook.hasNextQuestion())
            exit = true;

    }

    public void showStatistics()
    {
        System.out.println("Score: " + (int)(statistics.getOverallScore()*100) + "%");
    }
}
