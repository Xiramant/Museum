package table4K.data.film;

import javafx.scene.image.Image;
import table4K.data.IIconData;

import static table4K.Main4K.RESOURCES_PATH;

public class FilmIcon2Data implements IIconData {

    private Image film2Icon = new Image("file:///" + RESOURCES_PATH + "icon/film2_icon.png");

    public Image getIconImage() {
        return film2Icon;
    }

}
