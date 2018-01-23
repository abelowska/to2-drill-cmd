package pl.edu.agh.to2.questionbook;

import pl.edu.agh.to2.model.AverageQuestionRate;
import pl.edu.agh.to2.model.Question;
import pl.edu.agh.to2.serialization.SerializerFactory;

import java.util.List;

import static pl.edu.agh.to2.provider.Provider.Builder.RATES_FILE;

public class RatesAssigner {

     static void assignRates(List<Question> questions) {
        List<AverageQuestionRate> rates = SerializerFactory.createJsonSerializer().readRates(RATES_FILE);

        for (Question q : questions) {
            rates.stream().filter(rate -> q.getId() == rate.getQuestionId()).findFirst().ifPresent(r -> q.setRate(r));
        }

    }
}
