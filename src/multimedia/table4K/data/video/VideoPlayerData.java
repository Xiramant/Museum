package table4K.data.video;

import javafx.scene.image.Image;

import static table4K.Main4K.RESOURCES_PATH;
import static table4K.data.FileProcessing.createImage;

public class VideoPlayerData {

    private static final String PLAYER_BACKGROUND_PATH = RESOURCES_PATH + "video/video_player_background.jpg";

    private static final String VIGNETTE_PATH = RESOURCES_PATH + "video/vignette.png";

    private static final String VIDEO_PLAYER_SCENE_BACKGROUND_PATH = RESOURCES_PATH + "video/video_background.jpg";



    public static Image getPlayerBackground() {
        return createImage(PLAYER_BACKGROUND_PATH);
    }

    public static Image getVignetteImage() {
        return createImage(VIGNETTE_PATH);
    }

    public static Image getVideoPlayerSceneBackground() {
        return createImage(VIDEO_PLAYER_SCENE_BACKGROUND_PATH);
    }
}
