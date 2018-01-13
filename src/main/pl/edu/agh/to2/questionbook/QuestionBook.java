package pl.edu.agh.to2.questionbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pl.edu.agh.to2.model.Provider;
import pl.edu.agh.to2.model.AverageQuestionRate;
import pl.edu.agh.to2.model.Question;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public interface QuestionBook {

     static void assignRates(List<Question> questions) {
         Gson gson = new Gson();

         if (Files.exists(Paths.get(Provider.Builder.FILENAME))) {

             try (Reader reader = new FileReader(Provider.Builder.FILENAME)) {
                 Type listType = new TypeToken<List<AverageQuestionRate>>() {
                 }.getType();
                 List<AverageQuestionRate> rates = gson.fromJson(reader, listType);
                 for (Question q : questions) {
                     rates.stream().filter(rate -> q.getId() == rate.getQuestionId()).findFirst().ifPresent(r -> q.setRate(r));
                 }
             } catch (IOException ioe) {
                 ioe.printStackTrace();
             }

         }
     }

    void initQuestions();

    Question nextQuestion();

    boolean hasNextQuestion();

    List<Question> getQuestions();
}
