package table4K.view.film;

import javafx.scene.image.Image;
import table4K.data.IconData;
import table4K.view.IDisplayIcon;

import static table4K.view.MainView.DEBUGGING_RATIO;


public class FilmIcon2 implements IDisplayIcon {

    private static final Image FILM2_IMAGE = IconData.getFilmIcon2Image();
    private static final double FILM2_ICON_WIDTH = 536 / DEBUGGING_RATIO;
    private static final double FILM2_ICON_X = 3422 / DEBUGGING_RATIO;
    private static final double FILM2_ICON_Y = 369 / DEBUGGING_RATIO;
    private static final String FILM_SHADOW = "-fx-effect: dropshadow(gaussian, black, 15, 0.3, 8, 8);";


    public Image getIconImage() {
        return FILM2_IMAGE;
    }

    public double getIconWidth() {
        return FILM2_ICON_WIDTH;
    }

    public double getIconX() {
        return FILM2_ICON_X;
    }

    public double getIconY() {
        return FILM2_ICON_Y;
    }

    public String getIconStyle() {
        return FILM_SHADOW;
    }

}
