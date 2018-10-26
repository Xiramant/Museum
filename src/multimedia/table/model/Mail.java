package table.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static table.Main.*;
import static table.model.FileProcessing.*;

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
        initialPositionElements(sectionPanel.getChildren());

        //обработка действий с письмами
        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {
            actionProcessing((ImagePaneSection)sectionPanel.getChildren().get(i));
        }

    }

    //Метод первоначального расположения писем в разделе в шахматном порядке
    //Элементы в строке располагаются с равным расстоянием между элементами
    //Вертикальное выравнивание в строке - по низу элементов
    public static void initialPositionElements(final List<Node> elements) {

        //Определение максимального количества элементов в первой строке
        int maxElementsInFirstRow = (int) (TABLE_CENTER_SECTION_WIDTH / (SECTION_MAIL_WIDTH_MAX + SECTION_MAIL_WIDTH_SPACING_MIN));

        //Лист элементов с разбитием по рядам
        //Внешний список - список рядов
        //Внутренний список - элементы в ряду
        ArrayList<ArrayList<Pane>> groupElements = new ArrayList<>();

        //Временный список группы элементов в ряду
        ArrayList<Pane> groupElementsInRow = new ArrayList<>();

        //Максимальное количество элементов в ряду
        //Для табличной формы расположения элементов
        // оно бы соответствовало maxElementsInFirstRow
        //Для шахматного расположения элементов
        // в нечетном ряду количество элементов должно быть на 1 меньше,
        // чем в четном (или наоборот)
        int maxElementsInRow;

        //Номер ряда
        int rowNumber = 0;

        //Текущий элемент в ряде
        int currentElementInRow = 0;

        //расположение элементов в groupElements
        for (int i = 0; i < elements.size(); i++) {

            maxElementsInRow = (rowNumber % 2 == 0)? maxElementsInFirstRow: maxElementsInFirstRow - 1;

            if (currentElementInRow == maxElementsInRow) {
                //Т.к. объекты передаются по ссылкам, то
                // для того, чтобы внешний лист groupElements
                // не ссылался все разы только на последнюю версию groupElementsInRow
                // нужно создавать новый внешний лист,
                // в конструктор которого передается ссылка на groupElementsInRow
                groupElements.add(new ArrayList<>(groupElementsInRow));
                groupElementsInRow.clear();
                currentElementInRow = 0;
                rowNumber++;
            }

            groupElementsInRow.add((Pane) elements.get(i));
            currentElementInRow++;
        }

        //Добавление последнего ряда в groupElements,
        // т.к. цикл не доходит до условия в котором он добавляется
        groupElements.add(groupElementsInRow);

        //Лист сумм длин всех элементов в одном ряду
        ArrayList<Double> sumWidthInRow = new ArrayList<>();

        //Лист максимальных высот элементов в одном ряду
        ArrayList<Double> maxHeightInRow = new ArrayList<>();

        for (ArrayList<Pane> elementsInRow: groupElements) {

            double sumWidth = 0;
            double maxHeight = 0;

            for (Pane pane: elementsInRow) {

                sumWidth += pane.getPrefWidth();

                if (maxHeight < pane.getPrefHeight()) {
                    maxHeight = pane.getPrefHeight();
                }
            }

            sumWidthInRow.add(sumWidth);
            maxHeightInRow.add(maxHeight);
        }

        //Сумма максимальных высот элементов в ряду
        int sumMaxHeightInRow = 0;

        for (double maxHeight: maxHeightInRow) {
            sumMaxHeightInRow += maxHeight;
        }

        //Лист горизонтальных отступов между элементами в каждом ряду
        ArrayList<Double> xSpacingInRow = new ArrayList<>();

        //Вертикальный отступ между рядами элементов
        double ySpacing = 0;

        for (int i = 0; i < groupElements.size(); i++) {
            xSpacingInRow.add((TABLE_CENTER_SECTION_WIDTH - sumWidthInRow.get(i)) / (groupElements.get(i).size() + 1));
        }

        for (int i = 0; i < groupElements.size(); i++) {
            ySpacing = (TABLE_CENTER_SECTION_HEIGHT - sumMaxHeightInRow) / (groupElements.size() + 1);
        }

        //распределение элементов по горизонтали
        for (int i = 0; i < groupElements.size(); i++) {

            double leftSpace = 0;

            for (Pane pane: groupElements.get(i)) {
                leftSpace += xSpacingInRow.get(i);
                pane.setLayoutX(leftSpace);
                leftSpace += pane.getPrefWidth();
            }
        }

        //распределение элементов по вертикали
        double topSpace = 0;

        for (int i = 0; i < groupElements.size(); i++) {

            topSpace += ySpacing + maxHeightInRow.get(i);

            for (Pane pane: groupElements.get(i)) {
                pane.setLayoutY(topSpace - pane.getPrefHeight());
            }
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

            System.out.println("");
            System.out.println("getLayoutX = " + currentPanel.getLayoutX() + "; getLayoutY = " + currentPanel.getLayoutY());
            System.out.println("getTranslateX = " + currentPanel.getTranslateX() + "; getTranslateY = " + currentPanel.getTranslateY());

            if ((currentPanel.getLayoutX() + currentPanel.getTranslateX()) >= 0 &&
                    (currentPanel.getLayoutX() +  currentPanel.getTranslateX() + currentPanel.getPrefWidth() <= sectionPanel.getPrefWidth())) {
                currentPanel.getRelocationCoordinates().setXCurrent(currentPanel.getRelocationCoordinates().getXBegin() + mouseEvent.getSceneX());
                currentPanel.setTranslateX(currentPanel.getRelocationCoordinates().getXCurrent());

            }

            if ((currentPanel.getLayoutY() + currentPanel.getTranslateY()) >= 0 &&
                    (currentPanel.getLayoutY() + + currentPanel.getTranslateY() + currentPanel.getPrefHeight() <= sectionPanel.getPrefHeight())) {
                currentPanel.getRelocationCoordinates().setYCurrent(currentPanel.getRelocationCoordinates().getYBegin() + mouseEvent.getSceneY());
                currentPanel.setTranslateY(currentPanel.getRelocationCoordinates().getYCurrent());
            }


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
