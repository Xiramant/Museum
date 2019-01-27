package table4K.view.map;

import javafx.scene.image.Image;
import table4K.view.IDisplayIcon;

import static table4K.data.IconData.getMapIconImage;
import static table4K.view.MainView.DEBUGGING_RATIO;

public class MapIcon implements IDisplayIcon {

    private static final Image MAP_IMAGE = getMapIconImage();
    private static final double MAP_ICON_WIDTH = 1547 / DEBUGGING_RATIO;
    private static final double MAP_ICON_X = 0 / DEBUGGING_RATIO;
    private static final double MAP_ICON_Y = 107 / DEBUGGING_RATIO;
    private static final String MAP_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -4, 4);";


    public Image getIconImage() {
        return MAP_IMAGE;
    }

    public double getIconWidth() {
        return MAP_ICON_WIDTH;
    }

    public double getIconX() {
        return MAP_ICON_X;
    }

    public double getIconY() {
        return MAP_ICON_Y;
    }

    public String getIconStyle() {
        return MAP_SHADOW;
    }

}
