package table4K.view.video;

import javafx.scene.image.Image;
import table4K.data.IconData;
import table4K.view.IDisplayIcon;

import static table4K.view.MainView.DEBUGGING_RATIO;


public class VideoIcon2 implements IDisplayIcon {

    private static final Image VIDEO_ICON_2_IMAGE = IconData.getFilmIcon2Image();
    private static final double VIDEO_ICON_2_WIDTH = 536 / DEBUGGING_RATIO;
    private static final double VIDEO_ICON_2_X = 3422 / DEBUGGING_RATIO;
    private static final double VIDEO_ICON_2_Y = 369 / DEBUGGING_RATIO;
    private static final String VIDEO_ICON_2_SHADOW = "-fx-effect: dropshadow(gaussian, black, 15, 0.3, 8, 8);";


    public Image getIconImage() {
        return VIDEO_ICON_2_IMAGE;
    }

    public double getIconWidth() {
        return VIDEO_ICON_2_WIDTH;
    }

    public double getIconX() {
        return VIDEO_ICON_2_X;
    }

    public double getIconY() {
        return VIDEO_ICON_2_Y;
    }

    public String getIconStyle() {
        return VIDEO_ICON_2_SHADOW;
    }

}
