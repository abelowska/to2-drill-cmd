package pl.edu.agh.to2;

import pl.edu.agh.to2.grader.Grader;
import pl.edu.agh.to2.grader.StandardGrader;
import pl.edu.agh.to2.grader.Statistics;
import pl.edu.agh.to2.parser.Parser;
import pl.edu.agh.to2.parser.TxtParser;
import pl.edu.agh.to2.questionbook.QuestionBook;
import pl.edu.agh.to2.questionbook.StandardOrderQuestionBook;
import pl.edu.agh.to2.settings.Settings;

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


    public Statistics getStatistics()
    {
        return statistics;
    }

    public Grader getGrader()
    {
        return grader;
    }

    public QuestionBook getQuestionBook()
    {
        return questionBook;
    }

    public Parser getParser()
    {
        return parser;
    }

    public void buildComponents()
    {
        switch (settings.getParserType())
        {
            case TXT_PARSER:
                parser = new TxtParser(settings.getFilename());
        }

        switch (settings.getQuestionSortingType())
        {
            case FILE_ORDER:
                questionBook = new StandardOrderQuestionBook(parser);
        }

        switch (settings.getGraderType())
        {
            case STANDARD:
                grader = new StandardGrader();
        }

        statistics = new Statistics(grader);
        questionBook.getQuestions();
    }
}
