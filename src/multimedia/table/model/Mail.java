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

    static final String mailPath = RESOURCES_PATH + mailKey.getKeyWord() + "/";

    static ArrayList<ArrayList<Double>> pointList = new ArrayList<>();

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
            sectionPanel.getChildren().add(new ImagePane(mailFiles.get(i).get(mailIndex.get(i))));
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

            int children = i;

            EventHandler<MouseEvent> mouseEventHandler = event -> {

                if (!isDragAndDrop) {

                    if (event.getButton() == MouseButton.PRIMARY &&
                            mailIndex.get(children) < mailFiles.get(children).size() - 1) {
                        mailIndex.set(children, mailIndex.get(children) + 1);
                    }

                    if (event.getButton() == MouseButton.SECONDARY &&
                            mailIndex.get(children) > 0) {
                        mailIndex.set(children, mailIndex.get(children) - 1);
                    }

                    if (event.getClickCount() == 2) {
                        mailIndex.set(children, 0);
                    }

                    ((ImagePane) sectionPanel.getChildren().get(children)).setImageBackground(mailFiles.get(children).get(mailIndex.get(children)));

                }

                isDragAndDrop = false;
            };

            sectionPanel.getChildren().get(children).setOnMouseClicked(mouseEventHandler);
        }



        for (int i = 0; i < mailFiles.size(); i++) {

            ArrayList<Double> point = new ArrayList<>();

            for (int j = 0; j < 4; j++) {
                point.add(0d);
            }

            pointList.add(point);
        }


        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            int children = i;

            sectionPanel.getChildren().get(children).setOnMousePressed(mouseEvent -> {
                pointList.get(children).set(0, sectionPanel.getChildren().get(children).getTranslateX() - mouseEvent.getSceneX());
                pointList.get(children).set(1, sectionPanel.getChildren().get(children).getTranslateY() - mouseEvent.getSceneY());
                sectionPanel.getChildren().get(children).setCursor(Cursor.MOVE);
            });
        }

        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            int children = i;

            sectionPanel.getChildren().get(children).setOnMouseDragged(mouseEvent -> {
                pointList.get(children).set(2, pointList.get(children).get(0) + mouseEvent.getSceneX());
                pointList.get(children).set(3, pointList.get(children).get(1) + mouseEvent.getSceneY());
                sectionPanel.getChildren().get(children).setTranslateX(pointList.get(children).get(2));
                sectionPanel.getChildren().get(children).setTranslateY(pointList.get(children).get(3));
                sectionPanel.getChildren().get(children).setStyle(
                        "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);"
                );
            });
        }

        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            int children = i;

            sectionPanel.getChildren().get(children).setOnMouseReleased(mouseEvent -> {
                if (Math.abs(pointList.get(children).get(2)) + Math.abs(pointList.get(children).get(3)) > 2d) {
                    isDragAndDrop = true;
                }
                sectionPanel.getChildren().get(children).setStyle(
                        "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);"
                );
                pointList.get(children).set(0, 0d);
                pointList.get(children).set(1, 0d);
                pointList.get(children).set(2, 0d);
                pointList.get(children).set(3, 0d);
            });
        }

    }


}
