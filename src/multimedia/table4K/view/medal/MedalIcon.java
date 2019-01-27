package table4K.view.medal;

import javafx.scene.image.Image;
import table4K.data.IconData;
import table4K.view.IDisplayIcon;

import static table4K.view.MainView.DEBUGGING_RATIO;


public class MedalIcon implements IDisplayIcon {

    private static final Image MEDAL_IMAGE = IconData.getMedalIconImage();
    private static final double MEDAL_ICON_WIDTH = 526 / DEBUGGING_RATIO;
    private static final double MEDAL_ICON_X = 66 / DEBUGGING_RATIO;
    private static final double MEDAL_ICON_Y = 1653 / DEBUGGING_RATIO;
    private static final String MEDAL_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -4, 4);";


    public Image getIconImage() {
        return MEDAL_IMAGE;
    }

    public double getIconWidth() {
        return MEDAL_ICON_WIDTH;
    }

    public double getIconX() {
        return MEDAL_ICON_X;
    }

    public double getIconY() {
        return MEDAL_ICON_Y;
    }

    public String getIconStyle() {
        return MEDAL_SHADOW;
    }

}
