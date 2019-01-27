package table4K.view.mail;

import javafx.scene.image.Image;
import table4K.data.IconData;
import table4K.view.IDisplayIcon;

import static table4K.view.MainView.DEBUGGING_RATIO;


public class MailIcon implements IDisplayIcon {

    private static final Image MAIL_IMAGE = IconData.getMailIconImage();
    private static final double MAIL_ICON_WIDTH = 579 / DEBUGGING_RATIO;
    private static final double MAIL_ICON_X = 748 / DEBUGGING_RATIO;
    private static final double MAIL_ICON_Y = 1242 / DEBUGGING_RATIO;
    private static final String MAIL_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -4, 4);";


    public Image getIconImage() {
        return MAIL_IMAGE;
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
