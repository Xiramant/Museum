package table4K.map;

import general.ArrayListIndex;
import general.FileFormat;
import general.SectionKey;
import javafx.scene.layout.Pane;


import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.getDirKey;
import static general.FileProcessing.getFiles;
import static general.InitialLocation.initialPositionElements;
import static general.InitialLocation.initialPositionElementsForArea;
import static general.TextProcessing.readingFirstStokeFromFile;

import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.changeRootBackground;
import static table4K.Main4K.mainPane;


public class Map {

    //Файл изображения фона текстового блока
    // для первоначальной сцены раздела Карты
    public static final File TEXT_BACKGROUND_FILE = new File(RESOURCES_PATH + "map/text_case.png");

    //Ключевое слово раздела Map
    private static final SectionKey MAP_KEY = SectionKey.MAP;

    //минимальное расстояние по горизонтали между блоками изначальной сцены
    public static double MAP_INITIAL_PANE_MIN_WIDTH_SPACING = 100;

    //координаты области в которой должны разместиться
    // первоначальные текстовые блоки для выбора сражения
    public static double MAP_INITIAL_AREA_X_BEGIN = 800;
    public static double MAP_INITIAL_AREA_Y_BEGIN = 350;
    public static double MAP_INITIAL_AREA_X_END = 4000;
    public static double MAP_INITIAL_AREA_Y_END = 2050;

    //Карты:
    //внешний лист - лист сражений;
    //внутренний лист - карты для сражения.
    private static ArrayList<ArrayList<File>> mapImageFiles;

    //Текстовые файлы:
    //внешний лист - лист сражений;
    //внутренний лист - текстовые файлы для сражения.
    private static ArrayList<ArrayList<File>> mapTextFiles;

    //лист стрингов, в которые преобразуется содержание текстовых файлов
    private static ArrayListIndex<String> mapTextString = new ArrayListIndex<>();


    public static void setMapScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_map.jpg");

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
        mainPane.getChildren().clear();
        for (int i = 0; i < operationNameList.size(); i++) {
            mainPane.getChildren().add(new MapPaneInitial(operationNameList.get(i)));
        }
        initialPositionElementsForArea(mainPane.getChildren(),
                MAP_INITIAL_PANE_MIN_WIDTH_SPACING,
                MAP_INITIAL_AREA_X_BEGIN,
                MAP_INITIAL_AREA_Y_BEGIN,
                MAP_INITIAL_AREA_X_END,
                MAP_INITIAL_AREA_Y_END);

        for (int i = 0; i < mainPane.getChildren().size(); i++) {
            System.out.println("");
            System.out.println("i = " + i +
                    "; x = " + mainPane.getChildren().get(i).getLayoutX() +
                    "; y = " + mainPane.getChildren().get(i).getLayoutY());

        }

    }

}
