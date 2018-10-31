package table.section.map;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import general.*;

import java.io.File;
import java.util.ArrayList;

import static table.Main.*;
import static general.FileProcessing.*;
import static general.InitialLocation.initialPositionElements;
import static general.TextProcessing.*;

public class Map {

    //Файл изображения фона для текстового блока
    public static final File TEXT_BACKGROUND_FILE = new File(RESOURCES_PATH + "map/text_case_0_1.png");

    //Ключевое слово раздела Map
    private static final SectionKey MAP_KEY = SectionKey.MAP;

    //минимальное расстояние по горизонтали между блоками изначальной сцены
    private static final double MIN_WIDTH_SPACING = 30;

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

    //флаг осуществления перемещения текстового блока
    private static Boolean isDragAndDropMapText = false;

    //метод установки элементов раздела Map в sectionPane
    public static void setMapScene() {

        //лист директорий, в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileMapDirs = new ArrayList<>(getDir(MAP_KEY));

        mapImageFiles = new ArrayList<>(getFiles(fileMapDirs, FileFormat.IMAGE));
        mapTextFiles = new ArrayList<>(getFiles(fileMapDirs, FileFormat.TEXT));

        //Список названий сражений
        ArrayList<String> operationNameList = new ArrayList<>();
        for (int i = 0; i < mapTextFiles.size(); i++) {
            operationNameList.add(readingFirstStokeFromFile(mapTextFiles.get(i).get(0)));
        }

        //Инициализация первоначального состояния раздела Карты
        // с расположением списка сражений
        sectionPane.getChildren().clear();
        for (int i = 0; i < operationNameList.size(); i++) {
            sectionPane.getChildren().add(new InitialMapPane(operationNameList.get(i)));
        }
        initialPositionElements(sectionPane.getChildren(), ((Pane) sectionPane.getChildren().get(0)).getPrefWidth() + MIN_WIDTH_SPACING);

        //Обработка действий при нажатии на панель с названием операции
        for (int i = 0; i < sectionPane.getChildren().size(); i++) {
            actionProcessingInitialMap(i);
        }
    }

    //Метод обработки действий пользователя на начальной сцене
    public static void actionProcessingInitialMap(final int currentPanelIndex) {

        ImagePane currentPanel = (ImagePane) sectionPane.getChildren().get(currentPanelIndex);

        //действия по клику мышкой
        currentPanel.setOnMouseClicked(event -> {

            sectionPane.getChildren().clear();

            ArrayList<File> imageList = new ArrayList<>(mapImageFiles.get(currentPanelIndex));
            ArrayList<File> textList = new ArrayList<>(mapTextFiles.get(currentPanelIndex));

            mapTextString.clear();
            for (int i = 0; i < textList.size(); i++) {
                mapTextString.add(readingFileIntoString(textList.get(i)));
            }

            ImagePaneMap maps = new ImagePaneMap(imageList);
            maps.setLayoutX((TABLE_CENTER_SECTION_WIDTH - maps.getPrefWidth()) / 2);
            maps.setLayoutY((TABLE_CENTER_SECTION_HEIGHT - maps.getPrefHeight()) / 2);

            TextPaneMap text = new TextPaneMap(mapTextString.get(maps.getCurrentBackgroundIndex()));
            text.setLayoutX(TABLE_CENTER_SECTION_WIDTH - text.getPrefWidth());
            text.setLayoutY(TABLE_CENTER_SECTION_HEIGHT - text.getPrefHeight());

            sectionPane.getChildren().addAll(maps, text);

            //Обработка действий при нажатии на элементы в основном разделе
            for (int i = 0; i < sectionPane.getChildren().size(); i++) {
                actionProcessingMainMap(i);
            }
        });
    }

