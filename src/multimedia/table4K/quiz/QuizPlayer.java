package table4K.quiz;

public class QuizPlayer {

    private int questionNumber = 0;

    private int correctAnswer = 0;

    private int receivePoints = 0;

    private int maxPoints = 0;

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void incrementQuestionNumber() {
        this.questionNumber ++;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void incrementCorrectAnswer() {
        this.correctAnswer ++;
    }

    public int getReceivePoints() {
        return receivePoints;
    }

    public void setReceivePoints(final int receivePoints) {
        this.receivePoints += receivePoints;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(final int maxPoints) {
        this.maxPoints += maxPoints;
    }

}
