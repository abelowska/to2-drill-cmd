package pl.edu.agh.to2.model.parser;

import pl.edu.agh.to2.model.Answer;
import pl.edu.agh.to2.model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO handling wrong input file format
public class TxtParser implements Parser {
    String filename;
    private static final Pattern ANSWER_PATTERN = Pattern.compile("(>>>)?(.+)");
    private static final Pattern HEADER_PATTERN = Pattern.compile("(\\*)?(.+)");

    public TxtParser(String filename) {
        this.filename = filename;
    }

    private Question parseQuestion(BufferedReader br, String header) throws IOException, ParseException {
        Question q = new Question();

        Matcher m = HEADER_PATTERN.matcher(header);

        if (m.matches()) {
            String g1 = m.group(1);
            q.setHeader(m.group(2));
            q.setMultipleChoice(g1 == null);
        }

        String line = br.readLine();
        if (line == null) {
            throw new ParseException("Question without content");
        }
        q.setTitle(line);

        while ((line = br.readLine()) != null) {
            if (!line.matches("\\s*")) {
                Answer answer = parseAnswer(line);
                q.addAnswer(answer);
            } else {
                return q;
            }
        }
        return q;
    }

    private Answer parseAnswer(String line) throws ParseException {
        Matcher m = ANSWER_PATTERN.matcher(line);


        if (m.matches()) {
            String g1 = m.group(1);
            return new Answer(m.group(2), g1 != null);
        }
        throw new ParseException("Wrong format of answer");
    }

    @Override
    public List<Question> parseQuestions() throws ParseException {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.matches("\\s*")) {
                    Question question = parseQuestion(br, line);
                    questions.add(question);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;

    }
}
