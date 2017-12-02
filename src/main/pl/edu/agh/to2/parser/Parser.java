package pl.edu.agh.to2.parser;


import pl.edu.agh.to2.Question;

import java.util.List;

public interface Parser
{
    public List<Question> parseQuestions() throws ParseException;

}