package table4K.quiz;

import javafx.scene.text.Text;

import java.io.File;

import static table4K.Main4K.RESOURCES_PATH;

public class QuizButtonSelect extends QuizButton {

    public QuizPaneTextAnswer qpta;


    QuizButtonSelect(final String name, final QuizPaneTextAnswer qptaEnter) {
        super(name);

        qpta = qptaEnter;

        this.setOnMouseClicked(event -> {

            buttonStateInvert();

            if (isOnPush()) {
                qpta.setStyle("-fx-background-color: #464d46;");
            } else {
                qpta.setStyle("");
            }

        });
    }

    void setNonePush() {
        this.setOnPush(false);
        this.setImageBackground(new File(RESOURCES_PATH + "quiz/tv_button.png"));
        qpta.setStyle("");
    }
}
