package pl.edu.agh.to2.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import pl.edu.agh.to2.grader.Statistics;
import pl.edu.agh.to2.model.AverageQuestionRate;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonSerializer implements Serializer {

    @Override
    public void saveStatistics(Statistics actualStats, String pathToFile) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Statistics> statistics = new ArrayList<>();

        File f = new File(pathToFile);

        if(f.exists()){
            statistics.addAll(readStatistics(f.getAbsolutePath()));
        }
        statistics.add(actualStats);

        try (FileWriter writer = new FileWriter(f)) {
            gson.toJson(statistics, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveRatings(List<AverageQuestionRate> rates, String pathToFile) {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter(pathToFile)) {
            gson.toJson(rates, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AverageQuestionRate> readRates(String pathToFile) {
        Gson gson = new Gson();
        List<AverageQuestionRate> rates = new ArrayList<>();
        if (Files.exists(Paths.get(pathToFile))) {

            try (Reader reader = new FileReader(pathToFile)) {
                Type listType = new TypeToken<List<AverageQuestionRate>>() {}.getType();
                rates = gson.fromJson(reader, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rates;
    }

    @Override
    public List<Statistics> readStatistics (String pathToFile){
        Gson gson = new Gson();
        List<Statistics> stats = new ArrayList<>();
        if (Files.exists(Paths.get(pathToFile))) {

            try (Reader reader = new FileReader(pathToFile)) {
                Type listType = new TypeToken<List<Statistics>>() {}.getType();
                stats = gson.fromJson(reader, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stats;
    }
}
