package pl.edu.agh.to2.model.builder;

import pl.edu.agh.to2.model.Provider;
import pl.edu.agh.to2.model.grader.Statistics;
import pl.edu.agh.to2.model.parser.Parser;

import java.lang.reflect.InvocationTargetException;

public class Builder {
    private Provider provider;
    private Settings settings;

    public Builder(Settings settings) {
        this.settings = settings;
    }

    public void buildComponents() {
        provider = new Provider();

        try {
            provider.setGrader(settings.getGraderClass().newInstance());
            provider.setParser(settings.getParserClass().getDeclaredConstructor(String.class).newInstance(settings.getFilename()));
            provider.setQuestionBook(settings.getQuestionBookClass().getDeclaredConstructor(Parser.class).newInstance(provider.getParser()));

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        provider.setStatistics(new Statistics(provider.getGrader()));
        provider.getQuestionBook().initQuestions();

    }

    public Provider getProvider() {
        return provider;
    }
}
