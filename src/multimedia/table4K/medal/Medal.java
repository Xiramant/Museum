package table4K.medal;

import general.FileFormat;
import general.SectionKey;
import general.Slider;
import javafx.scene.Node;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.*;
import static general.SectionKey.*;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;
import static table4K.medal.MedalImage.MEDAL_SLIDER_IMAGE_HEIGHT_MAX;

public class Medal {

    private static final SectionKey MEDAL_KEY = SectionKey.MEDAL;
    private static final SectionKey ORDEN_KEY = ORDEN;

    //Ордена в ВОВ:
    //внешний лист - орден;
    //внутренний лист - фото к данному ордену.
    private static ArrayList<ArrayList<File>> medalImageFiles;

    //Ордена в ВОВ:
    //внешний лист - орден;
    //внутренний лист - тексты к данному ордену.
    private static ArrayList<ArrayList<File>> medalTextFiles;

    //ширина слайдера для медалей
    private static final double MEDAL_SLIDER_WIDTH = 3250 / debuggingRatio;

    //высота слайдера для медалей
    private static final double MEDAL_SLIDER_HEIGHT = MEDAL_SLIDER_IMAGE_HEIGHT_MAX;

    //ширина сабсцены, играющей роль маски видимости
    // для медалей
    private static final double MEDAL_SUBSCENE_SLIDER_WIDTH = 2950 / debuggingRatio;

    //количество орденов, отображаемых в слайдере
    private static int medalSliderNumber;

    //отступы слайдера для выбора медали/ордена
    public static double MEDAL_SLIDER_X = 800 / debuggingRatio;
    public static double MEDAL_SLIDER_Y = 587 / debuggingRatio;

    public static void setOrdenScene(final SectionKey medalType) {

        mainPane.getChildren().clear();

        //лист директорий медалей/орденов,
        // в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileDirs;

        if (medalType == ORDEN) {
            changeRootBackground(RESOURCES_PATH + "table_4K_orden.jpg");
            fileDirs = new ArrayList<>(getDirKey(MEDAL_KEY, ORDEN_KEY.getKeyWord()));
            medalSliderNumber = 6;
        } else {
            changeRootBackground(RESOURCES_PATH + "table_4K_medal.jpg");
            fileDirs = new ArrayList<>(getDirKey(MEDAL_KEY));
            medalSliderNumber = 8;
        }

        medalImageFiles = new ArrayList<>(getFiles(fileDirs, FileFormat.IMAGE));
        medalTextFiles = new ArrayList<>(getFiles(fileDirs, FileFormat.TEXT));

        ArrayList<Node> medalList = new ArrayList<>();
        for (int i = 0; i < medalImageFiles.size(); i++) {
            medalList.add(new MedalImage(medalImageFiles.get(i), medalTextFiles.get(i)));
        }

        Slider sliderMedal = new Slider(MEDAL_SLIDER_WIDTH,
                MEDAL_SLIDER_HEIGHT,
                MEDAL_SUBSCENE_SLIDER_WIDTH,
                medalSliderNumber,
                medalList);

        sliderMedal.setLayoutX(MEDAL_SLIDER_X);
        sliderMedal.setLayoutY(MEDAL_SLIDER_Y);

        mainPane.getChildren().addAll(sliderMedal, returnHome());
    }
}
