package table4K.film;

import general.FileFormat;
import general.OrderElements;
import general.SectionKey;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.getDirKey;
import static general.FileProcessing.getFiles;
import static general.InitialLocation.initialPositionElementsForArea;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;

public class Film {

    //параметры области, в которой располагаются коробки с фильмами
    private static final double HORIZONTAL_INTERVAL_MIN = 300 / debuggingRatio;
    private static final double AREA_LOCATION_X_BEGIN = 1000 / debuggingRatio;
    private static final double AREA_LOCATION_Y_BEGIN = 500 / debuggingRatio;
    private static final double AREA_LOCATION_X_END = 3800 / debuggingRatio;
    private static final double AREA_LOCATION_Y_END = 1900 / debuggingRatio;

    //тень от коробки с фильмом
    private static final String FILM_BOX_SHADOW = "-fx-effect: dropshadow(gaussian, black, 20, 0.3, -3, 10);";

    //Видео:
    //внешний лист - папка;
    //внутренний лист - видеофайл.
    private static ArrayList<ArrayList<File>> videoFile;

    //Текст:
    //внешний лист - папка;
    //внутренний лист - текстовый файл с названием фильма.
    private static ArrayList<ArrayList<File>> textFile;

    //объект, который нужен как переходное звено
    // для синхронизации потока в котором получаются метаданные видеофайла в классе FilmShow
    // и основного потока при добавлении объектов класса FilmShow
    //Без этого костыля поток с получением метаданных видеофайла может не успеть отработать до того,
    // как к нему обрадится следующий объектов класса FilmShow,
    // в результате чего метаданные извлекутся не для всех фильмов
    static final Object obj = new Object();

    private static final int TIME_SLEEP = 100;


    public static void setFilmScene() {
        changeRootBackground(RESOURCES_PATH + "table_4K_film_initial.jpg");
        mainPane.getChildren().clear();

        ArrayList<File> fileDirs = new ArrayList<>(getDirKey(SectionKey.FILM, SectionKey.FILM.getKeyWord()));

        videoFile = new ArrayList<>(getFiles(fileDirs, FileFormat.VIDEO));

        textFile = new ArrayList<>(getFiles(fileDirs, FileFormat.TEXT));

        for (int i = 0; i < videoFile.size(); i++) {
            FilmInitialImage temp = new FilmInitialImage(videoFile.get(i).get(0), textFile.get(i).get(0));
            temp.setStyle(FILM_BOX_SHADOW);
            try {
                synchronized(obj){
                    obj.wait(TIME_SLEEP);
                }
            } catch (InterruptedException e) {}

            mainPane.getChildren().add(temp);
        }

        initialPositionElementsForArea(mainPane.getChildren(),
                HORIZONTAL_INTERVAL_MIN,
                AREA_LOCATION_X_BEGIN,
                AREA_LOCATION_Y_BEGIN,
                AREA_LOCATION_X_END,
                AREA_LOCATION_Y_END,
                OrderElements.STAGGERED);

        mainPane.getChildren().add(returnHome());
    }
}