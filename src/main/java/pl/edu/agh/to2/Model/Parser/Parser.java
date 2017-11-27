package java.pl.edu.agh.to2.Model.Parser;


import java.pl.edu.agh.to2.Model.Question;

public interface Parser
{
    public Question parseQuestion();
    public boolean canParse();
}