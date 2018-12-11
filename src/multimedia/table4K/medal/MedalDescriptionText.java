package table4K.medal;

import general.ArrayListIndex;
import general.ImagePane;
import general.TextPane;
import javafx.geometry.VPos;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;

import static general.TextProcessing.readingFileIntoString;
import static table4K.Main4K.*;
import static table4K.medal.Medal.DESCRIPTION_HEIGHT;
import static table4K.medal.Medal.DESCRIPTION_WIDTH;

public class MedalDescriptionText extends ImagePane {

    //максимальная высота панели описания раздела Медали
    private static final double MEDAL_DESCRIPTION_TEXT_PANE_HEIGHT_MAX = 904 / debuggingRatio;

    //ширина/высота текстового блока описания раздела Медали
    private static final double MEDAL_DESCRIPTION_TEXT_BLOCK_WIDTH = 540 / debuggingRatio;
    private static final double MEDAL_DESCRIPTION_TEXT_BLOCK_HEIGHT = 770 / debuggingRatio;

    //положение текстового блока описания раздела Медали
    private static final double MEDAL_DESCRIPTION_TEXT_X = 60 / debuggingRatio;
    private static final double MEDAL_DESCRIPTION_TEXT_Y = 60 / debuggingRatio;

    //отступ сверху для блока количество страниц
    private static final double MEDAL_DESCRIPTION_PAGE_NUMBER_Y = 850 / debuggingRatio;

    //размер шрифта текстового блока
    private static final double MEDAL_DESCRIPTION_TEXT_BLOCK_FONT_SIZE = 28 / debuggingRatio;

    //размер шрифта блока количество страниц
    private static final double MEDAL_DESCRIPTION_PAGE_NUMBER_FONT_SIZE = 22 / debuggingRatio;

    //шрифт текстового блока
    private static final Font MEDAL_DESCRIPTION_TEXT_BLOCK_FONT = new Font("Book Antiqua Bold Italic", MEDAL_DESCRIPTION_TEXT_BLOCK_FONT_SIZE);

    //шрифт блока количество страниц
    private static final Font MEDAL_DESCRIPTION_PAGE_NUMBER_FONT = new Font("Book Antiqua Bold Italic", MEDAL_DESCRIPTION_PAGE_NUMBER_FONT_SIZE);

    //отображаемый текстовый блок
    private Text displayText = new Text();

    //отображение текста количества страниц
    private Text caseCountPageText = new Text();

    //лист стрингов с текстом, подходящим по размеру под заданный текстовый блок
    private ArrayListIndex<String> textPaneList = new ArrayListIndex<>();

    //область ограничения перемещения панели
    private static final double TOP_RESTRICTION = 0;
    private static final double BOTTOM_RESTRICTION = DESCRIPTION_HEIGHT - MEDAL_DESCRIPTION_TEXT_PANE_HEIGHT_MAX;
    private static final double LEFT_RESTRICTION = 0;
    private static final double RIGHT_RESTRICTION = DESCRIPTION_WIDTH - 653 / debuggingRatio;

