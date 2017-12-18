package pl.edu.agh.to2.model.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private int id;
    private String title;
    private boolean isMultipleChoice;
    private List<Answer> answers;

    public Question(int id, String title, boolean isMultipleChoice, List<Answer> answers) {
        this.id = id;
        this.title = title;
        this.isMultipleChoice = isMultipleChoice;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isMultipleChoice() {
        return isMultipleChoice;
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public int getValidAnswerCount() {
        return (int) answers.stream().filter(a -> a.isRight()).count();
    }


    @Override
    public boolean equals(Object o) {
        if (o.getClass() == getClass()) {
            return ((Question) o).id == this.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}