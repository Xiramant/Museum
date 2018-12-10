package table4K.medal;

import general.FileFormat;
import general.SectionKey;
import general.Slider;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.*;
import static general.SectionKey.*;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;
import static table4K.medal.MedalElement.MEDAL_SLIDER_IMAGE_HEIGHT_MAX;

public class Medal {

    protected static Pane descriptionPane;

    private static final SectionKey MEDAL_KEY = SectionKey.MEDAL;
    private static final SectionKey ORDEN_KEY = ORDEN;

    //Ордена в ВОВ:
    //внешний лист - медаль/орден;
    //внутренний лист - фото к данной медали/ордену.
    private static ArrayList<ArrayList<File>> medalImageFiles;

    //Ордена в ВОВ:
    //внешний лист - медаль/орден;
    //внутренний лист - тексты к данной медали/ордену.
    private static ArrayList<ArrayList<File>> medalTextFiles;

    //ширина слайдера для медалей/орденов
    private static final double MEDAL_SLIDER_WIDTH = 3250 / debuggingRatio;

    //высота слайдера для медалей/орденов
    private static final double MEDAL_SLIDER_HEIGHT = MEDAL_SLIDER_IMAGE_HEIGHT_MAX;

    //ширина сабсцены, играющей роль маски видимости
    // для медалей/орденов
    private static final double MEDAL_SUBSCENE_SLIDER_WIDTH = 2950 / debuggingRatio;

    //количество орденов, отображаемых в слайдере
    private static int medalSliderNumber;

    //отступы слайдера для выбора медали/ордена
    private static double MEDAL_SLIDER_X = 800 / debuggingRatio;
    private static double MEDAL_SLIDER_Y = 587 / debuggingRatio;

    //параметры Pane для выбора ордена
    private static final double SELECT_ORDEN_WIDTH = 500 / debuggingRatio;
    private static final double SELECT_ORDEN_HEIGHT = 470 / debuggingRatio;
    private static final double SELECT_ORDEN_X = 125 / debuggingRatio;
    private static final double SELECT_ORDEN_Y = 988 / debuggingRatio;

    //параметры Pane для выбора медали
    private static final double SELECT_MEDAL_WIDTH = 490 / debuggingRatio;
    private static final double SELECT_MEDAL_HEIGHT = 460 / debuggingRatio;
    private static final double SELECT_MEDAL_X = 97 / debuggingRatio;
    private static final double SELECT_MEDAL_Y = 1592 / debuggingRatio;

    //параметры descriptionPane
    public static final double DESCRIPTION_WIDTH = 3356 / debuggingRatio;
    public static final double DESCRIPTION_HEIGHT = 1111 / debuggingRatio;
    private static final double DESCRIPTION_X = 740 / debuggingRatio;
    private static final double DESCRIPTION_Y = 1049 / debuggingRatio;

    public static void setOrdenScene(final SectionKey medalType) {

        mainPane.getChildren().clear();

        descriptionPane = new Pane();
        descriptionPane.setPrefWidth(DESCRIPTION_WIDTH);
        descriptionPane.setPrefHeight(DESCRIPTION_HEIGHT);
        descriptionPane.setLayoutX(DESCRIPTION_X);
        descriptionPane.setLayoutY(DESCRIPTION_Y);

        //лист директорий медалей/орденов,
        // в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileDirs;

        //панель выбора отображения орденов или медалей
        Pane selectPane = new Pane();

        if (medalType == ORDEN) {
            changeRootBackground(RESOURCES_PATH + "table_4K_orden.jpg");
            fileDirs = new ArrayList<>(getDirKey(MEDAL_KEY, ORDEN_KEY.getKeyWord()));
            medalSliderNumber = 6;
            selectPane.setPrefWidth(SELECT_MEDAL_WIDTH);
            selectPane.setPrefHeight(SELECT_MEDAL_HEIGHT);
            selectPane.setLayoutX(SELECT_MEDAL_X);
            selectPane.setLayoutY(SELECT_MEDAL_Y);
        } else {
            changeRootBackground(RESOURCES_PATH + "table_4K_medal.jpg");
            fileDirs = new ArrayList<>(getDirKey(MEDAL_KEY));
            medalSliderNumber = 8;
            selectPane.setPrefWidth(SELECT_ORDEN_WIDTH);
            selectPane.setPrefHeight(SELECT_ORDEN_HEIGHT);
            selectPane.setLayoutX(SELECT_ORDEN_X);
            selectPane.setLayoutY(SELECT_ORDEN_Y);
        }

        medalImageFiles = new ArrayList<>(getFiles(fileDirs, FileFormat.IMAGE));
        medalTextFiles = new ArrayList<>(getFiles(fileDirs, FileFormat.TEXT));

        ArrayList<Node> medalList = new ArrayList<>();
        for (int i = 0; i < medalImageFiles.size(); i++) {
            medalList.add(new MedalElement(medalImageFiles.get(i), medalTextFiles.get(i)));
        }

        Slider sliderMedal = new Slider(MEDAL_SLIDER_WIDTH,
                MEDAL_SLIDER_HEIGHT,
                MEDAL_SUBSCENE_SLIDER_WIDTH,
                medalSliderNumber,
                medalList);

        sliderMedal.setLayoutX(MEDAL_SLIDER_X);
        sliderMedal.setLayoutY(MEDAL_SLIDER_Y);

        mainPane.getChildren().addAll(selectPane, sliderMedal, descriptionPane, returnHome());

        selectPane.setOnMouseClicked(event -> {
            if (selectPane.getLayoutY() == SELECT_ORDEN_Y) {
                setOrdenScene(ORDEN);
            } else {
                setOrdenScene(MEDAL);
            }
        });

        selectPane.setOnTouchReleased(event -> {
            if (selectPane.getLayoutY() == SELECT_ORDEN_Y) {
                setOrdenScene(ORDEN);
            } else {
                setOrdenScene(MEDAL);
            }
        });
    }
}
