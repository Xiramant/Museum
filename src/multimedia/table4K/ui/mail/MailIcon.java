package table4K.ui.mail;

import table4K.ui.IDisplayIcon;

import static table4K.Main4K.DEBUGGING_RATIO;
import static table4K.Main4K.RESOURCES_PATH;

public class MailIcon implements IDisplayIcon {

    private static final String MAIL_URL = "file:///" + RESOURCES_PATH + "icon/mail_icon.png";
    private static final double MAIL_ICON_WIDTH = 579 / DEBUGGING_RATIO;
    private static final double MAIL_ICON_X = 748 / DEBUGGING_RATIO;
    private static final double MAIL_ICON_Y = 1242 / DEBUGGING_RATIO;
    private static final String MAIL_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -4, 4);";

    public String getIconUrl() {
        return MAIL_URL;
    }

    public double getIconWidth() {
        return MAIL_ICON_WIDTH;
    }

    public double getIconX() {
        return MAIL_ICON_X;
    }

    public double getIconY() {
        return MAIL_ICON_Y;
    }

    public String getIconStyle() {
        return MAIL_SHADOW;
    }

}
