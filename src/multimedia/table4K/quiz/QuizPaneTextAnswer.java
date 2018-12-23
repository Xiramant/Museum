package table4K.quiz;

import javafx.geometry.VPos;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import static table4K.quiz.Quiz.*;


public class QuizPaneTextAnswer extends Pane {

    private String answer;

    private Text answerText = new Text();

    public String getAnswer() {
        return answer;
    }

    void setQPTAText(final String answerNumber, final String answerEnter) {

        answer = answerEnter;

        answerText.setText(answerNumber + answerEnter);
        answerText.setFont(textFont);
        answerText.setFill(TEXT_COLOR);
        answerText.setTextOrigin(VPos.TOP);
        answerText.setWrappingWidth(QUESTION_TEXT_WIDTH_MAX - blockTextVerticalInterval);
        answerText.setLayoutX(blockTextVerticalInterval / 2);
        answerText.setLayoutY(lineTextVerticalInterval / 2);

        this.setPrefWidth(QUESTION_TEXT_WIDTH_MAX);
        this.setPrefHeight(answerText.getLayoutBounds().getHeight() + lineTextVerticalInterval);

        this.getChildren().add(answerText);
    }

    public void setQPTAFont() {
        answerText.setFont(textFont);
    }

    public void setQPTAHeight() {
        answerText.setLayoutY(lineTextVerticalInterval / 2);
        this.setPrefHeight(answerText.getLayoutBounds().getHeight() + lineTextVerticalInterval);
    }

}
