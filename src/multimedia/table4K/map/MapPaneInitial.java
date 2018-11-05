package table4K.map;

import general.ImagePane;
import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import static table4K.map.Map.TEXT_BACKGROUND_FILE;

public class MapPaneInitial extends ImagePane{

    //максимальная ширина текстового блока
    // для начальной сцены
    public static double MAP_INITIAL_PANE_WIDTH_MAX = 530;

    //отступ текстового блока от верха
    public static double MAP_INITIAL_TEXT_TOP_PADDING = 350;

    //размер шрифта для текстового блока начальной сцены раздела Карты
    public static double MAP_INITIAL_TEXT_FONT = 50;

    //шрифт текстового блока
    //!!! не делать static иначе размер шрифта не изменяется
    private Font TEXT_FONT = new Font("Book Antiqua Bold Italic", MAP_INITIAL_TEXT_FONT);


    public MapPaneInitial(final String operationName) {

        super(TEXT_BACKGROUND_FILE, MAP_INITIAL_PANE_WIDTH_MAX, 0);

        Text textOperationName = new Text(operationName);

        textOperationName.setFont(TEXT_FONT);
        textOperationName.setWrappingWidth(this.getPrefWidth() * 0.8);
        textOperationName.setX(this.getPrefWidth() * 0.1);
        textOperationName.setTextAlignment(TextAlignment.CENTER);
        textOperationName.setY(MAP_INITIAL_TEXT_TOP_PADDING);
        textOperationName.setTextOrigin(VPos.CENTER);

        this.getChildren().add(textOperationName);

        this.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, 0, 2);");
    }
}
