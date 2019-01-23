package table4K.ui.quiz;

import general.SectionKey;
import table4K.ui.IDisplayIcon;

import static table4K.ui.MainView.DEBUGGING_RATIO;
import static table4K.Main4K.RESOURCES_PATH;

public class QuizIcon implements IDisplayIcon {

    private static final String QUIZ_URL = "file:///" + RESOURCES_PATH + "icon/quiz_icon.png";
    private static final double QUIZ_ICON_WIDTH = 617 / DEBUGGING_RATIO;
    private static final double QUIZ_ICON_X = 1669 / DEBUGGING_RATIO;
    private static final double QUIZ_ICON_Y = 1697 / DEBUGGING_RATIO;
    private static final String QUIZ_SHADOW = "-fx-effect: dropshadow(gaussian, black, 15, 0.3, -2, 8);";
    private static final SectionKey QUIZ_KEY = SectionKey.QUIZ;

    public String getIconUrl() {
        return QUIZ_URL;
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

    public SectionKey getKey() {
        return QUIZ_KEY;
    }
}
