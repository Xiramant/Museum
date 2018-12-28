package table4K.map;

import general.*;
import javafx.geometry.VPos;
import javafx.scene.input.InputEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

import static general.TextProcessing.readingFileIntoString;
import static table4K.Main4K.*;
import static table4K.map.Map.*;

public class MapPaneText extends ImagePaneIteration {

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

        setRestrCoor(MAP_INITIAL_AREA_X_BEGIN,
                MAP_INITIAL_AREA_Y_BEGIN,
                MAP_INITIAL_AREA_X_END + this.getPrefWidth(),
                MAP_INITIAL_AREA_Y_END + this.getPrefHeight());

        mouseEvent();
        touchEvent();

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
            setTextBlock(textPaneList.getNextElement());
        } else {
            setTextBlock(textPaneList.getFirstElement());
        }
    }

    private void setTextBlock(final String text) {
        displayText.setText(text);
        setCountPageText();
    }


    @Override
    protected void mouseEvent() {
        super.mousePressed();
        super.mouseDragged(MAP_PANE_TEXT_SHADOW_DRAG_AND_DROP);
        this.mouseReleased(MAP_PANE_TEXT_SHADOW_NORMAL);
    }

    @Override
    protected void mouseReleased(final String style) {
        this.setOnMouseReleased(event -> releasedAction(event, style));
    }

    @Override
    protected void mouseReleased() {
        mouseReleased("");
    }

    @Override
    protected void touchEvent() {
        super.touchPressed();
        super.touchMoved(MAP_PANE_TEXT_SHADOW_DRAG_AND_DROP);
        this.touchReleased(MAP_PANE_TEXT_SHADOW_NORMAL);
    }

    @Override
    protected void touchReleased(final String style) {
        this.setOnTouchReleased(event -> releasedAction(event, style));
    }

    @Override
    protected void touchReleased() {
        touchReleased("");
    }

    @Override
    public void setChildAction() {
        this.setNextTextBlock();
    }

    @Override
    protected void releasedAction(final InputEvent event, final String style) {
        super.releasedAction(event, style);
    }

}
