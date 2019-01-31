package table4K.model.video;

import javafx.scene.Group;
import javafx.scene.Node;
import table4K.controller.video.VideoListController;

import java.util.ArrayList;

import static table4K.BackHome.returnHome;
import static table4K.data.video.VideoData.getVideoContentList;
import static table4K.data.video.VideoData.getVideoListBackground;
import static table4K.view.MainView.setMainScene;
import static table4K.view.video.VideoListView.setVideoListLocation;


public class VideoList {

    public static void setVideoScene() {

        ArrayList<VideoContent> videoContents = getVideoContentList();

        ArrayList<VideoTape> videos = new ArrayList<>();
        for (VideoContent content: videoContents) {
            videos.add(new VideoTape(content));
        }

        for (VideoTape video: videos) {
            new VideoListController(video);
        }

        setVideoListLocation(videos);

        Group videoBoxes = new Group();
        for (VideoTape video: videos) {
            videoBoxes.getChildren().add(video.getBox());
        }

        ArrayList<Node> graphicElements = new ArrayList<>();
        graphicElements.add(videoBoxes);
        graphicElements.add(returnHome());

        setMainScene(getVideoListBackground(), graphicElements);

    }

}