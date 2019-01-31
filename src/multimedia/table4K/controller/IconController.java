package table4K.controller;

import javafx.scene.Node;
import table4K.controller.book.BookIconController;
import table4K.controller.video.VideoIconController;
import table4K.controller.mail.MailIconController;
import table4K.controller.map.MapIconController;
import table4K.controller.medal.MedalIconController;
import table4K.controller.portfolio.PortfolioIconController;
import table4K.controller.quiz.QuizIconController;



abstract public class IconController extends Table4KController{

    protected IconController(final Node iconArg) {
        super(iconArg);
    }

    abstract protected void action();



    public static IconController getBookIconController(final Node iconArg) {
        return new BookIconController(iconArg);
    }

    public static IconController getVideoIconController(final Node iconArg) {
        return new VideoIconController(iconArg);
    }

    public static IconController getMailIconController(final Node iconArg) {
        return new MailIconController(iconArg);
    }

    public static IconController getMapIconController(final Node iconArg) {
        return new MapIconController(iconArg);
    }

    public static IconController getMedalIconController(final Node iconArg) {
        return new MedalIconController(iconArg);
    }

    public static IconController getPortfolioIconController(final Node iconArg) {
        return new PortfolioIconController(iconArg);
    }

    public static IconController getQuizIconController(final Node iconArg) {
        return new QuizIconController(iconArg);
    }

}
