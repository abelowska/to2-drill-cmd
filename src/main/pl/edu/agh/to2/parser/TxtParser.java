package pl.edu.agh.to2.parser;

import pl.edu.agh.to2.Question;

import java.util.ArrayList;
import java.util.List;

public class TxtParser implements Parser
{
    String filename;

    public TxtParser(String filename)
    {
        this.filename = filename;
    }

    @Override
    public List<Question> parseQuestions()
    {
        Question question = new Question();

        question.setText("Sample Question");
        question.addAnswer(false, "Anserw 1");
        question.addAnswer(false, "Anserw 2");
        question.addAnswer(true, "Anserw 3");
        question.addAnswer(true, "Anserw 4");

        List<Question> questions = new ArrayList<>();
        questions.add(question);

        return questions;

    }
}
