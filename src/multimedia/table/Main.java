package table;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import table.model.IconPane;

import java.util.ArrayList;

public class Main extends Application {

    //путь к внешней папке с ресурсами проекта для музея по мультимедиа столу
    public static final String RESOURCES_PATH = "/home/xiramant/museumResources/table/";

    //Размеры интерактивного стола
    static final Double TABLE_WIDTH = 1920d;
    static final Double TABLE_HEIGHT = 1080d;

    //высота верхней пустой области стола
    static final Double TABLE_TOP_SECTION_HEIGHT = 80d;

    //размеры левой области стола с иконками секций
    static final Double TABLE_LEFT_SECTION_WIDTH = 400d;
    static final Double TABLE_LEFT_SECTION_HEIGHT = TABLE_HEIGHT - TABLE_TOP_SECTION_HEIGHT;

    //лист ключевых слов разделов
    // из которых будет формироваться интерактивный стол
    static ArrayList<String> section = new ArrayList<>();
    static {
        section.add("map");
        section.add("case");
        section.add("mail");
        section.add("medal");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        //создание родительской панели
        BorderPane root = new BorderPane();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        root.setPrefWidth(TABLE_WIDTH);
        root.setPrefHeight(TABLE_HEIGHT);


        //задание верхней области стола,
        // которая будет оставаться путой
        // (в этом месте предполагается размещение элементов скина стола,
        // которые не должны быть закрыты интерактивными элементами)
        Pane topEmpty = new Pane();
        topEmpty.setPrefHeight(TABLE_TOP_SECTION_HEIGHT);
        topEmpty.setMinHeight(topEmpty.getPrefHeight());
        topEmpty.setMaxHeight(topEmpty.getPrefHeight());
        root.setTop(topEmpty);


        //задание левой вертикальной области стола
        // в которой будут размещены иконки для выбора нужной секции:
        // карты, личные дела, письма, медали

        //лист панелей иконок секций
        ArrayList<Pane> iconsPaneList = new ArrayList<>();
        for (String sectionKeyWord: section) {
            iconsPaneList.add(new IconPane(sectionKeyWord));
        }

        //вычисление вертикального расстояния между иконками разделов
        VBox icon = new VBox(getPaneSpacing(iconsPaneList, TABLE_LEFT_SECTION_HEIGHT));
        icon.setPrefWidth(TABLE_LEFT_SECTION_WIDTH);
        icon.setPrefHeight(TABLE_LEFT_SECTION_HEIGHT);
        icon.setAlignment(Pos.CENTER);

        for (Pane pane: iconsPaneList) {
            icon.getChildren().add(pane);
        }

        root.setLeft(icon);


        //создание сцены для родительской панели и отображение
        Scene scene = new Scene(root);
        scene.getStylesheets().add("file:" + RESOURCES_PATH + "css/sceneStyle.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);

    }

    //метод возвращающий расстояние между иконками для их равномерного распределения
    private static int getPaneSpacing(final ArrayList<Pane> iconsPaneList, final double fieldSize) {

        int sumIconPaneHeight = 0;

        for (Pane iconPane: iconsPaneList) {
            sumIconPaneHeight += iconPane.getPrefHeight();
        }

        return (int) ((fieldSize - sumIconPaneHeight) /iconsPaneList.size());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
