package pl.edu.agh.to2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private String title;
    private String header;
    private boolean isMultipleChoice;
    private List<Answer> answers;

    public Question() {
        title = "";
        header = "unnamed";
        isMultipleChoice = true;
        answers = new ArrayList<>();
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMultipleChoice() {
        return isMultipleChoice;
    }

    public void setMultipleChoice(boolean multipleChoice) {
        isMultipleChoice = multipleChoice;
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public int getValidAnswerCount() {
        return (int) answers.stream().filter(a -> a.isRight()).count();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == getClass()) {
            return ((Question) o).header.equals(this.getHeader());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return header.hashCode();
    }
}