package table4K.view.video;


import general.GroupingOrder;
import javafx.scene.Group;
import javafx.scene.Node;
import table4K.model.AreaLocation;
import table4K.model.Dimension;
import table4K.model.video.VideoTape;


import java.util.ArrayList;

import static table4K.BackHome.returnHome;
import static table4K.data.video.VideoData.getVideoListBackground;
import static table4K.view.Location.setElementsLocation;
import static table4K.view.MainView.*;


public class VideoListView {

    //отступы от края стола до области размещения элементов
    private static final double LEFT_PADDING = 1000 / DEBUGGING_RATIO;
    private static final double TOP_PADDING = 500 / DEBUGGING_RATIO;
    private static final double RIGHT_PADDING = 250 / DEBUGGING_RATIO;
    private static final double BOTTOM_PADDING = 250 / DEBUGGING_RATIO;

    //минимальные интервалы между элементами
    private static final double HORIZONTAL_SPACING_MIN = 100 / DEBUGGING_RATIO;
    private static final double VERTICAL_SPACING_MIN = 50 / DEBUGGING_RATIO;

    //область, в которой располагаются коробки с фильмами
    private static final AreaLocation VIDEO_LIST_AREA_RESTRICTION = new AreaLocation(LEFT_PADDING,
                                                                                    TOP_PADDING,
                                                                        TABLE_WIDTH - LEFT_PADDING - RIGHT_PADDING,
                                                                        TABLE_HEIGHT - TOP_PADDING - BOTTOM_PADDING,
                                                                                    HORIZONTAL_SPACING_MIN,
                                                                                    VERTICAL_SPACING_MIN);



    public static void setVideoListView(final ArrayList<VideoTape> videosArg) {
        setVideoBoxLocation(videosArg);
        setVideoListScene(videosArg);
    }

    private static void setVideoBoxLocation(final ArrayList<VideoTape> videosArg) {
        ArrayList<Dimension> videoBoxListDimension = getVideoBoxListDimension(videosArg);

        setElementsLocation(videoBoxListDimension,
                VIDEO_LIST_AREA_RESTRICTION,
                GroupingOrder.STAGGERED);
    }

    private static ArrayList<Dimension> getVideoBoxListDimension(final ArrayList<VideoTape> videosArg) {
        ArrayList<Dimension> out = new ArrayList<>();

        for (VideoTape video: videosArg) {
            out.add(video.getBox());
        }

        return out;
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