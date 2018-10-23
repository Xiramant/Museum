package table.model;

import javafx.event.EventHandler;

import java.io.File;
import java.util.ArrayList;

import static table.Main.RESOURCES_PATH;
import static table.Main.sectionPanel;
import static table.model.FileProcessing.*;

import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Mail {

    static final SectionKey mailKey = SectionKey.MAIL;

    static final String mailPath = RESOURCES_PATH + mailKey.getKeyWord() + "/";

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


//        //обработка событий нажатия на кнопку мыши
//        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {
//
//            int children = i;
//
//            EventHandler<MouseEvent> mouseEventHandler = event -> {
//
//                if (event.getButton() == MouseButton.PRIMARY &&
//                        mailIndex.get(children) < mailFiles.get(children).size() - 1) {
//                    mailIndex.set(children, mailIndex.get(children) + 1);
//                }
//
//                if (event.getButton() == MouseButton.SECONDARY &&
//                        mailIndex.get(children) > 0) {
//                    mailIndex.set(children, mailIndex.get(children) - 1);
//                }
//
//                if (event.getClickCount() == 2) {
//                        mailIndex.set(children, 0);
//                }
//
//                ((ImagePane) sectionPanel.getChildren().get(children)).setImageBackground(mailFiles.get(children).get(mailIndex.get(children)));
//            };
//
//            sectionPanel.getChildren().get(children).addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandler);
//        }

        //обработка событий нажатия на кнопку мыши
        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            int children = i;

            EventHandler<MouseEvent> mouseEventHandler = event -> {

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
            };

            sectionPanel.getChildren().get(children).setOnMouseClicked(mouseEventHandler);
        }





        sectionPanel.getChildren().get(0).setOnMousePressed( mouseEvent -> {
            Drag.xDragMeasure = mouseEvent.getScreenX() - Drag.xPosition;
            Drag.yDragMeasure = mouseEvent.getScreenY() - Drag.yPosition;
            sectionPanel.getChildren().get(0).setCursor(Cursor.MOVE);
        });

        sectionPanel.getChildren().get(0).setOnMouseDragged( mouseEvent -> {
            Drag.xPosition = mouseEvent.getScreenX() - Drag.xDragMeasure;
            Drag.yPosition = mouseEvent.getScreenY() - Drag.yDragMeasure;
            sectionPanel.getChildren().get(0).setTranslateX(Drag.xPosition);
            sectionPanel.getChildren().get(0).setTranslateY(Drag.yPosition);

        });

        sectionPanel.getChildren().get(0).setOnMouseReleased( mouseEvent -> {
            sectionPanel.getChildren().get(0).setCursor(Cursor.HAND);
        });

//        sectionPanel.getChildren().get(0).setOnMouseEntered( mouseEvent -> {
//            sectionPanel.getChildren().get(0).setCursor(Cursor.HAND);
//        });




//        sectionPanel.getChildren().get(0).setOnDragDetected(event -> {
//
//            Dragboard db = sectionPanel.startDragAndDrop(TransferMode.MOVE);
//
//            // Visual during drag
//            SnapshotParameters snapshotParameters = new SnapshotParameters();
//            snapshotParameters.setFill(Color.TRANSPARENT);
//            db.setDragView(sectionPanel.snapshot(snapshotParameters, null));
//
//            event.consume();
//        });



        EventHandler<MouseEvent> mousePressedEventHandler = event -> {


        };


        sectionPanel.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedEventHandler);




    }


}
