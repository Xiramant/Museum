package table4K.controller.video;

import javafx.scene.media.Media;
import table4K.controller.Table4KController;
import table4K.model.video.VideoTape;

import static table4K.model.video.VideoPlayer.showVideo;



public class VideoListController extends Table4KController {

    private final Media videoMedia;



    public VideoListController(final VideoTape videoArg){
        super(videoArg.getBox());
        videoMedia = videoArg.getMedia();
    }



    @Override
    protected void action() {
        showVideo(videoMedia);
    }

}