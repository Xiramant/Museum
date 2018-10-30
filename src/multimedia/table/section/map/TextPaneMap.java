package table.section.map;

import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import table.model.ArrayListIndex;
import table.model.ImagePane;
import table.model.RelocationCoordinates;
import table.model.TextPane;

import static table.section.map.Map.TEXT_BACKGROUND_FILE;

public class TextPaneMap extends ImagePane {

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

    //лист стрингов с текстом, подходящим по размеру под заданный текстовый блок
    private ArrayListIndex<String> textPaneList = new ArrayListIndex<>();

    //Поле для хранения параметров расположения панели
    // при ее перемещении методом drag and drop
    private RelocationCoordinates relocCoord = new RelocationCoordinates();

    public RelocationCoordinates getRelocCoord() {
        return relocCoord;
    }

    public void setRelocCoord(final RelocationCoordinates relocCoord) {
        this.relocCoord = relocCoord;
    }


    public TextPaneMap(final String text) {

        super(TEXT_BACKGROUND_FILE);

        TextPane textPane = new TextPane();

        textPaneList.addAll(textPane.breakTextIntoBlocks(text,
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

        this.getChildren().addAll(displayText, countPageText);
    }

    //методы смены текста в текстовом блоке
    public void setNextTextBlock() {
        displayText.setText(textPaneList.getNextElement());
        setCountPageText();
    }

    public void setPrevTextBlock() {
        displayText.setText(textPaneList.getPrevElement());
        setCountPageText();
    }

    public void setFirstTextBlock() {
        displayText.setText(textPaneList.getFirstElement());
        setCountPageText();
    }

    public void setLastTextBlock() {
        displayText.setText(textPaneList.getLastElement());
        setCountPageText();
    }

    private void setCountPageText() {
        countPageText.setText("страница: " + (textPaneList.getCurrentIndex() + 1) + " / " + textPaneList.size());
    }

    //Метод по очистке данных в поле relocCoord
    public void clearRelocCoord() {
        setRelocCoord(new RelocationCoordinates());
    }
}
