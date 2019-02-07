package table4K.model.mail;


import javafx.scene.image.Image;
import table4K.model.imagePresentation.ImageViewIterationWithMove;

import java.util.ArrayList;

import static table4K.BackHome.returnHome;
import static table4K.data.mail.MailData.getMailImagesList;
import static table4K.model.mail.MailRepresentation.getMail;
import static table4K.view.mail.MailListView.setMailListView;

public class MailList {

    public static void setMailScene() {

        //Внешний ArrayList - отдельное письмо,
        // внутренний ArrayList - страницы письма
        ArrayList<ArrayList<Image>> mailImagesList = getMailImagesList();

        ArrayList<ImageViewIterationWithMove> mailList = getMailList(mailImagesList);

        setMailListView(mailList);

    }

    private static ArrayList<ImageViewIterationWithMove> getMailList(final ArrayList<ArrayList<Image>> mailImagesListArg) {
        ArrayList<ImageViewIterationWithMove> out = new ArrayList<>();
        for (ArrayList<Image> mailPages : mailImagesListArg) {
            out.add(getMail(mailPages));
        }
        return out;
    }

}