    //Метод обработки действий пользователя на основной сцене
    public static void actionProcessingMainMap(final int currentPanelIndex) {

        //при нажатии на карту
        if (currentPanelIndex == 0) {

            ImagePaneMap ips = (ImagePaneMap)sectionPane.getChildren().get(currentPanelIndex);

            ips.setOnMouseClicked(event -> {

                int prevBackgroundIndex = ips.getCurrentBackgroundIndex();

                //нажатие левой кнопки приводит к листанию текста вперед
                if (event.getButton() == MouseButton.PRIMARY) {
                    ips.setNextImageBackground();
                }

                //нажатие правой кнопки приводит к листанию текста назад
                if (event.getButton() == MouseButton.SECONDARY) {
                    ips.setPrevImageBackground();
                }

                //Двойной щелчок левой кнопкой приводит к переносу текста на последнюю страницу
                if (event.getClickCount() == 2) {
                    ips.setLastImageBackground();
                }

                //Двойной щелчок правой кнопкой приводит к возвращению текста на первую страницу
                if (event.getButton() == MouseButton.SECONDARY && event.getClickCount() == 2) {
                    ips.setFirstImageBackground();
                }

                int currentBackgroundIndex = ips.getCurrentBackgroundIndex();

                //действия, если произошла смена карты:
                //меняется текстовый блок, но его положение сохраняется
                if (currentBackgroundIndex != prevBackgroundIndex) {

                    ips.setLayoutX((TABLE_CENTER_SECTION_WIDTH - ips.getPrefWidth()) / 2);
                    ips.setLayoutY((TABLE_CENTER_SECTION_HEIGHT - ips.getPrefHeight()) / 2);

                    TextPaneMap tpmNew = new TextPaneMap(mapTextString.get(ips.getCurrentBackgroundIndex()));
                    tpmNew.setLayoutX( sectionPane.getChildren().get(1).getLayoutX());
                    tpmNew.setLayoutY( sectionPane.getChildren().get(1).getLayoutY());
                    sectionPane.getChildren().set(1, tpmNew);

                    //действия по нажатию объектов на основной сцене
                    //получается рекурсия, работа которой до конца не понятна
                    for (int i = 0; i < sectionPane.getChildren().size(); i++) {
                        actionProcessingMainMap(i);
                    }
                }
            });
        }

        //при нажатии на текстовый блок
        if (currentPanelIndex == 1) {

            TextPaneMap tpm = (TextPaneMap)sectionPane.getChildren().get(currentPanelIndex);

            //перемещение письма
            tpm.setOnMousePressed(mouseEvent -> {
                tpm.setCursor(Cursor.MOVE);

                tpm.getRelocCoord().setXBegin(mouseEvent.getSceneX());
                tpm.getRelocCoord().setYBegin(mouseEvent.getSceneY());
            });

            tpm.setOnMouseDragged(mouseEvent -> {

                tpm.getRelocCoord().setXDelta(mouseEvent.getSceneX() - tpm.getRelocCoord().getXBegin());
                tpm.setTranslateX(tpm.getRelocCoord().getXDelta());

                tpm.getRelocCoord().setYDelta(mouseEvent.getSceneY() - tpm.getRelocCoord().getYBegin());
                tpm.setTranslateY(tpm.getRelocCoord().getYDelta());

                tpm.setStyle(
                        "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);"
                );
            });

            tpm.setOnMouseReleased(mouseEvent -> {

                if (Math.abs(tpm.getRelocCoord().getXDelta()) +
                        Math.abs(tpm.getRelocCoord().getYDelta()) > 2d) {
                    isDragAndDropMapText = true;
                }

                tpm.setLayoutX(tpm.getLayoutX() + tpm.getTranslateX());
                tpm.setLayoutY(tpm.getLayoutY() + tpm.getTranslateY());
                tpm.setTranslateX(0);
                tpm.setTranslateY(0);

                locationWithinSectionPanel(tpm);

                tpm.setStyle(
                        "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);"
                );
                tpm.clearRelocCoord();
            });

            //действия при клике мышкой
            tpm.setOnMouseClicked(event -> {

                if (!isDragAndDropMapText) {

                    //нажатие левой кнопки приводит к листанию текста вперед
                    if (event.getButton() == MouseButton.PRIMARY) {
                        tpm.setNextTextBlock();
                    }

                    //нажатие правой кнопки приводит к листанию текста назад
                    if (event.getButton() == MouseButton.SECONDARY) {
                        tpm.setPrevTextBlock();
                    }

                    //Двойной щелчок левой кнопкой приводит к переносу текста на последнюю страницу
                    if (event.getClickCount() == 2) {
                        tpm.setLastTextBlock();
                    }

                    //Двойной щелчок правой кнопкой приводит к возвращению текста на первую страницу
                    if (event.getButton() == MouseButton.SECONDARY && event.getClickCount() == 2) {
                        tpm.setFirstTextBlock();
                    }
                }

                isDragAndDropMapText = false;
            });
        }
    }

    //ограничение перемещения текстового блока в пределах SectionPane
    public static void locationWithinSectionPanel(final TextPaneMap currentPanel) {

        if (currentPanel.getLayoutX() < 0) {
            currentPanel.setLayoutX(2);
        }

        if (currentPanel.getLayoutY() < 0) {
            currentPanel.setLayoutY(2);
        }
    }

}

