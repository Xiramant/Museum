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
import static table4K.Main4K.TABLE_HEIGHT;
import static table4K.Main4K.TABLE_WIDTH;
import static table4K.Main4K.debuggingRatio;
import static table4K.map.Map.*;

public class MapPaneText extends ImagePane {

    //максимальная ширина панели текстового блока для раздела Карты
    public static double МAP_PANE_TEXT_WIDTH_MAX = 640 / debuggingRatio;

    public static double МAP_PANE_TEXT_BOTTOM_INSET = 100 / debuggingRatio;

    //высота текстового блока
    public static double МAP_PANE_TEXT_HEIGHT_TEXT_BLOCK = 620 / debuggingRatio;

    //отступ сверху для текстового блока
    public static double МAP_PANE_TEXT_TOP_PADDING_TEXT_BLOCK = 160 / debuggingRatio;

    //отступ сверху для блока количество страниц
    public static double МAP_PANE_TEXT_TOP_PADDING_PAGE_NUMBER = 800 / debuggingRatio;

    //размер шрифта текстового блока
    public static double МAP_PANE_TEXT_FONT_SIZE_TEXT_BLOCK = 28 / debuggingRatio;

    //размер шрифта блока количество страниц
    public static double МAP_PANE_TEXT_FONT_SIZE_PAGE_NUMBER = 20 / debuggingRatio;

    //шрифт текстового блока
    private Font МAP_PANE_TEXT_FONT_TEXT_BLOCK = new Font("Book Antiqua Bold Italic", МAP_PANE_TEXT_FONT_SIZE_TEXT_BLOCK);

    //шрифт блока количество страниц
    private Font МAP_PANE_TEXT_FONT_PAGE_NUMBER = new Font("Book Antiqua Bold Italic", МAP_PANE_TEXT_FONT_SIZE_PAGE_NUMBER);

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

        for (int i = 0; i < textFiles.size(); i++) {
            mapTextString.add(readingFileIntoString(textFiles.get(i)));
        }

        setDisplayText(0);

        mouseAction();
        touchAction();

        this.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, 0, 3);");

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

            this.setStyle(
                    "-fx-effect: dropshadow(gaussian, black, 50, 0, 0, 10);"
            );
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

            this.setStyle(
                    "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 0, 3);"
            );
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

            this.setStyle(
                    "-fx-effect: dropshadow(gaussian, black, 50, 0, 0, 10);"
            );
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

            this.setStyle(
                    "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 0, 3);"
            );

            try {
                wait(1000);
            } catch (InterruptedException e) {
                System.out.println("проблема с установкой задержки в классе MapPaneText при отпускании тача");
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
