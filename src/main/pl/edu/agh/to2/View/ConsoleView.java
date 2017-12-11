package pl.edu.agh.to2.View;

import pl.edu.agh.to2.Answer;
import pl.edu.agh.to2.Presenter;
import pl.edu.agh.to2.Question;
import pl.edu.agh.to2.grader.Grader;
import pl.edu.agh.to2.grader.Statistics;
import pl.edu.agh.to2.parser.Parser;
import pl.edu.agh.to2.questionbook.QuestionBook;
import pl.edu.agh.to2.settings.Settings;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Class.forName;

public class ConsoleView implements View {

    private Presenter presenter;

    public ConsoleView(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public List<Integer> askQuestion(Question question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question.getTitle());

        int answerCounter = 1;
        for(Answer answer: question.getAnswers())
        {
            System.out.println(answerCounter + ") " + answer.getContent());
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

        return userAnswers;
    }

    @Override
    public void askForSettings(Settings settings) {
        Scanner scanner = new Scanner(System.in);
        String className;

        System.out.println(String.format ("Please provide grader class: [%s]", settings.getGraderType()));
        className = scanner.nextLine();
        if(!className.isEmpty()) {
            try {
                this.presenter.selectGrader((Class<? extends Grader>) Class.forName("pl.edu.agh.to2.grader." + className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        System.out.println(String.format ("Please provide parser class: [%s]", settings.getParserType()));
        className = scanner.nextLine();
        if(!className.isEmpty()) {
            try {
                this.presenter.selectParser((Class<? extends Parser>) Class.forName("pl.edu.agh.to2.parser." + className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }        }

        System.out.println(String.format ("Please provide question book class: [%s]", settings.getQuestionSortingType()));
        className = scanner.nextLine();
        if(!className.isEmpty()) {
            try{
                this.presenter.selectQuestionBookType((Class<? extends QuestionBook>) Class.forName("pl.edu.agh.to2.questionbook." + className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void askForFile() {
        System.out.println("You need to provide path to file with questions");
        Scanner scanner = new Scanner(System.in);

        String path = scanner.next();

        this.presenter.selectQuestionFile(Paths.get(path));
    }

    @Override
    public void showStatistics(int stats) {
        System.out.println("Score: " + stats + "%");
    }


}
