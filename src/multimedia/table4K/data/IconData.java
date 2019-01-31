package table4K.data;

import javafx.scene.image.Image;

import static table4K.Main4K.RESOURCES_PATH;
import static table4K.data.FileProcessing.createImage;



public class IconData {

    public static Image getBookIconImage() {
        return createImage(RESOURCES_PATH + "icon/book_icon.png");
    }

    public static Image getVideoIcon1Image() {
        return createImage(RESOURCES_PATH + "icon/video_icon_1.png");
    }
    public static Image getVideoIcon2Image() {
        return createImage(RESOURCES_PATH + "icon/video_icon_2.png");
    }

    public static Image getMailIconImage() {
        return createImage(RESOURCES_PATH + "icon/mail_icon.png");
    }

    public static Image getMapIconImage() {
        return createImage(RESOURCES_PATH + "icon/map_icon.png");
    }

    public static Image getMedalIconImage() {
        return createImage(RESOURCES_PATH + "icon/medal_icon.png");
    }

    public static Image getPortfolioIconImage() {
        return createImage(RESOURCES_PATH + "icon/portfolio_icon.png");
    }

    public static Image getQuizIconImage() {
        return createImage(RESOURCES_PATH + "icon/quiz_icon.png");
    }

}
