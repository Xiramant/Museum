package table4K.quiz;

import javafx.scene.Group;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static table4K.Main4K.RESOURCES_PATH;

public class QuizButtonSelectGroup extends Group {

    public void add(final QuizButtonSelect button) {
        this.getChildren().add(button);
        groupAction();
    }

    private void groupAction() {
        for(int i = 0; i < this.getChildren().size(); i++) {
            QuizButtonSelect temp = (QuizButtonSelect) this.getChildren().get(i);

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

    void setNonPush(final QuizButtonSelect tempEnter) {

        for(int i = 0; i < this.getChildren().size(); i++) {
            QuizButtonSelect temp = (QuizButtonSelect) this.getChildren().get(i);
            if (!temp.equals(tempEnter)) {
                temp.setNonePush();
            }
        }
    }


}
