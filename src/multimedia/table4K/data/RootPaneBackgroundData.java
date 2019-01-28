package table4K.data;

import javafx.scene.image.Image;

import static table4K.Main4K.RESOURCES_PATH;



public class RootPaneBackgroundData {

    public static Image getSectionSelectionBackground() {
        return createImage("table_with_lamp.jpg");
    }

    public static Image getBookBackground() {
        return createImage("table_4K_book.jpg");
    }



    static Image createImage(final String pathArg) {
        return new Image("file:///" + RESOURCES_PATH + pathArg);
    }
}