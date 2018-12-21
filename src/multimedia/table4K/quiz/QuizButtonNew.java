package table4K.quiz;

import java.io.File;

import static general.TouchWait.*;
import static table4K.quiz.Quiz.setQuizScene;

public class QuizButtonNew extends QuizButton {

    QuizButtonNew(final File imageFile) {
        super(imageFile);

        this.setOnMouseClicked(event -> {

            if (isTimeWaitEnd()) {
                setQuizScene();
                setTimeWait();
            }
        });

        this.setOnTouchReleased(event -> {
            quizButtonNewAction();
        });

        this.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {

                quizButtonNewAction();

                setTimeWait();
            }
        });
    }

    //действия при клике мышкой/таче на кнопке
    private static void quizButtonNewAction() {
        setQuizScene();
    }
}
