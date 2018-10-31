package table4K;

import general.ImagePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class Main4K extends Application{

    //Флаг дебаггинга,
    // по которому все размеры уменьшаются в 2 раза,
    // чтобы программу можно было дебажить на обычном мониторе
    private boolean debugging = true;

    //путь к директории с файлами
    public static final String RESOURCES_PATH = "C://museumResources/";

    //Размеры интерактивного стола
    public static double TABLE_WIDTH = 4096;
    public static double TABLE_HEIGHT = 2160;

    //Размеры иконки раздела Карты
    private static double MAP_ICON_WIDTH_MAX = 1547;
    private static double MAP_ICON_HEIGHT_MAX = 972;
    private static double MAP_ICON_X = 0;
    private static double MAP_ICON_Y = 107;

    //Размеры иконки раздела Письма
    private static double MAIL_ICON_WIDTH_MAX = 579;
    private static double MAIL_ICON_HEIGHT_MAX = 693;
    private static double MAIL_ICON_X = 429;
    private static double MAIL_ICON_Y = 1314;

    //Размеры иконки раздела Личные дела
    private static double PORTFOLIO_ICON_WIDTH_MAX = 752;
    private static double PORTFOLIO_ICON_HEIGHT_MAX = 977;
    private static double PORTFOLIO_ICON_X = 1899;
    private static double PORTFOLIO_ICON_Y = 684;

    //Размеры иконки раздела Медали
    private static double MEDAL_ICON_WIDTH_MAX = 526;
    private static double MEDAL_ICON_HEIGHT_MAX = 424;
    private static double MEDAL_ICON_X = 3385;
    private static double MEDAL_ICON_Y = 163;

    //Размеры иконки раздела Медали
    private static double BOOK_ICON_WIDTH_MAX = 672;
    private static double BOOK_ICON_HEIGHT_MAX = 895;
    private static double BOOK_ICON_X = 3264;
    private static double BOOK_ICON_Y = 1046;

    {
        if (debugging) {
            TABLE_WIDTH /= 2;
            TABLE_HEIGHT /= 2;

            MAP_ICON_WIDTH_MAX /= 2;
            MAP_ICON_HEIGHT_MAX /=2;
            MAP_ICON_X /= 2;
            MAP_ICON_Y /= 2;

            MAIL_ICON_WIDTH_MAX /= 2;
            MAIL_ICON_HEIGHT_MAX /=2;
            MAIL_ICON_X /= 2;
            MAIL_ICON_Y /= 2;

            PORTFOLIO_ICON_WIDTH_MAX /= 2;
            PORTFOLIO_ICON_HEIGHT_MAX /= 2;
            PORTFOLIO_ICON_X /= 2;
            PORTFOLIO_ICON_Y /= 2;

            MEDAL_ICON_WIDTH_MAX /= 2;
            MEDAL_ICON_HEIGHT_MAX /= 2;
            MEDAL_ICON_X /= 2;
            MEDAL_ICON_Y /= 2;

            BOOK_ICON_WIDTH_MAX /= 2;
            BOOK_ICON_HEIGHT_MAX /= 2;
            BOOK_ICON_X /= 2;
            BOOK_ICON_Y /= 2;
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

        Pane mainPane = new Pane();
        mainPane.setPrefWidth(TABLE_WIDTH);
        mainPane.setPrefHeight(TABLE_HEIGHT);
        root.setCenter(mainPane);

        //создание иконок для разделов
        ImagePane map = new ImagePane(new File(RESOURCES_PATH + "icon/map_icon.png"), MAP_ICON_WIDTH_MAX, MAP_ICON_HEIGHT_MAX);
        map.setLayoutX(MAP_ICON_X);
        map.setLayoutY(MAP_ICON_Y);
        map.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -4, 4);");

        ImagePane mail = new ImagePane(new File(RESOURCES_PATH + "icon/mail_icon.png"), MAIL_ICON_WIDTH_MAX, MAIL_ICON_HEIGHT_MAX);
        mail.setLayoutX(MAIL_ICON_X);
        mail.setLayoutY(MAIL_ICON_Y);
        mail.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -4, 4);");

        ImagePane portfolio = new ImagePane(new File(RESOURCES_PATH + "icon/portfolio_icon.png"), PORTFOLIO_ICON_WIDTH_MAX, PORTFOLIO_ICON_HEIGHT_MAX);
        portfolio.setLayoutX(PORTFOLIO_ICON_X);
        portfolio.setLayoutY(PORTFOLIO_ICON_Y);
        portfolio.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, 0, 5);");

        ImagePane medal = new ImagePane(new File(RESOURCES_PATH + "icon/medal_icon.png"), MEDAL_ICON_WIDTH_MAX, MEDAL_ICON_HEIGHT_MAX);
        medal.setLayoutX(MEDAL_ICON_X);
        medal.setLayoutY(MEDAL_ICON_Y);
        medal.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, 3, 2);");

        ImagePane book = new ImagePane(new File(RESOURCES_PATH + "icon/book_icon.png"), BOOK_ICON_WIDTH_MAX, BOOK_ICON_HEIGHT_MAX);
        book.setLayoutX(BOOK_ICON_X);
        book.setLayoutY(BOOK_ICON_Y);
        book.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, 8, 8);");


        mainPane.getChildren().addAll(map, mail, portfolio, medal, book);

        //создание сцены для родительской панели и отображение
        root.setBackground(new Background(new BackgroundImage(
                new Image("file:" + RESOURCES_PATH + "table_with_lamp.jpg"), NO_REPEAT, NO_REPEAT, CENTER,
                new BackgroundSize(TABLE_WIDTH, TABLE_HEIGHT, false, false, false, false))));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);

    }

}
