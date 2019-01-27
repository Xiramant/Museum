package table4K.view.quiz;

import javafx.scene.image.Image;
import table4K.data.IconData;
import table4K.view.IDisplayIcon;

import static table4K.view.MainView.DEBUGGING_RATIO;


public class QuizIcon implements IDisplayIcon {

    private static final Image QUIZ_IMAGE = IconData.getQuizIconImage();
    private static final double QUIZ_ICON_WIDTH = 617 / DEBUGGING_RATIO;
    private static final double QUIZ_ICON_X = 1669 / DEBUGGING_RATIO;
    private static final double QUIZ_ICON_Y = 1697 / DEBUGGING_RATIO;
    private static final String QUIZ_SHADOW = "-fx-effect: dropshadow(gaussian, black, 15, 0.3, -2, 8);";


    public Image getIconImage() {
        return QUIZ_IMAGE;
    }

    public double getIconWidth() {
        return QUIZ_ICON_WIDTH;
    }

    public double getIconX() {
        return QUIZ_ICON_X;
    }

    public double getIconY() {
        return QUIZ_ICON_Y;
    }

    public String getIconStyle() {
        return QUIZ_SHADOW;
    }

}
