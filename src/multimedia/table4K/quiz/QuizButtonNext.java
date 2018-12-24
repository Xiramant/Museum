package table4K.quiz;

import java.io.File;

import static general.TouchWait.isTimeWaitEnd;
import static general.TouchWait.setTimeWait;
import static table4K.quiz.Quiz.*;

public class QuizButtonNext extends QuizButton {

    private QuizButtonMedia media;
    private QuizButtonTest test;

    QuizButtonNext(final File imageFile,
                   final QuizButtonMedia mediaEnter,
                   final QuizButtonTest testEnter) {
        super(imageFile);

        media = mediaEnter;
        test = testEnter;

        this.setOnMouseClicked(event -> {
            quizButtonNextAction();
        });

        this.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                quizButtonNextAction();
                setTimeWait();
            }
        });
    }

    //действия при нажатии на кнопку Следующий
    private void quizButtonNextAction() {

        if (test.getGroup().isOnPush()) {

            //если не проводилась проверка верности ответа,
            // то проверсти проверку
            if (!test.getGroup().isFlagTest()) {
                test.buttonTestAction();
            }

            //установить кнопку Медиа в ненажатое состояние
            media.setQuizButtonMediaNonPush();

            //вывод на экран следующего вопроса или результата викторины,
            // если достигнуто заданное количество вопросов
            if (player.getQuestionNumber() == QUESTION_MAX) {
                setResult();
                setButtonResult();
            } else {
                setQuestion();
                setButton();
            }
        }
    }
}
