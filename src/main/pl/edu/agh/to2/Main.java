package pl.edu.agh.to2;

public class Main
{
    public static void main(String[] args)
    {
        ConsolePresenter presenter = new ConsolePresenter();

        Builder builder = new Builder(presenter.getSettings());
        builder.buildComponents();

        presenter.setQuestionBook(builder.getQuestionBook());
        presenter.setStatistics(builder.getStatistics());


        while(!presenter.isExit())
        {
            presenter.askNextQuestion();
        }

        presenter.showStatistics();

    }
}
