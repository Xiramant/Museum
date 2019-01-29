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
    public static Pane rootPane;

    public static void mainWindowInitialize(final Stage primaryStage) {
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

    //отображение на корневой панели переданного списка графических элементов
    public static void setRootPaneScene(final Image BackgroundArg, final ArrayList<Node> graphicElementsArg) {
        changeRootPaneBackground(BackgroundArg);
        rootPane.getChildren().clear();
        rootPane.getChildren().addAll(graphicElementsArg);
    }

    //замена изображения подложки корневой панели на изображение,
    // переданное в качестве аргумента
    public static void changeRootPaneBackground(final String imagePath) {
        rootPane.setBackground(new Background(new BackgroundImage(
                new Image("file:" + imagePath), NO_REPEAT, NO_REPEAT, DEFAULT,
                new BackgroundSize(TABLE_WIDTH, TABLE_HEIGHT, false, false, false, false))));
    }

    //замена изображения подложки корневой панели на изображение,
    // переданное в качестве аргумента
    public static void changeRootPaneBackground(final Image backgroundArg) {
        rootPane.setBackground(new Background(new BackgroundImage(
                backgroundArg, NO_REPEAT, NO_REPEAT, DEFAULT,
                new BackgroundSize(TABLE_WIDTH, TABLE_HEIGHT, false, false, false, false))));
    }

}
