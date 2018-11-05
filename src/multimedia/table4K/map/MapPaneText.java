package table4K.map;

import general.ArrayListIndex;
import general.ImagePane;
import general.TextPane;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

import static general.TextProcessing.readingFileIntoString;
import static table4K.Main4K.TABLE_HEIGHT;
import static table4K.Main4K.TABLE_WIDTH;
import static table4K.map.Map.TEXT_BACKGROUND_FILE;

public class MapPaneText extends ImagePane {

    //высота текстового блока
    private static final double TEXT_BLOCK_HEIGHT = 310;

    //отступ сверху для текстового блока
    private static final double TEXT_TOP_PADDING = 80;

    //шрифт текстового блока
    private static final Font TEXT_BLOCK_FONT = new Font("Book Antiqua Bold Italic", 14);

    //отступ сверху для блока количество страниц
    private static final double TEXT_BOTTOM_PADDING = 400;

    //шрифт блока количество страниц
    private static final Font COUNT_PAGE_FONT = new Font("Book Antiqua Bold Italic", 10);

    //отображаемый текстовый блок
    private Text displayText = new Text();

    //отображение текста количества страниц
    private Text countPageText = new Text();

    //лист стрингов, в которые преобразуется содержание текстовых файлов
    private ArrayListIndex<String> mapTextString = new ArrayListIndex<>();

    //лист стрингов с текстом, подходящим по размеру под заданный текстовый блок
    private ArrayListIndex<String> textPaneList = new ArrayListIndex<>();

    public static double МAP_PANE_TEXT_WIDTH_MAX = 640;


    public MapPaneText(final ArrayList<File> textFiles) {
        super(TEXT_BACKGROUND_FILE, МAP_PANE_TEXT_WIDTH_MAX, 0);

        this.setLayoutX(TABLE_WIDTH - this.getPrefWidth() - 200);
        this.setLayoutY(TABLE_HEIGHT - this.getPrefHeight());

        //лист текстовых файлов сражения
        ArrayList<File> textFile = new ArrayList<>(textFiles);

        for (int i = 0; i < textFile.size(); i++) {
            mapTextString.add(readingFileIntoString(textFile.get(i)));
        }

        setDisplayText(0);

        mouseAction();

        this.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);");

        this.getChildren().addAll(displayText, countPageText);
    }


    public void setDisplayText(final int mapTextStringIndex) {

        String currentTextString = mapTextString.get(mapTextStringIndex);

        TextPane textPane = new TextPane();

        textPaneList.clear();
        textPaneList.addAll(textPane.breakTextIntoBlocks(currentTextString,
                this.getPrefWidth() * 0.8,
                TEXT_BLOCK_HEIGHT,
                TEXT_BLOCK_FONT));

        displayText.setWrappingWidth(this.getPrefWidth() * 0.8);
        displayText.setFont(TEXT_BLOCK_FONT);
        displayText.setX(this.getPrefWidth() * 0.1);
        displayText.setY(TEXT_TOP_PADDING);
        displayText.setTextOrigin(VPos.TOP);

        displayText.setText(textPaneList.getFirstElement());

        countPageText.setFont(COUNT_PAGE_FONT);
        countPageText.setX(this.getPrefWidth() * 0.1);
        countPageText.setY(TEXT_BOTTOM_PADDING);
        countPageText.setTextOrigin(VPos.BOTTOM);

        setCountPageText();
    }


    //методы смены текста в текстовом блоке
    private void setNextTextBlock() {
        displayText.setText(textPaneList.getNextElement());
        setCountPageText();
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

   
    
    private void mouseAction() {

        //перемещение письма
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
                    "-fx-effect: dropshadow(gaussian, black, 50, 0, -10, 10);"
            );
        });

        this.setOnMouseReleased(mouseEvent -> {

            if (Math.abs(this.getRelocationCoordinates().getXDelta()) +
                    Math.abs(this.getRelocationCoordinates().getYDelta()) > 2d) {
                isDragAndDrop = true;
            }

            this.setLayoutX(this.getLayoutX() + this.getTranslateX());
            this.setLayoutY(this.getLayoutY() + this.getTranslateY());
            this.setTranslateX(0);
            this.setTranslateY(0);

//            locationWithinSectionPanel(this);

            this.setStyle(
                    "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 2);"
            );
            this.clearRelocationCoordinates();

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

                //Двойной щелчок левой кнопкой приводит к переносу текста на последнюю страницу
                if (event.getClickCount() == 2) {
                    this.setLastTextBlock();
                }

                //Двойной щелчок правой кнопкой приводит к возвращению текста на первую страницу
                if (event.getButton() == MouseButton.SECONDARY && event.getClickCount() == 2) {
                    this.setFirstTextBlock();
                }
            }

            isDragAndDrop = false;
        });
    }
    
    
    
    

    private void setCountPageText() {
        countPageText.setText("страница: " + (textPaneList.getCurrentIndex() + 1) + " / " + textPaneList.size());
    }
}
