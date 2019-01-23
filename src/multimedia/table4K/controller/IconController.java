package table4K.controller;

import general.SectionKey;
import javafx.scene.input.InputEvent;
import table4K.ui.IIcon;

import static general.SectionKey.ORDEN;
import static general.TouchWait.eventDelayBegin;
import static table4K.book.Book.setBookScene;
import static table4K.controller.ControllerParameters.isEventPermission;
import static table4K.film.Film.setFilmScene;
import static table4K.mail.Mail.setMailScene;
import static table4K.map.Map.setMapScene;
import static table4K.medal.Medal.setMedalScene;
import static table4K.portfolio.Portfolio.setPortfolioScene;
import static table4K.quiz.Quiz.setQuizScene;

public class IconController {

    private IIcon icon;

    public IconController(final IIcon iconArg) {
        this.icon = iconArg;
        action();
    }

    public void action() {
        icon.getIconImage().setOnMouseClicked(event -> sectionSelect(event, icon.getKey()));
        icon.getIconImage().setOnTouchReleased(event -> sectionSelect(event, icon.getKey()));
    }

    //выбор раздела
    public static void sectionSelect(final InputEvent event, final SectionKey sectionKey) {

        if (isEventPermission(event)) {

            switch (sectionKey) {
                case MAP:
                    setMapScene();
                    break;
                case MAIL:
                    setMailScene();
                    break;
                case PORTFOLIO:
                    setPortfolioScene();
                    break;
                case MEDAL:
                    setMedalScene(ORDEN);
                    break;
                case BOOK:
                    setBookScene();
                    break;
                case FILM:
                    setFilmScene();
                    break;
                case QUIZ:
                    setQuizScene();
                    break;
            }

            eventDelayBegin();
        }
    }

}
