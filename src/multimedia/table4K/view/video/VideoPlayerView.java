package table4K.view.video;

import general.ImagePane;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;

import java.util.ArrayList;

import static table4K.BackHome.returnBack;
import static table4K.BackHome.returnHome;
import static table4K.data.video.VideoPlayerData.*;
import static table4K.view.MainView.*;



public class VideoPlayerView {

    //Параметры области просмотра фильма.
    private static final double VIDEO_VIEW_WIDTH = 2640 / DEBUGGING_RATIO;

    //Отступ экрана слева.
    private static final double PLAYER_X = 1260 / DEBUGGING_RATIO;

    //Соотношение сторон видео по умолчанию.
    //Используется, если не получилось определить необходимое соотношение
    // с помощью самого видео.
    private static final double VIDEO_RATIO_DEFAULT = 16d / 9d;

    //mediaPlayer сделан в виде поля,
    // т.к. в форме локальной переменной,
    // если передавать его в качестве параметра из mediaPlayer.setOnReady() в метод setVideoView(),
    // то главный поток заканчивается быстрее,
    // чем успевает начаться побочный, формируемый mediaPlayer.setOnReady()
    // и у локальной переменной заканчивается срок жизни.
    //В результате в метод setVideoView() ничего не передается и
    // видео не проигрывается
    private static MediaPlayer mediaPlayer;



    public static void showVideo(final Media mediaArg) {
        mediaPlayer = new MediaPlayer(mediaArg);
        mediaPlayer.setOnReady(VideoPlayerView::setVideoView);
    }

    private static void setVideoView() {
        setVideoPlayerScene();
        mediaPlayer.setAutoPlay(true);
    }

    private static void setVideoPlayerScene() {
        ArrayList<Node> sceneGraphicElementList = new ArrayList<>();
        sceneGraphicElementList.add(setPlayer());
        sceneGraphicElementList.add(returnBack(mediaPlayer));
        sceneGraphicElementList.add(returnHome(mediaPlayer));

        setMainScene(getVideoPlayerSceneBackground(), sceneGraphicElementList);
    }

    private static ImagePane setPlayer() {
        ImagePane out = new ImagePane(getPlayerBackground(),
                VIDEO_VIEW_WIDTH,
                getVideoViewHeight());
        out.setLayoutX(PLAYER_X);
        out.setLayoutY(TABLE_HEIGHT / 2 - getVideoViewHeight() / 2);
        out.getChildren().addAll(getMediaView(), getVignette());

        out.setOnMouseClicked(event -> {
            Status status = mediaPlayer.getStatus();
            if (status == Status.PAUSED
                || status == Status.STOPPED) {
                mediaPlayer.play();
            }
            if (status == Status.PLAYING) {
                mediaPlayer.pause();
            }
        });

        return out;
    }

    private static double getVideoViewHeight() {
        return VIDEO_VIEW_WIDTH / getVideoRatio();
    }

    private static double getVideoRatio() {
        double videoWidth = mediaPlayer.getMedia().getWidth();
        double videoHeight = mediaPlayer.getMedia().getHeight();

        return (videoHeight != 0) ?
                videoWidth / videoHeight :
                VIDEO_RATIO_DEFAULT;
    }

    private static MediaView getMediaView() {
        MediaView out = new MediaView(mediaPlayer);
        out.setFitWidth(VIDEO_VIEW_WIDTH);

        return out;
    }

    private static ImageView getVignette() {
        ImageView out = new ImageView(getVignetteImage());
        out.setPreserveRatio(false);
        out.setFitWidth(VIDEO_VIEW_WIDTH);
        out.setFitHeight(getVideoViewHeight());

        return out;
    }

}
