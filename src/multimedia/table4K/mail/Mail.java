package table4K.mail;

import general.FileFormat;
import general.ImagePaneIteration;
import general.SectionKey;

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

    private static final double MAIL_WIDTH_MAX = 600 / debuggingRatio;

    public static final double MAIL_WIDTH_SPACING_MIN = 100 / debuggingRatio;

    //координаты начала и конца области для случайного расположения писем
    private static final double MAIL_AREA_BEGIN_X = 2909 / debuggingRatio;
    private static final double MAIL_AREA_BEGIN_Y = 441 / debuggingRatio;
    private static final double MAIL_AREA_END_X = 3490 / debuggingRatio;
    private static final double MAIL_AREA_END_Y = 1128 / debuggingRatio;

    //координаты первоначального расположения 4-х писем
    private static final double MAIL_FIRST_X = 993 / debuggingRatio;
    private static final double MAIL_FIRST_Y = 916 / debuggingRatio;
    private static final double MAIL_SECOND_X = 1670 / debuggingRatio;
    private static final double MAIL_SECOND_Y = 448 / debuggingRatio;
    private static final double MAIL_THIRD_X = 2374 / debuggingRatio;
    private static final double MAIL_THIRD_Y = 861 / debuggingRatio;
    private static final double MAIL_FORTH_X = 2000 / debuggingRatio;
    private static final double MAIL_FORTH_Y = 1550 / debuggingRatio;

    //ограничения на расположение писем
    static final double MAIL_LEFT = 900 / debuggingRatio;
    static final double MAIL_TOP = 370 / debuggingRatio;
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

        //инициализация первоначального состояния раздела Mail
        for (int i = 0; i < mailFiles.size(); i++) {
            mainPane.getChildren().add(new MailPane(mailFiles.get(i), MAIL_WIDTH_MAX, 0));
        }

        //первоначальное расположение писем на основной сцене

        //расположение писем в случайном месте в пределах заданной области
        if (mainPane.getChildren().size() > 0) {
            setRandomPositionInArea(mainPane.getChildren(),
                    MAIL_AREA_BEGIN_X,
                    MAIL_AREA_BEGIN_Y,
                    MAIL_AREA_END_X,
                    MAIL_AREA_END_Y);
        }

        //переопределение координат первых трех писем:
        // они открываются на 2 странице
        // и располагаются по заданным координатам
        if (mainPane.getChildren().size() > 0) {
            ((ImagePaneIteration)mainPane.getChildren().get(0)).setNextImageBackground();
            mainPane.getChildren().get(0).setLayoutX(MAIL_FIRST_X);
            mainPane.getChildren().get(0).setLayoutY(MAIL_FIRST_Y);
        }

        if (mainPane.getChildren().size() > 1) {
            ((ImagePaneIteration)mainPane.getChildren().get(1)).setNextImageBackground();
            mainPane.getChildren().get(1).setLayoutX(MAIL_SECOND_X);
            mainPane.getChildren().get(1).setLayoutY(MAIL_SECOND_Y);
        }

        if (mainPane.getChildren().size() > 2) {
            ((ImagePaneIteration)mainPane.getChildren().get(2)).setNextImageBackground();
            mainPane.getChildren().get(2).setLayoutX(MAIL_THIRD_X);
            mainPane.getChildren().get(2).setLayoutY(MAIL_THIRD_Y);
        }

        if (mainPane.getChildren().size() > 3) {
            mainPane.getChildren().get(3).setLayoutX(MAIL_FORTH_X);
            mainPane.getChildren().get(3).setLayoutY(MAIL_FORTH_Y);
        }

        mainPane.getChildren().add(returnHome());
    }
}
