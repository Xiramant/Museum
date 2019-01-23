package table4K;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import table4K.ui.MainView;


public class Main4K extends Application{

    //путь к директории с файлами
    public static final String RESOURCES_PATH = getResourcesPath();

    private static String getResourcesPath() {
        return (isWin())?
                "C://museumResources/":
                "/home/xiramant/museumResources/";
    }

    private static Boolean isWin() {
        return System.getProperty("os.name").startsWith("Win");
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainView.mainWindowInitialize(primaryStage);
    }

}