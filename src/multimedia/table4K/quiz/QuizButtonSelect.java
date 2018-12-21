package table4K.quiz;

import javafx.scene.text.Text;

import java.io.File;

import static table4K.Main4K.RESOURCES_PATH;

public class QuizButtonSelect extends QuizButton {

    //панель с вариантом ответа,
    // с которой связана кнопка выбора ответа
    private QuizPaneTextAnswer qpta;

    //цвет фона панели при нажатой кнопке
    private String qptaSelectStyle = "-fx-background-color: #464d46;";

    //цвет фона панели при не нажатой кнопке
    private String qptaNoneSelectStyle = "";

    //цвет фона панели с правильным ответом
    private String qptaCorrectStyle = "-fx-background-color: #0b6007;";


    QuizButtonSelect(final String name, final QuizPaneTextAnswer qptaEnter) {
        super(name);

        qpta = qptaEnter;
    }

    @Override
    void setOnPush(final boolean onPush) {
        super.setOnPush(onPush);
        setQPTAStyle();
    }

    @Override
    void setOnPushInvert() {
        super.setOnPushInvert();
        setQPTAStyle();
    }

    //Установка цвета фона панели с вариантом ответа связанной с кнопкой выбора
    // при нажатой и отпущенной кнопке
    private void setQPTAStyle(){
        if (isOnPush()) {
            this.qpta.setStyle(qptaSelectStyle);
        } else {
            this.qpta.setStyle(qptaNoneSelectStyle);
        }
    }

    String getAnswer() {
        return qpta.getAnswer();
    }

    void qptaSetCorrectStyle() {
        this.qpta.setStyle(qptaCorrectStyle);
    }

}
