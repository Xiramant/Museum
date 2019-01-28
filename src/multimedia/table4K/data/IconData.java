package table4K.data;

import javafx.scene.image.Image;

import static table4K.Main4K.RESOURCES_PATH;


public class IconData {

    public static Image getBookIconImage() {
        return new Image("file:///" + RESOURCES_PATH + "icon/book_icon.png");
    }

    public static Image getFilmIcon1Image() {
        return new Image("file:///" + RESOURCES_PATH + "icon/film1_icon.png");
    }
    public static Image getFilmIcon2Image() {
        return new Image("file:///" + RESOURCES_PATH + "icon/film2_icon.png");
    }

    public static Image getMailIconImage() {
        return new Image("file:///" + RESOURCES_PATH + "icon/mail_icon.png");
    }

    public static Image getMapIconImage() {
        return new Image("file:///" + RESOURCES_PATH + "icon/map_icon.png");
    }

    public static Image getMedalIconImage() {
        return new Image("file:///" + RESOURCES_PATH + "icon/medal_icon.png");
    }

    public static Image getPortfolioIconImage() {
        return new Image("file:///" + RESOURCES_PATH + "icon/portfolio_icon.png");
    }

    public static Image getQuizIconImage() {
        return new Image("file:///" + RESOURCES_PATH + "icon/quiz_icon.png");
    }
}
