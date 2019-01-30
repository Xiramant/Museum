package table4K.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;


public class MainView {

    //величина на которые уменьшаются все размеры изображений,
    // чтобы программу можно было дебажить на обычном мониторе
    public static final double DEBUGGING_RATIO = 2;

    //Размеры интерактивного стола
    public static final double TABLE_HEIGHT = 2160 / DEBUGGING_RATIO;
    public static final double TABLE_WIDTH = 4096 / DEBUGGING_RATIO;

    //Основная панель с содержимым
    // !!!!! после перехода всех классов к использованию setMainScene
    // сделать поле с доступом private
    public static Pane rootPane;

    public static void initializeMainWindow(final Stage primaryStage) {
        createRootPane();
        setPrimaryStage(primaryStage);
    }

    //создание родительской панели
    private static void createRootPane(){
        rootPane = new Pane();
        rootPane.setPrefWidth(TABLE_WIDTH);
        rootPane.setPrefHeight(TABLE_HEIGHT);
    }

    //установка параметров primaryStage
    private static void setPrimaryStage(final Stage primaryStage) {
        Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        primaryStage.setFullScreen(true);
    }

    //установка/изменение основной сцены
    public static void setMainScene(final Image BackgroundArg, final ArrayList<Node> graphicElementsArg) {
        setMainSceneBackground(BackgroundArg);
        rootPane.getChildren().clear();
        rootPane.getChildren().addAll(graphicElementsArg);
    }

    //замена фона основной сцены
    private static void setMainSceneBackground(final Image backgroundArg) {
        rootPane.setBackground(new Background(new BackgroundImage(
                backgroundArg, NO_REPEAT, NO_REPEAT, DEFAULT,
                new BackgroundSize(TABLE_WIDTH, TABLE_HEIGHT, false, false, false, false))));
    }

    //замена фона основной сцены
    // !!!!! старый метод, который следует удалить после перехода всех обращающихся классов к новому методу
    public static void setMainSceneBackground(final String imagePath) {
        setMainSceneBackground(new Image("file:" + imagePath));
    }

}