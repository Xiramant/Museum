package table4K.medal;

import general.ArrayListIndex;
import general.ImagePaneIteration;
import general.TextPane;
import javafx.geometry.VPos;
import javafx.scene.input.InputEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import table4K.ui.MainView;

import java.io.File;

import static general.TextProcessing.readingFileIntoString;
import static table4K.Main4K.*;
import static table4K.medal.Medal.DESCRIPTION_HEIGHT;
import static table4K.medal.Medal.DESCRIPTION_WIDTH;

public class MedalDescriptionText extends ImagePaneIteration {

    //максимальная высота панели описания раздела Медали
    private static final double MEDAL_DESCRIPTION_TEXT_PANE_HEIGHT_MAX = 904 / MainView.DEBUGGING_RATIO;

    //ширина/высота текстового блока описания раздела Медали
    private static final double MEDAL_DESCRIPTION_TEXT_BLOCK_WIDTH = 540 / MainView.DEBUGGING_RATIO;
    private static final double MEDAL_DESCRIPTION_TEXT_BLOCK_HEIGHT = 770 / MainView.DEBUGGING_RATIO;

    //положение текстового блока описания раздела Медали
    private static final double MEDAL_DESCRIPTION_TEXT_X = 60 / MainView.DEBUGGING_RATIO;
    private static final double MEDAL_DESCRIPTION_TEXT_Y = 60 / MainView.DEBUGGING_RATIO;

    //отступ сверху для блока количество страниц
    private static final double MEDAL_DESCRIPTION_PAGE_NUMBER_Y = 850 / MainView.DEBUGGING_RATIO;

    //размер шрифта текстового блока
    private static final double MEDAL_DESCRIPTION_TEXT_BLOCK_FONT_SIZE = 28 / MainView.DEBUGGING_RATIO;

    //размер шрифта блока количество страниц
    private static final double MEDAL_DESCRIPTION_PAGE_NUMBER_FONT_SIZE = 22 / MainView.DEBUGGING_RATIO;

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
    private static final double BOTTOM_RESTRICTION = DESCRIPTION_HEIGHT ;
    private static final double LEFT_RESTRICTION = 0;
    private static final double RIGHT_RESTRICTION = DESCRIPTION_WIDTH ;

    //тени для неподвижной/перемещаемой панели
    private static final String SHADOW_STILL = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, 1, 2);";
    private static final String SHADOW_MOVED = "-fx-effect: dropshadow(gaussian, black, 50, 0, 0, 10);";


    MedalDescriptionText(final File textFileDescription) {
        super(new File(RESOURCES_PATH + "medal/description.png"), 0, MEDAL_DESCRIPTION_TEXT_PANE_HEIGHT_MAX);

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

        setRestrCoor(LEFT_RESTRICTION,
                TOP_RESTRICTION,
                RIGHT_RESTRICTION,
                BOTTOM_RESTRICTION);

        mouseEvent();
        touchEvent();

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
        super.mouseDragged(SHADOW_MOVED);
        this.mouseReleased(SHADOW_STILL);
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
        super.touchMoved(SHADOW_MOVED);
        this.touchReleased(SHADOW_STILL);
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
