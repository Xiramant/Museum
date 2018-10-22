package table.model;

import javafx.event.EventHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import static table.Main.RESOURCES_PATH;
import static table.Main.sectionPanel;
import static table.model.FileProcessing.*;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Mail {

    static final SectionKey mailKey = SectionKey.MAIL;

    static final String mailPath = RESOURCES_PATH + mailKey.getKeyWord() + "/";

    public static void setMailsScene() {

        ArrayList<File> fileMailDirs = new ArrayList<>(getDir(mailKey));

        ArrayList<LinkedList<File>> mailsFiles = new ArrayList<>(getFiles(fileMailDirs, FileFormat.IMAGE));

//        sectionPanel.setMargin(sectionPanel, new Insets(50, 50, 50, 50));

        ArrayList<ListIterator<File>> mailListIterators = new ArrayList<>();

        for (LinkedList<File> mails: mailsFiles) {
            mailListIterators.add(mails.listIterator());
        }

        sectionPanel.getChildren().clear();
        for (ListIterator<File> mailsIterator: mailListIterators) {

//            System.out.println(mailsIterator.next());

            sectionPanel.getChildren().add(new ImagePane(mailsIterator.next()));
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


        for (int i = 0; i < sectionPanel.getChildren().size(); i++) {

            int children = i;

            EventHandler<MouseEvent> mouseEventHandler = event -> {

                if (event.getButton() == MouseButton.PRIMARY && mailListIterators.get(children).hasNext()) {
                    ((ImagePane) sectionPanel.getChildren().get(children)).setImageBackground(mailListIterators.get(children).next());
                }

                if (event.getButton() == MouseButton.SECONDARY && mailListIterators.get(children).hasPrevious()) {
                    ((ImagePane) sectionPanel.getChildren().get(children)).setImageBackground(mailListIterators.get(children).previous());
                }
            };

            sectionPanel.getChildren().get(children).addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandler);
        }
    }



}
