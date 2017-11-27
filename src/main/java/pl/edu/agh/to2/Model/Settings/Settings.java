package java.pl.edu.agh.to2.Model.Settings;

public class Settings
{
    private ParserType parserType;
    private QuestionSortingType questionSortingType;
    private String filename;

    Settings(SettingsBuilder builder)
    {
        parserType = builder.parserType;
        questionSortingType = builder.questionSortingType;
    }

    public ParserType getType()
    {
        return parserType;
    }

    public QuestionSortingType getSorting()
    {
        return questionSortingType;
    }

    public String getFilename()
    {
        return filename;
    }

    public SettingsBuilder createSettings()
    {
        return new SettingsBuilder();
    }

    private static class SettingsBuilder
    {
        private ParserType parserType;
        private QuestionSortingType questionSortingType;
        private String filename;

        SettingsBuilder()
        {
            parserType = ParserType.TXT_PARSER;
            questionSortingType = QuestionSortingType.FILE_ORDER;
        }

        public SettingsBuilder parserType(ParserType parserType)
        {
            this.parserType = parserType;
            return this;
        }

        public SettingsBuilder questionSorting(QuestionSortingType questionSortingType)
        {
            this.questionSortingType = questionSortingType;
            return this;
        }

        private SettingsBuilder filename(String filename)
        {
            this.filename = filename;
            return this;
        }

        public Settings build()
        {
            return new Settings(this);
        }
    }
}
