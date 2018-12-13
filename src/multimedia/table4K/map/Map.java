package table4K.map;

import general.FileFormat;
import general.OrderElements;
import general.SectionKey;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.getDirKey;
import static general.FileProcessing.getFiles;
import static general.InitialLocation.initialPositionElementsForArea;
import static general.TextProcessing.readingFirstStokeFromFile;

import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;


public class Map {

    //Файл изображения фона текстового блока
    // для первоначальной сцены раздела Карты
    public static final File TEXT_BACKGROUND_FILE = new File(RESOURCES_PATH + "map/text_case.png");

    //Ключевое слово раздела Map
    private static final SectionKey MAP_KEY = SectionKey.MAP;

    //минимальное расстояние по горизонтали между блоками изначальной сцены
    public static double MAP_INITIAL_PANE_MIN_WIDTH_SPACING = 100 / debuggingRatio;

    //координаты области в которой должны разместиться
    // первоначальные текстовые блоки для выбора сражения
    public static double MAP_INITIAL_AREA_X_BEGIN = 800 / debuggingRatio;
    public static double MAP_INITIAL_AREA_Y_BEGIN = 350 / debuggingRatio;
    public static double MAP_INITIAL_AREA_X_END = 4000 / debuggingRatio;
    public static double MAP_INITIAL_AREA_Y_END = 2050 / debuggingRatio;

    //Карты:
    //внешний лист - лист сражений;
    //внутренний лист - карты для сражения.
    private static ArrayList<ArrayList<File>> mapImageFiles;

    //Текстовые файлы:
    //внешний лист - лист сражений;
    //внутренний лист - текстовые файлы для сражения.
    private static ArrayList<ArrayList<File>> mapTextFiles;


    public static void setMapScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_map.jpg");
        mainPane.getChildren().clear();

        //лист директорий, в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileMapDirs = new ArrayList<>(getDirKey(MAP_KEY));

        mapImageFiles = new ArrayList<>(getFiles(fileMapDirs, FileFormat.IMAGE));
        mapTextFiles = new ArrayList<>(getFiles(fileMapDirs, FileFormat.TEXT));

        //Список названий сражений
        ArrayList<String> operationNameList = new ArrayList<>();
        for (int i = 0; i < mapTextFiles.size(); i++) {
            operationNameList.add(readingFirstStokeFromFile(mapTextFiles.get(i).get(0)));
        }

        //Инициализация первоначального состояния раздела Карты
        // с расположением списка сражений
        for (int i = 0; i < operationNameList.size(); i++) {
            mainPane.getChildren().add(new MapPaneInitial(operationNameList.get(i),
                                        mapImageFiles.get(i),
                                        mapTextFiles.get(i)));
        }
        initialPositionElementsForArea(mainPane.getChildren(),
                MAP_INITIAL_PANE_MIN_WIDTH_SPACING,
                MAP_INITIAL_AREA_X_BEGIN,
                MAP_INITIAL_AREA_Y_BEGIN,
                MAP_INITIAL_AREA_X_END,
                MAP_INITIAL_AREA_Y_END,
                OrderElements.STAGGERED);

        mainPane.getChildren().add(returnHome());
    }

}
