package pl.edu.agh.to2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private int id;
    private String title;
    private boolean isMultipleChoice;
    private List<Answer> answers;
    private AverageQuestionRate rate;

    public Question(int id, String title, boolean isMultipleChoice, List<Answer> answers) {
        this.id = id;
        this.title = title;
        this.isMultipleChoice = isMultipleChoice;
        this.answers = new ArrayList<>(answers);
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

    public AverageQuestionRate getRate() {
        return rate;
    }

    public void setRate(AverageQuestionRate rate) {
        this.rate = rate;
    }

    public void updateRate(QuestionRate questionRate){
        if(this.rate != null){
            this.rate.updateAverageRate(questionRate);
        }
        else {
            this.rate = new AverageQuestionRate(id);
            this.rate.updateAverageRate(questionRate);
        }
    }


    public void shuffleAnswers(){ Collections.shuffle(answers); }

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