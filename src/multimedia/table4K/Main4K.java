package table4K;

import javafx.application.Application;
import javafx.stage.Stage;

import static table4K.model.SectionSelection.sectionSelectionInitialize;
import static table4K.view.MainView.mainWindowInitialize;



public class Main4K extends Application{

    public static final String RESOURCES_PATH = getResourcesPath();

    private static String getResourcesPath() {
        if (osWindows()) {
            return "C://museumResources/";
        }

        if (osLinux()) {
            return "/home/xiramant/museumResources/";
        }

        System.out.println("Операционная система не поддерживается.");
        return "";
    }

    private static Boolean osWindows() {
        return System.getProperty("os.name").startsWith("Win");
    }

    private static Boolean osLinux() {
        return System.getProperty("os.name").startsWith("Lin");
    }



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainWindowInitialize(primaryStage);
        sectionSelectionInitialize();
    }

}