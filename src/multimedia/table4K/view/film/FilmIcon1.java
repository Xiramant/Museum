package table4K.view.film;

import javafx.scene.image.Image;
import table4K.data.IconData;
import table4K.view.IDisplayIcon;

import static table4K.view.MainView.DEBUGGING_RATIO;


public class FilmIcon1 implements IDisplayIcon {

    private static final Image FILM1_IMAGE = IconData.getFilmIcon1Image();
    private static final double FILM1_ICON_WIDTH = 584 / DEBUGGING_RATIO;
    private static final double FILM1_ICON_X = 3244 / DEBUGGING_RATIO;
    private static final double FILM1_ICON_Y = 146 / DEBUGGING_RATIO;
    private static final String FILM_SHADOW = "-fx-effect: dropshadow(gaussian, black, 15, 0.3, 8, 8);";


    public Image getIconImage() {
        return FILM1_IMAGE;
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

}
