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

    //путь к изображению коробки для видео
    private static final String VIDEO_BOX_PATH = RESOURCES_PATH + "video/video_box.png";

    //путь к изображению фона для сцены Video List (Перечень видео)
    private static final String VIDEO_LIST_BACKGROUND = RESOURCES_PATH + "table_4K_film_initial.jpg";



    public static ArrayList<VideoContent> getVideoContentList() {
        ArrayList<VideoContent> outList = new ArrayList<>();
        ArrayList<File> videoFileList = getVideoFileList();

        for (File videoFile: videoFileList) {
            outList.add(new VideoContent(getMedia(videoFile), getName(videoFile)));
        }

        return outList;
    }

    private static ArrayList<File> getVideoFileList() {
        return getFilesCertainType(new File(VIDEO_PATH), FileType.VIDEO);
    }

    private static Media getMedia(final File videoFileArg) {
        return new Media(videoFileArg.toURI().toString());
    }



    public static Image getVideoBoxImage() {
        return createImage(VIDEO_BOX_PATH);
    }

    public static Image getVideoListBackground() {
        return createImage(VIDEO_LIST_BACKGROUND);
    }

}