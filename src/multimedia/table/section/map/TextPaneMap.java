package table.section.map;

import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import table.model.ArrayListIndex;
import table.model.ImagePane;
import table.model.TextPane;

import static table.section.map.Map.TEXT_BACKGROUND_FILE;

public class TextPaneMap extends ImagePane {

    //высота текстового блока
    private final double TEXT_BLOCK_HEIGHT = 330;

    //отступ сверху для текстового блока
    private final double TEXT_TOP_PADDING = 82;

    //шрифт текстового блока
    private final Font TEXT_BLOCK_FONT = new Font("Book Antiqua Bold Italic", 14);

    //лист стрингов с текстом, подходящим по размеру под заданный текстовый блок
    private ArrayListIndex<String> textPaneList = new ArrayListIndex<>();

    //отображаемый текстовый блок
    private Text displayText = new Text();


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
        displayText.setTextAlignment(TextAlignment.LEFT);
        displayText.setY(TEXT_TOP_PADDING);
        displayText.setTextOrigin(VPos.TOP);

        displayText.setText(textPaneList.getFirstElement());

        this.getChildren().add(displayText);
    }

    //методы смены текста в текстовом блоке
    public void setNextTextBlock() {
        displayText.setText(textPaneList.getNextElement());
    }

    public void setPrevTextBlock() {
        displayText.setText(textPaneList.getPrevElement());
    }

    public void setFirstTextBlock() {
        displayText.setText(textPaneList.getFirstElement());
    }

    public void setLastTextBlock() {
        displayText.setText(textPaneList.getLastElement());
    }
}
