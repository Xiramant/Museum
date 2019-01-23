package table4K.ui.medal;

import table4K.ui.IDisplayIcon;

import static table4K.Main4K.DEBUGGING_RATIO;
import static table4K.Main4K.RESOURCES_PATH;

public class MedalIcon implements IDisplayIcon {

    private static final String MEDAL_URL = "file:///" + RESOURCES_PATH + "icon/medal_icon.png";
    private static final double MEDAL_ICON_WIDTH = 526 / DEBUGGING_RATIO;
    private static final double MEDAL_ICON_X = 66 / DEBUGGING_RATIO;
    private static final double MEDAL_ICON_Y = 1653 / DEBUGGING_RATIO;
    private static final String MEDAL_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -4, 4);";

    public String getIconUrl() {
        return MEDAL_URL;
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
