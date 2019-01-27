package table4K.data.mail;

import javafx.scene.image.Image;
import table4K.data.IIconData;

import static table4K.Main4K.RESOURCES_PATH;

public class MailIconData implements IIconData {

    private Image mailIcon = new Image("file:///" + RESOURCES_PATH + "icon/mail_icon.png");

    public Image getIconImage() {
        return mailIcon;
    }
}
