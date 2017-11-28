package pl.edu.agh.to2.settings;

public class Settings
{
    private ParserType parserType;
    private QuestionSortingType questionSortingType;
    private GraderType graderType;
    private String filename;

    Settings(SettingsBuilder builder)
    {
        parserType = builder.parserType;
        questionSortingType = builder.questionSortingType;
        graderType = builder.graderType;
        filename = builder.filename;
    }

    public ParserType getParserType()
    {
        return parserType;
    }

    public QuestionSortingType getQuestionSortingType()
    {
        return questionSortingType;
    }

    public String getFilename()
    {
        return filename;
    }

    public GraderType getGraderType()
    {
        return graderType;
    }

    public static SettingsBuilder createSettings()
    {
        return new SettingsBuilder();
    }

    public static class SettingsBuilder
    {
        private ParserType parserType;
        private QuestionSortingType questionSortingType;
        private GraderType graderType;
        private String filename;

        SettingsBuilder()
        {
            parserType = ParserType.TXT_PARSER;
            questionSortingType = QuestionSortingType.FILE_ORDER;
            graderType = GraderType.STANDARD;
        }

        public SettingsBuilder parserType(ParserType parserType)
        {
            this.parserType = parserType;
            return this;
        }

        public SettingsBuilder questionSortingType(QuestionSortingType questionSortingType)
        {
            this.questionSortingType = questionSortingType;
            return this;
        }

        public SettingsBuilder graderType(GraderType graderType)
        {
            this.graderType = graderType;
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
