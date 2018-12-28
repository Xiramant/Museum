package table4K.mail;

import general.FileFormat;
import general.ImageViewIteration;
import general.SectionKey;
import javafx.scene.Group;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.*;
import static general.InitialLocation.setRandomPositionInArea;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;

public class Mail {

    //путь к директории с файлами писем
    private static final String MAIL_PATH = RESOURCES_PATH + "mail/";

    private static final SectionKey MAIL_KEY = SectionKey.MAIL;

    private static final double MAIL_WIDTH_MAX = 600 / DEBUGGING_RATIO;

    public static final double MAIL_WIDTH_SPACING_MIN = 100 / DEBUGGING_RATIO;

    //координаты начала и конца области для случайного расположения писем
    private static final double MAIL_AREA_BEGIN_X = 2909 / DEBUGGING_RATIO;
    private static final double MAIL_AREA_BEGIN_Y = 441 / DEBUGGING_RATIO;
    private static final double MAIL_AREA_END_X = 3490 / DEBUGGING_RATIO;
    private static final double MAIL_AREA_END_Y = 1128 / DEBUGGING_RATIO;

    //координаты первоначального расположения 4-х писем
    private static final double MAIL_FIRST_X = 993 / DEBUGGING_RATIO;
    private static final double MAIL_FIRST_Y = 916 / DEBUGGING_RATIO;
    private static final double MAIL_SECOND_X = 1670 / DEBUGGING_RATIO;
    private static final double MAIL_SECOND_Y = 448 / DEBUGGING_RATIO;
    private static final double MAIL_THIRD_X = 2374 / DEBUGGING_RATIO;
    private static final double MAIL_THIRD_Y = 861 / DEBUGGING_RATIO;
    private static final double MAIL_FORTH_X = 2000 / DEBUGGING_RATIO;
    private static final double MAIL_FORTH_Y = 1550 / DEBUGGING_RATIO;

    //ограничения на расположение писем
    static final double MAIL_LEFT = 900 / DEBUGGING_RATIO;
    static final double MAIL_TOP = 370 / DEBUGGING_RATIO;
    //переопределения размеров для MAIL_RIGHT и MAIL_BOTTOM не сделаны, т.к.
    // они определяются по значениям TABLE_WIDTH и TABLE_HEIGHT
    // которые уже уменьшены
    static final double MAIL_RIGHT = TABLE_WIDTH;
    static final double MAIL_BOTTOM = TABLE_HEIGHT;

    public static void setMailScene() {

        changeRootBackground(RESOURCES_PATH + "table_4K_mail.jpg");
        mainPane.getChildren().clear();

        //лист директорий, в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileMailDirs = new ArrayList<>(getDirKey(MAIL_KEY));

        //файлы для отображения на основной сцене
        // Внешний ArrayList - отдельное письмо
        // Внутренний ArrayListIndex - страницы письма
        ArrayList<ArrayList<File>> mailFiles = new ArrayList<>(getFiles(fileMailDirs, FileFormat.IMAGE));

        //группа для писем
        Group mailGroup = new Group();

        //инициализация первоначального состояния раздела Mail
        for (int i = 0; i < mailFiles.size(); i++) {
            mailGroup.getChildren().add(new MailPane(mailFiles.get(i), MAIL_WIDTH_MAX));
        }

        //первоначальное расположение писем на основной сцене

        //расположение писем в случайном месте в пределах заданной области
        if (mailGroup.getChildren().size() > 0) {
            setRandomPositionInArea(mailGroup.getChildren(),
                    MAIL_AREA_BEGIN_X,
                    MAIL_AREA_BEGIN_Y,
                    MAIL_AREA_END_X,
                    MAIL_AREA_END_Y);
        }

        //переопределение координат первых трех писем:
        // они открываются на 2 странице
        // и располагаются по заданным координатам
        if (mailGroup.getChildren().size() > 0) {
            ((ImageViewIteration)mailGroup.getChildren().get(0)).setNextImage();
            mailGroup.getChildren().get(0).setLayoutX(MAIL_FIRST_X);
            mailGroup.getChildren().get(0).setLayoutY(MAIL_FIRST_Y);
        }

        if (mailGroup.getChildren().size() > 1) {
            ((ImageViewIteration)mailGroup.getChildren().get(1)).setNextImage();
            mailGroup.getChildren().get(1).setLayoutX(MAIL_SECOND_X);
            mailGroup.getChildren().get(1).setLayoutY(MAIL_SECOND_Y);
        }

        if (mailGroup.getChildren().size() > 2) {
            ((ImageViewIteration)mailGroup.getChildren().get(2)).setNextImage();
            mailGroup.getChildren().get(2).setLayoutX(MAIL_THIRD_X);
            mailGroup.getChildren().get(2).setLayoutY(MAIL_THIRD_Y);
        }

        if (mailGroup.getChildren().size() > 3) {
            mailGroup.getChildren().get(3).setLayoutX(MAIL_FORTH_X);
            mailGroup.getChildren().get(3).setLayoutY(MAIL_FORTH_Y);
        }

        mainPane.getChildren().addAll(mailGroup, returnHome());
    }
}
