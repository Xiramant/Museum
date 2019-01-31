package table4K.model.video;

import general.OrderElements;
import javafx.scene.Group;
import javafx.scene.Node;
import table4K.view.MainView;


import java.util.ArrayList;

import static general.InitialLocation.initialPositionElementsForArea;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.data.FileProcessing.createImage;
import static table4K.data.video.VideoData.getVideoContentList;
import static table4K.model.video.VideoPlayer.showVideo;
import static table4K.view.MainView.setMainScene;


public class VideoList {

    //параметры области, в которой располагаются коробки с фильмами
    private static final double HORIZONTAL_INTERVAL_MIN = 300 / MainView.DEBUGGING_RATIO;
    private static final double AREA_LOCATION_X_BEGIN = 1000 / MainView.DEBUGGING_RATIO;
    private static final double AREA_LOCATION_Y_BEGIN = 500 / MainView.DEBUGGING_RATIO;
    private static final double AREA_LOCATION_X_END = 3800 / MainView.DEBUGGING_RATIO;
    private static final double AREA_LOCATION_Y_END = 1900 / MainView.DEBUGGING_RATIO;

    public static void setVideoScene() {

        ArrayList<VideoContent> videoContents = getVideoContentList();

        ArrayList<VideoTape> videos = new ArrayList<>();
        for (VideoContent content: videoContents) {
            videos.add(new VideoTape(content));
        }

        Group videoBoxes = new Group();
        for (VideoTape video: videos) {
            videoBoxes.getChildren().add(video.getBox());
        }

        initialPositionElementsForArea(videoBoxes.getChildren(),
        HORIZONTAL_INTERVAL_MIN,
        AREA_LOCATION_X_BEGIN,
        AREA_LOCATION_Y_BEGIN,
        AREA_LOCATION_X_END,
        AREA_LOCATION_Y_END,
        OrderElements.STAGGERED);

        ArrayList<Node> graphicElements = new ArrayList<>();
        graphicElements.add(videoBoxes);
        graphicElements.add(returnHome());

        setMainScene(createImage(RESOURCES_PATH + "table_4K_film_initial.jpg"), graphicElements);


//        setLocation();











//
//        ArrayList<File> fileDirs = new ArrayList<>(getDirKey(SectionKey.FILM, SectionKey.FILM.getKeyWord()));
//
//        videoFile = new ArrayList<>(getFiles(fileDirs, FileFormat.VIDEO));
//
//        textFile = new ArrayList<>(getFiles(fileDirs, FileFormat.TEXT));
//
//
//
//        for (int i = 0; i < videoFile.size(); i++) {
//            FilmInitialImage temp = new FilmInitialImage(videoFile.get(i).get(0), textFile.get(i).get(0));
//            temp.setStyle(FILM_BOX_SHADOW);
//            try {
//                synchronized(obj){
//                    obj.wait(TIME_SLEEP);
//                }
//            } catch (InterruptedException e) {}
//
//            MainView.rootPane.getChildren().add(temp);
//        }
//
//        initialPositionElementsForArea(MainView.rootPane.getChildren(),
//                HORIZONTAL_INTERVAL_MIN,
//                AREA_LOCATION_X_BEGIN,
//                AREA_LOCATION_Y_BEGIN,
//                AREA_LOCATION_X_END,
//                AREA_LOCATION_Y_END,
//                OrderElements.STAGGERED);
//
        //        MainView.setMainSceneBackground(RESOURCES_PATH + "table_4K_film_initial.jpg");
//        MainView.rootPane.getChildren().clear();
//        MainView.rootPane.getChildren().add(returnHome());
    }
}
