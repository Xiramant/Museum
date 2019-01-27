package table4K.data.film;

import javafx.scene.image.Image;
import table4K.data.IIconData;

import static table4K.Main4K.RESOURCES_PATH;


public class FilmIcon1Data implements IIconData {

    private Image film1Icon = new Image("file:///" + RESOURCES_PATH + "icon/film1_icon.png");

    public Image getIconImage() {
        return film1Icon;
    }

}