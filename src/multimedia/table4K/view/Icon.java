package table4K.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import table4K.view.book.BookIcon;
import table4K.view.film.FilmIcon1;
import table4K.view.film.FilmIcon2;
import table4K.view.mail.MailIcon;
import table4K.view.map.MapIcon;
import table4K.view.medal.MedalIcon;
import table4K.view.portfolio.PortfolioIcon;
import table4K.view.quiz.QuizIcon;


public class Icon extends ImageView{

    protected Icon(final IDisplayIcon iconValue) {
        super(iconValue.getIconImage());
        this.setPreserveRatio(true);
        this.setFitWidth(iconValue.getIconWidth());
        this.setLayoutX(iconValue.getIconX());
        this.setLayoutY(iconValue.getIconY());
        this.setStyle(iconValue.getIconStyle());
    }


    public static Node getMapIcon() {
        return new Icon(new MapIcon());
    }

    public static Node getMail() {
        return new Icon(new MailIcon());
    }

    public static Node getPortfolio() {
        return new Icon(new PortfolioIcon());
    }

    public static Node getMedal() {
        return new Icon(new MedalIcon());
    }

    public static Node getBook() {
        return new Icon(new BookIcon());
    }

    public static Node getQuiz() {
        return new Icon(new QuizIcon());
    }

    public static Node getFilm() {
        Group film = new Group();
        film.getChildren().add(new Icon(new FilmIcon1()));
        film.getChildren().add(new Icon(new FilmIcon2()));
        return film;
    }

}
