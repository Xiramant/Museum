package table4K.portfolio;

import general.ImagePane;
import general.SectionKey;
import javafx.geometry.VPos;
import javafx.scene.input.InputEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

import static general.TextProcessing.readingFirstStokeFromFileAndSplitIntoWord;
import static general.TouchWait.isTimeWaitEnd;
import static general.TouchWait.setTimeWait;
import static table4K.BackHome.returnBack;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;

public class PersonalCardPane extends ImagePane {

    //лист файлов изображений личного дела
    private ArrayList<File> imageFiles = new ArrayList<>();

    //лист файлов текста личного дела
    private ArrayList<File> textFiles = new ArrayList<>();

    //фио
    private String fio;

    //максимальная ширина личной карточки
    private static final double PERSONAL_CARD_PANE_WIDTH_MAX = 450 / DEBUGGING_RATIO;

    //максимальная высота личной карточки
    public static final double PERSONAL_CARD_PANE_HEIGHT_MAX = 278 / DEBUGGING_RATIO;

    //максимальная ширина фото на личную карточку
    private static final double PERSONAL_CARD_PANE_PHOTO_WIDTH_MAX = 128 / DEBUGGING_RATIO;

    //отступы для фото для личной карточки
    private static final double PERSONAL_CARD_PANE_PHOTO_X = 50 / DEBUGGING_RATIO;
    private static final double PERSONAL_CARD_PANE_PHOTO_Y = 51 / DEBUGGING_RATIO;

    //шрифт для текста в личной карточки
    private static final Font PERSONAL_CARD_PANE_FONT = new Font("B52", 20 / DEBUGGING_RATIO);

    //отступы для текста в личной карточке
    private static final double PERSONAL_CARD_PANE_SURNAME_X = 270 / DEBUGGING_RATIO;
    private static final double PERSONAL_CARD_PANE_SURNAME_Y = 106 / DEBUGGING_RATIO;
    private static final double PERSONAL_CARD_PANE_NAME_X = 270 / DEBUGGING_RATIO;
    private static final double PERSONAL_CARD_PANE_NAME_Y = 130 / DEBUGGING_RATIO;
    private static final double PERSONAL_CARD_PANE_PATRONYMIC_X = 270 / DEBUGGING_RATIO;
    private static final double PERSONAL_CARD_PANE_PATRONYMIC_Y = 152 / DEBUGGING_RATIO;
    private static final double PERSONAL_CARD_PANE_YEAR_X = 291 / DEBUGGING_RATIO;
    private static final double PERSONAL_CARD_PANE_YEAR_Y = 178 / DEBUGGING_RATIO;

    //тень
    private static final String PERSONAL_CARD_PANE_SHADOW_STILL = "-fx-effect: dropshadow(gaussian, black, 5, 0.3, -0.5, 1);";


    public PersonalCardPane(final ArrayList<File> imageFilesEnter, final ArrayList<File> textFilesEnter) {
        super(new File(RESOURCES_PATH + "portfolio/personal_card.png"), PERSONAL_CARD_PANE_WIDTH_MAX, 0);

        imageFiles.addAll(imageFilesEnter);
        textFiles.addAll(textFilesEnter);

        ImagePane photo = new ImagePane(imageFiles.get(0), PERSONAL_CARD_PANE_PHOTO_WIDTH_MAX, 0);
        photo.setLayoutX(PERSONAL_CARD_PANE_PHOTO_X);
        photo.setLayoutY(PERSONAL_CARD_PANE_PHOTO_Y);
        photo.setStyle(PERSONAL_CARD_PANE_SHADOW_STILL);

        ArrayList<String> personalInfo = new ArrayList<>(readingFirstStokeFromFileAndSplitIntoWord(textFiles.get(0)));

        Text surname = new Text();
        surname.setFont(PERSONAL_CARD_PANE_FONT);
        surname.setTextOrigin(VPos.BOTTOM);
        surname.setLayoutX(PERSONAL_CARD_PANE_SURNAME_X);
        surname.setLayoutY(PERSONAL_CARD_PANE_SURNAME_Y);
        surname.setText(personalInfo.get(0));

        Text name = new Text();
        name.setFont(PERSONAL_CARD_PANE_FONT);
        name.setTextOrigin(VPos.BOTTOM);
        name.setLayoutX(PERSONAL_CARD_PANE_NAME_X);
        name.setLayoutY(PERSONAL_CARD_PANE_NAME_Y);
        name.setText(personalInfo.get(1));

        Text patronymic = new Text();
        patronymic.setFont(PERSONAL_CARD_PANE_FONT);
        patronymic.setTextOrigin(VPos.BOTTOM);
        patronymic.setLayoutX(PERSONAL_CARD_PANE_PATRONYMIC_X);
        patronymic.setLayoutY(PERSONAL_CARD_PANE_PATRONYMIC_Y);
        patronymic.setText(personalInfo.get(2));

        Text year = new Text();
        year.setFont(PERSONAL_CARD_PANE_FONT);
        year.setTextOrigin(VPos.BOTTOM);
        year.setLayoutX(PERSONAL_CARD_PANE_YEAR_X);
        year.setLayoutY(PERSONAL_CARD_PANE_YEAR_Y);
        year.setText(personalInfo.get(3));

        fio = surname.getText() + " " + name.getText() + " " + patronymic.getText();

        this.getChildren().addAll(photo, surname, name, patronymic, year);

        this.setOnMouseClicked(event -> personalCardAction(event));
        this.setOnTouchReleased(event -> personalCardAction(event));
    }

    private void personalCardAction(final InputEvent event) {

        if (isTimeWaitEnd() && actionPermission(event)) {
            PortfolioCasePane pcp = new PortfolioCasePane(fio, imageFiles, textFiles);

            changeRootBackground(RESOURCES_PATH + "table_4K_portfolio.jpg");
            mainPane.getChildren().clear();
            mainPane.getChildren().addAll(pcp, returnBack(SectionKey.PORTFOLIO), returnHome());

            setTimeWait();
        }
    }

}
