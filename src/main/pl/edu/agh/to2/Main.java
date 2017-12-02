package pl.edu.agh.to2;

import pl.edu.agh.to2.parser.ParseException;
import pl.edu.agh.to2.parser.TxtParser;
import pl.edu.agh.to2.settings.Settings;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        if(args.length < 1) {
            System.out.println("You need to provide path to file with questions");
        }

        Settings.SETTINGS.setFilename(args[0]);

        ConsolePresenter presenter = new ConsolePresenter();

        Builder builder = new Builder(presenter.getSettings());
        builder.buildComponents();

        presenter.setQuestionBook(builder.getQuestionBook());
        presenter.setStatistics(builder.getStatistics());


        while(!presenter.isExit())
        {
            presenter.askNextQuestion();
        }

        presenter.showStatistics();

//        TxtParser parser = new TxtParser("/Users/Anna/Desktop/filename.txt");
//
//        try {
//            List<Question> qs = parser.parseQuestions();
//
//            for (Question q : qs) {
//                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//                System.out.println(q.getHeader());
//                System.out.println(q.isMultipleChoice() ? "MULTIPLE" : "SINGLE");
//                System.out.println(q.getTitle());
//                System.out.println("ANSWERS");
//                for (Answer a : q.getAnswers()) {
//                    System.out.println(a.getContent() + " : " + (a.isRight() ? "RIGHT" : "WRONG"));
//                }
//            }
//        }catch (ParseException e) {
//            e.printStackTrace();
//        }


    }
}
