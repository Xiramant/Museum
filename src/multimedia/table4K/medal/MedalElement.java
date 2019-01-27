package table4K.medal;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

import static general.TextProcessing.readingFirstStokeFromFile;
import static general.TouchWait.eventDelayBegin;
import static table4K.view.MainView.DEBUGGING_RATIO;
import static table4K.controller.ControllerParameters.isEventPermission;
import static table4K.medal.Medal.DESCRIPTION_HEIGHT;
import static table4K.medal.Medal.DESCRIPTION_WIDTH;
import static table4K.medal.Medal.descriptionPane;

public class MedalElement extends ImageView {

    //список файлов изображений медалей/орденов
    private ArrayList<File> imageFiles;

    //список файлов текста медалей/орденов
    private ArrayList<File> textFiles;

    //величина соотношения высоты и длины изображения медали/ордена
    // для определения медаль это или орден
    static final double RATIO_MEDAL_OR_ORDEN = 1.5;

    //максимальная высота изображения медали для слайдера,
    // а также ордена, похожего на медаль
    static final double MEDAL_SLIDER_IMAGE_HEIGHT_MAX = 400 / DEBUGGING_RATIO;

    //максимальная высота изображения медали для ордена
    private static final double ORDEN_SLIDER_IMAGE_HEIGHT_MAX = 300 / DEBUGGING_RATIO;

    //тень изображения медали/ордена для слайдера
    private static final String MEDAL_SLIDER_IMAGE_SHADOW = "-fx-effect: dropshadow(gaussian, black, 6, 0.3, -1, 1);";

    //размер плашки, при превышении которой следует переходить к уменьшенному шрифту
    private static final double MEDAL_NAME_SIZE_MAX = 2100 / DEBUGGING_RATIO;

    //размер шрифта для названия медали/ордена
    private static final double MEDAL_NAME_FONT_SIZE = 80 / DEBUGGING_RATIO;

    //размер уменьшенного шрифта для названия медали/ордена
    private static final double MEDAL_NAME_FONT_SMALL_SIZE = 60 / DEBUGGING_RATIO;

    //шрифт для названия медали/ордена
    private static final Font MEDAL_NAME_FONT = Font.font("Arial", FontWeight.BOLD, MEDAL_NAME_FONT_SIZE);

    //уменьшенный шрифт для названия медали/ордена
    private static final Font MEDAL_NAME_FONT_SMALL = Font.font("Arial Narrow", FontWeight.BOLD, MEDAL_NAME_FONT_SMALL_SIZE);

    //цвет шрифта для названия медали/ордена
    private static final Color MEDAL_NAME_COLOR = Color.rgb(167, 6, 6);

    //положение серединной точки для названия медали/ордена
    private static final double MEDAL_NAME_CENTER_X = 1673 / DEBUGGING_RATIO;

    //положение нижней точки для названия медали/ордена
    private static final double MEDAL_NAME_BOTTOM_Y = -40 / DEBUGGING_RATIO;

    //интервал между медалями/орденами по горизонтали
    private static final double MEDAL_HORIZONTAL_INTERVAL = 100 / DEBUGGING_RATIO;

    //интервал до панели с текстом описания медали/ордена
    private static final double MEDAL_DESCRIPTION_INTERVAL = 150 / DEBUGGING_RATIO;


    MedalElement(final ArrayList<File> imageFilesEnter, final ArrayList<File> textFilesEnter) {
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
        this.setStyle(MEDAL_SLIDER_IMAGE_SHADOW);

        this.setOnMouseClicked(event -> medalImageAction(event, imageFiles, textFiles));
        this.setOnTouchReleased(event -> medalImageAction(event, imageFiles, textFiles));
    }

    private static void medalImageAction(final InputEvent event,
                                 final ArrayList<File> imageFilesEnter,
                                 final ArrayList<File> textFilesEnter) {

        if (isEventPermission(event)) {
            medalImageActionGeneral(imageFilesEnter, textFilesEnter);
            eventDelayBegin();
        }
    }

    static void medalImageActionGeneral(final ArrayList<File> imageFilesEnter,
                                 final ArrayList<File> textFilesEnter) {

        descriptionPane.getChildren().clear();

        ArrayList<File> imageFilesAction = imageFilesEnter;
        ArrayList<File> textFilesAction = textFilesEnter;

        Text name = setMedalNameText(textFilesAction.get(0));

        Group medalGroup = addElementsInGroup(imageFilesAction, textFilesAction.get(1));

        descriptionPane.getChildren().add(name);

        //Попытка передачи элементов в descriptionPane
        // непосредственно из группы medalGroup обычным способом неудалась,
        // т.к. при передаче элемента в descriptionPane он удалялся из группы medalGroup
        // и соответственно уменьшался размер (size) группы.
        //Пришлось соорудить следующую конструкцию:
        int sizeTemp = medalGroup.getChildren().size();
        for (int i = 0; i < sizeTemp; i++) {
            descriptionPane.getChildren().add(medalGroup.getChildren().get(0));
        }
    }

    //установка названия медали/ордена
    // на вход передается файл с путем до текстового файла с названием медали/ордена
    private static Text setMedalNameText(final File fileName) {

        Text name = new Text();

        name.setText(readingFirstStokeFromFile(fileName));
        name.setFont(MEDAL_NAME_FONT);
        if (name.getLayoutBounds().getWidth() > MEDAL_NAME_SIZE_MAX) {
            name.setFont(MEDAL_NAME_FONT_SMALL);
        }
        name.setFill(MEDAL_NAME_COLOR);
        name.setTextOrigin(VPos.BOTTOM);
        name.setLayoutX(MEDAL_NAME_CENTER_X - name.getLayoutBounds().getWidth() / 2);
        name.setLayoutY(MEDAL_NAME_BOTTOM_Y / DEBUGGING_RATIO);

        return name;
    }

    //добавление в группу медалей/орденов и текстовой панели с описанием
    private static Group addElementsInGroup(final ArrayList<File> imageFiles, final File textFile) {

        Group medalGroup = new Group();

        //добавление в группу медалей/орденов
        for (int i = 0; i < imageFiles.size(); i++) {
            MedalDescriptionImage temp = new MedalDescriptionImage(imageFiles.get(i));

            if (i != 0) {
                temp.setLayoutX(medalGroup.getLayoutBounds().getWidth() + MEDAL_HORIZONTAL_INTERVAL);
            }

            temp.setLayoutY(DESCRIPTION_HEIGHT / 2 - temp.getLayoutBounds().getHeight() / 2);

            medalGroup.getChildren().add(temp);
        }

        //добавление в группу текстовой панели с описанием
        MedalDescriptionText mdt = new MedalDescriptionText(textFile);
        mdt.setLayoutX(medalGroup.getLayoutBounds().getWidth() + MEDAL_DESCRIPTION_INTERVAL);
        mdt.setLayoutY(DESCRIPTION_HEIGHT / 2 - mdt.getPrefHeight() / 2);

        medalGroup.getChildren().add(mdt);

        //установка отступов слева для descriptionPane
        setLeftInsetForGroupElements(medalGroup);

        return medalGroup;
    }

    //задание отступов слева для элементов группы
    // для правильного расположения в descriptionPane
    private static void setLeftInsetForGroupElements(final Group medalGroup) {
        double leftInset = DESCRIPTION_WIDTH / 2 - medalGroup.getLayoutBounds().getWidth() / 2;

        for (int i = 0; i < medalGroup.getChildren().size(); i++) {
            medalGroup.getChildren().get(i).setLayoutX(medalGroup.getChildren().get(i).getLayoutX() + leftInset);
        }
    }

}
