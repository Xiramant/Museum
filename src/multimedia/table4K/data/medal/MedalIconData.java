package table4K.data.medal;

import javafx.scene.image.Image;
import table4K.data.IIconData;

import static table4K.Main4K.RESOURCES_PATH;

public class MedalIconData implements IIconData {

    private Image medalIcon = new Image("file:///" + RESOURCES_PATH + "icon/medal_icon.png");

    public Image getIconImage() {
        return medalIcon;
    }

}