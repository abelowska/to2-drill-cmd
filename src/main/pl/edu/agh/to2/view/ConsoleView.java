package pl.edu.agh.to2.view;

import pl.edu.agh.to2.provider.Provider;
import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.QuestionRate;
import pl.edu.agh.to2.presenter.Presenter;
import pl.edu.agh.to2.model.Question;
import pl.edu.agh.to2.grader.Grader;
import pl.edu.agh.to2.grader.Statistics;
import pl.edu.agh.to2.parser.Parser;
import pl.edu.agh.to2.questionbook.QuestionBook;

import java.math.BigDecimal;
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
    public Provider.Builder askForSettings() {
        Provider.Builder builder = Provider.construct();
        Scanner scanner = new Scanner(System.in);
        String className;

        String graderTypes = "";
        for (Class<?> c : Provider.Builder.validGraders)
            graderTypes += c.getSimpleName() + " | ";
        graderTypes = graderTypes.substring(0, graderTypes.length() - 3);
        System.out.println(String.format("Please provide grader class: [%s]", graderTypes));
        className = scanner.nextLine();
        if (!className.isEmpty()) {
            try {
                builder.graderClass((Class<? extends Grader>) Class.forName("pl.edu.agh.to2.grader." + className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else
            builder.graderClass(Provider.Builder.validGraders.get(0));

        String parserTypes = "";
        for (Class<?> c : Provider.Builder.validParsers)
            parserTypes += c.getSimpleName() + " | ";
        parserTypes = parserTypes.substring(0, parserTypes.length() - 3);
        System.out.println(String.format("Please provide parser class: [%s]", parserTypes));
        className = scanner.nextLine();
        if (!className.isEmpty()) {
            try {
                builder.parserClass((Class<? extends Parser>) Class.forName("pl.edu.agh.to2.parser." + className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else
            builder.parserClass(Provider.Builder.validParsers.get(0));

        String questionBookTypes = "";
        for (Class<?> c : Provider.Builder.validQuestionBooks)
            questionBookTypes += c.getSimpleName() + " | ";
        questionBookTypes = questionBookTypes.substring(0, questionBookTypes.length() - 3);
        System.out.println(String.format("Please provide question book class: [%s]", questionBookTypes));
        className = scanner.nextLine();
        if (!className.isEmpty()) {
            try {
                builder.questionBookClass((Class<? extends QuestionBook>) Class.forName("pl.edu.agh.to2.questionbook." + className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else
            builder.questionBookClass(Provider.Builder.validQuestionBooks.get(0));

        System.out.println("You need to provide path to file with questions");

        String path = scanner.next();

        builder.filename(Paths.get(path).toAbsolutePath().toString());

        return builder;
    }

    @Override
    public QuestionRate askForRate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nRate question\n 1-easy\n 2-medium \n 3-my brain exploded\n ...");

        while (true) {
            String str = scanner.next();

            if (str.matches("[123]")) {
                return new QuestionRate(Integer.parseInt(str));
            }
        }
    }

    @Override
    public void showStatistics(Statistics statistics) {
        System.out.println("Score: " + statistics.getOverallScore().multiply(new BigDecimal(100)) + "%");
    }




}