    //тени для неподвижной/перемещаемой панели
    private static final String SHADOW_STILL = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 1, 2);";
    private static final String SHADOW_MOVED = "-fx-effect: dropshadow(gaussian, black, 50, 0, 0, 10);";


    MedalDescriptionText(final File textFileDescription) {
        super(new File(RESOURCES_PATH + "medal/description.jpg"), 0, MEDAL_DESCRIPTION_TEXT_PANE_HEIGHT_MAX);

        String text = readingFileIntoString(textFileDescription);

        TextPane textPane = new TextPane();

        textPaneList.addAll(textPane.breakTextIntoBlocks(text,
                MEDAL_DESCRIPTION_TEXT_BLOCK_WIDTH,
                MEDAL_DESCRIPTION_TEXT_BLOCK_HEIGHT,
                MEDAL_DESCRIPTION_TEXT_BLOCK_FONT));

        displayText.setWrappingWidth(MEDAL_DESCRIPTION_TEXT_BLOCK_WIDTH);
        displayText.setFont(MEDAL_DESCRIPTION_TEXT_BLOCK_FONT);
        displayText.setX(MEDAL_DESCRIPTION_TEXT_X);
        displayText.setY(MEDAL_DESCRIPTION_TEXT_Y);
        displayText.setTextOrigin(VPos.TOP);

        displayText.setText(textPaneList.getFirstElement());

        caseCountPageText.setFont(MEDAL_DESCRIPTION_PAGE_NUMBER_FONT);
        caseCountPageText.setX(MEDAL_DESCRIPTION_TEXT_X);
        caseCountPageText.setY(MEDAL_DESCRIPTION_PAGE_NUMBER_Y);
        caseCountPageText.setTextOrigin(VPos.BOTTOM);

        setCountPageText();

        mouseAction();
        touchAction();

        this.setStyle(SHADOW_STILL);

        this.getChildren().addAll(displayText, caseCountPageText);
    }

    //установка отображения количества страниц
    private void setCountPageText() {
        caseCountPageText.setText("страница: " + (textPaneList.getCurrentIndex() + 1) + " / " + textPaneList.size());
    }

    //методы смены текста в текстовом блоке
    private void setNextTextBlock() {
        if(textPaneList.hasNextElement()) {
            displayText.setText(textPaneList.getNextElement());
            setCountPageText();
        } else {
            setFirstTextBlock();
        }
    }

    private void setPrevTextBlock() {
        displayText.setText(textPaneList.getPrevElement());
        setCountPageText();
    }

    private void setFirstTextBlock() {
        displayText.setText(textPaneList.getFirstElement());
        setCountPageText();
    }

    private void setLastTextBlock() {
        displayText.setText(textPaneList.getLastElement());
        setCountPageText();
    }

    //Обработка событий Мыши
    private void mouseAction() {

        //перемещение планшета
        this.setOnMousePressed(mouseEvent -> {
            this.toFront();

            this.getRelocationCoordinates().setXBegin(mouseEvent.getSceneX());
            this.getRelocationCoordinates().setYBegin(mouseEvent.getSceneY());
        });

        this.setOnMouseDragged(mouseEvent -> {

            this.getRelocationCoordinates().setXDelta(mouseEvent.getSceneX() - this.getRelocationCoordinates().getXBegin());
            this.setTranslateX(this.getRelocationCoordinates().getXDelta());

            this.getRelocationCoordinates().setYDelta(mouseEvent.getSceneY() - this.getRelocationCoordinates().getYBegin());
            this.setTranslateY(this.getRelocationCoordinates().getYDelta());

            this.setStyle(SHADOW_MOVED);
        });

        this.setOnMouseReleased(mouseEvent -> {

            if (Math.abs(this.getRelocationCoordinates().getXDelta()) +
                    Math.abs(this.getRelocationCoordinates().getYDelta()) > 10d) {
                isDragAndDrop = true;
            }

            this.setLayoutX(this.getLayoutX() + this.getTranslateX());
            this.setLayoutY(this.getLayoutY() + this.getTranslateY());
            this.setTranslateX(0);
            this.setTranslateY(0);

            this.clearRelocationCoordinates();

            setLocationRestriction();

            this.setStyle(SHADOW_STILL);
        });

        //действия при клике мышкой
        this.setOnMouseClicked(event -> {

            if (!isDragAndDrop) {

                //нажатие левой кнопки приводит к листанию текста вперед
                if (event.getButton() == MouseButton.PRIMARY) {
                    this.setNextTextBlock();
                }

                //нажатие правой кнопки приводит к листанию текста назад
                if (event.getButton() == MouseButton.SECONDARY) {
                    this.setPrevTextBlock();
                }

                //Двойной щелчок левой кнопкой приводит к переходу на последнюю страницу
                if (event.getClickCount() == 2) {
                    this.setLastTextBlock();
                }

                //Двойной щелчок правой кнопкой приводит к переходу на первую страницу
                if (event.getButton() == MouseButton.SECONDARY && event.getClickCount() == 2) {
                    this.setFirstTextBlock();
                }
            }

            isDragAndDrop = false;
        });
    }

    //Обработка событий Тача
    private void touchAction() {

        this.setOnTouchPressed(touchEvent -> {
            this.toFront();

            this.getRelocationCoordinates().setXBegin(touchEvent.getTouchPoint().getSceneX());
            this.getRelocationCoordinates().setYBegin(touchEvent.getTouchPoint().getSceneY());
        });

        this.setOnTouchMoved(touchEvent -> {
            this.getRelocationCoordinates().setXDelta(touchEvent.getTouchPoint().getSceneX() - this.getRelocationCoordinates().getXBegin());
            this.setTranslateX(this.getRelocationCoordinates().getXDelta());

            this.getRelocationCoordinates().setYDelta(touchEvent.getTouchPoint().getSceneY() - this.getRelocationCoordinates().getYBegin());
            this.setTranslateY(this.getRelocationCoordinates().getYDelta());

            this.setStyle(SHADOW_MOVED);
        });

        this.setOnTouchReleased(touchEvent -> {

            if (Math.abs(this.getRelocationCoordinates().getXDelta()) +
                    Math.abs(this.getRelocationCoordinates().getYDelta()) > 10d) {
                isDragAndDrop = true;
            } else {
                //исключение перемещения панели,
                // если она смещена меньше, чем на 10 пикселей
                // из-за плохой работы тачпанели на мультимедиа столе
                this.setTranslateX(0);
                this.setTranslateY(0);
            }

            this.setLayoutX(this.getLayoutX() + this.getTranslateX());
            this.setLayoutY(this.getLayoutY() + this.getTranslateY());
            this.setTranslateX(0);
            this.setTranslateY(0);

            this.clearRelocationCoordinates();

            setLocationRestriction();

            //перелистывание страниц письма вперед,
            // если оно не перемещалось
            if (!isDragAndDrop) {
                this.setNextTextBlock();
            }
            isDragAndDrop = false;

            this.setStyle(SHADOW_STILL);

            try {
                wait(1000);
            } catch (InterruptedException e) {
                System.out.println("проблема с установкой задержки в классе MedalDescriptionText при отпускании тача");
            }
        });

    }

    //ограничение на перемещение планшета
    private void setLocationRestriction() {

        if (this.getLayoutX() < LEFT_RESTRICTION) {
            this.setLayoutX(LEFT_RESTRICTION);
        }

        if (this.getLayoutY() < TOP_RESTRICTION) {
            this.setLayoutY(TOP_RESTRICTION);
        }

        if (this.getLayoutX() > RIGHT_RESTRICTION) {
            this.setLayoutX(RIGHT_RESTRICTION);
        }

        if (this.getLayoutY() > BOTTOM_RESTRICTION) {
            this.setLayoutY(BOTTOM_RESTRICTION);
        }
    }

}
