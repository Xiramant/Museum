package table4K.controller.quiz;

import javafx.scene.Node;
import table4K.controller.IconController;

import static table4K.quiz.Quiz.setQuizScene;


public class QuizIconController extends IconController {

    public QuizIconController(final Node quizIconArg) {
        super(quizIconArg);
    }

    public void action(){
        setQuizScene();
    }

}