package table.model;

import javafx.event.EventHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static table.Main.RESOURCES_PATH;
import static table.Main.sectionPanel;
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
            sectionPanel.getChildren().add(new ImagePaneSection(mailFiles.get(i)));
        }

        //расположение писем на основной сцене
//        sectionPanel.setMargin(sectionPanel, new Insets(50, 50, 50, 50));

        //по ширине
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

            currentPanel.getTwoPoint().setXBegin(currentPanel.getTranslateX() - mouseEvent.getSceneX());
            currentPanel.getTwoPoint().setYBegin(currentPanel.getTranslateY() - mouseEvent.getSceneY());
        });

        currentPanel.setOnMouseDragged(mouseEvent -> {

            currentPanel.getTwoPoint().setXCurrent(currentPanel.getTwoPoint().getXBegin() + mouseEvent.getSceneX());
            currentPanel.getTwoPoint().setYCurrent(currentPanel.getTwoPoint().getYBegin() + mouseEvent.getSceneY());
            currentPanel.setTranslateX(currentPanel.getTwoPoint().getXCurrent());
            currentPanel.setTranslateY(currentPanel.getTwoPoint().getYCurrent());
            currentPanel.setStyle(
                    "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);"
            );
        });

        currentPanel.setOnMouseReleased(mouseEvent -> {

            if (Math.abs(currentPanel.getTwoPoint().getXCurrent()) +
                    Math.abs(currentPanel.getTwoPoint().getYCurrent()) > 2d) {
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
