package table.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static table.Main.*;
import static table.model.FileProcessing.*;
import static table.model.InitialLocation.initialPositionElements;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;


public class Mail {

    static final SectionKey mailKey = SectionKey.MAIL;

    static Boolean isDragAndDrop = false;

    public static void setMailsScene() {

        //лист директорий, в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileMailDirs = new ArrayList<>(getDir(mailKey));

        //файлы для отображения на основной сцене
        // Внешний ArrayList - отдельное письмо
        // Внутренний ArrayListIndex - страницы письма
        ArrayList<ArrayListIndex<File>> mailFiles = new ArrayList<>(getFiles(fileMailDirs, FileFormat.IMAGE));

        //инициализация первоначального состояния раздела Mail
        sectionPanel.getChildren().clear();

        for (int i = 0; i < mailFiles.size(); i++) {
            sectionPanel.getChildren().add(new ImagePaneSection(mailFiles.get(i), SECTION_MAIL_WIDTH_MAX, 0));
        }

        //первоначальное расположение писем на основной сцене
        initialPositionElements(sectionPanel.getChildren(), SECTION_MAIL_WIDTH_MAX + SECTION_MAIL_WIDTH_SPACING_MIN);

        //обработка действий с письмами
        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {
            actionProcessing((ImagePaneSection)sectionPanel.getChildren().get(i));
        }

    }





    //Метод обработки действий пользователя
    // (сейчас реализовано перетаскивание и перелистывание страниц письма с помощью мыши)
    public static void actionProcessing(final ImagePaneSection currentPanel) {

        //перемещение письма
        currentPanel.setOnMousePressed(mouseEvent -> {
            currentPanel.toFront();
            currentPanel.setCursor(Cursor.MOVE);

            currentPanel.getRelocationCoordinates().setXBegin(mouseEvent.getSceneX());
            currentPanel.getRelocationCoordinates().setYBegin(mouseEvent.getSceneY());
        });

        currentPanel.setOnMouseDragged(mouseEvent -> {

            System.out.println("");
            System.out.println("getLayoutX = " + currentPanel.getLayoutX() + "; getLayoutY = " + currentPanel.getLayoutY());
            System.out.println("getTranslateX = " + currentPanel.getTranslateX() + "; getTranslateY = " + currentPanel.getTranslateY());

                currentPanel.getRelocationCoordinates().setXDelta(mouseEvent.getSceneX() - currentPanel.getRelocationCoordinates().getXBegin());
                currentPanel.setTranslateX(currentPanel.getRelocationCoordinates().getXDelta());

                currentPanel.getRelocationCoordinates().setYDelta(mouseEvent.getSceneY() - currentPanel.getRelocationCoordinates().getYBegin());
                currentPanel.setTranslateY(currentPanel.getRelocationCoordinates().getYDelta());

            currentPanel.setStyle(
                    "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);"
            );
        });

        currentPanel.setOnMouseReleased(mouseEvent -> {

            if (Math.abs(currentPanel.getRelocationCoordinates().getXDelta()) +
                    Math.abs(currentPanel.getRelocationCoordinates().getYDelta()) > 2d) {
                isDragAndDrop = true;
            }

            currentPanel.setLayoutX(currentPanel.getLayoutX() + currentPanel.getTranslateX());
            currentPanel.setLayoutY(currentPanel.getLayoutY() + currentPanel.getTranslateY());
            currentPanel.setTranslateX(0);
            currentPanel.setTranslateY(0);

            locationWithinSectionPanel(currentPanel);

            currentPanel.setStyle(
                    "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);"
            );
            currentPanel.clearTwoPoint();
        });


        //перелистывание страниц письма
        currentPanel.setOnMouseClicked(event -> {

            if (!isDragAndDrop) {

                //нажатие левой кнопки приводит к листанию страниц письма вперед
                if (event.getButton() == MouseButton.PRIMARY) {
                    currentPanel.setNextImageBackground();
                }

                //нажатие правой кнопки приводит к листанию страниц письма назад
                if (event.getButton() == MouseButton.SECONDARY) {
                    currentPanel.setPrevImageBackground();
                }

                //Двойной щелчок приводит к возвращению письма на первую страницу
                if (event.getClickCount() == 2) {
                    currentPanel.setFirstImageBackground();
                }

                locationWithinSectionPanel(currentPanel);
            }

            isDragAndDrop = false;
        });
    }

    public static void locationWithinSectionPanel(final ImagePaneSection currentPanel) {

        if (currentPanel.getLayoutX() < 0) {
            currentPanel.setLayoutX(2);
        }

        if (currentPanel.getLayoutY() < 0) {
            currentPanel.setLayoutY(2);
        }

        if (currentPanel.getLayoutX() + currentPanel.getPrefWidth() > TABLE_CENTER_SECTION_WIDTH) {
            currentPanel.setLayoutX(TABLE_CENTER_SECTION_WIDTH - currentPanel.getPrefWidth() - 2);
        }

        if (currentPanel.getLayoutY() + currentPanel.getPrefHeight() > TABLE_CENTER_SECTION_HEIGHT) {
            currentPanel.setLayoutY(TABLE_CENTER_SECTION_HEIGHT - currentPanel.getPrefHeight() - 2);
        }
    }

}
