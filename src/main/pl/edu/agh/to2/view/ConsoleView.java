package pl.edu.agh.to2.view;

import pl.edu.agh.to2.grader.Score;
import pl.edu.agh.to2.provider.Provider;
import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.QuestionRate;
import pl.edu.agh.to2.presenter.Presenter;
import pl.edu.agh.to2.model.Question;
import pl.edu.agh.to2.grader.Grader;
import pl.edu.agh.to2.grader.Statistics;
import pl.edu.agh.to2.parser.Parser;
import pl.edu.agh.to2.questionbook.QuestionBook;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleView implements View {

    private Presenter presenter;
    //private int questionCounter;

    public ConsoleView(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public List<Answer> askQuestion(Question question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println( question.getTitle());

        int answerCounter = 1;
        for (Answer answer : question.getAnswers()) {
            System.out.println(answerCounter + ") " + answer.getContent());
            answerCounter++;
        }

        Set<Integer> userAnswerIndexes = new HashSet<>();
        System.out.print("\n" + "Pick answers numbers(end with '.'): ");

        boolean parse = true;
        while (parse) {
            String str = scanner.next();

            if (str.contains("."))
                parse = false;
            else {
                userAnswerIndexes.add(Integer.valueOf(str) - 1);
            }
        }

        List<Answer> userAnswers = new ArrayList<>();
        for (Integer index : userAnswerIndexes)
            userAnswers.add(question.getAnswers().get(index));
        return userAnswers;
    }

    @Override
    public void showQuestionScore(Score score) {
        System.out.println("Question Score: " + score.getPercentage().multiply(new BigDecimal(100)) + "%");
        System.out.println("Correct Answers:\n");
        int answerCount = 1;
        for (Answer answer : score.getQuestion().getAnswers())
            System.out.println((answer.isRight() ? ">>> " : "") + answerCount++ + ") " + answer.getContent());
    }


    @Override
    public void askForFilePath(Provider.Builder builder) {
        System.out.println("You need to provide path to file with questions");

        boolean parsing;
        parsing = true;
        Scanner scanner = new Scanner(System.in);

        while (parsing) {
            String pathName = scanner.next();
            Path path = Paths.get(pathName);

            File file = path.toFile();
            if (file.exists() && !file.isDirectory()) {
                builder.filename(path.toAbsolutePath().toString());
                parsing = false;
            } else {
                System.out.println("File doesn't exist, please try again");
            }
        }
    }

    @Override
    public void askForSettings(Provider.Builder builder) {

        Scanner scanner = new Scanner(System.in);
        String className;
        boolean parsing;

        String graderTypes = "";
        for (Class<?> c : Provider.Builder.validGraders)
            graderTypes += c.getSimpleName() + " | ";
        graderTypes = graderTypes.substring(0, graderTypes.length() - 3);
        parsing = true;
        while (parsing) {
            System.out.println(String.format("Please provide grader class (press Enter for default): [%s]", graderTypes));
            className = scanner.nextLine();
            if (!className.isEmpty()) {
                try {
                    if (Arrays.asList(graderTypes.split(" | ")).contains(className)) {
                        builder.graderClass((Class<? extends Grader>) Class.forName("pl.edu.agh.to2.grader." + className));
                        parsing = false;
                    } else {
                        System.out.println("Invalid class name!");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                builder.graderClass(Provider.Builder.validGraders.get(0));
                parsing = false;
            }
        }

        String parserTypes = "";
        for (Class<?> c : Provider.Builder.validParsers)
            parserTypes += c.getSimpleName() + " | ";
        parserTypes = parserTypes.substring(0, parserTypes.length() - 3);
        parsing = true;
        while (parsing) {
            System.out.println(String.format("Please provide parser class (press Enter for default): [%s]", parserTypes));
            className = scanner.nextLine();
            if (!className.isEmpty()) {
                try {
                    if (Arrays.asList(parserTypes.split(" | ")).contains(className)) {
                        builder.parserClass((Class<? extends Parser>) Class.forName("pl.edu.agh.to2.parser." + className));
                        parsing = false;
                    } else {
                        System.out.println("Invalid class name!");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                builder.parserClass(Provider.Builder.validParsers.get(0));
                parsing = false;
            }
        }

        String questionBookTypes = "";
        for (Class<?> c : Provider.Builder.validQuestionBooks)
            questionBookTypes += c.getSimpleName() + " | ";
        questionBookTypes = questionBookTypes.substring(0, questionBookTypes.length() - 3);
        parsing = true;
        while (parsing) {
            System.out.println(String.format("Please provide question book class (press Enter for default): [%s]", questionBookTypes));
            className = scanner.nextLine();
            if (!className.isEmpty()) {
                try {
                    if (Arrays.asList(questionBookTypes.split(" | ")).contains(className)) {
                        builder.questionBookClass((Class<? extends QuestionBook>) Class.forName("pl.edu.agh.to2.questionbook." + className));
                        parsing = false;
                    } else {
                        System.out.println("Invalid class name!");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                builder.questionBookClass(Provider.Builder.validQuestionBooks.get(0));
                parsing = false;
            }
        }
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
        System.out.println("\n -------------------- \n Overall score: \n" + statistics.getOverallScore().multiply(BigDecimal.valueOf(100)) + "%");

        List <Score> scoreList= statistics.getScoreList();
        Collections.sort(scoreList, this::compareById);
        for (Score score : scoreList) {
            System.out.println("Question " +score.getQuestion().getId() + ": " + score.getPercentage().multiply(BigDecimal.valueOf(100)) + "%");
        }
        System.out.println("\n -------------------- \n");
    }

    private int compareById(Score s1, Score s2) {
        Integer id1 = s1.getQuestion().getId();
        Integer id2 = s2.getQuestion().getId();

        return Integer.compare(id1, id2);

    }

    @Override
    public void showAllStatistics(List<Statistics> statistics) {
        System.out.println("\n -----ALL YOUR STATISTICS----- \n");
        int count = 0;

        for (Statistics stats : statistics) {
            System.out.println(String.format("\n -------TEST %d--------\n", ++count));
            showStatistics(stats);
        }

    }

    @Override
    public Boolean askForConfirmation(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + message + " [Y/N]");

        while (true) {
            String str = scanner.next();

            if (str.toUpperCase().equals("N")) {
                return false;
            } else if (str.toUpperCase().equals("Y")) {
                return true;
            }
        }

    }
}
