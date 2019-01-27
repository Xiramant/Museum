package table4K.data.map;

import javafx.scene.image.Image;
import table4K.data.IIconData;


import static table4K.Main4K.RESOURCES_PATH;

public class MapIconData implements IIconData {

    private Image mapIcon = new Image("file:///" + RESOURCES_PATH + "icon/map_icon.png");

    public Image getIconImage() {
        return mapIcon;
    }

}
