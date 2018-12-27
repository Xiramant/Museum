package table4K.map;

import general.ArrayListIndex;
import general.ImagePane;
import general.TextPane;
import javafx.geometry.VPos;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

import static general.TextProcessing.readingFileIntoString;
import static general.TouchWait.isTimeWaitEnd;
import static general.TouchWait.setTimeWait;
import static table4K.Main4K.TABLE_HEIGHT;
import static table4K.Main4K.TABLE_WIDTH;
import static table4K.Main4K.DEBUGGING_RATIO;
import static table4K.map.Map.*;

public class MapPaneText extends ImagePane {

    //максимальная ширина панели текстового блока для раздела Карты
    private static final double МAP_PANE_TEXT_WIDTH_MAX = 640 / DEBUGGING_RATIO;

    private static final double МAP_PANE_TEXT_BOTTOM_INSET = 100 / DEBUGGING_RATIO;

    //высота текстового блока
    private static final double МAP_PANE_TEXT_HEIGHT_TEXT_BLOCK = 620 / DEBUGGING_RATIO;

    //отступ сверху для текстового блока
    private static final double МAP_PANE_TEXT_TOP_PADDING_TEXT_BLOCK = 160 / DEBUGGING_RATIO;

    //отступ сверху для блока количество страниц
    private static final double МAP_PANE_TEXT_TOP_PADDING_PAGE_NUMBER = 800 / DEBUGGING_RATIO;

    //размер шрифта текстового блока
    private static final double МAP_PANE_TEXT_FONT_SIZE_TEXT_BLOCK = 28 / DEBUGGING_RATIO;

    //размер шрифта блока количество страниц
    static final double МAP_PANE_TEXT_FONT_SIZE_PAGE_NUMBER = 20 / DEBUGGING_RATIO;

    //шрифт текстового блока
    private final Font МAP_PANE_TEXT_FONT_TEXT_BLOCK = new Font("Book Antiqua Bold Italic", МAP_PANE_TEXT_FONT_SIZE_TEXT_BLOCK);

    //шрифт блока количество страниц
    private final Font МAP_PANE_TEXT_FONT_PAGE_NUMBER = new Font("Book Antiqua Bold Italic", МAP_PANE_TEXT_FONT_SIZE_PAGE_NUMBER);

