package table4K.quiz;

import javafx.scene.input.InputEvent;

import java.io.File;

import static general.TouchWait.isTimeWaitEnd;
import static general.TouchWait.setTimeWait;
import static table4K.Main4K.actionPermission;
import static table4K.quiz.Quiz.*;

public class QuizButtonNext extends QuizButton {

    private QuizButtonMedia media;
    private QuizButtonTest test;

    private static final long RESULT_TIMEOUT = 3000;

    QuizButtonNext(final File imageFile,
                   final QuizButtonMedia mediaEnter,
                   final QuizButtonTest testEnter) {
        super(imageFile);

        media = mediaEnter;
        test = testEnter;

        this.setOnMouseClicked(event -> quizButtonNextAction(event));
        this.setOnTouchReleased(event -> quizButtonNextAction(event));
    }

    //действия при нажатии на кнопку Следующий
    private void quizButtonNextAction(final InputEvent event) {
        if (isTimeWaitEnd() && actionPermission(event)) {

            if (test.getGroup().isOnPush()) {

                //если не проводилась проверка верности ответа,
                // то проверсти проверку
                if (!test.getGroup().isFlagTest()) {
                    test.buttonTestActionGeneral();
                }

                //установить кнопку Медиа в ненажатое состояние
                media.setQuizButtonMediaNonPush();

                //вывод на экран следующего вопроса или результата викторины,
                // если достигнуто заданное количество вопросов
                if (player.getQuestionNumber() == QUESTION_MAX) {
                    setResult();
                    setButtonResult();

                    //задержка с длительным временем,
                    // чтобы игрок успел посмотреть результат,
                    // прежде чем выйти из него
                    setTimeWait(RESULT_TIMEOUT);
                    return;
                } else {
                    setQuestion();
                    setButton();
                }
            }

            setTimeWait();
        }
    }
}
