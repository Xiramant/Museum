package table4K.quiz;

import javafx.scene.Group;
import javafx.scene.input.InputEvent;

import static general.TouchWait.eventDelayBegin;
import static table4K.controller.ControllerParameters.isEventPermission;


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

            temp.setOnMouseClicked(event -> buttonGroupAction(event, temp));
            temp.setOnTouchReleased(event -> buttonGroupAction(event, temp));
        }
    }

    //задание реакции отдельной кнопки, входящей в группу, на действие
    private void buttonGroupAction(final InputEvent event, final QuizButtonSelect buttonCurrent) {

        if (isEventPermission(event) && !isFlagTest()) {

            buttonCurrent.setOnPushInvert();

            if (buttonCurrent.isOnPush()) {
                setNonPush(buttonCurrent);
            }

            eventDelayBegin();
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
