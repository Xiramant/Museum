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

    @Override
    public void setOnPush(final boolean onPush) {
        super.setOnPush(onPush);
        setQPTAStyle();
    }

    @Override
    public void setOnPushInvert() {
        super.setOnPushInvert();
        setQPTAStyle();
    }

    private void setQPTAStyle(){
        if (isOnPush()) {
            this.qpta.setStyle("-fx-background-color: #464d46;");
        } else {
            this.qpta.setStyle("");
        }
    }
//
//    private void setQPTAStyleCorrectAnswer(){
//        this.qpta.setStyle("-fx-background-color: #464d46;");
//    }

}
