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

        ArrayList<VideoContent> videoContentList = getVideoContentList();

        ArrayList<VideoTape> videoList = getVideoTapeList(videoContentList);

        setVideoListControllers(videoList);

        setVideoListLocation(videoList);

        setVideoListScene(videoList);

    }

    private static ArrayList<VideoTape> getVideoTapeList(final ArrayList<VideoContent> videoContentListArg) {
        ArrayList<VideoTape> outList = new ArrayList<>();

        for (VideoContent content: videoContentListArg) {
            outList.add(new VideoTape(content));
        }

        return outList;
    }

    private static void setVideoListControllers(final ArrayList<VideoTape> videoTapeListArg) {
        for (VideoTape video: videoTapeListArg) {
            new VideoListController(video);
        }
    }

    private static void setVideoListScene(final ArrayList<VideoTape> videoTapeListArg) {
        ArrayList<Node> sceneGraphicElementList = new ArrayList<>();
        sceneGraphicElementList.add(getVideoListBoxes(videoTapeListArg));
        sceneGraphicElementList.add(returnHome());

        setMainScene(getVideoListBackground(), sceneGraphicElementList);
    }

    private static Node getVideoListBoxes(final ArrayList<VideoTape> videoTapeListArg) {
        Group outList = new Group();
        
        for (VideoTape video: videoTapeListArg) {
            outList.getChildren().add(video.getBox());
        }

        return outList;
    }

}