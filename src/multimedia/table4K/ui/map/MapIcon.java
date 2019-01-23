package table4K.ui.map;

import general.SectionKey;
import table4K.ui.IDisplayIcon;

import static table4K.ui.MainView.DEBUGGING_RATIO;
import static table4K.Main4K.RESOURCES_PATH;

public class MapIcon implements IDisplayIcon {

    private static final String MAP_URL = "file:///" + RESOURCES_PATH + "icon/map_icon.png";
    private static final double MAP_ICON_WIDTH = 1547 / DEBUGGING_RATIO;
    private static final double MAP_ICON_X = 0 / DEBUGGING_RATIO;
    private static final double MAP_ICON_Y = 107 / DEBUGGING_RATIO;
    private static final String MAP_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -4, 4);";
    private static final SectionKey MAP_KEY = SectionKey.MAP;

    public String getIconUrl() {
        return MAP_URL;
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

    public SectionKey getKey() {
        return MAP_KEY;
    }

}
