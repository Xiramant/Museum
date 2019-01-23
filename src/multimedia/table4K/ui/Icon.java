package table4K.ui;

import general.SectionKey;
import javafx.scene.Group;
import javafx.scene.Node;
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

public class Icon implements IIcon{

    private Node image;

    public Node getIconImage() {
        return image;
    }

    private SectionKey key;

    public SectionKey getKey() {
        return key;
    }

    private Icon(final IDisplayIcon iconValue) {
        this.image = getImageView(iconValue);
        this.key =  iconValue.getKey();
    }

    private Icon(final IDisplayIcon iconValue1, final IDisplayIcon iconValue2) {
        this.image = new Group(getImageView(iconValue1), getImageView(iconValue2));
        this.key =  iconValue1.getKey();
    }

    private ImageView getImageView(final IDisplayIcon iconValue){
        ImageView iv = new ImageView(new Image(iconValue.getIconUrl()));
        iv.setPreserveRatio(true);
        iv.setFitWidth(iconValue.getIconWidth());
        iv.setLayoutX(iconValue.getIconX());
        iv.setLayoutY(iconValue.getIconY());
        iv.setStyle(iconValue.getIconStyle());

        return iv;
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

    public static Icon getFilm() {
        return new Icon(new FilmIcon1(), new FilmIcon2());
    }

}
