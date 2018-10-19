package table;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    //путь к внешней папке с ресурсами проекта для музея по мультимедиа столу
    final String resourcesPath = "/home/xiramant/museumResources/table/";

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = new Pane();
        primaryStage.initStyle(StageStyle.UNDECORATED);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("file:" + resourcesPath + "css/sceneStyle.css");

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
