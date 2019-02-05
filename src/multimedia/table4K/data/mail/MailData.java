package table4K.data.mail;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

import static table4K.Main4K.RESOURCES_PATH;
import static table4K.data.FileProcessing.*;



public class MailData {

    //путь к директории раздела Письма
    private static final String MAIL_PATH = RESOURCES_PATH + "mail/";

    private static final String DIR_KEY_WORD = "mail";



    public static ArrayList<ArrayList<Image>> getMailList() {
        File[] mailDirList = getFilesWithCertainName(new File(MAIL_PATH), DIR_KEY_WORD);
        ArrayList<ArrayList<Image>> out = new ArrayList<>();

        for (File mailDir : mailDirList) {
            out.add(new ArrayList<>(getImages(mailDir)));
        }
        return out;
    }
}
