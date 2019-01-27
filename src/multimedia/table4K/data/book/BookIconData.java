package table4K.data.book;

import javafx.scene.image.Image;
import table4K.data.IIconData;

import static table4K.Main4K.RESOURCES_PATH;


public class BookIconData implements IIconData {

    private Image bookIcon = new Image("file:///" + RESOURCES_PATH + "icon/book_icon.png");

    public Image getIconImage() {
        return bookIcon;
    }

}