package table4K.view.book;

import javafx.scene.image.Image;
import table4K.data.IconData;
import table4K.view.IDisplayIcon;

import static table4K.view.MainView.DEBUGGING_RATIO;


public class BookIcon implements IDisplayIcon {

    private static final Image BOOK_IMAGE = IconData.getBookIconImage();
    private static final double BOOK_ICON_WIDTH = 672 / DEBUGGING_RATIO;
    private static final double BOOK_ICON_X = 3169 / DEBUGGING_RATIO;
    private static final double BOOK_ICON_Y = 1129 / DEBUGGING_RATIO;
    private static final String BOOK_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 8, 8);";


    public Image getIconImage() {
        return BOOK_IMAGE;
    }

    public double getIconWidth() {
        return BOOK_ICON_WIDTH;
    }

    public double getIconX() {
        return BOOK_ICON_X;
    }

    public double getIconY() {
        return BOOK_ICON_Y;
    }

    public String getIconStyle() {
        return BOOK_SHADOW;
    }

}
