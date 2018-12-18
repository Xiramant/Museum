package table4K.quiz;

import javafx.scene.Group;

import java.io.File;
import java.util.ArrayList;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static table4K.Main4K.RESOURCES_PATH;

public class QuizButtonSelectGroup extends Group {

    ArrayList<QuizButtonSelect> buttonGroup = new ArrayList<>();

    QuizButtonSelectGroup(final ArrayList<QuizButtonSelect> buttonGroupEnter) {
        buttonGroup = buttonGroupEnter;

        for (QuizButtonSelect temp: buttonGroup) {
            this.getChildren().add(temp);
        }

        for(QuizButtonSelect temp : buttonGroup) {
            temp.setOnMouseClicked(event -> {

                temp.setOnPushInvert();

                if (temp.isOnPush()) {
                    temp.setImageBackground(new File(RESOURCES_PATH + "quiz/tv_button_shadow.png"));
                    temp.qpta.setStyle("-fx-background-color: #464d46;");
                    setNonPush(temp);
                } else {
                    temp.setImageBackground(new File(RESOURCES_PATH + "quiz/tv_button.png"));
                    temp.qpta.setStyle("");
                }

            });

        }

    }

    public void setButtonGroup(final QuizButtonSelect button) {
        this.buttonGroup.add(button);
    }

    void setNonPush(final QuizButtonSelect tempEnter) {

        for(int i = 0; i < buttonGroup.size(); i++) {
            QuizButtonSelect temp = buttonGroup.get(i);
            if (!temp.equals(tempEnter)) {
                temp.setNonePush();
            }
        }
    }


}
