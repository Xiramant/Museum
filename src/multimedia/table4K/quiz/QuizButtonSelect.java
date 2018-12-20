package table4K.quiz;

import javafx.scene.text.Text;

import java.io.File;

import static table4K.Main4K.RESOURCES_PATH;

public class QuizButtonSelect extends QuizButton {

    public QuizPaneTextAnswer qpta;


    QuizButtonSelect(final String name, final QuizPaneTextAnswer qptaEnter) {
        super(name);

        qpta = qptaEnter;
    }

    void setNonePush() {
        this.setOnPush(false);
        this.setImageBackground(new File(RESOURCES_PATH + "quiz/tv_button.png"));
        qpta.setStyle("");
    }
}
