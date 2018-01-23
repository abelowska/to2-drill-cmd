package pl.edu.agh.to2.questionbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pl.edu.agh.to2.provider.Provider;
import pl.edu.agh.to2.model.AverageQuestionRate;
import pl.edu.agh.to2.model.Question;
import pl.edu.agh.to2.serialization.Serializer;
import pl.edu.agh.to2.serialization.SerializerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static pl.edu.agh.to2.provider.Provider.Builder.RATES_FILE;

public interface QuestionBook {

    void initQuestions();

    Question nextQuestion();

    boolean hasNextQuestion();

    List<Question> getQuestions();
}
