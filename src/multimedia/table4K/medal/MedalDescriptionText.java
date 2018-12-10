package table4K.medal;

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
import static table4K.Main4K.RESOURCES_PATH;
import static table4K.Main4K.debuggingRatio;

public class MedalDescriptionText extends ImagePane {

    private ArrayListIndex<String> textPaneList = new ArrayListIndex<>();

    private Text displayText = new Text();

    public MedalDescriptionText(final File textFileDescription) {
        super(new File(RESOURCES_PATH + "medal/description.jpg"), 0, 900 / debuggingRatio);

        String text = readingFileIntoString(textFileDescription);

        TextPane textPane = new TextPane();

        textPaneList.addAll(textPane.breakTextIntoBlocks(text,
                this.getPrefWidth() * 0.8,
                700 / debuggingRatio,
                new Font("Book Antiqua Bold Italic", 22 / debuggingRatio)));

        displayText.setWrappingWidth(this.getPrefWidth() * 0.8);
        displayText.setFont(new Font("Book Antiqua Bold Italic", 22 / debuggingRatio));
        displayText.setX(this.getPrefWidth() * 0.1);
        displayText.setY(100 / debuggingRatio);
        displayText.setTextOrigin(VPos.TOP);

        displayText.setText(textPaneList.getFirstElement());

//        caseCountPageText.setFont(МAP_PANE_TEXT_FONT_PAGE_NUMBER);
//        caseCountPageText.setX(this.getPrefWidth() * 0.1);
//        caseCountPageText.setY(МAP_PANE_TEXT_TOP_PADDING_PAGE_NUMBER);
//        caseCountPageText.setTextOrigin(VPos.BOTTOM);
//
//        setCountPageText();

        mouseAction();
//        touchAction();

        this.getChildren().addAll(displayText);
    }

    //Обработка событий Мыши
    private void mouseAction() {

        //действия при клике мышкой
        this.setOnMouseClicked(event -> {

            //нажатие левой кнопки приводит к листанию текста вперед
            if (event.getButton() == MouseButton.PRIMARY) {
                this.setNextTextBlock();
            }
        });
    }

    //методы смены текста в текстовом блоке
    private void setNextTextBlock() {
        if(textPaneList.hasNextElement()) {
            displayText.setText(textPaneList.getNextElement());
        } else {
            setFirstTextBlock();
        }
    }

    private void setFirstTextBlock() {
        displayText.setText(textPaneList.getFirstElement());
    }

}
