package table4K.medal;

import general.FileFormat;
import general.SectionKey;
import general.Slider;
import javafx.scene.Node;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.Pane;
import table4K.ui.MainView;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.*;
import static general.SectionKey.*;
import static general.TouchWait.eventDelayBegin;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;
import static table4K.controller.ControllerParameters.isEventPermission;
import static table4K.medal.MedalElement.MEDAL_SLIDER_IMAGE_HEIGHT_MAX;
import static table4K.medal.MedalElement.medalImageActionGeneral;

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

    //панель выбора отображения орденов или медалей
    private static Pane selectPane;

    //ширина слайдера для медалей/орденов
    private static final double MEDAL_SLIDER_WIDTH = 3250 / MainView.DEBUGGING_RATIO;

    //высота слайдера для медалей/орденов
    private static final double MEDAL_SLIDER_HEIGHT = MEDAL_SLIDER_IMAGE_HEIGHT_MAX;

    //ширина сабсцены, играющей роль маски видимости
    // для медалей/орденов
    private static final double MEDAL_SUBSCENE_SLIDER_WIDTH = 2950 / MainView.DEBUGGING_RATIO;

    //количество орденов, отображаемых в слайдере
    private static int medalSliderNumber;

    //отступы слайдера для выбора медали/ордена
    private static final double MEDAL_SLIDER_X = 800 / MainView.DEBUGGING_RATIO;
    private static final double MEDAL_SLIDER_Y = 587 / MainView.DEBUGGING_RATIO;

    //параметры Pane для выбора ордена
    private static final double SELECT_ORDEN_WIDTH = 500 / MainView.DEBUGGING_RATIO;
    private static final double SELECT_ORDEN_HEIGHT = 470 / MainView.DEBUGGING_RATIO;
    private static final double SELECT_ORDEN_X = 125 / MainView.DEBUGGING_RATIO;
    private static final double SELECT_ORDEN_Y = 988 / MainView.DEBUGGING_RATIO;

    //параметры Pane для выбора медали
    private static final double SELECT_MEDAL_WIDTH = 490 / MainView.DEBUGGING_RATIO;
    private static final double SELECT_MEDAL_HEIGHT = 460 / MainView.DEBUGGING_RATIO;
    private static final double SELECT_MEDAL_X = 97 / MainView.DEBUGGING_RATIO;
    private static final double SELECT_MEDAL_Y = 1592 / MainView.DEBUGGING_RATIO;

    //параметры descriptionPane
    static final double DESCRIPTION_WIDTH = 3356 / MainView.DEBUGGING_RATIO;
    static final double DESCRIPTION_HEIGHT = 979 / MainView.DEBUGGING_RATIO;
    private static final double DESCRIPTION_X = MainView.TABLE_WIDTH - DESCRIPTION_WIDTH;
    private static final double DESCRIPTION_Y = MainView.TABLE_HEIGHT - DESCRIPTION_HEIGHT;


    public static void setMedalScene(final SectionKey medalType) {

        MainView.rootPane.getChildren().clear();

        descriptionPane = new Pane();
        descriptionPane.setPrefWidth(DESCRIPTION_WIDTH);
        descriptionPane.setPrefHeight(DESCRIPTION_HEIGHT);
        descriptionPane.setLayoutX(DESCRIPTION_X);
        descriptionPane.setLayoutY(DESCRIPTION_Y);

        //лист директорий медалей/орденов,
        // в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileDirs;

        selectPane = new Pane();

        //установка параметров при выборе подраздела Ордена или Медали
        if (medalType == ORDEN) {
            MainView.changeRootPaneBackground(RESOURCES_PATH + "table_4K_orden.jpg");
            fileDirs = new ArrayList<>(getDirKey(MEDAL_KEY, ORDEN_KEY.getKeyWord()));
            medalSliderNumber = 6;
            selectPane.setPrefWidth(SELECT_MEDAL_WIDTH);
            selectPane.setPrefHeight(SELECT_MEDAL_HEIGHT);
            selectPane.setLayoutX(SELECT_MEDAL_X);
            selectPane.setLayoutY(SELECT_MEDAL_Y);
        } else {
            MainView.changeRootPaneBackground(RESOURCES_PATH + "table_4K_medal.jpg");
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
        medalImageActionGeneral(medalImageFiles.get(4), medalTextFiles.get(4));

        MainView.rootPane.getChildren().addAll(selectPane, sliderMedal, descriptionPane, returnHome());

        //действия по выбору подраздела Ордена или Медали
        selectPane.setOnMouseClicked(event -> medalAction(event));
        selectPane.setOnTouchReleased(event -> medalAction(event));
    }

    private static void medalAction(final InputEvent event) {
        if (isEventPermission(event)) {
            if (selectPane.getLayoutY() == SELECT_ORDEN_Y) {
                setMedalScene(ORDEN);
            } else {
                setMedalScene(MEDAL);
            }

            eventDelayBegin();
        }
    }
}
