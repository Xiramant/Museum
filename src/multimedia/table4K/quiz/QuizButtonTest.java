package table4K.quiz;

public class QuizButtonTest extends QuizButton {

    private String correctAnswer;

    private QuizButtonSelectGroup group;

    public QuizButtonTest(final String nameEnter,
                          final String correctAnswerEnter,
                          final QuizButtonSelectGroup groupEnter) {
        super(nameEnter);

        correctAnswer = correctAnswerEnter;

        group =  groupEnter;

        this.setOnMouseClicked(event -> {

            if (group.isOnPush()) {
                if (!isOnPush()) {
                    super.setOnPush(true);
                    group.setFlagTestOn();
                    System.out.println("правильный ответ = " + correctAnswer);
                    for (int i = 0; i < group.getChildren().size(); i++) {
                        QuizButtonSelect temp = (QuizButtonSelect) group.getChildren().get(i);
                        System.out.println("вариант ответа = " + temp.qpta.answer);
                        if (temp.qpta.answer.equals(correctAnswer)) {
                            temp.qpta.setStyle("-fx-background-color: #ffffff;");
                        }
                    }
                    if (group.getPushButton().qpta.answer.equals(correctAnswer)) {
                        System.out.println("верно");
                    }
                }
            }

        });
    }


}
