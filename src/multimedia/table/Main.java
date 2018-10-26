package table;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import table.model.ImagePane;
import table.model.SectionKey;

import java.util.ArrayList;

import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static table.model.Mail.*;
import static table.model.SectionKey.*;

public class Main extends Application {

    //путь к внешней папке с ресурсами проекта для музея по мультимедиа столу
    public static final String RESOURCES_PATH;

    //Размеры интерактивного стола
    public static final double TABLE_WIDTH = 1920;
    public static final double TABLE_HEIGHT = 1080;

    //высота верхней пустой области стола
    static final double TABLE_TOP_SECTION_HEIGHT = 80;

    //размеры левой области стола с иконками секций
    static final double TABLE_LEFT_SECTION_WIDTH = 400;
    static final double TABLE_LEFT_SECTION_HEIGHT = TABLE_HEIGHT - TABLE_TOP_SECTION_HEIGHT;

    //максимальный размер иконок секций
    public static final double SECTION_ICON_WIDTH_MAX = 330;
    public static final double SECTION_ICON_HEIGHT_MAX = 300;

    //максимальная ширина писем в секции
    public static final double SECTION_MAIL_WIDTH_MAX = 300;
    public static final double SECTION_MAIL_WIDTH_SPACING_MIN = 50;

    //размеры центральной области стола с основным содержимым
    public static final double TABLE_CENTER_SECTION_WIDTH = TABLE_WIDTH - TABLE_LEFT_SECTION_WIDTH;
    public static final double TABLE_CENTER_SECTION_HEIGHT = TABLE_HEIGHT - TABLE_TOP_SECTION_HEIGHT;

    //Панель для отображения содержания раздела (секции)
    public static Pane sectionPanel = new Pane();

    //лист ключевых слов разделов
    // из которых будет формироваться интерактивный стол
    static ArrayList<SectionKey> section = new ArrayList<>();

    static {
        section.add(MAP);
        section.add(CASE);
        section.add(MAIL);
        section.add(MEDAL);

        //Задание пути к ресурсам на основании ОС
        if (isWin()) {
            RESOURCES_PATH = "C://museumResources/";
        } else {
            RESOURCES_PATH = "/home/xiramant/museumResources/table/";
        }
    }

    public static void main(String[] args) {
        launch(args);
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

        //создание центральной панели для отображения содержания раздела
//        StackPane sectionPanel = new StackPane();
        sectionPanel.setPrefWidth(TABLE_CENTER_SECTION_WIDTH);
        sectionPanel.setPrefHeight(TABLE_CENTER_SECTION_HEIGHT);
//        sectionPanel.setAlignment(Pos.CENTER);
        root.setCenter(sectionPanel);


        //задание левой вертикальной области стола
        // в которой будут размещены иконки для выбора нужной секции:
        // карты, личные дела, письма, медали

        //лист панелей иконок секций
        ArrayList<Pane> iconsPaneList = new ArrayList<>();
        for (SectionKey sectionKey: section) {
            iconsPaneList.add(new ImagePane(sectionKey, SECTION_ICON_WIDTH_MAX, SECTION_ICON_HEIGHT_MAX));
        }

        //вычисление вертикального расстояния между иконками разделов
        VBox icon = new VBox(getPaneSpacing(iconsPaneList, TABLE_LEFT_SECTION_HEIGHT));
        icon.setPrefWidth(TABLE_LEFT_SECTION_WIDTH);
        icon.setPrefHeight(TABLE_LEFT_SECTION_HEIGHT);
        icon.setAlignment(Pos.CENTER);
        icon.setFillWidth(false);
        root.setLeft(icon);

        for (Pane pane: iconsPaneList) {
            icon.getChildren().add(pane);
        }

        //обработка клика мышью на иконке раздела
        for (int i = 0; i < iconsPaneList.size(); i++) {

            SectionKey keyWord = section.get(i);

            icon.getChildren().get(i).setOnMouseClicked(event -> {

                switch (keyWord) {
                    case MAP:
                        System.out.println("Выбран раздел Карты");
                        setPanelSection(MAP);
                        break;
                    case CASE:
                        System.out.println("Выбран раздел Личные дела");
                    {
                        sectionPanel.getChildren().clear();
                    }
                    break;
                    case MAIL:
                        System.out.println("Выбран раздел Письма");
                    {
                        setMailsScene();
                    }
                    break;
                    case MEDAL:
                        System.out.println("Выбран раздел Медали");
                        break;
                }
            });
        }

        //создание сцены для родительской панели и отображение
        root.setBackground(new Background(new BackgroundImage(
                new Image("file:" + RESOURCES_PATH + "table.jpg"), NO_REPEAT, NO_REPEAT, CENTER,
                new BackgroundSize(1920, 1080, false, false, false, false))));
        Scene scene = new Scene(root);
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

    private static void setPanelSection(final SectionKey key) {

//        if (key.equals(MAP)) {
//            sectionPanel.getChildren().clear();
//            sectionPanel.getChildren().add(new ImagePane(MAP));
//        }
    }

    //метод определения запущена ли программа в ОС Windows
    public static Boolean isWin() {

        return System.getProperty("os.name").startsWith("Win");
    }



}
