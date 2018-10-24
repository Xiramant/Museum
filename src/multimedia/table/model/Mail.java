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

//        sectionPanel.setMargin(sectionPanel, new Insets(50, 50, 50, 50));


        //инициализация первоначального состояния раздела Mail
        sectionPanel.getChildren().clear();

        for (int i = 0; i < mailFiles.size(); i++) {
            sectionPanel.getChildren().add(new ImagePane(mailFiles.get(i)));
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


        //обработка действий с письмами
        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            TwoPoint point = new TwoPoint();
            int childrenBegin = i;
            int childrenCurrent = sectionPanel.getChildren().size() - 1;

            //перемещение письма
            sectionPanel.getChildren().get(childrenBegin).setOnMousePressed(mouseEvent -> {
                System.out.println("перед перетаскиванием");
//                System.out.println(Arrays.deepToString(mailFiles.toArray()));
                sectionPanel.getChildren().get(childrenBegin).toFront();

                ((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).getTwoPoint().setXBegin(sectionPanel.getChildren().get(childrenCurrent).getTranslateX() - mouseEvent.getSceneX());
                ((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).getTwoPoint().setYBegin(sectionPanel.getChildren().get(childrenCurrent).getTranslateY() - mouseEvent.getSceneY());
                sectionPanel.getChildren().get(childrenCurrent).setCursor(Cursor.MOVE);
            });

            sectionPanel.getChildren().get(childrenBegin).setOnMouseDragged(mouseEvent -> {

                ((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).getTwoPoint().setXCurrent(((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).getTwoPoint().getXBegin() + mouseEvent.getSceneX());
                ((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).getTwoPoint().setYCurrent(((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).getTwoPoint().getYBegin() + mouseEvent.getSceneY());
                sectionPanel.getChildren().get(childrenCurrent).setTranslateX(((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).getTwoPoint().getXCurrent());
                sectionPanel.getChildren().get(childrenCurrent).setTranslateY(((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).getTwoPoint().getYCurrent());
                sectionPanel.getChildren().get(childrenCurrent).setStyle(
                        "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);"
                );
            });

            sectionPanel.getChildren().get(childrenBegin).setOnMouseReleased(mouseEvent -> {
                if (Math.abs(((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).getTwoPoint().getXCurrent()) +
                        Math.abs(((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).getTwoPoint().getYCurrent()) > 2d) {
                    isDragAndDrop = true;
                }
                sectionPanel.getChildren().get(childrenCurrent).setStyle(
                        "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);"
                );
                ((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).clearTwoPoint();
            });


            //перелистывание страниц письма
            sectionPanel.getChildren().get(childrenBegin).setOnMouseClicked(event -> {

                if (!isDragAndDrop) {

//                    sectionPanel.getChildren().get(childrenBegin).toFront();
//                    mailFiles.add(mailFiles.get(childrenBegin));
//                    mailFiles.remove(childrenBegin);

                    //нажатие левой кнопки приводит к листанию страниц письма вперед
                    if (event.getButton() == MouseButton.PRIMARY) {
                        ((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).setNextImageBackground();
                    }

                    //нажатие правой кнопки приводит к листанию страниц письма назад
                    if (event.getButton() == MouseButton.SECONDARY) {
                        ((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).setPrevImageBackground();
                    }

//                    //Двойной щелчок приводит к возвращению письма на первую страницу
//                    if (event.getClickCount() == 2) {
//                        ((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).setImageBackground(((ImagePane) sectionPanel.getChildren().get(childrenCurrent)).getImageFiles().getFirstElement());
//                    }
                }

                isDragAndDrop = false;
            });
        }

    }


}
