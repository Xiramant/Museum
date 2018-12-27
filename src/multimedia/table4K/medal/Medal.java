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
import static general.TouchWait.isTimeWaitEnd;
import static general.TouchWait.setTimeWait;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;
import static table4K.medal.MedalElement.MEDAL_SLIDER_IMAGE_HEIGHT_MAX;
import static table4K.medal.MedalElement.medalImageAction;

public class Medal {

    static Pane descriptionPane;

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
    private static final double MEDAL_SLIDER_WIDTH = 3250 / DEBUGGING_RATIO;

    //высота слайдера для медалей/орденов
    private static final double MEDAL_SLIDER_HEIGHT = MEDAL_SLIDER_IMAGE_HEIGHT_MAX;

    //ширина сабсцены, играющей роль маски видимости
    // для медалей/орденов
    private static final double MEDAL_SUBSCENE_SLIDER_WIDTH = 2950 / DEBUGGING_RATIO;

    //количество орденов, отображаемых в слайдере
    private static int medalSliderNumber;

    //отступы слайдера для выбора медали/ордена
    private static final double MEDAL_SLIDER_X = 800 / DEBUGGING_RATIO;
    private static final double MEDAL_SLIDER_Y = 587 / DEBUGGING_RATIO;

    //параметры Pane для выбора ордена
    private static final double SELECT_ORDEN_WIDTH = 500 / DEBUGGING_RATIO;
    private static final double SELECT_ORDEN_HEIGHT = 470 / DEBUGGING_RATIO;
    private static final double SELECT_ORDEN_X = 125 / DEBUGGING_RATIO;
    private static final double SELECT_ORDEN_Y = 988 / DEBUGGING_RATIO;

    //параметры Pane для выбора медали
    private static final double SELECT_MEDAL_WIDTH = 490 / DEBUGGING_RATIO;
    private static final double SELECT_MEDAL_HEIGHT = 460 / DEBUGGING_RATIO;
    private static final double SELECT_MEDAL_X = 97 / DEBUGGING_RATIO;
    private static final double SELECT_MEDAL_Y = 1592 / DEBUGGING_RATIO;

    //параметры descriptionPane
    static final double DESCRIPTION_WIDTH = 3356 / DEBUGGING_RATIO;
    static final double DESCRIPTION_HEIGHT = 979 / DEBUGGING_RATIO;
    private static final double DESCRIPTION_X = TABLE_WIDTH - DESCRIPTION_WIDTH;
    private static final double DESCRIPTION_Y = TABLE_HEIGHT - DESCRIPTION_HEIGHT;


    public static void setMedalScene(final SectionKey medalType) {

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

        //установка параметров при выборе подраздела Ордена или Медали
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

        //установка слайдера для орденов/медалей
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

        //показ Ордена Кутузова или Медали Нахимова при выборе секции
        medalImageAction(medalImageFiles.get(4), medalTextFiles.get(4));

        mainPane.getChildren().addAll(selectPane, sliderMedal, descriptionPane, returnHome());

        //действия по выбору подраздела Ордена или Медали
        selectPane.setOnMouseClicked(event -> {
            if (selectPane.getLayoutY() == SELECT_ORDEN_Y) {
                setMedalScene(ORDEN);
            } else {
                setMedalScene(MEDAL);
            }
        });

        selectPane.setOnTouchReleased(event -> {
            if (isTimeWaitEnd()) {
                if (selectPane.getLayoutY() == SELECT_ORDEN_Y) {
                    setMedalScene(ORDEN);
                } else {
                    setMedalScene(MEDAL);
                }

                setTimeWait();
            }
        });
    }
}
