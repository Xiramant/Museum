package table4K.model.mail;


import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import table4K.model.imagePresentation.ImageViewIterationWithMove;

import java.io.File;
import java.util.ArrayList;

import static table4K.BackHome.returnHome;
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.data.RootPaneBackgroundData.getBookBackground;
import static table4K.data.mail.MailData.getMailList;
import static table4K.model.mail.MailRepresentation.getMail;
import static table4K.view.MainView.setMainScene;

public class MailList {

    public static void setMailScene() {

        //Внешний ArrayList - отдельное письмо,
        // внутренний ArrayList - страницы письма
        ArrayList<ArrayList<Image>> mailImagesList = getMailList();

        ArrayList<ImageViewIterationWithMove> mailList = new ArrayList<>();
        for (ArrayList<Image> mailPages : mailImagesList) {
            mailList.add(getMail(mailPages));
        }

        ArrayList<Node> mailRepresentationList = new ArrayList<>();
        for (ImageViewIterationWithMove mail : mailList) {
            mailRepresentationList.add(mail.getImageIteration().getImageRepresentation());
        }

        for (Node mailGraphic : mailRepresentationList) {
            mailGraphic.setLayoutX(500);
            mailGraphic.setLayoutY(500);
        }

        Group mailRepresentationGroup = new Group(mailRepresentationList);

        ArrayList<Node> graphicElements = new ArrayList<>();
        graphicElements.add(mailRepresentationGroup);
        graphicElements.add(returnHome());

        setMainScene(new Image((new File(RESOURCES_PATH + "table_4K_mail.jpg")).toURI().toString()), graphicElements);
    }
}
