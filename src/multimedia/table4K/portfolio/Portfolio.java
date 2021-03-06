package table4K.portfolio;

import general.FileFormat;
import general.GroupingOrder;
import general.SectionKey;
import table4K.view.MainView;

import java.io.File;
import java.util.ArrayList;

import static general.FileProcessing.getDirKey;
import static general.FileProcessing.getFiles;
import static general.InitialLocation.initialPositionElementsForArea;
import static table4K.BackHome.returnHome;
import static table4K.Main4K.*;

public class Portfolio {

    private static final SectionKey PORTFOLIO_KEY = SectionKey.PORTFOLIO;

    //Личные дела героев Советского союза:
    //внешний лист - личное дело;
    //внутренний лист - фото из личного дела.
    private static ArrayList<ArrayList<File>> heroImageFiles;

    //Личные дела героев Советского союза:
    //внешний лист - личное дело;
    //внутренний лист - текст из личного дела.
    private static ArrayList<ArrayList<File>> heroTextFiles;

    //минимальный интервал между карточками героев Советского союза
    private static final double PORTFOLIO_PCP_WIDTH_SPACING_MIN = 50 / MainView.DEBUGGING_RATIO;

    //Координаты области, в которой должны располагаться карточки героев Советского союза
    private static final double PORTFOLIO_PCP_AREA_X_BEGIN = 875 / MainView.DEBUGGING_RATIO;
    private static final double PORTFOLIO_PCP_AREA_X_END = 3975 / MainView.DEBUGGING_RATIO;
    private static final double PORTFOLIO_PCP_AREA_Y_BEGIN = 1150 / MainView.DEBUGGING_RATIO;
    private static final double PORTFOLIO_PCP_AREA_Y_END = 1850 / MainView.DEBUGGING_RATIO;

    //Параметры тени для карточек героев Советского союза
    private static final String PCP_SHADOW_STILL = "-fx-effect: dropshadow(gaussian, black, 10, 0.3, -2, 3);";


    public static void setPortfolioScene() {

        MainView.setMainSceneBackground(RESOURCES_PATH + "table_4K_portfolio_initial.jpg");
        MainView.rootPane.getChildren().clear();

        //лист директорий героев Советского Союза,
        // в которых содержатся файлы для отображения на основной сцене
        ArrayList<File> fileHeroDirs = new ArrayList<>(getDirKey(PORTFOLIO_KEY, "hero"));

        heroImageFiles = new ArrayList<>(getFiles(fileHeroDirs, FileFormat.IMAGE));
        heroTextFiles = new ArrayList<>(getFiles(fileHeroDirs, FileFormat.TEXT));

        for (int i = 0; i < heroImageFiles.size(); i++) {
            PersonalCardPane temp = new PersonalCardPane(heroImageFiles.get(i), heroTextFiles.get(i));
            temp.setStyle(PCP_SHADOW_STILL);
            MainView.rootPane.getChildren().add(temp);
        }

        initialPositionElementsForArea(MainView.rootPane.getChildren(),
                PORTFOLIO_PCP_WIDTH_SPACING_MIN,
                PORTFOLIO_PCP_AREA_X_BEGIN,
                PORTFOLIO_PCP_AREA_Y_BEGIN,
                PORTFOLIO_PCP_AREA_X_END,
                PORTFOLIO_PCP_AREA_Y_END,
                GroupingOrder.TABLED);

        MainView.rootPane.getChildren().add(returnHome());
    }
}
