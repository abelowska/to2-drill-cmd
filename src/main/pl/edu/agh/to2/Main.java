package pl.edu.agh.to2;

import pl.edu.agh.to2.presenter.Presenter;

public class Main {
    public static void main(String[] args) {
//        Settings.SETTINGS.setFilename(args.length > 0
//                ? args[0] : "");

        Presenter presenter = new Presenter();
        presenter.startTest();


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
