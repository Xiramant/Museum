package table4K.portfolio;

import general.ArrayListIndex;
import general.ImagePane;
import general.TextPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

import static general.TextProcessing.*;
import static table4K.Main4K.*;
import static table4K.Main4K.RESOURCES_PATH;

public class PortfolioCasePane extends ImagePane{

    //Развернутая папка Личное дело
    //максимальная высота
    private static final double PORTFOLIO_CASE_HEIGHT_MAX = 1300 / debuggingRatio;
    //отступ слева
    private static final double PORTFOLIO_CASE_X = 1500 / debuggingRatio;
    //отступ сверху
    private static final double PORTFOLIO_CASE_Y = 760 / debuggingRatio;

    //Текстовый блок ФИО на корешке папки Личное дело
    //шрифт
    private static final Font FIO_FONT = new Font("Book Antiqua Bold Italic", 52 / debuggingRatio);
    //цвет
    private static final Color FIO_COLOR = Color.rgb(201, 136, 33);
    //отступ слева
    private static final double FIO_X = 920 / debuggingRatio;
    //отступ сверху
    private static final double FIO_Y = 640 / debuggingRatio;

    //Фото для биографии
    //максимальная высота
    private static final double BIOGRAPHY_PHOTO_HEIGHT_MAX = 272 / debuggingRatio;
    //отступ слева
    private static final double BIOGRAPHY_PHOTO_X = 1021 / debuggingRatio;
    //отступ сверху
    private static final double BIOGRAPHY_PHOTO_Y = 128 / debuggingRatio;

    //Текстовый блок биографии
    //ширина
    private static final double BIOGRAPHY_TEXT_WIDTH = 620 / debuggingRatio;
    //высота
    private static final double BIOGRAPHY_TEXT_HEIGHT = 765 / debuggingRatio;
    //шрифт
    private static final Font BIOGRAPHY_TEXT_FONT =  new Font("B52", 23 / debuggingRatio);
    //межстрочный интервал
    private static final double BIOGRAPHY_TEXT_LINE_SPACING = 23 / 2 / debuggingRatio;
    //отступ слева
    private static final double BIOGRAPHY_TEXT_X =  1021 / debuggingRatio;
    //отступ сверху
    private static final double BIOGRAPHY_TEXT_Y =  435 / debuggingRatio;
    //лист стрингов для страниц при перелистывании текстового блока
    private ArrayListIndex<String> textBlockStrings;


    public PortfolioCasePane(final String fioEnter, final ArrayList<File> imageFilesEnter, final ArrayList<File> textFilesEnter) {
        super(new File(RESOURCES_PATH + "portfolio/portfolio_case.png"), 0, PORTFOLIO_CASE_HEIGHT_MAX);

        this.setLayoutX(PORTFOLIO_CASE_X);
        this.setLayoutY(PORTFOLIO_CASE_Y);
        this.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, 0, 3);");

        //получение текстового блока для ФИО
        Text fioBlock = getTextFIO(fioEnter);
        this.getChildren().add(fioBlock);

        //получение фото для биографии
        ImageView biographyPhoto = null;
        if (imageFilesEnter.size() != 0) {
            biographyPhoto = getBiographyPhoto(imageFilesEnter.get(0));
            this.getChildren().add(biographyPhoto);
        }

        //получение текста для биографии
        // с добавлением по 3 пробела перед каждой строчкой для отступа
        String biography = addSpaceToEachLine(readingFileIntoString(textFilesEnter.get(1)), 3);

        TextPane textPane = new TextPane();
        textBlockStrings = new ArrayListIndex<>();
        textBlockStrings.addAll(textPane.breakTextIntoBlocks(biography,
                BIOGRAPHY_TEXT_WIDTH,
                BIOGRAPHY_TEXT_HEIGHT,
                BIOGRAPHY_TEXT_FONT,
                BIOGRAPHY_TEXT_LINE_SPACING));

        Text biographyBlock = getBiographyText();
        this.getChildren().add(biographyBlock);

        //получение изображений для размещения в левой части
        // развернутой папки Личное дело
        ArrayList<File> imageFiles = new ArrayList<>();

        if (imageFilesEnter.size() > 1) {
            imageFiles.addAll(imageFilesEnter);
            imageFiles.remove(0);

            for (int i = imageFiles.size() - 1; i >= 0; i--) {
                PortfolioCasePhoto temp = new PortfolioCasePhoto(imageFiles.get(i));
                this.getChildren().add(temp);
            }
        }

    }

    //получение Text для ФИО с установленными параметрами
    private Text getTextFIO(final String fio) {
        Text temp = new Text(fio);

        temp.setFont(FIO_FONT);
        temp.setFill(FIO_COLOR);
        temp.setRotate(-90);
        temp.setLayoutX(FIO_X - temp.getLayoutBounds().getWidth() / 2);
        temp.setLayoutY(FIO_Y);

        return temp;
    }

    //получение ImageView для фото для биографии
    // с установленными параметрами
    private ImageView getBiographyPhoto(final File biographyPhotoFile) {
        ImageView temp = new ImageView("file:" + biographyPhotoFile.toString());

        temp.setFitHeight(BIOGRAPHY_PHOTO_HEIGHT_MAX);
        temp.setPreserveRatio(true);
        temp.setStyle("-fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);");
        temp.setLayoutX(BIOGRAPHY_PHOTO_X);
        temp.setLayoutY(BIOGRAPHY_PHOTO_Y);

        return temp;
    }

    //Получение Text для текста биографии
    // с установкой действий по нажатию мыши или тача
    private Text getBiographyText() {

        Text temp = new Text(textBlockStrings.getFirstElement());
        temp.setWrappingWidth(BIOGRAPHY_TEXT_WIDTH);
        temp.setFont(BIOGRAPHY_TEXT_FONT);
        temp.setLineSpacing(BIOGRAPHY_TEXT_LINE_SPACING);
        temp.setLayoutX(BIOGRAPHY_TEXT_X);
        temp.setLayoutY(BIOGRAPHY_TEXT_Y);

        temp.setOnMouseClicked(event -> {

            if (event.getButton() == MouseButton.PRIMARY) {
                temp.setText(textBlockStrings.getNextElement());
            }

            if (event.getButton() == MouseButton.SECONDARY) {
                temp.setText(textBlockStrings.getPrevElement());
            }
        });

        temp.setOnTouchReleased(event -> {

                temp.setText(textBlockStrings.getNextElement());

            try {
                wait(1000);
            } catch (InterruptedException e) {
                System.out.println("проблема с установкой задержки в классе PortfolioCasePane при отпускании тача");
            }
        });

        return temp;
    }
}
