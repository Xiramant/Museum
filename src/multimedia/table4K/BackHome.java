package table4K;

import general.SectionKey;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaPlayer;
import table4K.ui.MainView;

import static general.TouchWait.eventDelayBegin;
import static table4K.Main4K.*;
import static table4K.controller.ControllerParameters.isEventPermission;
import static table4K.film.Film.setFilmScene;
import static table4K.map.Map.setMapScene;
import static table4K.portfolio.Portfolio.setPortfolioScene;

public class BackHome extends Region {

    private static final double BUTTON_HEIGHT_MAX = 100 / MainView.DEBUGGING_RATIO;

    private static final double BUTTON_INDENT = 20 / MainView.DEBUGGING_RATIO;

    private static final String SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 2, 2);";

    //переход К разделам
    public static ImageView returnHome() {

        ImageView ivHome = getHomeImageView();

        ivHome.setOnMouseClicked(event -> returnHomeAction(event));
        ivHome.setOnTouchReleased(event -> returnHomeAction(event));

        return ivHome;
    }

    private static void returnHomeAction(final InputEvent event) {
        if (isEventPermission(event)) {
            MainView.setMainScene();
            eventDelayBegin();
        }
    }

    //переход К разделам для раздела Фильмы
    public static ImageView returnHome(final MediaPlayer mediaPlayer) {

        ImageView ivHome = getHomeImageView();

        ivHome.setOnMouseClicked(event -> returnHomeActionFromFilm(event, mediaPlayer));
        ivHome.setOnTouchReleased(event -> returnHomeActionFromFilm(event, mediaPlayer));

        return ivHome;
    }

    private static void returnHomeActionFromFilm(final InputEvent event, final MediaPlayer mediaPlayer) {
        if (isEventPermission(event)) {
            mediaPlayer.stop();
            MainView.setMainScene();
            eventDelayBegin();
        }
    }

    //установка кнопки К разделам
    private static ImageView getHomeImageView() {

        ImageView ivHome = new ImageView(new Image("file:" + RESOURCES_PATH + "section.png"));
        ivHome.setFitHeight(BUTTON_HEIGHT_MAX);
        ivHome.setPreserveRatio(true);
        ivHome.setStyle(SHADOW);
        ivHome.setLayoutX(MainView.TABLE_WIDTH - ivHome.getLayoutBounds().getWidth() - BUTTON_INDENT);
        ivHome.setLayoutY(MainView.TABLE_HEIGHT - ivHome.getLayoutBounds().getHeight() - BUTTON_INDENT);

        return ivHome;
    }

    //возврат на предыдущую сцену для фильмов
    public static ImageView returnBack(final MediaPlayer mediaPlayer) {

        ImageView ivBack = getBackImageView();

        ivBack.setOnMouseClicked(event -> returnBackActionFromFilm(event, mediaPlayer));
        ivBack.setOnTouchReleased(event -> returnBackActionFromFilm(event, mediaPlayer));

        return ivBack;
    }

    private static void returnBackActionFromFilm(final InputEvent event, final MediaPlayer mediaPlayer) {
        if (isEventPermission(event)) {
            mediaPlayer.stop();
            setFilmScene();
            eventDelayBegin();
        }
    }

    //возврат на предыдущую сцену
    public static ImageView returnBack(final SectionKey key) {

        ImageView ivBack = getBackImageView();

        ivBack.setOnMouseClicked(event -> returnBackAction(event, key));
        ivBack.setOnTouchReleased(event -> returnBackAction(event, key));

        return ivBack;
    }

    private static void returnBackAction(final InputEvent event, final SectionKey key) {
        if (isEventPermission(event)) {
            setScene(key);
            eventDelayBegin();
        }
    }


    //установка кноки Назад
    private static ImageView getBackImageView() {

        ImageView ivHome = getHomeImageView();

        ImageView ivBack = new ImageView(new Image("file:" + RESOURCES_PATH + "back.png"));
        ivBack.setFitHeight(BUTTON_HEIGHT_MAX);
        ivBack.setPreserveRatio(true);
        ivBack.setStyle(SHADOW);
        ivBack.setLayoutX(ivHome.getLayoutX() - ivBack.getLayoutBounds().getWidth() - BUTTON_INDENT);
        ivBack.setLayoutY(MainView.TABLE_HEIGHT - ivBack.getLayoutBounds().getHeight() - BUTTON_INDENT);

        return ivBack;
    }

    //возврат к определенной сцене, в зависимости от переданного ключа
    private static void setScene(final SectionKey key) {
        switch (key) {
            case MAP:
                setMapScene();
                break;
            case PORTFOLIO:
                setPortfolioScene();
                break;
            default:
                MainView.setMainScene();
                break;
        }
    }

}
