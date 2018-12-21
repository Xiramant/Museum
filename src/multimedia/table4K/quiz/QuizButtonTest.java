package table4K.quiz;

import static general.TouchWait.isTimeWaitEnd;
import static general.TouchWait.setTimeWait;
import static table4K.quiz.Quiz.pointsReceivedText;
import static table4K.quiz.Quiz.selectQuestion;
import static table4K.quiz.Quiz.player;

public class QuizButtonTest extends QuizButton {

    private String correctAnswer;

    private QuizButtonSelectGroup group;

    public QuizButtonSelectGroup getGroup() {
        return group;
    }


    public QuizButtonTest(final String nameEnter,
                          final String correctAnswerEnter,
                          final QuizButtonSelectGroup groupEnter) {
        super(nameEnter);

        correctAnswer = correctAnswerEnter;

        group =  groupEnter;

        this.setOnMouseClicked(event -> {
            buttonTestAction();
        });

        this.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {

                buttonTestAction();

                setTimeWait();
            }
        });
    }

    public void buttonTestAction() {

        if (group.isOnPush()) {
            if (!this.isOnPush()) {
                super.setOnPush(true);
                group.setFlagTestOn();

                selectCorrectAnswer();

                player.incrementMaxPoints(selectQuestion.getPoints());

                if (group.getPushButton().getAnswer().equals(correctAnswer)) {
                    player.incrementCorrectAnswer();
                    player.incremenrReceivePoints(selectQuestion.getPoints());
                }

                setPointsReceivedText();
            }
        }
    }

    //задание зеленого фона для правильного ответа
    private void selectCorrectAnswer() {
        for (int i = 0; i < group.getChildren().size(); i++) {
            QuizButtonSelect temp = (QuizButtonSelect) group.getChildren().get(i);
            if (temp.getAnswer().equals(correctAnswer)) {
                temp.qptaSetCorrectStyle();
            }
        }
    }

    //отображение количества полученных баллов за ответ
    private void setPointsReceivedText() {

        StringBuilder point = new StringBuilder();
        point.append(" + ");

        if (group.getPushButton().getAnswer().equals(correctAnswer)) {

            point.append(selectQuestion.getPoints());
            if (selectQuestion.getPoints() == 1) {
                point.append(" балл");
            } else {
                point.append(" балла");
            }

        } else {
            point.append("0 баллов");
        }

        pointsReceivedText.setText(pointsReceivedText.getText() + point.toString());
    }



}
