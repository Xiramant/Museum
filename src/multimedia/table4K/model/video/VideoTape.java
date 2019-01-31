package table4K.model.video;

import table4K.view.video.VideoBox;

public class VideoTape extends VideoContent{

    private final VideoBox box;



    public VideoBox getBox() {
        return box;
    }



    VideoTape(final VideoContent contentArg) {
        super(contentArg.getMedia(), contentArg.getName());
        box = new VideoBox(this.getName());
    }
}