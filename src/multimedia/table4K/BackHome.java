package table4K;

import general.SectionKey;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaPlayer;


import static general.TouchWait.isTimeWaitEnd;
import static general.TouchWait.setTimeWait;
import static table4K.Main4K.*;
import static table4K.film.Film.setFilmScene;
import static table4K.map.Map.setMapScene;
import static table4K.portfolio.Portfolio.setPortfolioScene;

public class BackHome extends Region {

    private static final double BUTTON_HEIGHT_MAX = 100 / DEBUGGING_RATIO;

    private static final double BUTTON_INDENT = 20 / DEBUGGING_RATIO;

    private static final String SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 2, 2);";

    //переход К разделам
    public static ImageView returnHome() {

        ImageView ivHome = getHomeImageView();

        ivHome.setOnMouseClicked(event -> setMainScene());
        ivHome.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                setMainScene();
                setTimeWait();
            }
        });

        return ivHome;
    }

    //переход К разделам для раздела Фильмы
    public static ImageView returnHome(final MediaPlayer mediaPlayer) {

        ImageView ivHome = getHomeImageView();


        ivHome.setOnMouseClicked(event -> {
            mediaPlayer.stop();
            setMainScene();
        });
        ivHome.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                mediaPlayer.stop();
                setMainScene();
                setTimeWait();
            }
        });

        return ivHome;
    }

    //установка кнопки К разделам
    private static ImageView getHomeImageView() {

        ImageView ivHome = new ImageView(new Image("file:" + RESOURCES_PATH + "section.png"));
        ivHome.setFitHeight(BUTTON_HEIGHT_MAX);
        ivHome.setPreserveRatio(true);
        ivHome.setStyle(SHADOW);
        ivHome.setLayoutX(TABLE_WIDTH - ivHome.getLayoutBounds().getWidth() - BUTTON_INDENT);
        ivHome.setLayoutY(TABLE_HEIGHT - ivHome.getLayoutBounds().getHeight() - BUTTON_INDENT);

        return ivHome;
    }

    //возврат на предыдущую сцену для фильмов
    public static ImageView returnBack(final MediaPlayer mediaPlayer) {

        ImageView ivBack = getBackImageView();

        ivBack.setOnMouseClicked(event -> {
            mediaPlayer.stop();
            setFilmScene();
        });
        ivBack.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                mediaPlayer.stop();
                setFilmScene();
                setTimeWait();
            }
        });

        return ivBack;
    }

    //возврат на предыдущую сцену
    public static ImageView returnBack(final SectionKey key) {

        ImageView ivBack = getBackImageView();

        ivBack.setOnMouseClicked(event -> {
            setScene(key);
        });
        ivBack.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                setScene(key);
                setTimeWait();
            }
        });

        return ivBack;
    }

    //установка кноки Назад
    private static ImageView getBackImageView() {

        ImageView ivHome = getHomeImageView();

        ImageView ivBack = new ImageView(new Image("file:" + RESOURCES_PATH + "back.png"));
        ivBack.setFitHeight(BUTTON_HEIGHT_MAX);
        ivBack.setPreserveRatio(true);
        ivBack.setStyle(SHADOW);
        ivBack.setLayoutX(ivHome.getLayoutX() - ivBack.getLayoutBounds().getWidth() - BUTTON_INDENT);
        ivBack.setLayoutY(TABLE_HEIGHT - ivBack.getLayoutBounds().getHeight() - BUTTON_INDENT);

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
                setMainScene();
                break;
        }

    }

}
