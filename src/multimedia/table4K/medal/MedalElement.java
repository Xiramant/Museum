package table4K.medal;

import general.ImagePane;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

import static general.TextProcessing.readingFirstStokeFromFile;
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.debuggingRatio;
import static table4K.medal.Medal.descriptionPane;

public class MedalElement extends ImageView {

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

    public MedalElement(final ArrayList<File> imageFilesEnter, final ArrayList<File> textFilesEnter) {
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

        this.setOnMouseClicked(event -> {
            medalImageAction(imageFiles, textFiles);
        });
    }

    static void medalImageAction(final ArrayList<File> imageFilesEnter, final ArrayList<File> textFilesEnter) {

        descriptionPane.getChildren().clear();

        ArrayList<File> imageFilesAction = imageFilesEnter;
        ArrayList<File> textFilesAction = textFilesEnter;

        Text name = new Text();
        name.setText(readingFirstStokeFromFile(textFilesAction.get(0)));
        name.setFont(Font.font("Arial", FontWeight.BOLD, 80 / debuggingRatio));
        if (name.getLayoutBounds().getWidth() > 2100 / debuggingRatio) {
            name.setFont(Font.font("Arial Narrow", FontWeight.BOLD, 60 / debuggingRatio));
        }
        name.setFill(Color.rgb(167, 6, 6));
        name.setTextOrigin(VPos.BOTTOM);
        name.setLayoutX(1673 / debuggingRatio - name.getLayoutBounds().getWidth() / 2);
        name.setLayoutY(109 / debuggingRatio);

        Group medalGroup = new Group();

        for (int i = 0; i < imageFilesAction.size(); i++) {
            ImageView temp = new ImageView("file:" + imageFilesAction.get(i).toString());
            double ratio = temp.getLayoutBounds().getHeight() / temp.getLayoutBounds().getWidth();
            if (ratio > RATIO_MEDAL_OR_ORDEN) {
                temp.setFitHeight(800 / debuggingRatio);
            } else {
                temp.setFitHeight(600 / debuggingRatio);
            }
            if (i != 0) {
                temp.setLayoutX(medalGroup.getLayoutBounds().getWidth() + 100 / debuggingRatio);
            }
            temp.setPreserveRatio(true);
            medalGroup.getChildren().add(temp);
        }

        ImagePane descriptionImage = new ImagePane(new File(RESOURCES_PATH + "medal/description.jpg"), 0, 900 / debuggingRatio);
        Text descriptionText = new Text();


        descriptionPane.getChildren().addAll(name, medalGroup);
    }

}
