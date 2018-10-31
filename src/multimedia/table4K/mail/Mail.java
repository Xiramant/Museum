package table4K.mail;

import general.FileFormat;
import general.ImagePaneSection;
import general.SectionKey;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.*;
import static general.InitialLocation.initialPositionElements;
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.changeRootBackground;
import static table4K.Main4K.mainPane;

public class Mail {

    //путь к директории с файлами писем
    private static final String MAIL_PATH = RESOURCES_PATH + "mail/";

    private static final SectionKey MAIL_KEY = SectionKey.MAIL;

    public static double MAIL_WIDTH_MAX = 600;

    public static double MAIL_WIDTH_SPACING_MIN = 100;

    public static void setMailScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_mail.jpg");

        //лист директорий, в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileMailDirs = new ArrayList<>(getDirKey(MAIL_KEY));

        //файлы для отображения на основной сцене
        // Внешний ArrayList - отдельное письмо
        // Внутренний ArrayListIndex - страницы письма
        ArrayList<ArrayList<File>> mailFiles = new ArrayList<>(getFiles(fileMailDirs, FileFormat.IMAGE));

        //инициализация первоначального состояния раздела Mail
        mainPane.getChildren().clear();

        for (int i = 0; i < mailFiles.size(); i++) {
            mainPane.getChildren().add(new ImagePaneSection(mailFiles.get(i), MAIL_WIDTH_MAX, 0));
        }

        System.out.println("mainPane.getWidth() = " + mainPane.getWidth() + "; mainPane.getHeight()" + mainPane.getHeight());
        //первоначальное расположение писем на основной сцене
        initialPositionElements(mainPane.getWidth(), mainPane.getHeight(), mainPane.getChildren(), MAIL_WIDTH_MAX + MAIL_WIDTH_SPACING_MIN);

    }
}
