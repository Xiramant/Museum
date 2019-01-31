package table4K.model.video;

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

public class VideoPlayer {

    //параметры области просмотра фильма
    private static final double FILM_VIEW_WIDTH = 2640 / MainView.DEBUGGING_RATIO;
    private static double FILM_VIEW_HEIGHT;

    //отступ экрана слева
    private static final double PLAYER_X = 1260 / MainView.DEBUGGING_RATIO;

    //mediaPlayer сделан в виде поля,
    // т.к. в форме локальной переменной,
    // если передавать его в качестве параметра из mediaPlayer.setOnReady() в метод setVideoView(),
    // то главный поток заканчивается быстрее,
    // чем успевает начаться побочный, формируемый mediaPlayer.setOnReady()
    // и у локальной переменной заканчивается срок жизни.
    // В результате в метод setVideoView() ничего не передается и
    // видео не проигрывается
    private static MediaPlayer mediaPlayer;



    public static void showVideo(final Media mediaArg) {
        mediaPlayer = new MediaPlayer(mediaArg);
        mediaPlayer.setOnReady(VideoPlayer::setVideoView);
    }

    private static void setVideoView() {
        double filmRatio = 16d / 9d;

        if (mediaPlayer.getMedia().getHeight() != 0) {
            filmRatio = (double) mediaPlayer.getMedia().getWidth()
                        / (double) mediaPlayer.getMedia().getHeight();
        }

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(FILM_VIEW_WIDTH);

        FILM_VIEW_HEIGHT = FILM_VIEW_WIDTH / filmRatio;

        ImagePane player = new ImagePane(new File(RESOURCES_PATH + "video/video_player_background.jpg"),
                FILM_VIEW_WIDTH,
                FILM_VIEW_HEIGHT);

        ImageView vignette = new ImageView(new Image("file:" + RESOURCES_PATH + "video/vignette.png"));
        vignette.setPreserveRatio(false);
        vignette.setFitWidth(FILM_VIEW_WIDTH);
        vignette.setFitHeight(FILM_VIEW_HEIGHT);

        player.setLayoutX(PLAYER_X);
        player.setLayoutY(MainView.TABLE_HEIGHT / 2 - FILM_VIEW_HEIGHT / 2);
        player.getChildren().addAll(mediaView, vignette);

        MainView.setMainSceneBackground(RESOURCES_PATH + "video/video_background.jpg");
        MainView.rootPane.getChildren().clear();
        MainView.rootPane.getChildren().addAll(player, returnHome(mediaPlayer), returnBack(mediaPlayer));

        mediaPlayer.setAutoPlay(true);
    }

}
