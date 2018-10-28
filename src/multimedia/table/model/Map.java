package table.model;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;

import static table.Main.*;
import static table.model.FileProcessing.*;
import static table.model.InitialLocation.initialPositionElements;
import static table.model.TextProcessing.*;

public class Map {

    static final SectionKey mapKey = SectionKey.MAP;

    static ArrayList<ArrayListIndex<File>> mapImageFiles;

    static ArrayList<ArrayListIndex<File>> mapTextFiles;

    public static void setMapScene() {

        //лист директорий, в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileMapDirs = new ArrayList<>(getDir(mapKey));

//        //файлы для отображения на основной сцене
//        // Внешний ArrayList - отдельное сражение
//        // Внутренний ArrayListIndex - карты сражения
       mapImageFiles = new ArrayList<>(getFiles(fileMapDirs, FileFormat.IMAGE));

        //файлы для отображения на основной сцене
        // Внешний ArrayList - отдельное сражение
        // Внутренний ArrayListIndex - карты сражения
        mapTextFiles = new ArrayList<>(getFiles(fileMapDirs, FileFormat.TEXT));

        for (int i = 0; i < mapTextFiles.size(); i++) {
            for (int j = 0; j < mapTextFiles.get(i).size(); j++) {

                System.out.println("textFile = " + mapTextFiles.get(i).get(j));
            }
        }

        ArrayList<String> operationNameList = new ArrayList<>();

        for (int i = 0; i < mapTextFiles.size(); i++) {
            System.out.println("i = " + i + "; file = " + mapTextFiles.get(i).get(0));
            operationNameList.add(readingFirstStokeFromFile(mapTextFiles.get(i).get(0)));
        }

        //Инициализация первоначального состояния раздела Карты
        // с расположением списка операций
        sectionPanel.getChildren().clear();

        for (int i = 0; i < operationNameList.size(); i++) {
            sectionPanel.getChildren().add(new InitialMapPane(operationNameList.get(i)));
        }

        initialPositionElements(sectionPanel.getChildren(), ((Pane) sectionPanel.getChildren().get(0)).getPrefWidth());

        //обработка действий с письмами
        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {
            actionProcessing(i);
        }

    }

    //Метод обработки действий пользователя
    // (сейчас реализовано перетаскивание и перелистывание страниц письма с помощью мыши)
    public static void actionProcessing(final int currentPanelIndex) {

        ImagePane currentPanel = (ImagePane)sectionPanel.getChildren().get(currentPanelIndex);

        //действия по клику мышкой
        currentPanel.setOnMouseClicked(event -> {

            sectionPanel.getChildren().clear();

            ArrayListIndex<File> imageList = new ArrayListIndex<>();
            imageList = mapImageFiles.get(currentPanelIndex);

            ArrayListIndex<File> textList = new ArrayListIndex<>();
            textList = mapTextFiles.get(currentPanelIndex);

            //лист стрингов, в которые преобразуется содержание текстовых файлов
            ArrayListIndex<String> mapTextString = new ArrayListIndex<>();

            for (int i = 0; i < textList.size(); i++) {
                mapTextString.add(readingFileIntoString(textList.get(i)));
            }

            ImagePaneSection maps = new ImagePaneSection(imageList);
            maps.setLayoutX((TABLE_CENTER_SECTION_WIDTH - maps.getPrefWidth()) / 2);
            maps.setLayoutY((TABLE_CENTER_SECTION_HEIGHT - maps.getPrefHeight()) / 2);

            TextPane text = new TextPane(new File(RESOURCES_PATH + "map/text_case_0_1.png"), mapTextString.get(maps.getBackgroundIndex()));
            text.setLayoutX(TABLE_CENTER_SECTION_WIDTH - text.getPrefWidth());
            text.setLayoutY(TABLE_CENTER_SECTION_HEIGHT - text.getPrefHeight());

            sectionPanel.getChildren().addAll(maps, text);

        });
    }

}

