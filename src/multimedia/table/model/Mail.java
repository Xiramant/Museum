package table.model;

import javafx.event.EventHandler;

import java.io.File;
import java.util.ArrayList;

import static table.Main.RESOURCES_PATH;
import static table.Main.sectionPanel;
import static table.model.FileProcessing.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Mail {

    static final SectionKey mailKey = SectionKey.MAIL;

    static final String mailPath = RESOURCES_PATH + mailKey.getKeyWord() + "/";

    public static void setMailsScene() {

        ArrayList<File> fileMailDirs = new ArrayList<>(getDir(mailKey));

        ArrayList<ArrayList<File>> mailsFiles = new ArrayList<>(getFiles(fileMailDirs, FileFormat.IMAGE));

//        sectionPanel.setMargin(sectionPanel, new Insets(50, 50, 50, 50));

        sectionPanel.getChildren().clear();
        for (ArrayList<File> mailsList : mailsFiles) {
            System.out.println(mailsList.get(0));

            sectionPanel.getChildren().add(new ImagePane(mailsList.get(0)));
        }
        
        

        int childsWidth = 0;

        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            childsWidth += (int) ((Pane) sectionPanel.getChildren().get(i)).getWidth();

            System.out.println("i = " + i + ", childsWidth = " + childsWidth);
        }

        System.out.println("childsWidth = " + childsWidth);

        System.out.println("sectionPanel = " + sectionPanel.getWidth());


        int marginWidth = (int)((sectionPanel.getWidth() - childsWidth) / (sectionPanel.getChildren().size() + 1));



        System.out.println("marginWidth = " + marginWidth);

        int leftSpace = marginWidth;

        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            sectionPanel.getChildren().get(i).setLayoutX(leftSpace);

            leftSpace += (int) ((Pane) sectionPanel.getChildren().get(i)).getWidth() + marginWidth;
        }





        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            int topSpace = (int) (sectionPanel.getHeight() - ((Pane) sectionPanel.getChildren().get(i)).getHeight()) / 2;

            sectionPanel.getChildren().get(i).setLayoutY(topSpace);

        }


        EventHandler<MouseEvent> mouseEventHandler = event -> {
            System.out.println("Письмо 1 нажато");
//            ((Pane)sectionPanel.getChildren().get(0)).setBackground(setImageBackground(mailsFiles.get(0).get(1)));
            ((ImagePane)sectionPanel.getChildren().get(0)).setImageBackground(mailsFiles.get(0).get(1));
        };

        sectionPanel.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandler);







    }



}
