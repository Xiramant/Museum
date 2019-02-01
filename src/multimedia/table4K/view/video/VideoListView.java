package table4K.view.video;


import general.OrderElements;
import table4K.model.Area;
import table4K.model.Dimension;
import table4K.model.video.VideoTape;


import java.util.ArrayList;

import static table4K.view.Location.setElementsLocation;
import static table4K.view.MainView.*;


public class VideoListView {

    //минимальные интервалы между элементами
    private static final double HORIZONTAL_SPACING_MIN = 100 / DEBUGGING_RATIO;
    private static final double VERTICAL_SPACING_MIN = 50 / DEBUGGING_RATIO;

    //область, в которой располагаются коробки с фильмами
    private static final Area VIDEO_LIST_AREA_RESTRICTION = new Area(1000 / DEBUGGING_RATIO,
                                                                                            TABLE_WIDTH - (250 / DEBUGGING_RATIO),
                                                                                            500 / DEBUGGING_RATIO,
                                                                                            TABLE_HEIGHT - (250 / DEBUGGING_RATIO));

    private static final double SCALE_FACTOR_INITIAL = 1;
    private static final double SCALE_FACTOR_DECREASE = 0.9;



    public static void setVideoListLocation(final ArrayList<VideoTape> videosArg) {

        ArrayList<Dimension> videoBoxListDimension = setVideoBoxListDimension(videosArg);

        double scaleFactor = SCALE_FACTOR_INITIAL;
        double verticalInterval;

        do {
            setVideoBoxScale(videosArg, scaleFactor);

            verticalInterval = setElementsLocation(videoBoxListDimension,
                                                    HORIZONTAL_SPACING_MIN,
                                                    VIDEO_LIST_AREA_RESTRICTION,
                                                    OrderElements.STAGGERED);

            scaleFactor *= SCALE_FACTOR_DECREASE;

        } while (verticalInterval < VERTICAL_SPACING_MIN);

    }

    private static ArrayList<Dimension> setVideoBoxListDimension(final ArrayList<VideoTape> videosArg) {
        ArrayList<Dimension> out = new ArrayList<>();

        for (VideoTape video: videosArg) {
            out.add(video.getBox());
        }

        return out;
    }

    private static void setVideoBoxScale(final ArrayList<VideoTape> videosArg, final double scaleFactorArg) {
        for (VideoTape video: videosArg) {
            video.getBox().setScaleX(scaleFactorArg);
            video.getBox().setScaleY(scaleFactorArg);
        }
    }

}