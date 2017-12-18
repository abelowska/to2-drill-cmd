package pl.edu.agh.to2.model.question;

import java.util.ArrayList;
import java.util.List;

public class QuestionBuilder {
    private int questionId;
    private String title;
    private boolean isMultipleChoice;
    private List<Answer> answers;

    public QuestionBuilder() {
        answers = new ArrayList<>();
    }

    public void generateId() {
        questionId = IdGenerator.getGenerator().generateId();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMultipleChoice(boolean multipleChoice) {
        isMultipleChoice = multipleChoice;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public Question constructQuestion() {
        return new Question(questionId, title, isMultipleChoice, answers);
    }
}
