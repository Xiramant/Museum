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

        initialPositionElements(sectionPane.getChildren(), ((Pane) sectionPane.getChildren().get(0)).getPrefWidth());

        //Обработка действий при нажатии на панель с названием операции
        for (int i = 0; i < sectionPane.getChildren().size(); i++) {
            actionProcessingInitialMap(i);
        }
    }

    //Метод обработки действий пользователя на начальной сцене
    // (сейчас реализовано перетаскивание и перелистывание страниц письма с помощью мыши)
    public static void actionProcessingInitialMap(final int currentPanelIndex) {

        ImagePane currentPanel = (ImagePane) sectionPane.getChildren().get(currentPanelIndex);

        //действия по клику мышкой
        currentPanel.setOnMouseClicked(event -> {

            sectionPane.getChildren().clear();

            ArrayList<File> imageList = new ArrayList<>(mapImageFiles.get(currentPanelIndex));
            ArrayList<File> textList = new ArrayList<>(mapTextFiles.get(currentPanelIndex));

            //лист стрингов, в которые преобразуется содержание текстовых файлов
            ArrayListIndex<String> mapTextString = new ArrayListIndex<>();

            for (int i = 0; i < textList.size(); i++) {
                mapTextString.add(readingFileIntoString(textList.get(i)));
            }

            ImagePaneSection maps = new ImagePaneSection(imageList);
            maps.setLayoutX((TABLE_CENTER_SECTION_WIDTH - maps.getPrefWidth()) / 2);
            maps.setLayoutY((TABLE_CENTER_SECTION_HEIGHT - maps.getPrefHeight()) / 2);

            TextPaneMap text = new TextPaneMap(mapTextString.get(maps.getBackgroundIndex()));
            text.setLayoutX(TABLE_CENTER_SECTION_WIDTH - text.getPrefWidth());
            text.setLayoutY(TABLE_CENTER_SECTION_HEIGHT - text.getPrefHeight());

            sectionPane.getChildren().addAll(maps, text);

            for (int i = 0; i < sectionPane.getChildren().size(); i++) {
                actionProcessingMainMap(i);
            }
        });


    }

    //Метод обработки действий пользователя на основной сцене
    // (сейчас реализовано перетаскивание и перелистывание страниц письма с помощью мыши)
    public static void actionProcessingMainMap(final int currentPanelIndex) {

        System.out.println("переход к основной сцене");

        if (currentPanelIndex == 0) {
            

        }

        if (currentPanelIndex == 1) {

            TextPaneMap tpm = (TextPaneMap)sectionPane.getChildren().get(currentPanelIndex);

            tpm.setOnMouseClicked(event -> {

                //нажатие левой кнопки приводит к листанию текста вперед
                if (event.getButton() == MouseButton.PRIMARY) {
                    tpm.setNextTextBlock();
                    System.out.println("щелчок левой");
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

