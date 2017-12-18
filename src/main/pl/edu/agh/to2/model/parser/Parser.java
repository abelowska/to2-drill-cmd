package pl.edu.agh.to2.model.parser;


import pl.edu.agh.to2.model.question.Question;

import java.util.List;

public interface Parser {
    public List<Question> parseQuestions() throws ParseException;

}