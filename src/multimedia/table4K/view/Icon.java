package table4K.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import table4K.view.book.BookIcon;
import table4K.view.video.VideoIcon1;
import table4K.view.video.VideoIcon2;
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

    public static Node getMailIcon() {
        return new Icon(new MailIcon());
    }

    public static Node getPortfolioIcon() {
        return new Icon(new PortfolioIcon());
    }

    public static Node getMedalIcon() {
        return new Icon(new MedalIcon());
    }

    public static Node getBookIcon() {
        return new Icon(new BookIcon());
    }

    public static Node getQuizIcon() {
        return new Icon(new QuizIcon());
    }

    public static Node getVideoIcon() {
        Group videoIcon = new Group();
        videoIcon.getChildren().add(new Icon(new VideoIcon1()));
        videoIcon.getChildren().add(new Icon(new VideoIcon2()));
        return videoIcon;
    }

}
