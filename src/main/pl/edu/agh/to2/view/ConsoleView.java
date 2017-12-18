package pl.edu.agh.to2.view;

import pl.edu.agh.to2.model.question.Answer;
import pl.edu.agh.to2.presenter.Presenter;
import pl.edu.agh.to2.model.question.Question;
import pl.edu.agh.to2.model.builder.Settings;
import pl.edu.agh.to2.model.grader.Grader;
import pl.edu.agh.to2.model.grader.Statistics;
import pl.edu.agh.to2.model.parser.Parser;
import pl.edu.agh.to2.model.questionbook.QuestionBook;

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
    public List<Answer> askQuestion(Question question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question.getTitle());

        int answerCounter = 1;
        for (Answer answer : question.getAnswers()) {
            System.out.println(answerCounter + ") " + answer.getContent());
            answerCounter++;
        }

        List<Integer> userAnswerIndexes = new ArrayList<>();
        System.out.print("\n" + "Pick answers numbers(end with q): ");

        boolean parse = true;
        while (parse) {
            String str = scanner.next();

            if (str.contains("q"))
                parse = false;
            else {
                userAnswerIndexes.add(Integer.valueOf(str) - 1);
            }
        }

        List<Answer> userAnswers = new ArrayList<>();
        for(Integer index: userAnswerIndexes)
            userAnswers.add(question.getAnswers().get(index));
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
                settings.graderClass((Class<? extends Grader>) Class.forName("pl.edu.agh.to2.model.grader." + className));
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
                settings.parserClass((Class<? extends Parser>) Class.forName("pl.edu.agh.to2.model.parser." + className));
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
                settings.questionBookClass((Class<? extends QuestionBook>) Class.forName("pl.edu.agh.to2.model.questionbook." + className));
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
    public void showStatistics(Statistics statistics) {
        System.out.println("Score: " + statistics.getOverallScore() * 100 + "%");
    }


}
