package table4K.controller;

import javafx.scene.Node;
import javafx.scene.input.InputEvent;
import table4K.controller.book.BookIconController;
import table4K.controller.film.FilmIconController;
import table4K.controller.mail.MailIconController;
import table4K.controller.map.MapIconController;
import table4K.controller.medal.MedalIconController;
import table4K.controller.portfolio.PortfolioIconController;
import table4K.controller.quiz.QuizIconController;

import static general.TouchWait.eventDelayBegin;
import static table4K.controller.ControllerParameters.isEventPermission;



abstract public class IconController {

    //метод перехода к разделу,
    // который должен быть переопределен
    // в подклассах, соответствующих разделам
    abstract protected void selectSection();

    protected IconController(final Node iconArg) {
        iconArg.setOnMouseClicked(this::selectSectionAfterPermission);
        iconArg.setOnTouchReleased(this::selectSectionAfterPermission);
    }

    private void selectSectionAfterPermission(final InputEvent event) {
        if (isEventPermission(event)) {
            selectSection();
            eventDelayBegin();
        }
    }



    public static IconController getBookIconController(final Node iconArg) {
        return new BookIconController(iconArg);
    }

    public static IconController getFilmIconController(final Node iconArg) {
        return new FilmIconController(iconArg);
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
