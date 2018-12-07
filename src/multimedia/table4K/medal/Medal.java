package table4K.medal;

import general.FileFormat;
import general.SectionKey;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.getDirKey;
import static general.FileProcessing.getFiles;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;
import static table4K.Main4K.debuggingRatio;

public class Medal {

    private static final SectionKey MEDAL_KEY = SectionKey.MEDAL;
    private static final SectionKey ORDEN_KEY = SectionKey.ORDEN;

    //Ордена в ВОВ:
    //внешний лист - орден;
    //внутренний лист - фото к данному ордену.
    private static ArrayList<ArrayList<File>> medalImageFiles;

    //Ордена в ВОВ:
    //внешний лист - орден;
    //внутренний лист - тексты к данному ордену.
    private static ArrayList<ArrayList<File>> medalTextFiles;

    //отступы слайдера для выбора медали/ордена
    public static double MEDAL_SLIDER_X = 800 / debuggingRatio;
    public static double MEDAL_SLIDER_Y = 587 / debuggingRatio;

    public static void setOrdenScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_orden.jpg");
        mainPane.getChildren().clear();

        //лист директорий орденов,
        // в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileOrdenDirs = new ArrayList<>(getDirKey(MEDAL_KEY, ORDEN_KEY.getKeyWord()));

        medalImageFiles = new ArrayList<>(getFiles(fileOrdenDirs, FileFormat.IMAGE));
        medalTextFiles = new ArrayList<>(getFiles(fileOrdenDirs, FileFormat.TEXT));

        MedalSlider sliderMedal = new MedalSlider(medalImageFiles, medalTextFiles);
        sliderMedal.setLayoutX(MEDAL_SLIDER_X);
        sliderMedal.setLayoutY(MEDAL_SLIDER_Y);

        mainPane.getChildren().addAll(sliderMedal, returnHome());

    }
}
