package pl.edu.agh.to2.model;

public class AverageQuestionRate {

    private double averageRate = 0.0;
    private int rateAmount = 0;
    private int questionId;


    public AverageQuestionRate(int questionId) {
        this.questionId = questionId;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public int getRateAmount() {
        return rateAmount;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void updateAverageRate(QuestionRate rate){
        averageRate = (averageRate*rateAmount + rate.getUserRate())/++rateAmount;
    }

}
