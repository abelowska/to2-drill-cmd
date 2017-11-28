package pl.edu.agh.to2;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class Question
{
    private String text;
    private List<Pair<Boolean, String>> answers;
    private int validAnswerCount;

    public Question()
    {
        text = "";
        validAnswerCount = 0;
        answers = new ArrayList<>();
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public List<Pair<Boolean, String>> getAnswers()
    {
        return answers;
    }

    public int getValidAnswerCount()
    {
        return validAnswerCount;
    }

    public void addAnswer(boolean valid, String text)
    {
        answers.add(new Pair<Boolean, String>(valid, text));
        if(valid)
            validAnswerCount++;
    }
}