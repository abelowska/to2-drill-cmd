package pl.edu.agh.to2;

public class Answer {
    private String content;

    private boolean isRight;

    public Answer(String content, boolean isRight) {
        this.content = content;
        this.isRight = isRight;
    }

    public String getContent() {
        return content;
    }

    public boolean isRight() {
        return isRight;
    }
}
