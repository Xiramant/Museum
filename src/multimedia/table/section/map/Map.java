package table.section.map;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import table.model.*;

import java.io.File;
import java.util.ArrayList;

import static table.Main.*;
import static table.model.FileProcessing.*;
import static table.model.InitialLocation.initialPositionElements;
import static table.model.TextProcessing.*;

public class Map {

    //Файл изображения фона для текстового блока
    public static final File TEXT_BACKGROUND_FILE = new File(RESOURCES_PATH + "map/text_case_0_1.png");

    //Ключевое слово раздела Map
    static final SectionKey mapKey = SectionKey.MAP;

    //Карты:
    //внешний лист - лист сражений;
    //внутренний лист - карты для сражения.
    static ArrayList<ArrayList<File>> mapImageFiles;

    //Текстовые файлы:
    //внешний лист - лист сражений;
    //внутренний лист - текстовые файлы для сражения.
    static ArrayList<ArrayList<File>> mapTextFiles;

    //лист стрингов, в которые преобразуется содержание текстовых файлов
    static ArrayListIndex<String> mapTextString = new ArrayListIndex<>();

    //минимальное расстояние по горизонтали между блоками изначальной сцены
    private static final double MIN_WIDTH_SPACING = 30;

    //метод установки элементов раздела Map в sectionPane
    public static void setMapScene() {

        //лист директорий, в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileMapDirs = new ArrayList<>(getDir(mapKey));

        mapImageFiles = new ArrayList<>(getFiles(fileMapDirs, FileFormat.IMAGE));
        mapTextFiles = new ArrayList<>(getFiles(fileMapDirs, FileFormat.TEXT));

        //Список названий сражений
        ArrayList<String> operationNameList = new ArrayList<>();

        for (int i = 0; i < mapTextFiles.size(); i++) {
            System.out.println("i = " + i + "; file = " + mapTextFiles.get(i).get(0));
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

        System.out.println("переход к основной сцене");

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

                if (currentBackgroundIndex != prevBackgroundIndex) {
                    TextPaneMap tpmNew = new TextPaneMap(mapTextString.get(ips.getCurrentBackgroundIndex()));
                    tpmNew.setLayoutX(TABLE_CENTER_SECTION_WIDTH - tpmNew.getPrefWidth());
                    tpmNew.setLayoutY(TABLE_CENTER_SECTION_HEIGHT - tpmNew.getPrefHeight());
                    sectionPane.getChildren().set(1, tpmNew);

                    for (int i = 0; i < sectionPane.getChildren().size(); i++) {
                        actionProcessingMainMap(i);
                    }
                }
            });
        }

        if (currentPanelIndex == 1) {

            TextPaneMap tpm = (TextPaneMap)sectionPane.getChildren().get(currentPanelIndex);

            tpm.setOnMouseClicked(event -> {

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
            });
        }
    }

}

