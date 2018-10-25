package table.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import static table.Main.*;
import static table.model.FileProcessing.*;

import javafx.scene.Cursor;
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

        //расположение писем на основной сцене
        //по ширине
        //максимальное количество элементов в ряду
        int maxElementsInFirstRow = (int) (TABLE_CENTER_SECTION_WIDTH / (SECTION_MAIL_WIDTH_MAX + SECTION_MAIL_WIDTH_SPACING_MIN));

        //количество рядов
        int elementsInRow = 0;
        int rowCount = 0;
        int maxElementsInRow;
        int currentElementInRow = 0;
        ArrayList<Integer> countElementsInRow = new ArrayList<>();

        ArrayList<Integer> maxWidthInRow = new ArrayList<>();
        maxWidthInRow.add(0);
        ArrayList<Integer> maxHeightInRow = new ArrayList<>();
        maxHeightInRow.add(0);

//        ArrayList<Map<Integer, PositionInGroup>> map = new ArrayList<>();

        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            maxElementsInRow = (rowCount % 2 == 0)? maxElementsInFirstRow: maxElementsInFirstRow - 1;

            if (currentElementInRow == maxElementsInRow) {
                countElementsInRow.add(currentElementInRow);
                currentElementInRow = 0;
                rowCount++;
                maxWidthInRow.add(0);
                maxHeightInRow.add(0);
            }

            ImagePaneSection currentChildren = (ImagePaneSection) sectionPanel.getChildren().get(i);

//            currentChildren.getPositionInGroup().setNumberIndex(elementsInRow);
//            currentChildren.getPositionInGroup().setRowIndex(rowCount);

            if (maxWidthInRow.get(rowCount) < currentChildren.getPrefWidth()) {
                maxWidthInRow.set(rowCount, (int)currentChildren.getPrefWidth());
            }

            if (maxHeightInRow.get(rowCount) < currentChildren.getPrefHeight()) {
                maxHeightInRow.set(rowCount, (int)currentChildren.getPrefHeight());
            }

            elementsInRow++;
        }

        countElementsInRow.add(currentElementInRow);

        ArrayList<Integer> xSpacing = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            xSpacing.add((int) ((TABLE_CENTER_SECTION_WIDTH - (countElementsInRow.get(i) * maxWidthInRow.get(i))) / (countElementsInRow.get(i) + 1)));
        }

        ArrayList<Integer> ySpacing = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            ySpacing.add((int) ((TABLE_CENTER_SECTION_HEIGHT - (rowCount * countElementsInRow.get(i))) / (countElementsInRow.get(i) + 1)));
        }





        int childsWidth = 0;

        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {
            childsWidth += (int) ((Pane) sectionPanel.getChildren().get(i)).getWidth();
        }

        int marginWidth = (int)((sectionPanel.getWidth() - childsWidth) / (sectionPanel.getChildren().size() + 1));

        int leftSpace = marginWidth;

        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {
            sectionPanel.getChildren().get(i).setLayoutX(leftSpace);
            leftSpace += (int) ((Pane) sectionPanel.getChildren().get(i)).getWidth() + marginWidth;
        }

        //по высоте
        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {
            int topSpace = (int) (sectionPanel.getHeight() - ((Pane) sectionPanel.getChildren().get(i)).getHeight()) / 2;
            sectionPanel.getChildren().get(i).setLayoutY(topSpace);
        }


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

            currentPanel.getRelocationCoordinates().setXBegin(currentPanel.getTranslateX() - mouseEvent.getSceneX());
            currentPanel.getRelocationCoordinates().setYBegin(currentPanel.getTranslateY() - mouseEvent.getSceneY());
        });

        currentPanel.setOnMouseDragged(mouseEvent -> {

            currentPanel.getRelocationCoordinates().setXCurrent(currentPanel.getRelocationCoordinates().getXBegin() + mouseEvent.getSceneX());
            currentPanel.getRelocationCoordinates().setYCurrent(currentPanel.getRelocationCoordinates().getYBegin() + mouseEvent.getSceneY());
            currentPanel.setTranslateX(currentPanel.getRelocationCoordinates().getXCurrent());
            currentPanel.setTranslateY(currentPanel.getRelocationCoordinates().getYCurrent());
            currentPanel.setStyle(
                    "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);"
            );
        });

        currentPanel.setOnMouseReleased(mouseEvent -> {

            if (Math.abs(currentPanel.getRelocationCoordinates().getXCurrent()) +
                    Math.abs(currentPanel.getRelocationCoordinates().getYCurrent()) > 2d) {
                isDragAndDrop = true;
            }
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
            }

            isDragAndDrop = false;
        });
    }

}
