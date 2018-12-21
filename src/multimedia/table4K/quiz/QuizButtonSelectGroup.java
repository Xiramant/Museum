package table4K.quiz;

import javafx.scene.Group;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import static general.TouchWait.isTimeWaitEnd;
import static general.TouchWait.setTimeWait;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.quiz.Quiz.setQuizScene;

public class QuizButtonSelectGroup extends Group {

    //Флаг запущенной проверки правильности ответа
    private boolean flagTest = false;

    public boolean isFlagTest() {
        return flagTest;
    }

    void setFlagTestOn() {
        this.flagTest = true;
        groupAction();
    }


    //добавление кнопки в группу
    public void add(final QuizButtonSelect button) {
        this.getChildren().add(button);

        groupAction();
    }

    //задание реакции группы кнопок на действия
    private void groupAction() {
        for(int i = 0; i < this.getChildren().size(); i++) {
            QuizButtonSelect temp = (QuizButtonSelect) this.getChildren().get(i);

            temp.setOnMouseClicked(event -> {
                buttonGroupAction(temp);
            });

            temp.setOnTouchReleased(event -> {
                if (isTimeWaitEnd()) {

                    buttonGroupAction(temp);

                    setTimeWait();
                }
            });
        }
    }

    //задание реакции отдельной кнопки, входящей в группу, на действие
    private void buttonGroupAction(final QuizButtonSelect buttonCurent) {

        if (!isFlagTest()) {

            buttonCurent.setOnPushInvert();

            if (buttonCurent.isOnPush()) {
                setNonPush(buttonCurent);
            }
        }
    }

    //установка всех остальных кнопок из группы,
    // кроме переданной в параметрах метода,
    // в состояние - не нажата
    void setNonPush(final QuizButtonSelect tempEnter) {
        for(int i = 0; i < this.getChildren().size(); i++) {
            QuizButtonSelect temp = (QuizButtonSelect) this.getChildren().get(i);
            if (!temp.equals(tempEnter)) {
                temp.setOnPush(false);
            }
        }
    }

    //возвращает true, если нажата хотябы одна кнопка из группы
    // в противном случае возвращает false
    public boolean isOnPush() {
        for(int i = 0; i < this.getChildren().size(); i++) {
            QuizButtonSelect temp = (QuizButtonSelect) this.getChildren().get(i);
            if (temp.isOnPush()) {
                return true;
            }
        }

        return false;
    }

    //возвращает кнопку из группы, которая нажата,
    // если не нажата ни одна из кнопок, то возвращает null
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
