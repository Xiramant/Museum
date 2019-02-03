package table4K.map;

import general.FileFormat;
import general.GroupingOrder;
import general.SectionKey;
import table4K.view.MainView;

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
    static final File TEXT_BACKGROUND_FILE = new File(RESOURCES_PATH + "map/text_case.png");

    //Ключевое слово раздела Map
    private static final SectionKey MAP_KEY = SectionKey.MAP;

    //минимальное расстояние по горизонтали между блоками изначальной сцены
    private static final double MAP_INITIAL_PANE_MIN_WIDTH_SPACING = 100 / MainView.DEBUGGING_RATIO;

    //координаты области в которой должны разместиться
    // первоначальные текстовые блоки для выбора сражения
    static final double MAP_INITIAL_AREA_X_BEGIN = 800 / MainView.DEBUGGING_RATIO;
    static final double MAP_INITIAL_AREA_Y_BEGIN = 350 / MainView.DEBUGGING_RATIO;
    static final double MAP_INITIAL_AREA_X_END = 4000 / MainView.DEBUGGING_RATIO;
    static final double MAP_INITIAL_AREA_Y_END = 2050 / MainView.DEBUGGING_RATIO;

    //Карты:
    //внешний лист - лист сражений;
    //внутренний лист - карты для сражения.
    private static ArrayList<ArrayList<File>> mapImageFiles;

    //Текстовые файлы:
    //внешний лист - лист сражений;
    //внутренний лист - текстовые файлы для сражения.
    private static ArrayList<ArrayList<File>> mapTextFiles;


    public static void setMapScene() {

        MainView.setMainSceneBackground(RESOURCES_PATH + "table_4K_map.jpg");
        MainView.rootPane.getChildren().clear();

        //лист директорий, в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileMapDirs = new ArrayList<>(getDirKey(MAP_KEY));

        mapImageFiles = new ArrayList<>(getFiles(fileMapDirs, FileFormat.IMAGE));
        mapTextFiles = new ArrayList<>(getFiles(fileMapDirs, FileFormat.TEXT));

        //Список названий сражений
        ArrayList<String> operationNameList = new ArrayList<>();
        for (ArrayList<File> mapTextFile : mapTextFiles) {
            operationNameList.add(readingFirstStokeFromFile(mapTextFile.get(0)));
        }

        //Инициализация первоначального состояния раздела Карты
        // с расположением списка сражений
        for (int i = 0; i < operationNameList.size(); i++) {
            MainView.rootPane.getChildren().add(new MapPaneInitial(operationNameList.get(i),
                                        mapImageFiles.get(i),
                                        mapTextFiles.get(i)));
        }
        initialPositionElementsForArea(MainView.rootPane.getChildren(),
                MAP_INITIAL_PANE_MIN_WIDTH_SPACING,
                MAP_INITIAL_AREA_X_BEGIN,
                MAP_INITIAL_AREA_Y_BEGIN,
                MAP_INITIAL_AREA_X_END,
                MAP_INITIAL_AREA_Y_END,
                GroupingOrder.STAGGERED);

        MainView.rootPane.getChildren().add(returnHome());
    }

}
