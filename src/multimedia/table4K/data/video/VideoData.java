package table4K.data.video;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import table4K.data.FileType;
import table4K.model.video.VideoContent;

import java.io.File;
import java.util.ArrayList;

import static table4K.Main4K.RESOURCES_PATH;
import static table4K.data.FileProcessing.*;



public class VideoData {

    //путь к директории с фильмами
    private static final String VIDEO_PATH = RESOURCES_PATH + "video/videos/";

    //часть пути к изображению коробки для видео
    private static final String VIDEO_BOX_PATH = RESOURCES_PATH + "video/video_box.png";



    public static ArrayList<VideoContent> getVideoContentList() {
        ArrayList<VideoContent> out = new ArrayList<>();

        ArrayList<File> videoFiles = getFilesCertainType(new File(VIDEO_PATH), FileType.VIDEO);

        for (File videoFile: videoFiles) {
            Media videoMedia = new Media(videoFile.toURI().toString());
            String videoName = getName(videoFile);
            out.add(new VideoContent(videoMedia, videoName));
        }

        return out;
    }

    public static Image getVideoBoxImage() {
        return createImage(VIDEO_BOX_PATH);
    }

    public static Image getVideoListBackground() {
        return createImage(RESOURCES_PATH + "table_4K_film_initial.jpg");
    }

}