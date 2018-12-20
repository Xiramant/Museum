package table4K.quiz;

import javafx.scene.Group;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static table4K.Main4K.RESOURCES_PATH;

public class QuizButtonSelectGroup extends Group {

    private boolean flagTest = false;

    public void setFlagTestOn() {
        this.flagTest = true;
        groupAction();
        System.out.println("flagTest = " + flagTest);
    }


    public void add(final QuizButtonSelect button) {
        this.getChildren().add(button);
        groupAction();
    }

    private void groupAction() {

        System.out.println("flagTest000 = " + flagTest);


        for(int i = 0; i < this.getChildren().size(); i++) {
            QuizButtonSelect temp = (QuizButtonSelect) this.getChildren().get(i);

            temp.setOnMouseClicked(event -> {
                if (!flagTest) {
                    temp.setOnPushInvert();
                    if (temp.isOnPush()) {
                        setNonPush(temp);
                    }
                }
            });
        }

    }

    void setNonPush(final QuizButtonSelect tempEnter) {
        for(int i = 0; i < this.getChildren().size(); i++) {
            QuizButtonSelect temp = (QuizButtonSelect) this.getChildren().get(i);
            if (!temp.equals(tempEnter)) {
                temp.setOnPush(false);
            }
        }
    }

    public boolean isOnPush() {
        for(int i = 0; i < this.getChildren().size(); i++) {
            QuizButtonSelect temp = (QuizButtonSelect) this.getChildren().get(i);
            if (temp.isOnPush()) {
                return true;
            }
        }

        return false;
    }

    public QuizButtonSelect getPushButton() {

        for(int i = 0; i < this.getChildren().size(); i++) {
            QuizButtonSelect temp = (QuizButtonSelect) this.getChildren().get(i);
            if (temp.isOnPush()) {
                return temp;
            }
        }

        return null;
    }
}
