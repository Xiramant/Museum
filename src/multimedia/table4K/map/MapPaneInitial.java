package table4K.map;

import general.ImagePane;
import general.SectionKey;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.input.InputEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import table4K.view.MainView;

import java.io.File;
import java.util.ArrayList;

import static general.TouchWait.eventDelayBegin;
import static table4K.BackHome.*;
import static table4K.controller.ControllerParameters.isEventPermission;
import static table4K.map.Map.*;

public class MapPaneInitial extends ImagePane{

    //лист файлов для изображений карт
    private ArrayList<File> mapImageFiles = new ArrayList<>();

    //лист файлов для текстовых описаний карт
    private ArrayList<File> mapTextFiles = new ArrayList<>();

    //максимальная ширина текстового блока
    // для начальной сцены
    private static final double MAP_INITIAL_PANE_WIDTH_MAX = 530 / MainView.DEBUGGING_RATIO;

    //отступ текстового блока от верха
    private static final double MAP_INITIAL_TEXT_TOP_PADDING = 350 / MainView.DEBUGGING_RATIO;

    //размер шрифта для текстового блока начальной сцены раздела Карты
    private static final double MAP_INITIAL_TEXT_FONT = 50 / MainView.DEBUGGING_RATIO;

    //шрифт текстового блока
    //!!! не делать static иначе размер шрифта не изменяется
    private Font TEXT_FONT = new Font("Book Antiqua Bold Italic", MAP_INITIAL_TEXT_FONT);

    private static final String MAP_PANE_INITIAL_SHADOW = "-fx-effect: dropshadow(gaussian, black, 10, 0.4, 0, 3);";

    private static final double MAP_PANE_INITIAL_TEXT_WIDTH_PERCENTAGE = 0.8;


    public MapPaneInitial(final String operationName, ArrayList<File> mapImageFiles, ArrayList<File> mapTextFiles) {

        super(TEXT_BACKGROUND_FILE, MAP_INITIAL_PANE_WIDTH_MAX, 0);

        this.mapImageFiles.addAll(mapImageFiles);
        this.mapTextFiles.addAll(mapTextFiles);

        Text textOperationName = new Text(operationName);

        textOperationName.setFont(TEXT_FONT);
        textOperationName.setWrappingWidth(this.getPrefWidth() * MAP_PANE_INITIAL_TEXT_WIDTH_PERCENTAGE);
        textOperationName.setX((this.getPrefWidth() - textOperationName.getWrappingWidth()) / 2);
        textOperationName.setTextAlignment(TextAlignment.CENTER);
        textOperationName.setY(MAP_INITIAL_TEXT_TOP_PADDING);
        textOperationName.setTextOrigin(VPos.CENTER);

        this.getChildren().add(textOperationName);

        this.setStyle(MAP_PANE_INITIAL_SHADOW);

        this.setOnMouseReleased(event -> action(event));
        this.setOnTouchReleased(event -> action(event));

    }


    private void action(final InputEvent event) {

        if (isEventPermission(event)) {

            MapPaneText text = new MapPaneText(mapTextFiles);
            MapPaneImage map = new MapPaneImage(mapImageFiles, text);

            Group mapGroup = new Group();
            mapGroup.getChildren().addAll(map, text);

            MainView.rootPane.getChildren().clear();
            MainView.rootPane.getChildren().addAll(mapGroup, returnBack(SectionKey.MAP), returnHome());

            eventDelayBegin();
        }
    }
}
