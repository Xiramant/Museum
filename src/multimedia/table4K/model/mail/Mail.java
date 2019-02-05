package table4K.model.mail;


import javafx.scene.Node;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

import static table4K.BackHome.returnHome;
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.data.RootPaneBackgroundData.getBookBackground;
import static table4K.data.mail.MailData.getMailList;
import static table4K.view.MainView.setMainScene;

public class Mail {

    public static void setMailScene() {

        //Внешний ArrayList - отдельное письмо,
        // внутренний ArrayList - страницы письма
        ArrayList<ArrayList<Image>> mailImagesList = getMailList();

        ArrayList<MailRepresentation> mailList = new ArrayList<>();
        for (ArrayList<Image> mailPages : mailImagesList) {
            mailList.add(new MailRepresentation(mailPages));
        }

        ArrayList<Node> graphicElements = new ArrayList<>();
        for (MailRepresentation mail : mailList) {
            graphicElements.add(mail.getImageIteration().getImageRepresentation());
        }
        for (Node mailGraphic : graphicElements) {
            System.out.println("x = " + mailGraphic.getLayoutX() + "; y = " + mailGraphic.getLayoutX());
            mailGraphic.setLayoutX(500);
            mailGraphic.setLayoutY(500);
        }


        graphicElements.add(returnHome());

        setMainScene(new Image((new File(RESOURCES_PATH + "table_4K_mail.jpg")).toURI().toString()), graphicElements);
    }
}
