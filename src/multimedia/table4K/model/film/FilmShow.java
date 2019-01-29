package table4K.model.film;

import general.ImagePane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import table4K.view.MainView;

import java.io.File;

import static table4K.BackHome.*;
import static table4K.Main4K.*;

public class FilmShow {

    //ширина области просмотра фильма
    private static final double FILM_VIEW_WIDTH = 2640 / MainView.DEBUGGING_RATIO;

    //высота области просмотра фильма
    private static double FILM_VIEW_HEIGHT;

    //отступ экрана слева
    private static final double PLAYER_X = 1260 / MainView.DEBUGGING_RATIO;


    public static void FilmShowSet(final String filmName, final double filmRatio) {

        MainView.changeRootPaneBackground(RESOURCES_PATH + "film/film_background.jpg");
        MainView.rootPane.getChildren().clear();

        Media media = new Media(filmName);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(FILM_VIEW_WIDTH);

        FILM_VIEW_HEIGHT = FILM_VIEW_WIDTH / filmRatio;

        ImagePane player = new ImagePane(new File(RESOURCES_PATH + "film/film_player_background.jpg"),
                                        FILM_VIEW_WIDTH,
                                        FILM_VIEW_HEIGHT);

        ImageView vignette = new ImageView(new Image("file:" + RESOURCES_PATH + "film/vignette.png"));
        vignette.setPreserveRatio(false);
        vignette.setFitWidth(FILM_VIEW_WIDTH);
        vignette.setFitHeight(FILM_VIEW_HEIGHT);

        player.setLayoutX(PLAYER_X);
        player.setLayoutY(MainView.TABLE_HEIGHT / 2 - FILM_VIEW_HEIGHT / 2);
        player.getChildren().addAll(mediaView, vignette);

        MainView.rootPane.getChildren().addAll(player, returnHome(mediaPlayer), returnBack(mediaPlayer));
    }


}
