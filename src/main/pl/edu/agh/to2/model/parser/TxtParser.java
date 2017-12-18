package pl.edu.agh.to2.model.parser;

import pl.edu.agh.to2.model.question.Answer;
import pl.edu.agh.to2.model.question.Question;
import pl.edu.agh.to2.model.question.QuestionBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO handling wrong input file format
public class TxtParser implements Parser {
    String filename;
    private static final Pattern ANSWER_PATTERN = Pattern.compile("(>>>)?+(.+)");
    private static final Pattern QUESTION_PATTERN = Pattern.compile("(\\*)?(.+)");

    public TxtParser(String filename) {
        this.filename = filename;
    }

    private Question parseQuestion(BufferedReader br, String title) throws IOException, ParseException {

        QuestionBuilder qb = new QuestionBuilder();

        Matcher m = QUESTION_PATTERN.matcher(title);

        if (m.matches()) {
            String g1 = m.group(1);
            qb.setTitle(m.group(2));
            qb.setMultipleChoice(g1 == null);
        }

        String line;

        while ((line = br.readLine()) != null) {
            if (!line.matches("\\s*")) {
                Answer answer = parseAnswer(line);
                qb.addAnswer(answer);
            } else {
                //return qb.constructQuestion();
                break;
            }
        }
        Question q = qb.constructQuestion();
        if (q.getAnswers().size() == 0) throw new ParseException("No answers");
        return qb.constructQuestion();
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
