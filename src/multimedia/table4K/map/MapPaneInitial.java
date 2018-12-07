package table4K.map;

import general.ImagePane;
import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.util.ArrayList;

import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;
import static table4K.map.Map.*;

public class MapPaneInitial extends ImagePane{

    //лист файлов для изображений карт
    private ArrayList<File> mapImageFiles = new ArrayList<>();

    //лист файлов для текстовых описаний карт
    private ArrayList<File> mapTextFiles = new ArrayList<>();

    //максимальная ширина текстового блока
    // для начальной сцены
    public static double MAP_INITIAL_PANE_WIDTH_MAX = 530 / debuggingRatio;

    //отступ текстового блока от верха
    public static double MAP_INITIAL_TEXT_TOP_PADDING = 350 / debuggingRatio;

    //размер шрифта для текстового блока начальной сцены раздела Карты
    public static double MAP_INITIAL_TEXT_FONT = 50 / debuggingRatio;

    //шрифт текстового блока
    //!!! не делать static иначе размер шрифта не изменяется
    private Font TEXT_FONT = new Font("Book Antiqua Bold Italic", MAP_INITIAL_TEXT_FONT);


    public MapPaneInitial(final String operationName, ArrayList<File> mapImageFiles, ArrayList<File> mapTextFiles) {

        super(TEXT_BACKGROUND_FILE, MAP_INITIAL_PANE_WIDTH_MAX, 0);

        this.mapImageFiles.addAll(mapImageFiles);
        this.mapTextFiles.addAll(mapTextFiles);

        Text textOperationName = new Text(operationName);

        textOperationName.setFont(TEXT_FONT);
        textOperationName.setWrappingWidth(this.getPrefWidth() * 0.8);
        textOperationName.setX(this.getPrefWidth() * 0.1);
        textOperationName.setTextAlignment(TextAlignment.CENTER);
        textOperationName.setY(MAP_INITIAL_TEXT_TOP_PADDING);
        textOperationName.setTextOrigin(VPos.CENTER);

        this.getChildren().add(textOperationName);

        this.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.4, 0, 3);");

        mapPaneInitialEvent();
    }

    //Метод обработки действий пользователя при выборе операции по клику мышкой
    public void mapPaneInitialEvent() {

        this.setOnMouseClicked(event -> action());

        this.setOnTouchPressed(event -> {
            action();

            try {
                wait(1000);
            } catch (InterruptedException e) {
                System.out.println("проблема с установкой задержки в классе MapPaneInitial при отпускании тача");
            }
        });
    }

    private void action() {

        MapPaneImage map = new MapPaneImage(mapImageFiles);
        MapPaneText text = new MapPaneText(mapTextFiles);

        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(map, text, returnHome());
    }
}
