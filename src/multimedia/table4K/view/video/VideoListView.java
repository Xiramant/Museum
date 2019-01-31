package table4K.view.video;


import general.OrderElements;
import javafx.scene.Group;
import table4K.model.Dimension;
import table4K.model.video.VideoTape;


import java.util.ArrayList;

import static table4K.view.Location.setElementsLocation;
import static table4K.view.MainView.DEBUGGING_RATIO;



public class VideoListView {

    //параметры области, в которой располагаются коробки с фильмами
    private static final double HORIZONTAL_INTERVAL_MIN = 100 / DEBUGGING_RATIO;
    private static final double VERTICAL_INTERVAL_MIN = 50 / DEBUGGING_RATIO;

    private static final double AREA_LOCATION_X_BEGIN = 1000 / DEBUGGING_RATIO;
    private static final double AREA_LOCATION_Y_BEGIN = 500 / DEBUGGING_RATIO;
    private static final double AREA_LOCATION_X_END = 3800 / DEBUGGING_RATIO;
    private static final double AREA_LOCATION_Y_END = 1900 / DEBUGGING_RATIO;



    public static void setVideoListLocation(final ArrayList<VideoTape> videosArg) {

        ArrayList<Dimension> videoBoxesDimension = new ArrayList<>();
        for (VideoTape video: videosArg) {
            videoBoxesDimension.add(video.getBox());
        }

//        for (VideoTape video: videosArg) {
//            video.getBox().setScaleX(0.9);
//            video.getBox().setScaleY(0.9);
//        }

        setElementsLocation(videoBoxesDimension,
                HORIZONTAL_INTERVAL_MIN,
                AREA_LOCATION_X_BEGIN,
                AREA_LOCATION_Y_BEGIN,
                AREA_LOCATION_X_END,
                AREA_LOCATION_Y_END,
                OrderElements.STAGGERED);
    }

}