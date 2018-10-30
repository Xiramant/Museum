package table.section.map;

import javafx.geometry.VPos;
import javafx.scene.text.*;
import table.model.ImagePane;

import static table.section.map.Map.TEXT_BACKGROUND_FILE;

public class InitialMapPane extends ImagePane {

    //максимальная ширина панели с текстом
    private static final double PANE_WIDTH_MAX = 300;

    //отступ текстового блока от верха
    private static final double TEXT_TOP_PADDING = 200;

    //шрифт текстового блока
    private static final Font TEXT_FONT = new Font("Book Antiqua Bold Italic", 30);


    public InitialMapPane(final String operationName) {

        super(TEXT_BACKGROUND_FILE, PANE_WIDTH_MAX, 0);

        Text textOperationName = new Text(operationName);

        textOperationName.setFont(TEXT_FONT);
        textOperationName.setWrappingWidth(this.getPrefWidth() * 0.8);
        textOperationName.setX(this.getPrefWidth() * 0.1);
        textOperationName.setTextAlignment(TextAlignment.CENTER);
        textOperationName.setY(TEXT_TOP_PADDING);
        textOperationName.setTextOrigin(VPos.CENTER);

        this.getChildren().add(textOperationName);
    }
}
