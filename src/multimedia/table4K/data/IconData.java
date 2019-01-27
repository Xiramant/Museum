package table4K.data;

import javafx.scene.image.Image;
import table4K.data.book.BookIconData;
import table4K.data.film.FilmIcon1Data;
import table4K.data.film.FilmIcon2Data;
import table4K.data.mail.MailIconData;
import table4K.data.map.MapIconData;
import table4K.data.medal.MedalIconData;
import table4K.data.portfolio.PortfolioIconData;
import table4K.data.quiz.QuizIconData;

public class IconData {

    public static Image getBookIconImage() {
        return new BookIconData().getIconImage();
    }

    public static Image getFilmIcon1Image() {
        return new FilmIcon1Data().getIconImage();
    }
    public static Image getFilmIcon2Image() {
        return new FilmIcon2Data().getIconImage();
    }

    public static Image getMailIconImage() {
        return new MailIconData().getIconImage();
    }

    public static Image getMapIconImage() {
        return new MapIconData().getIconImage();
    }

    public static Image getMedalIconImage() {
        return new MedalIconData().getIconImage();
    }

    public static Image getPortfolioIconImage() {
        return new PortfolioIconData().getIconImage();
    }

    public static Image getQuizIconImage() {
        return new QuizIconData().getIconImage();
    }
}