    //тень
    private static final String MAP_PANE_TEXT_SHADOW_NORMAL = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 0, 3);";
    private static final String MAP_PANE_TEXT_SHADOW_DRAG_AND_DROP = "-fx-effect: dropshadow(gaussian, black, 50, 0, 0, 10);";

    //отображаемый текстовый блок
    private Text displayText = new Text();

    //отображение текста количества страниц
    private Text caseCountPageText = new Text();

    //лист стрингов, в которые преобразуется содержание текстовых файлов
    private ArrayListIndex<String> mapTextString = new ArrayListIndex<>();

    //лист стрингов с текстом, подходящим по размеру под заданный текстовый блок
    private ArrayListIndex<String> textPaneList = new ArrayListIndex<>();


    public MapPaneText(final ArrayList<File> textFiles) {

        super(TEXT_BACKGROUND_FILE, МAP_PANE_TEXT_WIDTH_MAX, 0);

        this.setLayoutX(TABLE_WIDTH - this.getPrefWidth());
        this.setLayoutY(TABLE_HEIGHT - this.getPrefHeight() - МAP_PANE_TEXT_BOTTOM_INSET);

        for (File temp: textFiles) {
            mapTextString.add(readingFileIntoString(temp));
        }

        setDisplayText(0);

        mouseAction();
        touchAction();

        this.setStyle(MAP_PANE_TEXT_SHADOW_NORMAL);

        this.getChildren().addAll(displayText, caseCountPageText);
    }


    //установка текста, отображаемого в текстовом блоке
    public void setDisplayText(final int mapTextStringIndex) {

        //текущий стринг, содержащий текст сражения,
        // соответствующий отображаемой карте
        String currentTextString = mapTextString.get(mapTextStringIndex);

        TextPane textPane = new TextPane();

        textPaneList.clear();
        textPaneList.addAll(textPane.breakTextIntoBlocks(currentTextString,
                this.getPrefWidth() * 0.8,
                МAP_PANE_TEXT_HEIGHT_TEXT_BLOCK,
                МAP_PANE_TEXT_FONT_TEXT_BLOCK));

        displayText.setWrappingWidth(this.getPrefWidth() * 0.8);
        displayText.setFont(МAP_PANE_TEXT_FONT_TEXT_BLOCK);
        displayText.setX(this.getPrefWidth() * 0.1);
        displayText.setY(МAP_PANE_TEXT_TOP_PADDING_TEXT_BLOCK);
        displayText.setTextOrigin(VPos.TOP);

        displayText.setText(textPaneList.getFirstElement());

        caseCountPageText.setFont(МAP_PANE_TEXT_FONT_PAGE_NUMBER);
        caseCountPageText.setX(this.getPrefWidth() * 0.1);
        caseCountPageText.setY(МAP_PANE_TEXT_TOP_PADDING_PAGE_NUMBER);
        caseCountPageText.setTextOrigin(VPos.BOTTOM);

        setCountPageText();
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

            this.getRelocationCoordinates().setXBegin(mouseEvent.getSceneX());
            this.getRelocationCoordinates().setYBegin(mouseEvent.getSceneY());
        });

        this.setOnMouseDragged(mouseEvent -> {

            this.getRelocationCoordinates().setXDelta(mouseEvent.getSceneX() - this.getRelocationCoordinates().getXBegin());
            this.setTranslateX(this.getRelocationCoordinates().getXDelta());

            this.getRelocationCoordinates().setYDelta(mouseEvent.getSceneY() - this.getRelocationCoordinates().getYBegin());
            this.setTranslateY(this.getRelocationCoordinates().getYDelta());

            this.setStyle(MAP_PANE_TEXT_SHADOW_DRAG_AND_DROP);
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

            this.setStyle(MAP_PANE_TEXT_SHADOW_NORMAL);
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
            this.getRelocationCoordinates().setXBegin(touchEvent.getTouchPoint().getSceneX());
            this.getRelocationCoordinates().setYBegin(touchEvent.getTouchPoint().getSceneY());
        });

        this.setOnTouchMoved(touchEvent -> {
            this.getRelocationCoordinates().setXDelta(touchEvent.getTouchPoint().getSceneX() - this.getRelocationCoordinates().getXBegin());
            this.setTranslateX(this.getRelocationCoordinates().getXDelta());

            this.getRelocationCoordinates().setYDelta(touchEvent.getTouchPoint().getSceneY() - this.getRelocationCoordinates().getYBegin());
            this.setTranslateY(this.getRelocationCoordinates().getYDelta());

            this.setStyle(MAP_PANE_TEXT_SHADOW_DRAG_AND_DROP);
        });

        this.setOnTouchReleased(touchEvent -> {
            if (isTimeWaitEnd()) {

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

                this.setStyle(MAP_PANE_TEXT_SHADOW_NORMAL);

                setTimeWait();
            }
        });
    }

    //ограничение на перемещение планшета
    private void setLocationRestriction() {

        if (this.getLayoutX() < MAP_INITIAL_AREA_X_BEGIN) {
            this.setLayoutX(MAP_INITIAL_AREA_X_BEGIN);
        }

        if (this.getLayoutY() < MAP_INITIAL_AREA_Y_BEGIN) {
            this.setLayoutY(MAP_INITIAL_AREA_Y_BEGIN);
        }

        if (this.getLayoutX() > MAP_INITIAL_AREA_X_END) {
            this.setLayoutX(MAP_INITIAL_AREA_X_END);
        }

        if (this.getLayoutY() > MAP_INITIAL_AREA_Y_END) {
            this.setLayoutY(MAP_INITIAL_AREA_Y_END);
        }
    }

}
