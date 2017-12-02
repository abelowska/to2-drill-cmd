package pl.edu.agh.to2.settings;

public enum Settings
{
    SETTINGS;

    private ParserType parserType;
    private QuestionSortingType questionSortingType;
    private GraderType graderType;

    private String filename;

    private Settings()
    {

        parserType = ParserType.TXT_PARSER;
        questionSortingType = QuestionSortingType.FILE_ORDER;
        graderType = GraderType.STANDARD;
        filename = "";
    }

    public ParserType getParserType()
    {
        return parserType;
    }

    public void setParserType(ParserType parserType) {
        this.parserType = parserType;
    }

    public QuestionSortingType getQuestionSortingType()
    {
        return questionSortingType;
    }

    public void setQuestionSortingType(QuestionSortingType questionSortingType) {
        this.questionSortingType = questionSortingType;
    }

    public GraderType getGraderType()
    {
        return graderType;
    }

    public void setGraderType(GraderType graderType) {
        this.graderType = graderType;
    }

    public String getFilename()
    {
        return filename;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }

}
