package pl.edu.agh.to2.View;

import pl.edu.agh.to2.Answer;
import pl.edu.agh.to2.Presenter;
import pl.edu.agh.to2.Question;
import pl.edu.agh.to2.builder.Settings;
import pl.edu.agh.to2.grader.Grader;
import pl.edu.agh.to2.parser.Parser;
import pl.edu.agh.to2.questionbook.QuestionBook;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        for (Answer answer : question.getAnswers()) {
            System.out.println(answerCounter + ") " + answer.getContent());
            answerCounter++;
        }

        List<Integer> userAnswers = new ArrayList<>();
        System.out.print("\n" + "Pick answers numbers(end with q): ");

        boolean parse = true;
        while (parse) {
            String str = scanner.next();

            if (str.contains("q"))
                parse = false;
            else {
                userAnswers.add(Integer.valueOf(str) - 1);
            }
        }

        return userAnswers;
    }

    @Override
    public Settings askForSettings() {
        Settings settings = new Settings();
        Scanner scanner = new Scanner(System.in);
        String className;

        String graderTypes = "";
        for (Class<?> c : Settings.validGraders)
            graderTypes += c.getSimpleName() + " | ";
        graderTypes = graderTypes.substring(0, graderTypes.length() - 3);
        System.out.println(String.format("Please provide grader class: [%s]", graderTypes));
        className = scanner.nextLine();
        if (!className.isEmpty()) {
            try {
                settings.graderClass((Class<? extends Grader>) Class.forName("pl.edu.agh.to2.grader." + className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else
            settings.graderClass(Settings.validGraders.get(0));

        String parserTypes = "";
        for (Class<?> c : Settings.validParsers)
            parserTypes += c.getSimpleName() + " | ";
        parserTypes = parserTypes.substring(0, parserTypes.length() - 3);
        System.out.println(String.format("Please provide parser class: [%s]", parserTypes));
        className = scanner.nextLine();
        if (!className.isEmpty()) {
            try {
                settings.parserClass((Class<? extends Parser>) Class.forName("pl.edu.agh.to2.parser." + className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else
            settings.parserClass(Settings.validParsers.get(0));

        String questionBookTypes = "";
        for (Class<?> c : Settings.validQuestionBooks)
            questionBookTypes += c.getSimpleName() + " | ";
        questionBookTypes = questionBookTypes.substring(0, questionBookTypes.length() - 3);
        System.out.println(String.format("Please provide question book class: [%s]", questionBookTypes));
        className = scanner.nextLine();
        if (!className.isEmpty()) {
            try {
                settings.questionBookClass((Class<? extends QuestionBook>) Class.forName("pl.edu.agh.to2.questionbook." + className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else
            settings.questionBookClass(Settings.validQuestionBooks.get(0));

        System.out.println("You need to provide path to file with questions");

        String path = scanner.next();

        settings.filename(Paths.get(path).toAbsolutePath().toString());

        return settings;
    }

    @Override
    public void showStatistics(int stats) {
        System.out.println("Score: " + stats + "%");
    }


}
