package table4K.model.video;

import table4K.controller.video.VideoListController;

import java.util.ArrayList;

import static table4K.data.video.VideoData.getVideoContentList;
import static table4K.view.video.VideoListView.setVideoListView;



public class VideoList {

    public static void setVideoScene() {

        ArrayList<VideoContent> videoContentList = getVideoContentList();

        ArrayList<VideoTape> videoList = getVideoTapeList(videoContentList);

        setVideoListControllers(videoList);

        setVideoListView(videoList);
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

}