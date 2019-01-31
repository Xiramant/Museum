package table4K.data;

import javafx.scene.image.Image;

import static table4K.Main4K.RESOURCES_PATH;
import static table4K.data.FileProcessing.createImage;


public class RootPaneBackgroundData {

    public static Image getSectionSelectionBackground() {
        return createImage(RESOURCES_PATH + "table_with_lamp.jpg");
    }

    public static Image getBookBackground() {
        return createImage(RESOURCES_PATH + "table_4K_book.jpg");
    }

}