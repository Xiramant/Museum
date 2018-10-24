package table.model;

import javafx.event.EventHandler;

import java.io.File;
import java.util.ArrayList;

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
        // Внутренний ArrayList - страницы письма
        ArrayList<ArrayList<File>> mailFiles = new ArrayList<>(getFiles(fileMailDirs, FileFormat.IMAGE));

//        sectionPanel.setMargin(sectionPanel, new Insets(50, 50, 50, 50));

        //Лист указателей на текущую страницу письма,
        // отображаемую на основной сцене
        // ListIterator для данных целей не подошел,
        // т.к. при последовательности: next, previous, next, previous - отображается одна и таже страница
        ArrayList<Integer> mailIndex = new ArrayList<>();

        for (int i = 0; i < mailFiles.size(); i++) {
            mailIndex.add(0);
        }

        sectionPanel.getChildren().clear();

        for (int i = 0; i < mailFiles.size(); i++) {
            sectionPanel.getChildren().add(new ImagePane(mailFiles.get(i).get(0)));
        }


        //расположение писем на основной сцене
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


        //обработка событий нажатия на кнопку мыши
        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            int childrenCurrent = i;

            EventHandler<MouseEvent> mouseEventHandler = event -> {

                if (!isDragAndDrop) {

                    if (event.getButton() == MouseButton.PRIMARY &&
                            mailIndex.get(childrenCurrent) < mailFiles.get(childrenCurrent).size() - 1) {
                        mailIndex.set(childrenCurrent, mailIndex.get(childrenCurrent) + 1);
                    }

                    if (event.getButton() == MouseButton.SECONDARY &&
                            mailIndex.get(childrenCurrent) > 0) {
                        mailIndex.set(childrenCurrent, mailIndex.get(childrenCurrent) - 1);
                    }

                    if (event.getClickCount() == 2) {
                        mailIndex.set(childrenCurrent, 0);
                    }

                    ((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).setImageBackground(mailFiles.get(childrenCurrent).get(mailIndex.get(childrenCurrent)));
                }

                isDragAndDrop = false;
            };

            sectionPanel.getChildren().get(childrenCurrent).setOnMouseClicked(mouseEventHandler);
        }

        ArrayList<TwoPoint> pointList = new ArrayList<>();
        for (int i = 0; i < mailFiles.size(); i++) {
            pointList.add(new TwoPoint());
        }

        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            int childrenCurrent = i;

            sectionPanel.getChildren().get(childrenCurrent).setOnMousePressed(mouseEvent -> {
                pointList.get(childrenCurrent).setXBegin(sectionPanel.getChildren().get(childrenCurrent).getTranslateX() - mouseEvent.getSceneX());
                pointList.get(childrenCurrent).setYBegin(sectionPanel.getChildren().get(childrenCurrent).getTranslateY() - mouseEvent.getSceneY());
                sectionPanel.getChildren().get(childrenCurrent).setCursor(Cursor.MOVE);
            });
        }

        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            int childrenCurrent = i;

            sectionPanel.getChildren().get(childrenCurrent).setOnMouseDragged(mouseEvent -> {
                pointList.get(childrenCurrent).setXCurrent(pointList.get(childrenCurrent).getXBegin() + mouseEvent.getSceneX());
                pointList.get(childrenCurrent).setYCurrent(pointList.get(childrenCurrent).getYBegin() + mouseEvent.getSceneY());
                sectionPanel.getChildren().get(childrenCurrent).setTranslateX(pointList.get(childrenCurrent).getXCurrent());
                sectionPanel.getChildren().get(childrenCurrent).setTranslateY(pointList.get(childrenCurrent).getYCurrent());
                sectionPanel.getChildren().get(childrenCurrent).setStyle(
                        "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);"
                );
            });
        }

        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            int childrenCurrent = i;

            sectionPanel.getChildren().get(childrenCurrent).setOnMouseReleased(mouseEvent -> {
                if (Math.abs(pointList.get(childrenCurrent).getXCurrent()) + Math.abs(pointList.get(childrenCurrent).getYCurrent()) > 2d) {
                    isDragAndDrop = true;
                }
                sectionPanel.getChildren().get(childrenCurrent).setStyle(
                        "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);"
                );
                pointList.get(childrenCurrent).setXBegin(0d);
                pointList.get(childrenCurrent).setYBegin(0d);
                pointList.get(childrenCurrent).setXCurrent(0d);
                pointList.get(childrenCurrent).setYCurrent(0d);
            });
        }

    }


}
