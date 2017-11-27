package java.pl.edu.agh.to2.Model;

import java.pl.edu.agh.to2.Model.Grader.Grader;
import java.pl.edu.agh.to2.Model.Grader.Statistics;
import java.pl.edu.agh.to2.Model.Parser.Parser;
import java.pl.edu.agh.to2.Model.QuestionBook.QuestionBook;
import java.pl.edu.agh.to2.Model.Settings.Settings;

public class Builder
{
    private final Settings settings;
    private Statistics statistics;
    private Grader grader;
    private QuestionBook questionBook;
    private Parser parser;

    public Builder(Settings settings)
    {
        this.settings = settings;
    }
}
