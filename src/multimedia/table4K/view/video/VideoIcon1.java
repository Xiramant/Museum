package table4K.view.video;

import javafx.scene.image.Image;
import table4K.data.IconData;
import table4K.view.IDisplayIcon;

import static table4K.view.MainView.DEBUGGING_RATIO;


public class VideoIcon1 implements IDisplayIcon {

    private static final Image VIDEO_ICON_1_IMAGE = IconData.getFilmIcon1Image();
    private static final double VIDEO_ICON_1_WIDTH = 584 / DEBUGGING_RATIO;
    private static final double VIDEO_ICON_1_X = 3244 / DEBUGGING_RATIO;
    private static final double VIDEO_ICON_1_Y = 146 / DEBUGGING_RATIO;
    private static final String VIDEO_ICON_1_SHADOW = "-fx-effect: dropshadow(gaussian, black, 15, 0.3, 8, 8);";


    public Image getIconImage() {
        return VIDEO_ICON_1_IMAGE;
    }

    public double getIconWidth() {
        return VIDEO_ICON_1_WIDTH;
    }

    public double getIconX() {
        return VIDEO_ICON_1_X;
    }

    public double getIconY() {
        return VIDEO_ICON_1_Y;
    }

    public String getIconStyle() {
        return VIDEO_ICON_1_SHADOW;
    }

}
