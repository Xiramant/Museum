package table4K.quiz;

import javafx.geometry.VPos;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import static table4K.quiz.Quiz.*;


public class QuizPaneTextAnswer extends Pane {

    public Text answerText = new Text();

    public void setQPTAText(final String answerString) {

        answerText.setText(answerString);
        answerText.setFont(TEXT_FONT);
        answerText.setFill(TEXT_COLOR);
        answerText.setTextOrigin(VPos.TOP);
        answerText.setWrappingWidth(QUESTION_TEXT_WIDTH_MAX - BLOCK_TEXT_VERTICAL_INTERVAL);
        answerText.setLayoutX(BLOCK_TEXT_VERTICAL_INTERVAL / 2);
        answerText.setLayoutY(LINE_TEXT_VERTICAL_INTERVAL / 2);

        this.setPrefWidth(QUESTION_TEXT_WIDTH_MAX);
        this.setPrefHeight(answerText.getLayoutBounds().getHeight() + LINE_TEXT_VERTICAL_INTERVAL);

        this.getChildren().add(answerText);
    }

}