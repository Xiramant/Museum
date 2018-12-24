package table4K.quiz;

import java.io.File;

import static general.TouchWait.*;
import static table4K.quiz.Quiz.setQuizScene;

public class QuizButtonNew extends QuizButton {

    QuizButtonNew(final File imageFile) {
        super(imageFile);

        this.setOnMouseClicked(event -> {
            quizButtonNewAction();
        });

        this.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                quizButtonNewAction();
                setTimeWait();
            }
        });
    }

    //действия при нажатии на кнопку Новый
    private static void quizButtonNewAction() {
        setQuizScene();
    }
}
