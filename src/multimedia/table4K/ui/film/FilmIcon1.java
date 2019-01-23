package table4K.ui.film;

import general.SectionKey;
import table4K.ui.IDisplayIcon;

import static table4K.ui.MainView.DEBUGGING_RATIO;
import static table4K.Main4K.RESOURCES_PATH;

public class FilmIcon1 implements IDisplayIcon {

    private static final String FILM1_URL = "file:///" + RESOURCES_PATH + "icon/film1_icon.png";
    private static final double FILM1_ICON_WIDTH = 584 / DEBUGGING_RATIO;
    private static final double FILM1_ICON_X = 3244 / DEBUGGING_RATIO;
    private static final double FILM1_ICON_Y = 146 / DEBUGGING_RATIO;
    private static final String FILM_SHADOW = "-fx-effect: dropshadow(gaussian, black, 15, 0.3, 8, 8);";
    private static final SectionKey FILM_KEY = SectionKey.FILM;

    public String getIconUrl() {
        return FILM1_URL;
    }

    public double getIconWidth() {
        return FILM1_ICON_WIDTH;
    }

    public double getIconX() {
        return FILM1_ICON_X;
    }

    public double getIconY() {
        return FILM1_ICON_Y;
    }

    public String getIconStyle() {
        return FILM_SHADOW;
    }

    public SectionKey getKey() {
        return FILM_KEY;
    }

}
