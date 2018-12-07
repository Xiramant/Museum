package table4K.medal;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;

import static table4K.Main4K.debuggingRatio;

public class MedalImage extends ImageView {

    //список файлов изображений медалей/орденов
    private ArrayList<File> imageFiles;

    //список файлов текста медалей/орденов
    private ArrayList<File> textFiles;

    //величина соотношения высоты и длины изображения медали/ордена
    // для определения медаль это или орден
    private static double RATIO_MEDAL_OR_ORDEN = 1.5;

    //максимальная высота изображения медали для слайдера,
    // а также ордена, похожего на медаль
    public static double MEDAL_SLIDER_IMAGE_HEIGHT_MAX = 400 / debuggingRatio;

    //максимальная высота изображения медали для ордена
    private static double ORDEN_SLIDER_IMAGE_HEIGHT_MAX = 300 / debuggingRatio;

    public MedalImage(ArrayList<File> imageFilesEnter, ArrayList<File> textFilesEnter) {
        super(new Image("file:" + imageFilesEnter.get(0).toString()));

        imageFiles = imageFilesEnter;
        textFiles = textFilesEnter;

        double ratio = this.getLayoutBounds().getHeight() / this.getLayoutBounds().getWidth();

        if (ratio > RATIO_MEDAL_OR_ORDEN) {
            this.setFitHeight(MEDAL_SLIDER_IMAGE_HEIGHT_MAX);
        } else {
            this.setFitHeight(ORDEN_SLIDER_IMAGE_HEIGHT_MAX);
        }

        this.setPreserveRatio(true);
        this.setStyle("-fx-effect: dropshadow(gaussian, black, 6, 0.3, -1, 1);");
    }

}
