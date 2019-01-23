package table4K.ui.book;

import general.SectionKey;
import table4K.ui.IDisplayIcon;

import static table4K.ui.MainView.DEBUGGING_RATIO;
import static table4K.Main4K.RESOURCES_PATH;

public class BookIcon implements IDisplayIcon {

    private static final String BOOK_URL = "file:///" + RESOURCES_PATH + "icon/book_icon.png";
    private static final double BOOK_ICON_WIDTH = 672 / DEBUGGING_RATIO;
    private static final double BOOK_ICON_X = 3169 / DEBUGGING_RATIO;
    private static final double BOOK_ICON_Y = 1129 / DEBUGGING_RATIO;
    private static final String BOOK_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 8, 8);";
    private static final SectionKey BOOK_KEY = SectionKey.BOOK;

    public String getIconUrl() {
        return BOOK_URL;
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

    public SectionKey getKey() {
        return BOOK_KEY;
    }

}
