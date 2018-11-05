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
import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static table4K.Debugging.setDebugging;
import static table4K.mail.Mail.setMailScene;
import static table4K.book.Book.setBookScene;
import static table4K.map.Map.setMapScene;

public class Main4K extends Application{

    //Флаг дебаггинга,
    // по которому все размеры уменьшаются в 2 раза,
    // чтобы программу можно было дебажить на обычном мониторе
    private final boolean debugging = true;

    //путь к директории с файлами
    public static final String RESOURCES_PATH = "C://museumResources/";

    private static BorderPane root;

    //Основная панель с содержимым
    public static Pane mainPane;

    //Размеры интерактивного стола
    public static double TABLE_WIDTH = 4096;
    public static double TABLE_HEIGHT = 2160;

    //Иконка раздела Карты
    public static double MAP_ICON_WIDTH_MAX = 1547;
    public static double MAP_ICON_HEIGHT_MAX = 972;
    public static double MAP_ICON_X = 0;
    public static double MAP_ICON_Y = 107;

    //Иконка раздела Письма
    public static double MAIL_ICON_WIDTH_MAX = 579;
    public static double MAIL_ICON_HEIGHT_MAX = 693;
    public static double MAIL_ICON_X = 429;
    public static double MAIL_ICON_Y = 1314;

    //Иконка раздела Личные дела
    public static double PORTFOLIO_ICON_WIDTH_MAX = 752;
    public static double PORTFOLIO_ICON_HEIGHT_MAX = 977;
    public static double PORTFOLIO_ICON_X = 1899;
    public static double PORTFOLIO_ICON_Y = 684;

    //Иконка раздела Медали
    public static double MEDAL_ICON_WIDTH_MAX = 526;
    public static double MEDAL_ICON_HEIGHT_MAX = 424;
    public static double MEDAL_ICON_X = 3385;
    public static double MEDAL_ICON_Y = 163;

    //Иконка раздела Медали
    public static double BOOK_ICON_WIDTH_MAX = 672;
    public static double BOOK_ICON_HEIGHT_MAX = 895;
    public static double BOOK_ICON_X = 3264;
    public static double BOOK_ICON_Y = 1046;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        if (debugging) setDebugging();

        //создание родительской панели
        root = new BorderPane();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        root.setPrefWidth(TABLE_WIDTH);
        root.setPrefHeight(TABLE_HEIGHT);

        mainPane = new Pane();
        mainPane.setPrefWidth(TABLE_WIDTH);
        mainPane.setPrefHeight(TABLE_HEIGHT);
        root.setCenter(mainPane);
        mainPane.setLayoutX(0);
        mainPane.setLayoutY(0);

        //создание сцены для родительской панели и отображение
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);

        //установка элементов основной сцены
        setMainScene();
    }

    public static void changeRootBackground(final String imagePath) {
        root.setBackground(new Background(new BackgroundImage(
                new Image("file:" + imagePath), NO_REPEAT, NO_REPEAT, DEFAULT,
                new BackgroundSize(TABLE_WIDTH, TABLE_HEIGHT, false, false, false, false))));
    }

    public static void setMainScene() {

        changeRootBackground(RESOURCES_PATH + "table_with_lamp.jpg");

        mainPane.getChildren().clear();

        //создание иконок для разделов
        ImagePane map = new ImagePane(new File(RESOURCES_PATH + "icon/map_icon.png"), MAP_ICON_WIDTH_MAX, MAP_ICON_HEIGHT_MAX);
        map.setLayoutX(MAP_ICON_X);
        map.setLayoutY(MAP_ICON_Y);
        map.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -4, 4);");

        map.setOnMouseClicked(event -> setMapScene());
        map.setOnTouchReleased(event -> setMapScene());

        ImagePane mail = new ImagePane(new File(RESOURCES_PATH + "icon/mail_icon.png"), MAIL_ICON_WIDTH_MAX, MAIL_ICON_HEIGHT_MAX);
        mail.setLayoutX(MAIL_ICON_X);
        mail.setLayoutY(MAIL_ICON_Y);
        mail.setStyle("-fx-effect: dropshadow(gaussian, black, 10, 0.3, -4, 4);");

        mail.setOnMouseClicked(event -> setMailScene());
        mail.setOnTouchReleased(event -> setMailScene());

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

        book.setOnMouseClicked(event -> setBookScene());
        book.setOnTouchReleased(event -> setBookScene());


        mainPane.getChildren().addAll(map, mail, portfolio, medal, book);
    }

}
