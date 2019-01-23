package table4K.ui;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import table4K.Main4K;
import table4K.controller.IconController;

import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class MainView {

    //величина на которые уменьшаются все размеры изображений,
    // чтобы программу можно было дебажить на обычном мониторе
    public static final double DEBUGGING_RATIO = 2;

    //Размеры интерактивного стола
    public static double TABLE_HEIGHT = 2160 / DEBUGGING_RATIO;
    public static double TABLE_WIDTH = 4096 / DEBUGGING_RATIO;

    //Основная панель с содержимым
    public static Pane rootPane;

    public static void mainWindowInitialize(final Stage primaryStage) {
        createRootPane();
        setPrimaryStage(primaryStage);
        setMainScene();
    }

    //создание родительской панели
    private static void createRootPane(){
        rootPane = new Pane();
        rootPane.setPrefWidth(TABLE_WIDTH);
        rootPane.setPrefHeight(TABLE_HEIGHT);
    }

    //установка параметров primaryStage
    private static void setPrimaryStage(final Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);
    }

    //установка элементов первоначальной сцены
    public static void setMainScene() {

        changeRootPaneBackground(Main4K.RESOURCES_PATH + "table_with_lamp.jpg");

        rootPane.getChildren().clear();

        IIcon mapIcon = Icon.getMap();
        IconController mapController = new IconController(mapIcon);

        IIcon mailIcon = Icon.getMail();
        IconController mailController = new IconController(mailIcon);

        IIcon portfolioIcon = Icon.getPortfolio();
        IconController portfolioController = new IconController(portfolioIcon);

        IIcon medalIcon = Icon.getMedal();
        IconController medalController = new IconController(medalIcon);

        IIcon bookIcon = Icon.getBook();
        IconController bookController = new IconController(bookIcon);

        IIcon quizIcon = Icon.getQuiz();
        IconController quizController = new IconController(quizIcon);

        IIcon filmIcon = Icon.getFilm();
        IconController filmController = new IconController(filmIcon);

        rootPane.getChildren().addAll(
                mapIcon.getIconImage(),
                mailIcon.getIconImage(),
                portfolioIcon.getIconImage(),
                medalIcon.getIconImage(),
                bookIcon.getIconImage(),
                quizIcon.getIconImage(),
                filmIcon.getIconImage());
    }

    //замена изображения подложки корневой панели на изображение,
    // переданное в качестве аргумента
    public static void changeRootPaneBackground(final String imagePath) {
        rootPane.setBackground(new Background(new BackgroundImage(
                new Image("file:" + imagePath), NO_REPEAT, NO_REPEAT, DEFAULT,
                new BackgroundSize(TABLE_WIDTH, TABLE_HEIGHT, false, false, false, false))));
    }

}
