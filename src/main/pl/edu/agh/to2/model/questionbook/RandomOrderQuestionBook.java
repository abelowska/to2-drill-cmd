package pl.edu.agh.to2.model.questionbook;

import pl.edu.agh.to2.model.parser.ParseException;
import pl.edu.agh.to2.model.parser.Parser;
import pl.edu.agh.to2.model.question.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RandomOrderQuestionBook implements QuestionBook {
    List<Question> questions;
    Iterator<Question> it;
    Parser parser;

    public RandomOrderQuestionBook(Parser parser) {
        this.parser = parser;
        questions = new ArrayList<>();
    }

    @Override
    public void initQuestions() {
        try {
            questions = parser.parseQuestions();
            Collections.shuffle(questions);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        it = questions.iterator();
    }

    @Override
    public Question nextQuestion() {
        if (it.hasNext()) {
            Question q = it.next();
            q.shuffleAnswers();
            return q;
        }
        else
            return null;
    }

    @Override
    public boolean hasNextQuestion() { return it.hasNext(); }
}
