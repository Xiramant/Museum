package table4K.quiz;

import javafx.scene.input.InputEvent;

import java.io.File;

import static general.TouchWait.*;
import static table4K.controller.ControllerParameters.isEventPermission;
import static table4K.quiz.Quiz.setQuizScene;

public class QuizButtonNew extends QuizButton {

    QuizButtonNew(final File imageFile) {
        super(imageFile);

        this.setOnMouseClicked(event -> quizButtonNewAction(event));
        this.setOnTouchReleased(event -> quizButtonNewAction(event));
    }

    //действия при нажатии на кнопку Новый
    private static void quizButtonNewAction(final InputEvent event) {
        if (isEventPermission(event)) {
            setQuizScene();
            eventDelayBegin();
        }
    }
}
