package table4K.model.video;

import javafx.scene.media.Media;



public class VideoContent {

    private final Media media;

    private final String name;



    public Media getMedia() {
        return media;
    }

    public String getName() {
        return name;
    }



    public VideoContent(final Media mediaArg, final String nameArg) {
        media = mediaArg;
        name = nameArg;
    }

}