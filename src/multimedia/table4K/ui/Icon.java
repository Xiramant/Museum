package table4K.ui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import table4K.ui.book.BookIcon;
import table4K.ui.film.FilmIcon1;
import table4K.ui.film.FilmIcon2;
import table4K.ui.mail.MailIcon;
import table4K.ui.map.MapIcon;
import table4K.ui.medal.MedalIcon;
import table4K.ui.portfolio.PortfolioIcon;
import table4K.ui.quiz.QuizIcon;

public class Icon extends ImageView {

    private Icon(IDisplayIcon IDisplayIcon) {
        super(new Image(IDisplayIcon.getIconUrl()));
        this.setPreserveRatio(true);
        this.setFitWidth(IDisplayIcon.getIconWidth());
        this.setLayoutX(IDisplayIcon.getIconX());
        this.setLayoutY(IDisplayIcon.getIconY());
        this.setStyle(IDisplayIcon.getIconStyle());
    }

    public static Icon getMap() {
        return new Icon(new MapIcon());
    }

    public static Icon getMail() {
        return new Icon(new MailIcon());
    }

    public static Icon getPortfolio() {
        return new Icon(new PortfolioIcon());
    }

    public static Icon getMedal() {
        return new Icon(new MedalIcon());
    }

    public static Icon getBook() {
        return new Icon(new BookIcon());
    }

    public static Icon getQuiz() {
        return new Icon(new QuizIcon());
    }

    public static Group getFilm() {
        return new Group(new Icon(new FilmIcon1()), new Icon(new FilmIcon2()));
    }

}
